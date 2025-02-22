package it.cgmconsulting.raineri.controller;

import it.cgmconsulting.raineri.payload.request.FilmRequest;
import it.cgmconsulting.raineri.service.FilmService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;;

@RestController
@RequiredArgsConstructor
@Validated
public class FilmController {

    private final FilmService filmService;


    //Punto 1.
    @Transactional
    @PutMapping("/update-film/{filmId}")
    public ResponseEntity<?> updateFilm(@PathVariable @Min(1) long filmId,
                                        @RequestBody @Valid FilmRequest request){
        return filmService.updateFilm(filmId, request);
    }


    //Punto 2.
    @GetMapping("/find-films-by-language/{languageId}")
    public ResponseEntity<?> getFilmsByLanguage(@PathVariable @Min(1) long languageId){
        return filmService.getFilmsByLanguage(languageId);
    }

    //Punto 9.
    @GetMapping("/find-films-by-actors")
    public ResponseEntity<?> getFilmsByActors(@RequestParam @NotNull List<Long> collectionStaffId){
        return filmService.getFilmsByActors(collectionStaffId);
    }

}
