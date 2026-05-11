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


passageiro | fileira | assento | resultado
           |         |         |    
    01     |   01    |   01    | aprovado(3)
    01     |   21    |   19    | inválido(2)
    01     |   01    |   21    | inválido(0)   
   101     |   41    |   01    | aprovado(3)   
   101     |   01    |   19    | inválido(2)   
   101     |   41    |   21    | inválido(0)  
   201     |   31    |   01    | aprovado(3)   
   201     |   01    |   19    | aprovado(3)     
   201     |   41    |   19    | aprovado(3)   
   201     |   41    |   21    | inválido(0)
  1201     |   41    |   21    | inválido(1)
 */

public class BarcaTest {
    @Test
    @DisplayName("Verifica se ta ocupado")
    @ParameterizedTest
    @CsvSource({
        "F01A02, 3",
        "F02A01, 3",
        "F02A02, 3"
    })
    void testOcupaLugar(String assento, int expectedResult) {
        Barca barca = new Barca();
        barca.ocupaLugarSemVerificacao(01, 01); // Assento F01A01 ocupado
        assertEquals(expectedResult, barca.ocupaLugar(assento)); // Verifica se o resultado é 1 (assento ocupado)
    }
}
