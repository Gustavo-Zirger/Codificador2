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
     1     |   01    |   01    | aprovado(3)
     1     |   21    |   19    | inválido(2)
     1     |   01    |   21    | inválido(0)
     1     |   21    |   19    | inválido(2)
     1     |   01    |   21    | inválido(0)     
     2     |   41    |   01    | aprovado(3)   
     2     |   01    |   19    | inválido(2)   
     2     |   41    |   21    | inválido(0)  
     3     |   31    |   01    | aprovado(3)   
     3     |   01    |   19    | aprovado(3)     
     3     |   41    |   19    | aprovado(3)   
     3     |   41    |   21    | inválido(0)
     3     |   41    |   21    | inválido(1)
 */

public class BarcaTest {
    @Test
    @DisplayName("casos primeira distribuição de peso")
    @ParameterizedTest
    @CsvSource({ // "lugar, valor experado"
        "F01A01, 1",
        "F00A01, 0"
    })
    void testOcupaLugar(String lugar, int esperado) {
        Barca barca = new Barca();
        for(int f = 1; f <=3; f++){ // cont 60 fileira 1-3 cheia
            for(int a = 1; a<=20; a++){
                barca.ocupaLugarSemVerificacao(f,a);
            }
        }
        assertEquals(esperado, barca.ocupaLugar(lugar));
    }
}
