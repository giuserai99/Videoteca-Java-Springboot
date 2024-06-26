package it.cgmconsulting.raineri.controller;

import it.cgmconsulting.raineri.payload.request.RentalIdRequest;
import it.cgmconsulting.raineri.service.RentalService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Validated
public class RentalController {

    private final RentalService rentalService;

    //Punto 4.
    @GetMapping("/count-customers-by-store/{storeName}")
    public ResponseEntity<?> getTotalCustomers(@PathVariable @NotBlank String storeName){
        return rentalService.getTotalCustomers(storeName);
    }

    //Punto 5.
    @Transactional
    @PutMapping("/add-update-rental")
    public ResponseEntity<?> addUpdateRental(@RequestParam long storeId,
                                             @RequestBody @Valid RentalIdRequest request,
                                             @RequestParam(defaultValue = "") @Future LocalDateTime rentalReturn){
        return rentalService.addUpdateRental(storeId, request, rentalReturn);
    }

    //Punto 6.
    @GetMapping("/count-rentals-in-date-range-by-store/{storeId}")
    public ResponseEntity<?> getRentalsByDateRange(@PathVariable @Min(1) long storeId,
                                                   @RequestParam @PastOrPresent LocalDate start,
                                                   @RequestParam @PastOrPresent LocalDate end){
        return rentalService.getRentalsByDateRange(storeId, start, end);
    }

    //Punto 7.
    @GetMapping("/find-all-films-rent-by-one-customer/{customerId}")
    public ResponseEntity<?> getFilmsByCustomer(@PathVariable @Min(1) long customerId){
        return rentalService.getFilmsByCustomer(customerId);
    }

    //Punto 8.
    @GetMapping("/find-film-with-max-number-of-rent")
    public ResponseEntity<?> getFilmMaxRent(){
        return rentalService.getFilmMaxRent();
    }

    //Punto 10.
    @GetMapping("/find-rentable-films")
    public ResponseEntity<?> getRentableFilms(@RequestParam @NotBlank @Size(max = 20) String title){
        return rentalService.getRentableFilms(title);
    }

}
