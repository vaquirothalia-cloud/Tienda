package com.example.tiendaonline.controller;

import com.example.tiendaonline.entity.Categoria;
import com.example.tiendaonline.entity.Producto;
import com.example.tiendaonline.repository.CategoriaRepository;
import com.example.tiendaonline.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoController(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoRepository.findAll());
    }

    // ✅ Asociar producto a categoría
    @PostMapping("/{productoId}/categorias/{categoriaId}")
    public ResponseEntity<Producto> asociarCategoria(
            @PathVariable Long productoId,
            @PathVariable Long categoriaId) {

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        producto.getCategorias().add(categoria);
        productoRepository.save(producto);

        return ResponseEntity.ok(producto);
    }
}

