package com.libreria.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.spring.entidades.Editorial;
import com.libreria.spring.errores.ErrorServicio;
import com.libreria.spring.servicios.EditorialServicio;

@Controller
@RequestMapping("/Editorial")  // localhost:8080/Editorial
public class EditorialControlador {
	
	@Autowired
	private EditorialServicio editorialServicio;
	
	@GetMapping("/crearEditorial") // localhost:8080/Editorial/crearEditorial
	public String crearEditorial(){
		return ("crearEditorial");
	}
	
	@PostMapping("/crearEditorial")
	public String crearEditorial(@RequestParam String nombre) throws ErrorServicio{
		try {
			editorialServicio.crear(nombre);
		} catch (ErrorServicio e) {
		e.printStackTrace();
		}
		return "crearEditorial";
	}
	
	@GetMapping("/mostrarEditoriales")
	public String mostrarEditoriales(ModelMap modelo) throws ErrorServicio{
		
		try {
		List<Editorial> editoriales;
		editoriales = editorialServicio.buscarTodos();
		modelo.addAttribute("editoriales", editoriales);
		} catch(ErrorServicio e) {
			e.printStackTrace();
			System.out.println("Error en mostrar la lista de editoriales");
		}
		return "mostrarEditoriales";
	}
	
	
	
	

}
