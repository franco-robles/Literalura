package org.challengeliteralura.literalura.principal;

import org.challengeliteralura.literalura.model.*;
import org.challengeliteralura.literalura.repository.EscritorRepository;
import org.challengeliteralura.literalura.repository.LibroRepository;
import org.challengeliteralura.literalura.service.ConsumoAPI;
import org.challengeliteralura.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> datosSerie = new ArrayList<>();
    private LibroRepository repositorio;
    private EscritorRepository escritorRepository;


    public Principal(LibroRepository repository, EscritorRepository escritorRepo) {
        this.repositorio = repository;
        this.escritorRepository = escritorRepo;
    }

    public void mostrarMenu() {
        var option = -1;
        while (option != 0) {

            var menu = """
                    ************* Franco v.01 ***************
                    *         Proyecto: LiterAlura          *
                    *         Alura Latam y Oracle          *
                    *                                       *
                    *                                       *
                    *****************************************
                    
                    1 - Buscar Libro
                    2 - Buscar libros por Autor
                    3 - Buscar libro por idiomas
                    4 - Listar libros buscados previamente
                    5 - Ver autores guardados
                    6 - Ver autores vivos en el año especifico
                    """;
            System.out.println(menu);
            option = teclado.nextInt();
            teclado.nextLine();
            switch (option) {
                case 1:
                    //en la API
                    buscarlibro();
                    break;
                case 2:
                    //en la DB de postgres, no en la API
                    buscarLibroPorAutor();
                    break;
                case 3:
                    //pero en la DB de postgres
                    listarlibrosEnUnIdioma();
                    break;
                case 4:
                    //en la DB de postgres
                    listarLibrosBuscados();
                    break;
                case 5:
                    // en la DB de postgres
                    verAutoresGuardados();
                    break;
                case 6:
                    //en la DB de postgres
                    verAutoresVivosEnElYear();
                    break;
                case 0:
                    System.out.println("Gracias por usar nuestros servicios para buscar tus libros :) ");
                    break;
                default:
                    System.out.println("la opcion " + option + " es incorrecta, vuelva a intentar");
                    break;
            }

        }

    }


    /**
     * con esta funcion traemos los datos en formato json y usando el conversor pasamos
     * esos datos a DatosLibros y lo retornamos
     *
     * @return DatosLibros
     */
    private JsonResponse getDatosLibro() {
        System.out.println("Escribe el nombre del Libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        JsonResponse dLibros = conversor.obtenerDatos(json, JsonResponse.class);
        System.out.println(dLibros);
        return dLibros;
    }

    //opcion 1
    private void buscarlibro() {
        JsonResponse  dato = getDatosLibro();
        Libro libro = new Libro(dato.results().getFirst());

        // Primero guardo cada autor individualmente, en el caso en el que no este en la DB
        List<Escritor> escritores = new ArrayList<>();
        for (DatosEscritor autor : dato.results().getFirst().autores()) {
            Escritor writer = new Escritor(autor);
            if(!escritorRepository.existsByNombre(writer.getNombre())){
                escritorRepository.save(writer);
            } else{
                System.out.println("El escritor "+writer.getNombre()+" ya esta en tu DB, no se volera a agregar");
            }
            escritores.add(writer);
        }

        // Asociar los autores guardados al libro, si el autor ya esta en la base de datos tengo que usar ese y no cargar el nuevo
        // objeto que cree, sino traer el escritor desde la DB y asignarselo al libro
        List<Escritor> escritoresFinal = new ArrayList<>();
         escritores.forEach(e ->
         {
             if (escritorRepository.existsByNombre(e.getNombre())){
                 escritoresFinal.add(escritorRepository.findByNombre(e.getNombre()));
             }else{
                 escritoresFinal.add(e);
             }
         });

        libro.setAutores(escritoresFinal);
        //guarda el libro en la base de datos y al estar en cascade los asocia automaticamente
        //en caso de que el libro ya este guardado no hace nada, solo devuelve un msj
        if(!repositorio.existsByTitulo(libro.getTitulo())) {
            repositorio.save(libro);
        }else{
            System.out.println("El libro " + libro.getTitulo() + " ya esta en tu DB y no se volvera a agregar");
        }

    }

    //opcion 2
    private void buscarLibroPorAutor() {
        System.out.println("Escribe el nombre del autor que quieres buscar: ");
        var nombreEscritor = teclado.nextLine();
        Escritor salida = escritorRepository.findByNombreIgnoreCase(nombreEscritor);
        System.out.println(salida);
        salida.getLibros().forEach(System.out::println);
    }

    //opcion 4
    private void listarLibrosBuscados() {
        List<Libro> libros = repositorio.findAll();
        libros.forEach(l -> System.out.println("Libro: " +l.getTitulo()+ "\n    Descripción: "+l.getDescripcion() ));
    }

    //opcion 5
    private void verAutoresGuardados() {
        List<Escritor> escritoresEnDB = escritorRepository.findAll();
        escritoresEnDB.forEach(e -> System.out.println("Nombre: "+e.getNombre()));

    }

    //opcion 6

    /**
     * en el resultado estan los escritores que estan vivos en el año pedido
     */
    private void verAutoresVivosEnElYear() {
        System.out.println("Ingrese el año inicial: ");
        var year = teclado.nextInt();

        List<Escritor> escritoresVivos =  escritorRepository.findEscritorVivoEnElYear(year);

        if(escritoresVivos.isEmpty()){
            System.out.println("No se encontreo ningun escritor vivo en el año: (" + year + ")" );
        }else{
            escritoresVivos.stream().sorted(Comparator.comparing(Escritor::getNombre)).forEach(System.out::println);
        }
    }

    //Opcion 3
    private void listarlibrosEnUnIdioma() {
        var opcion = -1;
        List<Libro> librosEnElIdiomaPedido = new ArrayList<>();
        System.out.println("""
                Seleccione una opcion y se listaran os libros en el idioma seleccionado:
                1. Español
                2. Ingles
                """);

        opcion = teclado.nextInt();
        String idioma = "es";
        switch (opcion){
            case 1:
                librosEnElIdiomaPedido = repositorio.findByIdiomas("es");
                idioma = "español";
                break;
            case 2:
                 librosEnElIdiomaPedido = repositorio.findByIdiomas("en");
                idioma = "ingles";
                break;
            default:
                System.out.println("Opcion incorrecta, volver a intentar");
                break;

        }
        System.out.println(
                 "Tenes un total de " + librosEnElIdiomaPedido.size() + " libros \n"+
                 "en  "+ idioma +" en la base de datos, y son los siguiente: \n"

                );
        librosEnElIdiomaPedido.forEach(System.out::println);

    }





}
