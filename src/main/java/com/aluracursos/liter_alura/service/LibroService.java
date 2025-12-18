package com.aluracursos.liter_alura.service;

import com.aluracursos.liter_alura.model.Autor;
import com.aluracursos.liter_alura.model.DatosLibro;
import com.aluracursos.liter_alura.model.Libro;
import com.aluracursos.liter_alura.repository.AutorRepository;
import com.aluracursos.liter_alura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    public void guardarLibro(DatosLibro datosLibro){
        Optional<Libro> libroExistente = repository.findByTituloIgnoreCase(datosLibro.titulo());

        if (libroExistente.isPresent()){
            System.out.println("El libro ya está registrado en tu catálogo.");
        } else{
            Libro libro = new Libro(datosLibro);
            repository.save(libro);
            System.out.println(libro);
            System.out.println("Libro guardado con éxito.");
        }
    }

    public List<Libro> listarLibrosRegistrados() {
        return repository.findAll();
    }

    public List<Autor> listarAutoresRegistrados() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEnAnio(Integer anio) {
        return autorRepository.autorVivosEnDeterminadoAnio(anio);
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return repository.findByIdioma(idioma);
    }
}
