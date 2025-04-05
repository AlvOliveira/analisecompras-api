package com.digio.compras.analisecomprasapi.service;

import com.digio.compras.analisecomprasapi.dto.Cliente;
import com.digio.compras.analisecomprasapi.dto.ClienteFielResponse;
import com.digio.compras.analisecomprasapi.dto.Compra;
import com.digio.compras.analisecomprasapi.dto.CompraResponse;
import com.digio.compras.analisecomprasapi.dto.CompraResponse.DetalheCompra;
import com.digio.compras.analisecomprasapi.dto.Produto;
import com.digio.compras.analisecomprasapi.dto.RecomendacaoClienteResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alvaro
 */
@Service
public class ClientesComprasService {

    private final ProdutosService produtosService;
    private final ClientesService clientesService;
    

    public ClientesComprasService(ProdutosService produtosService, ClientesService clientesService) {
        this.produtosService = produtosService;
        this.clientesService = clientesService;
    }
    
    public List<CompraResponse> listarComprasOrdenadas() {
        Produto[] produtosArray = produtosService.getAllProdutos();
        Cliente[] clientesArray = clientesService.getAllClientes();

        Map<String, Produto> mapaProdutos = Arrays.stream(produtosArray)
                .collect(Collectors.toMap(Produto::getCodigo, Function.identity()));

        List<CompraResponse> respostas = new ArrayList<>();

        for (Cliente cliente : clientesArray) {
            CompraResponse resposta = new CompraResponse();
            resposta.setNomeCliente(cliente.getNome());
            resposta.setCpf(cliente.getCpf());

            BigDecimal total = BigDecimal.ZERO; // Correção: Usar BigDecimal.ZERO
            List<CompraResponse.DetalheCompra> detalhes = new ArrayList<>();

            for (Compra compra : cliente.getCompras()) {
                Produto produto = mapaProdutos.get(compra.getCodigo());
                if (produto != null) {
                    CompraResponse.DetalheCompra detalhe = new CompraResponse.DetalheCompra();
                    detalhe.setTipo(produto.getTipo_vinho());
                    detalhe.setSafra(produto.getSafra());
                    detalhe.setPrecoUnitario(produto.getPreco());
                    detalhe.setQuantidade(compra.getQuantidade());
                    detalhe.setAnoCompra(produto.getAno_compra());

                    // Correção: Multiplicação correta de BigDecimal
                    BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(compra.getQuantidade()));
                    detalhe.setSubtotal(subtotal);

                    // Correção: Soma correta de BigDecimals
                    total = total.add(subtotal); 

                    detalhes.add(detalhe);
                }
            }

            resposta.setItens(detalhes);
            resposta.setValorTotal(total); // Assumindo que setValorTotal aceita BigDecimal
            respostas.add(resposta);
        }

        // Correção: Comparação correta para BigDecimal
        return respostas.stream()
                .sorted(Comparator.comparing(CompraResponse::getValorTotal))
                .collect(Collectors.toList());
    }

    public CompraResponse buscarMaiorCompraPorAno(int ano) {
        
        List<CompraResponse> compraResponse = this.listarComprasOrdenadas();
        
        CompraResponse maiorCompra = null;
        double maiorValor = 0.0;

        for (CompraResponse compraResp : compraResponse) {
            
            for (DetalheCompra detalheCompra: compraResp.getItens()){
                if (detalheCompra.getAnoCompra() != ano) continue;

                double total = detalheCompra.getSubtotal().doubleValue();
                
                if (total > maiorValor) {
                    maiorCompra = new CompraResponse();
                    maiorCompra.setCpf(compraResp.getCpf());
                    maiorCompra.setNomeCliente(compraResp.getNomeCliente());
                    List<DetalheCompra> itens = new ArrayList<>();
                    itens.add(detalheCompra);
                    maiorCompra.setItens(itens);
                    maiorValor = total;
                    maiorCompra.setValorTotal(detalheCompra.getSubtotal());
                }
            }
        }
        
        return maiorCompra;
    }
    
    public List<ClienteFielResponse> buscarTop3ClientesFieis() {

        List<CompraResponse> compraResponse = listarComprasOrdenadas();
        
        List<ClienteFielResponse> ranking = new ArrayList<>();

        for (CompraResponse compraResp : compraResponse) {

            int quantidadeCompras = compraResp.getItens().size();

            if (quantidadeCompras > 0) {
                ClienteFielResponse response = new ClienteFielResponse();
                response.setNome(compraResp.getNomeCliente());
                response.setCpf(compraResp.getCpf());
                response.setQuantidadeCompras(quantidadeCompras);
                response.setValorTotalGasto(compraResp.getValorTotal());

                ranking.add(response);
            }
        }

         List<ClienteFielResponse> fielResp = ranking.stream()
                .sorted(Comparator
                        .comparing(ClienteFielResponse::getValorTotalGasto).reversed())
                .limit(3)
                .collect(Collectors.toList());
         
         return fielResp.stream()
                .sorted(Comparator
                        .comparing(ClienteFielResponse::getQuantidadeCompras).reversed())
                .collect(Collectors.toList());
    }
    
    public List<RecomendacaoClienteResponse> recomendarTipoPorCliente() {
        
        List<CompraResponse> compraResponse = this.listarComprasOrdenadas();
        
        List<RecomendacaoClienteResponse> recomendacoes = new ArrayList<>();
        
        for (CompraResponse compraResp : compraResponse) {
            Map<String, Integer> tipoContador = new HashMap<>();

            for (DetalheCompra detalheCompra: compraResp.getItens()){           
                tipoContador.merge(detalheCompra.getTipo(), detalheCompra.getQuantidade(), Integer::sum);
            }
            
            if (!tipoContador.isEmpty()) {
                String tipoMaisComprado = tipoContador.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("Desconhecido");

                int qtd = tipoContador.getOrDefault(tipoMaisComprado, 0);

                RecomendacaoClienteResponse rec = new RecomendacaoClienteResponse();
                rec.setNome(compraResp.getNomeCliente());
                rec.setCpf(compraResp.getCpf());
                rec.setTipoRecomendado(tipoMaisComprado);
                rec.setQuantidadeComprasDoTipo(qtd);

                recomendacoes.add(rec);
            }            
        }
        
        return recomendacoes;
    }

}