package com.example.Bavl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PanierDTO {
    private List<PanierItemDTO> items;
    private BigDecimal total;
} 