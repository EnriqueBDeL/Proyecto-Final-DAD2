-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2026 a las 19:19:47
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dad2_tuDNI`
--
CREATE DATABASE IF NOT EXISTS `dad2_tuDNI` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `dad2_tuDNI`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignaturas`
--

CREATE TABLE `asignaturas` (
  `ID` int(10) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `MAX_CAPACIDAD` int(10) NOT NULL DEFAULT 0,
  `ID_TITULACION` int(10) DEFAULT NULL,
  `ID_PROFESOR` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignaturas`
--

INSERT INTO `asignaturas` (`ID`, `NOMBRE`, `MAX_CAPACIDAD`, `ID_TITULACION`, `ID_PROFESOR`) VALUES
(4, 'Programación Orientada a Objetos', 60, 13, 3),
(5, 'Anatomía General', 80, 14, 4),
(6, 'Bases de Datos', 50, 13, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores`
--

CREATE TABLE `profesores` (
  `ID` int(10) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `APELLIDOS` varchar(100) NOT NULL,
  `DEPARTAMENTO` varchar(100) NOT NULL,
  `CORREO` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `profesores`
--

INSERT INTO `profesores` (`ID`, `NOMBRE`, `APELLIDOS`, `DEPARTAMENTO`, `CORREO`) VALUES
(3, 'Elena', 'García López', 'Ingeniería de Sistemas', 'elena.garcia@universidad.edu'),
(4, 'Carlos', 'Martínez Ruiz', 'Anatomía Humana', 'carlos.martinez@universidad.edu');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `titulaciones`
--

CREATE TABLE `titulaciones` (
  `ID` int(10) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `FACULTAD` varchar(100) NOT NULL,
  `CREDITOS` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `titulaciones`
--

INSERT INTO `titulaciones` (`ID`, `NOMBRE`, `FACULTAD`, `CREDITOS`) VALUES
(13, 'Grado en Ingeniería Informática', 'Escuela Politécnica', 240),
(14, 'Grado en Enfermería', 'Ciencias de la Salud', 240);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID_USERNAME` int(10) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `ROL` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_USERNAME`, `USERNAME`, `PASSWORD`, `ROL`) VALUES
(1, 'admin', 'admin', 'ADMIN'),
(2, 'jdirector', '12345', 'ADMIN'),
(3, 'luis_alumno', 'secreta', 'USER');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_TITULACION` (`ID_TITULACION`),
  ADD KEY `ID_PROFESOR` (`ID_PROFESOR`);

--
-- Indices de la tabla `profesores`
--
ALTER TABLE `profesores`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `titulaciones`
--
ALTER TABLE `titulaciones`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_USERNAME`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `profesores`
--
ALTER TABLE `profesores`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `titulaciones`
--
ALTER TABLE `titulaciones`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID_USERNAME` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  ADD CONSTRAINT `asignaturas_ibfk_1` FOREIGN KEY (`ID_TITULACION`) REFERENCES `titulaciones` (`ID`) ON DELETE SET NULL,
  ADD CONSTRAINT `asignaturas_ibfk_2` FOREIGN KEY (`ID_PROFESOR`) REFERENCES `profesores` (`ID`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
