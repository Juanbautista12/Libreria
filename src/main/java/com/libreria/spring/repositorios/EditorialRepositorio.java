package com.libreria.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreria.spring.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial,String> {
	@Query("SELECT a FROM editorial a WHERE a.nombre LIKE :nombre")
	public List<Editorial> buscarEditorialNombre(@Param("nombre") String nombre);
}
