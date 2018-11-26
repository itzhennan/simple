package cn.zznlin.simple.base.entity;

import cn.zznlin.simple.common.cons.Cons;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author zhennan
 * @Date 2018/7/15 11:39
 * @Description
 */
@Entity
@Table(name = Cons.TABLEHEAD+"user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(generator = Cons.GENERATOR)
    @GenericGenerator(name = Cons.GENERATOR, strategy = Cons.STRATEGY)
    @Column(name = "user_id", columnDefinition = "bigint")
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

    @OneToOne
    @JoinColumn(name = "file_id", columnDefinition = "BIGINT COMMENT'用户头像'")
    private UploadFiles uploadFiles;

    @ManyToMany
    @JoinTable(name = Cons.TABLEHEAD + "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
    private Set<Role> roles = new HashSet<Role>(0);

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

    public UploadFiles getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(UploadFiles uploadFiles) {
        this.uploadFiles = uploadFiles;
    }
}
