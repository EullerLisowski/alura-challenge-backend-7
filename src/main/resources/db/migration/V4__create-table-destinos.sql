create table destinos(
    id bigint not null auto_increment,
    nome varchar(60) not null,
    foto binary not null,
    preco decimal(10,2) not null,
    
    primary key (id)
);