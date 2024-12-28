package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ingredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredient;
    
    @Column(nullable = false)
    private String nomIngredient;
    
    @ManyToOne
    @JoinColumn(name = "idUnite", nullable = false)
    private Unite unite;
    
    @OneToMany(mappedBy = "ingredient")
    private List<Formule> formules;
} 