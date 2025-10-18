package com.example.tiendaonline.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

    public Categoria(){}
    public Long getId(){return id;}
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public Set<Producto> getProductos(){return productos;}
}
