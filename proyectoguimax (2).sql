-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2018 a las 21:17:22
-- Versión del servidor: 5.7.21-log
-- Versión de PHP: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoguimax`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caracteristica`
--

CREATE TABLE `caracteristica` (
  `id_carac` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `unidad_medida` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

--
-- Volcado de datos para la tabla `caracteristica`
--

INSERT INTO `caracteristica` (`id_carac`, `descripcion`, `unidad_medida`) VALUES
(1, 'longitud', 'cmt'),
(2, 'capacidad', 'cm3'),
(4, 'peso', 'grs');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoriaemple`
--

CREATE TABLE `categoriaemple` (
  `id_cat` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `sueldo_hora` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoriaemple`
--

INSERT INTO `categoriaemple` (`id_cat`, `descripcion`, `sueldo_hora`) VALUES
(1, 'Categoria1', 300),
(2, 'Categoria2', 400),
(3, 'Categoria3', 500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idcliente`) VALUES
(1),
(2),
(3),
(9),
(10),
(11),
(12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contacto`
--

CREATE TABLE `contacto` (
  `idpersona` int(11) NOT NULL,
  `permisootorgado` varchar(45) NOT NULL,
  `cliente_idcliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `contacto`
--

INSERT INTO `contacto` (`idpersona`, `permisootorgado`, `cliente_idcliente`) VALUES
(3, 'Presupuesto', 1),
(8, 'Total', 1),
(9, 'Total', 2),
(10, 'Pedido', 2),
(11, 'Total', 3),
(14, 'Total', 9),
(15, 'Pedido', 10),
(16, 'Presupuesto', 10),
(17, 'Total', 11),
(18, 'Total', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `idempresa` int(11) NOT NULL,
  `razon_social` varchar(45) NOT NULL,
  `cuit` int(11) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` int(11) NOT NULL,
  `localidad` varchar(45) NOT NULL,
  `codigo_postal` int(11) NOT NULL,
  `flagbaja` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`idempresa`, `razon_social`, `cuit`, `direccion`, `telefono`, `localidad`, `codigo_postal`, `flagbaja`) VALUES
(1, 'Miel sl', 1234312, 'garaxa', 1324344555, 'caba', 1234, 1),
(2, 'Fiel Electric', 1224322121, 'Zarasa 3132', 1222111223, 'caba', 1234, 1),
(3, 'mar', 1224322121, 'sadsa', 12312, '12312', 12312, 1),
(9, 'dhgjfdsgjhgf', 12321, '123123', 334327, '4311213', 123123, 1),
(10, 'dsjkfgdskfg', 12763712, '12736172', 32447, '1632712', 127367126, 1),
(11, 'sadas', 132123, '3213', 3131, '2313', 1231, 1),
(12, 'dzfsfds', 12313, '3213', 12312, '3212', 3213, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_material`
--

CREATE TABLE `linea_material` (
  `idlinea_material` int(11) NOT NULL,
  `idmaterial` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad_material` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `linea_material`
--

INSERT INTO `linea_material` (`idlinea_material`, `idmaterial`, `idproducto`, `cantidad_material`) VALUES
(1, 1, 4, 2),
(1, 1, 5, 21),
(1, 1, 7, 2),
(1, 1, 11, 2),
(1, 1, 14, 2),
(1, 1, 18, 4),
(1, 2, 1, 20),
(1, 2, 2, 280),
(1, 2, 3, 20),
(1, 2, 6, 2),
(1, 2, 8, 5),
(1, 2, 10, 2),
(1, 2, 13, 2),
(1, 2, 17, 2),
(1, 3, 15, 4),
(1, 3, 16, 4),
(1, 4, 12, 3),
(1, 5, 9, 3),
(1, 9, 19, 1),
(1, 9, 20, 1),
(1, 9, 21, 2131),
(1, 9, 22, 1),
(1, 9, 23, 0),
(1, 9, 24, 211),
(1, 9, 25, 1),
(1, 9, 26, 11),
(1, 9, 27, 21),
(1, 9, 28, 53),
(1, 9, 29, 11),
(1, 9, 30, 12),
(1, 9, 31, 21),
(1, 9, 32, 1),
(1, 9, 33, 31),
(1, 9, 34, 21),
(1, 9, 35, 1),
(1, 9, 36, 21),
(1, 9, 37, 21),
(1, 9, 38, 21),
(1, 9, 39, 21),
(1, 9, 40, 1),
(1, 9, 41, 11),
(1, 9, 42, 21),
(1, 9, 43, 211),
(1, 9, 44, 1),
(1, 9, 45, 1),
(1, 9, 46, 1),
(1, 9, 47, 31),
(1, 9, 48, 1),
(1, 9, 49, 21),
(1, 9, 50, 1),
(1, 9, 51, 21),
(1, 9, 52, 1),
(1, 9, 53, 1),
(1, 9, 54, 1),
(1, 9, 56, 21),
(1, 9, 57, 21),
(1, 9, 58, 12),
(1, 9, 59, 1),
(1, 9, 61, 111),
(1, 9, 62, 1),
(1, 9, 63, 21),
(1, 9, 64, 1),
(1, 9, 65, 31),
(1, 9, 66, 11),
(1, 9, 67, 1),
(1, 9, 68, 12),
(1, 9, 69, 1),
(1, 9, 70, 1),
(1, 9, 71, 123),
(1, 9, 72, 1),
(1, 9, 73, 11),
(1, 10, 60, 11),
(2, 1, 3, 30),
(2, 1, 6, 2),
(2, 1, 9, 4),
(2, 1, 12, 3),
(2, 1, 13, 2),
(2, 1, 16, 6),
(2, 2, 4, 2),
(2, 2, 5, 2),
(2, 2, 7, 2),
(2, 2, 11, 1),
(2, 2, 14, 3),
(2, 3, 1, 30),
(2, 3, 17, 4),
(2, 4, 10, 3),
(2, 5, 2, 30),
(2, 5, 15, 5),
(2, 10, 20, 1),
(2, 10, 21, 1),
(2, 10, 27, 1),
(2, 10, 29, 31),
(2, 10, 32, 31),
(2, 10, 33, 1),
(2, 10, 36, 13),
(2, 10, 37, 11),
(2, 10, 38, 11),
(2, 10, 42, 21),
(2, 10, 43, 1),
(2, 10, 45, 21),
(2, 10, 46, 1),
(2, 10, 47, 21),
(2, 10, 49, 1),
(2, 10, 51, 1),
(2, 10, 56, 1),
(2, 10, 58, 11),
(2, 10, 59, 1),
(2, 10, 61, 31),
(2, 10, 64, 231),
(2, 10, 67, 1),
(2, 10, 68, 1),
(2, 10, 70, 12),
(2, 10, 71, 211),
(2, 10, 72, 121),
(2, 10, 73, 21),
(2, 11, 18, 4),
(3, 1, 8, 6),
(3, 1, 17, 0),
(3, 2, 12, 3),
(3, 4, 3, 12),
(3, 4, 4, 2),
(3, 4, 5, 23),
(3, 4, 7, 2),
(3, 4, 11, 2),
(3, 4, 13, 2),
(3, 4, 14, 1),
(3, 6, 10, 5),
(3, 9, 16, 4),
(3, 10, 15, 27),
(3, 10, 54, 1),
(3, 10, 66, 61),
(3, 11, 27, 1),
(3, 11, 42, 211),
(3, 11, 59, 231),
(3, 11, 64, 31),
(3, 11, 68, 1),
(3, 11, 73, 41),
(4, 1, 15, 40),
(4, 4, 9, 2),
(4, 4, 16, 5),
(4, 5, 7, 3),
(4, 5, 11, 2),
(4, 5, 13, 2),
(4, 11, 17, 0),
(4, 14, 42, 51),
(4, 14, 73, 5),
(5, 3, 7, 3),
(5, 3, 9, 5),
(5, 3, 14, 5),
(5, 18, 42, 1),
(5, 18, 73, 6),
(6, 6, 7, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_presupuesto`
--

CREATE TABLE `linea_presupuesto` (
  `id_linea` int(11) NOT NULL,
  `id_presupuesto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `linea_presupuesto`
--

INSERT INTO `linea_presupuesto` (`id_linea`, `id_presupuesto`, `cantidad`, `id_producto`) VALUES
(1, 1, 7, 3),
(1, 2, 1, 2),
(1, 3, 1, 3),
(1, 4, 1, 5),
(1, 5, 4, 2),
(1, 6, 3, 2),
(1, 8, 4, 4),
(1, 10, 3, 1),
(1, 12, 4, 1),
(1, 13, 12, 2),
(1, 14, 1, 1),
(1, 15, 5, 1),
(1, 16, 1, 1),
(1, 17, 1, 1),
(1, 18, 2, 1),
(1, 19, 4, 2),
(1, 20, 9, 1),
(1, 22, 2, 2),
(1, 23, 3, 2),
(1, 24, 3, 1),
(1, 25, 5, 3),
(1, 26, 6, 1),
(1, 27, 1, 4),
(1, 28, 1, 1),
(1, 29, 3, 1),
(1, 30, 1, 2),
(1, 31, 1, 2),
(1, 32, 2, 1),
(1, 33, 1, 2),
(1, 34, 1, 2),
(1, 35, 4, 1),
(1, 36, 1, 2),
(1, 37, 6, 2),
(1, 38, 1, 2),
(1, 39, 4, 1),
(1, 40, 1, 2),
(1, 41, 2, 2),
(1, 42, 3, 4),
(1, 44, 3, 3),
(1, 46, 1, 4),
(1, 47, 1, 5),
(1, 48, 1, 2),
(1, 50, 1, 3),
(1, 51, 1, 1),
(1, 52, 34, 2),
(1, 53, 11, 3),
(1, 54, 31, 4),
(1, 55, 31, 2),
(1, 56, 1, 1),
(1, 57, 1, 1),
(2, 1, 7, 5),
(2, 6, 5, 5),
(2, 8, 2, 5),
(2, 10, 3, 13),
(2, 12, 4, 4),
(2, 13, 3, 5),
(2, 14, 4, 6),
(2, 15, 4, 3),
(2, 16, 1, 4),
(2, 17, 4, 4),
(2, 18, 4, 3),
(2, 19, 6, 5),
(2, 20, 3, 5),
(2, 22, 1, 4),
(2, 23, 4, 5),
(2, 24, 1, 3),
(2, 25, 7, 5),
(2, 26, 2, 3),
(2, 27, 4, 6),
(2, 28, 4, 3),
(2, 29, 1, 4),
(2, 30, 3, 4),
(2, 31, 3, 4),
(2, 32, 1, 3),
(2, 33, 2, 4),
(2, 34, 2, 5),
(2, 35, 1, 3),
(2, 36, 4, 5),
(2, 37, 1, 5),
(2, 38, 4, 5),
(2, 39, 1, 3),
(2, 40, 1, 4),
(2, 42, 2, 6),
(2, 44, 7, 5),
(2, 46, 1, 3),
(2, 51, 1, 2),
(2, 52, 33, 5),
(2, 53, 11, 6),
(2, 54, 21, 6),
(2, 56, 21, 2),
(2, 57, 34, 2),
(3, 8, 3, 7),
(3, 10, 9, 5),
(3, 12, 4, 6),
(3, 13, 4, 8),
(3, 14, 8, 8),
(3, 15, 2, 5),
(3, 16, 1, 6),
(3, 17, 6, 7),
(3, 18, 5, 6),
(3, 22, 2, 7),
(3, 24, 1, 5),
(3, 25, 6, 7),
(3, 27, 1, 3),
(3, 29, 1, 6),
(3, 34, 4, 7),
(3, 36, 1, 7),
(3, 42, 1, 8),
(3, 44, 1, 7),
(3, 51, 1, 3),
(3, 53, 31, 4),
(3, 54, 4, 7),
(3, 57, 1, 3),
(4, 10, 8, 10),
(4, 13, 12, 15),
(4, 14, 9, 3),
(4, 15, 9, 7),
(4, 42, 1, 17),
(4, 44, 1, 2),
(4, 51, 1, 4),
(4, 54, 3, 10),
(5, 14, 6, 7),
(5, 15, 10, 9),
(5, 42, 1, 19),
(5, 54, 21, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `material`
--

CREATE TABLE `material` (
  `idmaterial` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `stock_actual` int(11) NOT NULL,
  `stock_minimo` int(11) NOT NULL,
  `ultimo_precio` float NOT NULL,
  `precio_actual` float NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `flagbaja` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `material`
--

INSERT INTO `material` (`idmaterial`, `descripcion`, `stock_actual`, `stock_minimo`, `ultimo_precio`, `precio_actual`, `id_tipo`, `flagbaja`) VALUES
(1, 'Barra cobre 40 * 5 mm', 476, 300, 22, 22, 2, 1),
(2, 'cable 2.5mm', 3140, 300, 28, 38, 2, 1),
(3, 'Poliester ', 1150, 200, 12, 12, 3, 1),
(4, 'cable 3 mm', 3520, 200, 30, 70, 2, 1),
(5, 'barra 2,3 x 4', 3280, 100, 9.6, 20, 2, 1),
(6, 'Carbonato Calcico', 5050, 1000, 18, 30, 6, 1),
(7, 'Barra 4.5 x 1.5 cm', 300, 200, 22.5, 20, 2, 1),
(8, 'Bicarbonato', 300, 200, 12, 20, 6, 1),
(9, 'mare', 1100, 0, 67, 80, 1, 1),
(10, 'agdsgadg', 270, 0, 10, 20, 1, 1),
(11, 'hsgdf', 33330, 230, 1231, 123, 1, 1),
(12, 'marea', 3000, 2000, 20, 36, 4, 0),
(13, 'cobre 2.5mm', 6000, 600, 25, 30, 2, 0),
(14, '1233', 11110, 33, 2130, 210, 1, 1),
(15, '123123', 410, 0, 0, 12, 2, 1),
(16, 'dsffdaad', 12330, 2220, 13210, 1300, 3, 1),
(17, 'dsfyhjhg', 2000, 1220, 330, 200, 6, 1),
(18, 'asd', 0, 2, 0, 48, 1, 1),
(19, 'asdasd', 0, 0, 0, 0, 1, 0),
(20, 'erwr', 3424, 23234, 0, 0, 1, 1),
(21, '123331', 33, 122, 1, 20, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `material_proveedor`
--

CREATE TABLE `material_proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `id_material` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operacionautoriza`
--

CREATE TABLE `operacionautoriza` (
  `idoperacion_auto` int(11) NOT NULL,
  `idpedido` int(11) DEFAULT NULL,
  `idpresupuesto` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `idcontacto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `operacionautoriza`
--

INSERT INTO `operacionautoriza` (`idoperacion_auto`, `idpedido`, `idpresupuesto`, `fecha`, `idcontacto`) VALUES
(1, NULL, 1, '2018-10-03', 3),
(2, NULL, 3, '2018-10-03', 8),
(3, NULL, 2, '2018-10-03', 3),
(4, NULL, 4, '2018-10-03', 8),
(5, NULL, 5, '2018-10-03', 3),
(6, NULL, 6, '2018-10-03', 3),
(8, NULL, 8, '2018-10-03', 8),
(9, NULL, 10, '2018-10-03', 8),
(14, NULL, 12, '2018-10-08', 3),
(15, NULL, 13, '2018-10-15', 9),
(16, NULL, 14, '2018-10-15', 3),
(17, NULL, 15, '2018-10-15', 3),
(18, NULL, 16, '2018-10-15', 3),
(19, NULL, 17, '2018-10-15', 9),
(20, NULL, 18, '2018-10-15', 3),
(21, NULL, 19, '2018-10-15', 9),
(22, NULL, 20, '2018-10-15', 8),
(23, NULL, 22, '2018-10-15', 3),
(24, NULL, 23, '2018-10-15', 9),
(25, NULL, 24, '2018-10-15', 3),
(26, NULL, 25, '2018-10-15', 3),
(27, NULL, 26, '2018-10-15', 9),
(28, NULL, 27, '2018-10-15', 9),
(29, NULL, 28, '2018-10-15', 3),
(30, NULL, 29, '2018-10-15', 9),
(31, NULL, 30, '2018-10-15', 9),
(32, NULL, 31, '2018-10-15', 3),
(33, NULL, 32, '2018-10-15', 3),
(34, NULL, 33, '2018-10-15', 3),
(35, NULL, 34, '2018-10-15', 9),
(36, NULL, 35, '2018-10-15', 3),
(37, NULL, 36, '2018-10-15', 3),
(38, NULL, 37, '2018-10-15', 3),
(39, NULL, 38, '2018-10-15', 3),
(40, NULL, 39, '2018-10-15', 3),
(41, NULL, 40, '2018-10-15', 3),
(42, NULL, 41, '2018-10-16', 3),
(43, NULL, 42, '2018-10-23', 8),
(44, NULL, 44, '2018-10-23', 3),
(45, NULL, 46, '2018-10-30', 3),
(46, NULL, 47, '2018-10-30', 3),
(47, NULL, 48, '2018-10-30', 3),
(48, NULL, 50, '2018-10-30', 3),
(49, NULL, 51, '2018-10-30', 3),
(50, NULL, 52, '2018-10-30', 9),
(51, 1, NULL, '2018-10-31', 8),
(55, 5, NULL, '2018-10-31', 3),
(56, 6, NULL, '2018-10-31', 3),
(57, 7, NULL, '2018-10-31', 3),
(58, 8, NULL, '2018-11-06', 8),
(59, 9, NULL, '2018-11-06', 8),
(60, NULL, 53, '2018-11-13', 3),
(61, NULL, 54, '2018-11-13', 3),
(62, NULL, 55, '2018-11-13', 3),
(63, NULL, 56, '2018-11-13', 3),
(64, NULL, 57, '2018-11-13', 3),
(65, 10, NULL, '2018-11-13', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operacion_egreso`
--

CREATE TABLE `operacion_egreso` (
  `fecha_eg` date NOT NULL,
  `pedido_idpedido` int(11) NOT NULL,
  `usuario_id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `operacion_egreso`
--

INSERT INTO `operacion_egreso` (`fecha_eg`, `pedido_idpedido`, `usuario_id_persona`) VALUES
('2018-10-31', 1, 1),
('2018-10-31', 5, 1),
('2018-10-31', 6, 1),
('2018-10-31', 7, 1),
('2018-11-06', 9, 1),
('2018-11-13', 10, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `idpedido` int(11) NOT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_entrega` date NOT NULL,
  `total` float NOT NULL,
  `presupuesto_id_presupuesto` int(11) NOT NULL,
  `flag_baja` tinyint(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`idpedido`, `fecha_alta`, `fecha_entrega`, `total`, `presupuesto_id_presupuesto`, `flag_baja`) VALUES
(1, '2018-10-31', '2018-11-07', 1000000, 52, 1),
(5, '2018-10-31', '2018-10-31', 67518, 51, 1),
(6, '2018-10-31', '2018-10-31', 21240, 2, 1),
(7, '2018-10-31', '2018-10-31', 84960, 5, 1),
(8, '2018-11-06', '2018-11-06', 7260, 3, 1),
(9, '2018-11-06', '2018-11-06', 7260, 3, 1),
(10, '2018-11-13', '2018-11-13', 5448, 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id_permisos` int(11) NOT NULL,
  `desc_permiso` varchar(45) NOT NULL,
  `flagbaja` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id_permisos`, `desc_permiso`, `flagbaja`) VALUES
(1, 'Completo', 1),
(2, 'Restringido', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` int(11) NOT NULL,
  `cargo` varchar(45) NOT NULL,
  `flagbaja` tinyint(2) NOT NULL,
  `dni` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id_persona`, `nombre`, `apellido`, `telefono`, `cargo`, `flagbaja`, `dni`) VALUES
(1, 'Martu', 'Lugani', 123444, 'Gerente', 1, 23111),
(3, 'Jorge', 'dalesio', 2334432, 'Pedidos', 1, 23334445),
(4, 'Maram', 'Lopez', 123331111, 'Oficinista', 1, 12234555),
(5, 'Marce', 'Luicho', 122344, 'Empleado', 1, 23331),
(6, 'Fernando', 'Larrosa', 132343, 'Administrador', 1, 1234567),
(7, 'rtewtew', 'wreqe', 123122, 'Empleado', 1, 232412),
(8, 'Roberto', 'Dangelis', 899885, 'Presupuesto', 1, 453433322),
(9, 'Dniela', 'rodriguez', 121111, 'Jefa compras', 1, 223434452),
(10, 'Jorge', 'Ambrosio', 12344455, 'Empleado', 1, 123123123),
(11, 'Martu', 'Lugani', 12123123, 'Gerente', 1, 4554665),
(12, '213', '2131', 131, '31w', 1, 131),
(13, '2313', '321321', 12434, '143123', 1, 2313),
(14, '123131', '2131', 23213, '123312', 1, 23123),
(15, '2173712', '123123', 21312, '127312', 1, 13123),
(16, 'dsyfdsy', '2131', 23133, '123232', 1, 12312),
(17, '123123', '1323', 3213, '31321', 1, 1231),
(18, '3213', '313', 3131, '321', 1, 3123);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presupuesto`
--

CREATE TABLE `presupuesto` (
  `id_presupuesto` int(11) NOT NULL,
  `fecha_alta` date NOT NULL,
  `operariosfabricacion` int(11) NOT NULL,
  `falgbaja` tinyint(2) NOT NULL,
  `cantidad_dias` int(11) NOT NULL,
  `hora_pres` time NOT NULL,
  `porcentage_beneficio` int(11) NOT NULL,
  `costo_presupuesto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `presupuesto`
--

INSERT INTO `presupuesto` (`id_presupuesto`, `fecha_alta`, `operariosfabricacion`, `falgbaja`, `cantidad_dias`, `hora_pres`, `porcentage_beneficio`, `costo_presupuesto`) VALUES
(1, '2018-09-02', 3, 0, 10, '19:53:00', 40, 49574),
(2, '2018-10-31', 1, 0, 4, '20:11:00', 0, 21240),
(3, '2018-10-31', 1, 0, 3, '20:12:00', 10, 7260),
(4, '2018-10-31', 1, 0, 3, '20:14:00', 10, 5448),
(5, '2018-10-31', 1, 0, 15, '20:26:00', 40, 84960),
(6, '2018-10-31', 1, 1, 19, '20:29:00', 30, 90960),
(8, '2018-09-15', 3, 1, 24, '19:31:00', 20, 56101),
(10, '2018-11-13', 2, 1, 25, '14:32:00', 30, 124880),
(12, '2018-10-08', 2, 1, 35, '17:26:00', 30, 205024),
(13, '2018-11-13', 6, 1, 13, '09:09:00', 50, 349328),
(14, '2018-10-15', 2, 1, 12, '09:14:00', 30, 87594),
(15, '2018-10-15', 3, 1, 21, '09:15:00', 40, 159161),
(16, '2018-10-15', 2, 1, 10, '09:20:00', 20, 51256),
(17, '2018-10-15', 3, 1, 23, '09:22:00', 10, 205758),
(18, '2018-10-15', 2, 1, 5, '09:29:00', 20, 30280),
(19, '2018-10-15', 2, 1, 13, '09:39:00', 20, 69336),
(20, '2018-10-01', 2, 1, 8, '09:41:00', 70, 40000),
(22, '2018-10-15', 2, 1, 13, '09:43:00', 30, 72950),
(23, '2018-10-15', 1, 1, 15, '09:45:00', 30, 49774),
(24, '2018-10-15', 2, 1, 4, '09:49:00', 30, 16216),
(25, '2018-10-15', 2, 1, 11, '09:49:00', 30, 69718),
(26, '2018-10-15', 2, 1, 5, '09:51:00', 20, 23520),
(27, '2018-10-15', 2, 1, 9, '09:54:00', 40, 56760),
(28, '2018-10-01', 2, 1, 3, '10:06:00', 30, 2),
(29, '2018-10-15', 2, 1, 10, '10:10:00', 20, 55056),
(30, '2018-10-15', 2, 1, 26, '10:12:00', 10, 157374),
(31, '2018-10-15', 1, 1, 49, '10:14:00', 10, 157374),
(32, '2018-10-15', 1, 1, 4, '10:19:00', 10, 9860),
(33, '2018-10-15', 1, 1, 34, '10:22:00', 0, 108466),
(34, '2018-10-15', 2, 1, 4, '10:28:00', 20, 25046),
(35, '2018-10-15', 2, 1, 4, '10:32:00', 20, 13660),
(36, '2018-10-15', 1, 1, 10, '10:36:00', 20, 29845),
(37, '2018-10-15', 1, 1, 21, '10:38:00', 0, 68356),
(38, '2018-10-15', 1, 1, 9, '10:42:00', 0, 28474),
(39, '2018-10-15', 1, 1, 5, '10:42:00', 10, 13660),
(40, '2018-10-15', 1, 1, 20, '10:43:00', 10, 59558),
(41, '2018-10-16', 2, 1, 5, '09:45:00', 30, 21300),
(42, '2018-10-23', 1, 1, 35, '17:37:00', 20, 92300),
(44, '2018-10-23', 1, 1, 20, '17:40:00', 60, 22537),
(46, '2018-10-30', 1, 1, 17, '12:44:00', 0, 54968),
(47, '2018-10-30', 1, 1, 2, '16:04:00', 0, 4456),
(48, '2018-10-30', 1, 1, 4, '16:18:00', 0, 10650),
(50, '2018-10-30', 1, 1, 2, '16:30:00', 0, 6060),
(51, '2018-10-30', 1, 0, 21, '18:58:00', 0, 67518),
(52, '2018-10-30', 2, 0, 79, '18:59:00', 40, 509148),
(53, '2018-11-13', 7, 1, 72, '11:40:00', 10, 1606440),
(54, '2018-11-13', 3, 1, 171, '11:41:00', 20, 1609380),
(55, '2018-11-13', 1, 1, 97, '11:44:00', 0, 658440),
(56, '2018-11-13', 1, 1, 67, '11:46:00', 0, 448660),
(57, '2018-11-13', 2, 1, 61, '11:50:00', 40, 732040);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `voltamperios` int(11) NOT NULL,
  `horas_hombre` float UNSIGNED NOT NULL,
  `lugarUtilizacion` varchar(2) NOT NULL DEFAULT 'IN',
  `falgbaja` tinyint(2) NOT NULL,
  `precio_hora_hombre` float UNSIGNED NOT NULL,
  `id_categoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idproducto`, `modelo`, `voltamperios`, `horas_hombre`, `lugarUtilizacion`, `falgbaja`, `precio_hora_hombre`, `id_categoria`) VALUES
(1, 'Clase 05/s', 10, 5, 'IN', 1, 300, 1),
(2, 'DC40', 22, 25, 'IN', 1, 400, 2),
(3, 'MAR', 233, 10, 'IN', 1, 500, 3),
(4, 'clase', 4, 122, 'IN', 1, 400, 2),
(5, 'sad', 12, 11, 'IN', 1, 300, 1),
(6, 'qweqw', 3221, 1, 'IN', 1, 400, 2),
(7, 'caraba', 123, 3, 'IN', 1, 400, NULL),
(8, 'mara', 1233, 5, 'EX', 1, 500, NULL),
(9, 'srdrd', 344, 34, 'IN', 1, 300, NULL),
(10, 'trtrtet', 123213, 22, 'IN', 1, 300, NULL),
(11, 'rewer', 123, 44, 'IN', 1, 300, NULL),
(12, 'wew', 23, 11, 'IN', 1, 300, NULL),
(13, 'mode', 564, 12, 'IN', 1, 300, NULL),
(14, 'lmj', 931, 7, 'IN', 1, 300, NULL),
(15, 'Clase 05 s', 3333, 10, 'EX', 1, 400, NULL),
(16, 'Nuevo', 12333, 12, 'EX', 1, 400, NULL),
(17, 'sadads', 213, 123, 'IN', 1, 300, NULL),
(18, '123', 3312, 20, 'IN', 1, 300, NULL),
(19, '123123', 123, 11, 'IN', 1, 300, NULL),
(20, 'sdadd', 123, 123, 'IN', 1, 300, NULL),
(21, 'dxzczv', 12312, 123, 'IN', 1, 300, NULL),
(22, 'edfsd', 1231, 132, 'IN', 1, 300, NULL),
(23, 'erwrwe', 12321, 123, 'IN', 1, 300, NULL),
(24, 'asdas', 12312, 1231, 'IN', 1, 300, NULL),
(25, 'sadsa', 123, 123, 'IN', 1, 300, NULL),
(26, '12sada1.2', 2, 1, 'IN', 1, 300, NULL),
(27, 'sadsad', 123, 123, 'IN', 1, 300, NULL),
(28, '123123', 213, 12, 'IN', 1, 300, NULL),
(29, 'masd', 64, 2, 'IN', 1, 300, NULL),
(30, 'wqeqw', 333, 1, 'IN', 1, 300, NULL),
(31, '123', 1232, 123, 'IN', 1, 300, NULL),
(32, '12', 12, 12, 'IN', 1, 300, NULL),
(33, 'qwe', 321, 123, 'IN', 1, 300, NULL),
(34, '123', 2, 1, 'IN', 1, 300, NULL),
(35, '2131', 1123, 213, 'IN', 1, 300, NULL),
(36, '43434', 123, 2, 'IN', 1, 300, NULL),
(37, 'qweq', 132, 12, 'IN', 1, 300, NULL),
(38, 'qweqw', 121, 123, 'IN', 1, 300, NULL),
(39, 'qweq', 3123, 23, 'IN', 1, 300, NULL),
(40, 'sad', 122, 2, 'IN', 1, 300, NULL),
(41, '123', 123, 23, 'IN', 1, 300, NULL),
(42, '1231', 23, 23, 'IN', 1, 300, NULL),
(43, 'qwe', 431, 3, 'IN', 1, 300, NULL),
(44, '123', 1, 23, 'IN', 1, 300, NULL),
(45, 'qwe1', 123, 31, 'IN', 1, 300, NULL),
(46, '23', 311, 34, 'IN', 1, 300, NULL),
(47, '324', 322, 4, 'IN', 1, 300, NULL),
(48, 'qwe', 123, 31, 'IN', 1, 300, NULL),
(49, 'wqe', 213, 123, 'IN', 1, 300, NULL),
(50, 'qw', 123, 31, 'IN', 1, 300, NULL),
(51, 'qwe', 13, 31, 'IN', 1, 300, NULL),
(52, '123', 21, 21, 'IN', 1, 300, NULL),
(53, '123', 123, 21, 'IN', 1, 300, NULL),
(54, '123', 123, 3, 'IN', 1, 300, NULL),
(55, '32', 2, 10, 'IN', 1, 300, NULL),
(56, '123', 1334, 123, 'IN', 1, 300, NULL),
(57, 'qwe', 123, 312, 'IN', 1, 300, NULL),
(58, 'qweqw', 231, 31, 'IN', 1, 300, NULL),
(59, 'dsahdssagfd', 12323, 2123, 'IN', 1, 300, NULL),
(60, 'qwewq', 123, 123, 'IN', 1, 300, NULL),
(61, 'wqe', 213, 213, 'IN', 1, 300, NULL),
(62, '123', 12312, 2313, 'IN', 1, 300, NULL),
(63, '123', 23, 12, 'IN', 1, 300, NULL),
(64, '324', 123, 5, 'IN', 1, 300, NULL),
(65, 'qwe', 123, 321, 'IN', 1, 300, NULL),
(66, '213', 5, 30, 'IN', 1, 300, NULL),
(67, 'masd', 12312, 2131, 'IN', 1, 300, NULL),
(68, 'asdsa', 123, 1231, 'IN', 1, 300, NULL),
(69, 'sadsaa', 12312, 21, 'IN', 1, 300, NULL),
(70, 'qweq', 1231, 23, 'IN', 1, 300, NULL),
(71, 'sadsa', 123, 213, 'IN', 1, 300, NULL),
(72, 'asds', 21312, 12, 'IN', 1, 300, NULL),
(73, '1231WQE', 123, 21, 'IN', 1, 300, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `idproveedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `idroles` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `permiso` int(11) NOT NULL,
  `flagbaja` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idroles`, `descripcion`, `permiso`, `flagbaja`) VALUES
(1, 'Administrador', 1, 1),
(2, 'Empleado', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_permisos`
--

CREATE TABLE `roles_permisos` (
  `permisos_id_permisos` int(11) NOT NULL,
  `roles_idroles` int(11) NOT NULL,
  `flagbaja` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_material`
--

CREATE TABLE `tipo_material` (
  `id_tipo` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `id_caract` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

--
-- Volcado de datos para la tabla `tipo_material`
--

INSERT INTO `tipo_material` (`id_tipo`, `descripcion`, `id_caract`) VALUES
(1, 'metales', 4),
(2, 'barras', 1),
(3, 'liquidos', 2),
(4, 'cables', 1),
(5, 'mailar', 1),
(6, 'solidos', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `nommbreUsuario` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `intentos` int(11) NOT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_ultimo_log` date NOT NULL,
  `rol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nommbreUsuario`, `contrasenia`, `id_persona`, `intentos`, `fecha_alta`, `fecha_ultimo_log`, `rol`) VALUES
('l', '1234', 1, 3, '2018-08-19', '2018-11-20', 1),
('m', '1111', 4, 3, '2018-10-04', '2018-11-20', 2),
('mmt', '12345', 5, 3, '2018-10-07', '2018-10-07', 2),
('fer', '1234', 6, 3, '2018-10-16', '2018-10-16', 1),
('sad', '1111', 7, 3, '2018-10-16', '2018-10-16', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_roles`
--

CREATE TABLE `usuario_roles` (
  `id_usuario` varchar(45) NOT NULL,
  `id_roles` int(11) NOT NULL,
  `flag_baja` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `caracteristica`
--
ALTER TABLE `caracteristica`
  ADD PRIMARY KEY (`id_carac`);

--
-- Indices de la tabla `categoriaemple`
--
ALTER TABLE `categoriaemple`
  ADD PRIMARY KEY (`id_cat`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indices de la tabla `contacto`
--
ALTER TABLE `contacto`
  ADD PRIMARY KEY (`idpersona`),
  ADD KEY `fk_contacto_cliente1_idx` (`cliente_idcliente`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`idempresa`);

--
-- Indices de la tabla `linea_material`
--
ALTER TABLE `linea_material`
  ADD PRIMARY KEY (`idlinea_material`,`idmaterial`,`idproducto`),
  ADD KEY `producto_linea_idx` (`idproducto`),
  ADD KEY `material_linea_idx` (`idmaterial`);

--
-- Indices de la tabla `linea_presupuesto`
--
ALTER TABLE `linea_presupuesto`
  ADD PRIMARY KEY (`id_linea`,`id_presupuesto`,`id_producto`),
  ADD KEY `linea_presup_idx` (`id_presupuesto`),
  ADD KEY `linea_producto_idx` (`id_producto`);

--
-- Indices de la tabla `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`idmaterial`),
  ADD KEY `material_tipo_idx` (`id_tipo`);

--
-- Indices de la tabla `material_proveedor`
--
ALTER TABLE `material_proveedor`
  ADD PRIMARY KEY (`id_proveedor`,`id_material`),
  ADD KEY `material_proveedor_idx` (`id_material`);

--
-- Indices de la tabla `operacionautoriza`
--
ALTER TABLE `operacionautoriza`
  ADD PRIMARY KEY (`idoperacion_auto`),
  ADD KEY `pedido_operacjion_idx` (`idpedido`),
  ADD KEY `presupuesto_operacion_idx` (`idpresupuesto`),
  ADD KEY `contacto_operacion_idx` (`idcontacto`);

--
-- Indices de la tabla `operacion_egreso`
--
ALTER TABLE `operacion_egreso`
  ADD PRIMARY KEY (`pedido_idpedido`,`usuario_id_persona`),
  ADD KEY `fk_operacion_egreso_pedido1_idx` (`pedido_idpedido`),
  ADD KEY `fk_operacion_egreso_usuario1_idx` (`usuario_id_persona`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`idpedido`,`presupuesto_id_presupuesto`),
  ADD KEY `fk_pedido_presupuesto1_idx` (`presupuesto_id_presupuesto`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id_permisos`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`);

--
-- Indices de la tabla `presupuesto`
--
ALTER TABLE `presupuesto`
  ADD PRIMARY KEY (`id_presupuesto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idproducto`),
  ADD KEY `modelo` (`modelo`),
  ADD KEY `voltamperios` (`voltamperios`),
  ADD KEY `categoria_idx` (`id_categoria`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`idproveedor`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idroles`),
  ADD KEY `fk_roles_permiso_idx` (`permiso`);

--
-- Indices de la tabla `roles_permisos`
--
ALTER TABLE `roles_permisos`
  ADD PRIMARY KEY (`permisos_id_permisos`,`roles_idroles`),
  ADD KEY `fk_roles_permisos_permisos1_idx` (`permisos_id_permisos`),
  ADD KEY `fk_roles_permisos_roles1_idx` (`roles_idroles`);

--
-- Indices de la tabla `tipo_material`
--
ALTER TABLE `tipo_material`
  ADD PRIMARY KEY (`id_tipo`),
  ADD KEY `tipomaterial_caracteristica_idx` (`id_caract`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_persona`,`nommbreUsuario`),
  ADD KEY `fk_usuario_persona1_idx` (`id_persona`),
  ADD KEY `nombre` (`nommbreUsuario`),
  ADD KEY `fk_usuario_rol_idx` (`rol`);

--
-- Indices de la tabla `usuario_roles`
--
ALTER TABLE `usuario_roles`
  ADD PRIMARY KEY (`id_usuario`,`id_roles`),
  ADD KEY `fk_usuario_roles_roles1_idx` (`id_roles`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `caracteristica`
--
ALTER TABLE `caracteristica`
  MODIFY `id_carac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idempresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `material`
--
ALTER TABLE `material`
  MODIFY `idmaterial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `operacionautoriza`
--
ALTER TABLE `operacionautoriza`
  MODIFY `idoperacion_auto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `idpedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id_permisos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id_persona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idproducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `idroles` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_material`
--
ALTER TABLE `tipo_material`
  MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_empresa` FOREIGN KEY (`idcliente`) REFERENCES `empresa` (`idempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `contacto`
--
ALTER TABLE `contacto`
  ADD CONSTRAINT `fk_contacto_cliente1` FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `linea_material`
--
ALTER TABLE `linea_material`
  ADD CONSTRAINT `material_linea` FOREIGN KEY (`idmaterial`) REFERENCES `material` (`idmaterial`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `producto_linea` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `linea_presupuesto`
--
ALTER TABLE `linea_presupuesto`
  ADD CONSTRAINT `linea_presup` FOREIGN KEY (`id_presupuesto`) REFERENCES `presupuesto` (`id_presupuesto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `linea_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `material`
--
ALTER TABLE `material`
  ADD CONSTRAINT `material_tipo` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_material` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `material_proveedor`
--
ALTER TABLE `material_proveedor`
  ADD CONSTRAINT `material_proveedor` FOREIGN KEY (`id_material`) REFERENCES `material` (`idmaterial`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `proveedorMaterial` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`idproveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `operacionautoriza`
--
ALTER TABLE `operacionautoriza`
  ADD CONSTRAINT `contacto_operacion` FOREIGN KEY (`idcontacto`) REFERENCES `contacto` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `pedido_operacjion` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`idpedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `presupuesto_operacion` FOREIGN KEY (`idpresupuesto`) REFERENCES `presupuesto` (`id_presupuesto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `operacion_egreso`
--
ALTER TABLE `operacion_egreso`
  ADD CONSTRAINT `fk_operacion_egreso_pedido1` FOREIGN KEY (`pedido_idpedido`) REFERENCES `pedido` (`idpedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_operacion_egreso_usuario1` FOREIGN KEY (`usuario_id_persona`) REFERENCES `usuario` (`id_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_pedido_presupuesto1` FOREIGN KEY (`presupuesto_id_presupuesto`) REFERENCES `presupuesto` (`id_presupuesto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoriaemple` (`id_cat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD CONSTRAINT `fk_proveedor_empresa` FOREIGN KEY (`idproveedor`) REFERENCES `empresa` (`idempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `roles`
--
ALTER TABLE `roles`
  ADD CONSTRAINT `fk_roles_permiso` FOREIGN KEY (`permiso`) REFERENCES `permisos` (`id_permisos`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `roles_permisos`
--
ALTER TABLE `roles_permisos`
  ADD CONSTRAINT `fk_roles_permisos_permisos1` FOREIGN KEY (`permisos_id_permisos`) REFERENCES `permisos` (`id_permisos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_roles_permisos_roles1` FOREIGN KEY (`roles_idroles`) REFERENCES `roles` (`idroles`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tipo_material`
--
ALTER TABLE `tipo_material`
  ADD CONSTRAINT `tipomaterial_caracteristica` FOREIGN KEY (`id_caract`) REFERENCES `caracteristica` (`id_carac`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_persona1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`rol`) REFERENCES `roles` (`idroles`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario_roles`
--
ALTER TABLE `usuario_roles`
  ADD CONSTRAINT `fk_usuario_roles_roles1` FOREIGN KEY (`id_roles`) REFERENCES `roles` (`idroles`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_roles_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`nommbreUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
