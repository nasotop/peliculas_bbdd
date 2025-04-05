package com.peliculas_bbdd.peliculas_bbdd.datatransferobject;

import com.peliculas_bbdd.peliculas_bbdd.Enum.MovieGenre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {

    private Long id;
    private String title;
    private int year;
    private MovieGenre genre;
    private String director;
    private String sinopsis;
}
