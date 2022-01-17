select *
from tb_clima c
where  c.id not in (
    select terreno_id from tb_regiao
)