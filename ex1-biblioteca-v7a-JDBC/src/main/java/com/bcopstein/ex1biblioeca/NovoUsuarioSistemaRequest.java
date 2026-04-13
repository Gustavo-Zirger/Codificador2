package com.bcopstein.ex1biblioeca;

public record NovoUsuarioSistemaRequest(String username, String senha, String perfil) {
    
    public String perfilNormalizado(){
        if(perfil == null || perfil.isBlank()){
            return "CONSULTA";
        }
        var perfilEmMaiusculo = perfil.trim().toUpperCase();
        if("ADMIN".equals(perfilEmMaiusculo)){
            return "ADMIN";
        }
        return "CONSULTA";
    }
}
