package cn.zznlin.simple.base.pojo;

/**
 * @Author zhennan
 * @Date 2018/12/8 23:30
 * @Description
 */
public class ShiroBean {

    private Long permissionId;
    private String permissionKeyword;
    private String permissionName;

    private Long roleId;
    private String roleKeyword;
    private String roleName;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionKeyword() {
        return permissionKeyword;
    }

    public void setPermissionKeyword(String permissionKeyword) {
        this.permissionKeyword = permissionKeyword;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleKeyword() {
        return roleKeyword;
    }

    public void setRoleKeyword(String roleKeyword) {
        this.roleKeyword = roleKeyword;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
