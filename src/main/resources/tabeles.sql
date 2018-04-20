CREATE DATABASE  IF NOT EXISTS `aplisens`;
USE `aplisens`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `products_housing`;
DROP TABLE IF EXISTS `products_parameters`;
DROP TABLE IF EXISTS `products_designs`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `parameters`;
DROP TABLE IF EXISTS `designs`;
DROP TABLE IF EXISTS `housing`;
DROP TABLE IF EXISTS `categories`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  `create_date` date NOT NULL,
  `modify_date` date ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_ID` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255),
  `price` decimal(10,2) NOT NULL,
  `create_date` date NOT NULL,
  `modify_date` date,
  PRIMARY KEY (`id`),
  KEY `category` (`category_ID`),
  CONSTRAINT `category` FOREIGN KEY (`category_ID`) REFERENCES `categories` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `parameters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `param_value` varchar(50) NOT NULL,
  `code` varchar(10),
  `description` varchar(255),
  `create_date` date NOT NULL,
  `modify_date` date,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `products_parameters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_ID` int(11) NOT NULL,
  `parameter_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_param` (`product_ID`),
  KEY `parameter` (`parameter_ID`),
  CONSTRAINT `parameter` FOREIGN KEY (`parameter_ID`) REFERENCES `parameters` (`id`),
  CONSTRAINT `product_param` FOREIGN KEY (`product_ID`) REFERENCES `products` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `designs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `description` varchar(255),
  `code` varchar(10),
  `create_date` date NOT NULL,
  `modify_date` date,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `products_designs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_ID` int(11) NOT NULL,
  `design_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_design` (`product_ID`),
  KEY `design` (`design_ID`),
  CONSTRAINT `design` FOREIGN KEY (`design_ID`) REFERENCES `designs` (`id`),
  CONSTRAINT `product_design` FOREIGN KEY (`product_ID`) REFERENCES `products` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `housing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `code` varchar(10),
  `description` varchar(255),
  `create_date` date NOT NULL,
  `modify_date` date,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `products_housing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_ID` int(11) NOT NULL,
  `housing_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_housing` (`product_ID`),
  KEY `housing` (`housing_ID`),
  CONSTRAINT `product_housing` FOREIGN KEY (`product_ID`) REFERENCES `products` (`id`),
  CONSTRAINT `housing` FOREIGN KEY (`housing_ID`) REFERENCES `housing` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `description` varchar(255),
  `create_date` date NOT NULL,
  `modify_date` date,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;