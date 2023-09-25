/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - veterinaria
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`veterinaria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `veterinaria`;

/*Table structure for table `clientes` */

DROP TABLE IF EXISTS `clientes`;

CREATE TABLE `clientes` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(50) NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `dni` char(8) NOT NULL,
  `inactive_at` datetime DEFAULT NULL,
  `register_at` datetime NOT NULL DEFAULT current_timestamp(),
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `clientes` */

insert  into `clientes`(`idcliente`,`apellidos`,`nombres`,`dni`,`inactive_at`,`register_at`,`update_at`) values 
(1,'Martinez','Maria','78291819',NULL,'2023-09-25 17:19:32',NULL),
(2,'Torres Aguilar','Luis','71891910',NULL,'2023-09-25 17:19:32',NULL),
(3,'Carbajal','Mauricio','76341461',NULL,'2023-09-25 17:20:25',NULL),
(4,'Fajardo','Paola','75643325',NULL,'2023-09-25 18:13:34',NULL);

/*Table structure for table `mascotas` */

DROP TABLE IF EXISTS `mascotas`;

CREATE TABLE `mascotas` (
  `idmascota` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `color` varchar(30) NOT NULL,
  `especie` varchar(30) NOT NULL,
  `raza` varchar(30) NOT NULL,
  `genero` char(1) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `inactive_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`idmascota`),
  CONSTRAINT `ck_genero` CHECK (`genero` in ('H','M'))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `mascotas` */

insert  into `mascotas`(`idmascota`,`nombre`,`color`,`especie`,`raza`,`genero`,`create_at`,`inactive_at`,`update_at`) values 
(1,'Toby','Marrón','Perro','Labrador','M',NULL,NULL,NULL),
(2,'Campanita','Blanco y plomo','Gato','Persa','H',NULL,NULL,NULL),
(3,'Pelusa','Plomo','Gato','Siamés','M',NULL,NULL,NULL),
(4,'kira','blanco','Perro','Schnauzer','H',NULL,NULL,NULL),
(5,'Sam','Negro','Perro','Pitbull','H',NULL,NULL,NULL),
(6,'Max','Mostaza','Gato','Bengalí','M',NULL,NULL,NULL);

/* Procedure structure for procedure `spu_clientes_actualizar` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_clientes_actualizar` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_clientes_actualizar`(
IN _idcliente		INT,
IN _apellidos		VARCHAR(50),
IN _nombres		VARCHAR(30),
IN _dni			CHAR(8)
)
BEGIN
	UPDATE clientes SET
    apellidos = _apellidos,
    nombres = _nombres,
    dni = _dni,
    update_at = NOW()
    WHERE idcliente = idcliente;
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_clientes_eliminar` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_clientes_eliminar` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_clientes_eliminar`(IN _idcliente INT)
BEGIN
	UPDATE clientes SET
		inactive_at = NOW()
	WHERE idcliente = _idcliente;
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_clientes_getData` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_clientes_getData` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_clientes_getData`(IN _idcliente INT)
BEGIN
	SELECT 
		idcliente,
		apellidos,
		nombres,
		dni
		FROM clientes
		WHERE idcliente = _idcliente;
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_clientes_listar` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_clientes_listar` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_clientes_listar`()
BEGIN
	SELECT
		idcliente,
		apellidos,
		nombres,
		dni
		FROM clientes
		WHERE inactive_at IS NULL
		ORDER BY idcliente DESC;
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_clientes_registrar` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_clientes_registrar` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_clientes_registrar`(
	IN _apellidos 	VARCHAR(50),
	IN _nombres	VARCHAR(30),
	IN _dni		CHAR(8)
)
BEGIN
	INSERT INTO clientes
		(apellidos, nombres, dni)
        VALUES (_apellidos, _nombres, _dni);
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_mascotas_actualizar` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_mascotas_actualizar` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_mascotas_actualizar`(
IN _idmascota		INT,
IN _nombre		VARCHAR(30),
IN _color		VARCHAR(30),
in _especie		varchar(30),
in _raza		varchar(30),
IN _genero		CHAR(1)
)
BEGIN
	UPDATE mascotas SET
    nombre = _nombre,
    color = _color,
    especie = _especie,
    raza = _raza,
    genero = _genero,
    update_at = NOW()
    WHERE idmascota = idmascota;
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_mascotas_eliminar` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_mascotas_eliminar` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_mascotas_eliminar`(IN _idmascota INT)
BEGIN
	UPDATE mascotas SET
	inactive_at = NOW()
	WHERE idmascota = _idmascota;
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_mascotas_getData` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_mascotas_getData` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_mascotas_getData`(IN _idmascota INT)
BEGIN
	SELECT 
		idmascota,
		nombre,
		color, especie,
		raza, genero
		FROM mascotas
		WHERE idmascota = _idmascota;
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_mascotas_listar` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_mascotas_listar` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_mascotas_listar`()
BEGIN
	SELECT idmascota, nombre, color, especie, raza, genero
		FROM mascotas
		WHERE inactive_at IS NULL
		ORDER BY idmascota DESC;
END */$$
DELIMITER ;

/* Procedure structure for procedure `spu_mascotas_registrar` */

/*!50003 DROP PROCEDURE IF EXISTS  `spu_mascotas_registrar` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `spu_mascotas_registrar`(
	IN _nombre		VARCHAR(30),
	IN _color		VARCHAR(30),
	in _especie		varchar(30),
	in _raza		varchar(30),
	IN _genero		CHAR(1)
)
BEGIN 
	INSERT INTO mascotas
	(nombre, color, especie, raza, genero)
	VALUES (_nombre, _color, _especie, _raza, _genero);
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
