-- DROP TABLE IF EXISTS `tb_clima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_clima` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `viavel_para_vida` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK__psw__tb_clima__nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


-- DROP TABLE IF EXISTS `tb_terreno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_terreno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `trafegavel` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK__psw__tb_terreno__nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


-- DROP TABLE IF EXISTS `tb_planeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_planeta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK__psw__tb_planeta__nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


-- DROP TABLE IF EXISTS `tb_regiao`;
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
  KEY `FK__tb_regiao__clima_id__tb_clima_id` (`clima_id`),
  KEY `FK__tb_regiao__planeta_id__tb_planeta_id` (`planeta_id`),
  KEY `FK__tb_regiao__terreno_id__tb_regiao_id` (`terreno_id`),
  CONSTRAINT `FK__tb_regiao__planeta_id__tb_planeta_id` FOREIGN KEY (`planeta_id`) REFERENCES `tb_planeta` (`id`),
  CONSTRAINT `FK__tb_regiao__clima_id__tb_clima_id` FOREIGN KEY (`clima_id`) REFERENCES `tb_clima` (`id`),
  CONSTRAINT `FK__tb_regiao__terreno_id__tb_regiao_id` FOREIGN KEY (`terreno_id`) REFERENCES `tb_terreno` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
