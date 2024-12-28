package com.example.Bavl.services;

import com.example.Bavl.dto.PanierDTO;
import com.example.Bavl.dto.PanierItemDTO;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

@Service
public class PanierService {
    private final Map<Integer, Map<Integer, Integer>> paniers = new HashMap<>(); // clientId -> (produitId -> quantité)
    private final ProduitService produitService;

    public PanierService(ProduitService produitService) {
        this.produitService = produitService;
    }

    public void ajouterAuPanier(Integer clientId, Integer produitId, Integer quantite) {
        paniers.computeIfAbsent(clientId, k -> new HashMap<>());
        Map<Integer, Integer> panier = paniers.get(clientId);
        panier.merge(produitId, quantite, Integer::sum);
    }

    public void retirerDuPanier(Integer clientId, Integer produitId) {
        if (paniers.containsKey(clientId)) {
            paniers.get(clientId).remove(produitId);
        }
    }

    public void modifierQuantite(Integer clientId, Integer produitId, Integer quantite) {
        if (paniers.containsKey(clientId)) {
            if (quantite <= 0) {
                retirerDuPanier(clientId, produitId);
            } else {
                paniers.get(clientId).put(produitId, quantite);
            }
        }
    }

    public PanierDTO getPanier(Integer clientId) {
        if (!paniers.containsKey(clientId)) {
            return new PanierDTO(new ArrayList<>(), BigDecimal.ZERO);
        }

        Map<Integer, Integer> panier = paniers.get(clientId);
        List<PanierItemDTO> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
            var produit = produitService.getProduit(entry.getKey());
            var quantite = entry.getValue();
            var sousTotal = produit.getPrix().multiply(BigDecimal.valueOf(quantite));
            
            items.add(new PanierItemDTO(
                produit.getId(),
                produit.getNom(),
                produit.getPrix(),
                quantite,
                sousTotal
            ));
            
            total = total.add(sousTotal);
        }

        return new PanierDTO(items, total);
    }

    public void viderPanier(Integer clientId) {
        paniers.remove(clientId);
    }
} 