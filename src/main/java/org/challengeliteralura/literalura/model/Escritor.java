package org.challengeliteralura.literalura.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "escritor")
public class Escritor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String birth_year;
    private String death_year;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "autores", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Libro> libros;

    //Constructors
    public Escritor(){

    }
    public Escritor(DatosEscritor d){
        this.birth_year = d.birth_year();
        this.death_year = d.death_year();
        this.nombre = d.nombre();
    }

    //methods
    public Long getId() {
        return id;

    }
    public List<Libro> getLibros() {
        return libros;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Escritor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", birth_year=" + birth_year +
                ", death_year=" + death_year +
                ", libros=" + libros +
                '}';
    }
}
