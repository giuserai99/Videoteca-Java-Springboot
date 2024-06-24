package it.cgmconsulting.raineri.repository;

import it.cgmconsulting.raineri.entity.Rental;
import it.cgmconsulting.raineri.entity.RentalId;
import it.cgmconsulting.raineri.payload.response.CustomerStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, RentalId> {

    @Query(value = "SELECT new it.cgmconsulting.raineri.payload.response.CustomerStoreResponse(" +
            "r.rentalId.inventoryId.storeId.storeName, " +
            "COUNT(r.rentalId.customerId)) " +
            "FROM Rental r " +
            "WHERE r.rentalId.inventoryId.storeId.storeName = :storeName")
    CustomerStoreResponse getTotalCustomers(String storeName);
}
