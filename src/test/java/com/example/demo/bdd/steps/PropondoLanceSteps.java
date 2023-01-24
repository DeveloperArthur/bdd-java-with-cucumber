package com.example.demo.bdd.steps;

import com.example.demo.model.Lance;
import com.example.demo.model.Leilao;
import com.example.demo.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropondoLanceSteps {
    private Lance lance;
    private Leilao leilao;
    private List<Lance> lances;

    @Before
    public void setup(){
        this.lances = new ArrayList<Lance>();
        leilao = new Leilao("Tablet XPTO");
    }

    @After
    public void after(){
        //System.out.println("After");
    }

    @Dado("um lance valido")
    public void dado_um_lance_valido() {
        Usuario usuario = new Usuario("Arthur");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @Quando("propoe ao leilao")
    public void quando_propoe_ao_leilao() {
        leilao.propoe(lance);
    }

    @Entao("o lance eh aceito")
    public void entao_o_lance_eh_aceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }

    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario(Double valor, String nomeUsuario) {
        Usuario usuario = new Usuario(nomeUsuario);
        Lance lance = new Lance(usuario, new BigDecimal(valor));
        lances.add(lance);
    }

    @Quando("propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        leilao = new Leilao("Tablet XPTO");
        lances.forEach(lance -> { leilao.propoe(lance); });
    }

    @Entao("os lances sao aceitos")
    public void os_lances_sao_aceitos() {
        Assert.assertEquals(2, leilao.getLances().size());
        Assert.assertEquals(lances.get(0).getValor(), leilao.getLances().get(0).getValor());
        Assert.assertEquals(lances.get(1).getValor(), leilao.getLances().get(1).getValor());
    }

    @Dado("um lance de {double} reais e do usuario {string}")
    public void um_lance_de_reais(Double valor, String nome) {
        //System.out.println(nome);
        this.lance = new Lance(new BigDecimal(valor));
    }

    @Entao("o lance nao eh aceito")
    public void o_lance_nao_eh_aceito() {
        Assert.assertEquals(0, leilao.getLances().size());
    }

    @Entao("o segundo lance nao eh aceito")
    public void o_segundo_lance_nao_eh_aceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(lances.get(0).getValor(), leilao.getLances().get(0).getValor());
    }

    @Dado("dois lances")
    public void dois_lances(DataTable dataTable) {
        List<Map<String, String>> valores = dataTable.asMaps();
        for (Map<String, String> mapa : valores){
            String valor = mapa.get("valor");
            String nome = mapa.get("nome");
            Lance lance = new Lance(new Usuario(nome), new BigDecimal(valor));
            lances.add(lance);
        }
    }
}
