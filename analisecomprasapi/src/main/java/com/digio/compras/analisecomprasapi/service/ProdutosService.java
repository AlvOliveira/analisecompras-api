package com.digio.compras.analisecomprasapi.service;

import com.digio.compras.analisecomprasapi.dto.Produto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Alvaro
 */
@Service
public class ProdutosService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ProdutosService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        this.baseUrl = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json";
    }
  
    public Produto[] getAllProdutos()
    {
        Produto[] produtosArray = restTemplate.getForObject(this.baseUrl, Produto[].class);
        
        return produtosArray;
    }
}
