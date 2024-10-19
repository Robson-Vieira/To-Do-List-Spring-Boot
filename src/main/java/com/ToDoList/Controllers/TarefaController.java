package com.ToDoList.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoList.DTO.TarefaDTO;
import com.ToDoList.Services.TarefaServices;


@RestController
@RequestMapping(path="tarefa")
public class TarefaController {

	@Autowired 
	private TarefaServices service;
	
	@GetMapping()
	public List<TarefaDTO> listAll() {
		return service.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public TarefaDTO foundById(@PathVariable Long id) {
		return service.buscaPorId(id);
	}
	
	@PostMapping()
	public TarefaDTO create(@RequestBody TarefaDTO dto) {
		return service.criarTarefa(dto);
	}
	
	@PutMapping()
	public TarefaDTO update(@RequestBody TarefaDTO dto) {
		return  service.atualizarTarefa(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deletarTarefa(id);
	}
}
