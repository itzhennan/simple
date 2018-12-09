package cn.zznlin.simple.common.utils.genarate;

import cn.zznlin.simple.common.orm.dao.BaseDao;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import cn.zznlin.simple.common.utils.DateUtils;
import cn.zznlin.simple.demo.entity.DemoInfo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhennan zhang
 * @date 2018/12/7 11:19
 * @description
 *    自动生成Dao
 */
public class GenarateDaoCodeUtil {
    private static final String ipt = "import";
    private static final String pkg = "package";
    private static final String base = "BaseDao";
    private static final String blank = " ";
    private static final String changeRow = ";\n";
    private static final String change = "\n";
    private static final String pub = "public";
    private static final String intf = "interface";
    private static final String extd = "extends";
    private static final String impl = "implements";
    private static final String cls = "class";
    private static final String repository = "org.springframework.stereotype.Repository";
    private static final String currentTime = DateUtils.todayToYYYYMMDDStr();
    private static final String note = "/**\n * @author zhennan zhang\n * @date "+currentTime+"\n * @description\n */";


    @SuppressWarnings("rawtypes")
    private Class classzy;  //bean的类

    // home
    private String home = "";

    @SuppressWarnings("rawtypes")
    public GenarateDaoCodeUtil(Class classzy) {
        super();
        this.classzy = classzy;
        this.home = new File(String.valueOf(classzy.getResource("/"))).getParentFile().getParentFile().getPath().replace("file:\\", "") + "\\src\\main\\java";
    }

    //得到存放BaseDao的全路劲
    public String getBaseDaoPkg() {
        return BaseDao.class.getName();
    }

    //得到BaseDaoHibernate的全路勁
    public String getBaseDaoHibernatePkg() {
        return HibernateDaoSupport.class.getName();
    }

    //得到bean的全路径
    public String getBeanPkg() {
        return classzy.getName();
    }

    //得到Dao的名称
    public String getDaoName() {
        String daoName = classzy.getSimpleName();
        return daoName.replace("Info", "") + "Dao";
    }

    //得到存放dao文件的包名
    public String getPkgName() {
        String beanPrefix = classzy.getName();
        String beanP = beanPrefix.substring(0, beanPrefix.lastIndexOf("."));
        String daoPkg = beanP.substring(0, beanP.lastIndexOf("."));
        return daoPkg + ".dao";
    }

    //得到Dao文件路劲
    public String getDaoFilePath() throws Exception {
        String files[] = getPkgName().split("[.]");
        StringBuffer stringBuffer = new StringBuffer(home);
        for (String s : files) {
            stringBuffer.append("\\" + s);
        }
        String daoPath = stringBuffer.append("\\" + getDaoName()).toString() + ".java";
        return daoPath;
    }

    //得到dao文件的内容
    public String getDaoContent() {
        StringBuffer content = new StringBuffer();
        content.append(pkg + blank + getPkgName() + changeRow)
        .append(change)
        .append(ipt + blank + getBaseDaoPkg() + changeRow)
        .append(ipt + blank + getBeanPkg() + changeRow)
        .append(change)
        .append(note)
        .append(change)
        .append(pub + blank + intf + blank + getDaoName() + blank + extd + blank + base + "<" + classzy.getSimpleName() + ">{" + change)
        .append(change + "}" + change);
        return content.toString();
    }

    //生成dao文件
    public void generateDaoFile() throws Exception {
        String daoPath = getDaoFilePath();
        String content = getDaoContent();
        File daoFile = new File(daoPath);
        makeParent(daoFile);
        PrintWriter out = new PrintWriter(daoFile);
        out.print(content);
        out.flush();
        out.close();
    }

    // 得到daoImpl的名称
    public String getDaoImplName() {
        String daoName = classzy.getSimpleName();
        return daoName.replace("Info", "") + "DaoImpl";
    }

    //得到存放daoImpl文件的包名
    public String getDaoImplPkgName() {
        String beanPrefix = classzy.getName();
        String beanP = beanPrefix.substring(0, beanPrefix.lastIndexOf("."));
        String daoPkg = beanP.substring(0, beanP.lastIndexOf("."));
        return daoPkg + ".dao.impl";
    }

    //得到DaoImpl文件路劲
    public String getDaoImplFilePath() throws Exception {
        String files[] = getDaoImplPkgName().split("[.]");
        StringBuffer stringBuffer = new StringBuffer(home);
        for (String s : files) {
            stringBuffer.append("\\" + s);
        }
        String daoPath = stringBuffer.append("\\" + getDaoImplName()).toString() + ".java";
        return daoPath;
    }

    //得到daoImpl文件的内容
    public String getDaoImplContent() {
        StringBuffer content = new StringBuffer();
        content.append(pkg + blank + getDaoImplPkgName() + changeRow)
        .append(change)
        .append(ipt + blank + repository + changeRow)
        .append(ipt + blank + getBaseDaoHibernatePkg() + changeRow)
        .append(ipt + blank + getBeanPkg() + changeRow)
        .append(ipt + blank + getPkgName() + "." + getDaoName() + changeRow)
        .append(change)
        .append(note)
        .append(change)
        //使用spring 註解  首字母小寫
        .append("@Repository(" + "\"" + getDaoName().substring(0, 1).toLowerCase() + getDaoName().substring(1) + "\")" + change)
        .append(pub + blank + cls + blank + getDaoImplName() + blank + extd + blank + "HibernateDaoSupport<" + classzy.getSimpleName() + ">" + blank + impl + blank + getDaoName() + "{" + change)
        .append(change + "}" + change);
        return content.toString();
    }

    //生成daoImpl文件
    public void generateDaoImplFile() throws Exception {
        String daoPath = getDaoImplFilePath();
        String content = getDaoImplContent();
        File daoFile = new File(daoPath);
        makeParent(daoFile);
        PrintWriter out = new PrintWriter(daoFile);
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
        GenarateDaoCodeUtil genarateDaoCodeUtil = new GenarateDaoCodeUtil(DemoInfo.class);
        genarateDaoCodeUtil.generateDaoFile();
        genarateDaoCodeUtil.generateDaoImplFile();
    }

}

