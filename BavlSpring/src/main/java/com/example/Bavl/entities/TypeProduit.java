package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "typeproduit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeProduit;
    
    @Column(nullable = false)
    private String nonTypeProduit;
    
    @OneToMany(mappedBy = "typeProduit")
    private List<Produit> produits;
} 