package com.bcopstein.ex3_sist_vendas_hexagonal.infra.adapters.out.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.ItemPedido;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.Pedido;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.StatusPedido;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.portas.out.repositorios.RepositorioPedidos;

@Repository
public class RepositorioPedidosJPA implements RepositorioPedidos {

    private final PedidoSpringDataRepository pedidoSpringDataRepository;
    private final ProdutoSpringDataRepository produtoSpringDataRepository;

    public RepositorioPedidosJPA(PedidoSpringDataRepository pedidoSpringDataRepository,
            ProdutoSpringDataRepository produtoSpringDataRepository) {
        this.pedidoSpringDataRepository = pedidoSpringDataRepository;
        this.produtoSpringDataRepository = produtoSpringDataRepository;
    }

    @Override
    public Pedido persiste(Pedido pedido) {
        PedidoJpaEntity entity = toEntity(pedido);
        PedidoJpaEntity salvo = pedidoSpringDataRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public Pedido recuperaPedido(long id) {
        PedidoJpaEntity entity = pedidoSpringDataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido nao encontrado: " + id));
        return toDomain(entity);
    }

    @Override
    public List<Pedido> todosPedidos() {
        return pedidoSpringDataRepository.findAll()
            .stream()
            .map(entity->toDomain(entity))
            .toList();
    }

    private PedidoJpaEntity toEntity(Pedido pedido) {
        PedidoJpaEntity entity = new PedidoJpaEntity();

        // id=0 no dominio significa pedido novo sem identificador atribuido.
        if (pedido.getId() > 0) {
            entity.setId(pedido.getId());
        }

        entity.setData(pedido.getData());
        entity.setStatus(pedido.getStatus());
        entity.setEstado(pedido.getEstado());
        entity.setImposto(pedido.getImposto());
        entity.setDesconto(pedido.getDesconto());
        entity.setValorFinal(pedido.getValorFinal());

        List<ItemPedidoJpaEntity> itens = pedido.getItens()
                .map(item -> {
                    ItemPedidoJpaEntity itemEntity = new ItemPedidoJpaEntity();
                    itemEntity.setPedido(entity);
                    itemEntity.setProduto(recuperaProdutoEntity(item.getProdutoId()));
                    itemEntity.setPrecoProdutoConsiderado(item.getPrecoProdutoConsiderado());
                    itemEntity.setQuantidade(item.getQuantidade());
                    return itemEntity;
                })
                .toList();

        entity.setItens(new ArrayList<>(itens));
        return entity;
    }

    private ProdutoJpaEntity recuperaProdutoEntity(long produtoId) {
        return produtoSpringDataRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado para o item: " + produtoId));
    }

    private Pedido toDomain(PedidoJpaEntity entity) {
        Pedido pedido = new Pedido(entity.getId(), entity.getData(), entity.getEstado());

        // Garante que eventuais ações de defineStatus são repetidas
        // Este é um padrão essencial em arquitetura hexagonal + DDD: 
        // sempre use métodos de comportamento da entidade de domínio, 
        // nunca atribua estado diretamente.
        switch (entity.getStatus()) {
            case RESERVADO -> pedido.defineStatus(StatusPedido.RESERVADO);
            case CONFIRMADO -> {
                pedido.defineStatus(StatusPedido.RESERVADO);
                pedido.defineStatus(StatusPedido.CONFIRMADO);
            }
            case CANCELADO -> pedido.defineStatus(StatusPedido.CANCELADO);
            case NOVO -> {
            }
        }

        for (ItemPedidoJpaEntity itemEntity : entity.getItens()) {
            pedido.acrescentarItem(new ItemPedido(
                itemEntity.getProduto().getId(),
                itemEntity.getQuantidade(),
                itemEntity.getPrecoProdutoConsiderado()));
        }

        pedido.calculaCusto(entity.getImposto(), entity.getDesconto());

        return pedido;
    }
}