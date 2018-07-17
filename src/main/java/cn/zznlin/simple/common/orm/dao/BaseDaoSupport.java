package cn.zznlin.simple.common.orm.dao;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.common.utils.LoggerUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/7/15 23:02
 * @Description
 */
public abstract class BaseDaoSupport<T extends  BaseEntity> implements BaseDao<T> {


    protected final String CLASS_NAME = getClass().getName();

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void update(Object obj) {
        getSession().update(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(Serializable id) {
        return (T) getSession().get(getEntityClass(), id);
    }

    @Override
    @Transactional
    public void insert(Object entity) {
        getSession().save(entity);
    }

    @Override
    @Transactional
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    @Transactional
    public void merge(T entity) {
        getSession().merge(entity);
    }

    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        try {
            return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Exception e) {
            return (Class<T>) getEntity(getClass().getSuperclass().getGenericInterfaces());
        }
    }
    protected Class<?> getEntity(Type[] types) {
        Class<?> entityClass = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                entityClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else if (type instanceof Class) {
                entityClass = getEntity(((Class<?>) type).getGenericInterfaces());
            }
        }
        LoggerUtils.debug(CLASS_NAME, "ActualTypeArguments [entityClass {}]", entityClass);
        return entityClass;
    }

    @Override
    public List<T> findDatas(String propertyName, Object value) {
        Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
        if (propertyName.indexOf(".") != -1) {
            String alias = propertyName.substring(0, propertyName.indexOf("."));
            criteria.createAlias(alias, alias);
        }
        criteria.add(Restrictions.eq(propertyName, value));
        return criteria.list();
    }
}
