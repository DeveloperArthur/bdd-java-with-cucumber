package com.example.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Lance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private LocalDate data;
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Leilao leilao;

    @Deprecated
    public Lance() {}

    public Lance(BigDecimal valor) {
        this.valor = valor;
    }

    public Lance(Usuario usuario, BigDecimal valor) {
        if (valor.doubleValue() <= 0) {
            throw new IllegalArgumentException();
        }
        this.usuario = usuario;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }
}
