package it.cgmconsulting.raineri.payload.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class FilmRequest {

    @NotBlank @Size(min = 1, max = 100)
    private String title;

    @NotBlank @Size(min = 1, max = 255)
    private String description;

    @Min(1)
    private short releaseYear;

    @NotBlank @Size(min = 1, max = 20)
    private String language;

    @NotBlank @Size(min = 1, max = 30)
    private String genre;
}
