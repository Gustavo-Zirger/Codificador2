package gustavo.enrico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/*casos de teste:

passageiros: 

[0,100] - sentam na frente 1 a 20 
[101,200] - sentam atrás 40 a 60
[201,1200] - sentam em qualquer lugar

fileira: 

[1,60]

assentos:

[1,20]


* Retorna: 
     * 0 – Identificador de assento inválido 
     * 1 – Assento ocupado 
     * 2 – Assento bloqueado devido a distribuição de peso 
     * 3 – Ok, assento atribuído ao passageiro. 
     * 


    peso   | fileira | assento | resultado
           |         |         |    
     1     |   01    |   01    | inválido(1)
     1     |   00    |   01    | inválido(0)
     1     |   01    |   21    | inválido(0)
     1     |   04    |   01    | aprovado(3)
     1     |   21    |   01    | inválido(2)

     2     |   40    |   01    | inválido(1)
     2     |   61    |   01    | inválido(0)
     2     |   40    |   21    | inválido(0)
     2     |   44    |   01    | aprovado(3)
     2     |   05    |   01    | inválido(2)

     3     |   33    |   01    | aprovado(3)
     3     |   09    |   01    | aprovado(3)
     3     |   45    |   01    | aprovado(3)
 */

public class BarcaTest {
    @DisplayName("casos primeira distribuição de peso")
    @ParameterizedTest
    @CsvSource({ 
        "F01A01, 1",
        "F00A01, 0",
        "F01A21, 0",
        "F04A01, 3",
        "F21A01, 2"
    })
    void primeiroPeso(String lugar, int esperado) {
        Barca barca = new Barca();
        for(int f = 1; f <= 3; f++){ // cont 60 fileira 1-3 cheia
            for(int a = 1; a <= 20; a++){
                barca.ocupaLugarSemVerificacao(f,a);
            }
        }
        assertEquals(esperado, barca.ocupaLugar(lugar));
    }

    @DisplayName("casos segunda distribuição de peso")
    @ParameterizedTest
    @CsvSource({ 
        "F40A01, 1",
        "F61A01, 0",
        "F40A21, 0",
        "F44A01, 3",
        "F05A01, 2"
    })
    void segundoPeso(String lugar, int esperado) {
        Barca barca = new Barca();
        for(int f = 1; f <= 5; f++){ // cont 100 fileira 1-5 cheia
            for(int a = 1; a <= 20; a++){
                barca.ocupaLugarSemVerificacao(f,a);
            }
        }
        for(int f = 40; f <= 43; f++){ // cont 160 fileira 40-43 cheia
            for(int a = 1; a<=20; a++){
                barca.ocupaLugarSemVerificacao(f,a);
            }
        }
        assertEquals(esperado, barca.ocupaLugar(lugar));
    }

    @DisplayName("casos terceira distribuição de peso")
    @ParameterizedTest
    @CsvSource({ 
        "F33A01, 3",
        "F09A01, 3",
        "F46A01, 3"
    })
    void terceiroPeso(String lugar, int esperado) {
        Barca barca = new Barca();
        for(int f = 1; f <= 5; f++){ // cont 100 fileira 1-5 cheia
            for(int a = 1; a <= 20; a++){
                barca.ocupaLugarSemVerificacao(f,a);
            }
        }
        for(int f = 40; f <= 45; f++){ // cont 200 fileira 40-45 cheia
            for(int a = 1; a <= 20; a++){
                barca.ocupaLugarSemVerificacao(f,a);
            }
        }
        assertEquals(esperado, barca.ocupaLugar(lugar));
    }

    @Test
    @DisplayName("Caso lotado")
    void lotado(){
        Barca barca = new Barca();
        for(int f = 1; f<61;f++){
            for(int a = 1; a<21; a++){
                barca.ocupaLugarSemVerificacao(f, a);
            }
        }
        int resultado = barca.ocupaLugar("F60A20");
        assertEquals(1,resultado);
    }
}
