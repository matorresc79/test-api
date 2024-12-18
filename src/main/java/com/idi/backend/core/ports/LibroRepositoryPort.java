package com.idi.backend.core.ports;




import java.util.List;
import java.util.Optional;

import com.idi.backend.core.domain.Libro;

public interface LibroRepositoryPort {
    Optional<Libro> findById(Long id);
    List<Libro> findAll();
    Libro save(Libro libro);
    void deleteById(Long id);
}

