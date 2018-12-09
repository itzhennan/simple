package cn.zznlin.simple.common.utils.genarate;

import cn.zznlin.simple.common.orm.service.BaseService;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.common.utils.DateUtils;
import cn.zznlin.simple.demo.entity.DemoInfo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhennan zhang
 * @date 2018/12/7 13:53
 * @description
 */
public class GenarateServiceCodeUtil {
    private static final String ipt = "import";
    private static final String pkg = "package";
    private static final String base = "BaseService";
    private static final String blank = " ";
    private static final String changeRow = ";\n";
    private static final String change = "\n";
    private static final String tab = "\t";
    private static final String pub = "public";
    private static final String pri = "private";
    private static final String intf = "interface";
    private static final String extd = "extends";
    private static final String impl = "implements";
    private static final String cls = "class";
    private static final String repository = "org.springframework.stereotype.Service";
    private static final String currentTime = DateUtils.todayToYYYYMMDDStr();
    private static final String note = "/**\n * @author zhennan zhang\n * @date "+currentTime+"\n * @description\n */";

    @SuppressWarnings("rawtypes")
    private Class classzy;  //bean的类

    // home
    private String home = "";

    @SuppressWarnings("rawtypes")
    public GenarateServiceCodeUtil(Class classzy) {
        super();
        this.classzy = classzy;
        this.home = new File(String.valueOf(classzy.getResource("/"))).getParentFile().getParentFile().getPath().replace("file:\\", "") + "\\src\\main\\java";
    }

    //得到存放BaseService的全路劲
    public String getBaseServicePkg() {
        return BaseService.class.getName();
    }

    //得到BaseServiceHibernate的全路勁
    public String getBaseServiceHibernatePkg() {
        return HibernateServiceSupport.class.getName();
    }

    //得到bean的全路径
    public String getBeanPkg() {
        return classzy.getName();
    }

    //得到Service的名称
    public String getServiceName() {
        String daoName = classzy.getSimpleName();
        return daoName.replace("Info", "") + "Service";
    }

    //得到存放service文件的包名
    public String getPkgName() {
        String beanPrefix = classzy.getName();
        String beanP = beanPrefix.substring(0, beanPrefix.lastIndexOf("."));
        String servicePkg = beanP.substring(0, beanP.lastIndexOf("."));
        return servicePkg + ".service";
    }

    //得到Service文件路劲
    public String getServiceFilePath() throws Exception {
        String files[] = getPkgName().split("[.]");
        StringBuffer stringBuffer = new StringBuffer(home);
        for (String s : files) {
            stringBuffer.append("/" + s);
        }
        String servicePath = stringBuffer.append("/" + getServiceName()).toString() + ".java";
        return servicePath;
    }

    //得到service文件的内容
    public String getServiceContent() {
        StringBuffer content = new StringBuffer();
        content.append(pkg + blank + getPkgName() + changeRow)
                .append(change)
                .append(ipt + blank + getBaseServicePkg() + changeRow)
                .append(ipt + blank + getBeanPkg() + changeRow)
                .append(change)
                .append(note)
                .append(change)
                .append(pub + blank + intf + blank + getServiceName() + blank + extd + blank + base + "<" + classzy.getSimpleName() + ">{" + change)
                .append(change + "}" + change);
        return content.toString();
    }

    //生成service文件
    public void generateServiceFile() throws Exception {
        String servicePath = getServiceFilePath();
        String content = getServiceContent();
        File serviceFile = new File(servicePath);
        makeParent(serviceFile);
        PrintWriter out = new PrintWriter(serviceFile);
        out.print(content);
        out.flush();
        out.close();
    }

    // 得到serviceImpl的名称
    public String getServiceImplName() {
        String serviceName = classzy.getSimpleName();
        return serviceName.replace("Info", "") + "ServiceImpl";
    }

    //得到存放serviceImpl文件的包名
    public String getServiceImplPkgName() {
        String beanPrefix = classzy.getName();
        String beanP = beanPrefix.substring(0, beanPrefix.lastIndexOf("."));
        String servicePkg = beanP.substring(0, beanP.lastIndexOf("."));
        return servicePkg + ".service.impl";
    }

    //得到存放dao文件的文件路径
    public String getDaoPkgName() {
        String beanPrefix = classzy.getName();
        String beanP = beanPrefix.substring(0, beanPrefix.lastIndexOf("."));
        String servicePkg = beanP.substring(0, beanP.lastIndexOf("."));
        return servicePkg + ".dao";
    }

    //得到Dao的名称
    public String getDaoName() {
        String daoName = classzy.getSimpleName();
        return daoName.replace("Info", "") + "Dao";
    }

    //得到ServiceImpl文件路劲
    public String getServiceImplFilePath() throws Exception {
        String files[] = getServiceImplPkgName().split("[.]");
        StringBuffer stringBuffer = new StringBuffer(home);
        for (String s : files) {
            stringBuffer.append("/" + s);
        }
        String servicePath = stringBuffer.append("/" + getServiceImplName()).toString() + ".java";
        return servicePath;
    }

    //得到serviceImpl文件的内容
    public String getServiceImplContent() {
        StringBuffer content = new StringBuffer();
        content.append(pkg + blank + getServiceImplPkgName() + changeRow)
                .append(change)
                // 导包
                .append(ipt + blank + repository + changeRow)
                .append(ipt + blank + getBaseServiceHibernatePkg() + changeRow)
                .append(ipt + blank + getBeanPkg() + changeRow)
                .append(ipt + blank + getPkgName() + "." + getServiceName() + changeRow)
                .append(ipt + blank + getDaoPkgName() + "." + getDaoName() + changeRow)
                .append(change)

                // 类注解
                .append(note)
                .append(change)

                //使用spring注解
//                .append("@Service(" + "\"" + getServiceName().substring(0, 1).toLowerCase() + getServiceName().substring(1) + "\")" + change)
                .append("@Service()" + change)
                .append(pub + blank + cls + blank + getServiceImplName() + blank + extd + blank + "HibernateServiceSupport<" + classzy.getSimpleName() + ">" + blank + impl + blank + getServiceName() + "{" + change)
                .append(change)

                // 获得当前service所属的DAO
                .append(tab+"// 获得当前service所属的DAO").append(change)
                .append(tab + pri + blank + getDaoName() + blank + "getCurrentDao(){").append(change)
                .append(tab + tab + "return ("+getDaoName()+") baseDao;").append(change)
                .append(tab + "}")
                .append(change)

                .append(change)
                .append(change + "}" + change);
        return content.toString();
    }

    //生成serviceImpl文件
    public void generateServiceImplFile() throws Exception {
        String servicePath = getServiceImplFilePath();
        String content = getServiceImplContent();
        File serviceFile = new File(servicePath);
        makeParent(serviceFile);
        PrintWriter out = new PrintWriter(serviceFile);
        out.print(content);
        out.flush();
        out.close();
    }

    // 生成父文件夹
    public void makeParent(File outFile) throws IOException {
        if (!outFile.exists()) {
            makeDir(outFile.getParentFile());
            outFile.createNewFile();
        } else {
        }
    }

    /**
     * Create package
     *
     * @param dir
     */
    public static void makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }

    public static void main(String[] args) throws Exception {
        GenarateServiceCodeUtil util = new GenarateServiceCodeUtil(DemoInfo.class);
        util.generateServiceFile();
        util.generateServiceImplFile();
    }

}
