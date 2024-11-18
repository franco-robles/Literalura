package org.challengeliteralura.literalura.model;

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
    private Boolean derechosDeAutor;
    private String CantidadDeDescargas;
    @ManyToMany
    @JoinTable( name = "libro_escritor", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "escritor_id") )
    private List<Escritor> autores;


    //Constructors
    public Libro(){}

    public Libro(DatosLibros datos){
        this.titulo = datos.titulo();
        this.descripcion = datos.descripcion().getFirst();
        this.generos = datos.generos().getFirst();
        this.idiomas = datos.idiomas().getFirst();
        this.derechosDeAutor = datos.derechosDeAutor();
        this.CantidadDeDescargas = datos.CantidadDeDescargas();
        this.autores = new ArrayList<>();
        Escritor escritor = new Escritor(datos.autores().getFirst()) ;
        List<Libro> libros = new ArrayList<>();
        libros.add(this);
        escritor.setLibros(libros);
        autores.addLast(escritor) ;
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

    public Boolean getDerechosDeAutor() {
        return derechosDeAutor;
    }

    public void setDerechosDeAutor(Boolean derechosDeAutor) {
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
                ", derechosDeAutor=" + derechosDeAutor +
                ", CantidadDeDescargas='" + CantidadDeDescargas + '\'' +
                ", autores="+
                '}';
    }
}
