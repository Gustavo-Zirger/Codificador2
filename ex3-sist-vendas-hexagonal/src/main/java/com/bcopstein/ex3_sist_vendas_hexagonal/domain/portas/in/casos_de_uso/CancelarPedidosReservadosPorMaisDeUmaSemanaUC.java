package com.bcopstein.ex3_sist_vendas_hexagonal.domain.portas.in.casos_de_uso;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.Pedido;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.StatusPedido;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.ServicoEstoque;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.portas.out.repositorios.RepositorioPedidos;

@Component
public class CancelarPedidosReservadosPorMaisDeUmaSemanaUC {
	private final ServicoEstoque servicoEstoque;
	private final RepositorioPedidos repositorioPedidos;

	@Autowired
	public CancelarPedidosReservadosPorMaisDeUmaSemanaUC(ServicoEstoque servicoEstoque, RepositorioPedidos repositorioPedidos) {
		this.servicoEstoque = servicoEstoque;
		this.repositorioPedidos = repositorioPedidos;
	}

    public List<Pedido> executar(){
        // Calcula a data de uma semana atrás
        LocalDate dataLimite = LocalDate.now().minusWeeks(1);
        
        // Obtém pedidos reservados há mais de uma semana
        List<Pedido> pedidosAntigos = repositorioPedidos.pedidosReservadosAntesDe(dataLimite);
        
        // Cancela cada pedido
        return pedidosAntigos.stream()
            .map(pedido -> {
                servicoEstoque.liberarReservas(pedido);
                pedido.defineStatus(StatusPedido.CANCELADO);
                return repositorioPedidos.persiste(pedido);
            })
            .toList();
    }
}
