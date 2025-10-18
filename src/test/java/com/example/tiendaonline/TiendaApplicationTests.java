package com.example.tiendaonline;

import com.example.tiendaonline.entity.Cliente;
import com.example.tiendaonline.entity.Direccion;
import com.example.tiendaonline.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TiendaApplicationTests {
    @Autowired private ClienteRepository clienteRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void crearClienteConDireccion() {
        Cliente c = new Cliente();
        c.setNombre("Thalia Test");
        c.setEmail("thalia_test@example.com");
        Direccion d = new Direccion();
        d.setCalle("Calle Test");
        d.setCiudad("Neiva");
        d.setPais("CO");
        d.setZip("111111");
        c.setDireccion(d);
        Cliente saved = clienteRepository.save(c);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getDireccion()).isNotNull();
    }
}
