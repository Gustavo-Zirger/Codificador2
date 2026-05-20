package com.bcopstein.ex3_sist_vendas_hexagonal.infra.adapters.out.persistencia;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.Produto;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.portas.out.repositorios.RepositorioProdutos;

@Repository
public class RepositorioProdutosJPA implements RepositorioProdutos {

    private final ProdutoSpringDataRepository produtoSpringDataRepository;

    public RepositorioProdutosJPA(ProdutoSpringDataRepository produtoSpringDataRepository) {
        this.produtoSpringDataRepository = produtoSpringDataRepository;
    }

    @Override
    public Produto recuperaProduto(long id) {
        ProdutoJpaEntity entity = produtoSpringDataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado: " + id));
        return toDomain(entity);
    }

    @Override
    public Produto persiste(Produto produto) {
        ProdutoJpaEntity salvo = produtoSpringDataRepository.save(toEntity(produto));
        return toDomain(salvo);
    }

    @Override 
    public List<Produto> recuperaTodos(){
        return produtoSpringDataRepository.findAll()
                .stream()
                .map(produtoJpaEntity->toDomain(produtoJpaEntity))
                .toList();
    }

    private ProdutoJpaEntity toEntity(Produto produto) {
        ProdutoJpaEntity entity = new ProdutoJpaEntity();
        entity.setId(produto.getId());
        entity.setDescricao(produto.getDescricao());
        entity.setPreco(produto.getPreco());
        return entity;
    }

    private Produto toDomain(ProdutoJpaEntity entity) {
        return new Produto(entity.getId(), entity.getDescricao(), entity.getPreco());
    }
}