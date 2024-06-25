package it.cgmconsulting.raineri.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rental {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private RentalId rentalId;

    private LocalDateTime rentalReturn;

}
