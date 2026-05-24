package com.bcopstein.ex3_sist_vendas_hexagonal.domain.portas.in.casos_de_uso;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.ItemPedido;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.Pedido;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.model.Produto;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.portas.out.repositorios.RepositorioPedidos;
import com.bcopstein.ex3_sist_vendas_hexagonal.domain.portas.out.repositorios.RepositorioProdutos;

@Component
public class ListarProdutosReservadosPorMaisDeUmaSemanaUC {

	private final RepositorioPedidos repositorioPedidos;
	private final RepositorioProdutos repositorioProdutos;

	public ListarProdutosReservadosPorMaisDeUmaSemanaUC(RepositorioPedidos repositorioPedidos, RepositorioProdutos repositorioProdutos) {
		this.repositorioPedidos = repositorioPedidos;
		this.repositorioProdutos = repositorioProdutos;
	}

    public List<Produto> executar(){
        // Calcula a data de uma semana atrás
        LocalDate dataLimite = LocalDate.now().minusWeeks(1);
        
        // Obtém pedidos reservados há mais de uma semana
        List<Pedido> pedidosAntigos = repositorioPedidos.pedidosReservadosAntesDe(dataLimite);
        
        // Coleta todos os IDs dos produtos nesses pedidos
        List<Long> produtoIds = pedidosAntigos.stream()
            .flatMap(Pedido::getItens)
            .map(ItemPedido::getProdutoId)
            .distinct()
            .toList();
        
        // Recupera os produtos
        return produtoIds.stream()
            .map(repositorioProdutos::recuperaProduto)
            .toList();
    }
}
