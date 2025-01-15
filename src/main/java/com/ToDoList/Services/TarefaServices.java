package com.ToDoList.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ToDoList.Config.ModelMapperConfig;
import com.ToDoList.DTO.TarefaDTO;
import com.ToDoList.Exceptions.TarefaNotFoundException;
import com.ToDoList.Model.Tarefas.Tarefa;
import com.ToDoList.Repositories.TarefaRepository;

@Service
public class TarefaServices {

	@Autowired
	private TarefaRepository repository;
	

	public TarefaDTO buscaPorId(Long id) {
		Tarefa tarefa = repository.findById(id)
				.orElseThrow(() -> new TarefaNotFoundException("Tarefa de id:" + id + "nao encontrada!"));   //IMPLEMENTAR EXCESSAO
		 TarefaDTO dto = ModelMapperConfig.converteObjetos(tarefa, TarefaDTO.class);
		 return dto;
	}
	
	public List<TarefaDTO> buscarTodos() {
		List<TarefaDTO> lista = ModelMapperConfig.
				converteListas(repository.findAll(), TarefaDTO.class);
		return lista;
	}
	
	public TarefaDTO criarTarefa(TarefaDTO tarefa) {
		Tarefa entidade = repository.save(ModelMapperConfig.converteObjetos(tarefa, Tarefa.class));
		TarefaDTO dto = ModelMapperConfig.converteObjetos(entidade, TarefaDTO.class);
		return dto;
	}
	
	public TarefaDTO atualizarTarefa(TarefaDTO tarefadto) {
		Tarefa entidade = repository.findById(tarefadto.getId())
				.orElseThrow(() -> new TarefaNotFoundException("Tarefa de id:" + tarefadto.getId() + "nao encontrada!")); // IMPLEMENTAR EXCESSAO && PENSAR NOS CAMPOS QUE PODERAO SER ATUALIZADOS 
		
		entidade.setTitulo(tarefadto.getTitulo());
		entidade.setPrazo(tarefadto.getPrazo());
		entidade.setDescricao(tarefadto.getDescricao());
		entidade.setStatus(tarefadto.getStatus());
		entidade.setPrioridade(tarefadto.getPrioridade());
		
		TarefaDTO saida = ModelMapperConfig.converteObjetos(entidade, TarefaDTO.class);
		return saida;
	}
	
	public void deletarTarefa(Long id) {
		repository.deleteById(id);
	}
	
}
