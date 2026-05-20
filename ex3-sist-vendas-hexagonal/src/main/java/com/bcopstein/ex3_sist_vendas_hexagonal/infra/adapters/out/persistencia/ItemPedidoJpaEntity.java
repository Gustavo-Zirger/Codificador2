package com.bcopstein.ex3_sist_vendas_hexagonal.infra.adapters.out.persistencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedidoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoJpaEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoJpaEntity produto;

    @Column(name = "preco_produto_considerado", nullable = false)
    private double precoProdutoConsiderado;

    @Column(nullable = false)
    private int quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoJpaEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoJpaEntity pedido) {
        this.pedido = pedido;
    }

    public ProdutoJpaEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoJpaEntity produto) {
        this.produto = produto;
    }

    public double getPrecoProdutoConsiderado() {
        return precoProdutoConsiderado;
    }

    public void setPrecoProdutoConsiderado(double precoProdutoConsiderado) {
        this.precoProdutoConsiderado = precoProdutoConsiderado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}