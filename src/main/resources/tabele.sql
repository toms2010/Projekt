create database if not exists aplisens;

use aplisens;

drop table if exists `kategorie`;
drop table if exists `kody`;
drop table if exists `produkty`;
drop table if exists `parametry`;
drop table if exists `produkt_parametry`;
drop table if exists `wykonania`;
drop table if exists `produkt_wykonania`;

CREATE TABLE `kategorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(5) NOT NULL,
  `nazwa` varchar(50) NOT NULL,
  `data_utworzenia` date NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `kody` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kod` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `produkty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kategoria_ID` int(11) NOT NULL,
  `nazwa` varchar(50) NOT NULL,
  `kod_ID` int(11) NOT NULL,
  `dok_opis` varchar(200) NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `data_utworzenia` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Kategoria` (`kategoria_ID`),
  KEY `Kod` (`kod_ID`),
  CONSTRAINT `Kategoria` FOREIGN KEY (`kategoria_ID`) REFERENCES `kategorie` (`id`),
  CONSTRAINT `Kod` FOREIGN KEY (`kod_ID`) REFERENCES `kody` (`id`)
);

CREATE TABLE `parametry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  `wartosc` varchar(50) NOT NULL,
  `kod` varchar(50) NOT NULL,
  `opis` varchar(50) DEFAULT NULL,
  `data_utworzenia` date NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `produkt_parametry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produktID` int(11) NOT NULL,
  `parametrID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PRODUKT` (`produktID`),
  KEY `PARAMETR` (`parametrID`),
  CONSTRAINT `PARAMETR` FOREIGN KEY (`parametrID`) REFERENCES `parametry` (`id`),
  CONSTRAINT `PRODUKT` FOREIGN KEY (`produktID`) REFERENCES `produkty` (`id`)
);

CREATE TABLE `wykonania` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `opis` varchar(50) DEFAULT NULL,
  `kod` varchar(50) NOT NULL,
  `data_utworzenia` date NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `produkt_wykonania` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produktID` int(11) NOT NULL,
  `wykonanieID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PRODUKTY` (`produktID`),
  KEY `WYKONANIE` (`wykonanieID`),
  CONSTRAINT `PRODUKTY` FOREIGN KEY (`produktID`) REFERENCES `produkty` (`id`),
  CONSTRAINT `WYKONANIE` FOREIGN KEY (`wykonanieID`) REFERENCES `wykonania` (`id`)
);