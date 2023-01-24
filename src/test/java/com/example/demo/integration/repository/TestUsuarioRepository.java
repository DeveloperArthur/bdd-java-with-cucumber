package com.example.demo.integration.repository;

import com.example.demo.model.Usuario;
import com.example.demo.service.LanceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

@DataJpaTest
@Import(LanceService.class)
public class TestUsuarioRepository {
    //Quando queremos testar a camada de repository e o banco de dados

    @Autowired
    LanceService lanceService;

    @BeforeEach
    public void setup(){
        lanceService.salvaUsuario(new Usuario("Arthur", "arthur@arthur.com", String.valueOf(UUID.randomUUID())));
    }

    @Test
    public void deveBuscarUsuarioPeloNome(){
        Usuario user = lanceService.buscaUsuario("Arthur");
        Assertions.assertEquals(user.getEmail(), "arthur@arthur.com");
    }
}
