package cn.zznlin.simple.common.utils.genarate;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Author zhennan
 * @Date 2018/8/16 23:24
 * @Description 生成world工具类
 */
public class GenerateWordUtils {

    public static void main(String[] args) throws ClassNotFoundException {
        GenerateWordUtils generateWordUtils = new GenerateWordUtils();
        String fileName = generateWordUtils.getFileName();

        // 解析这个文件的tableName,columnName,
        generateWordUtils.getAnnotation("com.java.annotation.Student");
    }

    public String getFileName() {
        String fileName = "F:\\workspace\\IntellijWorkspace\\fornowLanguage\\src\\main\\java\\com\\fornow\\language\\activity\\entity\\SuperActivity.java";
        return fileName;
    }


    public void getAnnotation(String packageName) throws ClassNotFoundException {
        Class<?> stu = Class.forName(packageName);//静态加载类
        boolean isEmpty = stu.isAnnotationPresent(Table.class);
        if(isEmpty){
            Annotation[] annotation = null;
            annotation = stu.getAnnotations();//获取注解接口中的
            for (Annotation a : annotation) {
                Table my = (Table) a;//强制转换成Annotation_my类型
                System.out.println(packageName + ":\n" + my.name());
            }
        }

        Field[] field = stu.getDeclaredFields();
        for (Field f : field){
            f.setAccessible(true);
            if(f.isAnnotationPresent(Column.class)){
                Column annotation1 = f.getAnnotation(Column.class);
                System.out.println(f.getName()+"--字段-->"+annotation1.name());
                System.out.println(f.getName()+"--字段-->"+annotation1.columnDefinition());
            }
        }
    }

}
