package org.challengeliteralura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") ArrayList<String> descripcion,
        @JsonAlias("bookshelves") ArrayList<String> generos,
        @JsonAlias("languages") ArrayList<String> idiomas,
        @JsonAlias("copyright") Boolean derechosDeAutor,
        @JsonAlias("download_count") String CantidadDeDescargas,
        @JsonAlias("authors") List<DatosEscritor> autores)
        {
}
 /**

  "id": <number of Project Gutenberg ID>,
  "title": <string>,
  "subjects": <array of strings>,
  "authors": <array of Persons>,
  "translators": <array of Persons>,
  "bookshelves": <array of strings>,
  "languages": <array of strings>,
  "copyright": <boolean or null>,
  "media_type": <string>,
  "formats": <Format>,
  "download_count": <number>

  **/