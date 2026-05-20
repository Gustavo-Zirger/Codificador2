package com.bcopstein.ex3_sist_vendas_hexagonal.infra.adapters.out.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.EstadoBrasil;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class PedidoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoBrasil estado;

    @Column(nullable = false)
    private double imposto;

    @Column(nullable = false)
    private double desconto;

    @Column(name = "valor_final", nullable = false)
    private double valorFinal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoJpaEntity> itens = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public EstadoBrasil getEstado() {
        return estado;
    }

    public void setEstado(EstadoBrasil estado) {
        this.estado = estado;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public List<ItemPedidoJpaEntity> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoJpaEntity> itens) {
        this.itens = itens;
    }
}