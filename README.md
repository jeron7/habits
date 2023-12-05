# Habits

O projeto foi desenvolvido com as especificações da primeira edição da Next Level Week de 2023 da [Rocketseat](https://www.rocketseat.com.br/). 

## Objetivo

O objetivo desse projeto é criar uma aplicação que seja capaz de rastrear hábitos diários e exibí-los em um heatmap. 

### Aplicação Web

As telas que foram desenvolvidas nesse projeto podem ser encontradas no seguinte [board Figma](https://www.figma.com/community/file/1195326661124171197).
    
### REST API

Na REST API foram desenvolvidos os seguintes endpoints:

- ```POST /habits/``` criação de um hábito, informando detalhes do titulo e dias que se espera que este ele seja repetido.
- ```GET /habits/:id``` recuperar informações de um hábito a partir de seu id.
- ```GET /habits/?daysOfWeek``` recuperar todos os hábitos ou filtrá-los pelos dias da semana em que são praticados.
- ```POST /days/``` registro dos habitos realizados em um dia.
- ```GET /days/:id``` recuperar informações dos habitos práticados em um dia com pelo id.
- ```PUT /days/:id``` atualiza os habitos realizados em um dia.
- ```GET /days/summary/``` recuperar um sumário dos hábitos que foram praticados nos últimos 70 dias.
    
## Tecnologias

## Como executar
