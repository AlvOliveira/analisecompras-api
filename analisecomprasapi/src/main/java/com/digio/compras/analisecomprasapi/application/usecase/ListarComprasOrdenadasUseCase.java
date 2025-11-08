package com.digio.compras.analisecomprasapi.application.usecase;

import com.digio.compras.analisecomprasapi.application.dto.CompraDetalhada;
import com.digio.compras.analisecomprasapi.domain.entity.ClienteEntity;
import com.digio.compras.analisecomprasapi.domain.entity.CompraEntity;
import com.digio.compras.analisecomprasapi.domain.entity.ProdutoEntity;
import com.digio.compras.analisecomprasapi.domain.repository.ClienteRepository;
import com.digio.compras.analisecomprasapi.domain.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Caso de uso para listar compras ordenadas por valor.
 * Esta classe pertence à camada de aplicação e implementa a lógica de negócio.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ListarComprasOrdenadasUseCase {
    
    private static final Logger log = LoggerFactory.getLogger(ListarComprasOrdenadasUseCase.class);
    
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    /**
     * Construtor com injeção de dependências.
     * 
     * @param clienteRepository Repositório de clientes
     * @param produtoRepository Repositório de produtos
     */
    public ListarComprasOrdenadasUseCase(
            ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    /**
     * Executa o caso de uso de listar compras ordenadas por valor.
     * 
     * @return Flux de CompraDetalhada ordenado por valor total
     */
    public Flux<CompraDetalhada> execute() {
        log.info("Executando caso de uso: listar compras ordenadas");
        
        return produtoRepository.findAll()
            .collectMap(ProdutoEntity::getCodigo, Function.identity())
            .flatMapMany(mapaProdutos -> 
                clienteRepository.findAll()
                    .flatMap(cliente -> processarComprasCliente(cliente, mapaProdutos))
            )
            .sort((c1, c2) -> c1.getValorTotal().compareTo(c2.getValorTotal()))
            .doOnComplete(() -> log.info("Compras ordenadas listadas com sucesso"));
    }

    /**
     * Processa as compras de um cliente.
     * 
     * @param cliente O cliente
     * @param mapaProdutos Mapa de produtos por código
     * @return Mono de CompraDetalhada
     */
    private Mono<CompraDetalhada> processarComprasCliente(
            ClienteEntity cliente, 
            Map<String, ProdutoEntity> mapaProdutos) {
        
        List<CompraDetalhada.ItemCompra> itens = cliente.getCompras().stream()
            .map(compra -> criarItemCompra(compra, mapaProdutos))
            .filter(item -> item != null)
            .collect(Collectors.toList());
        
        BigDecimal valorTotal = itens.stream()
            .map(CompraDetalhada.ItemCompra::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return Mono.just(new CompraDetalhada(
            cliente.getNome(),
            cliente.getCpf(),
            itens,
            valorTotal
        ));
    }

    /**
     * Cria um item de compra com base nos dados da compra e do produto.
     * 
     * @param compra A compra
     * @param mapaProdutos Mapa de produtos
     * @return ItemCompra ou null se produto não encontrado
     */
    private CompraDetalhada.ItemCompra criarItemCompra(
            CompraEntity compra, 
            Map<String, ProdutoEntity> mapaProdutos) {
        
        ProdutoEntity produto = mapaProdutos.get(compra.getCodigoProduto());
        
        if (produto == null) {
            log.warn("Produto não encontrado: {}", compra.getCodigoProduto());
            return null;
        }
        
        BigDecimal subtotal = produto.calcularValorTotal(compra.getQuantidade());
        
        return new CompraDetalhada.ItemCompra(
            produto.getTipoVinho(),
            produto.getSafra(),
            produto.getPreco(),
            compra.getQuantidade(),
            produto.getAnoCompra(),
            subtotal
        );
    }
}
