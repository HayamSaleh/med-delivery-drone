INSERT INTO `drone_model`(`id`, `name`, `weight_limit_grams`) 
VALUES (1, 'Lightweight', 100),
		(2, 'Middleweight', 200),
		(3, 'Cruiserweight', 300),
		(4, 'Heavyweight', 500);
		
		
INSERT INTO `drone_state` (`id`, `name`)
VALUES(1, 'IDLE'), (2, 'LOADING'), (3, 'LOADED'), (4, 'DELIVERING'), (5, 'DELIVERED'), (6, 'RETURNING');

INSERT INTO `drone` (`serial_number`, `model_id`, `current_state_id`, `battery_current_capacity`)
VALUES ('serial_number1', 1, 1, 100),
('serial_number2', 2, 1, 100),
('serial_number3', 3, 1, 100),
('serial_number4', 4, 2, 100);




		
