package com.peliculas_bbdd.peliculas_bbdd.repository;

import com.peliculas_bbdd.peliculas_bbdd.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
