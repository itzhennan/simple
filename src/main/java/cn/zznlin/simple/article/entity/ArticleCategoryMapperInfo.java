package cn.zznlin.simple.article.entity;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.common.cons.Cons;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 文章 与 个人分类 映射表
 * @author zhennan zhang
 * @date 2018/11/24 14:37
 * @description
 */
@Entity
@Table(name = Cons.TABLEHEAD+"article_category_mapper")
public class ArticleCategoryMapperInfo extends BaseEntity {

    @Id
    @GeneratedValue(generator = Cons.GENERATOR)
    @GenericGenerator(name = Cons.GENERATOR, strategy = Cons.STRATEGY)
    @Column(name = "mapper_id", columnDefinition = "bigint")
    private Long mapperId;

    @ManyToOne
    @JoinColumn(name = "article_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '文章'")
    private ArticleInfo article;

    @ManyToOne
    @JoinColumn(name = "category_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '个人分类'")
    private ArticleCategoryInfo category;

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

    public ArticleCategoryInfo getCategory() {
        return category;
    }

    public void setCategory(ArticleCategoryInfo category) {
        this.category = category;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
