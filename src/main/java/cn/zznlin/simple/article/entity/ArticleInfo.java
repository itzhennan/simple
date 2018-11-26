package cn.zznlin.simple.article.entity;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.base.entity.SMDInfo;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.cons.Cons;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

/**
 * @Author zhennan
 * @Date 2018/10/29 19:23
 * @Description
 */
@Entity
@Table(name = Cons.TABLEHEAD+"article")
public class ArticleInfo extends BaseEntity {

    @Id
    @GeneratedValue(generator = Cons.GENERATOR)
    @GenericGenerator(name = Cons.GENERATOR, strategy = Cons.STRATEGY)
    @Column(name = "article_id", columnDefinition = "bigint")
    private Long articleId;

    // 发布日期
    @Column(updatable = false)
    @Type(type = "cn.zznlin.simple.base.type.PersistentDateTime")
    private DateTime publicDateTime;

    // 作者
    @ManyToOne
    @JoinColumn(name = "user_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '创作者'")
    private User user;

    // 文章标题
    @Column(name = "title",columnDefinition = "VARCHAR(255) COMMENT '文章标题'")
    private String title;

    // 摘要
    @Column(name="abstracts",columnDefinition = "TEXT COMMENT '文章摘要'")
    private String abstracts;

    // 内容
    @Column(name="cont",columnDefinition = "TEXT COMMENT '文章内容'")
    private String cont;

    //文章标签
    @OneToMany(mappedBy = "article",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<ArticleTagMapperInfo> articleMapperTags;

    //个人分类
    @OneToMany(mappedBy = "article",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<ArticleCategoryMapperInfo> articleMapperCategorys;

    //文章类型  文章类型 1:原创 2:转载  3:翻译
    @ManyToOne
    @JoinColumn(name = "type",columnDefinition = "BIGINT(11) COMMENT '文章类型 1:原创 2:转载  3:翻译'")
    private SMDInfo type;

    //博客分类
    @ManyToOne
    @JoinColumn(name = "blog_category",columnDefinition = "BIGINT(11) COMMENT '博客分类'")
    private SMDInfo blogCategory;

    // 是否私密文章
    @Column(name = "is_private",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT' 0:公开文章  1:私密文章'")
    private Integer isPrivate = 0;

    // 状态  0:保存草稿  1:发布博客
    @Column(name = "status",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT' 0:保存草稿  1:发布博客'")
    private Integer status = 0;

    // 是否删除
    @Column(name = "is_del",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
    private Integer isDel = 0;

    // 是否置顶
    @Column(name = "is_top",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否置顶 0:否  1:是'")
    private Integer isTop = 0;

    @Column(name = "read_count",columnDefinition = "BIGINT(11) default 0 COMMENT '阅读量'")
    private Long readCount = 0L;

    @Column(name = "good_count",columnDefinition = "BIGINT(11) default 0 COMMENT '点赞量'")
    private Long goodCount = 0L;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public DateTime getPublicDateTime() {
        return publicDateTime;
    }

    public void setPublicDateTime(DateTime publicDateTime) {
        this.publicDateTime = publicDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public List<ArticleTagMapperInfo> getArticleMapperTags() {
        return articleMapperTags;
    }

    public void setArticleMapperTags(List<ArticleTagMapperInfo> articleMapperTags) {
        this.articleMapperTags = articleMapperTags;
    }

    public List<ArticleCategoryMapperInfo> getArticleMapperCategorys() {
        return articleMapperCategorys;
    }

    public void setArticleMapperCategorys(List<ArticleCategoryMapperInfo> articleMapperCategorys) {
        this.articleMapperCategorys = articleMapperCategorys;
    }

    public SMDInfo getType() {
        return type;
    }

    public void setType(SMDInfo type) {
        this.type = type;
    }

    public SMDInfo getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(SMDInfo blogCategory) {
        this.blogCategory = blogCategory;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Long goodCount) {
        this.goodCount = goodCount;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }
}
