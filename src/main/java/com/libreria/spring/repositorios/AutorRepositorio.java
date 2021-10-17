package com.libreria.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreria.spring.entidades.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor,String> {
	@Query("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
	public List<Autor> buscarAutorNombre(@Param("nombre") String nombre);
}
