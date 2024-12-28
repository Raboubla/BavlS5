package com.example.Bavl.repositories;

import com.example.Bavl.entities.Produit;
import com.example.Bavl.entities.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    List<Produit> findByTypeProduit(TypeProduit typeProduit);
    List<Produit> findByPrixVenteBetween(BigDecimal min, BigDecimal max);
} 