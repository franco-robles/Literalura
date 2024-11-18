package org.challengeliteralura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonResponse(
        @JsonAlias("results") List<DatosLibros> results
) {}
/**
 * {
 *      "count": 5,
 *      "next": null,
 *      "previous": null,
 *      "results": [{},{},{},{},{}]
 * }
 */