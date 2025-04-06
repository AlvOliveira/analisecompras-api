# Desafio Técnico: Microserviço Java Spring Boot para Análise de Compras de Vinhos

## APIs Mock
- **Produtos**: [https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json](https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json)  
- **Clientes e Compras**: [https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json](https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json)

## Requisitos Não Funcionais
- Aplicação desenvolvida em **Java 24** com **Spring Boot**.
- Containerizada via **Docker Compose**.

---

## Endpoints da API

1. `GET /compras`  
   Retorna uma lista de compras ordenadas de forma crescente pelo valor total. Cada item deve conter: nome e CPF do cliente, dados dos produtos, quantidade e valor total da compra.

2. `GET /maior-compra/{ano}`  
   Retorna a maior compra do ano informado, com nome e CPF do cliente, produtos, quantidade e valor total.

3. `GET /clientes-fieis`  
   Retorna os 3 clientes mais fiéis, com base na quantidade de compras e nos maiores valores totais.

4. `GET /recomendacao/cliente/tipo`  
   Retorna uma recomendação de vinho com base nos tipos mais comprados por um cliente.

---

## Como clonar o projeto
```bash
git clone https://github.com/AlvOliveira/analisecompras-api.git
```

## Subindo o ambiente com Docker

Navegue até o diretório analisecompras-api e execute:

```bash
docker-compose --project-name analisecompras-api up --build -d
```  

Aguarde de 10 a 15 segundos para a aplicação ficar operacional.

## Testando a API via navegador:

* Lista de compras:
```bash
http://localhost:8080/compras
```

* Lista de compras:
```bash
http://localhost:8080/maior-compra/2019
```

* Top 3 clientes fiéis:
```bash
http://localhost:8080/clientes-fieis
```

* Recomendação de vinho para cliente:
```bash
http://localhost:8080/recomendacao/cliente/tipo
```

## Testando com *"curl"*

## 1. Listar Compras
Navegue até o diretório analisecompras-api e execute:

Para Windows PowerShell:
```bash
./testCompras.cmd
```
Para Windows Command Prompt:
```bash
testCompras.cmd
```
Para Linux/MacOS Terminal:
```bash
chmod +x testCompras.sh

./testCompras.sh
```

Conteúdo de **testCompras.cmd** para Windows:
```bash
curl -X GET http://localhost:8080/compras ^
-H "Content-Type: application/json"
```

Conteúdo de **testCompras.sh** para Linux/MacOS: 
```bash
#!/bin/bash

curl -X GET http://localhost:8080/compras \
-H "Content-Type: application/json"
```

## 2. Maior Compra por Ano

Para Windows PowerShell:
```bash
./testMaiorCompraAno.cmd
```
Para Windows Command Prompt:
```bash
testMaiorCompraAno.cmd
```
Para Linux/MacOS Terminal:
```bash
chmod +x testMaiorCompraAno.sh

./testMaiorCompraAno.sh
```

Conteúdo de **testMaiorCompraAno.cmd** para Windows:
 
```bash
curl -X GET http://localhost:8080/maior-compra/2019 ^
-H "Content-Type: application/json" 
```

Conteúdo de **testMaiorCompraAno.sh** para Linux/MacOS: 
```bash
#!/bin/bash

curl -X GET http://localhost:8080/maior-compra/2019 \
-H "Content-Type: application/json" 
```

## 3. Top 3 Clientes Fiéis

Para Windows PowerShell:
```bash
./testClientesFieis.cmd
```
Para Windows Command Prompt:
```bash
testClientesFieis.cmd
```
Para Linux/MacOS terminal:
```bash
chmod +x testClientesFieis.sh

./testClientesFieis.sh
```

Conteúdo de **testClientesFieis.cmd** para Windows: 
```bash
curl -X GET http://localhost:8080/clientes-fieis ^
-H "Content-Type: application/json"
```

Conteúdo de teste **testClientesFieis.sh** para Linux/MacOS: 
```bash
#!/bin/bash

curl -X GET http://localhost:8080/clientes-fieis \
-H "Content-Type: application/json" 
```

## 4. Recomendação de Vinho

Para Windows PowerShell:
```bash
./testRecomendacao.cmd
```
Para Windows Command Prompt:
```bash
testRecomendacao.cmd
```
Para Linux/MacOS terminal:
```bash
chmod +x testRecomendacao.sh

./testRecomendacao.sh
```

Conteúdo de **testRecomendacao.cmd** para Windows: 
```bash
curl -X GET http://localhost:8080/recomendacao/cliente/tipo ^
-H "Content-Type: application/json"
```

Conteúdo de **testRecomendacao.sh** para Linux/MacOS: 
```bash
#!/bin/bash

curl -X GET http://localhost:8080/recomendacao/cliente/tipo \
-H "Content-Type: application/json" 
```

## Testando com *Swagger*
```bash
http://localhost:8080/swagger-ui/index.html
```


