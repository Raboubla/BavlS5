package com.example.Bavl.services;

import com.example.Bavl.dto.ClientDTO;
import com.example.Bavl.dto.ClientRequest;
import com.example.Bavl.entities.Client;
import com.example.Bavl.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public ClientDTO getClient(Integer id) {
        return convertToDTO(findClientById(id));
    }

    public ClientDTO updateClient(Integer id, ClientRequest request) {
        Client client = findClientById(id);
        
        client.setNomClient(request.getNomClient());
        client.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            client.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        client.setTelephone(request.getTelephone());
        client.setAdresse(request.getAdresse());

        return convertToDTO(clientRepository.save(client));
    }

    public void deleteClient(Integer id) {
        Client client = findClientById(id);
        // Vérifier s'il n'y a pas de commandes en cours
        if (!client.getCommandes().isEmpty()) {
            throw new RuntimeException("Impossible de supprimer un client qui a des commandes");
        }
        clientRepository.delete(client);
    }

    private Client findClientById(Integer id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client non trouvé"));
    }

    private ClientDTO convertToDTO(Client client) {
        return ClientDTO.builder()
            .id(client.getIdClient())
            .nomClient(client.getNomClient())
            .email(client.getEmail())
            .telephone(client.getTelephone())
            .adresse(client.getAdresse())
            .build();
    }

    public ClientDTO getCurrentClient() {
        // À implémenter avec Spring Security pour obtenir le client connecté
        throw new UnsupportedOperationException("Not implemented yet");
    }
} 