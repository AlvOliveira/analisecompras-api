package com.digio.compras.analisecomprasapi.application.usecase;

import com.digio.compras.analisecomprasapi.application.dto.ClienteFiel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;

/**
 * Caso de uso para buscar os top 3 clientes mais fiéis.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class BuscarClientesFieisUseCase {
    
    private static final Logger log = LoggerFactory.getLogger(BuscarClientesFieisUseCase.class);
    private static final int TOP_CLIENTES = 3;
    
    private final ListarComprasOrdenadasUseCase listarComprasUseCase;

    public BuscarClientesFieisUseCase(ListarComprasOrdenadasUseCase listarComprasUseCase) {
        this.listarComprasUseCase = listarComprasUseCase;
    }

    /**
     * Executa o caso de uso para buscar os top 3 clientes mais fiéis.
     * Considera clientes fiéis aqueles com maior quantidade de compras e maiores valores gastos.
     * 
     * @return Flux dos top 3 ClienteFiel
     */
    public Flux<ClienteFiel> execute() {
        log.info("Executando caso de uso: buscar top {} clientes fiéis", TOP_CLIENTES);
        
        return listarComprasUseCase.execute()
            .map(compra -> new ClienteFiel(
                compra.getNomeCliente(),
                compra.getCpf(),
                compra.getItens().size(),
                compra.getValorTotal()
            ))
            .sort(Comparator
                .comparing(ClienteFiel::getQuantidadeCompras)
                .thenComparing(ClienteFiel::getValorTotalGasto)
                .reversed()
            )
            .take(TOP_CLIENTES)
            .doOnComplete(() -> log.info("Top {} clientes fiéis encontrados", TOP_CLIENTES));
    }
}
