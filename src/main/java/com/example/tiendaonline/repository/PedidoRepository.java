package com.example.tiendaonline.repository;

import com.example.tiendaonline.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
