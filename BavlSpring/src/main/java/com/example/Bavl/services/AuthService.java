package com.example.Bavl.services;

import com.example.Bavl.dto.AuthResponse;
import com.example.Bavl.dto.LoginRequest;
import com.example.Bavl.dto.RegisterRequest;
import com.example.Bavl.entities.Client;
import com.example.Bavl.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.Bavl.security.JwtService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(ClientRepository clientRepository, 
                      PasswordEncoder passwordEncoder, 
                      JwtService jwtService) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (clientRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        Client client = new Client();
        client.setNomClient(request.getNomClient());
        client.setEmail(request.getEmail());
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setTelephone(request.getTelephone());
        client.setAdresse(request.getAdresse());

        clientRepository.save(client);

        String token = jwtService.generateToken(client);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        Client client = clientRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        if (!passwordEncoder.matches(request.getPassword(), client.getPassword())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        String token = jwtService.generateToken(client);
        return new AuthResponse(token);
    }
} 