package com.libreria.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreria.spring.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,String> {

}
