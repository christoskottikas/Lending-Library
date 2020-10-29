package com.example.assignment.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer roleId;

	@Column(name = "name")
	private String roleName;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "roleID")
	@JsonManagedReference
	private Collection<User> userCollection;

	public Role() {
	}

	public Role(Integer roleId, String roleName, Collection<User> userCollection) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.userCollection = userCollection;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Collection<User> getUserCollection() {
		return userCollection;
	}

	public void setUserCollection(Collection<User> userCollection) {
		this.userCollection = userCollection;
	}

}
