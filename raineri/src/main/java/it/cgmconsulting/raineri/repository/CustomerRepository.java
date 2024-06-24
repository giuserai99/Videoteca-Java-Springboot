package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
