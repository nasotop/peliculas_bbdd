package com.peliculas_bbdd.peliculas_bbdd.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MovieGenre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    FILM_NOIR("Film-Noir"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSIC("Music"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCI_FI("Sci-Fi"),
    SHORT("Short"),
    SPORT("Sport"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western"),
    ROAD_MOVIE("Road Movie"),
    UNKNOWN("Desconocido");

    private final String displayName;
    
    private String rawUnknownValue = null;

    MovieGenre(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String toJson() {
        return getDisplayName();
    }

    @JsonCreator
    public static MovieGenre fromString(String value) {
        for (MovieGenre genre : values()) {
            if (genre.displayName.equalsIgnoreCase(value)) {
                return genre;
            }
        }
        MovieGenre unknown = MovieGenre.UNKNOWN;
        unknown.rawUnknownValue = value;
        return unknown;
    }

    public String getDisplayName() {
        return rawUnknownValue != null ? rawUnknownValue : displayName;
    }

    public static MovieGenre fromDisplayName(String displayName) {
        for (MovieGenre genre : MovieGenre.values()) {
            if (genre.displayName.equalsIgnoreCase(displayName)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Género no válido: " + displayName);
    }
}
