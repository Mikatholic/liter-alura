package com.aluracursos.liter_alura.principal;

import com.aluracursos.liter_alura.model.Datos;
import com.aluracursos.liter_alura.model.DatosLibro;
import com.aluracursos.liter_alura.service.ConsumoAPI;
import com.aluracursos.liter_alura.service.ConvierteDatos;
import com.aluracursos.liter_alura.service.LibroService;

import java.util.Scanner;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroService libroService;

    public Principal(LibroService libroService) {
        this.libroService = libroService;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ***************************************************
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    ***************************************************
                    """;
            System.out.println(menu);
            try {
                opcion = lectura.nextInt();
                lectura.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroWeb();
                        break;
                    case 2:
                        libroService.listarLibrosRegistrados().forEach(System.out::println);
                        break;
                    case 3:
                        libroService.listarAutoresRegistrados().forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("Ingrese el año que desea consultar:");
                        var anio = lectura.nextInt();
                        lectura.nextLine();
                        var autoresVivos = libroService.listarAutoresVivosEnAnio(anio);
                        if(autoresVivos.isEmpty()){
                            System.out.println("No se encontraron autores vivos en ese año.");
                        } else {
                            autoresVivos.forEach(System.out::println);
                        }
                        break;
                    case 5:
                        System.out.println("""
                                Ingrese el idioma para buscar los libros:
                                es - español
                                en - inglés
                                fr - francés
                                pt - portugués
                                """);
                        var idioma = lectura.nextLine();
                        var librosPorIdioma = libroService.listarLibrosPorIdioma(idioma);
                        if(librosPorIdioma.isEmpty()){
                            System.out.println("No hay libros registrados en ese idioma.");
                        } else {
                            librosPorIdioma.forEach(System.out::println);
                        }
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                lectura.nextLine();
            }
        }
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos != null) {
            libroService.guardarLibro(datos);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var nombreLibro = lectura.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        return datosBusqueda.resultadoLibros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}