package cn.zznlin.simple.base.service;

import cn.zznlin.simple.base.entity.Permission;
import cn.zznlin.simple.base.pojo.ShiroBean;
import cn.zznlin.simple.common.orm.service.BaseService;

import java.util.List;

/**
 * @author zhennan zhang
 * @date 2018-12-08
 * @description
 */
public interface PermissionService extends BaseService<Permission>{

    List<ShiroBean> findPermissionListByUserId(Long id);
}
