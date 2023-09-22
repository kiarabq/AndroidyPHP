USE veterinaria

-- Registrar cliente

CREATE PROCEDURE spu_clientes_registrar
(
	 IN _apellidos 	VARCHAR(50),
    IN _nombres		VARCHAR(30),
    IN _dni				CHAR(8),
    IN _claveacceso	VARCHAR(200)
)
BEGIN
	INSERT INTO clientes
		(apellidos, nombres, dni, claveacceso)
        VALUES (_apellidos, _nombres, _dni, _claveacceso);
END $$

CALL spu_clientes_registrar(3, 'Tasayco', 'Barbara', '79829192', '');


DELIMITER $$
CREATE PROCEDURE spu_clientes_listar()
BEGIN
	SELECT
		idcliente,
		apellidos,
		nombres,
		dni,
		claveacceso
		FROM clientes
		ORDER BY idcliente DESC;
END $$

CALL spu_clientes_listar();


-- Registrar mascota
CREATE PROCEDURE spu_mascotas_registrar
(
	IN _idcliente	INT,
	IN _idraza		INT,
	IN _nombre		VARCHAR(30),
	IN _fotografia	VARCHAR(200),
	IN _color		VARCHAR(30),
	IN _genero		CHAR(1)
)
BEGIN 
	INSERT INTO mascotas
	(idcliente, idraza, nombre, fotografia, color, genero)
	VALUES (_idcliente, _idraza, _nombre, _fotografia, _color, _genero);
END $$

CALL spu_mascotas_registrar(3, 3, 'Pelusa', '', 'Plomo', 'H');

DELIMITER $$
CREATE PROCEDURE spu_mascotas_listar()
BEGIN
	SELECT idmascota, idcliente,
	idraza, nombre, fotografia,
	color, genero
		FROM mascotas
		ORDER BY idmascota DESC;
END $$

CALL spu_mascotas_listar();

-- Consultar cliente
DELIMITER $$
CREATE PROCEDURE spu_clientes_buscar_dni(IN _dni CHAR(8))
BEGIN
	SELECT	clientes.idcliente,
				clientes.nombre,
				clientes.fotografia, clientes.color,
				clientes, genero
		FROM clientes
		INNER JOIN mascotas ON mascotas.idmascota = clientes.idmascota
		WHERE clientes.dni = _dni AND clientes.estado = '1';
END $$

