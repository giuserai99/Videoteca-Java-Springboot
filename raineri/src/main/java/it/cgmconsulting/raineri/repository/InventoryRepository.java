package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsByStoreIdStoreIdAndFilmIdFilmId(long storeId, long FilmId);

    Optional<Inventory> findByInventoryId(long inventoryId);

    boolean existsByFilmIdTitle(String title);
}
