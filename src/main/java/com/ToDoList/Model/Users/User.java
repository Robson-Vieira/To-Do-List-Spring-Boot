package com.ToDoList.Model.Users;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name = "users")
@Entity
public class User implements Serializable, UserDetails{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(unique = false, nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(unique = false, nullable = false)
	private Permission permission;
	
	public User(Long id, String username, String password, Permission permission) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.permission = permission;
	}

	public User() {
		super();
	}

	public User(String username, String password, Permission permission) {
		super();
		this.username = username;
		this.password = password;
		this.permission = permission;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, password, permission, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& permission == other.permission && Objects.equals(username, other.username);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (permission == Permission.ROLE_ADMIN) { 
			return java.util.List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else {
			return java.util.List.of( new SimpleGrantedAuthority("ROLE_USER"));
		}
		
	}

	@Override
	public String getPassword() {
	
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

}
