package cn.zznlin.simple.base.dao.impl;

import cn.zznlin.simple.base.dao.MenuDao;
import cn.zznlin.simple.base.entity.Menu;
import cn.zznlin.simple.common.orm.dao.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @Author zhennan
 * @Date 2018/11/25 23:01
 * @Description
 */
@Repository
public class MenuDaoImpl extends HibernateDaoSupport<Menu> implements MenuDao {
}
