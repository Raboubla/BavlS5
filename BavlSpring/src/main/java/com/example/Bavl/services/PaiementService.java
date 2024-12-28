package com.example.Bavl.services;

import com.example.Bavl.dto.PaiementDTO;
import com.example.Bavl.dto.PaiementRequest;
import com.example.Bavl.entities.Commande;
import com.example.Bavl.entities.Paiement;
import com.example.Bavl.repositories.CommandeRepository;
import com.example.Bavl.repositories.PaiementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
@Transactional
public class PaiementService {
    private final PaiementRepository paiementRepository;
    private final CommandeRepository commandeRepository;

    public PaiementService(PaiementRepository paiementRepository, 
                          CommandeRepository commandeRepository) {
        this.paiementRepository = paiementRepository;
        this.commandeRepository = commandeRepository;
    }

    public PaiementDTO createPaiement(Integer commandeId, PaiementRequest request) {
        Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        // Vérifier si la commande n'est pas déjà payée
        if ("PAYEE".equals(commande.getStatus())) {
            throw new RuntimeException("Cette commande est déjà payée");
        }

        // Vérifier le montant du paiement
        BigDecimal totalCommande = calculerTotalCommande(commande);
        if (request.getMontant().compareTo(totalCommande) != 0) {
            throw new RuntimeException("Le montant du paiement ne correspond pas au total de la commande");
        }

        Paiement paiement = new Paiement();
        paiement.setMontant(request.getMontant());
        paiement.setMethode(request.getMethode());
        paiement.setDatePaiement(LocalDate.now());
        paiement.setCommande(commande);

        paiement = paiementRepository.save(paiement);

        // Mettre à jour le statut de la commande
        commande.setStatus("PAYEE");
        commandeRepository.save(commande);

        return convertToDTO(paiement);
    }

    public List<PaiementDTO> getPaiementsCommande(Integer commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        return paiementRepository.findByCommande(commande)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public PaiementDTO getPaiement(Integer id) {
        return convertToDTO(paiementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paiement non trouvé")));
    }

    private PaiementDTO convertToDTO(Paiement paiement) {
        return PaiementDTO.builder()
            .id(paiement.getIdPaiement())
            .montant(paiement.getMontant())
            .datePaiement(paiement.getDatePaiement())
            .methode(paiement.getMethode())
            .commandeId(paiement.getCommande().getIdCommande())
            .build();
    }

    private BigDecimal calculerTotalCommande(Commande commande) {
        return commande.getCommandeProduits().stream()
            .map(cp -> cp.getProduit().getPrixVente().multiply(BigDecimal.valueOf(cp.getQuantite())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
} 