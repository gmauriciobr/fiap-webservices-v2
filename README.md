# Trabalho Webservices & Restfull Technologies

- Turma 40SCJ
- Professor: Eduardo Galego

#### Grupo

- Guilherme Mauricio - RM: 340879
- Douglas Mateus - RM: 342031
- João Rafael - RM: 342038
- Julia Assunção - RM: 341934

#### Definição

Este documento tem como objetivo descrever os requisitos para a elaboração de um projeto usado como avaliação da disciplina, portanto escolhemos montar uma aplicação de marcação de pontos de entradas e saídas, muito usado em empresas para registrar as horas trabalhadas de cada colaborador.

#### Especificações

##### Backend - JAVA

- Java 11
- Spring Framework
- Spring Boot
- Spring Data
- SpringFox
- Lombok
- Flyway
- JUnit
- MySQL 8
- H2
- Gradle
- Docker

##### Frontend - React

- ReactJS
- React-redux
- Redux-saga
- Axios
- Styled-components
- Reactotron

#### Requisitos

- Docker

#### Como executar o projeto:

\*\* O terminal deve estar na pasta raiz do projeto

1. Executar o comando para fazer o build e aguardar a execução:

   ```
      docker-compose build
   ```

2. Depois de executado o comando anterior, executar o seguinte:

   ```
      docker-compose up -d
   ```

#### Link

**Swagger:** http://localhost:11080

O projeto irá executar na porta 8080 (http://localhost:8080)

#### Extras

- Como executar o storybook?

1. Para o yarn, execute:
   ```
   yarn storybook
   ```
2. Para o NPM, execute:
   ```
   npm run storybook
   ```
