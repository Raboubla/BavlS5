package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduit;
    
    @Column(nullable = false)
    private String nomProduit;
    
    @Column(nullable = false)
    private BigDecimal prixRevient;
    
    @Column(nullable = false)
    private BigDecimal prixVente;
    
    private String image;
    
    @ManyToOne
    @JoinColumn(name = "idTypeProduit", nullable = false)
    private TypeProduit typeProduit;
    
    @OneToMany(mappedBy = "produit")
    private List<CommandeProduit> commandeProduits;
    
    @OneToMany(mappedBy = "produit")
    private List<Stock> stocks;
    
    @OneToMany(mappedBy = "produit")
    private List<Formule> formules;
} 