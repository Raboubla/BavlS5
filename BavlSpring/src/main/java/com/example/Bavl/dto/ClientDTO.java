package com.example.Bavl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Integer id;
    private String nomClient;
    private String email;
    private String telephone;
    private String adresse;
} 