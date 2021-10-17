package com.libreria.spring.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.spring.entidades.Autor;
import com.libreria.spring.errores.ErrorServicio;
import com.libreria.spring.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

	@Autowired
	private AutorRepositorio autorRepositorio;

	
	// Crear autor
	@Transactional
	public void crear(String nombre) throws ErrorServicio {
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre del autor no puede ser vacio");
		}
		Autor autor = new Autor();
		autor.setNombre(nombre);
		autor.setAlta(true);
		autorRepositorio.save(autor);
	}
		
	// Modificar autor
	@Transactional
	public void modificarNombre(String id, String nombre) throws Exception{
		Optional<Autor> respuesta = autorRepositorio.findById(id);
		
		if(respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setNombre(nombre);
			
			autorRepositorio.save(autor);
		} else {
			throw new ErrorServicio("No se encontro al usuario solicitado");
		}
	}
	
	// Deshabilitar autor
	@Transactional
	public void deshabilitar(String id) throws Exception{
		Optional<Autor> respuesta = autorRepositorio.findById(id);
		
		if(respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setAlta(false);
			
			autorRepositorio.save(autor);
		} else {
			throw new ErrorServicio("No se encontro al usuario solicitado");
		}
	}
	
	// Habilitar autor
	@Transactional
	public void habilitar(String id) throws Exception{
		Optional<Autor> respuesta = autorRepositorio.findById(id);
		
		if(respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setAlta(true);
			
			autorRepositorio.save(autor);
		} else {
			throw new ErrorServicio("No se encontro al usuario solicitado");
		}
	}
	
	// Buscar todos los autores
	@Transactional(readOnly = true)
	public List<Autor> buscarTodos()throws ErrorServicio {
		return autorRepositorio.findAll();
	}
	
	// Buscar autor por id
	@Transactional
	public Autor buscarPorId(String id) {
		Autor autor = autorRepositorio.findById(id).get();
		return autor;
	}
	
	
	public Autor buscarPorNombre(String nombre){
        Autor autor = new Autor();
//		for (Autor aux : autorRepositorio.buscarAutorNombre(nombre)) {	
//		}
		autor = autorRepositorio.buscarAutorNombre(nombre).get(0);
		return autor;
	}
	
//	// Buscar autor por nombre
//	public List<String> buscarIdPorNombre(String nombre) {
//		List<Autor> lista = autorRepositorio.findAll();
//		List<String> listaId = null;
//		
//		for (Autor aux : lista) {
//			if (aux.getNombre().equalsIgnoreCase(nombre)) {
//				String id = aux.getId();
//				listaId.add(id);
//			}
//		}
//		return listaId;
//	}
	


	
	
	
	
	
	
	
}
