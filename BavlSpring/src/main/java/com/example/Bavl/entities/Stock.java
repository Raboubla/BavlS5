

package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStock;
    
    @Column(nullable = false)
    private BigDecimal quantiteStock;
    
    private LocalDate dateFaranyMaj;
    
    @ManyToOne
    @JoinColumn(name = "idProduit", nullable = false)
    private Produit produit;
    
    @OneToMany(mappedBy = "stock")
    private List<MvtStock> mouvements;
} 