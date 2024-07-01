package it.cgmconsulting.raineri.service;

import it.cgmconsulting.raineri.entity.Film;
import it.cgmconsulting.raineri.entity.Genre;
import it.cgmconsulting.raineri.entity.Language;
import it.cgmconsulting.raineri.exception.GenericException;
import it.cgmconsulting.raineri.exception.ResourceNotFoundException;
import it.cgmconsulting.raineri.payload.request.FilmRequest;
import it.cgmconsulting.raineri.payload.response.FilmResponse;
import it.cgmconsulting.raineri.repository.FilmRepository;
import it.cgmconsulting.raineri.repository.FilmStaffRepository;
import it.cgmconsulting.raineri.repository.GenreRepository;
import it.cgmconsulting.raineri.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;
    private final FilmStaffRepository filmStaffRepository;

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


    //Punto 9.
    public ResponseEntity<?> getFilmsByActors(List<Long> collectionStaffId){

        List<FilmResponse> listFilm = new ArrayList<>();
        List<Set<Long>> listFilmId = new ArrayList<>();

        for(Long staffId : collectionStaffId){
            List<Long> listFilmIds = filmStaffRepository.getFilmIdByStaff(staffId);
            if(!listFilmIds.isEmpty()) {
                listFilmId.add(new HashSet<>(listFilmIds));
            } else {
                return ResponseEntity.badRequest().body("No film found with this staffId");
            }
        }

        Set<Long> commonFilm = listFilmId.get(0);
        for(int i = 1; i < listFilmId.size(); i++){
            commonFilm.retainAll(listFilmId.get(i));
        }

        for (Long filmId : commonFilm) {
            FilmResponse film = filmRepository.getFilms(filmId);
            if(film == null){
                throw new ResourceNotFoundException("Film", "filmId", filmId);
            }else {
                listFilm.add(film);
            }
        }

        if(listFilm.isEmpty())
            return ResponseEntity.status(204).body("No content found");

        listFilm.sort((film1, film2) -> film1.getTitle().compareToIgnoreCase(film2.getTitle()));

        return ResponseEntity.ok().body(listFilm);
    }


}
