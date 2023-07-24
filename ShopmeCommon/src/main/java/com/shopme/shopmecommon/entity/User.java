package com.shopme.shopmecommon.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=120, nullable = false, unique = true)
	private String email;
	
	@Column(length=64, nullable = false)
	private String password;
	
	@Column(name = "first_name", length=45, nullable = false)	
	private String firstName;
	
	@Column(name = "last_name", length=45, nullable = false)	
	private String lastName;
	
	@Column(length=64)	
	private String photos;
	
	private Boolean enabled;
	
	public User() {
		
	}
	
	public User(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	@ManyToMany
	@JoinTable(
			name="user_role",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	private Set<Role> role = new HashSet<>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	public void addRole(Role role) {
		this.role.add(role);
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", photos=" + photos + ", enabled=" + enabled + ", role=" + role + "]";
	}
	

}
