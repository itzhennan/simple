package cn.zznlin.simple.article.pojo;

/**
 * @Author zhennan
 * @Date 2018/11/21 23:12
 * @Description
 */
public class ArticleCond {
    private Long userId = -99L;

    /**
     * 获得博客首页的SQL
     * @return
     */
    public String findIndex() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ")
        .append("    sar.article_id artid, sar.publicDateTime, sar.type typ,sar.title titl, sar.abstracts ")
        .append(" FROM simple_article sar ")
        .append(" LEFT JOIN simple_user su ON su.user_id = sar.user_id ");
        if(userId != -99){
            sb.append(" su.user_id = ").append(userId);
        }
        return sb.toString();
    }
}
