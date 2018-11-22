package cn.zznlin.simple.base.dao.impl;

import cn.zznlin.simple.base.dao.SMDDao;
import cn.zznlin.simple.base.entity.SMDInfo;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/23 0:14
 * @Description
 */
@Repository
public class SMDDaoImpl extends HibernateDaoSupport<SMDInfo> implements SMDDao {
}
