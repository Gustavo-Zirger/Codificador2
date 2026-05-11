package gustavo.enrico;

public class Barca { 

    int[][] lugares;
    int cont;

    public Barca(){ 
        lugares = new int[60][20];
        cont = 0;
    } 

 

    // Método auxiliar projetado para facilitar testes (montagem de cenário) 
    // Ocupa o lugar sem verificação, isto é, simplesmente ocupa o lugar 
    // sem validar nenhuma das regras de ocupação 
    public void ocupaLugarSemVerificacao(int fila, int assento){ 
        lugares[fila][assento] = 1;
    } 

 

    /* 

     * Retorna: 
     * 0 – Identificador de assento inválido 
     * 1 – Assento ocupado 
     * 2 – Assento bloqueado devido a distribuição de peso 
     * 3 – Ok, assento atribuído ao passageiro. 
     * 
     * casos de teste:
        [0,100] - sentam na frente 1 a 20
        [101,200] - sentam atrás 40 a 60
        [201,1200] - sentam em qualquer lugar
     */ 

    public int ocupaLugar(String assentoInformado){ 
        char[] partes = assentoInformado.toCharArray();
        int fileira = Character.getNumericValue(partes[1]) * 10 + Character.getNumericValue(partes[2]);
        int assento = Character.getNumericValue(partes[4]) * 10 + Character.getNumericValue(partes[5]);
        
        if(partes[0] == 'F' && partes[3] == 'A' && fileira > 0 && fileira <= 60 && assento > 0 && assento <= 20){

            if(cont < 100){
                if(fileira <= 20 && assento <= 20){
                    {

                }
            }
            
            }

        }
        return 0; // assento inválido
    }

    public static void main(String[] args){
        Barca barca = new Barca();
        System.out.println("teste");
        System.out.println(barca.ocupaLugar("F0129"));
    }
} 