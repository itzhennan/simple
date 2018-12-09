package cn.zznlin.simple.demo.dao.impl;

import org.springframework.stereotype.Repository;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import cn.zznlin.simple.demo.entity.DemoInfo;
import cn.zznlin.simple.demo.dao.DemoDao;

/**
 * @author zhennan zhang
 * @date 2018-12-07
 * @description
 */
@Repository("demoDao")
public class DemoDaoImpl extends HibernateDaoSupport<DemoInfo> implements DemoDao{

}
