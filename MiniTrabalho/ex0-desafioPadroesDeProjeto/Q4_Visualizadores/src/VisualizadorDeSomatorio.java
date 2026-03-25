import java.util.List;

public class VisualizadorDeSomatorio {
    private List<Integer> valores;

    public VisualizadorDeSomatorio(List<Integer> valores){
        this.valores = valores;
    }

    public void defineValores(List<Integer> valores){
        this.valores = valores;
    }

    public void acrescentaValor(Integer valor){
        this.valores.add(valor);
    }

    public void exibeSomatorio(){
        Integer soma = valores.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Somatorio: "+soma+", quantidade de elementos analisados: "+valores.size());
    }
}
