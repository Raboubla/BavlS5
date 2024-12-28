package com.example.Bavl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeProduitDTO {
    private String nomProduit;
    private Integer quantite;
    private BigDecimal prix;
} 