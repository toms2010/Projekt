CREATE DATABASE  IF NOT EXISTS `aplisens` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `aplisens`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: aplisens
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `kategorie`
--

DROP TABLE IF EXISTS `kategorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(5) NOT NULL,
  `nazwa` varchar(50) NOT NULL,
  `data_utworzenia` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorie`
--

LOCK TABLES `kategorie` WRITE;
/*!40000 ALTER TABLE `kategorie` DISABLE KEYS */;
INSERT INTO `kategorie` VALUES (1,'SG','Sonda głębokości','2022-03-20'),(2,'PC','Czujnik ciśnienia','2022-03-20'),(3,'PME','Wyświetlacz','2022-03-20'),(4,'CT','CZujnik temperatury','2022-03-20');
/*!40000 ALTER TABLE `kategorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kody`
--

DROP TABLE IF EXISTS `kody`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kody` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kod` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kody`
--

LOCK TABLES `kody` WRITE;
/*!40000 ALTER TABLE `kody` DISABLE KEYS */;
INSERT INTO `kody` VALUES (1,'SG'),(2,'PC');
/*!40000 ALTER TABLE `kody` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametry`
--

DROP TABLE IF EXISTS `parametry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  `wartosc` varchar(50) NOT NULL,
  `kod` varchar(50) NOT NULL,
  `opis` varchar(50) DEFAULT NULL,
  `data_utworzenia` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametry`
--

LOCK TABLES `parametry` WRITE;
/*!40000 ALTER TABLE `parametry` DISABLE KEYS */;
INSERT INTO `parametry` VALUES (1,'a','1','2','3','2001-01-20'),(2,'b','1','2','3','2001-01-20'),(3,'c','1','2','3','2001-01-20');
/*!40000 ALTER TABLE `parametry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produkt_parametry`
--

DROP TABLE IF EXISTS `produkt_parametry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produkt_parametry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produktID` int(11) NOT NULL,
  `parametrID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PRODUKT` (`produktID`),
  KEY `PARAMETR` (`parametrID`),
  CONSTRAINT `PARAMETR` FOREIGN KEY (`parametrID`) REFERENCES `parametry` (`id`),
  CONSTRAINT `PRODUKT` FOREIGN KEY (`produktID`) REFERENCES `produkty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produkt_parametry`
--

LOCK TABLES `produkt_parametry` WRITE;
/*!40000 ALTER TABLE `produkt_parametry` DISABLE KEYS */;
INSERT INTO `produkt_parametry` VALUES (9,3,1),(10,3,2),(11,4,1),(12,5,2);
/*!40000 ALTER TABLE `produkt_parametry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produkt_wykonania`
--

DROP TABLE IF EXISTS `produkt_wykonania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produkt_wykonania` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produktID` int(11) NOT NULL,
  `wykonanieID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PRODUKTY` (`produktID`),
  KEY `WYKONANIE` (`wykonanieID`),
  CONSTRAINT `PRODUKTY` FOREIGN KEY (`produktID`) REFERENCES `produkty` (`id`),
  CONSTRAINT `WYKONANIE` FOREIGN KEY (`wykonanieID`) REFERENCES `wykonania` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produkt_wykonania`
--

LOCK TABLES `produkt_wykonania` WRITE;
/*!40000 ALTER TABLE `produkt_wykonania` DISABLE KEYS */;
INSERT INTO `produkt_wykonania` VALUES (1,3,1),(2,3,2),(3,5,1),(4,4,2);
/*!40000 ALTER TABLE `produkt_wykonania` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produkty`
--

DROP TABLE IF EXISTS `produkty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produkty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kategoria_ID` int(11) NOT NULL,
  `nazwa` varchar(50) NOT NULL,
  `dok_opis` varchar(200) NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `data_utworzenia` date NOT NULL,
  `kod` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Kategoria` (`kategoria_ID`),
  CONSTRAINT `Kategoria` FOREIGN KEY (`kategoria_ID`) REFERENCES `kategorie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produkty`
--

LOCK TABLES `produkty` WRITE;
/*!40000 ALTER TABLE `produkty` DISABLE KEYS */;
INSERT INTO `produkty` VALUES (3,1,'Sonda','ddddd',1111.00,'2001-01-20','SG-25'),(4,1,'Sonda2','aaa',1111.00,'2001-01-20','SG-16'),(5,2,'przetwornik','werfsef',2222.00,'2001-01-20','PC-28');
/*!40000 ALTER TABLE `produkty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wykonania`
--

DROP TABLE IF EXISTS `wykonania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wykonania` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `opis` varchar(50) DEFAULT NULL,
  `kod` varchar(50) NOT NULL,
  `data_utworzenia` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wykonania`
--

LOCK TABLES `wykonania` WRITE;
/*!40000 ALTER TABLE `wykonania` DISABLE KEYS */;
INSERT INTO `wykonania` VALUES (1,'aaaa',213.00,'a','ss','2001-01-20'),(2,'adfaefd',323.00,'awdaw','aa','2001-01-20');
/*!40000 ALTER TABLE `wykonania` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-28 20:17:51
