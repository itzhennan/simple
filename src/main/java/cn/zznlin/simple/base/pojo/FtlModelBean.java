package cn.zznlin.simple.base.pojo;

import java.io.Serializable;

/**
 * @Author zhennan
 * @Date 2018/7/19 7:30
 * @Description
 */
public class FtlModelBean implements Serializable{

    // id
    private Long id;
    // title
    private String title;
    // text
    private String text;
    // url
    private String url;
    // Iimage Url
    private String imageUrl;
    // time
    private String time;

    //
    private String content;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
