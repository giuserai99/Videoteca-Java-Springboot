package it.cgmconsulting.raineri.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FilmRentableResponse {

    private String title;

    private String storeName;

    private long numberCopies;

    private long numberCopiesAvailable;

}
