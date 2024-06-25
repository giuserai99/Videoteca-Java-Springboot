package it.cgmconsulting.raineri.service;

import it.cgmconsulting.raineri.entity.Film;
import it.cgmconsulting.raineri.entity.Genre;
import it.cgmconsulting.raineri.entity.Language;
import it.cgmconsulting.raineri.exception.ResourceNotFoundException;
import it.cgmconsulting.raineri.payload.request.FilmRequest;
import it.cgmconsulting.raineri.repository.FilmRepository;
import it.cgmconsulting.raineri.repository.GenreRepository;
import it.cgmconsulting.raineri.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;

    //Punto 1.
    public ResponseEntity<?> updateFilm(long filmId, FilmRequest request){

        Film film = filmRepository.findByFilmId(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "filmId", filmId));

        Language language = languageRepository.findByLanguageName(request.getLanguage())
                .orElseThrow(() -> new ResourceNotFoundException("Language", "languageName", request.getLanguage()));

        Genre genre = genreRepository.findByGenreName(request.getGenre())
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "genreName", request.getGenre()));


        film.setTitle(request.getTitle());
        film.setDescription(request.getDescription());
        film.setReleaseYear(request.getReleaseYear());
        film.setLanguageId(language);
        film.setGenreId(genre);

        return ResponseEntity.status(200).body("film updated");
    }

    //Punto 2.
    public ResponseEntity<?> getFilmsByLanguage(long languageId){
        if(filmRepository.getFilmsByLanguage(languageId).isEmpty())
            throw new ResourceNotFoundException("Language", "languageId", languageId);

        return ResponseEntity.status(200).body(filmRepository.getFilmsByLanguage(languageId));
    }


}
