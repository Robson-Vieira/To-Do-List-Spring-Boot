package com.ToDoList.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoList.DTO.Credenciais.CredenciaisLoginDTO;
import com.ToDoList.DTO.Credenciais.CredenciaisRegisterDTO;
import com.ToDoList.Model.Users.User;
import com.ToDoList.Repositories.UserRepository;
import com.ToDoList.Services.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name="Authenticação de usuários",description = "Endpoints responsáveis por registrar e autenticar usuários" )
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService service;

	@Autowired
	private UserRepository repository;

	@Operation(summary = "Login do usuário",
			description = "Realiza a autenticação do usuário e retorna um token JWT em caso de sucesso.   OBS: Endpoint publico!",
			responses = @ApiResponse(
	                responseCode = "200",
	                description = "Autenticação bem-sucedida. Token JWT retornado."))
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid CredenciaisLoginDTO credenciais) {
		try {
			var auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(credenciais.getUsername(), credenciais.getPassword()));

			String token = service.genereteToken((User) auth.getPrincipal());
			return ResponseEntity.ok().body(token);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!!");
		}
		
	}
	 @Operation(
		        summary = "Registro de novo usuário",
		        description = "Registra um novo usuário no sistema.",
		        security = @SecurityRequirement(name="BearerAuth"),
		        responses = {
		                @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso."),
		                @ApiResponse(responseCode = "400", description = "Requisição inválida.")
		            }
		        )
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid CredenciaisRegisterDTO credenciais) {

		String username = credenciais.getUsername();

		UserDetails user = repository.findByUsername(username);

		if (user != null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nome de usuário já existe!");
		}

		String password = new BCryptPasswordEncoder().encode(credenciais.getPassword());

		User usuario = new User(username, password, credenciais.getPermission());

		User entity = repository.save(usuario);

		if (entity == null)return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario nao cadastrado");

		return ResponseEntity.status(HttpStatus.OK).body("Usuario cadastrado com sucesso");
	}
}
