package org.challengeliteralura.literalura.repository;

import org.challengeliteralura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {


    boolean existsByTitulo(String titulo);


    List<Libro> findByIdiomas(String idioma);
}
