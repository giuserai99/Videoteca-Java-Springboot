package it.cgmconsulting.raineri.controller;

import it.cgmconsulting.raineri.repository.FilmRepository;
import it.cgmconsulting.raineri.repository.InventoryRepository;
import it.cgmconsulting.raineri.service.InventoryService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService  inventoryService;


    //Punto 3.
    @PostMapping("/add-film-to-store/{storeId}/{filmId}")
    public ResponseEntity<?> addFilm(@PathVariable @Min(1) long storeId,
                                     @PathVariable @Min(1) long filmId){
        return inventoryService.addFilm(storeId, filmId);
    }

}
