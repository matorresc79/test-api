package com.idi.backend.adpters.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idi.backend.adpters.output.entity.LibroEntity;
import com.idi.backend.core.domain.Libro;
import com.idi.backend.core.ports.LibroRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LibroRepositoryAdapter implements LibroRepositoryPort {

    @Autowired
    private LibroJpaRepository jpaRepository;

    @Override
    public Optional<Libro> findById(Long id) {
        return jpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Libro> findAll() {
        List<LibroEntity> entities = (List<LibroEntity>) jpaRepository.findAll();
        return entities.stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Libro save(Libro libro) {
        LibroEntity entity = mapToEntity(libro);
        LibroEntity savedEntity = jpaRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    // Métodos de mapeo entre Libro y LibroEntity
    private Libro mapToDomain(LibroEntity entity) {
        return new Libro(entity.getId(), entity.getNombre(), entity.getDescripcion(), entity.getCategoriaId());
    }

    private LibroEntity mapToEntity(Libro libro) {
        LibroEntity entity = new LibroEntity();
        entity.setId(libro.getId());
        entity.setNombre(libro.getNombre());
        entity.setDescripcion(libro.getDescripcion());
        entity.setCategoriaId(libro.getCategoriaId());
        return entity;
    }
}