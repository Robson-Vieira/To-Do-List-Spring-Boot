package com.ToDoList.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ToDoList.Model.Tarefas.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	
}
