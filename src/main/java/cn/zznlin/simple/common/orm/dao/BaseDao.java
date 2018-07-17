package cn.zznlin.simple.common.orm.dao;

import cn.zznlin.simple.base.entity.BaseEntity;
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

    Long count(String propertyName, Object value);

    Long count();

    long countCriteriaResult(final Criteria criteria);

    DetachedCriteria createDetachedCriteria();

    DetachedCriteria createDetachedCriteria(String alias);

}
