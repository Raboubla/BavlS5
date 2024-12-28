package com.example.Bavl.controllers;

import com.example.Bavl.dto.CommandeDTO;
import com.example.Bavl.dto.CommandeRequest;
import com.example.Bavl.services.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "*")
public class CommandeController {
    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<CommandeDTO> createCommande(@RequestBody CommandeRequest request) {
        return ResponseEntity.ok(commandeService.createCommande(request));
    }

    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<List<CommandeDTO>> getCommandesClient(@PathVariable Integer clientId) {
        return ResponseEntity.ok(commandeService.getCommandesClient(clientId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public ResponseEntity<CommandeDTO> getCommande(@PathVariable Integer id) {
        return ResponseEntity.ok(commandeService.getCommande(id));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommandeDTO> updateStatus(
        @PathVariable Integer id, 
        @RequestParam String status
    ) {
        return ResponseEntity.ok(commandeService.updateStatus(id, status));
    }
} 