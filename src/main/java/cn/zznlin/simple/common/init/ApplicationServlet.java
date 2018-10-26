package cn.zznlin.simple.common.init;

import cn.zznlin.simple.common.utils.LoggerUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author zhennan zhang
 * @date 2018/10/25 12:06
 * @description
 */

public class ApplicationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String  CLASS_NAME = ApplicationServlet.class.getName();

    public ApplicationServlet() {
        super();
    }

    public void init() throws ServletException {
        try {
            ServletContext sc = getServletContext();

//			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
//			CountryService countryService = (CountryService)applicationContext.getBean("countryService");
//			List<Country> countryList = countryService.findAll();
//			sc.setAttribute(ConstVar.APP_MPA_COUNTRY, countryList);
            LoggerUtils.IS_LOG = Boolean.valueOf(SystemPropertyInit.getInstance().getProperty("isLog"));

            // 生成静态版本数
            sc.setAttribute("staticVersion", System.currentTimeMillis());
            LoggerUtils.debug(CLASS_NAME, "staticVersion ==> " + sc.getAttribute("staticVersion"));

            // 配置文件中--获取视频地址
            sc.setAttribute("videoHost", SystemPropertyInit.getInstance().getProperty("upyun.videourl"));
            LoggerUtils.debug(CLASS_NAME, "videoHost ==> " + sc.getAttribute("videoHost"));

            // 配置文件中--获取upyun图片地址
            sc.setAttribute("imageHost", SystemPropertyInit.getInstance().getProperty("upyun.image"));
            LoggerUtils.debug(CLASS_NAME, "upyunImageHost ==> " + sc.getAttribute("imageHost"));

            // 配置文件中--获取动态图片地址
            sc.setAttribute("imgServer", SystemPropertyInit.getInstance().getProperty("image.host"));
            LoggerUtils.debug(CLASS_NAME, "imgServer ==> " + sc.getAttribute("imgServer"));

            // 配置文件中--获取动态图片地址
            sc.setAttribute("supimgServer", SystemPropertyInit.getInstance().getProperty("supimg.host"));
            LoggerUtils.debug(CLASS_NAME, "supimgServer ==> " + sc.getAttribute("supimgServer"));

            // 配置文件中--获取静态图片地址
            sc.setAttribute("statichost", SystemPropertyInit.getInstance().getProperty("static.host"));
            LoggerUtils.debug(CLASS_NAME, "statichost ==> " + sc.getAttribute("statichost"));

            // 配置文件中--获取静态CSS地址
            sc.setAttribute("csshost", SystemPropertyInit.getInstance().getProperty("css.host"));
            LoggerUtils.debug(CLASS_NAME, "csshost ==> " + sc.getAttribute("csshost"));

            // 配置文件中--获取静态JS地址
            sc.setAttribute("jshost", SystemPropertyInit.getInstance().getProperty("js.host"));
            LoggerUtils.debug(CLASS_NAME, "jshost ==> " + sc.getAttribute("jshost"));

            // 电话显示-获取状态
            sc.setAttribute("isopen", LoggerUtils.IS_OPEN);
            LoggerUtils.debug(CLASS_NAME, "isopen ==> " + sc.getAttribute("isopen"));

            // 电话显示-获取状态
            sc.setAttribute("serverHost", SystemPropertyInit.getInstance().getProperty("server.host"));
            LoggerUtils.debug(CLASS_NAME, "serverHost ==> " + sc.getAttribute("serverHost"));

            // 配置文件中--goeasyAPPKEY
            sc.setAttribute("superkey", SystemPropertyInit.getInstance().getProperty("goeasy.key"));
            LoggerUtils.debug(CLASS_NAME, "superkey ==> " + sc.getAttribute("superkey"));

            LoggerUtils.debug(CLASS_NAME, "Application context load success!");
        } catch (Exception e) {
            LoggerUtils.error(CLASS_NAME, "Application context load error!" + e.toString());
            e.printStackTrace();
        }
    }


    public void destroy() {
        super.destroy();
    }
}

