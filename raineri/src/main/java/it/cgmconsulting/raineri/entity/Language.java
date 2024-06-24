package it.cgmconsulting.raineri.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long languageId;

    @Column(unique = true, nullable = false, length = 20)
    private String languageName;

    public Language(String languageName) {
        this.languageName = languageName;
    }
}
