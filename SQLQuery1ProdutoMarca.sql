CREATE DATABASE MARCAPRODUTO
GO
USE MARCAPRODUTO
GO
CREATE TABLE MARCA
(
ID		INT				NOT NULL,
NOME	VARCHAR(50)		NOT NULL
PRIMARY KEY (ID)
)
GO
CREATE TABLE PRODUTO
(
ID			INT				NOT NULL,
NOME		VARCHAR(20)		NOT NULL,
PRECO		DECIMAL(7,2)	NOT NULL,
ID_MARCA	INT				NOT NULL
PRIMARY KEY (ID)
FOREIGN KEY (ID_MARCA)
			REFERENCES MARCA (ID)
)