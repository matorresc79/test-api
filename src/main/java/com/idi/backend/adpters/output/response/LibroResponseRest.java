package com.idi.backend.adpters.output.response;

public class LibroResponseRest extends ResponseRest {
    
    private LibroResponse libroResponse = new LibroResponse();

    public LibroResponse getLibroResponse() {
        return libroResponse;
    }

    public void setLibroResponse(LibroResponse libroResponse) {
        this.libroResponse = libroResponse;
    }
}
