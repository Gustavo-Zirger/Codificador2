package gustavo.enrico;
/**
 * Grupo 13: Milena Schultz
 */
public class Barca {

    private int qtdPassageiros;
    private static final int TOTALFILEIRAS = 60;
    private static final int TOTALASSENTOS = 20;  
    private final boolean [][]assentos;

    public Barca() {
        this.assentos = new boolean [TOTALFILEIRAS][TOTALASSENTOS];
        this.qtdPassageiros = 0;
    }
    /**
     * verifica se um assento especifico esta ocupado
     * @param assento
     * @param fila
     * @return
     */
    public boolean isOcupado(int assento, int fila) {
        //troca para o indice 0 do java para nao dar erro de logica
        int f = fila - 1;
        int a = assento - 1;
        // alterei o <= era <
        if (f >= 0 && f <= TOTALFILEIRAS && a >= 0 && a <= TOTALASSENTOS){
            return assentos[f][a];
        }
        return true;
    }
    /**
     * define as condicoes de distribuicao de peso
     * @param assento
     * @param fila
     * @return
     */
    private boolean distribuicaoPeso(int fileira) {
        if (qtdPassageiros >= 0 && qtdPassageiros <= 99) {
            return fileira >= 1 && fileira <= 20;
        }
        if (qtdPassageiros >= 100 && qtdPassageiros <= 199) {
            return fileira >= 40 && fileira <= 60;
        }
        return true;
    }
   /**
    * metodo auxiliar projetado para facilitar testes (montagem de cenario)
    * ocupa o lugar sem verificacao, isto eh, simplesmente ocupa o lugar 
    * sem validar nenhuma das regras de ocupacao
    * @param fila
    * @param assento
    */
    public void ocupaLugarSemVerificacao(int fila, int assento) {
        int f = fila - 1;
        int a = assento - 1;
        // alterei o <= era <
        if (f >= 0 && f <= 60 && a >= 0 && a <= 20) { 
            if(!assentos[f][a]) {
                assentos[f][a] = true;
                this.qtdPassageiros++;
            }
        }
    }
    /**
     * retorna:
     * 0 - identificador de assento invalido
     * 1 - assento ocupado
     * 2 - assento bloqueado devido a distribuicao de peso
     * 3 - ok, assento atribuido ao passageiro
     * @param assentoInformado
     * @return
     */
    public int ocupaLugar(String assentoInformado) {
        if (assentoInformado == null || assentoInformado.length() != 6) {
            return 0;
        }
        try {
            int f = Integer.parseInt(assentoInformado.substring(1, 3));
            int a = Integer.parseInt(assentoInformado.substring(4, 6));

            if (f < 1 || f > 60 || a < 1 || a >20) {
                return 0;
            }
            if (!distribuicaoPeso(f)) {
                return 2;
            }
            if (isOcupado(f, a)) {
                return 1;
            }
            
            this.assentos[f - 1][a - 1] = true;
            this.qtdPassageiros++;
            return 3;

        } catch (NumberFormatException e) {
            return 0;
        }
    }
    /**
     * 
     * @param f
     * @param a
     * @return
     */
    public String formataAssento(int f, int a) {
        return String.format("F%02dA%02d", f, a);
    }
}