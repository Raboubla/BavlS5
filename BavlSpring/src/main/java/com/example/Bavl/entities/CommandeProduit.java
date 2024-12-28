package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commandeproduit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCommandeProduit;
    
    @Column(nullable = false)
    private Integer quantite;
    
    @ManyToOne
    @JoinColumn(name = "idCommande", nullable = false)
    private Commande commande;
    
    @ManyToOne
    @JoinColumn(name = "idProduit", nullable = false)
    private Produit produit;
} 