package com.idi.backend.infrastructure.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
    @Bean
    public UserDetailsManager userDetailsManager(DataSource datasource) {
    	return new JdbcUserDetailsManager(datasource);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	http.authorizeHttpRequests(configure ->{
    		configure
    			.requestMatchers(HttpMethod.GET,"/v1/libros").hasRole("Empleado")
    			.requestMatchers(HttpMethod.GET,"/v1/libros/**").hasRole("Jefe");
    	});
    	http.httpBasic(Customizer.withDefaults());
    	http.csrf(csrf -> csrf.disable());
    	
    	return http.build();
    }
    /*
     @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
    	UserDetails manuel = User.builder()
    			.username("manuel")
    			.password("{noop}manu123")
    			.roles("Empleado")
    			.build();
    	UserDetails alejandro = User.builder()
    			.username("alejandro")
    			.password("{noop}ale123")
    			.roles("Jefe")
    			.build();
    	return new InMemoryUserDetailsManager(manuel, alejandro);
    }
     * */
    
}
