package com.example.tiendaonline.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String nombre;
    @Column(nullable=false, unique=true)
    private String email;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Direccion direccion;


    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos;


    public Cliente(){}

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public Direccion getDireccion(){return direccion;}
    public void setDireccion(Direccion d){ this.direccion=d; if(d!=null) d.setCliente(this); }
    public List<Pedido> getPedidos(){return pedidos;}
}
