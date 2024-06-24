package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
