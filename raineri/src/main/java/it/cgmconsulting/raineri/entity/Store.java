package it.cgmconsulting.raineri.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long storeId;

    @Column(unique = true, nullable = false, length = 60)
    private String storeName;

    public Store(String storeName) {
        this.storeName = storeName;
    }
}
