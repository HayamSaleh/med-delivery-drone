INSERT INTO `drone_model`(`id`, `name`, `weight_limit_grams`) 
VALUES (1, 'Lightweight', 100),
		(2, 'Middleweight', 200),
		(3, 'Cruiserweight', 300),
		(4, 'Heavyweight', 500);
		

INSERT INTO `drone` (`serial_number`, `model_id`,`battery_level` )
VALUES ('serial_number1', 1, 100),
('serial_number2', 2, 50),
('serial_number3', 3, 20),
('serial_number4', 4, 10);


INSERT INTO `medication`
(`code`, `name`, `weight_grams`, `image`)
VALUES ('MED001', 'Medication_1', 1.5, RAWTOHEX('MED001 image')),
('MED002', 'Medication_2', 1.5, RAWTOHEX('MED002 image')),
('MED003', 'Medication_3', 5, RAWTOHEX('MED003 image')),
('MED004', 'Medication_4', 2, RAWTOHEX('MED004 image'));



		
