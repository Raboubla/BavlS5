package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvaluation;
    
    private String satisfaction;
    
    private String commentaire;
    
    @Column(nullable = false)
    private LocalDate dateEvaluation;
    
    @ManyToOne
    @JoinColumn(name = "idCommande", nullable = false)
    private Commande commande;
} 