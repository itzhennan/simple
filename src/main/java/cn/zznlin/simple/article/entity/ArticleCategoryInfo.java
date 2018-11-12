package cn.zznlin.simple.article.entity;

import cn.zznlin.simple.base.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhennan
 * @Date 2018/10/29 19:23
 * @Description
 *    文章个人分类一个文章可以有多个个人分类
 */
@Entity
@Table(name = "simple_article_category")
public class ArticleCategoryInfo extends BaseEntity {

    @Id
    @GeneratedValue(generator = "identity")
    @GenericGenerator(name="identity",strategy = "identity")
    @Column(name = "article_category_id",columnDefinition = "BIGINT(11) COMMENT '文章个人分类Id'")
    private Long articleCategoryId;

    // 分类名称
    @Column(name = "category_name",columnDefinition = "VARCHAR(255) NOT NULL COMMENT '文章个人分类名称'")
    private String categoryName;

    @Column(name = "is_del",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
    private Integer isDel = 0;

    @ManyToOne
    @JoinColumn(name = "article_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '文章'")
    private ArticleInfo article;

    public Long getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Long articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public ArticleInfo getArticle() {
        return article;
    }

    public void setArticle(ArticleInfo article) {
        this.article = article;
    }

}
