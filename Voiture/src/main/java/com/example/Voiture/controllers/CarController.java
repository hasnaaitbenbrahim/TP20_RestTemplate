package com.example.Voiture.controllers;


import com.example.Voiture.models.CarResponse;
import com.example.Voiture.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/car")
public class CarController {
    @Autowired
    private CarService carService;
    
    /**
     * Récupère toutes les voitures avec les détails des clients
     * GET /api/car
     */
    @GetMapping
    public List<CarResponse> findAll() {
        return carService.findAll();
    }
    
    /**
     * Récupère une voiture par son ID avec les détails du client
     * GET /api/car/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            CarResponse car = carService.findById(id);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur: " + e.getMessage());
        }
    }
    
    /**
     * Ajoute une nouvelle voiture
     * POST /api/car
     */
    @PostMapping
    public ResponseEntity<com.example.Voiture.entities.Car> save(@RequestBody com.example.Voiture.entities.Car car) {
        com.example.Voiture.entities.Car savedCar = carService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }
    
    /**
     * Met à jour une voiture existante
     * PUT /api/car/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody com.example.Voiture.entities.Car carDetails) {
        try {
            com.example.Voiture.entities.Car updatedCar = carService.update(id, carDetails);
            return ResponseEntity.ok(updatedCar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur: " + e.getMessage());
        }
    }
    
    /**
     * Supprime une voiture
     * DELETE /api/car/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            carService.delete(id);
            return ResponseEntity.ok().body("Voiture supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur: " + e.getMessage());
        }
    }
}

