package com.idi.backend.adpters.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idi.backend.adpters.output.response.LibroResponse;
import com.idi.backend.adpters.output.response.LibroResponseRest;
import com.idi.backend.core.domain.Libro;
import com.idi.backend.core.ports.LibroServicePort;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/v1/libros")
public class LibroRestController {

    private final LibroServicePort libroService;

    @Autowired
    public LibroRestController(LibroServicePort libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<LibroResponseRest> obtenerLibros() {
        List<Libro> libros = libroService.listarLibros();
        return new ResponseEntity<>(buildResponse(libros, "00", "Libros encontrados"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponseRest> obtenerLibroPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerLibroPorId(id);
        return new ResponseEntity<>(buildResponse(Collections.singletonList(libro), "00", "Libro encontrado"), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LibroResponseRest> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.crearLibro(libro);
        return new ResponseEntity<>(buildResponse(Collections.singletonList(nuevoLibro), "00", "Libro creado"), HttpStatus.CREATED);
    }

    // Método para construir la estructura de respuesta
    private LibroResponseRest buildResponse(List<Libro> libros, String codigo, String mensaje) {
        LibroResponseRest responseRest = new LibroResponseRest();
        LibroResponse response = new LibroResponse();

        // Configurar datos
        response.setLibro(libros);
        responseRest.setLibroResponse(response);

        // Configurar metadatos
        responseRest.setMetadata("Respuesta ok", codigo, mensaje);

        return responseRest;
    }
}