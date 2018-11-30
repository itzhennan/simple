package cn.zznlin.simple.common.security;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author zhennan
 * @Date 2018/11/25 23:26
 * @Description
 */
public class Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权方法");
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证方法");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findByName(username);
        if(user==null){
            return null;
        }else{
            // principal 主角 当前登录人, credentials 密码, realmName
            // 将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数传入用户输入的用户名。
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
            // 设置盐，用来核对密码
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getUserName()));

            return authenticationInfo;
        }
    }
}
