package cn.zznlin.simple.base.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author zhennan
 * @Date 2018/7/15 11:39
 * @Description
 */
@Entity
@Table(name = "simple_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "user_id",columnDefinition = "BIGINT(11) COMMENT '主键基础用户ID'")
    private Long userId;

    @Column(name = "user_name",columnDefinition = "VARCHAR(50) COMMENT '基础用户姓名'")
    private String userName;

    @Column(name = "user_status",columnDefinition = "INT(1) default 0 COMMENT '用户状态，0正常 1限制登录 '")
    private Integer userStatus;

    @Column(name = "user_phone",columnDefinition = "VARCHAR(11) COMMENT'用户手机号'")
    private String userPhone;

    @Column(name = "user_password",columnDefinition = "VARCHAR(50) COMMENT'用户密码，MD5'")
    private String password;

    @Column(name = "user_email",columnDefinition = "VARCHAR(50) COMMENT'用户邮箱'")
    private String userEmail;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
