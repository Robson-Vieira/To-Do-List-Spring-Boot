package com.ToDoList.Services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.ToDoList.Model.Users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

	@Value("${spring.security.token.SECRET}")
	private String secret;

	@Value("${spring.security.token.VALIDITY}")
	private Long validity;

	
	public String genereteToken(User user) {
		Date now = new Date();
		try {
			Algorithm alg = Algorithm.HMAC256(secret.getBytes());
			
			List<String> roles = user.getAuthorities()
					.stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());

			return JWT.create()
					.withIssuer("API-Todolist")
					.withSubject(user.getUsername())
					.withClaim("roles", roles)
					.withExpiresAt(new Date(now.getTime() + validity))
					.sign(alg)
					.strip();
		} catch (JWTCreationException e) {
			throw new JWTCreationException("Erro na geracao do token!", e);
		}
			
		
	}

	public String validateToken(String token) {

		try {
			Algorithm alg = Algorithm.HMAC256(secret.getBytes());
			return JWT.require(alg)
					.withIssuer("API-Todolist")
					.build()
					.verify(token)
					.getSubject();
		}

		catch (JWTVerificationException e) {
			return "Erro de verificacao!";
		}

	}
}
