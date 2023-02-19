INSERT INTO `drone_model`(`id`, `name`, `weight_limit_grams`) 
VALUES (1, 'Lightweight', 100),
		(2, 'Middleweight', 200),
		(3, 'Cruiserweight', 300),
		(4, 'Heavyweight', 500);
		

INSERT INTO `drone` (`serial_number`, `model_id`)
VALUES ('serial_number1', 1),
('serial_number2', 2),
('serial_number3', 3),
('serial_number4', 4);


INSERT INTO `medication`
(`code`, `name`, `weight_grams`, `image`)
VALUES ('MED001', 'Medication 1', 1.5, RAWTOHEX('MED001 image')),
('MED002', 'Medication 2', 1.5, RAWTOHEX('MED002 image')),
('MED003', 'Medication 2', 5, RAWTOHEX('MED003 image')),
('MED004', 'Medication 2', 2, RAWTOHEX('MED004 image'));



		
