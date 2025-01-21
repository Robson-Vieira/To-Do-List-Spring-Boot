package com.ToDoList.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ToDoList.Services.TokenService;
import com.ToDoList.Services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class TokenFilter extends OncePerRequestFilter{

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService usuariosService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = this.recoveryToken(request);
		
		if (token != null) {
			String username = tokenService.validateToken(token);
			var user = usuariosService.loadUserByUsername(username);
			
			if (user.getUsername() == null) {
				throw new UsernameNotFoundException("Usuario nao encontrado!");
			}
			var authentication = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String recoveryToken (HttpServletRequest request) {
		var token = request.getHeader("Authorization");
		
		if (token == null || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.replace("Bearer ","");
	}
}
