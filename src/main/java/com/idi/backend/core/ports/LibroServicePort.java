package com.idi.backend.core.ports;

import java.util.List;

import com.idi.backend.core.domain.Libro;

public interface LibroServicePort {
    Libro obtenerLibroPorId(Long id);
    List<Libro> listarLibros();
    Libro crearLibro(Libro libro);
    Libro actualizarLibro(Long id, Libro libro);
    void eliminarLibro(Long id);
}
