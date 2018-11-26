package cn.zznlin.simple.article.entity;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.common.cons.Cons;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhennan
 * @Date 2018/10/29 19:23
 * @Description
 *    文章标签 一个文章可以有多个标签
 */
@Entity
@Table(name = Cons.TABLEHEAD+"article_tag")
public class ArticleTagInfo extends BaseEntity {

    @Id
    @GeneratedValue(generator = Cons.GENERATOR)
    @GenericGenerator(name = Cons.GENERATOR, strategy = Cons.STRATEGY)
    @Column(name = "article_tag_id", columnDefinition = "bigint")
    private Long articleTagId;

    // 文章标签
    @Column(name = "tag_name",columnDefinition = "VARCHAR(255) NOT NULL COMMENT '文章标签'")
    private String tagName;

    @Column(name = "is_del",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
    private Integer isDel = 0;

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

}
