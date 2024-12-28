package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commande")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCommande;
    
    @Column(nullable = false)
    private LocalDate dateCommande;
    
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "idClient", nullable = false)
    private Client client;
    
    @OneToMany(mappedBy = "commande")
    private List<CommandeProduit> commandeProduits;
    
    @OneToMany(mappedBy = "commande")
    private List<Paiement> paiements;
    
    @OneToMany(mappedBy = "commande")
    private List<Evaluation> evaluations;
} 