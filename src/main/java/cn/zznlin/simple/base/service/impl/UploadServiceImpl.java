package cn.zznlin.simple.base.service.impl;

import cn.zznlin.simple.base.entity.UploadFiles;
import cn.zznlin.simple.base.service.UploadService;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Service;

/**
 * @Author zhennan
 * @Date 2018/10/22 22:30
 * @Description
 */
@Service
public class UploadServiceImpl extends HibernateDaoSupport<UploadFiles> implements UploadService {
}
