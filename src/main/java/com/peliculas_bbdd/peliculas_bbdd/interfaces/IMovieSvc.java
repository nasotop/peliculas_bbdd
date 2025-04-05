package com.peliculas_bbdd.peliculas_bbdd.interfaces;

import java.util.List;
import java.util.Optional;

import com.peliculas_bbdd.peliculas_bbdd.datatransferobject.MovieDto;
import com.peliculas_bbdd.peliculas_bbdd.model.Movie;

public interface IMovieSvc {
    /**
     * Metodo para consultar todas las peliculas
     * 
     * @return Devuelve lista de DTO de peliculas
     */
    List<MovieDto> getAllMovies();

    /**
     * Metodo para consultar una pelicula por id
     * 
     * @param id id de la pelicula
     * @return devuelte DTO de pelicula en caso de que exista
     */
    MovieDto getMovieById(Long id) throws Exception;

    /**
     * Metodo para crear pelicula en BBDD
     * 
     * @param dto DTO de la pelicula a crear
     * @return devuelve DTO de pelicula creada
     */

    MovieDto createMovie(MovieDto dto) throws Exception;

    /**
     * Metodo para actualizar pelicula
     * 
     * @param id  id de pelicula por actualizar
     * @param dto DTO de pelicula con modificaciones
     * @return devuelve DTO de pelicula modificada
     */
    MovieDto updateMovie(Long id, MovieDto dto) throws Exception;

    /**
     * Metodo para eliminar una pelicula
     * 
     * @param id id de la pelicula a eliminar
     */
    void deleteMovie(Long id) throws Exception;
}
