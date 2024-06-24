package it.cgmconsulting.raineri.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FilmResponse {

    private long filmId;

    private String title;

    private String description;

    private short releaseYear;

    private String languageName;


}
