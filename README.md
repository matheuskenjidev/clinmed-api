# 🏥 ClinMed API

API REST desenvolvida para gerenciamento de uma clínica médica, permitindo o cadastro e gerenciamento de médicos e pacientes, além de autenticação e controle de acesso.

O projeto foi desenvolvido com **Java e Spring Boot**, aplicando boas práticas de desenvolvimento de APIs REST, separação de responsabilidades, validação de dados, persistência com JPA/Hibernate e autenticação utilizando JWT.

---

## 📌 Sobre o projeto

O **ClinMed API** é uma aplicação backend voltada para o gerenciamento de informações de uma clínica médica.

A aplicação permite realizar operações de cadastro, consulta, atualização e exclusão de médicos e pacientes, além de disponibilizar um sistema de autenticação para proteger os recursos da API.

O projeto foi desenvolvido com foco em aprendizado e aplicação prática de conceitos utilizados no desenvolvimento de aplicações backend com Java e Spring Boot.

---

## 🚀 Funcionalidades

### 🔐 Autenticação

* Login de usuários
* Autenticação utilizando **Spring Security**
* Geração de tokens **JWT**
* Proteção de endpoints
* Tratamento de falhas de autenticação

### 👨‍⚕️ Médicos

* Cadastro de médicos
* Listagem de médicos
* Consulta de informações
* Atualização de dados
* Exclusão lógica de médicos
* Validação dos dados recebidos

### 🧑‍⚕️ Pacientes

* Cadastro de pacientes
* Listagem de pacientes
* Atualização de dados
* Exclusão lógica de pacientes
* Paginação e ordenação
* Validação dos dados recebidos

### ⚠️ Tratamento de erros

* Tratamento global de exceções
* Respostas HTTP apropriadas
* Tratamento de erros de validação
* Tratamento de falhas de autenticação
* Padronização das respostas de erro

---

## 🛠️ Tecnologias utilizadas

| Tecnologia          | Utilização                                   |
| ------------------- | -------------------------------------------- |
| **Java 21**         | Linguagem principal                          |
| **Spring Boot**     | Desenvolvimento da API                       |
| **Spring Web**      | Criação dos endpoints REST                   |
| **Spring Data JPA** | Persistência e acesso aos dados              |
| **Hibernate**       | ORM                                          |
| **Spring Security** | Autenticação e segurança                     |
| **JWT**             | Autenticação baseada em tokens               |
| **MySQL**           | Banco de dados relacional                    |
| **Flyway**          | Versionamento e gerenciamento das migrations |
| **Maven**           | Gerenciamento de dependências                |
| **Bean Validation** | Validação dos dados                          |
| **Lombok**          | Redução de código boilerplate                |

---

## 🏗️ Arquitetura

O projeto utiliza uma organização baseada em responsabilidades, separando os principais componentes da aplicação:

```text
src
└── main
    └── java
        └── br.com.clinmed
            ├── controller
            ├── medico
            ├── paciente
            ├── usuario
            ├── endereco
            └── infra
                ├── exception
                └── security
```

A aplicação utiliza **DTOs (Data Transfer Objects)** para controlar os dados recebidos e enviados pela API, evitando a exposição direta das entidades de persistência.

---

## 🔒 Segurança

A autenticação da aplicação é realizada utilizando **Spring Security**.

Após o login, a API gera um **token JWT**, que deve ser enviado nas requisições aos endpoints protegidos através do header:

```http
Authorization: Bearer SEU_TOKEN
```

O fluxo de autenticação segue, de forma simplificada:

```text
Cliente
   │
   │ Login + senha
   ▼
API
   │
   │ Autenticação
   ▼
Spring Security
   │
   │ Credenciais válidas
   ▼
JWT
   │
   │ Token
   ▼
Cliente
   │
   │ Requisição autenticada
   ▼
Endpoint protegido
```

---

## 📡 Principais endpoints

### Autenticação

```http
POST /login
```

Realiza a autenticação do usuário e retorna um token JWT.

### Médicos

```http
POST   /medicos
GET    /medicos
PUT    /medicos
DELETE /medicos/{id}
```

### Pacientes

```http
POST   /pacientes
GET    /pacientes
PUT    /pacientes
DELETE /pacientes/{id}
```

Os endpoints que exigem autenticação devem receber o token JWT no header `Authorization`.

---

## 📄 Exemplo de cadastro de médico

### Request

```http
POST /medicos
Content-Type: application/json
```

```json
{
    "nome": "João Silva",
    "email": "joao.silva@email.com",
    "crm": "123456",
    "telefone": "11999999999",
    "especialidade": "CARDIOLOGIA",
    "endereco": {
        "logradouro": "Rua Exemplo",
        "bairro": "Centro",
        "cep": "01001000",
        "numero": "100",
        "complemento": "Sala 2",
        "cidade": "São Paulo",
        "uf": "SP"
    }
}
```

---

## 📄 Exemplo de resposta

```json
{
    "id": 1,
    "nome": "João Silva",
    "email": "joao.silva@email.com",
    "crm": "123456",
    "especialidade": "CARDIOLOGIA",
    "endereco": {
        "logradouro": "Rua Exemplo",
        "bairro": "Centro",
        "cep": "01001000",
        "numero": "100",
        "complemento": "Sala 2",
        "cidade": "São Paulo",
        "uf": "SP"
    }
}
```

---

## 🗄️ Banco de dados

O projeto utiliza **MySQL** como banco de dados e **Flyway** para controle das alterações do schema.

As migrations são executadas de forma versionada, permitindo manter a estrutura do banco sincronizada com as versões da aplicação.

Exemplo:

```text
db
└── migration
    ├── V1__create-table-medicos.sql
    ├── V2__create-table-pacientes.sql
    └── V3__alter-table-medicos-add-column-ativo.sql
```

---

## ⚙️ Como executar o projeto

### Pré-requisitos

Antes de executar a aplicação, certifique-se de possuir instalado:

* Java 21
* Maven
* MySQL
* Git

### 1. Clone o repositório

```bash
git clone https://github.com/SEU-USUARIO/clinmed-api.git
```

### 2. Acesse o projeto

```bash
cd clinmed-api
```

### 3. Configure o banco de dados

Crie um banco MySQL:

```sql
CREATE DATABASE clinmed_api;
```

Configure as propriedades de conexão no arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinmed_api
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Execute a aplicação

Utilizando Maven:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```

---

## 🧪 Testando a API

A API pode ser testada utilizando ferramentas como:

* Postman
* Insomnia
* Bruno
* cURL

O fluxo recomendado para testar a aplicação é:

```text
1. Criar usuário
       ↓
2. Realizar login
       ↓
3. Obter token JWT
       ↓
4. Enviar token nas requisições
       ↓
5. Utilizar os endpoints protegidos
```

---

## 📚 Conceitos aplicados

Durante o desenvolvimento do projeto foram aplicados conceitos importantes de desenvolvimento backend, incluindo:

* Desenvolvimento de APIs REST
* HTTP e códigos de status
* CRUD
* DTOs
* Validação de dados
* Injeção de dependências
* Spring IoC
* JPA e Hibernate
* Relacionamento entre entidades
* Paginação
* Autenticação e autorização
* Spring Security
* JWT
* Tratamento global de exceções
* Migrations com Flyway
* Persistência em banco de dados relacional
* Organização e separação de responsabilidades

---

## 🎯 Objetivo

O objetivo do projeto é consolidar conhecimentos em **Java, Spring Boot e desenvolvimento backend**, simulando a construção de uma API utilizada em um cenário real de negócio.

Além da implementação das funcionalidades, o projeto busca aplicar práticas de organização, segurança, validação e manutenção de código.

---

## 👨‍💻 Autor

**Matheus Kenji**

Desenvolvedor Backend Java em formação, com foco em desenvolvimento de APIs utilizando **Java e Spring Boot**.

---

## 📌 Status

🚧 **Em desenvolvimento**

Novas funcionalidades e melhorias poderão ser adicionadas ao projeto conforme a evolução dos estudos em desenvolvimento backend.
