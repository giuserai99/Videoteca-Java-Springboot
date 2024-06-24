package it.cgmconsulting.raineri.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long filmId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private short releaseYear;

    @ManyToOne
    @JoinColumn(nullable = false, name = "language_id")
    private Language languageId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "genre_id")
    private Genre genreId;


    public Film(String title, String description, short releaseYear, Language languageId, Genre genreId) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.genreId = genreId;
    }
}
