package com.ToDoList.Exceptions;

public class TarefaNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TarefaNotFoundException(String mensagem) {
		super(mensagem);
	}

	
}
