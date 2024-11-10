package org.challengeliteralura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEscritor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String birth_year,
        @JsonAlias("death_year") String death_year ){

}
