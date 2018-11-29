package cn.zznlin.simple.base.service;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.orm.service.BaseService;

/**
 * @Author zhennan
 * @Date 2018/7/15 22:57
 * @Description
 */
public interface UserService extends BaseService<User>{
    User findByNameAndPassword(String username, String password);

    User findByName(String username);
}
