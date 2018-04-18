USE `aplisens`;

SET SQL_SAFE_UPDATES=0;
DELETE FROM `aplisens`.`authorities`; 
DELETE FROM `aplisens`.`users`;
DELETE FROM `aplisens`.`products_housing`; 
DELETE FROM `aplisens`.`products_parameters`;
DELETE FROM `aplisens`.`products_designs`; 
DELETE FROM `aplisens`.`parameters`; 
DELETE FROM `aplisens`.`designs`; 
DELETE FROM `aplisens`.`housing`;  
DELETE FROM `aplisens`.`products`; 
DELETE FROM `aplisens`.`categories`;
SET SQL_SAFE_UPDATES=1;

INSERT INTO `aplisens`.`users` (`username`, `password`, `enabled`)
VALUES ('admin', '{noop}admin', 1), ('user', '{noop}user', 1);

INSERT INTO `aplisens`.`authorities` (`username`, `authority`)
VALUES ('admin', 'ROLE_ADMIN'), ('admin', 'ROLE_USER'), ('user', 'ROLE_USER');

INSERT INTO `aplisens`.`categories` (`id`, `code`, `name`, `create_date`)
VALUES (1, 'SG', 'Sonda głębokości', '2018-03-20'), (2, 'PC','Czujnik ciśnienia','2018-03-20'), (3, 'PME','Wyświetlacz','2018-03-20'),(4, 'CT','Czujnik temperatury','2018-03-20');

INSERT INTO `aplisens`.`products` (`id`, `category_ID`, `code`, `name`, `description`, `price`, `create_date`)
VALUES (1, 1, 'SG-25', 'Hydrostatyczna sonda głębokości', 'Hydrostatyczna sonda głębokości SG-25 przeznaczona jest do pomiaru poziomów cieczy w zbiornikach, studniach głębinowych itd.', 1130.00,  '2018-03-20'), 
       (2, 1, 'SG-16', 'Hydrostatyczna sonda głębokości', 'Sonda SG-16 jest konstrukcją specjalizowaną, przeznaczoną do pomiaru poziomu wody w zbiornikach o małej średnicy', 1450.00,  '2018-03-20'), 
       (3, 2, 'APC-2000ALW', 'Inteligentny przetwornik ciśnienia', 'Przetwornik ciśnienia przeznaczony jest do pomiaru ciśnenia, podciśnienia oraz ciśnienia obsolutnego gazów, cieczy i par.', 2250.00,  '2018-03-20'), 
       (4, 2, 'PC-28','Przetwornik ciśnienia', 'Przetwornik ciśnienia przeznaczony jest do pomiaru ciśnenia, podciśnienia oraz ciśnienia obsolutnego gazów, cieczy i par.', 760.00,'2018-03-20');

INSERT INTO `aplisens`.`parameters` (`id`, `name`, `param_value`, `description`, `create_date`)
VALUES (1, 'Sygnał wyjściowy', '4..20mA', 'Opis sygnału',  '2018-03-20'), 
       (2, 'Sygnał wyjściowy', '4..20mA + HART', 'Opis sygnału z Hartem', '2018-03-20'), 
       (3, 'Materiał', 'stal 316L', 'Opis stali', '2018-03-20'), 
       (4, 'Zasilanie', '10..55V DC', 'Opis zasilania', '2018-03-20'), 
       (5, 'Materiał membrany','Hasteloy','' ,'2018-03-20');

INSERT INTO `aplisens`.`products_parameters` (`id`, `product_ID`, `parameter_ID`)
VALUES (1, 1, 1), (2, 1, 3), (3, 1, 4),(4, 1, 5),
       (5, 2, 1), (6, 2, 3), (7, 2, 4), (8, 2, 5),
       (9, 3, 2), (10, 3, 3), (11, 3, 4),
       (12, 4, 1), (13, 4, 4);

INSERT INTO `aplisens`.`designs` (`id`, `name`, `price`, `description`, `create_date`)
VALUES (1, 'Ex', 220, 'Wykonanie iskrobezpieczne',  '2018-03-20'), 
       (2, 'Teflon', 100, 'Kabel pokryty teflonem', '2018-03-20'), 
       (3, 'MR', 500, 'Wykonanie do zastosowań morskich', '2018-03-20'), 
       (4, 'PED', 200, 'Wykonanie z dyrektywą PED', '2018-03-20'), 
       (5, 'IP67', 150, 'Obudowa wodoszczelna' ,'2018-03-20'),  
       (6, 'HS', 700, 'Ultrastabilny zakres pomiarowy' ,'2018-03-20'); 
       
INSERT INTO `aplisens`.`products_designs` (`id`, `product_ID`, `design_ID`)
VALUES (1, 1, 1), (2, 1, 2), (3, 1, 3),(4, 1, 4),
       (5, 2, 1), (6, 2, 2), (7, 2, 3),
       (8, 3, 1), (9, 3, 4), (10, 3, 5), (11, 3, 6),
       (12, 4, 1), (13, 4, 3), (14, 4, 4);
       
INSERT INTO `aplisens`.`housing` (`id`, `name`, `price`, `description`, `create_date`)
VALUES (1, 'PD', 0, 'Przyłącze typu pd (standard)',  '2018-03-20'), 
       (2, 'PZ', 220, 'Obudowa ze stali kwasoodpornej', '2018-03-20'), 
       (3, 'PM12', 80, 'Podwyższona szczelność', '2018-03-20'), 
       (4, 'ALW', 400, 'Z wyświetlaczem', '2018-03-20'); 

INSERT INTO `aplisens`.`products_housing` (`id`, `product_ID`, `housing_ID`)
VALUES (1, 3, 1), (2, 3, 2), (3, 3, 3),(4, 3, 4),
       (5, 4, 4), (6, 4, 2);            
