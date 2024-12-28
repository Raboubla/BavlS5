package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "unite")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnite;
    
    @Column(nullable = false)
    private String unite;
    
    @OneToMany(mappedBy = "unite")
    private List<Ingredient> ingredients;
} 