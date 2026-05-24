package com.bcopstein.ex3_sist_vendas_hexagonal.domain.portas.out.repositorios;

import java.time.LocalDate;
import java.util.List;

import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.Pedido;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.StatusPedido;

public interface RepositorioPedidos {
    Pedido persiste(Pedido pedido);
    Pedido recuperaPedido(long id);
    List<Pedido> todosPedidos();
    List<Pedido> pedidosPorStatus(StatusPedido status);
    List<Pedido> pedidosReservadosAntesDe(LocalDate data);
}
