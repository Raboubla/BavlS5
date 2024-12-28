package com.example.Bavl.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaiementRequest {
    private BigDecimal montant;
    private String methode; // CASH, MOBILE_MONEY, CARTE
} 