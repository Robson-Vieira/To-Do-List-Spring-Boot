package com.ToDoList.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoList.DTO.Credenciais.CredenciaisLoginDTO;
import com.ToDoList.DTO.Credenciais.CredenciaisRegisterDTO;
import com.ToDoList.Model.Users.Usuarios;
import com.ToDoList.Repositories.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(name = "/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService service;

	@Autowired
	private UsuariosRepository repository;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody CredenciaisLoginDTO credenciais) {

		var auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(credenciais.getNome(), credenciais.getSenha()));

		String token = service.generetdToken((Usuarios) auth.getPrincipal());
		return ResponseEntity.ok(token);

	}

	@PostMapping("/register")
	public String register(@RequestBody CredenciaisRegisterDTO credenciais) {
		
		String username = credenciais.getNome();
		
		UserDetails user = repository.FindByUsername(username);
		
		if (user != null) throw new RuntimeException("Nome de usuario inválido!");
		
		Usuarios usuario = new Usuarios();
		
		return "Usuario cadastrado com sucesso";
}
