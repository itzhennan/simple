package cn.zznlin.simple.common.orm.dao;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.common.utils.LoggerUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.Subcriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Simon Lv
 * @since 2012-5-28
 */
@Transactional(readOnly = true)
public abstract class BaseDaoSupport<T extends BaseEntity> implements BaseDao<T> {

    @Override
    public void update(Object obj) {
        getSession().update(obj);

    }

    protected final String CLASS_NAME = getClass().getName();

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

    @Override
    @Transactional
    public void update(T entity) {
//		Class<?> clz = entity.getClass();
//		Object obj;
//			obj = clz.newInstance();

//			Integer version =  (Integer)clz.getMethod("getVersion").invoke(obj);

//			clz.getMethod("setVersion",Integer.class).invoke(obj,version);
        Integer version =entity.getVersion();
        version ++;
        entity.setVersion(version);
        getSession().update(entity);

    }

    @Override
    @Transactional
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        if(entity==null){
            return;
        }
        getSession().delete(entity);
    }

    @Override
    @Transactional
    public void delete(Serializable id) {
        T entity = get(id);
        if (entity == null) {
            return;
        }
        getSession().delete(entity);
    }

    @Override
    public void evict(Object... entities) {
        if (entities == null) {
            return;
        }
        for (Object entity : entities) {
            getSession().evict(entity);
        }
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return createDetachedCriteria().getExecutableCriteria(getSession()).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findDatas(DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findDatas(String propertyName, Object value) {
        Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
        if (propertyName.indexOf(".") != -1) {
            String alias = propertyName.substring(0, propertyName.indexOf("."));
            criteria.createAlias(alias, alias);
        }
        criteria.add(Restrictions.eq(propertyName, value));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findDatas(Map<String, Object> map) {
        Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
        for (Entry<String, Object> entry : map.entrySet()) {
            String propertyName = entry.getKey();
            Object value = entry.getValue();
            createAlias(criteria, propertyName);
            addRestriction(criteria, propertyName, value);
        }
        return criteria.list();
    }

    //创建别名
    public void createAlias(Criteria criteria, String propertyNames) {
        int lastIndex  = propertyNames.lastIndexOf(".");
        if (lastIndex != -1) {
            String aliasPrefix = propertyNames.substring(0, lastIndex);
            String[] propertyNameArray = aliasPrefix.split("\\.");

            StringBuilder sb = new StringBuilder();
            for (String propertyName : propertyNameArray) {
                if (sb.length() > 0) {
                    sb.append(".");
                }
                sb.append(propertyName);

                String associationPath = sb.toString();
                String alias = associationPath.replaceAll("\\.", "_");
                //判断别名对否存在
                boolean aliasExits = existAlias(criteria, alias);
                if(!aliasExits){
                    criteria.createAlias(associationPath, alias);
                    System.out.println(associationPath + " , " + alias);
                }
            }
        }
    }


    //添加查询条件
    public void addRestriction(Criteria criteria, String propertyNames, Object value){
        int lastIndex  = propertyNames.lastIndexOf(".");
        if (lastIndex!=-1) {
            String alias = propertyNames.substring(0, lastIndex);
            String last = propertyNames.substring(lastIndex);
            propertyNames = alias.replaceAll("\\.", "_") + last;
        }
        criteria.add(Restrictions.eq(propertyNames, value));
    }

    /**
     * 判断别名是否存在[http://ju.outofmemory.cn/entry/156068]
     *
     * @param criteria
     * @param alias
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean existAlias(Criteria criteria, String alias) {
        Iterator<Subcriteria> it = ((CriteriaImpl) criteria).iterateSubcriteria();
        while (it.hasNext()) {
            Subcriteria sub = (Subcriteria) it.next();
            if (alias.equals(sub.getAlias())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
    }

    @Override
    public DetachedCriteria createDetachedCriteria(String alias) {
        return DetachedCriteria.forClass(getEntityClass(), alias);
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

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
