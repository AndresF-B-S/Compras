CREATE DATABASE  IF NOT EXISTS `mapris` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mapris`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mapris
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aplazamientos`
--

DROP TABLE IF EXISTS `aplazamientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aplazamientos` (
  `fk_id_usuario` int(11) NOT NULL,
  `motivo` text COMMENT 'Este campo almacena la razon por la cual se hace el aplazamiento',
  `inicio` datetime DEFAULT NULL,
  `fin` datetime DEFAULT NULL,
  PRIMARY KEY (`fk_id_usuario`),
  CONSTRAINT `fk_aplazamientos_clientes1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `clientes` (`fk_id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aplazamientos`
--

LOCK TABLES `aplazamientos` WRITE;
/*!40000 ALTER TABLE `aplazamientos` DISABLE KEYS */;
/*!40000 ALTER TABLE `aplazamientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `fk_id_usuario` int(11) NOT NULL,
  `estado` varchar(20) NOT NULL COMMENT 'Este campo almacena el estado que se encuentra el cliente',
  `fk_id_empresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`fk_id_usuario`),
  KEY `fk_clientes_empresas2_idx` (`fk_id_empresa`),
  CONSTRAINT `fk_clientes_empresas2` FOREIGN KEY (`fk_id_empresa`) REFERENCES `empresas` (`id_empresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_clientes_usuarios2` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (3,'Activa',1),(4,'Activa',1);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos` (
  `id_curso` int(11) NOT NULL AUTO_INCREMENT,
  `fk_id_servicio` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `sesiones` int(11) DEFAULT NULL,
  `valor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_curso`),
  KEY `fk_cursos_servicios1_idx` (`fk_id_servicio`),
  CONSTRAINT `fk_cursos_servicios1` FOREIGN KEY (`fk_id_servicio`) REFERENCES `servicios` (`id_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (1,1,'En proceso',8,100000);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datosclinicos`
--

DROP TABLE IF EXISTS `datosclinicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datosclinicos` (
  `id_datos_clinicos` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_sangre` varchar(3) DEFAULT NULL COMMENT 'Este campo almacena el tipo de sangre del cliente',
  `alergias` varchar(45) DEFAULT NULL COMMENT 'Este campo almacena el archivo correspondiente al dato clinico del programa Posparto',
  `url` varchar(80) DEFAULT NULL COMMENT 'Este campo almacena el archivo correspondiente al dato clinico del programa Prenatal',
  `fk_id_usuario` int(11) NOT NULL,
  `patologia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_datos_clinicos`),
  KEY `fk_datosclinicos_clientes1_idx` (`fk_id_usuario`),
  CONSTRAINT `fk_datosclinicos_clientes1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `clientes` (`fk_id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datosclinicos`
--

LOCK TABLES `datosclinicos` WRITE;
/*!40000 ALTER TABLE `datosclinicos` DISABLE KEYS */;
/*!40000 ALTER TABLE `datosclinicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_horario`
--

DROP TABLE IF EXISTS `detalle_horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_horario` (
  `iddetalle_horario` int(11) NOT NULL,
  `horarios_id_horario` int(11) NOT NULL,
  `hora_inicio` time DEFAULT NULL,
  `duracion` int(11) DEFAULT NULL,
  `dia_semana` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddetalle_horario`),
  KEY `fk_detalle_horario_horarios1_idx` (`horarios_id_horario`),
  CONSTRAINT `fk_detalle_horario_horarios1` FOREIGN KEY (`horarios_id_horario`) REFERENCES `horarios` (`id_horario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_horario`
--

LOCK TABLES `detalle_horario` WRITE;
/*!40000 ALTER TABLE `detalle_horario` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresas`
--

DROP TABLE IF EXISTS `empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresas` (
  `id_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `NIT` varchar(20) NOT NULL COMMENT 'Este campo es la clave primaria de la tabla y  almacena el (id) ',
  `nombre` varchar(45) NOT NULL COMMENT 'Este campo almacena el nombre de la empresa',
  `direccionP` varchar(45) DEFAULT NULL,
  `direccionO` varchar(45) DEFAULT NULL,
  `telefonoF` bigint(20) DEFAULT NULL,
  `telefonoC` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresas`
--

LOCK TABLES `empresas` WRITE;
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
INSERT INTO `empresas` VALUES (1,'8192','Cafam','Cll 54 # 32 - 53','Cra 182 # 45 - 31',1231231,4123123123),(2,'9178293','Exito','Cll 54 # 32 - 53','Cra 182 # 45 - 31',5019230,3109230182);
/*!40000 ALTER TABLE `empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados` (
  `id_estados` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Los estados hace referencia al acceso del sistema de información, si su estado al momento de ingresar al sistema es bloqueado, no podrá ingresar, mientras que, si su estado es activo accedera con normalidad. Esto lo decide el administrador al bloquear o no un usuario, el estado por default que tendra un cliente al registrarse es bloqueado mientras se le asigna su programa y rutina, y se avala su inscripción',
  `nombre` varchar(45) DEFAULT NULL,
  `descripccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_estados`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'Activo','Activo en el sistema'),(2,'Bloqueado','Bloqueado del sistema'),(3,'Sin Rol','Sin rol asignado en el sistema');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios`
--

DROP TABLE IF EXISTS `horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horarios` (
  `id_horario` int(11) NOT NULL AUTO_INCREMENT,
  `fk_id_curso` int(11) NOT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  PRIMARY KEY (`id_horario`),
  KEY `fk_horarios_cursos1_idx` (`fk_id_curso`),
  CONSTRAINT `fk_horarios_cursos1` FOREIGN KEY (`fk_id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios`
--

LOCK TABLES `horarios` WRITE;
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripciones`
--

DROP TABLE IF EXISTS `inscripciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inscripciones` (
  `id_inscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `fk_id_curso` int(11) NOT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_inscripcion`),
  KEY `fk_clientes_has_cursos_cursos1_idx` (`fk_id_curso`),
  KEY `fk_inscripciones_clientes1_idx` (`fk_id_usuario`),
  CONSTRAINT `fk_clientes_has_cursos_cursos1` FOREIGN KEY (`fk_id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inscripciones_clientes1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `clientes` (`fk_id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripciones`
--

LOCK TABLES `inscripciones` WRITE;
/*!40000 ALTER TABLE `inscripciones` DISABLE KEYS */;
INSERT INTO `inscripciones` VALUES (1,1,4,'Activa');
/*!40000 ALTER TABLE `inscripciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `url` text,
  `icon` varchar(45) DEFAULT NULL,
  `permisos_padre` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_permisos_permisos1_idx` (`permisos_padre`),
  CONSTRAINT `fk_permisos_permisos1` FOREIGN KEY (`permisos_padre`) REFERENCES `permisos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
INSERT INTO `permisos` VALUES (0,'inicio','/app/index.xhtml\n','fa fa-home',NULL),(1,'perfil','','perm identity',NULL),(2,'usuarios','/app/administrador/usuarios/ver.xhtml','group',NULL),(3,'aplazamientos','','fa fa-clock-o',NULL),(4,'citas','','fa fa-calendar-check-o',NULL),(5,'calendario','','fa fa-calendar',NULL),(6,'agendar','','fa fa-calendar-check-o',NULL),(7,'inscripciones','/app/administrador/inscripciones/ver.xhtml','fa fa-list-alt',NULL),(8,'empresa','/app/administrador/empresa/ver.xhtml','fa fa-columns',NULL),(9,'rutina','/app/administrador/rutinas/ver.xhtml','fa fa-user-plus',NULL),(10,'sesiones','/app/administrador/sesiones/ver.xhtml','fa fa-cog',NULL),(11,'miPerfil','/app/perfil/miperfil.xhtml','fa fa-user',1),(12,'cambiarDatos','/app/perfil/cambiardatos.xhtml','fa fa-pencil',1),(13,'programas',NULL,NULL,NULL),(21,'listarUsuarios','/app/usuarios/listar.xhtml','fa fa-list',2),(22,'nuevoUsuario','/app/usuarios/nuevo.xhtml','fa fa-user-plus',2),(30,'editarAplazamiento','/app//usuarios/editar.xhtml','fa fa-pencil-square-o',2),(31,'listaAplazamientos','/app/aplazamientos/lista.xhtml','fa fa-list',3),(32,'registrarAplazamiento','/app/aplazamientos/registrarAplazamiento.xhtml','fa fa-plus',3),(33,'verAplazamiento','/app/aplazamientos/ver.xhtml','fa fa-user',3),(34,'verAplazamiento','/app/aplazamientos/verCliente.xhtml','fa fa-user',3),(41,'misProgramas','/app/cliente/programas/programas.xhtml',NULL,13),(51,'nuevoCalendario','/app/administrador/calendario/nuevo.xhtml','fa fa-user-plus',5),(52,'listarCalendario','/app/administrador/calendario/calendario.xhtml','fa fa-calendar',5),(61,'reservar','/app/cliente/servicios/servicios.xhtml','fa fa-pencil-square-o',6),(62,'Mi calendario','/app/cliente/calendario/calendario.xhtml',NULL,5),(71,'listaInscripciones','/app/administrador/inscripciones/inscripciones.xhtml','fa fa-list-ol',7),(72,'editarIncripcion','/app/administrador/inscripciones/editar.xhtml','fa fa-pencil',7),(73,'nuevaInscripcion','/app/administrador/inscripciones/registrar.xhtml','fa fa-user-plus',7),(81,'listarEmpresas','/app/administrador/empresa/empresas.xhtml','fa fa-th-large',8),(82,'editarEmpresa','/app/administrador/empresa/editar.xhtml','fa fa-pencil',8),(83,'nuevaEmpresa','/app/administrador/empresa/registrar.xhtml','fa fa-user-plus',8),(91,'listarRutina','/app/administrador/rutinas/rutinas.xhtml','fa fa-th-large',9),(92,'editarRutina','/app/administrador/rutinas/editar.xhtml','fa fa-cog',9),(93,'nuevaRutina','/app/administrador/rutinas/nuevo.xhtml','fa fa-th-large',9),(101,'nuevaSesion','/app/administrador/sesiones/registrar.xhtml','fa fa-user-plus',10),(102,'listarSesiones','/app/administrador/sesiones/sesiones.xhtml','fa fa-th-large',10),(103,'editarSesiones','/app/administrador/sesiones/editar.xhtml','fa fa-cog',10),(111,'salones','/app/administrador/salones/ver.xhtml','fa fa-cog',NULL),(112,'registrarSalones','/app/administrador/salones/registrar.xhtml','fa fa-user',111),(113,'listarSalones','/app/administrador/salones/salones.xhtml','fa fa-user',111),(114,'editarSalones','/app/administrador/salones/editar.xhtml','fa fa-user',111),(222,'programas','/app/administrador/programas/ver.xhtml','fa fa-user',NULL),(223,'listarProgramas','/app/administrador/programas/programas.xhtml','fa fa-th-large',222),(224,'registrarProgramas','/app/administrador/programas/registrar.xhtml','fa fa-cog',222),(225,'editarProgramas','/app/administrador/programas/editar.xhtml','fa fa-user',222),(333,'personalMedico','/app/administrador/personal/ver.xhtml','fa fa-user',NULL),(334,'listarPersonal','/app/administrador/personal/medico.xhtml','fa fa-th-large',333),(335,'nuevoPersonal','/app/administrador/personal/registrar.xhtml','fa fa-user-plus',333),(444,'salonesServicios','/app/administrador/salones-servicios/editar.xhtml','fa fa-user',NULL),(445,'listarSalonesServicios','/app/administrador/salones-servicios/salones-servicios.xhtml','fa fa-th-large',444),(446,'nuevoSalonesServicios','/app/administrador/salones-servicios/nuevo.xhtml','fa fa-user',444),(555,'servicio','/app/administrador/servicios/ver.xhtml','fa fa-user',NULL),(556,'listarServicio','/app/administrador/servicios/servicios.xhtml','fa fa-th-large',555),(557,'registrarServicio','/app/administrador/servicios/registrar.xhtml','fa fa-th-large',555),(558,'editarServicio','/app/administrador/servicios/editar.xhtml','fa fa-th-large',555),(666,'cliente','/app/administrador/clientes/ver.xhtml','fa fa-th-large',NULL),(667,'listarCliente','/app/administrador/clientes/cliente.xhtml','fa fa-user',666),(668,'editarCliente','/app/administrador/clientes/editar.xhtml','fa fa-user',666),(669,'registrarCliente','/app/administrador/clientes/registrar.xhtml','fa fa-user',666),(777,'datosClinicos','','fa fa-user',NULL),(778,'registrarDatos','/app/cliente/datos/registrar.xhtml','fa fa-user',777),(779,'verDatos','/app/cliente/datos/ver.xhtml','fa fa-user',777),(780,'datosClinicos',NULL,'fa fa-user',NULL),(781,'listarDatos','/app/personal/datos/clinicos.xhtml','fa fa-user',780),(782,'verDatos','/app/personal/datos/ver.xhtml','fa fa-user',780),(800,'tienda','','perm identity',NULL), (801,'listaProductos','/app/tienda/lista.xhtml','perm identity',800);
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisosroles`
--

DROP TABLE IF EXISTS `permisosroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisosroles` (
  `fk_id_permiso` int(11) NOT NULL,
  `fk_id_rol` int(11) NOT NULL,
  PRIMARY KEY (`fk_id_permiso`,`fk_id_rol`),
  KEY `fk_permisos_has_roles_roles1_idx` (`fk_id_rol`),
  KEY `fk_permisos_has_roles_permisos1_idx` (`fk_id_permiso`),
  CONSTRAINT `fk_permisos_has_roles_permisos1` FOREIGN KEY (`fk_id_permiso`) REFERENCES `permisos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_permisos_has_roles_roles1` FOREIGN KEY (`fk_id_rol`) REFERENCES `roles` (`idRoles`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisosroles`
--

LOCK TABLES `permisosroles` WRITE;
/*!40000 ALTER TABLE `permisosroles` DISABLE KEYS */;
INSERT INTO `permisosroles` VALUES (1,1),(2,1),(3,1),(5,1),(7,1),(8,1),(10,1),(11,1),(12,1),(21,1),(22,1),(31,1),(33,1),(51,1),(52,1),(71,1),(72,1),(73,1),(81,1),(82,1),(83,1),(101,1),(102,1),(333,1),(334,1),(335,1),(666,1),(667,1),(668,1),(669,1),(1,2),(3,2),(5,2),(6,2),(11,2),(12,2),(13,2),(32,2),(34,2),(41,2),(61,2),(62,2),(777,2),(778,2),(779,2),(9,3),(91,3),(92,3),(93,3),(111,3),(112,3),(113,3),(114,3),(222,3),(223,3),(224,3),(225,3),(444,3),(445,3),(446,3),(555,3),(556,3),(557,3),(558,3),(666,3),(667,3),(668,3),(669,3),(780,3),(781,3),(782,3),(800,2),(801,2);
/*!40000 ALTER TABLE `permisosroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalmedico`
--

DROP TABLE IF EXISTS `personalmedico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalmedico` (
  `fk_id_usuario` int(11) NOT NULL,
  `perfilProfesional` varchar(45) NOT NULL COMMENT 'Este campo almacena la especialidad que tiene cada Personal Medico ',
  `cargo` varchar(20) NOT NULL COMMENT 'Este campo almacena el tipo de cargo que tiene el personal Medico (Prenatal o Posnatal)',
  PRIMARY KEY (`fk_id_usuario`),
  CONSTRAINT `fk_personalmedico_usuarios1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalmedico`
--

LOCK TABLES `personalmedico` WRITE;
/*!40000 ALTER TABLE `personalmedico` DISABLE KEYS */;
INSERT INTO `personalmedico` VALUES (3,'Pediatra','Profesional en la sa');
/*!40000 ALTER TABLE `personalmedico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `idRoles` int(11) NOT NULL COMMENT 'Este campo es la clave primaria de la tabla y  almacena el (id)',
  `nombre` varchar(20) NOT NULL COMMENT 'Este campo almacena el nombre de cada rol',
  `descripcion` text,
  PRIMARY KEY (`idRoles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Administrador','Encargado primordial del sistema'),(2,'Cliente','Usuario recurrente del sistema'),(3,'Personal Medico','Personal involucrado del gimnasio');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolesusuarios`
--

DROP TABLE IF EXISTS `rolesusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolesusuarios` (
  `fk_id_roles` int(11) NOT NULL DEFAULT '2' COMMENT 'Este campo almacena el (id) de la tabla "Roles"',
  `fk_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`fk_id_usuario`,`fk_id_roles`),
  KEY `fkRoles_RolesUsuarios_idx` (`fk_id_roles`),
  CONSTRAINT `fK_RolesUsuarios` FOREIGN KEY (`fk_id_roles`) REFERENCES `roles` (`idRoles`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_rolesusuarios_usuarios1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolesusuarios`
--

LOCK TABLES `rolesusuarios` WRITE;
/*!40000 ALTER TABLE `rolesusuarios` DISABLE KEYS */;
INSERT INTO `rolesusuarios` VALUES (1,2),(2,3);
/*!40000 ALTER TABLE `rolesusuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salones`
--

DROP TABLE IF EXISTS `salones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salones` (
  `id_salones` int(11) NOT NULL AUTO_INCREMENT COMMENT 'La tabla salones hace referencia los espacios que tiene el gimnasio para el desarrollo de cada uno de los programas\n',
  `salon` varchar(45) NOT NULL,
  `descripcion` text,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_salones`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salones`
--

LOCK TABLES `salones` WRITE;
/*!40000 ALTER TABLE `salones` DISABLE KEYS */;
INSERT INTO `salones` VALUES (1,'Salón Prenatal A','Salón acondicionado con elementos fisicos, para las actividades prenatales','Activo');
/*!40000 ALTER TABLE `salones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salones_has_servicios`
--

DROP TABLE IF EXISTS `salones_has_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salones_has_servicios` (
  `salones_id_salones` int(11) NOT NULL,
  `servicios_id_servicio` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`salones_id_salones`,`servicios_id_servicio`),
  KEY `fk_salones_has_servicios_servicios1_idx` (`servicios_id_servicio`),
  KEY `fk_salones_has_servicios_salones1_idx` (`salones_id_salones`),
  CONSTRAINT `fk_salones_has_servicios_salones1` FOREIGN KEY (`salones_id_salones`) REFERENCES `salones` (`id_salones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_salones_has_servicios_servicios1` FOREIGN KEY (`servicios_id_servicio`) REFERENCES `servicios` (`id_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salones_has_servicios`
--

LOCK TABLES `salones_has_servicios` WRITE;
/*!40000 ALTER TABLE `salones_has_servicios` DISABLE KEYS */;
INSERT INTO `salones_has_servicios` VALUES (1,1,'Vinculado'),(1,2,'Vinculado');
/*!40000 ALTER TABLE `salones_has_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicios` (
  `id_servicio` int(11) NOT NULL AUTO_INCREMENT COMMENT 'La tabla servicios hace referencia a los servicios que el gimnasio ofrece en los programas o independientemente de ellos, es decir, Spa, Pilates, Yoga, Baby Rumba, Control Nutricional, entre otros. Por lo general las rutinas contienen una serie de servicios y una rutina podrá pertenecer a muchos programas que harían un paquete completo que ofrece el gimnasio. ',
  `nombre` varchar(20) DEFAULT NULL COMMENT 'Este campo almacena el nombre de programa ',
  `descripcion` text COMMENT 'Este campo almacena una pequeña descripcion de cada programa',
  `valor` double DEFAULT NULL,
  `objetivo` text,
  `sesiones` int(11) NOT NULL,
  `fk_sub_servicios` int(11) DEFAULT NULL,
  `fk_tipo_servicio` int(11) NOT NULL,
  PRIMARY KEY (`id_servicio`),
  KEY `fk_servicios_subservicios_idx` (`fk_sub_servicios`),
  KEY `fk_servicios_tipos_servicios1_idx` (`fk_tipo_servicio`),
  CONSTRAINT `fk_servicios_subservicios` FOREIGN KEY (`fk_sub_servicios`) REFERENCES `servicios` (`id_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_servicios_tipos_servicios1` FOREIGN KEY (`fk_tipo_servicio`) REFERENCES `tipos_servicios` (`id_tipo_servicio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES (1,'Yoga','Clase de relajación orientada a las artes orientales del Yoga',100000,'Relajación',4,NULL,3),(2,'Pilates','Clase de acondicionamiento fisico',90000,'Acondicionamiento fisico',3,NULL,3);
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_servicios`
--

DROP TABLE IF EXISTS `tipos_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_servicios` (
  `id_tipo_servicio` int(11) NOT NULL AUTO_INCREMENT,
  `tipos_servicio` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_servicios`
--

LOCK TABLES `tipos_servicios` WRITE;
/*!40000 ALTER TABLE `tipos_servicios` DISABLE KEYS */;
INSERT INTO `tipos_servicios` VALUES (1,'Programa','Agrupación de servicios'),(2,'Rutina','Servicio conformado por distintas actividades'),(3,'Servicio','Actividad individual');
/*!40000 ALTER TABLE `tipos_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` varchar(20) NOT NULL COMMENT 'Este campo almacena la cedula de todos los usuarios',
  `nombres` varchar(20) NOT NULL COMMENT 'Este campo almacena el nombre de cada usuario',
  `apellidos` varchar(20) NOT NULL,
  `clave` varchar(200) NOT NULL,
  `fechaNaci` date DEFAULT NULL COMMENT 'Este campo almacena la fecha de nacimineto de cada usuario',
  `imagen_perfil` varchar(45) DEFAULT NULL,
  `correoElectronico` varchar(45) DEFAULT NULL,
  `telefonoFijo` varchar(7) DEFAULT '0000000',
  `telefonoCelular` varchar(10) DEFAULT '310000000',
  `fecha_registro` date DEFAULT NULL,
  `fk_id_estados` int(11) NOT NULL DEFAULT '3',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `cedula_UNIQUE` (`cedula`),
  KEY `fk_usuarios_estados_idx` (`fk_id_estados`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`fk_id_estados`) REFERENCES `estados` (`id_estados`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `mapris`.`productos` ( `id_productos` INT(11) NOT NULL AUTO_INCREMENT , `nombre` VARCHAR(20) NOT NULL , `descripcion` VARCHAR(90) NOT NULL , `ruta_foto` VARCHAR(45) NOT NULL , `precio_unidad` INT(11) NOT NULL , `unidades` INT(11) NOT NULL , `id_usuarios` INT(11) NULL DEFAULT NULL , PRIMARY KEY (`id_productos`)) ENGINE = InnoDB;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'1111','Lola','Martinez','fceeb9b9d469401fe558062c4bd25954','1999-01-01',NULL,'','0000000','310000000','2017-08-02',1),(3,'7777','Maria','Loaiza','fceeb9b9d469401fe558062c4bd25954','1999-08-10',NULL,'mari@gmail.com','5019230','3109230182','2017-08-03',1),(4,'9999','Sandra','Paez','fceeb9b9d469401fe558062c4bd25954','1999-01-01',NULL,'mari@gmail.com','3123123','3123123123','2017-08-09',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mapris`.`tr_usuario_before_insert`
BEFORE INSERT ON `mapris`.`usuarios`
FOR EACH ROW
SET NEW.clave =  fc_encriptar(new.clave),
new.fecha_registro = CURRENT_DATE */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mapris`.`tr_before_update_password`
BEFORE UPDATE ON `mapris`.`usuarios`
FOR EACH ROW
SET new.clave = fc_encriptar(new.clave) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'mapris'
--

--
-- Dumping routines for database 'mapris'
--
/*!50003 DROP FUNCTION IF EXISTS `fc_descencriptar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fc_descencriptar`(`clave_codificada` VARCHAR(10)) RETURNS varchar(10) CHARSET latin1
BEGIN
DECLARE var VARCHAR(10);
SET var = (SELECT decode(clave_codificada,255));
RETURN var;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fc_encriptar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fc_encriptar`(`clave` VARCHAR(35) CHARSET utf8) RETURNS varchar(35) CHARSET utf8
BEGIN
DECLARE var VARCHAR(35);
SET var = (SELECT MD5(clave));
return var;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pr_validar_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_validar_usuario`(IN `pr_cedula` VARCHAR(20), IN `pr_clave` VARCHAR(20))
    NO SQL
SELECT * FROM usuarios WHERE cedula = pr_cedula AND  clave = fc_encriptar(pr_clave) ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-10 22:27:57
