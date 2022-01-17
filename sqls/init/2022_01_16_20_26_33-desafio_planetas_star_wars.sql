-- MySQL dump 10.19  Distrib 10.3.29-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: desafio_planetas_star_wars
-- ------------------------------------------------------
-- Server version	10.3.29-MariaDB-0ubuntu0.20.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_clima`
--

DROP TABLE IF EXISTS `tb_clima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_clima` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `viavel_para_vida` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m7kx91xxaxpv4o11thk2xtyra` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_clima`
--

LOCK TABLES `tb_clima` WRITE;
/*!40000 ALTER TABLE `tb_clima` DISABLE KEYS */;
INSERT INTO `tb_clima` (`id`, `ativo`, `nome`, `viavel_para_vida`) VALUES (2,'','Quente',''),(8,'','Muito Calor (Mais que 60oC)','\0'),(26,'','Ameno - Tropical',''),(27,'','Quente Tropical 34',''),(28,'','Tropical - Com chuvas fortes - Monções',''),(37,'','Exremamente Frio','');
/*!40000 ALTER TABLE `tb_clima` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_planeta`
--

DROP TABLE IF EXISTS `tb_planeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_planeta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lrvjqlsbkefirkf624p7e7q8o` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_planeta`
--

LOCK TABLES `tb_planeta` WRITE;
/*!40000 ALTER TABLE `tb_planeta` DISABLE KEYS */;
INSERT INTO `tb_planeta` (`id`, `ativo`, `nome`) VALUES (1,'','Mercúrio'),(6,'','Terra'),(17,'','Venus'),(19,'','Marte'),(20,'','Júpiter'),(21,'','Saturno'),(22,'','Urano'),(23,'','Netuno'),(24,'','Plutão');
/*!40000 ALTER TABLE `tb_planeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_regiao`
--

DROP TABLE IF EXISTS `tb_regiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_regiao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `clima_id` bigint(20) DEFAULT NULL,
  `planeta_id` bigint(20) DEFAULT NULL,
  `terreno_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi3oxyxsdf34wwetpgvhqug8fe` (`clima_id`),
  KEY `FK5h0iyimn9t5p8n5wrr2mr454l` (`planeta_id`),
  KEY `FKqbel9j64iodo1l4y4fgffy4q6` (`terreno_id`),
  CONSTRAINT `FK5h0iyimn9t5p8n5wrr2mr454l` FOREIGN KEY (`planeta_id`) REFERENCES `tb_planeta` (`id`),
  CONSTRAINT `FKi3oxyxsdf34wwetpgvhqug8fe` FOREIGN KEY (`clima_id`) REFERENCES `tb_clima` (`id`),
  CONSTRAINT `FKqbel9j64iodo1l4y4fgffy4q6` FOREIGN KEY (`terreno_id`) REFERENCES `tb_terreno` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_regiao`
--

LOCK TABLES `tb_regiao` WRITE;
/*!40000 ALTER TABLE `tb_regiao` DISABLE KEYS */;
INSERT INTO `tb_regiao` (`id`, `ativo`, `nome`, `clima_id`, `planeta_id`, `terreno_id`) VALUES (1,'','Extremo Sul',26,1,9),(2,'','Extremo Norte',2,1,3),(6,'','America do Sul',26,6,12),(7,'','Africa',27,6,13),(8,'','Oriente',28,6,14),(19,'','Extremo Norte',8,17,5),(20,'','Extremo Norte',8,19,5),(21,'','Extremo Norte',8,20,5),(22,'','Extremo Norte',8,21,5),(23,'','Extremo Norte',8,22,5),(24,'','Extremo Norte',8,23,5),(25,'','Extremo Norte',8,24,5),(28,'','Antartida',37,6,12);
/*!40000 ALTER TABLE `tb_regiao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_terreno`
--

DROP TABLE IF EXISTS `tb_terreno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_terreno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `trafegavel` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_potdgav1wmyf3n9exhf7mxd7l` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_terreno`
--

LOCK TABLES `tb_terreno` WRITE;
/*!40000 ALTER TABLE `tb_terreno` DISABLE KEYS */;
INSERT INTO `tb_terreno` (`id`, `ativo`, `nome`, `trafegavel`) VALUES (3,'','Arenoso com dunas',''),(5,'','Argila batida - plano',''),(9,'','Barranco Largo',''),(12,'','Planalto',''),(13,'','Savanas',''),(14,'','Montanhoso','');
/*!40000 ALTER TABLE `tb_terreno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `vw_planetas`
--

DROP TABLE IF EXISTS `vw_planetas`;
/*!50001 DROP VIEW IF EXISTS `vw_planetas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_planetas` (
  `id` tinyint NOT NULL,
  `nome` tinyint NOT NULL,
  `nomeRegiao` tinyint NOT NULL,
  `nomeClima` tinyint NOT NULL,
  `viavel_para_vida` tinyint NOT NULL,
  `nomeTerreno` tinyint NOT NULL,
  `trafegavel` tinyint NOT NULL,
  `planeta_ativo` tinyint NOT NULL,
  `regiao_ativo` tinyint NOT NULL,
  `clima_ativo` tinyint NOT NULL,
  `terreno_ativo` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vw_planetas`
--

/*!50001 DROP TABLE IF EXISTS `vw_planetas`*/;
/*!50001 DROP VIEW IF EXISTS `vw_planetas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`curso_udemy`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_planetas` AS select `p`.`id` AS `id`,`p`.`nome` AS `nome`,`r`.`nome` AS `nomeRegiao`,`c`.`nome` AS `nomeClima`,`c`.`viavel_para_vida` AS `viavel_para_vida`,`t`.`nome` AS `nomeTerreno`,`t`.`trafegavel` AS `trafegavel`,`p`.`ativo` AS `planeta_ativo`,`r`.`ativo` AS `regiao_ativo`,`c`.`ativo` AS `clima_ativo`,`t`.`ativo` AS `terreno_ativo` from (((`tb_planeta` `p` left join `tb_regiao` `r` on(`p`.`id` = `r`.`planeta_id`)) left join `tb_clima` `c` on(`c`.`id` = `r`.`clima_id`)) left join `tb_terreno` `t` on(`t`.`id` = `r`.`terreno_id`)) order by `p`.`nome` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-16 20:26:33
