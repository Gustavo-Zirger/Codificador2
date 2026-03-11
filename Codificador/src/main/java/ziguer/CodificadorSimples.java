package ziguer;

import java.time.LocalDate;

public class CodificadorSimples implements Codificador {
    public String getNome() {
        return "Codificador Simples";
    }

    public LocalDate getDataCriacao() {
        return LocalDate.of(2026, 03, 9);
    }

    public int getNivelSeguranca(){
        return 2;
    }

    public String codifica(String str) {
        StringBuilder encoded = new StringBuilder();
        int sum = 1;

        for (char c : str.toCharArray()) {
            encoded.append((char) (c + (str.length()-sum)));
            sum++;
        }

        return encoded.toString();
    }

    public String decodifica(String str) {
        StringBuilder encoded = new StringBuilder();
        int sum = 1;
        
        for (char c : str.toCharArray()) {
            encoded.append((char) (c - (str.length()-sum)));
            sum++;
        }
        
        return encoded.toString();
    }
}