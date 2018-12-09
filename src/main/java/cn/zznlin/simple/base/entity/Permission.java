package cn.zznlin.simple.base.entity;

import cn.zznlin.simple.common.cons.Cons;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:权限名称
 */
@Entity
@Table(name = Cons.TABLEHEAD + "permission")
public class Permission extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name = "permission_id")
	private int id;

	@Column(name = "name")
	private String name; // 权限名称

	@Column(name = "keyword")
	private String keyword; // 权限关键字，用于权限控制

	@Column(name = "description")
	private String description; // 描述

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles = new HashSet<Role>(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
