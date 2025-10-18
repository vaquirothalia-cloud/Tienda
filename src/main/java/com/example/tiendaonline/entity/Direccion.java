package com.example.tiendaonline.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private String ciudad;
    private String pais;
    private String zip;

    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    @JsonBackReference
    private Cliente cliente;

    public Direccion(){}

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getCalle(){return calle;}
    public void setCalle(String calle){this.calle=calle;}
    public String getCiudad(){return ciudad;}
    public void setCiudad(String ciudad){this.ciudad=ciudad;}
    public String getPais(){return pais;}
    public void setPais(String pais){this.pais=pais;}
    public String getZip(){return zip;}
    public void setZip(String zip){this.zip=zip;}
    public Cliente getCliente(){return cliente;}
    public void setCliente(Cliente c){this.cliente=c;}
}
