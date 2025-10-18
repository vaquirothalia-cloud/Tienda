package com.example.tiendaonline.controller;

import com.example.tiendaonline.entity.Categoria;
import com.example.tiendaonline.entity.Producto;
import com.example.tiendaonline.repository.CategoriaRepository;
import com.example.tiendaonline.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaController(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    // 🟢 Crear categoría
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    // 🟢 Listar todas
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // 🟢 Asociar producto a categoría
    @PostMapping("/{categoriaId}/productos/{productoId}")
    public ResponseEntity<?> asociarProducto(@PathVariable Long categoriaId, @PathVariable Long productoId) {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow();
        Producto producto = productoRepository.findById(productoId).orElseThrow();
        producto.getCategorias().add(categoria);
        productoRepository.save(producto);
        return ResponseEntity.ok("Producto asociado a categoría correctamente ✅");
    }

    // 🟢 Listar productos por categoría
    @GetMapping("/{categoriaId}/productos")
    public ResponseEntity<List<Producto>> listarProductosPorCategoria(@PathVariable Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow();
        return ResponseEntity.ok(new ArrayList<>(categoria.getProductos()));
    }

}

