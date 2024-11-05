CREATE DATABASE db_sistemacadastro;

USE db_sistemacadastro;

CREATE TABLE tb_usuario (
  cpf VARCHAR(11) NOT NULL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  senha VARCHAR(6) NOT NULL
);

