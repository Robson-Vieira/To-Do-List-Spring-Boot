package com.ToDoList.Model.Users;

import java.util.Collection;
import java.util.List;
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

@Entity
public class Usuarios  implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String nome;
	@Column(nullable = false)
	private String senha;
	@Enumerated(EnumType.STRING)
	private Permissao permissao;

	public Usuarios() {}
	
	public Usuarios(Long id,String nome,String senha,Permissao permissao) {
		this.id =id;
		this.nome=nome;
		this.senha=senha;
		this.permissao=permissao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, permissao, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && permissao == other.permissao
				&& Objects.equals(senha, other.senha);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (permissao == Permissao.ROLE_ADMIN){ 	return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));}
		
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return senha;
		
	}

	@Override
	public String getUsername() {
		return nome;
	}
	
	
	

}
