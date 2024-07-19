<h1 align="center" style="font-weight: bold;">🏢 Gestão de Vagas 💻</h1>

<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/riatladias/gestao_vagas/blob/main/LICENSE)

</div>

## 🔥 Descrição
API REST para controle de Empresas, Vagas e aplicações, utilizando dos principais pacotes do Spring Boot para persistência, segurança, autorização entre outros conceitos.

## ⚙️ Pré-requisitos
- Java 17
- Maven
- Docker

## 🛠️ Guia de instalação

```bash
# Clonar repositório
git clone https://github.com/riatladias/gestao_vagas.git

# Entrar na pasta do projeto
cd gestao_vagas

# Executar o cantainer do banco de dados PostgreSQL
docker-compose up -d

# Executar o projeto
mvn spring-boot:run
```

## Tecnologias
- Java
- Spring Boot
- Maven
- Docker
- JPA / Hibernate
- PostgreSQL
- Spring Security / JWT
- Swagger

## Lib's
- Jakarta
- Lombok
- JWT

## 📋 Instruções de uso / Documentação

Toda documentação pode ser acessada:
http://localhost:8080/swagger-ui/index.html#/

### 📍 Endpoints - Candidate

​
| route                           | descrição                                                                                                      |
| ------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| <kbd>POST /candidate/</kbd>     | Essa função é reponsável por cadastrar um candidato, veja [detalhes](#post-create-detail)                       |
| <kbd>POST /candidate/auth</kbd> | rota de autenticação do candidato, veja [detalhes](#post-auth-detail)                                           |
| <kbd>GET /candidate/</kbd>      | Essa função é reponsável por buscar as informações do perfil do candidato, veja [detalhes](#get-profile-detail) |

---
<h3 id="post-create-detail">POST /candidate/</h3>

**REQUEST**
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "Riatla Dias",
  "username": "Dias",
  "email": "riatla@example.com",
  "password": "admin@1234",
  "description": "Desenvolvedor Java",
  "curriculum": "string"
}
```

**RESPONSE**
```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "Riatla Dias",
  "username": "Dias",
  "email": "riatla@example.com",
  "password": "admin@1234",
  "description": "Desenvolvedor Java",
  "curriculum": "string",
  "createdAt": "2024-06-14T19:35:03.093Z"
}
```
---

<h3 id="post-auth-detail">POST /candidate/auth</h3>

**REQUEST**
```json
{
  "password": "string",
  "username": "string"
}
```
---

<h3 id="get-profile-detail">GET /candidate/</h3>

**RESPONSE**
```json
{
  "name": "teste",
  "age": 20,
  "email": "lorem@gmail.com"
}
```


### 📍 Endpoints - Company

















## Modelo conceitual
![Modelo Conceitual](/assets/diagrama.png)

## Implatação em produção (Em construção)

## Autor
Riatla V.B.V. Dias

By Riatla Dias ⭐ [Visit my Linkedin](https://www.linkedin.com/in/riatladias/)
