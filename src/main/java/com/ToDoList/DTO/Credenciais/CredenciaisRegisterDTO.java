package com.ToDoList.DTO.Credenciais;

import java.util.Objects;

import com.ToDoList.Model.Users.Permission;

public class CredenciaisRegisterDTO {

	private String username;
	private String password;
	private Permission permission;
	
	public CredenciaisRegisterDTO(String username, String password, Permission permission) {
		this.username = username;
		this.password = password;
		this.permission = permission;
	}
	
	public CredenciaisRegisterDTO() {}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(password, permission, username);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CredenciaisRegisterDTO other = (CredenciaisRegisterDTO) obj;
		return Objects.equals(password, other.password) && permission == other.permission
				&& Objects.equals(username, other.username);
	}
}