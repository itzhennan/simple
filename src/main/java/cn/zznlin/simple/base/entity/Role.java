package cn.zznlin.simple.base.entity;

import cn.zznlin.simple.common.cons.Cons;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:角色
 */
@Entity
@Table(name = Cons.TABLEHEAD + "role")
public class Role extends BaseEntity{
	@Id
	@GeneratedValue
	@Column(name = "role_id")
	private int id;
	@Column(name = "name")
	private String name; // 角色名称
	@Column(name = "keyword")
	private String keyword; // 角色关键字，用于权限控制
	@Column(name = "description")
	private String description; // 描述

	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<User>(0);

	@ManyToMany
	@JoinTable(name = Cons.TABLEHEAD +"role_permission", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "role_id") }, inverseJoinColumns = {
					@JoinColumn(name = "permission_id", referencedColumnName = "permission_id") })
	private Set<Permission> permissions = new HashSet<Permission>(0);

	@ManyToMany
	@JoinTable(name = Cons.TABLEHEAD +"role_menu", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "role_id") }, inverseJoinColumns = {
					@JoinColumn(name = "menu_id", referencedColumnName = "menu_id") })
	private Set<Menu> menus = new HashSet<Menu>(0);

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

}
