package com.libreria.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.spring.entidades.Libro;
import com.libreria.spring.errores.ErrorServicio;
import com.libreria.spring.servicios.LibroServicio;

@Controller
@RequestMapping("/Libro") // localhost:8080/Libro
public class LibroControlador {

	@Autowired
	private LibroServicio libroServicio;
	
	@GetMapping("/crearLibro")
	public String crearLibro() {
		return "crearLibro";
	}
	
	@PostMapping("/crearLibro")
	public String guardarLibro(@RequestParam Integer isbn,@RequestParam String titulo,@RequestParam Integer anio,
			@RequestParam Integer ejemplares, @RequestParam Integer ejemplaresprestados
			, @RequestParam String autor,@RequestParam String editorial) throws Exception {
	try	{
		libroServicio.crear(isbn, titulo, anio, ejemplares, ejemplaresprestados, autor, editorial);
	} catch (ErrorServicio e) {
		e.printStackTrace();
	}		
		return "crearLibro";
	}
	
	
	
	@GetMapping("/mostrarLibros")
	public String mostrarLibros(ModelMap modelo) throws ErrorServicio {
		
		try {
		List<Libro> libros;
		libros = libroServicio.buscarTodos();
		modelo.addAttribute("libros", libros);
		} catch (ErrorServicio e) {
			e.printStackTrace();
		}
		return "mostrarLibros";
	}
	
}




























