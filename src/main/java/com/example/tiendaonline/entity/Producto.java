package com.example.tiendaonline.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String nombre;
    @Column(nullable=false)
    private BigDecimal precio;
    @Column(nullable=false)
    private Integer stock;

    @ManyToMany
    @JoinTable(name="producto_categoria",
        joinColumns = @JoinColumn(name="producto_id"),
        inverseJoinColumns = @JoinColumn(name="categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();

    public Producto(){}
    public Long getId(){return id;}
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public BigDecimal getPrecio(){return precio;}
    public void setPrecio(BigDecimal precio){this.precio=precio;}
    public Integer getStock(){return stock;}
    public void setStock(Integer stock){this.stock=stock;}
    public Set<Categoria> getCategorias(){return categorias;}
}
