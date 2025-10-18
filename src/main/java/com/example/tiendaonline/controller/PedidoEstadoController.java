package com.example.tiendaonline.controller;

import com.example.tiendaonline.entity.Pedido;
import com.example.tiendaonline.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoEstadoController {
    private final PedidoRepository pedidoRepository;
    public PedidoEstadoController(PedidoRepository pedidoRepository){this.pedidoRepository = pedidoRepository;}

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestParam String valor) {
        Optional<Pedido> maybe = pedidoRepository.findById(id);
        if (maybe.isEmpty()) return ResponseEntity.notFound().build();
        Pedido p = maybe.get();
        String actual = p.getEstado();
        if (actual.equals("ENTREGADO") || actual.equals("CANCELADO")) {
            return ResponseEntity.badRequest().body("No se puede cambiar el estado desde " + actual);
        }
        if (valor.equals("CANCELADO")) {
            p.setEstado("CANCELADO");
        } else if (actual.equals("CREADO") && valor.equals("ENVIADO")) {
            p.setEstado("ENVIADO");
        } else if (actual.equals("ENVIADO") && valor.equals("ENTREGADO")) {
            p.setEstado("ENTREGADO");
        } else {
            return ResponseEntity.badRequest().body("Transición de estado inválida: " + actual + " -> " + valor);
        }
        pedidoRepository.save(p);
        return ResponseEntity.ok(p);
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(){ return ResponseEntity.ok(pedidoRepository.findAll()); }
}
