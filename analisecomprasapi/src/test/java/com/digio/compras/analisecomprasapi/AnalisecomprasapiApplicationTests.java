package com.digio.compras.analisecomprasapi;

import com.digio.compras.analisecomprasapi.dto.Cliente;
import com.digio.compras.analisecomprasapi.dto.ClienteFielResponse;
import com.digio.compras.analisecomprasapi.dto.CompraResponse;
import com.digio.compras.analisecomprasapi.dto.Produto;
import com.digio.compras.analisecomprasapi.dto.RecomendacaoClienteResponse;
import com.digio.compras.analisecomprasapi.service.ClientesComprasService;
import com.digio.compras.analisecomprasapi.service.ClientesService;
import com.digio.compras.analisecomprasapi.service.ProdutosService;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnaliseComprasapiApplicationTests {

    @Autowired
    private ClientesService clientesService;
    
    @Autowired
    private ProdutosService produtosService;
    
    @Autowired
    private ClientesComprasService clientesComprasService;
    
    @Test
    public void testRecomendarTipoPorCliente() {
        //preparar
        
        // Executar
        List<RecomendacaoClienteResponse> response = clientesComprasService.recomendarTipoPorCliente();
        
        // Verificar os resultados esperados
        assertEquals(14, response.size());
    } 

    @Test
    public void testTop3ClientesFieis() {
        //preparar
        
        // Executar
        List<ClienteFielResponse> response = clientesComprasService.buscarTop3ClientesFieis();
        
        // Verificar os resultados esperados
        assertEquals(3, response.size());
    } 
    
    @Test
    public void testMaiorCompraAno() {
        //preparar
        
        // Executar
        CompraResponse response = clientesComprasService.buscarMaiorCompraPorAno(2019);
        
        BigDecimal esperado = new BigDecimal("1897.50");
        // Verificar os resultados esperados
        assertEquals(0, (int)esperado.compareTo(response.getValorTotal()));
    } 

    @Test
    public void testClientesCompras() {
        //preparar
        
        // Executar
        List<CompraResponse> response = clientesComprasService.listarComprasOrdenadas();
        
        // Verificar os resultados esperados
        assertEquals(14, response.size());
    } 
    
    @Test
    public void testClientes() {
        //preparar
        
        // Executar
        Cliente[] response = clientesService.getAllClientes();
        
        // Verificar os resultados esperados
        assertEquals(14, response.length);
    }       
    
    @Test
    public void testProdutos() {
        //preparar
        
        // Executar
        Produto[] response = produtosService.getAllProdutos();
        
        // Verificar os resultados esperados
        assertEquals(20, response.length);
    }          

}
