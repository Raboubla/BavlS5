package com.example.Bavl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {
    private Integer id;
    private BigDecimal montant;
    private LocalDate datePaiement;
    private String methode;
    private Integer commandeId;
} 