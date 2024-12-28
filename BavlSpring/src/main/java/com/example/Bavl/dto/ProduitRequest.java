package com.example.Bavl.dto;

import lombok.Data;
import java.math.BigDecimal;
import com.example.Bavl.entities.TypeProduit;

@Data
public class ProduitRequest {
    private String nomProduit;
    private BigDecimal prixRevient;
    private BigDecimal prixVente;
    private String image;
    private TypeProduit typeProduit;
} 