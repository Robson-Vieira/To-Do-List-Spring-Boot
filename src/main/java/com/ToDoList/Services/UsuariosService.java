package com.ToDoList.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ToDoList.Repositories.UsuariosRepository;

@Service
public class UsuariosService implements UserDetailsService {

	@Autowired
	private UsuariosRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.FindByUsername(username);
		return user;
	}

}
