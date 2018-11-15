package cn.zznlin.simple.base.service.impl;

import cn.zznlin.simple.article.entity.ArticleCategoryInfo;
import cn.zznlin.simple.base.dao.UploadDao;
import cn.zznlin.simple.base.entity.UploadFiles;
import cn.zznlin.simple.base.service.UploadService;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author zhennan
 * @Date 2018/10/22 22:30
 * @Description
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Resource(name = "UploadDao")
    private UploadDao uploadDao;

    @Override
    public void save(UploadFiles uploadFile) {
        uploadDao.save(uploadFile);
    }
}
