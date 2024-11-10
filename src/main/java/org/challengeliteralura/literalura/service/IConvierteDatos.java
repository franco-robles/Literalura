package org.challengeliteralura.literalura.service;

import org.challengeliteralura.literalura.model.DatosLibros;
import org.challengeliteralura.literalura.model.Libro;

import java.util.List;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
    List<DatosLibros> obtenerDatosLibros(String json, Class<DatosLibros> datosLibrosClass);
}
