package cn.zznlin.simple.base.service.impl;

import org.springframework.stereotype.Service;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.base.entity.Menu;
import cn.zznlin.simple.base.service.MenuService;
import cn.zznlin.simple.base.dao.MenuDao;

/**
 * @author zhennan zhang
 * @date 2018-12-08
 * @description
 */
@Service()
public class MenuServiceImpl extends HibernateServiceSupport<Menu> implements MenuService{

	// 获得当前service所属的DAO
	private MenuDao getCurrentDao(){
		return (MenuDao) baseDao;
	}


}
