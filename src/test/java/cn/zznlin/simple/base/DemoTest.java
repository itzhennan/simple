package cn.zznlin.simple.base;

/**
 * @Author zhennan
 * @Date 2018/9/9 10:56
 * @Description
 */
public class DemoTest {

    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4,allocation5;
        allocation1 = new byte[1 * _1MB];
        allocation2 = new byte[1 * _1MB];
        allocation3 = new byte[1 * _1MB];
        allocation4 = new byte[7 * _1MB];
        allocation5 = new byte[1 * _1MB];
//        allocation5 = new byte[1 * _1MB];
    }

    public static void main(String[] agrs) {
        testAllocation();
    }
}
