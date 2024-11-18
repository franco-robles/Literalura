package org.challengeliteralura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "escritor")
public class Escritor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer birth_year;
    private Integer death_year;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "autores", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Libro> libros;

    //Constructors
    public Escritor(){

    }
    public Escritor(DatosEscritor d){
        this.birth_year = d.birth_year();
        this.death_year = d.death_year();
        this.nombre = d.nombre();
        // Encontrar el índice de la coma
        //int commaIndex = d.nombre().indexOf(",");
        // Extraer la subcadena desde el inicio hasta antes de la coma
        //String resultString = d.nombre().substring(0, commaIndex);
        //this.nombre = resultString;
    }

    //methods
    public Long getId() {
        return id;

    }
    public List<Libro> getLibros() {
        return libros;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getDeath_year() {return death_year;}

    public void setDeath_year(Integer death_year) {this.death_year = death_year;}

    public Integer getBirth_year() {return birth_year;}

    public void setBirth_year(Integer birth_year) {this.birth_year = birth_year;}

    public void setLibros(List<Libro> libros) {
       this.libros = libros;
   }

    @Override
    public String toString() {
        return "Escritor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", death_year='" + death_year + '\'' +
                ", libros=" +
                '}';
    }


}
