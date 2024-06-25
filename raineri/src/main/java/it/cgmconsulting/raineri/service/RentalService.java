package it.cgmconsulting.raineri.service;

import it.cgmconsulting.raineri.entity.*;
import it.cgmconsulting.raineri.exception.GenericException;
import it.cgmconsulting.raineri.exception.ResourceNotFoundException;
import it.cgmconsulting.raineri.payload.request.RentalIdRequest;
import it.cgmconsulting.raineri.payload.response.CustomerStoreResponse;
import it.cgmconsulting.raineri.repository.CustomerRepository;
import it.cgmconsulting.raineri.repository.InventoryRepository;
import it.cgmconsulting.raineri.repository.RentalRepository;
import it.cgmconsulting.raineri.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final StoreRepository storeRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;

    //Punto 4.
    public ResponseEntity<?> getTotalCustomers(String storeName){

        CustomerStoreResponse customer = rentalRepository.getTotalCustomers(storeName);

        if(customer.getStoreName() == null)
            throw new ResourceNotFoundException("Store", "storeName", storeName);

        return ResponseEntity.status(200).body(customer);
    }


    //Punto 5.
    public ResponseEntity<?> addUpdateRental(long storeId, RentalIdRequest request, LocalDateTime rentalReturn){

        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(()-> new ResourceNotFoundException("Store", "storeId", storeId));

        Inventory inventory = inventoryRepository.findByInventoryId(request.getInventoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Inventory", "inventoryId", request.getInventoryId()));

        if(!store.equals(inventory.getStoreId()))
            return ResponseEntity.badRequest().body("Film not present in this store");

        Customer customer = customerRepository.findByCustomerId(request.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "customerId", request.getCustomerId()));

        if(rentalRepository.existsByRentalIdInventoryIdInventoryIdAndRentalReturnIsNull(request.getInventoryId()))
            return ResponseEntity.badRequest().body("Film already rented");

        RentalId rentalId = new RentalId(customer, inventory, request.getRentalDate());

        Rental rental = rentalRepository.findByRentalId(rentalId);

        if(rental == null) {
            rentalReturn = null;
            rental = new Rental(rentalId, rentalReturn);
            rentalRepository.save(rental);
            return ResponseEntity.ok().body("Rental added");
        }

        rental.setRentalReturn(rentalReturn);
        return ResponseEntity.ok().body("RentalReturn updated");
    }


    //Punto 6.
    public ResponseEntity<?> getRentalsByDateRange(long storeId, LocalDate start, LocalDate end){

        if(!storeRepository.existsByStoreId(storeId))
            throw new ResourceNotFoundException("Store", "storeId", storeId);

        if(start.isAfter(end))
            throw new GenericException("Start date must been before end", HttpStatus.CONFLICT);

        return ResponseEntity.ok().body(rentalRepository.countRentals(storeId, start, end));
    }

    //Punto 7.
    public ResponseEntity<?> getFilmsByCustomer(long customerId){

        if(!customerRepository.existsByCustomerId(customerId))
            throw new ResourceNotFoundException("Customer", "customerId", customerId);

        return ResponseEntity.ok().body(rentalRepository.getFilmRent(customerId));
    }

//    //Punto 8.
//    public ResponseEntity<?> getFilmMaxRent(){
//        return ResponseEntity.ok().body(rentalRepository.getFilmMaxRent());
//    }

}
