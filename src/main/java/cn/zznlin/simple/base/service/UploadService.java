package cn.zznlin.simple.base.service;

import cn.zznlin.simple.base.entity.UploadFiles;
import cn.zznlin.simple.common.orm.service.BaseService;

/**
 * @Author zhennan
 * @Date 2018/10/22 22:29
 * @Description
 */
public interface UploadService extends BaseService<UploadFiles>{
    void save(UploadFiles uploadFile);
}
