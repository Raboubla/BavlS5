package com.example.Bavl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PanierItemDTO {
    private Integer produitId;
    private String nomProduit;
    private BigDecimal prixUnitaire;
    private Integer quantite;
    private BigDecimal sousTotal;
} 