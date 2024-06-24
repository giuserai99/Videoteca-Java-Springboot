package it.cgmconsulting.raineri.service;

import it.cgmconsulting.raineri.entity.Store;
import it.cgmconsulting.raineri.exception.GenericException;
import it.cgmconsulting.raineri.exception.ResourceNotFoundException;
import it.cgmconsulting.raineri.payload.response.CustomerStoreResponse;
import it.cgmconsulting.raineri.repository.RentalRepository;
import it.cgmconsulting.raineri.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final StoreRepository storeRepository;

    //Punto 4.
    public ResponseEntity<?> getTotalCustomers(String storeName){

        CustomerStoreResponse customer = rentalRepository.getTotalCustomers(storeName);

        if(customer.getStoreName() == null)
            throw new ResourceNotFoundException("Store", "storeName", storeName);

        return ResponseEntity.status(200).body(customer);
    }

}
