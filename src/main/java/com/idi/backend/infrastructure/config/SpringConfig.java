package com.idi.backend.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.idi.backend.adpters.output.LibroRepositoryAdapter;
import com.idi.backend.core.ports.LibroServicePort;
import com.idi.backend.core.service.LibroServiceImpl;

@Configuration
public class SpringConfig {

    private final LibroRepositoryAdapter repositoryAdapter;

    public SpringConfig(LibroRepositoryAdapter repositoryAdapter) {
        this.repositoryAdapter = repositoryAdapter;
    }

    @Bean
    public LibroServicePort libroServicePort() {
        return new LibroServiceImpl(repositoryAdapter);
    }
}
