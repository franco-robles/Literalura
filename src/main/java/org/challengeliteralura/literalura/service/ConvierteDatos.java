package org.challengeliteralura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.challengeliteralura.literalura.model.DatosLibros;
import org.challengeliteralura.literalura.model.Libro;

import java.io.IOException;
import java.util.List;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper =  new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DatosLibros> obtenerDatosLibros(String json, Class<DatosLibros> datosLibrosClass) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            ArrayNode resultsNode = (ArrayNode) rootNode.path("results");

            List<DatosLibros> libros = objectMapper
                    .readerForListOf(DatosLibros.class)
                    .readValue(resultsNode.get(0));
            System.out.println("Los libros"+libros);
            return libros;

        } catch (JsonProcessingException e) {

            throw new RuntimeException("error con JsonPrecessingException", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
