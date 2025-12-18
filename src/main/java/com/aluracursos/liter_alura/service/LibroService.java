package com.aluracursos.liter_alura.service;

import com.aluracursos.liter_alura.model.DatosLibro;
import com.aluracursos.liter_alura.model.Libro;
import com.aluracursos.liter_alura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;
    public void guardarLibro(DatosLibro datosLibro){
        Optional<Libro> libroExistente = repository.findByTituloIgnoreCase(datosLibro.titulo());

        if (libroExistente.isPresent()){
            System.out.println("El libro ya está registrado en tu catálogo.");
        } else{
            Libro libro = new Libro(datosLibro);
            repository.save(libro);
            System.out.println("Libro guardado con éxito: " + libro.getTitulo());
        }
    }
}
