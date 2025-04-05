package com.peliculas_bbdd.peliculas_bbdd.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.peliculas_bbdd.peliculas_bbdd.Enum.MovieGenre;
import com.peliculas_bbdd.peliculas_bbdd.datatransferobject.MovieDto;
import com.peliculas_bbdd.peliculas_bbdd.model.Movie;

public class MovieMapper {

    public static Movie toEntity(MovieDto dto){
        return Movie.builder()
        .id(dto.getId())
        .director(dto.getDirector())
        .genre(dto.getGenre().getDisplayName())
        .sinopsis(dto.getSinopsis())
        .title(dto.getTitle())
        .year(dto.getYear())
        .build();
    }

    public static List<Movie> toEntities(List<MovieDto> dtos){
        return dtos.stream().map(MovieMapper::toEntity).collect(Collectors.toList());
    }

    public static MovieDto toDto(Movie entity){
        return MovieDto.builder()
        .id(entity.getId())
        .director(entity.getDirector())
        .sinopsis(entity.getSinopsis())
        .year(entity.getYear())
        .title(entity.getTitle())
        .genre(MovieGenre.fromDisplayName(entity.getGenre()))
        .build();
    }

    public static List<MovieDto> toDtos(List<Movie> entities){
        return entities.stream().map(MovieMapper::toDto).collect(Collectors.toList());
    }
}
