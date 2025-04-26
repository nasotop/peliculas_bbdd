package com.peliculas_bbdd.peliculas_bbdd.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.peliculas_bbdd.peliculas_bbdd.model.Movie;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository _movieRepository;

    @Test
    public void getMovieByIdTest() {
        Long id = (long) 2;

        var result = _movieRepository.findById(id);

        assertEquals(result.isPresent(), true);

    }

    @Test
    public void createMovieTest() {
        var movie = Movie.builder()
                .director("Laura Aguirre")
                .genre("ROAD_MOVIE")
                .title("Carreteras del Silencio")
                .year(2014)
                .sinopsis(
                        "Dos hermanos distanciados emprenden un viaje en auto para cumplir el último deseo de su madre.")
                .build();

        var createdMovie =_movieRepository.save(movie);

        assertEquals(movie.getDirector(),createdMovie.getDirector());
        assertEquals(movie.getYear(),createdMovie.getYear());
        assertEquals(movie.getTitle(),createdMovie.getTitle());
        assertEquals(movie.getSinopsis(),createdMovie.getSinopsis());
        assertEquals(movie.getGenre(),createdMovie.getGenre());

    }
}
