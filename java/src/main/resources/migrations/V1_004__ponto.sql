create table ponto (
    id bigint not null auto_increment,
    justificativa varchar(255),
    marcacao datetime(6),
    usuario_id bigint,
    data_criacao datetime(6),
    data_alteracao datetime(6),
    primary key (id)
)