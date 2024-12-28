package com.example.Bavl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Bavl.entities.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProduit(Produit produit);
    List<Stock> findByQuantiteStockLessThan(BigDecimal quantite);
} 