SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
CREATE DATABASE IF NOT EXISTS `invoices` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `invoices`;

DROP TABLE IF EXISTS `propietario`;
CREATE TABLE `propietario` (
  `pid` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `telefono` varchar(256) NOT NULL,
  `seccion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `recibo`;
CREATE TABLE `recibo` (
  `id` bigint(20) NOT NULL,
  `base_imponible` double NOT NULL,
  `cantidad` int(11) NOT NULL,
  `direccion_contacto` varchar(255) DEFAULT NULL,
  `direccion_envio` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `impuestos` float NOT NULL,
  `nombre_contacto` varchar(255) DEFAULT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `precio_unitario` float NOT NULL,
  `total` double NOT NULL,
  `valido` bit(1) NOT NULL,
  `propietario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `propietario`
  ADD PRIMARY KEY (`pid`);

ALTER TABLE `recibo`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `propietario`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `recibo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;