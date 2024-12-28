package com.example.Bavl.controllers;

import com.example.Bavl.services.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
public class StatController {
    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    // Vue Facture
    @GetMapping("/factures/{idCommande}")
    public ResponseEntity<Map<String, Object>> getFacture(@PathVariable Integer idCommande) {
        return ResponseEntity.ok(statService.getFacture(idCommande));
    }

    // Vue Inventaire Produits
    @GetMapping("/inventaire")
    public ResponseEntity<List<Map<String, Object>>> getInventaireProduits() {
        return ResponseEntity.ok(statService.getInventaireProduits());
    }

    // Vue Ventes par Produit
    @GetMapping("/ventes/produits")
    public ResponseEntity<List<Map<String, Object>>> getVentesParProduit() {
        return ResponseEntity.ok(statService.getVentesParProduit());
    }

    // Vue Détails Commandes Clients
    @GetMapping("/commandes/details")
    public ResponseEntity<List<Map<String, Object>>> getDetailsCommandesClients() {
        return ResponseEntity.ok(statService.getDetailsCommandesClients());
    }

    // Vue Analyse Coûts Production
    @GetMapping("/couts-production")
    public ResponseEntity<List<Map<String, Object>>> getAnalyseCoutsProduction() {
        return ResponseEntity.ok(statService.getAnalyseCoutsProduction());
    }

    // Vue Statistiques Ventes
    @GetMapping("/ventes/stats")
    public ResponseEntity<List<Map<String, Object>>> getStatistiquesVentes(
        @RequestParam(required = false) String periode // jour, semaine, mois
    ) {
        return ResponseEntity.ok(statService.getStatistiquesVentes(periode));
    }

    // Vue Performance Produits
    @GetMapping("/produits/performance")
    public ResponseEntity<List<Map<String, Object>>> getPerformanceProduits() {
        return ResponseEntity.ok(statService.getPerformanceProduits());
    }

    // Vue Tendances Commandes
    @GetMapping("/commandes/tendances")
    public ResponseEntity<List<Map<String, Object>>> getTendancesCommandes() {
        return ResponseEntity.ok(statService.getTendancesCommandes());
    }
} 