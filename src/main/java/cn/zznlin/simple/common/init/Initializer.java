package cn.zznlin.simple.common.init;

import cn.zznlin.simple.common.utils.LoggerUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author zhennan zhang
 * @date 2018/10/25 12:08
 * @description
 */
public class Initializer extends HttpServlet {
    private static final String CLASS_NAME = Initializer.class.getName();
    private static final long serialVersionUID = 1L;
    public static boolean runAlready = false;

    /**
     * Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if (runAlready) {
            LoggerUtils.error(CLASS_NAME, "Initializer already run, exiting init()");
            return;
        }
        runAlready = true;
        // Now, set up the application's config file.
        SystemPropertyInit.getInstance();
    }

    /**
     * Destroys the servlet.
     */
    public void destroy() {
        LoggerUtils.debug(CLASS_NAME, "Initializer servlet destroyed.");
    }
}

