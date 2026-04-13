package com.bcopstein.ex1biblioeca;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Long> {
    Optional <UsuarioSistema> findByUsername(String username);
    boolean existsByUsername(String username);
    
}

