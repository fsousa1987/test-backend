# Projeto de Captura de Licitações - README.md

## Descrição

Este repositório contém a implementação de um serviço de captura de licitações baseado nos serviços da Effecti. O objetivo é extrair informações sobre as licitações disponíveis em http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp e disponibilizá-las através de uma API que permita aos usuários marcar as oportunidades como lidas ou não lidas.

## Desafio

O desafio consiste em:

1. Desenvolver um serviço de captura em Java para obter as informações das licitações.
2. Modelar os dados que serão extraídos dessa captura.
3. Opcionalmente, implementar a persistência dessas informações.

## Requisitos

Para atender aos requisitos do desafio, o projeto conta com as seguintes características:

- Implementação do back-end utilizando o conceito de REST para as operações da API.
- A captura é preferencialmente realizada em Java.


## Tecnologias Utilizadas

- Java
- Spring Boot
- REST API
- H2 (Não foi feita persistência de dados ainda)
- ModelMapper
- Jsoup
- [Outras tecnologias podem ser adicionadas conforme necessário]

## Instruções de Uso

1. Clonar este repositório.
2. Configurar as dependências e ambiente de execução conforme descrito no `application.properties`.
3. Executar a aplicação.
4. Acesse a API através do endpoint especificado.
5. A aplicação está rodando no encode ISO-8859-1. 

---

**Nota1:** Este é um projeto fictício para demonstrar habilidades de desenvolvimento com base no desafio proposto pela Effecti. Não está diretamente associado à Effecti ou ao http://comprasnet.gov.br/.

**Nota2:** Achei muito interessante o Java scraping para retirar informações de uma página HTML. Mesmo em caso de reprovação neste processo seletivo, pretendo continuar desenvolvendo esse projeto.

**Nota3:** Agradeço a todos que chegaram aqui e volte sempre.



