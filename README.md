# Vendinha da Vó Cecília

Este é um projeto desenvolvido como parte de um teste para a vaga de Desenvolvedor Web na Empresa Sertão. O objetivo é criar um sistema simples para controle de estoque da "Vendinha da Vó Cecília", onde é possível realizar o cadastro, listagem, edição e exclusão de produtos, além de permitir login de usuários.

## Tecnologias Utilizadas

- **Backend**: Java com Spring Boot
- **Frontend**: React.js
- **Banco de Dados**: MySQL
- **Autenticação**: JWT (JSON Web Tokens) para controle de login
- **Persistência de Dados**: Spring Data JPA
- **Documentação da API**: Swagger (Springdoc OpenAPI)

## Dependências do Projeto

No backend, foram incluídas as seguintes dependências:

- **Spring Web**: Para criação dos endpoints RESTful.
- **Spring Data JPA**: Para integração com o banco de dados usando JPA (Hibernate).
- **Spring Security**: Para implementar autenticação e autorização.
- **MySQL Driver**: Para conexão com o banco de dados MySQL.
- **Springdoc OpenAPI**: Para gerar a documentação da API com Swagger.

### Dependências no `pom.xml`

- **Spring Web**: Para criar endpoints RESTful e trabalhar com HTTP.

- **Spring Data JPA**: Para integração com o banco de dados MySQL usando JPA e Hibernate.

- **Spring Security**: Para implementar autenticação e autorização.

- **MySQL Driver**: Para conectar o Spring Boot ao banco de dados MySQL.

- **Swagger (Springdoc OpenAPI)**: Para documentar automaticamente os endpoints da API usando Swagger UI.

- **Spring Boot DevTools** (opcional): Para facilitar o desenvolvimento com reload automático durante mudanças no código.

## Como Executar o Projeto

### Pré-requisitos

1. Ter o [Node.js](https://nodejs.org/) instalado para executar o frontend.
2. Ter o [Java 23+](https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html) e o [Maven](https://maven.apache.org/) instalados para rodar o backend.
3. Ter o MySQL instalado e configurado.

### Passos para Executar o Backend (Spring Boot)

1. Clone o repositório:
   ```bash
   git clone git@github.com:PedroWC/vendinha-vo-cecilia.git
   ```

2. Acesse a pasta do backend:
   ```bash
   cd vendinha-vo-cecilia/backend
   ```

3. Configure o arquivo `application.properties` com as credenciais do banco de dados MySQL:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/vendinha
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

O backend será executado em `http://localhost:8080`.

### Passos para Executar o Frontend (React)

1. Acesse a pasta do frontend:
   ```bash
   cd vendinha-vo-cecilia/frontend
   ```

2. Instale as dependências:
   ```bash
   npm install
   ```

3. Execute o projeto:
   ```bash
   npm start
   ```

O frontend será executado em `http://localhost:3000`.

## Estrutura do Projeto

```plaintext
vendinha-vo-cecilia/
├── backend/       # Código do backend em Java (Spring Boot)
├── frontend/      # Código do frontend em React
└── README.md      # Documentação do projeto
```

## Modelo de Classes do Banco de Dados

Aqui está o modelo de classes que será utilizado para persistência dos dados no banco MySQL:

```plaintext
+-----------------+         +------------------+
|     User        |         |    Product        |
+-----------------+         +------------------+
| id (PK)         |         | id (PK)           |
| username        |         | name              |
| password        |         | description       |
| email (opcional)|         | price             |
| role (opcional) |         | quantityInStock   |
+-----------------+         +------------------+
                                  |
                                  |
                          +-----------------+
                          |    Category     |
                          +-----------------+
                          | id (PK)         |
                          | name            |
                          +-----------------+

                          (opcional)
                                  |
                                  |
                          +------------------------+
                          |    StockMovement       |
                          +------------------------+
                          | id (PK)                |
                          | product (FK)           |
                          | quantity               |
                          | movementDate           |
                          | type                   |
                          +------------------------+
```

### Entidades Principais

1. **User**: Representa os usuários do sistema que podem fazer login e gerenciar os produtos.
2. **Product**: Representa os produtos da vendinha, contendo atributos como nome, descrição, preço e quantidade em estoque.
3. **Category** (opcional): Organiza os produtos em diferentes categorias.
4. **StockMovement** (opcional): Controla as movimentações de estoque, como entrada e saída de produtos.

## Documentação dos Endpoints (Swagger)

A documentação dos endpoints da API pode ser acessada após iniciar o backend na seguinte URL:

```
http://localhost:8080/swagger-ui/index.html
```

## Considerações Finais

Este projeto foi desenvolvido com foco na organização do código, eficiência e usabilidade. Caso tenha dificuldades na execução ou surjam dúvidas, sinta-se à vontade para entrar em contato.