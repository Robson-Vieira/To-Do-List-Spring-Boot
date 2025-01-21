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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/tarefas")
@Tag(name = "Gerenciador de tarefas.", description = "Endpoints reestritos para usuarios autenticados ")
public class TarefaController {

	@Autowired 
	private TarefaServices service;
	
	@Operation(summary = "Lista todas as tarefas cadastradas.", description = "Busca todas as tarefas cadastradas.",
			security = @SecurityRequirement(name="BearerAuth"),
			responses = {
				@ApiResponse(responseCode = "200", content = @Content),
				@ApiResponse(responseCode = "200", content = @Content),
		}
	)
	@GetMapping()
	public List<TarefaDTO> listAll() {
		return service.buscarTodos();
	}
	
	@Operation(summary = "Lista uma tarefa a partir de uma ID fornecido.", 
			security = @SecurityRequirement(name="BearerAuth"),
			description = "Busca uma tarefa a partir de uma ID fornecido.")
	@GetMapping("/{id}")
	public TarefaDTO foundById(@PathVariable Long id) {
		return service.buscaPorId(id);
	}
	
	@Operation(summary = "Cria uma nova tarefa.",
			security = @SecurityRequirement(name="BearerAuth"),
			description ="Cria uma nova tarefa." )
	@PostMapping()
	public TarefaDTO create(@RequestBody TarefaDTO dto) {
		return service.criarTarefa(dto);
	}
	@Operation(summary = "Atualiza uma tarefa.", 
			security = @SecurityRequirement(name="BearerAuth"),
			description = "Atualiza uma tarefa.")
	@PutMapping()
	public TarefaDTO update(@RequestBody TarefaDTO dto) {
		return  service.atualizarTarefa(dto);
	}
	
	@Operation(summary = "Deleta uma tarefa a partir de um ID fornecido.",
			security = @SecurityRequirement(name="BearerAuth"),
			description = "Deleta uma tarefa a partir de um ID fornecido.")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deletarTarefa(id);
	}
}
