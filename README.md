# Desafio técnico para criação microserviço em Java com Spring Boot para analise de compra de vinhos
API´s Mock:
Lista de Produtos: https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json
Lista de Clientes e Compras: https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json

# Requisitos não funcionais
- Aplicação foi construida em Java com Spring Boot no JDK 24.

# Aplicação a ser desenvolvida
1. GET: /compras - Retornar uma lista das compras ordenadas de forma crescente por valor, deve conter o nome dos clientes, cpf dos clientes, dado dos produtos, quantidade das compras e valores totais de cada compra.
2. GET: /maior-compra/ano - (Exemplo: /maior-compra/2016) - Retornar a maior compra do ano informando os dados da compra disponibilizados, deve ter o nome do cliente, cpf do cliente, dado do produto, quantidade da compra e seu valor total.
3. GET: /clientes-fieis - Retornar o Top 3 clientes mais fieis, clientes que possuem mais compras recorrentes com maiores valores.
4. GET: /recomendacao/cliente/tipo - Retornar uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.

# Como baixar o aplicativo para seu ambiente
```
git clone https://github.com/AlvOliveira/analisecompras-api.git
```
# Instruções de configuração do ambiente

Navegue até o diretório analisecompras-api e execute o seguinte comando:

```
docker-compose --project-name analisecompras-api up --build -d
```  

Aguarde pelo entre 10 e 15 segundos para que o aplicativo fique operacional.

# Instruções de teste no seu browser preferido:

Gerar uma lista de compras ordenada de forma crescente pelo valor total, contendo o nome e CPF dos clientes, dados dos produtos, quantidades compradas e o valor total de cada compra:
```
http://localhost:8080/compras
```

Exibir a maior compra do ano com todas as informações disponíveis, incluindo nome e CPF do cliente, dados do produto, quantidade comprada e valor total da compra.
```
http://localhost:8080/maior-compra/2019
```

Listar os 3 clientes mais fiéis, com base na quantidade de compras recorrentes e nos maiores valores totais.
```
http://localhost:8080/clientes-fieis
```

Sugerir um vinho ao cliente com base nos tipos de vinho que ele mais costuma comprar.
```
http://localhost:8080/recomendacao/cliente/tipo
```

## As instruções a seguir são para validar APIs por meio do aplicativo "curl"

## Exemple: Gerar uma lista de compras ordenada
Navegue até o diretório analisecompras-api e execute os seguintes comandos no seu terminal preferido:

Para Windows PowerShell:
```
./testCompras.cmd
```
Para Windows Command Prompt:
```
testCompras.cmd
```
Para Linux/MacOS Terminal:
```
chmod +x testCompras.sh

./testCompras.sh
```

Conteúdo do arquivo de teste **testCompras.cmd** para Windows:
```
curl -X GET http://localhost:8080/compras ^
-H "Content-Type: application/json"
```

Conteúdo do arquivo de teste **testCompras.sh** para Linux/MacOS: 
```
#!/bin/bash

curl -X GET http://localhost:8080/compras \
-H "Content-Type: application/json"
```

## Exemple: Retornar a maior compra do ano
Navegue até o diretório analisecompras-api e execute os seguintes comandos no seu terminal preferido:

For Windows PowerShell:
```
./testMaiorCompraAno.cmd
```
For Windows Command Prompt:
```
testMaiorCompraAno.cmd
```
For Linux/MacOS Terminal:
```
chmod +x testMaiorCompraAno.sh

./testMaiorCompraAno.sh
```

Conteúdo do arquivo de teste **testMaiorCompraAno.cmd** para Windows:
 
```
curl -X GET http://localhost:8080/maior-compra/2019 ^
-H "Content-Type: application/json" 
```

Conteúdo do arquivo de teste **testMaiorCompraAno.sh** para Linux/MacOS: 
```
#!/bin/bash

curl -X GET http://localhost:8080/maior-compra/2019 \
-H "Content-Type: application/json" 
```

## Example: Retornar o Top 3 clientes mais fieis
Navegue até o diretório analisecompras-api e execute os seguintes comandos no seu terminal preferido:

For Windows PowerShell:
```
./testClientesFieis.cmd
```
For Windows Command Prompt:
```
testClientesFieis.cmd
```
For Linux/MacOS terminal:
```
chmod +x testClientesFieis.sh

./testClientesFieis.sh
```

Conteúdo do arquivo de teste  **testClientesFieis.cmd** para Windows: 
```
curl -X GET http://localhost:8080/clientes-fieis ^
-H "Content-Type: application/json"
```

Conteúdo do arquivo de teste  **testClientesFieis.sh** para Linux/MacOS: 
```
#!/bin/bash

curl -X GET http://localhost:8080/clientes-fieis \
-H "Content-Type: application/json" 
```

## Example: Retornar uma recomendação de vinho
Navegue até o diretório analisecompras-api e execute os seguintes comandos no seu terminal preferido:

For Windows PowerShell:
```
./testRecomendacao.cmd
```
For Windows Command Prompt:
```
testRecomendacao.cmd
```
For Linux/MacOS terminal:
```
chmod +x testRecomendacao.sh

./testRecomendacao.sh
```

Conteúdo do arquivo de teste  **testRecomendacao.cmd** para Windows: 
```
curl -X GET http://localhost:8080/recomendacao/cliente/tipo ^
-H "Content-Type: application/json"
```

Conteúdo do arquivo de teste  **testRecomendacao.sh** para Linux/MacOS: 
```
#!/bin/bash

curl -X GET http://localhost:8080/recomendacao/cliente/tipo \
-H "Content-Type: application/json" 
```
