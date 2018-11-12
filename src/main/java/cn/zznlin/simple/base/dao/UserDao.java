package cn.zznlin.simple.base.dao;

import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/13 0:16
 * @Description
 */
@Repository(value = "UserDao")
public class UserDao extends HibernateDaoSupport<User> implements BaseDao<User> {
}
