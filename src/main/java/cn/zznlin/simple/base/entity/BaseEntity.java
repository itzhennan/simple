package cn.zznlin.simple.base.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
/**
 * @Author zhennan
 * @Date 2018/7/14
 * @Description
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Column(updatable = false)
    @Type(type = "cn.zznlin.simple.base.type.PersistentDateTime")
    private DateTime createDateTime;

    @Type(type = "cn.zznlin.simple.base.type.PersistentDateTime")
    private DateTime lastModifyDateTime;

    @Column(name = "version", columnDefinition = "int(11) default 0")
    private Integer version=0;

    public DateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(DateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public DateTime getLastModifyDateTime() {
        return lastModifyDateTime;
    }

    public void setLastModifyDateTime(DateTime lastModifyDateTime) {
        this.lastModifyDateTime = lastModifyDateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
