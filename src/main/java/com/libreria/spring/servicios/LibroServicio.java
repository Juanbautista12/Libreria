package com.libreria.spring.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.spring.entidades.*;
import com.libreria.spring.errores.ErrorServicio;
import com.libreria.spring.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

	@Autowired
	private LibroRepositorio libroRepositorio;
	@Autowired
	private AutorServicio autorServicio;
	@Autowired
	private EditorialServicio editorialServicio;
	
	@Transactional
	public void crear(Integer isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			String autor, String editorial) throws Exception {	
		
		if(isbn == null){
			throw new ErrorServicio("No puede ser nulo el isbn");
		}
		//String idAutor = autorServicio.buscarPorNombre(autor).getId();
		Autor autor2 = autorServicio.buscarPorNombre(autor);
		Editorial editorial2 = editorialServicio.buscarPorNombre(editorial);
		
		Libro libro = new Libro();
		libro.setIsbn(isbn);
		libro.setTitulo(titulo);
		libro.setAnio(anio);
		libro.setEjemplares(ejemplares);
		libro.setEjemplaresPrestados(ejemplaresPrestados);
		libro.setEjemplaresRestantes(ejemplares-ejemplaresPrestados);
		libro.setAlta(true);
		libro.setAutor(autor2);
		libro.setEditorial(editorial2);
				
		libroRepositorio.save(libro);
	}
	
	
	@Transactional
	public void modificarTitulo(String id, String titulo) {
		Libro libro = libroRepositorio.findById(id).get();
		libro.setTitulo(titulo);
		libroRepositorio.save(libro);
	}
	
	@Transactional
	public void deshabilitar(String id) throws Exception {
		Optional<Libro> respuesta = libroRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Libro libro = respuesta.get();
			libro.setAlta(false);
			libroRepositorio.save(libro);
		} else {
			throw new ErrorServicio("No se pudo encontrar ese libro");
		}
	}
	
	
	
	// Buscar todos los libros
	@Transactional(readOnly=true)
	public List<Libro> buscarTodos() throws ErrorServicio{
		return libroRepositorio.findAll();
	}
	
}
