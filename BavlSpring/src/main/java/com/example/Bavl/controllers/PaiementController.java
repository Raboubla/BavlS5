package com.example.Bavl.controllers;

import com.example.Bavl.dto.PaiementDTO;
import com.example.Bavl.dto.PaiementRequest;
import com.example.Bavl.services.PaiementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "*")
public class PaiementController {
    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping("/commande/{commandeId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<PaiementDTO> createPaiement(
        @PathVariable Integer commandeId,
        @RequestBody PaiementRequest request
    ) {
        return ResponseEntity.ok(paiementService.createPaiement(commandeId, request));
    }

    @GetMapping("/commande/{commandeId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<List<PaiementDTO>> getPaiementsCommande(@PathVariable Integer commandeId) {
        return ResponseEntity.ok(paiementService.getPaiementsCommande(commandeId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<PaiementDTO> getPaiement(@PathVariable Integer id) {
        return ResponseEntity.ok(paiementService.getPaiement(id));
    }
} 