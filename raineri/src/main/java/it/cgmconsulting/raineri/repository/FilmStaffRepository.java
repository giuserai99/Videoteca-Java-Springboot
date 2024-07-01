package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.FilmStaff;
import it.cgmconsulting.raineri.entity.FilmStaffId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmStaffRepository extends JpaRepository<FilmStaff, FilmStaffId> {

    @Query(value = "SELECT s.filmStaffId.filmId.filmId " +
            "FROM FilmStaff s " +
            "WHERE s.filmStaffId.staffId.staffId = :staffId")
    List<Long> getFilmIdByStaff(long staffId);

}
