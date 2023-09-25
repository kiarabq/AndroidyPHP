CREATE DATABASE veterinaria
USE veterinaria

CREATE TABLE clientes
(
	idcliente		INT AUTO_INCREMENT PRIMARY KEY,
	apellidos		VARCHAR(50)		NOT NULL,
	nombres			VARCHAR(30)		NOT NULL,
	dni			CHAR(8)			NOT NULL,
	inactive_at		DATETIME		NULL,
	register_at		DATETIME		NOT NULL DEFAULT NOW(),
	update_at		DATETIME		NULL
)ENGINE = INNODB;

INSERT INTO clientes (apellidos, nombres, dni) VALUES
('Martinez', 'Maria', '78291819'),
('Torres Aguilar', 'Luis', '71891910');
	
SELECT * FROM clientes;


CREATE TABLE mascotas
(
	idmascota		INT AUTO_INCREMENT PRIMARY KEY,
	nombre			VARCHAR(30)		NOT NULL,
	color			VARCHAR(30)		NOT NULL,
	especie			VARCHAR(30)		NOT NULL,
	raza			VARCHAR(30)		NOT NULL,
	genero			CHAR(1)			NOT NULL,
	create_at		DATETIME		NULL,
	inactive_at		DATETIME		NULL,
	update_at		DATETIME		NULL,
	CONSTRAINT ck_genero CHECK (genero IN ('H','M'))
)ENGINE = INNODB;

INSERT INTO mascotas (nombre, color, especie, raza, genero) VALUES
('Toby', 'Marrón', 'Perro', 'Labrador', 'M'),
('Campanita', 'Blanco y plomo', 'Gato', 'Persa', 'H');

SELECT * FROM mascotas;

