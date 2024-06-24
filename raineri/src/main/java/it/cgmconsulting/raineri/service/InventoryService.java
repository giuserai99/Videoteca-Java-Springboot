package it.cgmconsulting.raineri.service;

import it.cgmconsulting.raineri.entity.Film;
import it.cgmconsulting.raineri.entity.Inventory;
import it.cgmconsulting.raineri.entity.Store;
import it.cgmconsulting.raineri.exception.GenericException;
import it.cgmconsulting.raineri.exception.ResourceNotFoundException;
import it.cgmconsulting.raineri.repository.FilmRepository;
import it.cgmconsulting.raineri.repository.InventoryRepository;
import it.cgmconsulting.raineri.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final StoreRepository storeRepository;
    private final FilmRepository filmRepository;

    //Punto 3.
    public ResponseEntity<?> addFilm(long storeId, long filmId){

        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store", "storeId", storeId));

        Film film = filmRepository.findByFilmId(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "filmId", filmId));

        if(inventoryRepository.existsByStoreIdStoreIdAndFilmIdFilmId(storeId, filmId))
            throw new GenericException("Inventory already exists", HttpStatus.CONFLICT);

        Inventory inventory = new Inventory(store, film);
        inventoryRepository.save(inventory);

        return ResponseEntity.status(201).body("Inventory created");
    }

}
