# Projeto Básico usado como desafio curso de Spring Boot Com Kotlin.

## Especificação do desafio

### Esse é o seu desafio, caso aceite =)

Você deverá desenvolver uma API responsável pelos dados dos planetas de Star wars.

- [X] Essa API deve ser REST.

- Os planetas devem ter no mínimo os seguintes atributos:

  + [X] Nome (VARCHAR(255))

  + [X] Clima (Enumeração que vem de outra tabela - Descrição VARCHAR(50))

  + [X] Terreno (Enumeração que vem de outra tabela - Descrição VARCHAR(50))

- Os dados devem ser persistidos em algum banco de dados a sua escolha.
  + [X] MariaDB / MySQL
    + No nosso caso foi no MariaDB e tem migrações usando o Flyway.

- Você deve implementar todas as operações de CRUD.

- Listagens de : 
  - [X] Terrenos
  - [X] Climas
  - [X] Planetas <Nome, id>
  - [X] Listagem de detalhes do planeta com regiões
  
- Adicionar
  - [X] Terreno
  - [X] Clima
  - [X] Planeta
    - [X] Região em Planeta

- Você deve implementar alguns métodos de busca a sua escolha.
  - [x] Busca por nome de Clima  
  - [X] Busca por nome de planeta
  - [X] Busca por nome de Terreno

- Detalhamento de Clima e Terreno.
  - Para o detalhamento deles, eu aponto além da característica, os planetas que as utilizam.

- [X] Sua api deverá fazer uso de cache.
    - Cache usando o padrão do SpringBoot
      + Foi usada no Terreno e no Clima, pois eles sofrem menos alterações que os planetas.  
  
- [ ] Sua api deverá ser documentada.
  - Exemplos de como acessar a API podem ser vistos no Postman in : https://www.postman.com/spedison/workspace/planetasstarwars/overview 
  - Usando o ______
  - Para a versão 2.6.2 do SpringBoot o Swaddger ou qualquer outro não tem o devido suporte.
  - A documentação foi realizada no PostMan na URL: [PostMan Doc Requests.](https://www.postman.com/spedison/workspace/planetasstarwars/overview)  

- [X] Sua api deverá utilizar o Actuator.
  * http://<HOST>:<PORTA>/actuator/
  * Referência : https://www.baeldung.com/spring-boot-actuators
  * Também tem documentação no Postman.

- [X] Sua api deverá estar versionada no Github.
  - Local público em: [Git PlanetasStarWars @ Spedison](https://github.com/spedison/desafio-planetas-stars-wars) 

#Bônus:

* [X] Sua Api deverá ser " Dockerizada" e deverá ser possível rodá-la através de Docker-Compose.
  - Documentado em doc/como_compilar.md

* [ ] Ao finalizar enviar um e-mail para eluminumsoluçoes@gmail.com com o link do Github do seu projeto.

* [ ] Adicionar autenticação para segurança da API

* [ ] Adicionar segurança para o Actuator

Boa diversão.
