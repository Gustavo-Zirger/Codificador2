package com.bcopstein.ex3_sist_vendas_hexagonal.infra.adapters.out.persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoSpringDataRepository extends JpaRepository<PedidoJpaEntity, Long> {
    // A anotação @EntityGraph garante que os itens serão 
    // carregados junto com o pedido (eager fetch). Sem isso o 
    // padrão é FetchType.LAZY que implica que os itens só são 
    // carregados a medida que são consultados gerando excesso de
    // consultas no banco (problema N+1)
    @Override
    @EntityGraph(attributePaths = { "itens", "itens.produto" })
    Optional<PedidoJpaEntity> findById(Long id);

    @Override
    @EntityGraph(attributePaths = { "itens", "itens.produto" })
    List<PedidoJpaEntity> findAll();
}