package cn.zznlin.simple.base.dao.impl;

import cn.zznlin.simple.base.dao.PermissionDao;
import cn.zznlin.simple.base.entity.Permission;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/25 23:03
 * @Description
 */
@Repository
public class PermissionDaoImpl extends HibernateDaoSupport<Permission> implements PermissionDao {

}
