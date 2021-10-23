create table usuario_perfil (
    id_usuario bigint not null,
    id_perfil bigint not null,
    primary key (id_usuario, id_perfil)
)