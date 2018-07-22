package cn.zznlin.simple;

import cn.zznlin.simple.base.BaseJunitTest;
import cn.zznlin.simple.common.init.SystemPropertyInit;
import cn.zznlin.simple.common.utils.LoggerUtils;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @Author zhennan
 * @Date 2018/7/17 23:58
 * @Description
 */
public class MyTest extends BaseJunitTest{

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Test
    public void test() throws IOException, TemplateException {
        LoggerUtils.debug(this.getClass().getName(),"index.html模板生成中....");
        final String url = SystemPropertyInit.class.getResource("/").getPath();
        String srcPath = url+"/freemarker/index.ftl";
        String tarPath = url+"/main/index.html";
        System.out.println("index模板路径-->"+srcPath);
        System.out.println("index模板路径-->"+tarPath);
        LoggerUtils.debug(this.getClass().getName(),"index模板路径-->"+srcPath);
//        Configuration configuration = freeMarkerConfigurer.getConfiguration();
//        Template template = configuration.getTemplate(srcPath);
//        Map root = new HashMap();
//        //取商品信息....
//        FileWriter out = new FileWriter(new File(tarPath));
//        template.process(root, out);
//        out.flush();
//        out.close();
        LoggerUtils.debug(this.getClass().getName(),"index.html生成成功，文件路径-->"+tarPath);
    }

}
