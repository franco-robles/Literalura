package org.challengeliteralura.literalura.repository;

import org.challengeliteralura.literalura.model.Escritor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EscritorRepository extends JpaRepository<Escritor, Long> {

    boolean existsByNombre(String writer);

    @EntityGraph(attributePaths = {"libros"})
    @Query("SELECT e FROM Escritor e JOIN FETCH e.libros l WHERE e.nombre = :nombre")
    Escritor findByNombreIgnoreCase(@Param("nombre") String nombreEscritor);

    Escritor findByNombre(String nombreEscritor);

    /**
     * en el resultado estan los escritoes que nacieron antes de year y muerieron despue de year
     * por ende estan vivos en el año seleccionado
     * @return
     */
    @Query("SELECT e FROM Escritor e WHERE e.birth_year < :year AND e.death_year > :year")
    List<Escritor> findEscritorVivoEnElYear(Integer year);

}
