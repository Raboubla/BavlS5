package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaiement;
    
    @Column(nullable = false)
    private BigDecimal montant;
    
    @Column(nullable = false)
    private LocalDate datePaiement;
    
    @Column(nullable = false)
    private String methode;
    
    @ManyToOne
    @JoinColumn(name = "idCommande", nullable = false)
    private Commande commande;
} 