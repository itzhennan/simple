package cn.zznlin.simple.freemarker.controller;

import cn.zznlin.simple.base.pojo.FtlModelBean;
import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.init.SystemPropertyInit;
import cn.zznlin.simple.common.utils.HttpUtils;
import cn.zznlin.simple.common.utils.LoggerUtils;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhennan
 * @Date 2018/7/20 8:00
 * @Description
 */
@Controller
@RequestMapping("/generateFtl")
public class FreemarkerController extends CommonController {

    private static final String CLASS_NAME = FreemarkerController.class.getName();

    @Autowired
    private FreeMarkerConfig freeMarkerConfigurer;

    @RequestMapping("/generateOne")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            LoggerUtils.debug(CLASS_NAME, "index.html模板生成中....");
            String srcPath = "index.ftl";
            LoggerUtils.debug(CLASS_NAME, "index模板路径-->" + srcPath);
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate(srcPath);
            Map root = new HashMap();
            // index信息
            // server
            FtlModelBean server = new FtlModelBean();
            server.setUrl(SystemPropertyInit.getInstance().getProperty("host"));
            server.setText("Zznlin Blog");
            root.put("server",server);
            // 导航栏信息
            ArrayList<FtlModelBean> navigations = Lists.newArrayList();
            FtlModelBean n = new FtlModelBean();
            n.setText("导航1");
            n.setUrl("#");
            navigations.add(n);
            root.put("navigations",navigations);
            // 搜索框
            FtlModelBean search = new FtlModelBean();
            search.setText("搜索");
            search.setUrl("#");
            root.put("search",search);
            // 菜单项
            ArrayList<FtlModelBean> menus = Lists.newArrayList();
            FtlModelBean m = new FtlModelBean();
            m.setUrl("#");
            m.setTitle("菜单1");
            m.setText("菜单1的详细信息");
            menus.add(m);
            root.put("menus",menus);
            // 登录
            FtlModelBean login = new FtlModelBean();
            login.setText("点我登录");
            login.setUrl("#");
            root.put("login",login);


            // 主页显示内容
            ArrayList<FtlModelBean> mainInfo = Lists.newArrayList();
            // 第一篇文章
            FtlModelBean main = new FtlModelBean();
            // 文章标题信息
            FtlModelBean header = new FtlModelBean();
            header.setUrl("#");
            header.setText("第一篇文章");
            header.setTitle("第一篇文章的详细描述");
            main.setHeader(header);
            // 作者信息
            FtlModelBean author = new FtlModelBean();
            author.setTime("2018-07-22");
            author.setUrl("#");
            author.setText("Zznlin");
            author.setImageUrl("images/avatar.jpg");
            main.setAuthor(author);
            // 文章简介
            FtlModelBean body = new FtlModelBean();
            body.setUrl("#");
            body.setText("第一篇文章详细介绍");
            body.setImageUrl("images/pic01.jpg");
            main.setBody(body);
            // 文章点赞信息
            FtlModelBean footer = new FtlModelBean();
            // 继续阅读
            FtlModelBean readText = new FtlModelBean();
            readText.setUrl("#");
            readText.setText("继续阅读");
            footer.setReadText(readText);
            // 通用信息
            FtlModelBean general = new FtlModelBean();
            general.setUrl("#");
            general.setText("通用信息");
            footer.setGeneral(general);
            // 点赞数量
            FtlModelBean love = new FtlModelBean();
            love.setUrl("#");
            love.setText("999999");
            footer.setLove(love);
            // 阅读数量
            FtlModelBean comment = new FtlModelBean();
            comment.setUrl("#");
            comment.setText("999999");
            footer.setComment(comment);

            main.setFooter(footer);
            mainInfo.add(main);
            root.put("mainInfo",mainInfo);

            // 左侧信息
            // 用户信息
            FtlModelBean user = new FtlModelBean();
            user.setUrl("#");
            user.setImageUrl("images/logo.jpg");
            user.setText("ZZNLIN BLOG");
            user.setContent("随时随地随想随写<br/>记录生活的<a href='#'>点点滴滴</a>");

            root.put("user",user);

            // 分类
            ArrayList<FtlModelBean> categorys = Lists.newArrayList();
            // 第一个分类
            FtlModelBean c = new FtlModelBean();
            // 头信息
            FtlModelBean cheader = new FtlModelBean();
            cheader.setUrl("#");
            cheader.setTitle("学习");
            cheader.setTime("2018-07-22");
            cheader.setImageUrl("images/pic04.jpg");
            c.setHeader(cheader);
            // 作者信息
            FtlModelBean cauthor = new FtlModelBean();
            cauthor.setUrl("#");
            cauthor.setImageUrl("images/avatar.jpg");
            c.setAuthor(cauthor);

            categorys.add(c);

            root.put("categorys",categorys);

            // 热门阅读
            ArrayList<FtlModelBean> hotReader = Lists.newArrayList();
            // 第一个分类
            FtlModelBean h = new FtlModelBean();
            h.setUrl("#");
            h.setTitle("热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1");
            h.setTime("2018-07-22");
            h.setImageUrl("images/pic08.jpg");

            FtlModelBean hlove = new FtlModelBean();
            hlove.setUrl("#");
            hlove.setText("99999");
            h.setLove(hlove);

            FtlModelBean hcomment = new FtlModelBean();
            hcomment.setUrl("#");
            hcomment.setText("99999");
            h.setComment(hcomment);

            hotReader.add(h);

            root.put("hotReader",hotReader);
            // 关于我们
            FtlModelBean about = new FtlModelBean();
            about.setTitle("ABOUT");
            about.setText("关于我们的详细介绍。。。");
            about.setUrl("#");
            about.setContent("了解更多");
            root.put("about",about);

            String realPath = request.getSession().getServletContext().getRealPath("/");
            String tarPath = srcPath.replace("ftl","html");
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(realPath+tarPath), "UTF-8"));
            template.process(root, out);
            out.flush();
            out.close();
            LoggerUtils.debug(CLASS_NAME, "index.html生成成功，文件路径-->" + realPath+tarPath);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
             HttpUtils.respWrite(response,"Ok");
        }
    }

    @Override
    protected String getModule() {
        return null;
    }
}
