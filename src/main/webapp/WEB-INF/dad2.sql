CREATE DATABASE IF NOT EXISTS `dad2_EBAG` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `dad2_EBAG`;


CREATE TABLE IF NOT EXISTS `titulaciones` (
  `ID` varchar(10) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `titulaciones` (`ID`, `NOMBRE`) VALUES
('1', 'Informática'),
('2', 'Arquitectura');

CREATE TABLE IF NOT EXISTS `usuarios` (
	`ID_USERNAME` INT NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `ROL` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `usuarios` (`USERNAME`, `PASSWORD`, `ROL`) VALUES
('admin', 'admin', 'ADMIN');

COMMIT;