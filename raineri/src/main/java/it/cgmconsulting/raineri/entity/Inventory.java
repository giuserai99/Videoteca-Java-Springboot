package it.cgmconsulting.raineri.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long inventoryId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "store_id")
    private Store storeId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "film_id")
    private Film filmId;

    public Inventory(Store storeId, Film filmId) {
        this.storeId = storeId;
        this.filmId = filmId;
    }
}
