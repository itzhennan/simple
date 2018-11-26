package cn.zznlin.simple.article.entity;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.common.cons.Cons;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 文章 和 文章标签 映射类
 * @author zhennan zhang
 * @date 2018/11/24 14:43
 * @description
 */
@Entity
@Table(name = Cons.TABLEHEAD+"article_tag_mapper")
public class ArticleTagMapperInfo extends BaseEntity{
    @Id
    @GeneratedValue(generator = Cons.GENERATOR)
    @GenericGenerator(name = Cons.GENERATOR, strategy = Cons.STRATEGY)
    @Column(name = "mapper_id", columnDefinition = "bigint")
    private Long mapperId;

    @ManyToOne
    @JoinColumn(name = "article_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '文章'")
    private ArticleInfo article;

    @ManyToOne
    @JoinColumn(name = "tag_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '个人标签'")
    private ArticleTagInfo tag;

    @Column(name = "is_del",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
    private Integer isDel = 0;

    public Long getMapperId() {
        return mapperId;
    }

    public void setMapperId(Long mapperId) {
        this.mapperId = mapperId;
    }

    public ArticleInfo getArticle() {
        return article;
    }

    public void setArticle(ArticleInfo article) {
        this.article = article;
    }

    public ArticleTagInfo getTag() {
        return tag;
    }

    public void setTag(ArticleTagInfo tag) {
        this.tag = tag;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
