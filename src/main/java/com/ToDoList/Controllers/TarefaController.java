package com.ToDoList.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.data.domain.Sort;
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
	public ResponseEntity<Page<TarefaDTO>> listAll(
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "limit",defaultValue = "10") Integer limit,
			@RequestParam(value = "direction",defaultValue = "asc") String direction) {	
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC: Direction.ASC;
		
		var pageble = PageRequest.of(page,limit, Sort.by(sortDirection, "titulo"));
		
		
		return ResponseEntity.ok(service.buscarTodos(pageble)) ;
		//adicionar alteracoes ao SWAGGER
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
