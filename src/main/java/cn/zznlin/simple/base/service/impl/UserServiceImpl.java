package cn.zznlin.simple.base.service.impl;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.base.service.UserService;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Service;

/**
 * @Author zhennan
 * @Date 2018/7/15 23:08
 * @Description
 */
@Service
public class UserServiceImpl extends HibernateDaoSupport<User> implements UserService {
}
