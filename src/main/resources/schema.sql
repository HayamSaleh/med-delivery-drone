
CREATE TABLE `drone_model` (
    `id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL unique,
	`weight_limit_grams` FLOAT NOT NULL CHECK (weight_limit_grams <= 500),
	PRIMARY KEY (id)
);

CREATE TABLE `drone_state` (
    `id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL unique,
	PRIMARY KEY (id)
);

CREATE TABLE `drone` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `serial_number` VARCHAR(100) NOT NULL,
	`model_id` INTEGER NOT NULL,
	`current_state_id` INTEGER NOT NULL,
	`battery_current_capacity` FLOAT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_droneModel FOREIGN KEY (model_id) REFERENCES drone_model(id),
	CONSTRAINT FK_droneState FOREIGN KEY (current_state_id) REFERENCES drone_state(id)
);

CREATE TABLE `medication` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`code` VARCHAR(100) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`weight` FLOAT NOT NULL,
	`image` BLOB NOT NULL,
	PRIMARY KEY (id)
--	UNIQUE KEY (code)
);

CREATE TABLE delivery (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `drone_id` BIGINT NOT NULL,
  `start_time` TIMESTAMP NOT NULL,
  `end_time` TIMESTAMP,
  `delivery_status` ENUM('PENDING', 'IN_PROGRESS', 'DELIVERED') NOT NULL,
  PRIMARY KEY (id),	
  CONSTRAINT FK_deliveryDrone FOREIGN KEY (drone_id) REFERENCES drone(id)
);

CREATE TABLE delivery_medication (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `delivery_id` BIGINT NOT NULL,
  `medication_id` BIGINT NOT NULL,
  `quantity` INTEGER NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_delivery FOREIGN KEY (delivery_id) REFERENCES delivery(id),
  CONSTRAINT FK_medication FOREIGN KEY (medication_id) REFERENCES medication(id)
);