package cn.zznlin.simple.base.pojo;

/**
 * @Author zhennan
 * @Date 2018/12/8 23:23
 * @Description
 */
public class PermissionCond {
    private Long userId;

    public String getPermissionsByUserIdSql() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT sp_.permission_id permissionId,sp_.keyword permissionKeyword,sp_.`name` permissionName,sr.role_id roleId,sr.keyword roleKeyword,sr.`name` roleName FROM simple_permission sp_ ")
        .append(" LEFT JOIN simple_role_permission srp ON srp.permission_id = sp_.permission_id ")
        .append(" LEFT JOIN simple_role sr ON sr.role_id = srp.role_id ")
        .append(" LEFT JOIN simple_user_role sur ON sur.role_id = sr.role_id ")
        .append(" WHERE 1=1 ")
        .append(" AND sur.user_id = ").append(userId);
        return sb.toString();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
