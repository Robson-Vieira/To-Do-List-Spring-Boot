package com.ToDoList.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ToDoList.Model.Users.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Long, Usuarios> {

	UserDetails FindByUsername(String nome);
}
