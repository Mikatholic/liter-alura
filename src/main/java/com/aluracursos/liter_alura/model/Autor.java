package com.aluracursos.liter_alura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer getFechaDeFallecimiento;

    @OneToMany(mappedBy = "autor, cascade = CascadeType.ALL, fetch = FetchType.EAGER")
    private List<Libro> libros;

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getGetFechaDeFallecimiento() {
        return getFechaDeFallecimiento;
    }

    public void setGetFechaDeFallecimiento(Integer getFechaDeFallecimiento) {
        this.getFechaDeFallecimiento = getFechaDeFallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
