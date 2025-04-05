package com.peliculas_bbdd.peliculas_bbdd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas_bbdd.peliculas_bbdd.Enum.MovieGenre;
import com.peliculas_bbdd.peliculas_bbdd.datatransferobject.MovieDto;
import com.peliculas_bbdd.peliculas_bbdd.interfaces.IMovieSvc;
import com.peliculas_bbdd.peliculas_bbdd.mapper.MovieMapper;
import com.peliculas_bbdd.peliculas_bbdd.repository.MovieRepository;

@Service
public class MovieSvcImpl implements IMovieSvc {
    @Autowired
    private MovieRepository _movieRepository;

    @Override
    public List<MovieDto> getAllMovies() {
        return MovieMapper.toDtos(_movieRepository.findAll());
    }

    @Override
    public MovieDto getMovieById(Long id) throws Exception {
        var entity = _movieRepository.findById(id);
        if (!entity.isPresent())
            throw new Exception("No se encontró pelicula con el id: " + id);
        return entity
                .map(MovieMapper::toDto).get();
    }

    @Override
    public MovieDto createMovie(MovieDto dto) throws Exception {
        if (dto.getGenre() == MovieGenre.UNKNOWN)
            throw new Exception("No se reconoce el genero: " + dto.getGenre().getDisplayName());

        var entity = MovieMapper.toEntity(dto);
        return MovieMapper.toDto(_movieRepository.save(entity));
    }

    @Override
    public MovieDto updateMovie(Long id, MovieDto dto) throws Exception {
        if (!_movieRepository.existsById(id))
            throw new Exception("No se encuentra pelicula asociada al id " + id);

        if (dto.getGenre() == MovieGenre.UNKNOWN)
            throw new Exception("No se reconoce el genero: " + dto.getGenre().getDisplayName());

        var entity = MovieMapper.toEntity(dto);
        entity.setId(id);
        return MovieMapper.toDto(_movieRepository.save(entity));

    }

    @Override
    public void deleteMovie(Long id) throws Exception {
        if (!_movieRepository.existsById(id))
            throw new Exception("No se encuentra pelicula asociada al id " + id);

        _movieRepository.deleteById(id);

    }

}
