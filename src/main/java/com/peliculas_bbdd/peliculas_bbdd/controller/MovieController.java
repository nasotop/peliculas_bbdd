package com.peliculas_bbdd.peliculas_bbdd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas_bbdd.peliculas_bbdd.datatransferobject.GenericResponseDto;
import com.peliculas_bbdd.peliculas_bbdd.datatransferobject.GenericSingleResponseDto;
import com.peliculas_bbdd.peliculas_bbdd.datatransferobject.MovieDto;
import com.peliculas_bbdd.peliculas_bbdd.mapper.GenericResponseMapper;
import com.peliculas_bbdd.peliculas_bbdd.model.Movie;
import com.peliculas_bbdd.peliculas_bbdd.service.MovieSvcImpl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controlador con CRUD de peliculas
 */
@RestController
@RequestMapping("api/movie")
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    private MovieSvcImpl _movieSvcImpl;

    /**
     * Endpoint para consultar todas las peliculas
     * 
     * @return devuelve DTO generico con la lista de las peliculas disponibles
     */
    @GetMapping
    public GenericResponseDto<MovieDto> getAllMovies() {
        GenericResponseDto<MovieDto> result = new GenericResponseDto<>();
        try {
            var contents = _movieSvcImpl.getAllMovies();

            for (MovieDto content : contents) {
                var linkGet = linkTo(methodOn(MovieController.class).getMovie(content.getId())).withRel("Get");

                var linkUpdate = linkTo(methodOn(MovieController.class).updateMovie(content.getId(), null))
                        .withRel("Update");

                var linkDelete = linkTo(methodOn(MovieController.class).deleteMovie(content.getId())).withRel("Delete");

                content.add(linkGet);

                content.add(linkUpdate);

                content.add(linkDelete);
            }

            result = GenericResponseMapper.ToGenericResponseDto(contents);
        } catch (Exception e) {
            result.loadError(e.getMessage());
        }
        return result;
    }

    /**
     * Endpoint para consultar una pelicula por id
     * 
     * @param id id de la pelicula
     * @return devuelve DTO generico con DTO de pelicula
     */
    @GetMapping("/{id}")
    public GenericSingleResponseDto<MovieDto> getMovie(@PathVariable Long id) {

        GenericSingleResponseDto<MovieDto> result = new GenericSingleResponseDto<>();
        try {
            var content = _movieSvcImpl.getMovieById(id);

            var linkUpdate = linkTo(methodOn(MovieController.class).updateMovie(id, null)).withRel("Update");

            var linkDelete = linkTo(methodOn(MovieController.class).deleteMovie(id)).withRel("Delete");

            content.add(linkUpdate);

            content.add(linkDelete);
            result = GenericResponseMapper.ToGenericSingleResponseDto(content);
        } catch (Exception e) {
            result.loadError(e.getMessage());
        }
        return result;
    }

    /**
     * Endpoint para crear una pelicula
     * 
     * @param entity DTO de pelicula a crear
     * @return devuelve DTO generico con DTO de pelicula para crear
     */
    @PostMapping("/create")
    public GenericSingleResponseDto<MovieDto> createMovie(@RequestBody MovieDto entity) {
        GenericSingleResponseDto<MovieDto> result = new GenericSingleResponseDto<>();
        try {
            var content = _movieSvcImpl.createMovie(entity);

            var linkGet = linkTo(methodOn(MovieController.class).getMovie(content.getId())).withRel("Get");

            var linkUpdate = linkTo(methodOn(MovieController.class).updateMovie(content.getId(), null))
                    .withRel("Update");

            var linkDelete = linkTo(methodOn(MovieController.class).deleteMovie(content.getId())).withRel("Delete");

            content.add(linkGet);

            content.add(linkUpdate);

            content.add(linkDelete);

            result = GenericResponseMapper.ToGenericSingleResponseDto(content);

        } catch (Exception e) {
            result.loadError(e.getMessage());
        }
        return result;
    }

    /**
     * Endpoint para actualizar pelicula
     * 
     * @param id     id de la pelicula
     * @param entity DTO de la pelicula modificada
     * @return Devuelve DTO generico con DTO de pelicula actualizada
     */
    @PostMapping("/update/{id}")
    public GenericSingleResponseDto<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto entity) {
        GenericSingleResponseDto<MovieDto> result = new GenericSingleResponseDto<>();
        try {
            var content = _movieSvcImpl.updateMovie(id, entity);

            var linkGet = linkTo(methodOn(MovieController.class).getMovie(id)).withRel("Get");

            var linkDelete = linkTo(methodOn(MovieController.class).deleteMovie(id)).withRel("Update");

            content.add(linkDelete);

            content.add(linkGet);

            result = GenericResponseMapper.ToGenericSingleResponseDto(content);

        } catch (Exception e) {
            result.loadError(e.getMessage());
        }
        return result;
    }

    /**
     * Endpoint para borrar una pelicula por id
     * 
     * @param id id de la pelicula
     * @return Devuelve DTO generico con mensaje positivo o negativo de eliminacion
     */
    @PutMapping("delete/{id}")
    public GenericSingleResponseDto<String> deleteMovie(@PathVariable Long id) {
        GenericSingleResponseDto<String> result = new GenericSingleResponseDto<>();
        try {
            _movieSvcImpl.deleteMovie(id);

            result.setContent("Pelicula eliminada de forma correcta");
        } catch (Exception e) {
            result.loadError(e.getMessage());

        }
        return result;
    }
}
