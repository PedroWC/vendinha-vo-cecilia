# Informações importantes

## **Endpoints Disponíveis**

---

### **Autenticação (AuthController)**

#### POST /api/auth/logi

    -> Descrição: Autentica um usuário e retorna um token JWT para aces.
    -> Parâmetros: `LoginDTO` (JSON no corpo da requisição) conten:
        - `email`: Email do usuário.
        - `password`: Senha do usuário.
    -> Retorno: Token JWT no formato de stri.
    -> Respostas:
        - `200`: Autenticação bem-sucedida com token JWT.
        - `401`: Credenciais inválidas.
        - `500`: Erro interno no servidor.

---

### **Produtos (ProductController)**

#### GET /api/product

    -> Descrição: Retorna uma lista de todos os produt.
    -> Parâmetros: Nenh.
    -> Retorno: Lista de `ProductDT.
    -> Respostas:
        - `200`: Lista de produtos retornada com sucesso.
        - `500`: Erro interno no servidor.

#### GET /api/products/{id

    -> Descrição: Retorna um produto específico com base no ID forneci.
    -> Parâmetros:
        - `id`: ID do produto (Path Variable).
    -> Retorno: `ProductDTO` com os detalhes do produ.
    -> Respostas:
        - `200`: Produto retornado com sucesso.
        - `404`: Produto não encontrado.
        - `500`: Erro interno no servidor.

#### POST /api/product

    -> Descrição: Cria um novo produto e retorna os detalhes do produto cria.
    -> Parâmetros: `ProductDTO` (JSON no corpo da requisiçã.
    -> Retorno: `ProductDTO` com os detalhes do produto cria.
    -> Respostas:
        - `200`: Produto criado com sucesso.
        - `400`: Erro de validação.
        - `500`: Erro interno no servidor.

#### PUT /api/products/{id

    -> Descrição: Atualiza um produto existente com base no ID forneci.
    -> Parâmetros:
        - `id`: ID do produto (Path Variable).
        - `ProductDTO` (JSON no corpo da requisição).
    -> Retorno: `ProductDTO` com os detalhes do produto atualiza.
    -> Respostas:
        - `200`: Produto atualizado com sucesso.
        - `404`: Produto não encontrado.
        - `500`: Erro interno no servidor.

#### DELETE /api/products/{id

    -> Descrição: Exclui um produto existente com base no ID forneci.
    -> Parâmetros:
        - `id`: ID do produto (Path Variable).
    -> Retorno: Nenh.
    -> Respostas:
        - `204`: Produto excluído com sucesso.
        - `404`: Produto não encontrado.
        - `500`: Erro interno no servidor.

---

### **Usuários (UserController)**

#### GET /api/user

    -> Descrição: Retorna uma lista com todos os usuários cadastrad.
    -> Parâmetros: Nenh.
    -> Retorno: Lista de `UserDT.
    -> Respostas:
        - `200`: Lista de usuários retornada com sucesso.
        - `500`: Erro interno no servidor.

#### GET /api/users/{id

    -> Descrição: Retorna um usuário específico com base no ID forneci.
    -> Parâmetros:
        - `id`: ID do usuário (Path Variable).
    -> Retorno: `UserDTO` com os detalhes do usuár.
    -> Respostas:
        - `200`: Usuário retornado com sucesso.
        - `404`: Usuário não encontrado.
        - `500`: Erro interno no servidor.

#### POST /api/user

    -> Descrição: Cria um novo usuár.
    -> Parâmetros: `UserDTO` (JSON no corpo da requisiçã.
    -> Retorno: `UserDTO` com os detalhes do usuário cria.
    -> Respostas:
        - `201`: Usuário criado com sucesso.
        - `400`: Requisição inválida.
        - `500`: Erro interno no servidor.

#### PUT /api/users/{id

    -> Descrição: Atualiza um usuário existente com base no ID forneci.
    -> Parâmetros:
        - `id`: ID do usuário (Path Variable).
        - `UserDTO` (JSON no corpo da requisição).
    -> Retorno: `UserDTO` com os detalhes do usuário atualiza.
    -> Respostas:
        - `200`: Usuário atualizado com sucesso.
        - `404`: Usuário não encontrado.
        - `500`: Erro interno no servidor.

#### DELETE /api/users/{id

    -> Descrição: Exclui um usuário existente com base no ID forneci.
    -> Parâmetros:
        - `id`: ID do usuário (Path Variable).
    -> Retorno: Nenh.
    -> Respostas:
        - `204`: Usuário excluído com sucesso.
        - `404`: Usuário não encontrado.
        - `500`: Erro interno no servidor.

---

### **Estrutura dos DTOs**

### **ProductDTO**

Representa as informações de um produto.

#### Parâmetros:

    - `id` (Long): ID do produto (opcional para criação, obrigatório para atualização).
    - `name` (String): Nome do produto (obrigatório).
    - `description` (String): Descrição do produto (opcional).
    - `price` (Double): Preço do produto (obrigatório).
    - `quantity` (Integer): Quantidade disponível em estoque (obrigatório).
    - `categoryId` (Long): ID da categoria associada ao produto (obrigatório).

---

### **UserDTO**

Representa as informações de um usuário.

#### Parâmetros:

    - `id` (Long): ID do usuário (opcional para criação, obrigatório para atualização).
    - `username` (String): Nome de usuário (obrigatório).
    - `email` (String): Email do usuário (obrigatório).
    - `password` (String): Senha do usuário (obrigatório ao criar, opcional ao atualizar).
    - `role` (String): Papel do usuário (ex: ROLE_USER, ROLE_ADMIN) (opcional).

---

### **LoginDTO**

Utilizado para autenticação de usuários.

#### Parâmetros:

    - `email` (String): Email do usuário (obrigatório).
    - `password` (String): Senha do usuário (obrigatório).

---