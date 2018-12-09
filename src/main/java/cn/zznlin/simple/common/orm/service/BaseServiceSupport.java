package cn.zznlin.simple.common.orm.service;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.common.orm.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author zhennan
 * @Date 2018/11/22 23:56
 * @Description
 */
public class BaseServiceSupport<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected BaseDao<T> baseDao;

    @Override
    public T get(Serializable id) {
        return baseDao.get(id);
    }

    @Override
    public void insert(Object entity) {
        baseDao.insert(entity);
    }

    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Override
    public void merge(T entity) {
        baseDao.merge(entity);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public void update(Object obj) {
        baseDao.update(obj);
    }

    @Override
    public void saveOrUpdate(T entity) {
        baseDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }

    @Override
    public void clear() {
        baseDao.clear();
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public List<T> findDatas(String propertyName, Object value) {
        return baseDao.findDatas(propertyName,value);
    }

    @Override
    public List<T> findDatas(Map<String, Object> map) {
        return baseDao.findDatas(map);
    }

    @Override
    public Long count(String propertyName, Object value) {
        return baseDao.count(propertyName,value);
    }

    @Override
    public Long count() {
        return baseDao.count();
    }

}
