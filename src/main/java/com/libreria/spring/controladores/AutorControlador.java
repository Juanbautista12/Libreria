package com.libreria.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.spring.entidades.Autor;
import com.libreria.spring.errores.ErrorServicio;
import com.libreria.spring.servicios.AutorServicio;


@Controller
@RequestMapping("/Autor") // localhost:8080/Autor
public class AutorControlador {

	@Autowired
	private AutorServicio autorServicio;
	
	@GetMapping("/crearAutor") // localhost:8080/Autor/crearAutor
	public String crearAutor() {
		return "crearAutor";
	}
	
	@PostMapping("/crearAutor")
	public String guardarAutor(@RequestParam String nombre) {
		try {
			autorServicio.crear(nombre);
		} catch(ErrorServicio e){
			e.printStackTrace();
		}
		return "crearAutor";
	}
	
	@GetMapping("/mostrarAutores")
	public String mostrarAutores(ModelMap modelo) throws ErrorServicio {
		
		try {
		List<Autor> autores;	
		autores = autorServicio.buscarTodos();
		modelo.addAttribute("autores", autores);
		} catch(ErrorServicio e){
			e.printStackTrace();
			System.out.println("Error en mostrar la lista");
		}	
	return "mostrarAutores";
	}
	
	
	
	
	
	
	
}
