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

    // 内容
    private String content;

    private FtlModelBean header;
    private FtlModelBean body;
    private FtlModelBean author;
    private FtlModelBean footer;

    // footer的属性
    private FtlModelBean readText;
    private FtlModelBean general;
    private FtlModelBean love;
    private FtlModelBean comment;



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

    public FtlModelBean getHeader() {
        return header;
    }

    public void setHeader(FtlModelBean header) {
        this.header = header;
    }

    public FtlModelBean getBody() {
        return body;
    }

    public void setBody(FtlModelBean body) {
        this.body = body;
    }

    public FtlModelBean getAuthor() {
        return author;
    }

    public void setAuthor(FtlModelBean author) {
        this.author = author;
    }

    public FtlModelBean getFooter() {
        return footer;
    }

    public void setFooter(FtlModelBean footer) {
        this.footer = footer;
    }

    public FtlModelBean getReadText() {
        return readText;
    }

    public void setReadText(FtlModelBean readText) {
        this.readText = readText;
    }

    public FtlModelBean getGeneral() {
        return general;
    }

    public void setGeneral(FtlModelBean general) {
        this.general = general;
    }

    public FtlModelBean getLove() {
        return love;
    }

    public void setLove(FtlModelBean love) {
        this.love = love;
    }

    public FtlModelBean getComment() {
        return comment;
    }

    public void setComment(FtlModelBean comment) {
        this.comment = comment;
    }
}
