package com.example.tiendaonline.repository;

import com.example.tiendaonline.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findByCategoriasNombre(String nombre, Pageable pageable);
}
