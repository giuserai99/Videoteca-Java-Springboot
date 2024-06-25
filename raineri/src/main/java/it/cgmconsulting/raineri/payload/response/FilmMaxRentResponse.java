package it.cgmconsulting.raineri.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FilmMaxRentResponse {

    private long filmId;

    private String title;

    private long totNoleggi;
}
