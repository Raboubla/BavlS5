package com.example.Bavl.services;

import com.example.Bavl.dto.*;
import com.example.Bavl.entities.*;
import com.example.Bavl.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;
    private final StockRepository stockRepository;
    private final ClientRepository clientRepository;

    public CommandeService(CommandeRepository commandeRepository,
                         ProduitRepository produitRepository,
                         StockRepository stockRepository,
                         ClientRepository clientRepository) {
        this.commandeRepository = commandeRepository;
        this.produitRepository = produitRepository;
        this.stockRepository = stockRepository;
        this.clientRepository = clientRepository;
    }

    public CommandeDTO createCommande(CommandeRequest request) {
        // Vérifier le client
        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        // Créer la commande
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setDateCommande(LocalDate.now());
        commande.setStatus("EN_ATTENTE");

        // Sauvegarder la commande
        commande = commandeRepository.save(commande);

        // Ajouter les produits
        for (CommandeProduitRequest item : request.getProduits()) {
            // Vérifier le stock
            Produit produit = produitRepository.findById(item.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            
            Stock stock = stockRepository.findByProduit(produit)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Stock non trouvé"));

            if (stock.getQuantiteStock().compareTo(BigDecimal.valueOf(item.getQuantite())) < 0) {
                throw new RuntimeException("Stock insuffisant");
            }

            // Créer la ligne de commande
            CommandeProduit commandeProduit = new CommandeProduit();
            commandeProduit.setCommande(commande);
            commandeProduit.setProduit(produit);
            commandeProduit.setQuantite(item.getQuantite());

            // Mettre à jour le stock
            stock.setQuantiteStock(stock.getQuantiteStock().subtract(BigDecimal.valueOf(item.getQuantite())));
            stockRepository.save(stock);
        }

        return convertToDTO(commande);
    }

    public List<CommandeDTO> getCommandesClient(Integer clientId) {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        return commandeRepository.findByClient(client)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public CommandeDTO getCommande(Integer id) {
        Commande commande = commandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        return convertToDTO(commande);
    }

    public CommandeDTO updateStatus(Integer id, String status) {
        Commande commande = commandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        // Vérifier si le statut est valide
        if (!isValidStatus(status)) {
            throw new RuntimeException("Statut invalide");
        }

        commande.setStatus(status);
        commande = commandeRepository.save(commande);

        // Si la commande est annulée, remettre les produits en stock
        if ("ANNULEE".equals(status)) {
            remettreEnStock(commande);
        }

        return convertToDTO(commande);
    }

    private boolean isValidStatus(String status) {
        return List.of("EN_ATTENTE", "CONFIRMEE", "EN_PREPARATION", "PRETE", "EN_LIVRAISON", "LIVREE", "PAYEE", "ANNULEE")
            .contains(status);
    }

    private void remettreEnStock(Commande commande) {
        for (CommandeProduit cp : commande.getCommandeProduits()) {
            Stock stock = stockRepository.findByProduit(cp.getProduit())
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Stock non trouvé"));

            BigDecimal nouvelleQuantite = stock.getQuantiteStock()
                .add(BigDecimal.valueOf(cp.getQuantite()));
            stock.setQuantiteStock(nouvelleQuantite);
            stockRepository.save(stock);
        }
    }

    private CommandeDTO convertToDTO(Commande commande) {
        return CommandeDTO.builder()
            .id(commande.getIdCommande())
            .date(commande.getDateCommande())
            .status(commande.getStatus())
            .produits(commande.getCommandeProduits().stream()
                .map(cp -> new CommandeProduitDTO(
                    cp.getProduit().getNomProduit(),
                    cp.getQuantite(),
                    cp.getProduit().getPrixVente()
                ))
                .collect(Collectors.toList()))
            .build();
    }
} 