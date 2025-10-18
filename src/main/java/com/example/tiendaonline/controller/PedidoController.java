package com.example.tiendaonline.controller;

import com.example.tiendaonline.entity.Cliente;
import com.example.tiendaonline.entity.ItemPedido;
import com.example.tiendaonline.entity.Pedido;
import com.example.tiendaonline.entity.Producto;
import com.example.tiendaonline.repository.ClienteRepository;
import com.example.tiendaonline.repository.PedidoRepository;
import com.example.tiendaonline.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/clientes/{clienteId}/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoController(PedidoRepository pedidoRepository, ProductoRepository productoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<?> crearPedido(@PathVariable Long clienteId, @RequestBody Pedido pedido) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
        pedido.setCliente(cliente);

        // evitar producto duplicado en el mismo pedido
        Set<Long> productosEnPedido = new HashSet<>();
        for (ItemPedido item : pedido.getItems()) {
            if (item.getProducto() == null || item.getProducto().getId() == null) {
                return ResponseEntity.badRequest().body("Cada item debe incluir producto.id"); 
            }
            if (!productosEnPedido.add(item.getProducto().getId())) {
                return ResponseEntity.badRequest().body("No se permiten productos duplicados en el mismo pedido");
            }
        }

        double total = 0;
        for (ItemPedido item : pedido.getItems()) {
            Producto producto = productoRepository.findById(item.getProducto().getId()).orElseThrow();
            if (producto.getStock() < item.getCantidad()) {
                return ResponseEntity.badRequest().body("Stock insuficiente para: " + producto.getNombre());
            }
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            item.setPedido(pedido);
            item.setProducto(producto);
            item.setPrecioUnitario(producto.getPrecio());
            total += producto.getPrecio().multiply(java.math.BigDecimal.valueOf(item.getCantidad())).doubleValue();
        }

        pedido.setTotal(total);
        Pedido saved = pedidoRepository.save(pedido);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> listarPedidos(@PathVariable Long clienteId) {
        Cliente c = clienteRepository.findById(clienteId).orElseThrow();
        return ResponseEntity.ok(c.getPedidos());
    }
}
