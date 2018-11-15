package cn.zznlin.simple.base.dao;

import cn.zznlin.simple.base.entity.UploadFiles;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @author zhennan zhang
 * @date 2018/11/15 16:53
 * @description
 */
@Repository(value = "UploadDao")
public class UploadDao extends HibernateDaoSupport<UploadFiles> implements BaseDao<UploadFiles> {
}
