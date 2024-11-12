package org.challengeliteralura.literalura.principal;

import org.challengeliteralura.literalura.model.DatosEscritor;
import org.challengeliteralura.literalura.model.DatosLibros;
import org.challengeliteralura.literalura.model.JsonResponse;
import org.challengeliteralura.literalura.model.Libro;
import org.challengeliteralura.literalura.repository.LibroRepository;
import org.challengeliteralura.literalura.service.ConsumoAPI;
import org.challengeliteralura.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> datosSerie = new ArrayList<>();
    private LibroRepository repositorio;
    private List<Libro> series;
    DatosEscritor datoWriter;

    public void mostrarMenu() {
        var option = -1;
        while (option != 0) {

            var menu = """
                    1 - Buscar Libro
                    2 - Buscar libros por Autor
                    3 - Buscar libro por idiomas
                    4 - Buscar por topic
                    """;
            System.out.println(menu);
            option = teclado.nextInt();
            teclado.nextLine();
            switch (option) {
                case 1:
                    buscarlibro();
                    break;
                case 2:
                    // buscarLibroPorAutor();
                    break;
                case 3:
                    // buscarLibrosPorIdioma();
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
    //

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
       //List<DatosLibros> datos = conversor.obtenerDatosLibros(json, DatosLibros.class);
       //System.out.println(datos.toString());
       //datoWriter = conversor.obtenerDatos(json, DatosEscritor.class);
       //return datos;
        return dLibros;
    }
    private void buscarlibro() {
        JsonResponse  dato = getDatosLibro();
        Libro libro = new Libro(dato.results().getFirst());
        System.out.println("el autor es: " );
        System.out.println(libro);
    }


}
