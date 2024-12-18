package com.idi.backend.adpters.output.response;

import java.util.List;

import com.idi.backend.core.domain.Libro;

public class LibroResponse {

    private List<Libro> libro;

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }
}
