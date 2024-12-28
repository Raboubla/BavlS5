package com.example.Bavl.controllers;

import com.example.Bavl.dto.ProduitDTO;
import com.example.Bavl.dto.ProduitRequest;
import com.example.Bavl.services.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*")
public class ProduitController {
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAllProduits() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> getProduit(@PathVariable Integer id) {
        return ResponseEntity.ok(produitService.getProduit(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProduitDTO> createProduit(@RequestBody ProduitRequest request) {
        return ResponseEntity.ok(produitService.createProduit(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable Integer id, @RequestBody ProduitRequest request) {
        return ResponseEntity.ok(produitService.updateProduit(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduit(@PathVariable Integer id) {
        produitService.deleteProduit(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<ProduitDTO>> getProduitsByType(@PathVariable Integer typeId) {
        return ResponseEntity.ok(produitService.getProduitsByType(typeId));
    }
} 