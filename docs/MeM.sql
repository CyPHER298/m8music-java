/*Felipe Soares Gonçalves - RM559175

Henrique Batista de Souza - RM99742

Julia Lima Rodrigues - RM559781*/

DROP TABLE avaliacao CASCADE CONSTRAINTS;
DROP TABLE pedido CASCADE CONSTRAINTS;
DROP TABLE musica CASCADE CONSTRAINTS;
DROP TABLE cantor CASCADE CONSTRAINTS;
DROP TABLE cliente CASCADE CONSTRAINTS;

set serveroutput on
set verify off

CREATE TABLE cliente (
  id_cliente NUMBER(2) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nm_cliente VARCHAR2(50) NOT NULL
);

CREATE TABLE cantor (
  id_cantor NUMBER(2) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nm_cantor VARCHAR2(50) NOT NULL,
  senha_cantor VARCHAR2(10),
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

CREATE TABLE avaliacao (
  id_avaliacao NUMBER(2) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nota NUMBER CONSTRAINT chk_nota CHECK (nota BETWEEN 1 AND 5),
  id_musica NUMBER(2) NOT NULL,
  id_cliente NUMBER(2) NOT NULL,
  FOREIGN KEY (id_musica) REFERENCES musica(id_musica),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

SELECT * FROM cliente;
SELECT * FROM cantor;
SELECT * FROM musica;
SELECT * FROM pedido;
SELECT * FROM avaliacao;

DECLARE
BEGIN
  FOR cliente_pedido IN (
    SELECT c.nm_cliente, COUNT(p.id_pedido) AS total_pedidos
    FROM cliente c
    INNER JOIN pedido p ON c.id_cliente = p.id_cliente
    GROUP BY c.nm_cliente
    ORDER BY total_pedidos DESC
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Cliente: ' || cliente_pedido.nm_cliente || ' | Total de Pedidos: ' || cliente_pedido.total_pedidos);
  END LOOP;
END;

DECLARE
BEGIN
  FOR musica_avaliacao IN (
    SELECT m.titulo, ROUND(AVG(a.nota), 2) AS media_nota
    FROM musica m
    LEFT JOIN avaliacao a ON m.id_musica = a.id_musica
    GROUP BY m.titulo
    ORDER BY media_nota DESC NULLS LAST
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Música: ' || musica_avaliacao.titulo || ' | Média de Nota: ' || NVL(TO_CHAR(musica_avaliacao.media_nota), 'Sem avaliação'));
  END LOOP;
END;

DECLARE
BEGIN
    FOR registro_cantor IN (
    SELECT 
      c.nm_cantor, 
      COUNT(m.id_musica) AS total_musicas
    FROM musica m
    RIGHT JOIN cantor c ON m.artista = c.nm_cantor
    GROUP BY c.nm_cantor
    ORDER BY total_musicas DESC
    ) LOOP
    DBMS_OUTPUT.PUT_LINE('Cantor: ' || registro_cantor.nm_cantor || ' | Total de músicas: ' || registro_cantor.total_musicas);
  END LOOP;
END;


DECLARE
  v_id_cantor   NUMBER := 3;
  v_novo_email  VARCHAR2(50) := 'arianagrande@email.com';
  v_existe      NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_existe
  FROM cantor
  WHERE id_cantor = v_id_cantor;
  IF v_existe > 0 THEN
    UPDATE cantor
    SET email_cantor = v_novo_email
    WHERE id_cantor = v_id_cantor;
    DBMS_OUTPUT.PUT_LINE('E-mail do cantor com ID ' || v_id_cantor || ' foi atualizado para: ' || v_novo_email);
  ELSE
    DBMS_OUTPUT.PUT_LINE('Nenhum cantor encontrado com o ID informado: ' || v_id_cantor);
  END IF;
  COMMIT;
END;


DECLARE
  v_id_avaliacao NUMBER := 5;
  v_existe       NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_existe
  FROM avaliacao
  WHERE id_avaliacao = v_id_avaliacao;
  IF v_existe > 0 THEN
    DELETE FROM avaliacao
    WHERE id_avaliacao = v_id_avaliacao;
    DBMS_OUTPUT.PUT_LINE('Avaliação com ID ' || v_id_avaliacao || ' foi excluída com sucesso.');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Nenhuma avaliação encontrada com o ID informado: ' || v_id_avaliacao);
  END IF;
  COMMIT;
END;
