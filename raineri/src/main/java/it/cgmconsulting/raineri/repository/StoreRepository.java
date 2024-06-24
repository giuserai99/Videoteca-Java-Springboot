package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByStoreId(long storeId);

}
