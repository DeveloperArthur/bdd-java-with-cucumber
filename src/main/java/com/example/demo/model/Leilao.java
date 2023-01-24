package com.example.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Leilao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valorInicial;
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    private LocalDate dataAbertura;
    @OneToMany(mappedBy = "leilao")
    private List<Lance> lances = new ArrayList<>();

    @Deprecated
    public Leilao() {
    }

    public Leilao(String nome) {
        this.nome = nome;
    }

    public Leilao(String nome, BigDecimal valorInicial, LocalDate dataAbertura) {
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.dataAbertura = dataAbertura;
    }


    public Leilao(String nome, BigDecimal valorInicial, Usuario usuario) {
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.usuario = usuario;
        this.dataAbertura = LocalDate.now();
    }

    public Leilao(String nome, BigDecimal valorInicial, LocalDate data, Usuario usuario) {
        this.nome = nome;
        this.valorInicial = valorInicial;
        this.usuario = usuario;
        this.dataAbertura = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    public boolean propoe(Lance lanceAtual) {
        if (!valorDoLanceMaiorQueZero(lanceAtual)) return false;
        else if (this.estaSemLances() || ehUmLanceValido(lanceAtual)) {
            adicionarLance(lanceAtual);
            return true;
        }
        return false;
    }

    private void adicionarLance(Lance lance) {
        lances.add(lance);
        lance.setLeilao(this);
    }

    private boolean ehUmLanceValido(Lance lance) {
        return valorEhMaior(lance, ultimoLanceDado()) &&
                oUltimoUsuarioNaoEhOMesmoDo(lance) &&
                totalDeLancesDoUsuarioEhMenorIgual5(lance.getUsuario());
    }

    private boolean valorDoLanceMaiorQueZero(Lance lance) {
        return lance.getValor().compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean valorEhMaior(Lance lance, Lance ultimoLanceDado) {
        return lance.getValor().compareTo(ultimoLanceDado.getValor()) > 0;
    }


    private boolean totalDeLancesDoUsuarioEhMenorIgual5(Usuario usuario) {
        int totalDeLances = qtdDeLancesDo(usuario);
        return totalDeLances < 5;
    }

    private boolean oUltimoUsuarioNaoEhOMesmoDo(Lance lance) {
        Usuario ultimoUsuarioQueDeuLance = ultimoLanceDado().getUsuario();
        return !ultimoUsuarioQueDeuLance.equals(lance.getUsuario());
    }

    private int qtdDeLancesDo(Usuario usuario) {
        int total = 0;
        for (Lance l : lances) {
            if (l.getUsuario().equals(usuario))
                total++;
        }
        return total;
    }

    private boolean estaSemLances() {
        return this.lances.isEmpty();
    }

    private Lance ultimoLanceDado() {
        return lances.get(lances.size() - 1);
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

    public boolean temLances() {
        return !this.lances.isEmpty();
    }
}
