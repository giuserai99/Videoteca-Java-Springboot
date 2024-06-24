package it.cgmconsulting.raineri.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long genreId;

    @Column(unique = true, nullable = false, length = 30)
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }
}
