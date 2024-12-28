package com.example.Bavl.services;

import com.example.Bavl.dto.ProduitDTO;
import com.example.Bavl.dto.ProduitRequest;
import com.example.Bavl.entities.Produit;
import com.example.Bavl.entities.Stock;
import com.example.Bavl.entities.TypeProduit;
import com.example.Bavl.repositories.ProduitRepository;
import com.example.Bavl.repositories.StockRepository;
import com.example.Bavl.repositories.TypeProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduitService {
    private final ProduitRepository produitRepository;
    private final StockRepository stockRepository;
    private final TypeProduitRepository typeProduitRepository;

    public ProduitService(ProduitRepository produitRepository, 
                         StockRepository stockRepository,
                         TypeProduitRepository typeProduitRepository) {
        this.produitRepository = produitRepository;
        this.stockRepository = stockRepository;
        this.typeProduitRepository = typeProduitRepository;
    }

    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public ProduitDTO getProduit(Integer id) {
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        return convertToDTO(produit);
    }

    public ProduitDTO createProduit(ProduitRequest request) {
        Produit produit = new Produit();
        produit.setNomProduit(request.getNomProduit());
        produit.setPrixRevient(request.getPrixRevient());
        produit.setPrixVente(request.getPrixVente());
        produit.setImage(request.getImage());
        produit.setTypeProduit(request.getTypeProduit());

        produit = produitRepository.save(produit);
        return convertToDTO(produit);
    }

    private ProduitDTO convertToDTO(Produit produit) {
        Stock stock = stockRepository.findByProduit(produit)
            .stream()
            .findFirst()
            .orElse(null);

        return ProduitDTO.builder()
            .id(produit.getIdProduit())
            .nom(produit.getNomProduit())
            .prix(produit.getPrixVente())
            .image(produit.getImage())
            .type(produit.getTypeProduit().getNonTypeProduit())
            .quantiteStock(stock != null ? stock.getQuantiteStock() : BigDecimal.ZERO)
            .build();
    }

    public ProduitDTO updateProduit(Integer id, ProduitRequest request) {
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        produit.setNomProduit(request.getNomProduit());
        produit.setPrixRevient(request.getPrixRevient());
        produit.setPrixVente(request.getPrixVente());
        produit.setImage(request.getImage());
        produit.setTypeProduit(request.getTypeProduit());

        produit = produitRepository.save(produit);
        return convertToDTO(produit);
    }

    public void deleteProduit(Integer id) {
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            
        // Vérifier s'il n'y a pas de commandes en cours pour ce produit
        List<Stock> stocks = stockRepository.findByProduit(produit);
        if (!stocks.isEmpty()) {
            throw new RuntimeException("Impossible de supprimer un produit qui a du stock");
        }

        produitRepository.delete(produit);
    }

    public List<ProduitDTO> getProduitsByType(Integer typeId) {
        TypeProduit typeProduit = typeProduitRepository.findById(typeId)
            .orElseThrow(() -> new RuntimeException("Type de produit non trouvé"));

        return produitRepository.findByTypeProduit(typeProduit)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
} 