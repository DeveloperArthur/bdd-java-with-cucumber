package com.example.demo.unit;

import com.example.demo.model.Lance;
import com.example.demo.model.Usuario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LanceTest {
    @Test
    public void deveRecusarLancesComValorDeZero() {
        assertThrows(IllegalArgumentException.class, () -> new Lance(new Usuario("John Doe"), BigDecimal.ZERO));
    }

    @Test
    public void deveRecusarLancesComValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Lance(new Usuario("John Doe"), new BigDecimal("-10")));
    }
}
