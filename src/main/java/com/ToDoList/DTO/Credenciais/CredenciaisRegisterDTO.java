package com.ToDoList.DTO.Credenciais;

import java.util.Objects;

import com.ToDoList.Model.Users.Permissao;

public class CredenciaisRegisterDTO {

	private String nome;
	private String senha;
	private Permissao permissao;

	public CredenciaisRegisterDTO(String nome, String senha, Permissao permissao) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.permissao = permissao;
	}

	public CredenciaisRegisterDTO() {
		super();
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
		return Objects.hash(nome, permissao, senha);
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
		return Objects.equals(nome, other.nome) && permissao == other.permissao && Objects.equals(senha, other.senha);
	}

}
