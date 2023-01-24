package com.example.demo.controller;

import com.example.demo.model.Leilao;
import com.example.demo.service.LanceService;
import com.example.demo.dto.NovoLanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/lances")
public class LanceController {

    @Autowired
    private LanceService service;

    @PostMapping
    public ResponseEntity<String> novoLance(@RequestBody NovoLanceDto lanceDto) {
        Leilao leilao = service.getLeilao(lanceDto.getLeilaoId());

        boolean resultadoDoLance = service.propoeLance(lanceDto, "Arthur");
        if (!resultadoDoLance) throw new IllegalArgumentException("Erro: Lance invalido");

        return ResponseEntity.ok("Lance adicionado com sucesso!");
    }
}
