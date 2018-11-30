package cn.zznlin.simple.common.exception.resolver;

import cn.zznlin.simple.common.bean.ReturnBaseJson;
import cn.zznlin.simple.common.cons.AuthorCons;
import cn.zznlin.simple.common.exception.pojo.Code404Exception;
import cn.zznlin.simple.common.exception.pojo.IgnoreException;
import cn.zznlin.simple.common.init.SystemPropertyInit;
import cn.zznlin.simple.common.senum.HttpExceptionEnum;
import cn.zznlin.simple.common.utils.MailSendUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhennan zhang
 * @date 2018/11/30 15:36
 * @description
 *   自定义全局异常处理器
 */
public class SimpleExceptionResolver implements HandlerExceptionResolver,Ordered {

    private static Logger logger = LoggerFactory.getLogger(SimpleExceptionResolver.class);

    // 给最高优先级，使用我们的默认排序
    private static int order = Ordered.HIGHEST_PRECEDENCE;

    // 是否发送异常邮件  true 发送
    private static Boolean isSendMail = SystemPropertyInit.getInstance().getBooleanProperty("isSendCodeMail",true);

    // 服务器host
    private static String host = SystemPropertyInit.getInstance().getProperty("server.host");

    private FastJsonConfig fastJsonConfig;


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = specialExceptionResolve(ex, request,handler);
        if (null == mv) {
            if(ex instanceof IgnoreException){
                return null;
            }else if(ex instanceof Code404Exception){
                // 404报错
                mv = errorResult("404", "redirect:/404.html", request);
            }else{
                saveException(handler,ex);
                // 500 报错
                mv = errorResult("500", "redirect:/500.html", request);
            }
        }
        return mv;
    }

    /**
     * 这个方法是拷贝 {@link org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver#doResolveException},
     * 加入自定义处理，实现对400， 404， 405， 406， 415， 500(参数问题导致)， 503的处理
     *
     * @param ex      异常信息
     * @param request 当前请求对象(用于判断当前请求是否为ajax请求)
     * @return 视图模型对象
     */
    private ModelAndView specialExceptionResolve(Exception ex, HttpServletRequest request,Object handler) {
        try {
            if (ex instanceof NoSuchRequestHandlingMethodException
                    || ex instanceof NoHandlerFoundException) {
                return result(HttpExceptionEnum.NOT_FOUND_EXCEPTION, request);
            }
            else if (ex instanceof HttpRequestMethodNotSupportedException) {
                return result(HttpExceptionEnum.NOT_SUPPORTED_METHOD_EXCEPTION, request);
            }
            else if (ex instanceof HttpMediaTypeNotSupportedException) {
                return result(HttpExceptionEnum.NOT_SUPPORTED_MEDIA_TYPE_EXCEPTION, request);
            }
            else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                return result(HttpExceptionEnum.NOT_ACCEPTABLE_MEDIA_TYPE_EXCEPTION, request);
            }
            else if (ex instanceof MissingPathVariableException) {
                saveException(handler,ex);
                return result(HttpExceptionEnum.MISSING_PATH_VARIABLE_EXCEPTION, request);
            }
            else if (ex instanceof MissingServletRequestParameterException) {
                return result(HttpExceptionEnum.MISSING_REQUEST_PARAMETER_EXCEPTION, request);
            }
            else if (ex instanceof ServletRequestBindingException) {
                return result(HttpExceptionEnum.REQUEST_BINDING_EXCEPTION, request);
            }
            else if (ex instanceof ConversionNotSupportedException) {
                saveException(handler,ex);
                return result(HttpExceptionEnum.NOT_SUPPORTED_CONVERSION_EXCEPTION, request);
            }
            else if (ex instanceof TypeMismatchException) {
                return result(HttpExceptionEnum.TYPE_MISMATCH_EXCEPTION, request);
            }
            else if (ex instanceof HttpMessageNotReadableException) {
                return result(HttpExceptionEnum.MESSAGE_NOT_READABLE_EXCEPTION, request);
            }
            else if (ex instanceof HttpMessageNotWritableException) {
                return result(HttpExceptionEnum.MESSAGE_NOT_WRITABLE_EXCEPTION, request);
            }
            else if (ex instanceof MethodArgumentNotValidException) {
                return result(HttpExceptionEnum.NOT_VALID_METHOD_ARGUMENT_EXCEPTION, request);
            }
            else if (ex instanceof MissingServletRequestPartException) {
                return result(HttpExceptionEnum.MISSING_REQUEST_PART_EXCEPTION, request);
            }
            else if (ex instanceof BindException) {
                return result(HttpExceptionEnum.BIND_EXCEPTION, request);
            }
        } catch (Exception handlerException) {
            logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
        }
        return null;
    }

    /**
     * 判断是否ajax请求
     *
     * @param request 请求对象
     * @return true:ajax请求  false:非ajax请求
     */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 返回错误信息
     *
     * @param message 错误信息
     * @param url     错误页url
     * @param request 请求对象
     * @return 模型视图对象
     */
    private ModelAndView errorResult(String message, String url, HttpServletRequest request) {
        logger.warn("请求处理失败，请求url=[{}], 失败原因 : {}", request.getRequestURI(), message);
        if (isAjax(request)) {
            return jsonResult(500, message);
        } else {
            return normalResult(message, url);
        }
    }

    /**
     * 返回异常信息
     *
     * @param httpException 异常信息
     * @param request 请求对象
     * @return 模型视图对象
     */
    private ModelAndView result(HttpExceptionEnum httpException, HttpServletRequest request) {
        logger.warn("请求处理失败，请求url=[{}], 失败原因 : {}", request.getRequestURI(), httpException.getMsg());
        if (isAjax(request)) {
            return jsonResult(httpException.getCode(), httpException.getMsg());
        } else {
            return normalResult(httpException.getMsg(), "/error");
        }
    }

    /**
     * 返回错误页
     *
     * @param message 错误信息
     * @param url     错误页url
     * @return 模型视图对象
     */
    private ModelAndView normalResult(String message, String url) {
        Map<String, String> model = new HashMap<String, String>();
        model.put("errorMessage", message);
        return new ModelAndView(url, model);
    }

    /**
     * 返回错误数据
     *
     * @param message 错误信息
     * @return 模型视图对象
     */
    private ModelAndView jsonResult(int code, String message) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        view.setFastJsonConfig(fastJsonConfig);
        view.setAttributesMap((JSONObject) JSON.toJSON(new ReturnBaseJson(code, message)));
        mv.setView(view);
        return mv;
    }

    /**
     * 保存异常信息
     * @param handler
     */
    private void saveException(Object handler, Exception e) {
        try {
            // Exception的具体说明
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            String exception = baos.toString();

            // 封装 产生异常的类的信息
            String modelName = "Unknow";
            String functionName = "Unknow";
            if(handler instanceof HandlerMethod){
                HandlerMethod method = (HandlerMethod) handler;
                modelName = method.getMethod().getDeclaringClass().getSimpleName();
                functionName = method.getMethod().getName();
            }
            String mailSubject = StringUtils.getRandomCode()+"Simple:[(" + host + ")]"
                    + AuthorCons.zhangzhennan + ":" + modelName + "==>" + functionName;

            // 打印错误信息
            e.printStackTrace();

            if(isSendMail){
                // 发送错误的邮件
                MailSendUtils.sendSyncErrorMail("18519149312@163.com", mailSubject, exception);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order){
        this.order = order;
    }


}
