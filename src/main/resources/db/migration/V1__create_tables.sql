CREATE TABLE cliente (
                         id_cliente NUMBER(2) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         nm_cliente VARCHAR2(50) NOT NULL
);

CREATE TABLE cantor (
                        id_cantor NUMBER(2) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        nm_cantor VARCHAR2(50) NOT NULL,
                        senha_cantor VARCHAR2(200) NOT NULL,
                        email_cantor VARCHAR2(50) UNIQUE
);

CREATE TABLE musica (
                        id_musica NUMBER(2) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        titulo VARCHAR2(50) NOT NULL,
                        artista VARCHAR2(50),
                        genero VARCHAR2(50)
);

CREATE TABLE pedido (
                        id_pedido NUMBER(2) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        id_cliente NUMBER(2) NOT NULL,
                        id_musica NUMBER(2) NOT NULL,
                        FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
                        FOREIGN KEY (id_musica) REFERENCES musica(id_musica)
);