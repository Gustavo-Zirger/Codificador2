package gustavo.enrico;

public class Barca { 

    int[][] lugares;
    int cont;

    public Barca(){ 
        this.lugares = new int[60][20];
        cont = 0;
    } 

 

    // Método auxiliar projetado para facilitar testes (montagem de cenário) 
    // Ocupa o lugar sem verificação, isto é, simplesmente ocupa o lugar 
    // sem validar nenhuma das regras de ocupação 
    public void ocupaLugarSemVerificacao(int fila, int assento){
        lugares[fila-1][assento-1] = 1;
        cont++; 
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
        //F01A01 F[0] 0[1] 1[2] A[3] 0[4] 1[5]
        // char[] partes = assentoInformado.toCharArray();
        // int fileira = Character.getNumericValue(partes[1]) * 10 + Character.getNumericValue(partes[2]);
        // int assento = Character.getNumericValue(partes[4]) * 10 + Character.getNumericValue(partes[5]);

        int fileira = Integer.parseInt(assentoInformado.substring(1, 3));
        int assento = Integer.parseInt(assentoInformado.substring(4));


        if(assentoInformado.substring(0,1).equals("F") && assentoInformado.substring(3,4).equals("A") && fileira > 0 && fileira <= 60 && assento > 0 && assento <= 20){

            if(cont <= 100){
                if(fileira <= 20){
                    if(lugares[fileira-1][assento-1] == 0){
                        lugares[fileira-1][assento-1] = 1;
                        cont++;
                        return 3; // assento atribuído ao passageiro
                    }
                    else{
                        return 1; // assento ocupado
                    }
                }
                else{
                    return 2; // bloqueado distribuição de peso
                } 
            }
            else if(cont > 100 && cont <= 200){
                if(fileira >= 40 && fileira <= 60){
                    if(lugares[fileira-1][assento-1] == 0){
                        lugares[fileira-1][assento-1] = 1;
                        cont++;
                        return 3; // assento atribuído ao passageiro
                    }
                    else{
                        return 1; // assento ocupado
                    }
                }
                else{
                    return 2; // bloqueado distribuição de peso
                } 
            }
            else{
                if(cont < 1200){
                    if(lugares[fileira-1][assento-1] == 0){
                        lugares[fileira-1][assento-1] = 1;
                        cont++;
                        return 3; // assento atribuído ao passageiro
                    }
                    else{
                        return 1; // assento ocupado
                    }                
                }
                else{
                    return 1; // assento ocupado
                }
            }
        }
        return 0; // assento inválido
    }
} 