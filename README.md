# Projeto AsCan

- Projeto criado para avaliação do desafio final de backend e sql do Ascan (Programa de estágio do Instituto Atlântico)

## Descrição

- O projeto consiste em uma API Rest que faz o gerenciamento de assinaturas de um serviço de streaming

## Detalhamento

- A API faz esse gerenciamento utilizando o framework Springboot, serviço de mensageria com o RabbitMQ e banco de dados MySQL
- Ela segue o seguinte fluxo detalhado no Diagrama de sequência:
-   ![image](https://user-images.githubusercontent.com/62728067/170690458-99798142-9753-4a9b-b58f-63ec557d896c.png)

## Rodando o projeto localmente:

- Clonar o projeto
- Executar o comando docker-compose -up
- Rodar o build e testar a aplicação
- A API apresenta uma documentação com a utilização de Swagger onde podem ser testados os endpoints e notificações da mesma.
