# Projeto Básico usado como desafio curso de Spring Boot Com Kotlin.

## Especificação do desafio

###Esse é o seu desafio, caso aceite =)

Você deverá desenvolver uma API responsável pelos dados dos planetas de Star wars.

- Essa API deve ser REST.

- Os planetas devem ter no mínimo os seguintes atributos:

  + Nome (VARCHAR(255))

  + Clima (Enumeração que vem de outra tabela - Descrição VARCHAR(50))

  + Terreno (Enumeração que vem de outra tabela - Descrição VARCHAR(50))

- Os dados devem ser persistidos em algum banco de dados a sua escolha.
  + MariaDB / MySQL

- Você deve implementar todas as operações de CRUD.

- Listagens de : 
  - Terrenos - Feito
  - Climas - Feito
  - Planetas <Nome, id> - Feito
  - Listagem de detalhes do planeta com regiões - Feito
- Adicionar
  - Terreno
  - Clima
  - Planeta
    - Região em Planeta

- Você deve implementar alguns métodos de busca a sua escolha.
  - Busca por nome de Clima   (OK, com Paginação usando o nome)
  - Busca por nome de planeta (OK, com Paginação usando o nome)
  - Busca por nome de Terreno (OK, com Paginação usando o nome)

- Sua api deverá fazer uso de cache.
    - Cache usando o padrão do SpringBoot (OK)
      + Foi usada no Terreno e no Clima, pois eles sofrem menos alterações que os planetas.  
  
- Sua api deverá ser documentada.
  - Exemplos de como acessar a API podem ser vistos no Postman in : https://www.postman.com/spedison/workspace/planetasstarwars/overview 
  - Usando o ______

- Sua api deverá utilizar o Actuator.

- Sua api deverá estar versionada no Github. - OK.

#Bônus:

Sua Api deverá ser " Dockerizada" e deverá ser possível rodá-la através de Docker-Compose.

Ao finalizar enviar um e-mail para eluminumsoluçoes@gmail.com com o link do Github do seu projeto.



Boa diversão.