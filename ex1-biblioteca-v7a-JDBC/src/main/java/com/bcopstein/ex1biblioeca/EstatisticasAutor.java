package com.bcopstein.ex1biblioeca;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class EstatisticasAutor {
    private Map<String,Integer> contAutor;

    public EstatisticasAutor(){
        contAutor = new HashMap<>();
    }

    public void informaConsultaAutor(String autor){
        contAutor.put(autor, contAutor.getOrDefault(autor, 0) + 1);
    }

    public String autorMaisConsultado(){
        return contAutor.entrySet()
                .stream()
                .max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public String autorMenosConsultado(){
        return contAutor.entrySet()
                .stream()
                .min((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
