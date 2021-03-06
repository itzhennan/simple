package cn.zznlin.simple.common.orm.interceptor;


import cn.zznlin.simple.base.entity.BaseEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by zhennan on 2018/7/14.
 */
@SuppressWarnings("serial")
public class EntityInterceptor extends EmptyInterceptor {

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames,
                                Type[] types) {
        if (entity instanceof BaseEntity) {
            BaseEntity BaseEntity = (BaseEntity) entity;
            boolean modified = false;
            for (int i = 0; i < propertyNames.length; i++) {
                if ("lastModifyDateTime".equals(propertyNames[i])) {
                    DateTime lastModifyDateTime = new DateTime();
                    currentState[i] = lastModifyDateTime;
                    BaseEntity.setLastModifyDateTime(lastModifyDateTime);
                    modified = true;
                }
            }
            return modified;
        }
        return false;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            boolean modified = false;
            for (int i = 0; i < propertyNames.length; i++) {
                if ("createDateTime".equals(propertyNames[i])) {
                    DateTime createDateTime = new DateTime();
                    state[i] = createDateTime;
                    baseEntity.setCreateDateTime(createDateTime);
                    modified = true;
                } else if ("lastModifyDateTime".equals(propertyNames[i])) {
                    DateTime lastModifyDateTime = new DateTime();
                    state[i] = lastModifyDateTime;
                    baseEntity.setLastModifyDateTime(lastModifyDateTime);
                    modified = true;
                }
            }
            return modified;
        }
        return false;
    }
}
