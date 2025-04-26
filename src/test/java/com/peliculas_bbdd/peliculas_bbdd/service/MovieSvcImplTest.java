package com.peliculas_bbdd.peliculas_bbdd.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.peliculas_bbdd.peliculas_bbdd.interfaces.IMovieSvc;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieSvcImplTest {
    @Autowired
    private IMovieSvc _movieSvcImpl;

    @Test
    public void getAllMoviesTest(){
        var result = _movieSvcImpl.getAllMovies();

        assertEquals(result.isEmpty(),false);
    }
}
