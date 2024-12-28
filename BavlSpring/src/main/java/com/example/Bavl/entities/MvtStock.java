package com.example.Bavl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mvtstock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MvtStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMvtStock;
    
    @Column(nullable = false)
    private String typeMouvement;
    
    @Column(nullable = false)
    private BigDecimal quantite;
    
    @Column(nullable = false)
    private LocalDate dateMvt;
    
    @ManyToOne
    @JoinColumn(name = "idStock", nullable = false)
    private Stock stock;
} 