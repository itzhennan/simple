package cn.zznlin.simple.admin.calendar.pojo;

/**
 * @author zhennan zhang
 * @date 2018/12/7 11:02
 * @description
 */
public class CalendarBean {

    private Long id;
    // 标题
    private String title;
    // 默认是日期+时间
    private Integer type = 0;
    // 开始时间
    private String start;
    // 结束时间
    private String end;
    // 颜色
    private String color;
    // 网址
    private String url;
    // 是否作废
    private Integer isDel = 0;
    // 时间
    private Long time = 0L;

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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
