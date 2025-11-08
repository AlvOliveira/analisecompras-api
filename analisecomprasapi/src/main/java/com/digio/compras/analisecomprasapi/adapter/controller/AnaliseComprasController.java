package com.digio.compras.analisecomprasapi.adapter.controller;

import com.digio.compras.analisecomprasapi.adapter.dto.response.ClienteFielResponseDto;
import com.digio.compras.analisecomprasapi.adapter.dto.response.CompraResponseDto;
import com.digio.compras.analisecomprasapi.adapter.dto.response.RecomendacaoResponseDto;
import com.digio.compras.analisecomprasapi.adapter.mapper.CompraResponseMapper;
import com.digio.compras.analisecomprasapi.application.usecase.BuscarClientesFieisUseCase;
import com.digio.compras.analisecomprasapi.application.usecase.BuscarMaiorCompraAnoUseCase;
import com.digio.compras.analisecomprasapi.application.usecase.ListarComprasOrdenadasUseCase;
import com.digio.compras.analisecomprasapi.application.usecase.RecomendarVinhoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

/**
 * Controller REST responsável pelos endpoints de análise de compras de vinhos.
 * Todos os endpoints são reativos e utilizam Project Reactor (Mono/Flux).
 * 
 * @author Alvaro
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping
@Tag(name = "Análise de Compras", description = "API para análise de compras de vinhos")
public class AnaliseComprasController {
    
    private static final Logger log = LoggerFactory.getLogger(AnaliseComprasController.class);
    
    private final ListarComprasOrdenadasUseCase listarComprasUseCase;
    private final BuscarMaiorCompraAnoUseCase buscarMaiorCompraUseCase;
    private final BuscarClientesFieisUseCase buscarClientesFieisUseCase;
    private final RecomendarVinhoUseCase recomendarVinhoUseCase;

    /**
     * Construtor com injeção de dependências dos casos de uso.
     *
     * @param listarComprasUseCase Caso de uso para listar compras
     * @param buscarMaiorCompraUseCase Caso de uso para buscar maior compra
     * @param buscarClientesFieisUseCase Caso de uso para buscar clientes fiéis
     * @param recomendarVinhoUseCase Caso de uso para recomendar vinhos
     */
    public AnaliseComprasController(
            ListarComprasOrdenadasUseCase listarComprasUseCase,
            BuscarMaiorCompraAnoUseCase buscarMaiorCompraUseCase,
            BuscarClientesFieisUseCase buscarClientesFieisUseCase,
            RecomendarVinhoUseCase recomendarVinhoUseCase) {
        this.listarComprasUseCase = listarComprasUseCase;
        this.buscarMaiorCompraUseCase = buscarMaiorCompraUseCase;
        this.buscarClientesFieisUseCase = buscarClientesFieisUseCase;
        this.recomendarVinhoUseCase = recomendarVinhoUseCase;
    }

    /**
     * Endpoint GET /compras - Retorna lista de compras ordenadas por valor crescente.
     *
     * @return Flux de CompraResponseDto
     */
    @Operation(
        summary = "Listar compras ordenadas",
        description = "Retorna uma lista das compras ordenadas de forma crescente por valor, " +
                     "contendo nome dos clientes, CPF, dados dos produtos, quantidades e valores totais"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de compras retornada com sucesso",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CompraResponseDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "502",
            description = "Erro ao comunicar com API externa"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor"
        )
    })
    @GetMapping(value = "/compras", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CompraResponseDto> getComprasOrdenadas() {
        log.info("Endpoint GET /compras chamado");
        
        return listarComprasUseCase.execute()
            .map(CompraResponseMapper::toResponseDto)
            .doOnComplete(() -> log.info("Compras ordenadas retornadas com sucesso"));
    }

    /**
     * Endpoint GET /maior-compra/{ano} - Retorna a maior compra do ano informado.
     *
     * @param ano O ano para busca (ex: 2016)
     * @return Mono de CompraResponseDto
     */
    @Operation(
        summary = "Buscar maior compra por ano",
        description = "Retorna a maior compra do ano informado com dados completos: " +
                     "nome do cliente, CPF, dados do produto, quantidade e valor total"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Maior compra encontrada",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = CompraResponseDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Nenhuma compra encontrada para o ano informado"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Ano inválido"
        ),
        @ApiResponse(
            responseCode = "502",
            description = "Erro ao comunicar com API externa"
        )
    })
    @GetMapping(value = "/maior-compra/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CompraResponseDto> getMaiorCompraPorAno(
            @Parameter(description = "Ano da compra (ex: 2016, 2017, 2018)", example = "2016", required = true)
            @PathVariable @Min(2000) @Max(2100) int ano) {
        log.info("Endpoint GET /maior-compra/{} chamado", ano);
        
        return buscarMaiorCompraUseCase.execute(ano)
            .map(CompraResponseMapper::toResponseDto)
            .doOnSuccess(compra -> log.info("Maior compra do ano {} retornada", ano));
    }

    /**
     * Endpoint GET /clientes-fieis - Retorna o Top 3 clientes mais fiéis.
     *
     * @return Flux de ClienteFielResponseDto
     */
    @Operation(
        summary = "Listar clientes mais fiéis",
        description = "Retorna o Top 3 clientes mais fiéis, considerando aqueles com mais compras " +
                     "recorrentes e maiores valores gastos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Top 3 clientes fiéis retornados",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ClienteFielResponseDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "502",
            description = "Erro ao comunicar com API externa"
        )
    })
    @GetMapping(value = "/clientes-fieis", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ClienteFielResponseDto> getClientesFieis() {
        log.info("Endpoint GET /clientes-fieis chamado");
        
        return buscarClientesFieisUseCase.execute()
            .map(CompraResponseMapper::toClienteFielResponseDto)
            .doOnComplete(() -> log.info("Top 3 clientes fiéis retornados"));
    }

    /**
     * Endpoint GET /recomendacao/cliente/tipo - Retorna recomendações de vinho por cliente.
     *
     * @return Flux de RecomendacaoResponseDto
     */
    @Operation(
        summary = "Recomendar vinho por cliente",
        description = "Retorna uma recomendação de vinho para cada cliente baseado nos tipos " +
                     "de vinho que o cliente mais compra"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Recomendações retornadas com sucesso",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = RecomendacaoResponseDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "502",
            description = "Erro ao comunicar com API externa"
        )
    })
    @GetMapping(value = "/recomendacao/cliente/tipo", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<RecomendacaoResponseDto> getRecomendacaoPorCliente() {
        log.info("Endpoint GET /recomendacao/cliente/tipo chamado");
        
        return recomendarVinhoUseCase.execute()
            .map(CompraResponseMapper::toRecomendacaoResponseDto)
            .doOnComplete(() -> log.info("Recomendações retornadas com sucesso"));
    }
}
