package com.example.Bavl.dto;

import lombok.Data;
import java.util.List;

@Data
public class CommandeRequest {
    private Integer clientId;
    private List<CommandeProduitRequest> produits;
} 