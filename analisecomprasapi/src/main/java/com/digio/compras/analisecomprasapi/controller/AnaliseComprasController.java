package com.digio.compras.analisecomprasapi.controller;

import com.digio.compras.analisecomprasapi.dto.ClienteFielResponse;
import com.digio.compras.analisecomprasapi.dto.CompraResponse;
import com.digio.compras.analisecomprasapi.dto.RecomendacaoClienteResponse;
import com.digio.compras.analisecomprasapi.model.CommandResultDefault;
import com.digio.compras.analisecomprasapi.service.ClientesComprasService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alvaro
 */
@RestController
@RequestMapping("/")
public class AnaliseComprasController {

    private final ClientesComprasService clientesComprasService;
    private static final Logger log = LoggerFactory.getLogger(AnaliseComprasController.class);

    public AnaliseComprasController(ClientesComprasService clientesComprasService) {
        this.clientesComprasService = clientesComprasService;
    }

    @GetMapping("/compras")
    public ResponseEntity<CommandResultDefault> getComprasOrdenadas() {
        
        try {
            
            List<CompraResponse> data = clientesComprasService.listarComprasOrdenadas();
            return ResponseEntity.ok(new CommandResultDefault(true, "Realizado com sucesso.", data));
            
        } catch (RuntimeException e) {
            
            var error = "Erro ao processar o clientesComprasService.listarComprasOrdenadas()";
            log.error(error, e);
            
            return ResponseEntity.ok(new CommandResultDefault(false, error, e.getMessage()));
        }
    }

    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<CommandResultDefault> getMaiorCompraPorAno(@PathVariable int ano) {
        
        try {
            
            CompraResponse data = clientesComprasService.buscarMaiorCompraPorAno(ano);
            return ResponseEntity.ok(new CommandResultDefault(true, "Realizado com sucesso.", (data != null) ? data : "Nenhuma compra encontrada para o ano informado."));
            
        } catch (RuntimeException e) {
            
            var error = "Erro ao processar o clientesComprasService.buscarMaiorCompraPorAno(..)";
            log.error(error, e);
            
            return ResponseEntity.ok(new CommandResultDefault(false, error, e.getMessage()));
        }
    }

    @GetMapping("/clientes-fieis")
    public ResponseEntity<CommandResultDefault> getFieis() {
        
        try {
            
            List<ClienteFielResponse> data = clientesComprasService.buscarTop3ClientesFieis();
            return ResponseEntity.ok(new CommandResultDefault(true, "Realizado com sucesso.", data));
            
        } catch (RuntimeException e) {
            
            var error = "Erro ao processar o clientesComprasService.buscarTop3ClientesFieis()";
            log.error(error, e);
            
            return ResponseEntity.ok(new CommandResultDefault(false, error, e.getMessage()));
        }
    }

    @GetMapping("/recomendacao/cliente/tipo")
    public ResponseEntity<CommandResultDefault> getRecomendacaoTipoPorCliente() {
        
        try {
            
            List<RecomendacaoClienteResponse> data = clientesComprasService.recomendarTipoPorCliente();
            return ResponseEntity.ok(new CommandResultDefault(true, "Realizado com sucesso.", data));
            
        } catch (RuntimeException e) {
            
            var error = "Erro ao processar o clientesComprasService.recomendarTipoPorCliente()";
            log.error(error, e);
            
            return ResponseEntity.ok(new CommandResultDefault(false, error, e.getMessage()));
        }
    }
}
