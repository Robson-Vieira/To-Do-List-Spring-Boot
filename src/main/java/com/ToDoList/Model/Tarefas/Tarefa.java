package com.ToDoList.Model.Tarefas;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@CreationTimestamp
	private Date criacao;
	
	@NotBlank
	private Date prazo;							// PENSAR EM UM ENDPOINT PARA ESTENDER O PRAZO 
	
	@NotBlank
	private String descricao;                      
	
	@Enumerated(EnumType.STRING)				
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	
	public Tarefa() {}
	
	public Tarefa(Long id, String titulo, Date criacao, Date prazo, String descricao, Status status,
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
		return Objects.hash(criacao, descricao, id, prazo, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(criacao, other.criacao) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(prazo, other.prazo)
				&& Objects.equals(titulo, other.titulo);
	}
	
	
	
	
}
