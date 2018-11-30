package cn.zznlin.simple.common.utils;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.pojo.UserBean;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author zhennan zhang
 * @date 2018/11/29 17:47
 * @description
 *      Shiro 密码加密Bean
 */
public class ShiroMD5Utils {

    /**
     * 添加user的密码加密方法
     * @param user
     * @return
     */
    public static String  SysMd5(UserBean user) {
        String hashAlgorithmName = "MD5";//加密方式

        Object crdentials = user.getPassword();//密码原值

        ByteSource salt = ByteSource.Util.bytes(user.getName());//以账号作为盐值

        int hashIterations = 1024;//加密1024次

        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);

        return hash.toString();
    }
}
