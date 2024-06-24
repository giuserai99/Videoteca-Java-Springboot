package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Film;
import it.cgmconsulting.raineri.payload.response.FilmResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<Film> findByFilmId(long filmId);

    @Query(value = "SELECT new it.cgmconsulting.raineri.payload.response.FilmResponse(" +
            "f.filmId, " +
            "f.title, " +
            "f.description, " +
            "f.releaseYear, " +
            "f.languageId.languageName" +
            ") FROM Film f " +
            "WHERE languageId.languageId = :languageId")
    List<FilmResponse> getFilmsByLanguage(long languageId);
}
