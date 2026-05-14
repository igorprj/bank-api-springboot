# Bank API - Spring Boot

API REST bancária desenvolvida com Java e Spring Boot com foco em operações bancárias básicas, persistência de dados e arquitetura back-end.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman

## Funcionalidades

- Cadastro de contas bancárias
- Consulta de contas
- Atualização de dados
- Exclusão de contas
- Persistência de dados com MySQL
- Estrutura em DTOs
- Testes de endpoints com Postman

## Objetivo do Projeto

Este projeto foi desenvolvido para aprimorar conhecimentos em:

- Desenvolvimento Back-end com Java
- Criação de APIs REST
- Integração com banco de dados
- Arquitetura utilizando Spring Boot
- Organização de código e boas práticas

## Estrutura do Projeto

```bash
src/
 ├── controller
 ├── service
 ├── repository
 ├── dto
 ├── entity
 └── config
Como Executar o Projeto
Pré-requisitos
  Java 17+
  MySQL
  Maven
Clonando o repositório
git clone https://github.com/SEU-USUARIO/bank-api-springboot.git
Configurando o banco de dados

Configure o arquivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/bank_api
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

Executando o projeto
mvn spring-boot:run
Testes da API

Os endpoints podem ser testados utilizando:

Postman
Insomnia

Melhorias Futuras
  Autenticação com JWT
  Spring Security
  Transferências bancárias
  Documentação com Swagger
  Tratamento global de exceções
  Deploy da aplicação

Autor
Igor Carvalho
Linkedin: www.linkedin.com/in/igor-carvalho-6a9619334

