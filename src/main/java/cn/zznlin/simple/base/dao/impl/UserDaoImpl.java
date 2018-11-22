package cn.zznlin.simple.base.dao.impl;

import cn.zznlin.simple.base.dao.UserDao;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/23 0:30
 * @Description
 */
@Repository()
public class UserDaoImpl extends HibernateDaoSupport<User> implements UserDao {
}
