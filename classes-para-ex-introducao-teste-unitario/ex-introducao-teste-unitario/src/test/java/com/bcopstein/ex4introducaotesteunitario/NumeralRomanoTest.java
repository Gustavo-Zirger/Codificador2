package com.bcopstein.ex4introducaotesteunitario;

import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NumeralRomanoTest {

@BeforeEach
 void setUp(){
    NumeralRomano romano = new NumeralRomano();
}

@Test
void testeSum(){
    int rEsp = 1;
    String rObj = romano.converter("I");
    assertEquals(rEsp, rObj);
    }
}
