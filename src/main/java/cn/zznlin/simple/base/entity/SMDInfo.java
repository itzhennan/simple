package cn.zznlin.simple.base.entity;

import cn.zznlin.simple.common.BaseCons;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhennan
 * @Date 2018/10/29 19:46
 * @Description
 *     项目字典表
 */
@Entity
@Table(name = "simple_smd")
public class SMDInfo extends BaseEntity {

    @Id
    @GeneratedValue(generator = "simple_generator")
    @GenericGenerator(name = "simple_generator", strategy = BaseCons.STRATEGY)
    @Column(name = "smd_id", columnDefinition = "bigint")
    private Long smdId;

    @Column(name = "smd_name",columnDefinition = "VARCHAR(255) NOT NULL COMMENT '名称'")
    private String smdName;

    /*
        1. 文章标签
     */
    @Column(name="type",columnDefinition = "INT(11) DEFAULT 0 NOT NULL COMMENT '分类'")
    private Integer type = 0;

    @Column(name = "is_del",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
    private Integer isDel = 0;

    @Column(name="sort",columnDefinition = "INT(11) DEFAULT 0 NOT NULL COMMENT '排序'")
    private Integer sort = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id",columnDefinition = "BIGINT(11) COMMENT '父ID'")
    private SMDInfo parent;

    public Long getSmdId() {
        return smdId;
    }

    public void setSmdId(Long smdId) {
        this.smdId = smdId;
    }

    public String getSmdName() {
        return smdName;
    }

    public void setSmdName(String smdName) {
        this.smdName = smdName;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public SMDInfo getParent() {
        return parent;
    }

    public void setParent(SMDInfo parent) {
        this.parent = parent;
    }

}
