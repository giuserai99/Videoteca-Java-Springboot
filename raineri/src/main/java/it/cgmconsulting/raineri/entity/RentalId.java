package it.cgmconsulting.raineri.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class RentalId implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "inventory_id")
    private Inventory inventoryId;

    private LocalDateTime rentalDate;

}
