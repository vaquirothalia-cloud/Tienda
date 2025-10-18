package com.example.tiendaonline.controller;

import com.example.tiendaonline.entity.Cliente;
import com.example.tiendaonline.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository repo;
    public ClienteController(ClienteRepository repo){this.repo = repo;}

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente c){ return ResponseEntity.ok(repo.save(c)); }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){ return ResponseEntity.ok(repo.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Long id){ return ResponseEntity.of(repo.findById(id)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id){ repo.deleteById(id); return ResponseEntity.noContent().build(); }
}
