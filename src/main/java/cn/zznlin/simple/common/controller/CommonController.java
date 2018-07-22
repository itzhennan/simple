package cn.zznlin.simple.common.controller;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.pojo.SessionBean;
import cn.zznlin.simple.base.pojo.ValidateResult;
import cn.zznlin.simple.base.service.UserService;
import cn.zznlin.simple.common.config.ResultPath;
import cn.zznlin.simple.common.config.ViewName;
import cn.zznlin.simple.common.cons.Cons;
import cn.zznlin.simple.common.init.SystemPropertyInit;
import cn.zznlin.simple.common.utils.LoggerUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/7/15 10:37
 * @Description
 */
public abstract class CommonController
{
    protected final String CLASS_NAME = getClass().getName();
    protected final String SIMPLE_CLASS_NAME = getClass().getSimpleName();

    protected abstract String getModule();
    protected String show = "show";
    protected String edit = "edit";
    protected String update = "update";
    // 是否发送异常邮件  true 发送
    private Boolean isSendMail = SystemPropertyInit.getInstance().getBooleanProperty("isSendCodeMail",true);

    protected String forward(ViewName viewName) {
        String path = this.getClass().getAnnotation(RequestMapping.class)
                .value()[0];
        LoggerUtils
                .debug(CLASS_NAME, "[CommonController] whole path ==> "
                        + getModule() + path.replaceAll("-", "") + path
                        + viewName.getValue());
        return getModule() + path.replaceAll("-", "") + path
                + viewName.getValue();
    }

    protected String redirect(String action) {
        return ViewName.redirect.getValue() + action;
    }

    protected String forward(String action) {
        return ViewName.forward.getValue() + action;
    }


    /**
     * 保存异常信息
     *
     * @param authorName
     * @param modelName
     * @param functionName
     * @param e
     */
    protected void saveException(String authorName, String modelName,
                                 String functionName, Exception e) {
        try {
            // Exception的具体说明
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            String exception = baos.toString();

            String mailContent = StringUtils.getRandomCode()+"Language:[("
                    + SystemPropertyInit.getInstance().getProperty("server.host") + ")]"
                    + authorName + ":" + modelName + "==>" + functionName;
            if(isSendMail){
                // 发送错误的邮件
//                mailSendUtil.sendSyncErrorMail("18519149312@163.com", mailContent, exception);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

//    /**
//     * 下载资源
//     * @param response
//     * @param url
//     */
//	@RequestMapping("/downloadFile/{url}")
//	public void download(HttpServletResponse response, @PathVariable String url){
//		try {
//			int lastIndexOf = url.lastIndexOf("/");
//			String resourceFile = url.substring(lastIndexOf+1);
//			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(resourceFile, "UTF-8"));
//			URL uri = new URL(url);
//			InputStream inputStream = uri.openStream();
//			OutputStream outputStream = response.getOutputStream();
//
//			int read = -1;
//			int len = inputStream.available();
//			byte[] buffer = new byte[len];
//			while ( (read = inputStream.read(buffer)) != -1) {
//				outputStream.write(buffer, 0, read);
//			}
//			outputStream.flush();
//			inputStream.close();
//			outputStream.close();
//		} catch (Exception e) {
//			saveException(AuthodType.wangqijun.getName(), SIMPLE_CLASS_NAME, "download()", e);
//			e.printStackTrace();
//		}
//	}

    /**
     * 返回JSON结果集：{"status":"SUCCESS/FAILURE"}
     *
     * @throws Exception
     */
//    protected void rtnResponseData(boolean isTrue, HttpServletResponse response)
//            throws Exception {
//        boolean rtnType = ReturnModule.success.getStatus();
//        if (!isTrue) {
//            rtnType = ReturnModule.failure.getStatus();
//        }
//        LoggerUtils.debug(CLASS_NAME, "[rtnResponseData] rtnType ==> "
//                + rtnType);
//        HttpUtils.respWrite(response, new ReturnJsonBean(rtnType).toJson());
//    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loop(List<T> list, List<T> nodes, int rows) {
        for (int i = 0; i < nodes.size(); i++) {
            T node = nodes.get(i);
            try {
                list.add(node);
                Method getChild = node.getClass().getMethod("getChild");
                List<T> childs = (List<T>) getChild.invoke(node);
                if (CollectionUtils.isNotEmpty(childs)) {
                    loop(list, childs, rows + 1);
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return list;
    }



    public ValidateResult controllerValidate(HttpSession session){
        ValidateResult validateResult = new ValidateResult();
        SessionBean sessionBean = (SessionBean) session.getAttribute(Cons.ZZNLIN_SESSION_BEAN);
        if (sessionBean == null) {
            validateResult.setStatus(1);
            validateResult.setUrl(ResultPath.TO_LOGIN);
            return validateResult;
        }
        User currentUser = sessionBean.getCurrentUser();
        if (currentUser == null) {
            validateResult.setStatus(1);
            validateResult.setUrl(ResultPath.TO_LOGIN);
            return validateResult;
        }
        validateResult.setCurrentUser(currentUser);
        return validateResult;
    }

    @Autowired
    protected UserService userService;

}

