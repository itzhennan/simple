package cn.zznlin.simple.common.orm.dao;

import cn.zznlin.simple.base.entity.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DaoSupport;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author zhennan
 * @Date 2018/7/15 23:11
 * @Description
 */
public class HibernateDaoSupport<T extends BaseEntity> extends BaseDaoSupport<T> {
    @Override
    public void update(T entity) {

    }

    @Override
    public void saveOrUpdate(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void evict(Object... entities) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findDatas(Map<String, Object> map) {
        return null;
    }

    @Override
    public Long count(String propertyName, Object value) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public long countCriteriaResult(Criteria criteria) {
        return 0;
    }

    @Override
    public DetachedCriteria createDetachedCriteria() {
        return null;
    }

    @Override
    public DetachedCriteria createDetachedCriteria(String alias) {
        return null;
    }
}
