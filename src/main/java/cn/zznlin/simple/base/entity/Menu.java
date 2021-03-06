package cn.zznlin.simple.base.entity;

import cn.zznlin.simple.common.cons.Cons;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:菜单
 */
@Entity
@Table(name = Cons.TABLEHEAD + "menu")
public class Menu extends BaseEntity{
	@Id
	@GeneratedValue
	@Column(name = "menu_id")
	private int id;
	@Column(name = "name")
	private String name; // 菜单名称
	@Column(name = "page")
	private String page; // 访问路径
	@Column(name = "priority")
	private Integer priority; // 优先级
	@Column(name = "description")
	private String description; // 描述

	@ManyToMany(mappedBy = "menus")
	private Set<Role> roles = new HashSet<Role>(0);

	@OneToMany(mappedBy = "parentMenu")
	private Set<Menu> childrenMenus = new HashSet<Menu>();

	@ManyToOne
	@JoinColumn(name = "pid")
	private Menu parentMenu;

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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Menu> getChildrenMenus() {
		return childrenMenus;
	}

	public void setChildrenMenus(Set<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

}
