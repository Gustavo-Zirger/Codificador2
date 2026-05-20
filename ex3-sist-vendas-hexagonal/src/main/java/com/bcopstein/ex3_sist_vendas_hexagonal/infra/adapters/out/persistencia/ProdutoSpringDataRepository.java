package com.bcopstein.ex3_sist_vendas_hexagonal.infra.adapters.out.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoSpringDataRepository extends JpaRepository<ProdutoJpaEntity, Long> {
}