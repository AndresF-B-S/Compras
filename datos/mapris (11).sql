-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-08-2017 a las 03:25:09
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mapris`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_validar_usuario` (IN `pr_cedula` VARCHAR(20), IN `pr_clave` VARCHAR(20))  NO SQL
SELECT * FROM usuarios WHERE cedula = pr_cedula AND  clave = fc_encriptar(pr_clave)$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `fc_descencriptar` (`clave_codificada` VARCHAR(10)) RETURNS VARCHAR(10) CHARSET latin1 BEGIN
DECLARE var VARCHAR(10);
SET var = (SELECT decode(clave_codificada,255));
RETURN var;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `fc_encriptar` (`clave` VARCHAR(35) CHARSET utf8) RETURNS VARCHAR(35) CHARSET utf8 BEGIN
DECLARE var VARCHAR(35);
SET var = (SELECT MD5(clave));
return var;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aplazamientos`
--

CREATE TABLE `aplazamientos` (
  `fk_id_usuario` int(11) NOT NULL,
  `motivo` text COMMENT 'Este campo almacena la razon por la cual se hace el aplazamiento',
  `inicio` datetime DEFAULT NULL,
  `fin` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `fk_id_usuario` int(11) NOT NULL,
  `estado` varchar(20) NOT NULL COMMENT 'Este campo almacena el estado que se encuentra el cliente',
  `fk_id_empresa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `id_curso` int(11) NOT NULL,
  `fk_id_servicio` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `sesiones` int(11) DEFAULT NULL,
  `valor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datosclinicos`
--

CREATE TABLE `datosclinicos` (
  `id_datos_clinicos` int(11) NOT NULL,
  `tipo_sangre` varchar(3) DEFAULT NULL COMMENT 'Este campo almacena el tipo de sangre del cliente',
  `alergias` varchar(20) DEFAULT NULL COMMENT 'Este campo almacena el archivo correspondiente al dato clinico del programa Posparto',
  `url` int(11) DEFAULT NULL COMMENT 'Este campo almacena el archivo correspondiente al dato clinico del programa Prenatal',
  `fk_id_usuario` int(11) NOT NULL,
  `patologia` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_horario`
--

CREATE TABLE `detalle_horario` (
  `iddetalle_horario` int(11) NOT NULL,
  `horarios_id_horario` int(11) NOT NULL,
  `hora_inicio` time DEFAULT NULL,
  `duracion` int(11) DEFAULT NULL,
  `dia_semana` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
  `id_empresa` int(11) NOT NULL,
  `NIT` varchar(20) NOT NULL COMMENT 'Este campo es la clave primaria de la tabla y  almacena el (id) ',
  `nombre` varchar(45) NOT NULL COMMENT 'Este campo almacena el nombre de la empresa',
  `direccionP` varchar(45) DEFAULT NULL,
  `direccionO` varchar(45) DEFAULT NULL,
  `telefonoF` bigint(20) DEFAULT NULL,
  `telefonoC` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`id_empresa`, `NIT`, `nombre`, `direccionP`, `direccionO`, `telefonoF`, `telefonoC`) VALUES
(1, '8192', 'Cafam', 'Cll 54 # 32 - 53', 'Cra 182 # 45 - 31', 1231231, 4123123123),
(2, '9178293', 'Exito', 'Cll 54 # 32 - 53', 'Cra 182 # 45 - 31', 5019230, 3109230182);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `id_estados` int(11) NOT NULL COMMENT 'Los estados hace referencia al acceso del sistema de información, si su estado al momento de ingresar al sistema es bloqueado, no podrá ingresar, mientras que, si su estado es activo accedera con normalidad. Esto lo decide el administrador al bloquear o no un usuario, el estado por default que tendra un cliente al registrarse es bloqueado mientras se le asigna su programa y rutina, y se avala su inscripción',
  `nombre` varchar(45) DEFAULT NULL,
  `descripccion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`id_estados`, `nombre`, `descripccion`) VALUES
(1, 'Activo', 'Activo en el sistema'),
(2, 'Bloqueado', 'Bloqueado del sistema'),
(3, 'Sin Rol', 'Sin rol asignado en el sistema');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios`
--

CREATE TABLE `horarios` (
  `id_horario` int(11) NOT NULL,
  `fk_id_curso` int(11) NOT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripciones`
--

CREATE TABLE `inscripciones` (
  `id_inscripcion` int(11) NOT NULL,
  `fk_id_curso` int(11) NOT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `url` text,
  `icon` varchar(45) DEFAULT NULL,
  `permisos_padre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id`, `nombre`, `url`, `icon`, `permisos_padre`) VALUES
(0, 'inicio', '/app/index.xhtml\n', 'fa fa-home', NULL),
(1, 'perfil', '', 'perm identity', NULL),
(2, 'usuarios', '/app/administrador/usuarios/ver.xhtml', 'group', NULL),
(3, 'aplazamientos', '', 'fa fa-clock-o', NULL),
(4, 'citas', '', 'fa fa-calendar-check-o', NULL),
(5, 'calendario', '', 'fa fa-calendar', NULL),
(6, 'agendar', '', 'fa fa-calendar-check-o', NULL),
(7, 'inscripciones', '/app/administrador/inscripciones/ver.xhtml', 'fa fa-list-alt', NULL),
(8, 'empresa', '/app/administrador/empresa/ver.xhtml', 'fa fa-columns', NULL),
(9, 'rutina', '/app/administrador/rutinas/ver.xhtml', 'fa fa-user-plus', NULL),
(10, 'sesiones', '/app/administrador/sesiones/ver.xhtml', 'fa fa-cog', NULL),
(11, 'miPerfil', '/app/perfil/miperfil.xhtml', 'fa fa-user', 1),
(12, 'cambiarDatos', '/app/perfil/cambiardatos.xhtml', 'fa fa-pencil', 1),
(13, 'programas', NULL, NULL, NULL),
(21, 'listarUsuarios', '/app/usuarios/listar.xhtml', 'fa fa-list', 2),
(22, 'nuevoUsuario', '/app/usuarios/nuevo.xhtml', 'fa fa-user-plus', 2),
(30, 'editarAplazamiento', '/app//usuarios/editar.xhtml', 'fa fa-pencil-square-o', 2),
(31, 'listaAplazamientos', '/app/aplazamientos/lista.xhtml', 'fa fa-list', 3),
(32, 'registrarAplazamiento', '/app/aplazamientos/registrarAplazamiento.xhtml', 'fa fa-plus', 3),
(33, 'verAplazamiento', '/app/aplazamientos/ver.xhtml', 'fa fa-user', 3),
(34, 'verAplazamiento', '/app/aplazamientos/verCliente.xhtml', 'fa fa-user', 3),
(41, 'misProgramas', '/app/cliente/programas/programas.xhtml', NULL, 13),
(51, 'nuevoCalendario', '/app/administrador/calendario/nuevo.xhtml', 'fa fa-user-plus', 5),
(52, 'listarCalendario', '/app/administrador/calendario/calendario.xhtml', 'fa fa-calendar', 5),
(61, 'reservar', '/app/cliente/servicios/servicios.xhtml', 'fa fa-pencil-square-o', 6),
(62, 'Mi calendario', '/app/cliente/calendario/calendario.xhtml', NULL, 5),
(71, 'listaInscripciones', '/app/administrador/inscripciones/inscripciones.xhtml', 'fa fa-list-ol', 7),
(72, 'editarIncripcion', '/app/administrador/inscripciones/editar.xhtml', 'fa fa-pencil', 7),
(73, 'nuevaInscripcion', '/app/administrador/inscripciones/registrar.xhtml', 'fa fa-user-plus', 7),
(81, 'listarEmpresas', '/app/administrador/empresa/empresas.xhtml', 'fa fa-th-large', 8),
(82, 'editarEmpresa', '/app/administrador/empresa/editar.xhtml', 'fa fa-pencil', 8),
(83, 'nuevaEmpresa', '/app/administrador/empresa/registrar.xhtml', 'fa fa-user-plus', 8),
(91, 'listarRutina', '/app/administrador/rutinas/rutinas.xhtml', 'fa fa-th-large', 9),
(92, 'editarRutina', '/app/administrador/rutinas/editar.xhtml', 'fa fa-cog', 9),
(93, 'nuevaRutina', '/app/administrador/rutinas/nuevo.xhtml', 'fa fa-th-large', 9),
(101, 'nuevaSesion', '/app/administrador/sesiones/registrar.xhtml', 'fa fa-user-plus', 10),
(102, 'listarSesiones', '/app/administrador/sesiones/sesiones.xhtml', 'fa fa-th-large', 10),
(103, 'editarSesiones', '/app/administrador/sesiones/editar.xhtml', 'fa fa-cog', 10),
(111, 'salones', '/app/administrador/salones/ver.xhtml', 'fa fa-cog', NULL),
(112, 'registrarSalones', '/app/administrador/salones/registrar.xhtml', 'fa fa-user', 111),
(113, 'listarSalones', '/app/administrador/salones/salones.xhtml', 'fa fa-user', 111),
(114, 'editarSalones', '/app/administrador/salones/editar.xhtml', 'fa fa-user', 111),
(222, 'programas', '/app/administrador/programas/ver.xhtml', 'fa fa-user', NULL),
(223, 'listarProgramas', '/app/administrador/programas/programas.xhtml', 'fa fa-th-large', 222),
(224, 'registrarProgramas', '/app/administrador/programas/registrar.xhtml', 'fa fa-cog', 222),
(225, 'editarProgramas', '/app/administrador/programas/editar.xhtml', 'fa fa-user', 222),
(333, 'personalMedico', '/app/administrador/personal/ver.xhtml', 'fa fa-user', NULL),
(334, 'listarPersonal', '/app/administrador/personal/medico.xhtml', 'fa fa-th-large', 333),
(335, 'nuevoPersonal', '/app/administrador/personal/registrar.xhtml', 'fa fa-user-plus', 333),
(444, 'salonesServicios', '/app/administrador/salones-servicios/editar.xhtml', 'fa fa-user', NULL),
(445, 'listarSalonesServicios', '/app/administrador/salones-servicios/salones-servicios.xhtml', 'fa fa-th-large', 444),
(446, 'nuevoSalonesServicios', '/app/administrador/salones-servicios/nuevo.xhtml', 'fa fa-user', 444),
(555, 'servicio', '/app/administrador/servicios/ver.xhtml', 'fa fa-user', NULL),
(556, 'listarServicio', '/app/administrador/servicios/servicios.xhtml', 'fa fa-th-large', 555),
(557, 'registrarServicio', '/app/administrador/servicios/registrar.xhtml', 'fa fa-th-large', 555),
(558, 'editarServicio', '/app/administrador/servicios/editar.xhtml', 'fa fa-th-large', 555),
(666, 'cliente', '/app/administrador/clientes/ver.xhtml', 'fa fa-th-large', NULL),
(667, 'listarCliente', '/app/administrador/clientes/cliente.xhtml', 'fa fa-user', 666),
(668, 'editarCliente', '/app/administrador/clientes/editar.xhtml', 'fa fa-user', 666),
(669, 'registrarCliente', '/app/administrador/clientes/registrar.xhtml', 'fa fa-user', 666),
(777, 'datosClinicos', '', 'fa fa-user', NULL),
(778, 'registrarDatos', '/app/cliente/datos/registrar.xhtml', 'fa fa-user', 777),
(779, 'verDatos', '/app/cliente/datos/ver.xhtml', 'fa fa-user', 777),
(780, 'datosClinicos', NULL, 'fa fa-user', NULL),
(781, 'listarDatos', '/app/personal/datos/clinicos.xhtml', 'fa fa-user', 780),
(782, 'verDatos', '/app/personal/datos/ver.xhtml', 'fa fa-user', 780);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisosroles`
--

CREATE TABLE `permisosroles` (
  `fk_id_permiso` int(11) NOT NULL,
  `fk_id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `permisosroles`
--

INSERT INTO `permisosroles` (`fk_id_permiso`, `fk_id_rol`) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(3, 2),
(5, 1),
(5, 2),
(6, 2),
(7, 1),
(8, 1),
(9, 3),
(10, 1),
(11, 1),
(11, 2),
(12, 1),
(12, 2),
(13, 2),
(21, 1),
(22, 1),
(31, 1),
(32, 2),
(33, 1),
(34, 2),
(41, 2),
(51, 1),
(52, 1),
(61, 2),
(62, 2),
(71, 1),
(72, 1),
(73, 1),
(81, 1),
(82, 1),
(83, 1),
(91, 3),
(92, 3),
(93, 3),
(101, 1),
(102, 1),
(111, 3),
(112, 3),
(113, 3),
(114, 3),
(222, 3),
(223, 3),
(224, 3),
(225, 3),
(333, 1),
(334, 1),
(335, 1),
(444, 3),
(445, 3),
(446, 3),
(555, 3),
(556, 3),
(557, 3),
(558, 3),
(666, 1),
(666, 3),
(667, 1),
(667, 3),
(668, 1),
(668, 3),
(669, 1),
(669, 3),
(777, 2),
(778, 2),
(779, 2),
(780, 3),
(781, 3),
(782, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personalmedico`
--

CREATE TABLE `personalmedico` (
  `fk_id_usuario` int(11) NOT NULL,
  `perfilProfesional` varchar(45) NOT NULL COMMENT 'Este campo almacena la especialidad que tiene cada Personal Medico ',
  `cargo` varchar(20) NOT NULL COMMENT 'Este campo almacena el tipo de cargo que tiene el personal Medico (Prenatal o Posnatal)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `personalmedico`
--

INSERT INTO `personalmedico` (`fk_id_usuario`, `perfilProfesional`, `cargo`) VALUES
(3, 'Pediatra', 'Profesional en la sa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `idRoles` int(11) NOT NULL COMMENT 'Este campo es la clave primaria de la tabla y  almacena el (id)',
  `nombre` varchar(20) NOT NULL COMMENT 'Este campo almacena el nombre de cada rol',
  `descripcion` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idRoles`, `nombre`, `descripcion`) VALUES
(1, 'Administrador', 'Encargado primordial del sistema'),
(2, 'Cliente', 'Usuario recurrente del sistema'),
(3, 'Personal Medico', 'Personal involucrado del gimnasio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rolesusuarios`
--

CREATE TABLE `rolesusuarios` (
  `fk_id_roles` int(11) NOT NULL DEFAULT '2' COMMENT 'Este campo almacena el (id) de la tabla "Roles"',
  `fk_id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rolesusuarios`
--

INSERT INTO `rolesusuarios` (`fk_id_roles`, `fk_id_usuario`) VALUES
(1, 2),
(2, 3),
(3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salones`
--

CREATE TABLE `salones` (
  `id_salones` int(11) NOT NULL COMMENT 'La tabla salones hace referencia los espacios que tiene el gimnasio para el desarrollo de cada uno de los programas\n',
  `salon` varchar(45) NOT NULL,
  `descripcion` text,
  `estado` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `salones`
--

INSERT INTO `salones` (`id_salones`, `salon`, `descripcion`, `estado`) VALUES
(1, 'Salón Prenatal A', 'Salón acondicionado con elementos fisicos, para las actividades prenatales', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salones_has_servicios`
--

CREATE TABLE `salones_has_servicios` (
  `salones_id_salones` int(11) NOT NULL,
  `servicios_id_servicio` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `id_servicio` int(11) NOT NULL COMMENT 'La tabla servicios hace referencia a los servicios que el gimnasio ofrece en los programas o independientemente de ellos, es decir, Spa, Pilates, Yoga, Baby Rumba, Control Nutricional, entre otros. Por lo general las rutinas contienen una serie de servicios y una rutina podrá pertenecer a muchos programas que harían un paquete completo que ofrece el gimnasio. ',
  `nombre` varchar(20) DEFAULT NULL COMMENT 'Este campo almacena el nombre de programa ',
  `descripcion` text COMMENT 'Este campo almacena una pequeña descripcion de cada programa',
  `valor` double DEFAULT NULL,
  `objetivo` text,
  `sesiones` int(11) NOT NULL,
  `fk_sub_servicios` int(11) DEFAULT NULL,
  `fk_tipo_servicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`id_servicio`, `nombre`, `descripcion`, `valor`, `objetivo`, `sesiones`, `fk_sub_servicios`, `fk_tipo_servicio`) VALUES
(1, 'Yoga', 'Clase de relajación orientada a las artes orientales del Yoga', 100000, 'Relajación', 4, NULL, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_servicios`
--

CREATE TABLE `tipos_servicios` (
  `id_tipo_servicio` int(11) NOT NULL,
  `tipos_servicio` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipos_servicios`
--

INSERT INTO `tipos_servicios` (`id_tipo_servicio`, `tipos_servicio`, `descripcion`) VALUES
(1, 'Programa', 'Agrupación de servicios'),
(2, 'Rutina', 'Servicio conformado por distintas actividades'),
(3, 'Servicio', 'Actividad individual');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `cedula` varchar(20) NOT NULL COMMENT 'Este campo almacena la cedula de todos los usuarios',
  `nombres` varchar(20) NOT NULL COMMENT 'Este campo almacena el nombre de cada usuario',
  `apellidos` varchar(20) NOT NULL,
  `clave` varchar(200) NOT NULL,
  `fechaNaci` date DEFAULT NULL COMMENT 'Este campo almacena la fecha de nacimineto de cada usuario',
  `imagen_perfil` varchar(200) DEFAULT NULL,
  `correoElectronico` varchar(45) DEFAULT NULL,
  `telefonoFijo` varchar(7) DEFAULT '0000000',
  `telefonoCelular` varchar(10) DEFAULT '310000000',
  `fecha_registro` date DEFAULT NULL,
  `fk_id_estados` int(11) NOT NULL DEFAULT '3'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `cedula`, `nombres`, `apellidos`, `clave`, `fechaNaci`, `imagen_perfil`, `correoElectronico`, `telefonoFijo`, `telefonoCelular`, `fecha_registro`, `fk_id_estados`) VALUES
(2, '1111', 'Lola', 'Martinez', 'fceeb9b9d469401fe558062c4bd25954', '1999-01-01', '/files/profileimg/1111/perfil/perfil.jpg', '', '0000000', '310000000', '2017-08-02', 1),
(3, '7777', 'Maria', 'Loaiza', 'fceeb9b9d469401fe558062c4bd25954', '1999-08-10', '/files/users/7777/profile_img/perfil.png', 'mari@gmail.com', '5019230', '3109230182', '2017-08-03', 1);

--
-- Disparadores `usuarios`
--
DELIMITER $$
CREATE TRIGGER `tr_before_update_password` BEFORE UPDATE ON `usuarios` FOR EACH ROW SET new.clave = fc_encriptar(new.clave)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tr_usuario_before_insert` BEFORE INSERT ON `usuarios` FOR EACH ROW SET NEW.clave =  fc_encriptar(new.clave),
new.fecha_registro = CURRENT_DATE
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aplazamientos`
--
ALTER TABLE `aplazamientos`
  ADD PRIMARY KEY (`fk_id_usuario`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`fk_id_usuario`),
  ADD KEY `fk_clientes_empresas2_idx` (`fk_id_empresa`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id_curso`),
  ADD KEY `fk_cursos_servicios1_idx` (`fk_id_servicio`);

--
-- Indices de la tabla `datosclinicos`
--
ALTER TABLE `datosclinicos`
  ADD PRIMARY KEY (`id_datos_clinicos`),
  ADD KEY `fk_datosclinicos_clientes1_idx` (`fk_id_usuario`);

--
-- Indices de la tabla `detalle_horario`
--
ALTER TABLE `detalle_horario`
  ADD PRIMARY KEY (`iddetalle_horario`),
  ADD KEY `fk_detalle_horario_horarios1_idx` (`horarios_id_horario`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
  ADD PRIMARY KEY (`id_empresa`);

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id_estados`);

--
-- Indices de la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD PRIMARY KEY (`id_horario`),
  ADD KEY `fk_horarios_cursos1_idx` (`fk_id_curso`);

--
-- Indices de la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  ADD PRIMARY KEY (`id_inscripcion`),
  ADD KEY `fk_clientes_has_cursos_cursos1_idx` (`fk_id_curso`),
  ADD KEY `fk_inscripciones_clientes1_idx` (`fk_id_usuario`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_permisos_permisos1_idx` (`permisos_padre`);

--
-- Indices de la tabla `permisosroles`
--
ALTER TABLE `permisosroles`
  ADD PRIMARY KEY (`fk_id_permiso`,`fk_id_rol`),
  ADD KEY `fk_permisos_has_roles_roles1_idx` (`fk_id_rol`),
  ADD KEY `fk_permisos_has_roles_permisos1_idx` (`fk_id_permiso`);

--
-- Indices de la tabla `personalmedico`
--
ALTER TABLE `personalmedico`
  ADD PRIMARY KEY (`fk_id_usuario`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idRoles`);

--
-- Indices de la tabla `rolesusuarios`
--
ALTER TABLE `rolesusuarios`
  ADD PRIMARY KEY (`fk_id_usuario`,`fk_id_roles`),
  ADD KEY `fkRoles_RolesUsuarios_idx` (`fk_id_roles`);

--
-- Indices de la tabla `salones`
--
ALTER TABLE `salones`
  ADD PRIMARY KEY (`id_salones`);

--
-- Indices de la tabla `salones_has_servicios`
--
ALTER TABLE `salones_has_servicios`
  ADD PRIMARY KEY (`salones_id_salones`,`servicios_id_servicio`),
  ADD KEY `fk_salones_has_servicios_servicios1_idx` (`servicios_id_servicio`),
  ADD KEY `fk_salones_has_servicios_salones1_idx` (`salones_id_salones`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`id_servicio`),
  ADD KEY `fk_servicios_subservicios_idx` (`fk_sub_servicios`),
  ADD KEY `fk_servicios_tipos_servicios1_idx` (`fk_tipo_servicio`);

--
-- Indices de la tabla `tipos_servicios`
--
ALTER TABLE `tipos_servicios`
  ADD PRIMARY KEY (`id_tipo_servicio`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `cedula_UNIQUE` (`cedula`),
  ADD KEY `fk_usuarios_estados_idx` (`fk_id_estados`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cursos`
--
ALTER TABLE `cursos`
  MODIFY `id_curso` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `datosclinicos`
--
ALTER TABLE `datosclinicos`
  MODIFY `id_datos_clinicos` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `empresas`
--
ALTER TABLE `empresas`
  MODIFY `id_empresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `estados`
--
ALTER TABLE `estados`
  MODIFY `id_estados` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Los estados hace referencia al acceso del sistema de información, si su estado al momento de ingresar al sistema es bloqueado, no podrá ingresar, mientras que, si su estado es activo accedera con normalidad. Esto lo decide el administrador al bloquear o no un usuario, el estado por default que tendra un cliente al registrarse es bloqueado mientras se le asigna su programa y rutina, y se avala su inscripción', AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `horarios`
--
ALTER TABLE `horarios`
  MODIFY `id_horario` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  MODIFY `id_inscripcion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `salones`
--
ALTER TABLE `salones`
  MODIFY `id_salones` int(11) NOT NULL AUTO_INCREMENT COMMENT 'La tabla salones hace referencia los espacios que tiene el gimnasio para el desarrollo de cada uno de los programas\n', AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT COMMENT 'La tabla servicios hace referencia a los servicios que el gimnasio ofrece en los programas o independientemente de ellos, es decir, Spa, Pilates, Yoga, Baby Rumba, Control Nutricional, entre otros. Por lo general las rutinas contienen una serie de servicios y una rutina podrá pertenecer a muchos programas que harían un paquete completo que ofrece el gimnasio. ', AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tipos_servicios`
--
ALTER TABLE `tipos_servicios`
  MODIFY `id_tipo_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `aplazamientos`
--
ALTER TABLE `aplazamientos`
  ADD CONSTRAINT `fk_aplazamientos_clientes1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `clientes` (`fk_id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `fk_clientes_empresas2` FOREIGN KEY (`fk_id_empresa`) REFERENCES `empresas` (`id_empresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_clientes_usuarios2` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `fk_cursos_servicios1` FOREIGN KEY (`fk_id_servicio`) REFERENCES `servicios` (`id_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `datosclinicos`
--
ALTER TABLE `datosclinicos`
  ADD CONSTRAINT `fk_datosclinicos_clientes1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `clientes` (`fk_id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_horario`
--
ALTER TABLE `detalle_horario`
  ADD CONSTRAINT `fk_detalle_horario_horarios1` FOREIGN KEY (`horarios_id_horario`) REFERENCES `horarios` (`id_horario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD CONSTRAINT `fk_horarios_cursos1` FOREIGN KEY (`fk_id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  ADD CONSTRAINT `fk_clientes_has_cursos_cursos1` FOREIGN KEY (`fk_id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_inscripciones_clientes1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `clientes` (`fk_id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `fk_permisos_permisos1` FOREIGN KEY (`permisos_padre`) REFERENCES `permisos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `permisosroles`
--
ALTER TABLE `permisosroles`
  ADD CONSTRAINT `fk_permisos_has_roles_permisos1` FOREIGN KEY (`fk_id_permiso`) REFERENCES `permisos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_permisos_has_roles_roles1` FOREIGN KEY (`fk_id_rol`) REFERENCES `roles` (`idRoles`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `personalmedico`
--
ALTER TABLE `personalmedico`
  ADD CONSTRAINT `fk_personalmedico_usuarios1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `rolesusuarios`
--
ALTER TABLE `rolesusuarios`
  ADD CONSTRAINT `fK_RolesUsuarios` FOREIGN KEY (`fk_id_roles`) REFERENCES `roles` (`idRoles`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_rolesusuarios_usuarios1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `salones_has_servicios`
--
ALTER TABLE `salones_has_servicios`
  ADD CONSTRAINT `fk_salones_has_servicios_salones1` FOREIGN KEY (`salones_id_salones`) REFERENCES `salones` (`id_salones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_salones_has_servicios_servicios1` FOREIGN KEY (`servicios_id_servicio`) REFERENCES `servicios` (`id_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD CONSTRAINT `fk_servicios_subservicios` FOREIGN KEY (`fk_sub_servicios`) REFERENCES `servicios` (`id_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_servicios_tipos_servicios1` FOREIGN KEY (`fk_tipo_servicio`) REFERENCES `tipos_servicios` (`id_tipo_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`fk_id_estados`) REFERENCES `estados` (`id_estados`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
