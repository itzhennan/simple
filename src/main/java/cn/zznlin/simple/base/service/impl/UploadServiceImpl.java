package cn.zznlin.simple.base.service.impl;

import cn.zznlin.simple.base.dao.UploadDao;
import cn.zznlin.simple.base.entity.UploadFiles;
import cn.zznlin.simple.base.service.UploadService;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhennan
 * @Date 2018/10/22 22:30
 * @Description
 */
@Service
public class UploadServiceImpl extends HibernateServiceSupport<UploadFiles> implements UploadService {

    @Autowired
    private UploadDao uploadDao;

    @Override
    public void save(UploadFiles uploadFile) {
        uploadDao.save(uploadFile);
    }
}
