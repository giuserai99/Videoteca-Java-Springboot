package it.cgmconsulting.raineri.controller;

import it.cgmconsulting.raineri.service.RentalService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    //Punto 4.
    @GetMapping("/count-customers-by-store/{storeName}")
    public ResponseEntity<?> getTotalCustomers(@PathVariable @NotBlank String storeName){
        return rentalService.getTotalCustomers(storeName);
    }

}
