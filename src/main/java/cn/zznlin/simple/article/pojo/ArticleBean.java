package cn.zznlin.simple.article.pojo;

/**
 * @author zhennan zhang
 * @date 2018/11/12 12:34
 * @description
 */
public class ArticleBean {
    //
    private String titl;
    //
    private Long typ;
    //
    private String cont;
    //
    private String categories;
    //
    private Long chnl;
    //
    private Long level;
    //
    private String tag2;
    //
    private Long artid;
    //
    private Boolean isPrivate;
    //
    private String Description;
    //
    private String stat;
    
    private String fileName;
    // 是否自动保存
    private Integer isauto;
    // 摘要
    private String abstracts;

    public String getTitl() {
        return titl;
    }

    public void setTitl(String titl) {
        this.titl = titl;
    }

    public Long getTyp() {
        return typ;
    }

    public void setTyp(Long typ) {
        this.typ = typ;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Long getChnl() {
        return chnl;
    }

    public void setChnl(Long chnl) {
        this.chnl = chnl;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public Long getArtid() {
        return artid;
    }

    public void setArtid(Long artid) {
        this.artid = artid;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Integer getIsauto() {
        return isauto;
    }

    public void setIsauto(Integer isauto) {
        this.isauto = isauto;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }
}
