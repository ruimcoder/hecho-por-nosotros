-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: us-cdbr-east-04.cleardb.com    Database: heroku_7c58c21ece5258d
-- ------------------------------------------------------
-- Server version	5.6.50-log

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
-- Table structure for table `contactos`
--

DROP TABLE IF EXISTS `contactos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facebook` varchar(255) DEFAULT NULL,
  `instagram` varchar(255) DEFAULT NULL,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `emprendimiento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc3xegfs7m0krrxevvgo00w6j4` (`emprendimiento_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactos`
--

LOCK TABLES `contactos` WRITE;
/*!40000 ALTER TABLE `contactos` DISABLE KEYS */;
INSERT INTO `contactos` VALUES (5,'Maipu 151 sur','maico12@hotmail.com','cipriano.com','cipriano',-31.5370909,-68.5251802,'Argentina','26465123856',5),(15,'Avenida Siempre Viva 221','grooveok@gmail.com','www.groove.com','www.groove.com',-31.4173391,-64.183319,'Argentina','26645789',35);
/*!40000 ALTER TABLE `contactos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprendimiento_file`
--

DROP TABLE IF EXISTS `emprendimiento_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprendimiento_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `emprendimiento_id` bigint(20) DEFAULT NULL,
  `file_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK13nfyrr8hhljue3l6yjoidbia` (`emprendimiento_id`),
  KEY `FK10tsuwq8k7mregkyagik34okp` (`file_id`)
) ENGINE=MyISAM AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprendimiento_file`
--

LOCK TABLES `emprendimiento_file` WRITE;
/*!40000 ALTER TABLE `emprendimiento_file` DISABLE KEYS */;
INSERT INTO `emprendimiento_file` VALUES (5,'logo',5,5),(15,'portada',5,15),(25,'certificado',5,55),(35,'certificado',5,65),(85,'logo',35,125),(95,'portada',35,135),(105,'certificado',35,155),(165,'portada',65,245),(155,'logo',65,235);
/*!40000 ALTER TABLE `emprendimiento_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprendimiento_file_entity`
--

DROP TABLE IF EXISTS `emprendimiento_file_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprendimiento_file_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `emprendimiento_id` bigint(20) DEFAULT NULL,
  `file_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1rho8ia21tkd5jdns14y7xc24` (`emprendimiento_id`),
  KEY `FKd73g44bhpyy9wuu0hjenjse7k` (`file_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprendimiento_file_entity`
--

LOCK TABLES `emprendimiento_file_entity` WRITE;
/*!40000 ALTER TABLE `emprendimiento_file_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `emprendimiento_file_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprendimiento_meta`
--

DROP TABLE IF EXISTS `emprendimiento_meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprendimiento_meta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emprendimiento_id` bigint(20) DEFAULT NULL,
  `meta_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt9cg3pcon44vt0hn23kg8dx4u` (`emprendimiento_id`),
  KEY `FKilgu41gohjlqrca7o2jrbeidy` (`meta_id`)
) ENGINE=MyISAM AUTO_INCREMENT=856 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprendimiento_meta`
--

LOCK TABLES `emprendimiento_meta` WRITE;
/*!40000 ALTER TABLE `emprendimiento_meta` DISABLE KEYS */;
INSERT INTO `emprendimiento_meta` VALUES (835,5,165),(825,5,155),(815,5,145),(745,35,165),(735,35,115),(725,35,105),(715,35,85),(705,35,75),(695,35,65),(805,5,135),(795,5,125),(785,5,65),(775,5,5),(855,65,165),(845,65,155);
/*!40000 ALTER TABLE `emprendimiento_meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprendimientos`
--

DROP TABLE IF EXISTS `emprendimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprendimientos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `africa` bit(1) NOT NULL,
  `antartida` bit(1) NOT NULL,
  `asia` bit(1) NOT NULL,
  `comentario_meta` varchar(255) DEFAULT NULL,
  `count_colaboracion` bigint(20) DEFAULT NULL,
  `count_empleados` bigint(20) DEFAULT NULL,
  `count_jovenes` bigint(20) DEFAULT NULL,
  `count_jovenes_colaboracion` bigint(20) DEFAULT NULL,
  `count_mujeres` bigint(20) DEFAULT NULL,
  `count_mujeres_colaboracion` bigint(20) DEFAULT NULL,
  `deleted_at` bit(1) DEFAULT NULL,
  `europa` bit(1) NOT NULL,
  `exporta` bit(1) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `norteamerica` bit(1) NOT NULL,
  `oceania` bit(1) NOT NULL,
  `pertenece` varchar(255) DEFAULT NULL,
  `publicado` bit(1) DEFAULT NULL,
  `registro` bigint(20) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `sudamerica` bit(1) NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `contacto_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk92dlupori2pjt4vow69ct8ou` (`contacto_id`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprendimientos`
--

LOCK TABLES `emprendimientos` WRITE;
/*!40000 ALTER TABLE `emprendimientos` DISABLE KEYS */;
INSERT INTO `emprendimientos` VALUES (5,'\0','\0','\0','mi meta es simple maico',3,5,6,5,12,25,'\0','\0','\0','Femenino','Lomoteca Ciprianos','\0','\0',NULL,'',51212,'Sabor inigualable','\0','Micro o mediana empresa',5),(15,'\0','\0','\0','',8,7,7,8,5,8,'','\0','\0','Femenino','probandito','\0','\0',NULL,'',12321,'asdsad','\0','Micro o mediana empresa',NULL),(25,'\0','\0','\0','hola',4,6,34,23,5,43,'','\0','\0','Masculino','prueba 3','\0','\0','Asociación','\0',132512,'Sabores Artesanales!','\0','Productor de agricultura o ganaderia',NULL),(35,'\0','\0','\0','sss',90,90,24,36,24,54,'\0','\0','\0','Femenino','Groove','\0','\0',NULL,'',43422,'El estilo no tiene reglas, las creas tú','\0','Micro o mediana empresa',15),(45,'\0','\0','\0','hola',22,3223,22,22,22,22,'','\0','\0','Masculino','ess','\0','\0','Organización','\0',3232,'sadsad','\0','Artesano',NULL),(55,'\0','\0','\0','hola',3,33,3,3,3,3,'','\0','\0','Masculino','dfgdfgdf','\0','\0','Fundación','\0',4545,'dfgdfgdf','\0','Productor de agricultura o ganaderia',NULL),(65,'\0','\0','\0','Chau',5,5,5,5,5,5,'\0','\0','\0','Femenino','mi emprendimiento','\0','\0','Fundación','\0',12312,'Sabores Artesanales!','\0','Artesano',NULL);
/*!40000 ALTER TABLE `emprendimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_informacion`
--

DROP TABLE IF EXISTS `file_informacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_informacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `file_id` bigint(20) DEFAULT NULL,
  `informacion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK54g1cms1akojed2uadb2y478r` (`file_id`),
  KEY `FKiw2hofjujxu819l8xip18frc4` (`informacion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_informacion`
--

LOCK TABLES `file_informacion` WRITE;
/*!40000 ALTER TABLE `file_informacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_informacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=246 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (5,'1641248834390_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641248834390_file.jpeg'),(15,'1641248835105_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641248835105_file.jpeg'),(25,'1641249302276_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641249302276_file.jpeg'),(35,'1641249302505_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641249302505_file.jpeg'),(45,'1641249302587_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641249302587_file.jpeg'),(55,'1641249348906_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641249348906_file.jpeg'),(65,'1641249349076_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641249349076_file.jpeg'),(75,'1641249402804_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641249402804_file.jpeg'),(85,'1641252037857_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641252037857_file.jpeg'),(95,'1641252038057_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641252038057_file.jpeg'),(105,'1641252268393_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641252268393_file.jpeg'),(115,'1641252268579_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641252268579_file.jpeg'),(125,'1641323908823_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641323908823_file.jpeg'),(135,'1641323909518_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641323909518_file.jpeg'),(145,'1641324829220_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641324829220_file.jpeg'),(155,'1641324852639_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641324852639_file.jpeg'),(165,'1641324885722_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641324885722_file.jpeg'),(175,'1641325307769_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641325307769_file.jpeg'),(185,'1641325393087_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641325393087_file.jpeg'),(195,'1641328574962_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641328574962_file.jpeg'),(205,'1641328575252_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641328575252_file.jpeg'),(215,'1641333202669_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641333202669_file.jpeg'),(225,'1641333203188_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641333203188_file.jpeg'),(235,'1641338687272_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641338687272_file.jpeg'),(245,'1641338687553_file.jpeg','https://storehechoxnosotros.s3.us-east-2.amazonaws.com/1641338687553_file.jpeg');
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion`
--

DROP TABLE IF EXISTS `informacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `texto` varchar(2500) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `url_video` varchar(255) DEFAULT NULL,
  `emprendimiento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6keerxwek2lbt9s61roedtprs` (`emprendimiento_id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion`
--

LOCK TABLES `informacion` WRITE;
/*!40000 ALTER TABLE `informacion` DISABLE KEYS */;
INSERT INTO `informacion` VALUES (5,'En el 2010 los hermanos Morfil decidieron cambiarle el pan al típico lomo. Así nació la pachata en un local llamado Megastore, ubicado en Capital. A los dos años decidieron independizarse y abrieron el local Puesto 32. \"Creamos un sánguche en un pan baguette, bien hecho de principio a final. ¿Qué pasaba? Vos antes te comías un lomito y tenías que ir mordisquéandole todas las orillas para llegar al centro, donde se juntaba la lechuga, el tomate, todo. Nosotros empezamos a hacer un sánguche bien hecho, que se pueda comer de punta a punta y más prolijo\", ','historia','www.cipriano.com',5),(15,'¿Cuál es el secreto? \"Siempre el mismo corte de carne, que es el lomo redondo. Lo cortamos nosotros con la máquina de fiambre. Este producto no se puede estandarizar como la hamburguesa. Si te toca una mala carne, no la podés vender. Hay que estar todo el tiempo encima\", indicó Santiago. Además, afirmó que \"la onda es encontrar un equilibrio, que tenga bastante carne, que no esté flaco pero tampoco que tenga una bestialidad\",','tecnica','https://www.youtube.com/watch?v=a8yhZzXTb2c',5),(25,'Aliquam posuere, mauris ut fringilla blandit, magna ex cursus ipsum, sit amet dictum lorem metus sed sem. Integer enim leo, semper non fermentum malesuada, auctor ut metus. Nam tellus ante, mollis vel justo in, viverra consectetur nunc. Proin mollis tortor eros, in vehicula magna cursus eget. Duis sed malesuada metus. In dignissim ex eros, quis imperdiet sapien luctus ac. Nulla volutpat massa euismod, tincidunt ipsum nec, feugiat odio. Donec ac quam facilisis, rutrum sapien non, pellentesque lorem.\n\nNam non elementum tortor. Curabitur luctus porttitor purus, non commodo elit consequat nec. Suspendisse potenti. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed quis nibh eget tellus porttitor finibus in a risus. Integer semper, magna sit amet euismod sagittis, felis augue finibus arcu, id sodales ipsum neque vitae est. In hac habitasse platea dictumst. Suspendisse lobortis, ante ut pretium consectetur, tortor tellus aliquet odio, congue varius ligula erat eget sapien. Sed faucibus ultrices auctor. Donec at augue finibus, placerat enim sit amet, luctus eros. Morbi et elementum sapien. Phasellus id lorem at ex iaculis sollicitudin vel at purus.\n','historia','www.youtube.com',35),(35,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed venenatis ante tempor iaculis eleifend. Nulla ut tempor erat. Etiam nisi turpis, consectetur a diam sed, euismod mollis massa. Sed eleifend egestas tincidunt. In id est placerat diam scelerisque gravida. Curabitur vel elementum justo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas massa massa, pulvinar nec tortor vel, dictum sodales magna. Mauris nec ipsum eget tortor placerat tincidunt. Vivamus in neque ut dui vehicula tristique id iaculis quam. In lacinia nibh ut aliquet ullamcorper. Sed volutpat pulvinar est id mollis. In cursus, purus sit amet pretium ornare, mauris leo tempus velit, sed tincidunt orci nisi eu risus. Nullam pulvinar luctus neque quis dictum.\n\nFusce in urna viverra, vehicula magna ac, efficitur mi. Fusce dictum arcu est, ac hendrerit metus congue a. Sed purus erat, egestas id sagittis sed, efficitur nec magna. Maecenas tempus tincidunt ligula non vulputate. Sed mattis, erat non varius tristique, enim erat pharetra arcu, et sodales lectus quam et orci. Etiam id felis dui. Phasellus eget venenatis arcu. Proin nec pellentesque turpis, et maximus lectus. Nulla in nulla nec sapien elementum pharetra. Sed diam nisl, ultrices nec fermentum et, dapibus vitae lorem. Nunc ac sem gravida lacus volutpat pulvinar ut eget mauris. Fusce tristique, risus nec varius rutrum, est arcu blandit quam, quis iaculis metus magna vel purus. Proin vestibulum elit augue, in congue erat consectetur in.','tecnica','www.youtube.com',35);
/*!40000 ALTER TABLE `informacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion_file`
--

DROP TABLE IF EXISTS `informacion_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informacion_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `file_id` bigint(20) DEFAULT NULL,
  `informacion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5sugy2o2hcv4r84ogia9dtu4j` (`file_id`),
  KEY `FK91ck3a772o6woioih617qq01v` (`informacion_id`)
) ENGINE=MyISAM AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion_file`
--

LOCK TABLES `informacion_file` WRITE;
/*!40000 ALTER TABLE `informacion_file` DISABLE KEYS */;
INSERT INTO `informacion_file` VALUES (5,'foto',25,5),(15,'foto',35,5),(25,'foto',45,5),(35,'foto',75,15),(45,'foto',145,25),(55,'foto',165,35),(65,'foto',175,25),(75,'foto',185,35);
/*!40000 ALTER TABLE `informacion_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_proveedor`
--

DROP TABLE IF EXISTS `material_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_proveedor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `especificar` varchar(255) DEFAULT NULL,
  `id_material` bigint(20) DEFAULT NULL,
  `id_proveedor` bigint(20) DEFAULT NULL,
  `registered_at` datetime DEFAULT NULL,
  `materiales` bigint(20) DEFAULT NULL,
  `proveedor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdmd2gejfbm2r6h03g2bennx67` (`materiales`),
  KEY `FK9qvk54lqmpkccdloemixo78fa` (`proveedor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_proveedor`
--

LOCK TABLES `material_proveedor` WRITE;
/*!40000 ALTER TABLE `material_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiales`
--

DROP TABLE IF EXISTS `materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materiales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales`
--

LOCK TABLES `materiales` WRITE;
/*!40000 ALTER TABLE `materiales` DISABLE KEYS */;
INSERT INTO `materiales` VALUES (5,'Algodón'),(15,'Alpaca'),(25,'Botones'),(35,'Cremalleras'),(45,'Hilo'),(55,'Lana'),(65,'Madera'),(75,'Metal'),(85,'Seda'),(95,'Vicuña'),(105,'Tela'),(115,'Otros');
/*!40000 ALTER TABLE `materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metas`
--

DROP TABLE IF EXISTS `metas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metas`
--

LOCK TABLES `metas` WRITE;
/*!40000 ALTER TABLE `metas` DISABLE KEYS */;
INSERT INTO `metas` VALUES (5,'Fin de la pobreza'),(15,'Hambre cero'),(25,'Salud y bienestar'),(35,'Educación de calidad'),(45,'Igualdad de genero'),(55,'Agua limpia y saneamiento'),(65,'Energia asquible y no contaminante'),(75,'Trabajo decente y crecimiento economico'),(85,'Industria, innovación e infraestructura'),(95,'Reducción de las desigualdades'),(105,'Ciudades y comunidades sostenibles'),(115,'Producción y consumo responsable'),(125,'Acción por el clima'),(135,'Vida submarina'),(145,'Vida de ecosistemas terrestres'),(155,'Paz, justicia e instituciones solidas'),(165,'Alianzas para lograr los objetivos');
/*!40000 ALTER TABLE `metas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(255) DEFAULT NULL,
  `cp` bigint(20) DEFAULT NULL,
  `deleted_at` bit(1) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `emprendimiento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ovuodbu53wkn2jvxeua5s7ag` (`emprendimiento_id`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (5,'capital',5400,'\0','Salta 432 sur','Carniceria Unions','argentina',5),(15,'capital',54004,'\0','Heras 15 norte','Verduras HyG','argentina',5),(25,'San Luis',6654,'\0','Mendoza Sur','Taller de Costura Frías','Argentina',35),(35,'La Rioja',1239,'\0','Miramar Oeste','Roberto','Arentina',35),(45,'sdfsdfsd',757,'','sdfsdfsd','dsfsdfsdf','sdfsdf',55),(55,'barcelona',5400,'\0','madrid 15','Proveedor de maico 13','argentina',65);
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores_materiales`
--

DROP TABLE IF EXISTS `proveedores_materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedores_materiales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `especificar` varchar(255) DEFAULT NULL,
  `materiales_id` bigint(20) DEFAULT NULL,
  `proveedores_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4m00gv9w75l6j6yvo4u3psxo3` (`materiales_id`),
  KEY `FKhtum4ld82u1r24857vacj0g1u` (`proveedores_id`)
) ENGINE=MyISAM AUTO_INCREMENT=356 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores_materiales`
--

LOCK TABLES `proveedores_materiales` WRITE;
/*!40000 ALTER TABLE `proveedores_materiales` DISABLE KEYS */;
INSERT INTO `proveedores_materiales` VALUES (275,NULL,85,35),(265,NULL,25,35),(255,NULL,5,35),(165,'verduras',115,15),(155,NULL,55,15),(145,NULL,35,15),(245,NULL,85,25),(235,NULL,55,25),(225,NULL,45,25),(215,NULL,25,25),(335,NULL,25,55),(345,NULL,35,55),(355,'plastico',115,55);
/*!40000 ALTER TABLE `proveedores_materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puntos`
--

DROP TABLE IF EXISTS `puntos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `puntos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(255) DEFAULT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  `cp` bigint(20) DEFAULT NULL,
  `deleted_at` bit(1) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `direccion_secundaria` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `sitio_web` varchar(255) DEFAULT NULL,
  `emprendimiento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKesiqfieiuoldkpqvcgrffp19b` (`emprendimiento_id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puntos`
--

LOCK TABLES `puntos` WRITE;
/*!40000 ALTER TABLE `puntos` DISABLE KEYS */;
INSERT INTO `puntos` VALUES (5,'capital','maico.com',5400,'\0',NULL,'Maradona 499 Norte','Cipriano Verano','argentina','Tienda','maico.com',5),(15,'Santa Lucia','2645589782',5400,'\0',NULL,'25 de mayo','Verduleria Frutales','argentina','Plaza','maico.com',5),(25,'San Juan','www.grooveok@gmail.com',899,'\0',NULL,'Calvento Norte','Groove Verano','Argentina','Tienda','www.groove.com',35),(35,'La Rioja','26455789',788,'\0',NULL,'Boulevard Sur','Quicko','Argentina','Casa','www.quicko.com',35);
/*!40000 ALTER TABLE `puntos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_emprendimiento`
--

DROP TABLE IF EXISTS `user_emprendimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_emprendimiento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted_at` bit(1) DEFAULT NULL,
  `uid_firebase` varchar(255) DEFAULT NULL,
  `emprendimientos` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jcujvsgj0ssm07c58pvw8tew` (`emprendimientos`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_emprendimiento`
--

LOCK TABLES `user_emprendimiento` WRITE;
/*!40000 ALTER TABLE `user_emprendimiento` DISABLE KEYS */;
INSERT INTO `user_emprendimiento` VALUES (5,'\0','AsPF7f1QPxgmFk1pTQWAQ7vlrGl1',5),(15,'','AsPF7f1QPxgmFk1pTQWAQ7vlrGl1',15),(25,'','AsPF7f1QPxgmFk1pTQWAQ7vlrGl1',25),(35,'\0','AsPF7f1QPxgmFk1pTQWAQ7vlrGl1',35),(45,'','AsPF7f1QPxgmFk1pTQWAQ7vlrGl1',45),(55,'','HHzKXBIyJrVqYPaeS520tBXO4Yw2',55),(65,'\0','HHzKXBIyJrVqYPaeS520tBXO4Yw2',65);
/*!40000 ALTER TABLE `user_emprendimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'heroku_7c58c21ece5258d'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-07 10:24:35
