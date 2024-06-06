create table depoimentos(
    id bigint not null auto_increment,
    nome varchar(60) not null,
    depoimento varchar(200) not null,
    foto binary not null,
    
    primary key (id)
);