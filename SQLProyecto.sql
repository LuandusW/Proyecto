DROP DATABASE IF EXISTS DINOSAURIO;
CREATE DATABASE DINOSAURIO;
USE DINOSAURIO;
DROP TABLE IF EXISTS USUARIOS;
CREATE TABLE USUARIOS
	(id INT AUTO_INCREMENT PRIMARY KEY,
	nom_usuario VARCHAR(20),
	puntuacion INT);
INSERT INTO USUARIOS VALUES(1,'Fausto',1500);
INSERT INTO USUARIOS VALUES(2,'Pedri',2500);
INSERT INTO USUARIOS VALUES(3,'Martinelli',4000);
INSERT INTO USUARIOS VALUES(4,'Gabriel Jesus',3000);
INSERT INTO USUARIOS VALUES(4,'Gabriel Jesus',3000)
