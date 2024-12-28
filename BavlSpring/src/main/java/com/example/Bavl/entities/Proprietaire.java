package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "proprietaire")
@Data
public class Proprietaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProprietaire;
    
    @Column(nullable = false)
    private String nomProprietaire;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
} 