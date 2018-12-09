package cn.zznlin.simple.common.security;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.pojo.ShiroBean;
import cn.zznlin.simple.base.service.MenuService;
import cn.zznlin.simple.base.service.PermissionService;
import cn.zznlin.simple.base.service.RoleService;
import cn.zznlin.simple.base.service.UserService;
import cn.zznlin.simple.common.bean.LoginInfo;
import com.google.common.collect.Sets;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @Author zhennan
 * @Date 2018/11/25 23:26
 * @Description
 */
public class Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        LoginInfo loginUser = (LoginInfo) principalCollection.getPrimaryPrincipal();

        // 根据身份信息获取权限信息
        // 从数据库获取到权限数据
        List<ShiroBean> shiroBeanList = null;
        try {
            shiroBeanList = permissionService.findPermissionListByUserId(loginUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 单独定一个集合对象
        Set<String> permissions = Sets.newHashSet();
        Set<String> roles = Sets.newHashSet();
        if (shiroBeanList != null) {
            for (ShiroBean shiroBean : shiroBeanList) {
                // 将数据库中的权限标签 符放入集合
                permissions.add(shiroBean.getPermissionKeyword());
                roles.add(shiroBean.getRoleKeyword());
            }
        }



        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        simpleAuthorizationInfo.addRoles(roles);

        return simpleAuthorizationInfo;
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
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setId(user.getUserId());
            loginInfo.setName(user.getUserName());
            // principal 主角 当前登录人, credentials 密码, realmName
            // 将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数传入用户输入的用户名。
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginInfo, user.getPassword(), getName());
            // 设置盐，用来核对密码
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getUserName()));

            return authenticationInfo;
        }
    }
}
