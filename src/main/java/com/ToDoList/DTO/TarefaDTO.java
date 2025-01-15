package com.ToDoList.DTO;

import java.util.Date;
import java.util.Objects;

import com.ToDoList.Model.Tarefas.Prioridade;
import com.ToDoList.Model.Tarefas.Status;

public class TarefaDTO {

	private Long id;
	private String titulo;
	private Date criacao;
	private Date prazo;
	private String descricao;
	private Status status;
	private Prioridade prioridade;
	
	public TarefaDTO() {}
	
	public TarefaDTO(Long id, String titulo, Date criacao, Date prazo, String descricao, Status status,
			Prioridade prioridade) {
		this.id = id;
		this.titulo = titulo;
		this.criacao = criacao;
		this.prazo = prazo;
		this.descricao = descricao;
		this.status = status;
		this.prioridade = prioridade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(criacao, descricao, id, prazo, prioridade, status, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarefaDTO other = (TarefaDTO) obj;
		return Objects.equals(criacao, other.criacao) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(prazo, other.prazo) && prioridade == other.prioridade
				&& status == other.status && Objects.equals(titulo, other.titulo);
	}
	
	
	

}
