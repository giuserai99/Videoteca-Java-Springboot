package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerId(long customerId);

    boolean existsByCustomerId(long customerId);
}
