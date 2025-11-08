# Guia Rápido - Análise de Compras API

## 🚀 Início Rápido

### Opção 1: Docker (Recomendado)
```bash
# Clone o repositório
git clone https://github.com/AlvOliveira/analisecompras-api.git

# Entre no diretório
cd analisecompras-api

# Suba o ambiente
docker-compose up --build -d

# Aguarde 10-15 segundos e acesse
http://localhost:8080/swagger-ui.html
```

### Opção 2: Execução Local
```bash
# Entre no diretório do projeto
cd analisecompras-api/analisecomprasapi

# Windows
.\mvnw.cmd clean package
java -jar target/analisecomprasapi-1.0.0.jar

# Linux/MacOS
./mvnw clean package
java -jar target/analisecomprasapi-1.0.0.jar
```

---

## 📋 Endpoints Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/compras` | Lista todas as compras ordenadas |
| GET | `/maior-compra/{ano}` | Maior compra de um ano específico |
| GET | `/clientes-fieis` | Top 3 clientes mais fiéis |
| GET | `/recomendacao/cliente/tipo` | Recomendação de vinho |

---

## 🧪 Testar os Endpoints

### Usando os Scripts Fornecidos

**Windows:**
```bash
testCompras.cmd
testMaiorCompraAno.cmd
testClientesFieis.cmd
testRecomendacao.cmd
```

**Linux/MacOS:**
```bash
chmod +x *.sh
./testCompras.sh
./testMaiorCompraAno.sh
./testClientesFieis.sh
./testRecomendacao.sh
```

### Usando o Navegador
- Compras: http://localhost:8080/compras
- Maior Compra 2019: http://localhost:8080/maior-compra/2019
- Clientes Fiéis: http://localhost:8080/clientes-fieis
- Recomendação: http://localhost:8080/recomendacao/cliente/tipo

### Usando Swagger
http://localhost:8080/swagger-ui.html

---

## 📊 Monitoramento (Actuator)

- Health: http://localhost:8080/actuator/health
- Info: http://localhost:8080/actuator/info
- Metrics: http://localhost:8080/actuator/metrics

---

## 🧪 Executar Testes

```bash
# Windows
.\mvnw.cmd test

# Linux/MacOS
./mvnw test
```

---

## 🐳 Comandos Docker Úteis

```bash
# Subir o ambiente
docker-compose up -d

# Ver logs
docker-compose logs -f

# Parar o ambiente
docker-compose down

# Rebuild
docker-compose up --build -d

# Remover tudo (incluindo volumes)
docker-compose down -v
```

---

## 🔧 Profiles

### Development (mais logs)
```bash
java -jar target/analisecomprasapi-1.0.0.jar --spring.profiles.active=dev
```

### Production (menos logs)
```bash
java -jar target/analisecomprasapi-1.0.0.jar --spring.profiles.active=prod
```

---

## 📦 Tecnologias

- Java 17
- Spring Boot 2.7.18
- Spring WebFlux (Reativo)
- Docker
- Maven

---

## 🏗️ Arquitetura

Clean Architecture com 4 camadas:
1. **Domain** - Entidades e regras de negócio
2. **Application** - Casos de uso
3. **Infrastructure** - Integrações externas
4. **Adapter** - Controllers e DTOs

---

## 📝 Exemplos de Resposta

### GET /compras
```json
[
  {
    "nomeCliente": "João Silva",
    "cpf": "111.222.333-44",
    "valorTotal": 1500.00,
    "compras": [
      {
        "codigo": "123",
        "variedade": "Tinto",
        "safra": "2018",
        "preco": 150.00,
        "quantidade": 10,
        "ano": 2020
      }
    ]
  }
]
```

### GET /clientes-fieis
```json
[
  {
    "nome": "Maria Santos",
    "cpf": "999.888.777-66",
    "quantidadeCompras": 25,
    "valorTotal": 10500.00
  }
]
```

---

## 🆘 Solução de Problemas

### Porta 8080 já em uso
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/MacOS
lsof -ti:8080 | xargs kill -9
```

### Docker não está rodando
```bash
# Verifique se o Docker está ativo
docker --version
docker ps
```

### Erro de compilação
```bash
# Limpe e recompile
mvnw clean install -U
```

---

## 📚 Mais Informações

- [README.md](README.md) - Documentação completa
- [VALIDACAO_IMPLEMENTACAO.md](VALIDACAO_IMPLEMENTACAO.md) - Validação técnica
- Swagger UI: http://localhost:8080/swagger-ui.html

---

**Versão:** 1.0.0  
**Última Atualização:** 08/11/2025
