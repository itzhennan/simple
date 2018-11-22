package cn.zznlin.simple.base.dao.impl;

import cn.zznlin.simple.base.dao.UploadDao;
import cn.zznlin.simple.base.entity.UploadFiles;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/23 0:28
 * @Description
 */
@Repository()
public class UploadDaoImpl extends HibernateDaoSupport<UploadFiles> implements UploadDao {
}
