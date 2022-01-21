# Como empacotar a aplicação e Executar a aplicação

## Como empacotar e executar aplicação
 
A aplicação foi feita em Kotlin e deve ser compilada e empacatada em um único jar que possui o container TomCat.

Nesse caso para compilar usaremos o Maven que está na versão 3.8.4 Usando o openjdk 11

antes de iniciar esse processo devemos configurar as variáveis de ambiente para atualização do banco de dados.

| Nome da Variável | Valor Sugerido         | Significado                                                                                  |
|------------------|------------------------|----------------------------------------------------------------------------------------------|
| SENHA_ROOT_APP   | 123456                 | Senha de root do banco MySQL                                                                 |
| SENHA            | 123456                 | Senha do usuário da aplicação                                                                |
| HOST             | db                     | Nome do host do banco da app                                                                 |
| USUARIO_ROOT_APP | root                   | usuário que é root do banco (tem todos os acessos para criar a alterar tabelas, por exemplo) |
| BANCO            | planetas_star_wars_dev | Nome do banco de dados utilizado                                                             |
| USUARIO          | curso_udemy            | Usuário da aplicação que tem acessos somente de CRUD ao banco.                               |

Utilizamos o Plugin `flyway-maven-plugin` e durante o processos de compilação tivemos que desabilitar os testes unitário e deixar executar o migration do Flyway.

Deixando claro que os usuários utilizados e o banco já devem estar criados antes do início do processo.

Caso deseja apagar todos os arquivos anteriores para iniciar a compilação do Zero deve-se utilizar:

`nvn clean`

Compilando todos os fontes temos:

`mvn kotlin:compile package spring-boot:repackage`

O resultado desse processo é no diretório target/planetas-star-wars-0.0.1-SNAPSHOT.jar.

Esse arquivo pode ser executado usando comando no terminal `java -Djava.security.egd=file:/dev/./urandom -jar target/planetas-star-wars-0.0.1-SNAPSHOT.jar` .  

Para instalação do Java e do Maven pode-se utilizar o  [SdkMan](https://sdkman.io/) pois tem procedimentos muito simples e bem objetivos. Além de montar um ambiente de desenvolvimento bem flexível com a possiblidade de alterar as versões de Maven e JDK com apenas um comando.

Com isso temos a compilação e empacotamento realizado. Banco de dados operacional e devidamente atualizado. 

As futuras alterações do Banco devem ser realizadas adicionando novos arquivos .sql no diretório srv/main/resources/db/migration.  

A migração também é realizada, além do maven, no ambiente de produção, pelo próprio aplicativo. Assim, cada vez que realizar uma atualização do aplicativo, automaticamente o banco será atualizado sem a necessidade de um DBA. A única ponto que deve ser ajustado são as variáveis de ambiente.

Fim do processo de empacotamento.

## Como colocar a aplicação em um container Docker.

Nossa aplicação está utilizando o  [Docker-Compose](https://docs.docker.com/compose/)

Antes de iniciar o arquivo .env deve ser ajustado com os valores adequados, mas que em um primeiro teste não é necessária a alteração.

- Usado pelo docker compose
NOME_BANCO=planetas_star_wars

- App do Sistema (Menor privilégios)
SENHA_BANCO_USER=123456
USUARIO_BANCO=curso_udemy

- Usuário Root do Banco
USUARIO_ROOT_APP=root
SENHA_ROOT_APP=123456

Um bom documento como treino do Docker-Compose pode ser [Blog do 4Linux](https://blog.4linux.com.br/docker-compose-explicado/).

No diretório do projeto execute o comando: `docker-compose -d up app_psw`
Para parar os containeres execute o comando: `docker-compose down app_psw`

Para criar um Banco MySQL de teste com a porta 3307 podemos utilizar o comando Docker:

`docker-compose -f docker-compose-mysql-test.yml up`


Para conectar use o host localhost, porta 3307, usuário root e senha 123456 (Que estão definidos no arquvivo .env)


