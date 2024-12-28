package com.example.Bavl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Bavl.entities.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    List<Commande> findByClient(Client client);
    List<Commande> findByStatus(String status);
    List<Commande> findByDateCommandeBetween(LocalDate debut, LocalDate fin);
} 