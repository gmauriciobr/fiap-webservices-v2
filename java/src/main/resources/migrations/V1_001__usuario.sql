CREATE TABLE usuario (
   id bigint not null auto_increment,
   nome varchar(250) not null,
   email varchar(250) not null,
   password varchar(250) not null,
   matricula varchar(20) null,
   data_criacao datetime not null,
   data_alteracao datetime not null,
   primary key (id),
   unique index `email_unique` (`email` ASC) VISIBLE
);