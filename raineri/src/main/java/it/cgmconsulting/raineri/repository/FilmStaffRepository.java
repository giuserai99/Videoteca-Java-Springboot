package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.FilmStaff;
import it.cgmconsulting.raineri.entity.FilmStaffId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmStaffRepository extends JpaRepository<FilmStaff, FilmStaffId> {
}
