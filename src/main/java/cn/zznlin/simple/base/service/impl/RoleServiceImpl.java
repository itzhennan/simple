package cn.zznlin.simple.base.service.impl;

import org.springframework.stereotype.Service;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.base.entity.Role;
import cn.zznlin.simple.base.service.RoleService;
import cn.zznlin.simple.base.dao.RoleDao;

/**
 * @author zhennan zhang
 * @date 2018-12-08
 * @description
 */
@Service()
public class RoleServiceImpl extends HibernateServiceSupport<Role> implements RoleService{

	// 获得当前service所属的DAO
	private RoleDao getCurrentDao(){
		return (RoleDao) baseDao;
	}


}
