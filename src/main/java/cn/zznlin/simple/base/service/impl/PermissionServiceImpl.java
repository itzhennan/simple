package cn.zznlin.simple.base.service.impl;

import cn.zznlin.simple.base.dao.PermissionDao;
import cn.zznlin.simple.base.entity.Permission;
import cn.zznlin.simple.base.pojo.PermissionCond;
import cn.zznlin.simple.base.pojo.ShiroBean;
import cn.zznlin.simple.base.service.PermissionService;
import cn.zznlin.simple.common.orm.service.HibernateServiceSupport;
import cn.zznlin.simple.common.utils.ReflectionUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhennan zhang
 * @date 2018-12-08
 * @description
 */
@Service()
public class PermissionServiceImpl extends HibernateServiceSupport<Permission> implements PermissionService{

	// 获得当前service所属的DAO
	private PermissionDao getCurrentDao(){
		return (PermissionDao) baseDao;
	}

	/**
	 * 查找用户的所有权限
	 * @param id
	 * @return
	 */
	@Override
	public List<ShiroBean> findPermissionListByUserId(Long id) {
        List<ShiroBean> list = Lists.newArrayList();
        PermissionCond permissionCond = new PermissionCond();
        permissionCond.setUserId(id);
        List<Map<String, Object>> pageByNative = getCurrentDao().findPageByNative(permissionCond.getPermissionsByUserIdSql());
        for (Map<String, Object> map: pageByNative) {
            list.add(ReflectionUtils.fillClassForMap(map,ShiroBean.class));
        }
        return list;
	}
}
