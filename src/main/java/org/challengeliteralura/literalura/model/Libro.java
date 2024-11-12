package org.challengeliteralura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String descripcion;
    private String generos;
    private String idiomas;
    private String derechosDeAutor;
    private String CantidadDeDescargas;
    @ManyToMany
    private List<Escritor> autores;
    //Constructors
    public Libro(){}

    public Libro(DatosLibros datos){
        this.titulo = datos.titulo();
        this.descripcion = datos.descripcion().getFirst();
        this.generos = datos.generos().getFirst();
        this.derechosDeAutor = datos.derechosDeAutor();
        this.CantidadDeDescargas = datos.CantidadDeDescargas();
        this.autores = new ArrayList<>();
        autores.addLast(new Escritor(datos.autores().getFirst())) ;
    }

    //getters and setters


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getDerechosDeAutor() {
        return derechosDeAutor;
    }

    public void setDerechosDeAutor(String derechosDeAutor) {
        this.derechosDeAutor = derechosDeAutor;
    }

    public String getCantidadDeDescargas() {
        return CantidadDeDescargas;
    }

    public void setCantidadDeDescargas(String cantidadDeDescargas) {
        CantidadDeDescargas = cantidadDeDescargas;
    }

    public List<Escritor> getAutores() {
        return autores;
    }

    public void setAutores(List<Escritor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", generos='" + generos + '\'' +
                ", idiomas='" + idiomas + '\'' +
                ", derechosDeAutor='" + derechosDeAutor + '\'' +
                ", CantidadDeDescargas='" + CantidadDeDescargas + '\'' +
                ", autores=" + autores +
                '}';
    }
}
