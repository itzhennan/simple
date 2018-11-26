package cn.zznlin.simple.article.pojo;

/**
 * @Author zhennan
 * @Date 2018/11/21 23:12
 * @Description
 */
public class ArticleCond {
    private Long userId = -99L;
    // 0:保存草稿  1:发布博客
    private Integer status = -99;
    // 0:公开文章  1:私密文章
    private Integer isPrivate = -99;
    // 是否作废 0:否  1:是
    private Integer isDel = -99;

    /**
     * 获得博客首页的SQL
     * @return
     */
    public String findIndex() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ")
        .append("    sar.article_id artid, sar.publicDateTime, sar.type typ,sar.title titl, sar.abstracts ")
        .append(" FROM simple_article sar ")
        .append(" LEFT JOIN simple_user su ON su.user_id = sar.user_id ")
        .append(" WHERE 1=1");
        if(userId != -99){
            sb.append(" AND su.user_id = ").append(userId);
        }
        if(isDel != -99){
            sb.append(" AND sar.is_del = ").append(isDel);
        }
        if(status != -99){
            sb.append(" AND sar.status = ").append(status);
        }
        if(isPrivate != -99){
            sb.append(" AND sar.is_private = ").append(isPrivate);
        }
        sb.append(" ORDER BY sar.createDateTime DESC");
        return sb.toString();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    /**
     * 0:保存草稿  1:发布博客
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    /**
     * 0:公开文章  1:私密文章
     * @param isPrivate
     */
    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 是否作废 0:否  1:是
     * @param isDel
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
