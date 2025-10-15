# Sistema de Gestão Financeira

## 📖 Sobre o Projeto

Este é um projeto de um sistema de gestão financeira desenvolvido em Java com o framework Spring Boot. O objetivo é criar uma API RESTful robusta para gerenciar transações financeiras, começando com um módulo de controle de caixa.

Este projeto está sendo desenvolvido como parte de um processo de aprendizado e construção de portfólio, seguindo as melhores práticas de desenvolvimento de software, como arquitetura em camadas (Controller, Service, Repository) e injeção de dependências.

**Status do Projeto:** 🚀 **Módulo 1 (Controle de Caixa) - Concluído!**

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3
* **Banco de Dados:** H2 Database (em memória)
* **Acesso a Dados:** Spring Data JPA
* **Build Tool:** Maven
* **Utilitários:** Lombok

---

## ⚙️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [JDK 21](https://www.oracle.com/java/technologies/downloads/#jdk21-windows) ou superior.
* [Maven](https://maven.apache.org/download.cgi) 3.8 ou superior.
* Uma IDE de sua preferência (ex: IntelliJ, VS Code).
* Um cliente de API para testes (ex: [Postman](https://www.postman.com/downloads/) ou [Insomnia](https://insomnia.rest/download)).

---

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para executar a aplicação em seu ambiente local:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git](https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git)
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd sistema-gestao
    ```

3.  **Compile o projeto com o Maven:**
    *O Maven Wrapper (`mvnw`) cuidará de tudo para você.*
    ```bash
    ./mvnw clean install
    ```

4.  **Execute a aplicação:**
    *Você pode executar pela sua IDE, localizando a classe `SistemaGestaoApplication.java` e rodando o método `main`, ou via linha de comando:*
    ```bash
    java -jar target/sistema-gestao-0.0.1-SNAPSHOT.jar
    ```

5.  **Acesse o console do banco H2 (Opcional):**
    *Abra seu navegador e acesse `http://localhost:8080/h2-console`.*
    *Certifique-se que o campo `JDBC URL` está como `jdbc:h2:mem:gestaodb` e clique em `Connect`.*

A API estará disponível em `http://localhost:8080`.

---

## Endpoints da API (Módulo de Caixa)

A rota base para este módulo é `/caixa`.

| Método HTTP | Endpoint              | Descrição                                 | Exemplo de Body (JSON)                                            |
|-------------|-----------------------|-------------------------------------------|-------------------------------------------------------------------|
| `POST`      | `/caixa/`             | Cria um novo registro de entrada ou saída. | `{"tipo": "ENTRADA", "descricao": "Salário", "valor": 5000.00}`    |
| `GET`       | `/caixa/`             | Lista todos os registros do caixa.        | N/A                                                               |
| `PUT`       | `/caixa/{id}`         | Atualiza um registro existente pelo seu ID. | `{"tipo": "SAIDA", "descricao": "Aluguel", "valor": 1500.00}`      |
| `DELETE`    | `/caixa/{id}`         | Exclui um registro pelo seu ID.           | N/A                                                               |

---
## 📄 Documentação Completa

Para uma visão mais detalhada da arquitetura e dos fluxos de trabalho, consulte a documentação completa.

[Acessar a documentação em PDF](./docs/seu-arquivo.pdf)

---

Desenvolvido com ❤️ por [Seu Nome Aqui].
