package com.example.Bavl.services;

import com.example.Bavl.entities.Admin;
import com.example.Bavl.repositories.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin updateAdmin(Integer id, Admin updatedAdmin) {
        return adminRepository.findById(id)
            .map(admin -> {
                admin.setNomAdmin(updatedAdmin.getNomAdmin());
                admin.setEmail(updatedAdmin.getEmail());
                // Crypter le nouveau mot de passe si fourni
                if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
                    admin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
                }
                return adminRepository.save(admin);
            })
            .orElseThrow(() -> new RuntimeException("Admin non trouvé"));
    }
} 