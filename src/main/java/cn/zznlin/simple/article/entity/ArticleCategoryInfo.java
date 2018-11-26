package cn.zznlin.simple.article.entity;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.cons.Cons;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhennan
 * @Date 2018/10/29 19:23
 * @Description
 *    文章个人分类 一个文章可以有多个个人分类,一个个人分类可以归属多个文章
 */
@Entity
@Table(name = Cons.TABLEHEAD+"article_category")
public class ArticleCategoryInfo extends BaseEntity {

    @Id
    @GeneratedValue(generator = Cons.GENERATOR)
    @GenericGenerator(name = Cons.GENERATOR, strategy = Cons.STRATEGY)
    @Column(name = "article_category_id", columnDefinition = "bigint")
    private Long articleCategoryId;

    // 分类名称
    @Column(name = "category_name",columnDefinition = "VARCHAR(255) NOT NULL COMMENT '文章个人分类名称'")
    private String categoryName;

    // 作者
    @ManyToOne
    @JoinColumn(name = "user_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '创作者'")
    private User user;

    @Column(name = "is_del",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
    private Integer isDel = 0;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
