package org.challengeliteralura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEscritor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer birth_year,
        @JsonAlias("death_year") Integer death_year ){

}
