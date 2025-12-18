package com.aluracursos.liter_alura.repository;

import com.aluracursos.liter_alura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND a.fechaDeFallecimiento >= :anio")
    List<Autor> autorVivosEnDeterminadoAnio(Integer anio);
}
