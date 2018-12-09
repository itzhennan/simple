package cn.zznlin.simple.admin.calendar.entity;

import cn.zznlin.simple.base.entity.BaseEntity;
import cn.zznlin.simple.base.entity.User;
import cn.zznlin.simple.common.cons.Cons;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhennan zhang
 * @date 2018/12/7 10:27
 * @description
 *     日历备忘录
 */
@Entity
@Table(name = Cons.TABLEHEAD+"calendar")
public class CalendarInfo extends BaseEntity {

    @Id
    @GeneratedValue(generator = Cons.GENERATOR)
    @GenericGenerator(name = Cons.GENERATOR, strategy = Cons.STRATEGY)
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    // 标题
    @Column(name = "title",columnDefinition = "VARCHAR(255) NOT NULL COMMENT '标题'")
    private String title;

    // 默认是日期+时间
    @Column(name = "type",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'时间数据类型 0:日期+时间 1:日期  '")
    private Integer type = 0;

    // 开始时间
    @Column(name = "start",columnDefinition = "BIGINT(11) COMMENT'开始时间'")
    private Long start;

    // 结束时间
    @Column(name = "end",columnDefinition = "BIGINT(11) COMMENT'结束时间'")
    private Long end;

    // 颜色
    @Column(name = "color",columnDefinition = "VARCHAR(7) COMMENT '颜色'")
    private String color;

    // 网址
    @Column(name = "url",columnDefinition = "VARCHAR(255) COMMENT '网址'")
    private String url;

    // 作者
    @ManyToOne
    @JoinColumn(name = "user_id",columnDefinition = "BIGINT(11) NOT NULL COMMENT '创作者'")
    private User user;

    @Column(name = "is_del",columnDefinition = "INT(1) DEFAULT 0 NOT NULL COMMENT'是否作废 0:否  1:是'")
    private Integer isDel = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
