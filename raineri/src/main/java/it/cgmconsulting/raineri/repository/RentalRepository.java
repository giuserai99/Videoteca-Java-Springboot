package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Rental;
import it.cgmconsulting.raineri.entity.RentalId;
import it.cgmconsulting.raineri.payload.response.CustomerStoreResponse;
import it.cgmconsulting.raineri.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.raineri.payload.response.FilmRentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface RentalRepository extends JpaRepository<Rental, RentalId> {

    @Query(value = "SELECT new it.cgmconsulting.raineri.payload.response.CustomerStoreResponse(" +
            "r.rentalId.inventoryId.storeId.storeName, " +
            "COUNT(r.rentalId.customerId)) " +
            "FROM Rental r " +
            "WHERE r.rentalId.inventoryId.storeId.storeName = :storeName")
    CustomerStoreResponse getTotalCustomers(String storeName);

    boolean existsByRentalIdInventoryIdInventoryIdAndRentalReturnIsNull(long inventoryId);

    Rental findByRentalId(RentalId rentalId);

    @Query(value = "SELECT COUNT(*) " +
            "FROM Rental r " +
            "JOIN Inventory i " +
            "ON r.inventory_id = i.inventory_id " +
            "WHERE i.store_id = :storeId " +
            "AND r.rental_date BETWEEN :start AND :end", nativeQuery = true)
    int countRentals(long storeId, LocalDate start, LocalDate end);

    @Query(value = "SELECT new it.cgmconsulting.raineri.payload.response.FilmRentResponse(" +
            "r.rentalId.inventoryId.filmId.filmId, " +
            "r.rentalId.inventoryId.filmId.title, " +
            "r.rentalId.inventoryId.storeId.storeName) " +
            "FROM Rental r " +
            "WHERE r.rentalId.customerId.customerId = :customerId")
    List<FilmRentResponse> getFilmRent(long customerId);

//    @Query(value = "SELECT new it.cgmconsulting.raineri.payload.response.FilmMaxRentResponse(" +
//            "r.rentalId.inventoryId.filmId.filmId, " +
//            "r.rentalId.inventoryId.filmId.title," +
//            "(SELECT MAX(COUNT(r.rentalId.inventoryId.filmId)))) " +
//            "FROM Rental r")
//    List<FilmMaxRentResponse> getFilmMaxRent();
}
