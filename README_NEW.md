# API de Análise de Compras de Vinhos - Digio

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-green.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📋 Descrição

Microsserviço desenvolvido em Spring Boot WebFlux para análise de compras de vinhos da loja Digio. A aplicação consome dados de APIs externas e fornece endpoints REST para análise de compras, identificação de clientes fiéis e recomendação de produtos.

## 🏗️ Arquitetura

O projeto segue os princípios de **Clean Architecture** e **SOLID**, com separação clara de responsabilidades em camadas:

```
📦 analisecomprasapi
├── 📂 domain                    # Camada de Domínio
│   ├── entity                   # Entidades de negócio
│   ├── repository               # Interfaces de repositório
│   └── exception                # Exceções de domínio
├── 📂 application               # Camada de Aplicação
│   ├── usecase                  # Casos de uso (lógica de negócio)
│   └── dto                      # DTOs internos da aplicação
├── 📂 infrastructure            # Camada de Infraestrutura
│   ├── repository               # Implementações de repositórios
│   ├── client                   # Clientes de APIs externas
│   ├── mapper                   # Mapeadores de infraestrutura
│   └── config                   # Configurações
└── 📂 adapter                   # Camada de Adaptadores
    ├── controller               # Controllers REST
    ├── dto                      # DTOs de resposta
    ├── mapper                   # Mapeadores de adaptadores
    └── exception                # Handlers de exceção
```

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3.4.4**
- **Spring WebFlux** (Programação Reativa)
- **Project Reactor** (Mono/Flux)
- **SpringDoc OpenAPI** (Documentação Swagger)
- **Spring Boot Actuator** (Health Checks)
- **JUnit 5** (Testes Unitários)
- **Mockito** (Mocks para testes)
- **WebTestClient** (Testes de Integração)
- **Maven** (Gerenciamento de dependências)

## 📡 Endpoints

### 1. Listar Compras Ordenadas
```http
GET /compras
```
Retorna lista de compras ordenadas por valor crescente, contendo dados dos clientes, produtos, quantidades e valores totais.

### 2. Maior Compra por Ano
```http
GET /maior-compra/{ano}
```
Retorna a maior compra do ano especificado com todos os dados disponíveis.

**Exemplo:**
```http
GET /maior-compra/2020
```

### 3. Clientes Mais Fiéis
```http
GET /clientes-fieis
```
Retorna o Top 3 clientes mais fiéis (com mais compras recorrentes e maiores valores gastos).

### 4. Recomendação de Vinho
```http
GET /recomendacao/cliente/tipo
```
Retorna recomendações de vinho para cada cliente baseado nos tipos mais comprados.

## 📚 Documentação da API

A documentação completa da API está disponível via Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

Documentação OpenAPI (JSON):
```
http://localhost:8080/api-docs
```

## 🔧 Configuração e Execução

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+

### Executar em Modo Desenvolvimento

```bash
cd analisecomprasapi
./mvnw spring-boot:run
```

Ou no Windows:
```bash
cd analisecomprasapi
mvnw.cmd spring-boot:run
```

### Executar em Modo Produção

```bash
./mvnw clean package -Pprod
java -jar target/analisecomprasapi-1.0.0.jar
```

### Executar Testes

```bash
# Todos os testes
./mvnw test

# Com cobertura
./mvnw clean test jacoco:report
```

## 🏥 Health Check

O serviço expõe endpoints de monitoramento via Actuator:

```http
GET /actuator/health
GET /actuator/info
GET /actuator/metrics
```

## 🎯 Fontes de Dados

A aplicação consome dados das seguintes APIs externas:

- **Produtos**: https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json
- **Clientes**: https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json

## 🧪 Testes

O projeto possui cobertura abrangente de testes:

- **Testes Unitários**: Casos de uso isolados com mocks
- **Testes de Integração**: Controllers com WebTestClient
- **Testes de Entidades**: Validação de regras de domínio

### Executar Testes Específicos

```bash
# Testes de um caso de uso específico
./mvnw test -Dtest=ListarComprasOrdenadasUseCaseTest

# Testes do controller
./mvnw test -Dtest=AnaliseComprasControllerTest
```

## 📦 Build e Deploy

### Gerar JAR Executável

```bash
./mvnw clean package -DskipTests
```

O arquivo JAR será gerado em: `target/analisecomprasapi-1.0.0.jar`

### Executar com Docker

```bash
# Build da imagem
docker build -t analisecompras-api:1.0.0 -f analisecomprasapi/Dockerfile .

# Executar container
docker run -p 8080:8080 analisecompras-api:1.0.0
```

Ou usando Docker Compose:

```bash
docker-compose up
```

## 🔒 Qualidade de Código

O projeto segue rigorosamente:

- ✅ **Princípios SOLID**
- ✅ **Clean Code**
- ✅ **Clean Architecture**
- ✅ **Programação Reativa**
- ✅ **Separação de Responsabilidades**
- ✅ **Baixo Acoplamento**
- ✅ **Alta Coesão**
- ✅ **Documentação JavaDoc**
- ✅ **Testes Unitários e de Integração**

## 📝 Versionamento

O projeto segue **Semantic Versioning** (SemVer):

- **MAJOR**: Mudanças incompatíveis na API
- **MINOR**: Novas funcionalidades compatíveis
- **PATCH**: Correções de bugs

**Versão Atual**: 1.0.0

## 👥 Autor

**Alvaro Oliveira**

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

---

**Desenvolvido com ❤️ para o Desafio Digio**
