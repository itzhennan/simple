package cn.zznlin.simple.base.service;

import cn.zznlin.simple.base.entity.UploadFiles;
import cn.zznlin.simple.common.orm.dao.BaseDao;

/**
 * @Author zhennan
 * @Date 2018/10/22 22:29
 * @Description
 */
public interface UploadService {
    void save(UploadFiles uploadFile);
}
