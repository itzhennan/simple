package cn.zznlin.simple;

import java.util.UUID;

/**
 * @Author zhennan
 * @Date 2018/7/17 23:58
 * @Description
 */
public class MyTest {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid.toString());
        }
    }

}
