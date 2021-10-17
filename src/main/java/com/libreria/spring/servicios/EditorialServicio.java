package com.libreria.spring.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.spring.repositorios.EditorialRepositorio;
import com.libreria.spring.entidades.Editorial;
import com.libreria.spring.errores.ErrorServicio;

@Service
public class EditorialServicio {

	@Autowired
	private EditorialRepositorio Editorialrepositorio;
	
	
	// Crear editorial
	@Transactional
	public void crear(String nombre) throws ErrorServicio {
		
		if(nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre de la editorial no puede ser nulo");
		}
		
		Editorial editorial = new Editorial();
		editorial.setNombre(nombre);
		editorial.setAlta(true);
		Editorialrepositorio.save(editorial);
	}
	
	// Modificar editorial
	@Transactional
	public void modificarNombre(String id, String nombre) throws Exception {
		
		Optional<Editorial> respuesta = Editorialrepositorio.findById(id);
		
		if (respuesta.isPresent()) {
			Editorial editorial = respuesta.get();
			editorial.setNombre(nombre);	
			
			Editorialrepositorio.save(editorial);
		} else {
			throw new ErrorServicio("No se pudo encontrar la editorial");
		}
	}
	
	// Deshabilitar editorial
	@Transactional
	public void deshabilitar(String id) throws Exception{
		Optional<Editorial> respuesta = Editorialrepositorio.findById(id);
		
		if(respuesta.isPresent()) {
			Editorial editorial = respuesta.get();
			editorial.setAlta(false);
			
			Editorialrepositorio.save(editorial);
		} else {
			throw new ErrorServicio("No se encontro la editorial solicitada");
		}
	}
	
	// Habilitar editorial
	@Transactional
	public void habilitar(String id) throws Exception{
		Optional<Editorial> respuesta = Editorialrepositorio.findById(id);
		
		if(respuesta.isPresent()) {
			Editorial editorial = respuesta.get();
			editorial.setAlta(true);
			
			Editorialrepositorio.save(editorial);
		} else {
			throw new ErrorServicio("No se encontro la editorial solicitada");
		}
	}
	
	// Buscar todas las editoriales
	@Transactional(readOnly=true)
	public List<Editorial> buscarTodos() throws ErrorServicio{
		return Editorialrepositorio.findAll();
	}
	
	// Buscar editorial por id
	public Editorial buscarPorId(String id) {
		Editorial editorial = Editorialrepositorio.findById(id).get();
		return editorial;
	}
	
	
//  Buscar editorial por nombre
	public Editorial buscarPorNombre(String nombre) {
		Editorial editorial = Editorialrepositorio.buscarEditorialNombre(nombre).get(0);
			return editorial;
	}
	
	
	//  Buscar editorial por nombre
	
//	public Editorial buscarPorNombre(String nombre) {
//		List<Editorial> lista = Editorialrepositorio.findAll();
//		List<Editorial> lista2 = null;
//		for (Editorial aux : lista) {
//		   if (aux.getNombre().equalsIgnoreCase(nombre)) {
//			   String IdEditorial = aux.getId();
//		   }
//		}
//		return IdEditorial;
//	}
//	
	
	
	
	
	
	
	
	
	
	
}
