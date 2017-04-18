create table cardapio(
id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
prato varchar(255) NOT NULL,
descricao varchar(255) NOT NULL,
periodo varchar(255) NOT NULL
);


CREATE TABLE usuario (
 id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
 login varchar(20) NOT NULL,
 senha varchar(10) NOT NULL,
 conectado integer NOT NULL
);