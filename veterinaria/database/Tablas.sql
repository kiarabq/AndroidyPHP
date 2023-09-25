CREATE DATABASE veterinaria
USE veterinaria

CREATE TABLE clientes
(
	idcliente		INT AUTO_INCREMENT PRIMARY KEY,
	apellidos		VARCHAR(50)		NOT NULL,
	nombres			VARCHAR(30)		NOT NULL,
	dni				CHAR(8)			NOT NULL,
	inactive_at		DATETIME		NULL,
	register_at		DATETIME		NOT NULL DEFAULT NOW(),
	update_at		DATETIME		NULL
)ENGINE = INNODB;

INSERT INTO clientes (apellidos, nombres, dni) VALUES
('Martinez', 'Maria', '78291819'),
('Torres Aguilar', 'Luis', '71891910');
	
SELECT * FROM clientes;


CREATE TABLE animales
(
	idanimal			INT AUTO_INCREMENT PRIMARY KEY,
	nombreanimal	VARCHAR(50),
	CONSTRAINT uk_nombreanimal_animales UNIQUE (nombreanimal)
)ENGINE = INNODB;

INSERT INTO animales (nombreanimal) VALUES
('Perro'),
('Gato');


CREATE TABLE razas
(
	idraza			INT AUTO_INCREMENT PRIMARY KEY,
	idanimal			INT            NOT NULL,
	nombreraza		VARCHAR (30)	NOT NULL,
	CONSTRAINT fk_idanimal FOREIGN KEY (idanimal) REFERENCES animales(idanimal),
	CONSTRAINT uk_nombreraza_razas UNIQUE (nombreraza)
)ENGINE = INNODB;

INSERT INTO razas (idanimal, nombreraza) VALUES
(1, 'Schnauzer'),
(2, 'Persa');

CREATE TABLE mascotas
(
	idmascota		INT AUTO_INCREMENT PRIMARY KEY,
	idcliente		INT            NOT NULL,
	idraza			INT            NOT NULL,
	nombre			VARCHAR(30)		NOT NULL,
	color			VARCHAR(30)		NOT NULL,
	genero			CHAR(1)			NOT NULL,
	create_at			DATETIME		NULL,
	inactive_at			DATETIME		NULL,
	update_at			DATETIME		NULL,
	CONSTRAINT fk_idcliente FOREIGN KEY (idcliente) REFERENCES clientes(idcliente),
	CONSTRAINT fk_idraza FOREIGN KEY (idraza) REFERENCES razas(idraza),
	CONSTRAINT ck_genero CHECK (genero IN ('H','M'))
)ENGINE = INNODB;

INSERT INTO mascotas (idcliente, idraza, nombre, color, genero) VALUES
(1, 1, 'Toby', 'Marrón', 'M'),
(2, 2, 'Campanita', 'Blanco y plomo', 'H');


SELECT * FROM animales
SELECT * FROM razas
SELECT * FROM mascotas

