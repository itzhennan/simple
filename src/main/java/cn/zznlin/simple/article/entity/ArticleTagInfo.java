package cn.zznlin.simple.article.entity;

import cn.zznlin.simple.base.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhennan
 * @Date 2018/10/29 19:23
 * @Description
 *    文章标签 一个文章可以有多个标签
 */
@Entity
@Table(name = "simple_article_tar")
public class ArticleTagInfo extends BaseEntity {

    @Id
    @GeneratedValue(generator = "identity")
    @GenericGenerator(name="identity",strategy = "identity")
    @Column(name = "article_tag_id",columnDefinition = "BIGINT(11) COMMENT '文章标签Id'")
    private Long articleTagId;

    // 文章标签
    @Column(name = "tag_name",columnDefinition = "VARCHAR(255) NOT NULL COMMENT '文章标签'")
    private String tagName;

    @Column(name = "is_del",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
    private Integer isDel = 0;

    @ManyToOne
    @JoinColumn(name = "article_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '文章'")
    private ArticleInfo article;

    public Long getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(Long articleTagId) {
        this.articleTagId = articleTagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
