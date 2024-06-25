package it.cgmconsulting.raineri.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FilmRentResponse {

    private long filmId;

    private String title;

    private String storeName;

}
