package com.example.Bavl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDTO {
    private Integer id;
    private LocalDate date;
    private String status;
    private List<CommandeProduitDTO> produits;
} 