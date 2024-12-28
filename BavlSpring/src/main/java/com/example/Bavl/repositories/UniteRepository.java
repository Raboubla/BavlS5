package com.example.Bavl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Bavl.entities.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UniteRepository extends JpaRepository<Unite, Integer> {
    Optional<Unite> findByUnite(String unite);
} 