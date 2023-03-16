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
  `id` int(11) NOT NULL,
  `propietario` int(11) NOT NULL,
  `fecha_emision` timestamp NULL DEFAULT NULL,
  `fecha_vencimiento` int(11) NOT NULL,
  `nombre_contacto` varchar(256) DEFAULT NULL,
  `direccion_contacto` varchar(256) DEFAULT NULL,
  `direccion_envio` varchar(256) DEFAULT NULL,
  `nombre_producto` varchar(256) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unitario` float NOT NULL,
  `base_imponible` float NOT NULL,
  `impuestos` float NOT NULL,
  `total` double NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `valido` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `propietario`
  ADD PRIMARY KEY (`pid`);

ALTER TABLE `recibo`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `propietario`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `recibo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;