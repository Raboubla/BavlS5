package com.example.Bavl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDTO {
    private Integer id;
    private String nom;
    private BigDecimal prix;
    private String image;
    private String type;
    private BigDecimal quantiteStock;
} 