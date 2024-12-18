package com.idi.backend.core.service;

import java.util.List;
import java.util.Optional;

import com.idi.backend.core.domain.Libro;
import com.idi.backend.core.ports.LibroRepositoryPort;
import com.idi.backend.core.ports.LibroServicePort;

import jakarta.transaction.Transactional;


public class LibroServiceImpl implements LibroServicePort {

    private final LibroRepositoryPort repository;

    public LibroServiceImpl(LibroRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Libro obtenerLibroPorId(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener libro por ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public List<Libro> listarLibros() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar libros", e);
        }
    }

    @Override
    @Transactional
    public Libro crearLibro(Libro libro) {
        try {
            return repository.save(libro);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear libro", e);
        }
    }

    @Override
    @Transactional
    public Libro actualizarLibro(Long id, Libro libro) {
        try {
            Optional<Libro> libroExistente = repository.findById(id);

            if (libroExistente.isEmpty()) {
                throw new RuntimeException("Libro no encontrado con ID: " + id);
            }

            Libro actualizado = libroExistente.get();
            actualizado.setNombre(libro.getNombre());
            actualizado.setDescripcion(libro.getDescripcion());
            actualizado.setCategoriaId(libro.getCategoriaId());

            return repository.save(actualizado);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar libro con ID: " + id, e);
        }
    }

    @Override
    @Transactional
    public void eliminarLibro(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar libro con ID: " + id, e);
        }
    }
}
