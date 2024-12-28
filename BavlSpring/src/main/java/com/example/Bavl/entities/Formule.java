package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "formule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormule;
    
    @Column(nullable = false)
    private BigDecimal quantite;
    
    @ManyToOne
    @JoinColumn(name = "idProduit", nullable = false)
    private Produit produit;
    
    @ManyToOne
    @JoinColumn(name = "idIngredient", nullable = false)
    private Ingredient ingredient;
} 