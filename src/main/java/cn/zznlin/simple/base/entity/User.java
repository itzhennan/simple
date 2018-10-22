package cn.zznlin.simple.base.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author zhennan
 * @Date 2018/7/15 11:39
 * @Description
 */
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "user_id",columnDefinition = "bigint(11) COMMENT '主键基础用户ID'")
    private Long userId;

    @Column(name = "user_name",columnDefinition = "varchar(50) COMMENT '基础用户姓名'")
    private String userName;

    @Column(name = "user_status",columnDefinition = "integer(11) default 0 COMMENT '用户状态，0正常 1限制登录 '")
    private String userStatus;

    @Column(name = "user_phone",columnDefinition = "bigint(11) COMMENT'用户手机号'")
    private Long userPhone;

    @Column(name = "user_password",columnDefinition = "varchar(50) COMMENT'用户密码，MD5'")
    private String password;

    @Column(name = "user_email",columnDefinition = "varchar(50) COMMENT'用户邮箱'")
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
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
