package cn.zznlin.simple.base.dao.impl;

import cn.zznlin.simple.base.dao.RoleDao;
import cn.zznlin.simple.base.entity.Role;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/25 23:04
 * @Description
 */
@Repository
public class RoleDaoImpl extends HibernateDaoSupport<Role> implements RoleDao {
}
