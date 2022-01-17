create or replace view vw_planetas as
select p.id               AS id,
       p.nome             AS nome,
       r.nome             AS nomeRegiao,
       c.nome             AS nomeClima,
       c.viavel_para_vida AS viavel_para_vida,
       t.nome             AS nomeTerreno,
       t.trafegavel       AS trafegavel,
       p.ativo AS planeta_ativo ,
       r.ativo AS regiao_ativo ,
       c.ativo AS clima_ativo ,
       t.ativo AS terreno_ativo
from
     desafio_planetas_star_wars.tb_planeta p
     left join desafio_planetas_star_wars.tb_regiao r on (p.id = r.planeta_id)
     left join desafio_planetas_star_wars.tb_clima c on (c.id = r.clima_id )
     left join desafio_planetas_star_wars.tb_terreno t on (t.id = r.terreno_id)
order by p.nome;