package br.edu.ifpr.cars.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculadoraTest {


    @Test
    public void testeSomar() {
        Calculadora cal = new Calculadora();
        int result = cal.somar(2, 5);
        assertEquals(7, result);

    }

}
