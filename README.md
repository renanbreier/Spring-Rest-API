
# 📚 API RESTful - Sistema de Gerenciamento de Cursos

Este projeto é uma API RESTful desenvolvida em **Spring Boot** com **MySQL**, seguindo as melhores práticas de desenvolvimento back-end. O sistema permite o gerenciamento de **alunos**, **cursos**, **instrutores** e **inscrições** entre alunos e cursos.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Apache Maven
- NGINX (para balanceamento de carga)
- Apache JMeter (para testes de performance)

---

## 📌 Estrutura da API (Principais Endpoints)

### 🔗 Alunos (`/api/alunos`)

| Método | Endpoint                   | Descrição                             |
|------- |--------------------------- |------------------------------------- |
| GET    | `/api/alunos`              | Listar todos os alunos              |
| POST   | `/api/alunos`              | Criar um novo aluno                 |
| GET    | `/api/alunos/{id}`         | Buscar aluno por ID                 |
| PUT    | `/api/alunos/{id}`         | Atualizar um aluno existente        |
| DELETE | `/api/alunos/{id}`         | Remover um aluno                    |
| GET    | `/api/alunos/{id}/cursos`  | Listar cursos em que o aluno está matriculado |

---

### 🔗 Cursos (`/api/cursos`)

| Método | Endpoint                   | Descrição                             |
|------- |--------------------------- |------------------------------------- |
| GET    | `/api/cursos`              | Listar todos os cursos              |
| POST   | `/api/cursos`              | Criar um novo curso                 |
| GET    | `/api/cursos/{id}`         | Buscar curso por ID                 |
| PUT    | `/api/cursos/{id}`         | Atualizar um curso existente        |
| DELETE | `/api/cursos/{id}`         | Remover um curso                    |
| GET    | `/api/cursos/{id}/alunos`  | Listar alunos matriculados no curso |

---

### 🔗 Instrutores (`/api/instrutores`)

| Método | Endpoint                      | Descrição                      |
|------  |------------------------------ |------------------------------ |
| GET    | `/api/instrutores`            | Listar todos os instrutores   |
| POST   | `/api/instrutores`            | Criar um novo instrutor       |
| GET    | `/api/instrutores/{id}`       | Buscar instrutor por ID       |
| PUT    | `/api/instrutores/{id}`       | Atualizar um instrutor        |
| DELETE | `/api/instrutores/{id}`       | Remover um instrutor          |

---

### 🔗 Inscrições (`/api/inscricoes`)

| Método | Endpoint                  | Descrição                                   |
|------  |-------------------------- |------------------------------------------ |
| GET    | `/api/inscricoes`         | Listar todas as inscrições                |
| POST   | `/api/inscricoes`         | Criar uma nova inscrição (aluno em curso) |
| GET    | `/api/inscricoes/{id}`    | Buscar inscrição por ID                  |
| PUT    | `/api/inscricoes/{id}`    | Atualizar uma inscrição                  |
| DELETE | `/api/inscricoes/{id}`    | Remover uma inscrição                    |

---

## 🛠️ Como Executar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```

2. Compile o projeto:

```bash
mvn clean package
```

3. Execute a aplicação:

```bash
java -jar target/nome-do-seu-jar.jar --server.port=8081
```

> Suba mais de uma instância em portas diferentes se desejar usar o NGINX como balanceador.

---

## ⚙️ Balanceamento de Carga com NGINX

Para testes de escalabilidade, o projeto inclui um exemplo de configuração de NGINX como balanceador de carga.  
Veja o arquivo:

```
nginx/nginx.conf
```

## 🧪 Testes de Performance com JMeter

Os testes de performance foram realizados utilizando o Apache JMeter.

Foram comparados dois cenários:

- **Sem balanceamento:** Requisições diretas para uma instância da API.
- **Com balanceamento:** Requisições passando pelo NGINX distribuindo entre múltiplas instâncias.

Relatórios e gráficos comparativos estão disponíveis na pasta:

```
performance-tests/
```

---

## ✅ Conclusão

Este projeto demonstra conceitos fundamentais de:

- API RESTful com Spring Boot
- Persistência com JPA + MySQL
- Balanceamento de carga com NGINX
- Testes de performance com JMeter
- Padrões de desenvolvimento com DTOs para evitar recursividade

---

## 👨‍💻 Autores

- Renan Breier
- Gustavo Silva
- Cauã Moreto
