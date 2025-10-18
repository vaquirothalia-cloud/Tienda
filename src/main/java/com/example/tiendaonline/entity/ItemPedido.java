package com.example.tiendaonline.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items_pedido", uniqueConstraints = @UniqueConstraint(name = "uk_pedido_producto", columnNames = {"pedido_id","producto_id"}))
public class ItemPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private BigDecimal precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public ItemPedido(){}
    public Long getId(){return id;}
    public Integer getCantidad(){return cantidad;}
    public void setCantidad(Integer cantidad){this.cantidad=cantidad;}
    public java.math.BigDecimal getPrecioUnitario(){return precioUnitario;}
    public void setPrecioUnitario(java.math.BigDecimal p){this.precioUnitario=p;}
    public Pedido getPedido(){return pedido;}
    public void setPedido(Pedido p){this.pedido=p;}
    public Producto getProducto(){return producto;}
    public void setProducto(Producto p){this.producto=p;}
}
