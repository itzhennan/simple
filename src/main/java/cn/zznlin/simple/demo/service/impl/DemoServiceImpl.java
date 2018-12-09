package cn.zznlin.simple.demo.service.impl;

import org.springframework.stereotype.Service;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.demo.entity.DemoInfo;
import cn.zznlin.simple.demo.service.DemoService;
import cn.zznlin.simple.demo.dao.DemoDao;

/**
 * @author zhennan zhang
 * @date 2018-12-07
 * @description
 */
@Service("demoService")
public class DemoServiceImpl extends HibernateServiceSupport<DemoInfo> implements DemoService{

	// 获得当前service所属的DAO
	private DemoDao getCurrentDao(){
		return (DemoDao) baseDao;
	}


}
