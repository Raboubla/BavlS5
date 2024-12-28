package com.example.Bavl.controllers;

import com.example.Bavl.dto.PanierDTO;
import com.example.Bavl.services.PanierService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panier")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('CLIENT')")
public class PanierController {
    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Void> ajouterAuPanier(
        @RequestParam Integer clientId,
        @RequestParam Integer produitId,
        @RequestParam Integer quantite
    ) {
        panierService.ajouterAuPanier(clientId, produitId, quantite);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/retirer")
    public ResponseEntity<Void> retirerDuPanier(
        @RequestParam Integer clientId,
        @RequestParam Integer produitId
    ) {
        panierService.retirerDuPanier(clientId, produitId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/quantite")
    public ResponseEntity<Void> modifierQuantite(
        @RequestParam Integer clientId,
        @RequestParam Integer produitId,
        @RequestParam Integer quantite
    ) {
        panierService.modifierQuantite(clientId, produitId, quantite);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<PanierDTO> getPanier(@PathVariable Integer clientId) {
        return ResponseEntity.ok(panierService.getPanier(clientId));
    }

    @DeleteMapping("/{clientId}/vider")
    public ResponseEntity<Void> viderPanier(@PathVariable Integer clientId) {
        panierService.viderPanier(clientId);
        return ResponseEntity.ok().build();
    }
} 