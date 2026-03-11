package ziguer;

import java.time.LocalDate;

public class CodificadorSimples implements Codificador {
    public String getNome() {
        return "Codificador Enrico";
    }

    public LocalDate getDataCriacao() {
        return LocalDate.of(2025, 03, 09);
    }

    public int getNivelSeguranca(){
        return 5;
    }

    public String codifica(String str) {
        StringBuilder encoded = new StringBuilder();
        char i = "a";
        char f = "z";
        
        for (char c : str.toCharArray()) {
            encoded.append((char) (f - (c - i)));
        }

        return encoded.toString();
    }

    public String decodifica(String str) {
        StringBuilder encoded = new StringBuilder();
        
        for (char c : str.toCharArray()) {
            encoded.append((char) (f + (c + i)));
        }
        
        return encoded.toString();
    }
}