package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsByStoreIdStoreIdAndFilmIdFilmId(long storeId, long FilmId);

}
