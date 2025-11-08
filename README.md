# Desafio Técnico: Microserviço Java Spring Boot para Análise de Compras de Vinhos

## APIs Mock
- **Produtos**: [https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json](https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json)  
- **Clientes e Compras**: [https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json](https://rgr3viiqdl8sikgv.public.blob.vercelstorage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json)

## Requisitos Não Funcionais
- Aplicação desenvolvida em **Java 17+** com **Spring Boot 2.7.18**.
- Containerizada via **Docker Compose**.
- Arquitetura **Clean Architecture** com 4 camadas (Domain, Application, Infrastructure, Adapter).
- Programação **Reativa** com Spring WebFlux e Project Reactor.
- Testes unitários e de integração com JUnit 5 e Mockito.

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

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 2.7.18**
- **Spring WebFlux** (Programação Reativa)
- **Project Reactor** (Mono/Flux)
- **Spring Boot Actuator** (Métricas e Health Check)
- **SpringDoc OpenAPI** (Documentação Swagger)
- **JUnit 5** (Testes Unitários)
- **Mockito** (Mocks)
- **WebTestClient** (Testes de Integração)
- **Docker & Docker Compose**
- **Maven** (Gerenciamento de Dependências)

## Arquitetura

O projeto segue os princípios de **Clean Architecture** com 4 camadas:

1. **Domain Layer** (Camada de Domínio)
   - Entidades de negócio (`ClienteEntity`, `ProdutoEntity`, `CompraEntity`)
   - Interfaces de repositórios
   - Exceções de domínio

2. **Application Layer** (Camada de Aplicação)
   - Casos de uso (Use Cases)
   - DTOs de aplicação
   - Lógica de negócio

3. **Infrastructure Layer** (Camada de Infraestrutura)
   - Implementação dos repositórios
   - Integração com APIs externas via WebClient
   - Configurações

4. **Adapter Layer** (Camada de Adaptadores)
   - Controllers REST
   - DTOs de resposta
   - Tratamento global de exceções
   - Mapeadores

### Princípios Aplicados
- ✅ **SOLID**
- ✅ **Clean Code**
- ✅ **Dependency Inversion**
- ✅ **Single Responsibility**
- ✅ **Separation of Concerns**

## Como clonar o projeto
```bash
git clone https://github.com/AlvOliveira/analisecompras-api.git
```

## Compilando o projeto localmente

Navegue até o diretório `analisecomprasapi` e execute:

**Windows:**
```bash
.\mvnw.cmd clean package
```

**Linux/MacOS:**
```bash
./mvnw clean package
```

## Executando localmente (sem Docker)

Após compilar, execute:

```bash
java -jar target/analisecomprasapi-1.0.0.jar
```

Ou use o Maven:

**Windows:**
```bash
.\mvnw.cmd spring-boot:run
```

**Linux/MacOS:**
```bash
./mvnw spring-boot:run
```

## Subindo o ambiente com *"Docker"*

Navegue até o diretório analisecompras-api e execute:

```bash
docker-compose --project-name analisecompras-api up --build -d
```  

Aguarde de 10 a 15 segundos para a aplicação ficar operacional.

## Testando a API via navegador

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

Para **Windows** PowerShell:
```bash
./testCompras.cmd
```
Para **Windows** Command Prompt:
```bash
testCompras.cmd
```
Para **Linux/MacOS** Terminal:
```bash
chmod +x testCompras.sh

./testCompras.sh
```

Conteúdo de **testCompras.cmd** para **Windows**:
```bash
curl -X GET http://localhost:8080/compras ^
-H "Content-Type: application/json"
```

Conteúdo de **testCompras.sh** para **Linux/MacOS**: 
```bash
#!/bin/bash

curl -X GET http://localhost:8080/compras \
-H "Content-Type: application/json"
```

## 2. Maior Compra por Ano

Para **Windows** PowerShell:
```bash
./testMaiorCompraAno.cmd
```
Para **Windows** Command Prompt:
```bash
testMaiorCompraAno.cmd
```
Para **Linux/MacOS** Terminal:
```bash
chmod +x testMaiorCompraAno.sh

./testMaiorCompraAno.sh
```

Conteúdo de **testMaiorCompraAno.cmd** para **Windows**:
 
```bash
curl -X GET http://localhost:8080/maior-compra/2019 ^
-H "Content-Type: application/json" 
```

Conteúdo de **testMaiorCompraAno.sh** para **Linux/MacOS**: 
```bash
#!/bin/bash

curl -X GET http://localhost:8080/maior-compra/2019 \
-H "Content-Type: application/json" 
```

## 3. Top 3 Clientes Fiéis

Para **Windows** PowerShell:
```bash
./testClientesFieis.cmd
```
Para **Windows** Command Prompt:
```bash
testClientesFieis.cmd
```
Para **Linux/MacOS** terminal:
```bash
chmod +x testClientesFieis.sh

./testClientesFieis.sh
```

Conteúdo de **testClientesFieis.cmd** para **Windows**: 
```bash
curl -X GET http://localhost:8080/clientes-fieis ^
-H "Content-Type: application/json"
```

Conteúdo de teste **testClientesFieis.sh** para **Linux/MacOS**: 
```bash
#!/bin/bash

curl -X GET http://localhost:8080/clientes-fieis \
-H "Content-Type: application/json" 
```

## 4. Recomendação de Vinho

Para **Windows** PowerShell:
```bash
./testRecomendacao.cmd
```
Para **Windows** Command Prompt:
```bash
testRecomendacao.cmd
```
Para **Linux/MacOS** terminal:
```bash
chmod +x testRecomendacao.sh

./testRecomendacao.sh
```

Conteúdo de **testRecomendacao.cmd** para **Windows**: 
```bash
curl -X GET http://localhost:8080/recomendacao/cliente/tipo ^
-H "Content-Type: application/json"
```

Conteúdo de **testRecomendacao.sh** para **Linux/MacOS**: 
```bash
#!/bin/bash

curl -X GET http://localhost:8080/recomendacao/cliente/tipo \
-H "Content-Type: application/json" 
```

## Testando com *"Swagger"*
Acesse a documentação interativa da API:
```bash
http://localhost:8080/swagger-ui.html
```

## Endpoints do Actuator
Monitoramento e métricas da aplicação:
```bash
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info
http://localhost:8080/actuator/metrics
```

## Executando os Testes

O projeto possui testes unitários e de integração:

**Windows:**
```bash
.\mvnw.cmd test
```

**Linux/MacOS:**
```bash
./mvnw test
```

### Cobertura de Testes
- ✅ Testes unitários dos casos de uso
- ✅ Testes de integração do controller
- ✅ Testes com StepVerifier (reactive streams)
- ✅ Testes com WebTestClient

## Estrutura do Projeto

```
analisecomprasapi/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/digio/compras/analisecomprasapi/
│   │   │       ├── AnalisecomprasapiApplication.java
│   │   │       ├── adapter/
│   │   │       │   ├── controller/
│   │   │       │   │   ├── AnaliseComprasController.java
│   │   │       │   │   └── GlobalExceptionHandler.java
│   │   │       │   ├── dto/
│   │   │       │   │   ├── CompraResponseDto.java
│   │   │       │   │   ├── ClienteFielResponseDto.java
│   │   │       │   │   └── RecomendacaoResponseDto.java
│   │   │       │   └── mapper/
│   │   │       │       └── CompraResponseMapper.java
│   │   │       ├── application/
│   │   │       │   ├── dto/
│   │   │       │   │   ├── CompraDetalhada.java
│   │   │       │   │   ├── ClienteFiel.java
│   │   │       │   │   └── RecomendacaoVinho.java
│   │   │       │   └── usecase/
│   │   │       │       ├── ListarComprasOrdenadasUseCase.java
│   │   │       │       ├── BuscarMaiorCompraAnoUseCase.java
│   │   │       │       ├── BuscarClientesFieisUseCase.java
│   │   │       │       └── RecomendarVinhoUseCase.java
│   │   │       ├── domain/
│   │   │       │   ├── entity/
│   │   │       │   │   ├── ClienteEntity.java
│   │   │       │   │   ├── ProdutoEntity.java
│   │   │       │   │   └── CompraEntity.java
│   │   │       │   ├── repository/
│   │   │       │   │   ├── ClienteRepository.java
│   │   │       │   │   └── ProdutoRepository.java
│   │   │       │   └── exception/
│   │   │       │       ├── ResourceNotFoundException.java
│   │   │       │       └── ExternalApiException.java
│   │   │       └── infrastructure/
│   │   │           ├── repository/
│   │   │           │   ├── ClienteRepositoryImpl.java
│   │   │           │   └── ProdutoRepositoryImpl.java
│   │   │           ├── dto/
│   │   │           │   ├── ClienteApiDto.java
│   │   │           │   ├── ProdutoApiDto.java
│   │   │           │   └── CompraApiDto.java
│   │   │           ├── mapper/
│   │   │           │   ├── ClienteMapper.java
│   │   │           │   └── ProdutoMapper.java
│   │   │           └── config/
│   │   │               ├── WebClientConfig.java
│   │   │               └── OpenApiConfig.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test/
│       └── java/
│           └── com/digio/compras/analisecomprasapi/
│               ├── adapter/controller/
│               │   └── AnaliseComprasControllerTest.java
│               └── application/usecase/
│                   ├── ListarComprasOrdenadasUseCaseTest.java
│                   └── BuscarMaiorCompraAnoUseCaseTest.java
├── Dockerfile
└── pom.xml
```

## Profiles de Configuração

### Development (dev)
```bash
java -jar target/analisecomprasapi-1.0.0.jar --spring.profiles.active=dev
```
- Log level: DEBUG
- Actuator com todos os endpoints expostos

### Production (prod)
```bash
java -jar target/analisecomprasapi-1.0.0.jar --spring.profiles.active=prod
```
- Log level: INFO
- Actuator com endpoints limitados (health e info)
