alter table depoimentos 
add column data_atualizacao datetime null,
add column data_criacao datetime not null default now(),
add column ativo boolean not null default true;
