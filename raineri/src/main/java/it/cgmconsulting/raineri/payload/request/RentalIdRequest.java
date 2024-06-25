package it.cgmconsulting.raineri.payload.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentalIdRequest {

    @Min(1)
    private long customerId;

    @Min(1)
    private long inventoryId;

    @PastOrPresent
    private LocalDateTime rentalDate = LocalDateTime.now();
}
