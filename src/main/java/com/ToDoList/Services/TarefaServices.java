package com.ToDoList.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ToDoList.Model.Tarefa;
import com.ToDoList.Repositories.TarefaRepository;

@Service
public class TarefaServices {

	@Autowired
	private TarefaRepository repository;
	
	
	public Tarefa buscaPorId(Long id) {
		Tarefa tarefa = repository.findById(id);   //IMPLEMENTAR EXCESSAO
		
	}
	
	public List<Tarefa> buscarTodos() {
		return repository.findAll();
	}
	
	public Tarefa criarTarefa(Tarefa tarefa) {
		return repository.save(tarefa);
	}
	
	public Tarefa atualizarTarefa(Tarefa novaTarefa) {
		Optional<Tarefa> antigaTarefa = repository.findById(novaTarefa.getId()); // IMPLEMENTAR EXCESSAO 
																				 // PENSAR NOS CAMPOS QUE PODERAO SER ATUALIZADOS 
		
	}
	
	
	
	
	
	
	
	
	
	public void deletarTarefa(Long id) {
		repository.deleteById(id);
	}
	
}
