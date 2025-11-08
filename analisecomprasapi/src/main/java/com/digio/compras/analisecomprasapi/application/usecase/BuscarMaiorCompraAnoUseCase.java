package com.digio.compras.analisecomprasapi.application.usecase;

import com.digio.compras.analisecomprasapi.application.dto.CompraDetalhada;
import com.digio.compras.analisecomprasapi.domain.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Caso de uso para buscar a maior compra de um ano específico.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class BuscarMaiorCompraAnoUseCase {
    
    private static final Logger log = LoggerFactory.getLogger(BuscarMaiorCompraAnoUseCase.class);
    
    private final ListarComprasOrdenadasUseCase listarComprasUseCase;

    public BuscarMaiorCompraAnoUseCase(ListarComprasOrdenadasUseCase listarComprasUseCase) {
        this.listarComprasUseCase = listarComprasUseCase;
    }

    /**
     * Executa o caso de uso para buscar a maior compra do ano.
     * 
     * @param ano O ano a ser pesquisado
     * @return Mono de CompraDetalhada representando a maior compra
     */
    public Mono<CompraDetalhada> execute(int ano) {
        log.info("Executando caso de uso: buscar maior compra do ano {}", ano);
        
        return listarComprasUseCase.execute()
            .flatMap(compra -> Mono.justOrEmpty(
                compra.getItens().stream()
                    .filter(item -> item.getAnoCompra() == ano)
                    .findFirst()
                    .map(item -> new CompraDetalhada(
                        compra.getNomeCliente(),
                        compra.getCpf(),
                        java.util.List.of(item),
                        item.getSubtotal()
                    ))
            ))
            .reduce((c1, c2) -> 
                c1.getValorTotal().compareTo(c2.getValorTotal()) > 0 ? c1 : c2
            )
            .switchIfEmpty(Mono.error(
                new ResourceNotFoundException("Nenhuma compra encontrada para o ano " + ano)
            ))
            .doOnSuccess(compra -> log.info("Maior compra do ano {} encontrada", ano));
    }
}
