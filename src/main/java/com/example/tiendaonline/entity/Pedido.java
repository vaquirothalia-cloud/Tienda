package com.example.tiendaonline.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();

    private double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemPedido> items = new ArrayList<>();

    @Column(nullable = false)
    private String estado = "CREADO";

    public Pedido(){}

    public Long getId(){return id;}
    public LocalDateTime getFecha(){return fecha;}
    public double getTotal(){return total;}
    public void setTotal(double total){this.total=total;}
    public Cliente getCliente(){return cliente;}
    public void setCliente(Cliente cliente){this.cliente=cliente;}
    public List<ItemPedido> getItems(){return items;}
    public void setItems(List<ItemPedido> items){this.items=items;}
    public String getEstado(){return estado;}
    public void setEstado(String estado){this.estado=estado;}
    public void recalcTotal(){
        this.total = items.stream().mapToDouble(it -> it.getPrecioUnitario().multiply(java.math.BigDecimal.valueOf(it.getCantidad())).doubleValue()).sum();
    }
}
