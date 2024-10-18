package com.ToDoList.Config;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class ModelMapperConfig {


	public static final ModelMapper mapper = new ModelMapper();
	
	public static <O,D> D converteObjetos (O origem, Class<D> destino) {
		return mapper.map(origem, destino);
	}
	
	public static <O,D> List<D> converteListas (List<O> origem, Class<D> destino){
		List<D> lista = new ArrayList<>();
		for (O o : origem) {
			lista.add(mapper.map(o, destino));
		}
		return lista;
	}
	
}