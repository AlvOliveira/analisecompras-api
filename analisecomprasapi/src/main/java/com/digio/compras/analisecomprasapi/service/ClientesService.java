package com.digio.compras.analisecomprasapi.service;

import com.digio.compras.analisecomprasapi.dto.Cliente;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Alvaro
 */
@Service
public class ClientesService {
    private final RestTemplate restTemplate;
    private final String baseUrl;
    
    public ClientesService(RestTemplateBuilder builder)
    {
        this.restTemplate = builder.build();
        this.baseUrl = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json";
    }
    
    public Cliente[] getAllClientes()
    {
        Cliente[] clientesArray = restTemplate.getForObject(this.baseUrl, Cliente[].class);
        
        return clientesArray;
    }    
    
}
