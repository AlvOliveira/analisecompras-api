package com.digio.compras.analisecomprasapi.application.usecase;

import com.digio.compras.analisecomprasapi.application.dto.RecomendacaoVinho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Caso de uso para recomendar tipos de vinho baseado no histórico de compras dos clientes.
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class RecomendarVinhoUseCase {
    
    private static final Logger log = LoggerFactory.getLogger(RecomendarVinhoUseCase.class);
    
    private final ListarComprasOrdenadasUseCase listarComprasUseCase;

    public RecomendarVinhoUseCase(ListarComprasOrdenadasUseCase listarComprasUseCase) {
        this.listarComprasUseCase = listarComprasUseCase;
    }

    /**
     * Executa o caso de uso para recomendar vinhos para todos os clientes.
     * A recomendação é baseada no tipo de vinho mais comprado por cada cliente.
     * 
     * @return Flux de RecomendacaoVinho
     */
    public Flux<RecomendacaoVinho> execute() {
        log.info("Executando caso de uso: recomendar vinhos para clientes");
        
        return listarComprasUseCase.execute()
            .map(compra -> {
                Map<String, Integer> tipoContador = new HashMap<>();
                
                compra.getItens().forEach(item -> 
                    tipoContador.merge(item.getTipo(), item.getQuantidade(), Integer::sum)
                );
                
                if (tipoContador.isEmpty()) {
                    return new RecomendacaoVinho(
                        compra.getNomeCliente(),
                        compra.getCpf(),
                        "Nenhuma recomendação",
                        0
                    );
                }
                
                Map.Entry<String, Integer> tipoMaisComprado = tipoContador.entrySet().stream()
                    .max(Comparator.comparingInt(Map.Entry::getValue))
                    .orElseThrow();
                
                return new RecomendacaoVinho(
                    compra.getNomeCliente(),
                    compra.getCpf(),
                    tipoMaisComprado.getKey(),
                    tipoMaisComprado.getValue()
                );
            })
            .doOnComplete(() -> log.info("Recomendações de vinho geradas com sucesso"));
    }
}
