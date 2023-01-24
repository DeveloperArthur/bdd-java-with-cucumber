package com.example.demo.dto;

import com.example.demo.model.Lance;
import com.example.demo.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLanceDto {
    private BigDecimal valor;
    private Long leilaoId;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getLeilaoId() {
        return leilaoId;
    }

    public void setLeilaoId(Long leilaoId) {
        this.leilaoId = leilaoId;
    }

    public Lance toLance(Usuario usuario) {
        Lance lance = new Lance(usuario, valor);
        lance.setData(LocalDate.now());
        return lance;
    }
}
