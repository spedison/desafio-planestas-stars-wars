* Rodando o MariaDB manualmente.

  + docker network create some-network
    + Comando cria uma rede interna Bridge para
    
  + docker run --detach --network some-network --name some-mariadb --env MARIADB_USER=example-user --env MARIADB_PASSWORD=123456 --env MARIADB_ROOT_PASSWORD=123456 --env MARIADB_DATABASE=app_banco_1  mariadb:10.3.29


| Parte do comando                   | Comentário                              |
|------------------------------------|-----------------------------------------|
| docker run                         | executam um container                   |
| --detach                           | Em segundo plano                        |
| --network some-network             | Com uma rede isolada entre containeres. |
| --name some-mariadb                | Esse container terá o nome some-mariadb |
| --env MARIADB_USER=example-user    | Usuário criado example-user             |
| --env MARIADB_PASSWORD=123456      | Com senha   123456                      |
| --env MARIADB_ROOT_PASSWORD=123456 | Senha de root  123456                   |
| --env MARIADB_DATABASE=app_banco_1 | Criando o Banco app_banco_1             |
| mariadb:10.3.29                    | A partir dessa imagem.                  |

