package cn.zznlin.simple.common.orm.dao;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.common.bean.Page;
import cn.zznlin.simple.common.orm.filter.PropertyFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author zhennan
 * @Date 2018/7/15 22:59
 * @Description
 */
public interface BaseDao <T extends BaseEntity> {

    T get(Serializable id);

    void insert(Object entity);

    void save(T entity);

    void merge(T entity);

    void update(T entity);

    void update(Object obj);

    void saveOrUpdate(T entity);

    void delete(T entity);

    void delete(Serializable id);

    void evict(Object... entities);

    void clear();

    List<T> findAll();

    List<T> findDatas(String propertyName, Object value);

    List<T> findDatas(Map<String, Object> map);

    List<T> findPage(Page page);

    List<T> findPage(Object object, Page page);

    List<T> findDatas(String propertyName, Object value, Page page);

    List<T> findPage(DetachedCriteria detachedCriteria, Page page);

    List<T> findPage(final Page page, final List<PropertyFilter> filters);

    List<T> findPage(DetachedCriteria detachedCriteria, final Page page, final List<PropertyFilter> filters);

    Long count(String propertyName, Object value);

    Long count();

    long countCriteriaResult(final Criteria criteria);

    DetachedCriteria createDetachedCriteria();

    DetachedCriteria createDetachedCriteria(String alias);
}
