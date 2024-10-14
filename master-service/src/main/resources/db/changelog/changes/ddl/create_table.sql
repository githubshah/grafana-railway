--liquibase formatted sql

--changeset shaid-train:1
DROP TABLE IF EXISTS master_train;
CREATE TABLE master_train (
	id int8 NOT NULL,
	name varchar(255) NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_train_pkey PRIMARY KEY (id)
);

CREATE TABLE master.master_coach (
	id int8 NOT NULL,
	name varchar(255) NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_coach_pkey PRIMARY KEY (id)
);

CREATE TABLE master.master_fare_policy (
	id int8 NOT NULL,
	coach_percentage_filled int8 NOT NULL,
	increase_price_by int8 NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_fare_policy_pkey PRIMARY KEY (id)
);

CREATE TABLE master.master_station (
	id int8 NOT NULL,
	name varchar(255) NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_station_pkey PRIMARY KEY (id)
);

CREATE TABLE master.master_route (
	id int8 NOT NULL,
	name varchar(255) NOT NULL,
	source_station_id int8 NOT NULL,
	destination_station_id int8 NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_route_pkey PRIMARY KEY (id)
);
ALTER TABLE master.master_route ADD CONSTRAINT fk55lwgwi0j8d4p0ufp5w5ydpla FOREIGN KEY (source_station_id) REFERENCES master_station(id);
ALTER TABLE master.master_route ADD CONSTRAINT fkjwexyq9v8xao7db53mmae93vd FOREIGN KEY (destination_station_id) REFERENCES master_station(id);

CREATE TABLE master.master_seat (
	id int8 NOT NULL,
	name varchar(255) NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_seat_pkey PRIMARY KEY (id)
);

CREATE TABLE master.master_seat_type (
	id int8 NOT NULL,
	seat_type varchar(255) NOT NULL,
	seat_code varchar(255) NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_seat_type_pkey PRIMARY KEY (id)
);

CREATE TABLE master.master_seat_type_mapping (
	id int8 NOT NULL,
	seat_id int8 NOT NULL,
	seat_type_id int8 NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_seat_type_mapping_pkey PRIMARY KEY (id)
);
ALTER TABLE master.master_seat_type_mapping ADD CONSTRAINT fk1ukqabr3w3dbt66dtxl8my85q FOREIGN KEY (seat_id) REFERENCES master.master_seat(id);
ALTER TABLE master.master_seat_type_mapping ADD CONSTRAINT fk5a9y5gtq2yf4u4l4cup9pld9r FOREIGN KEY (seat_type_id) REFERENCES master.master_seat_type(id);

CREATE TABLE master.master_train_coach_base_price_policy (
	id int8 NOT NULL,
	train_id int8 NOT NULL,
	coach_id int8 NOT NULL,
	base_price float8 NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_train_coach_base_price_policy_pkey PRIMARY KEY (id)
);


ALTER TABLE master.master_train_coach_base_price_policy ADD CONSTRAINT fk6b2o0bw5kaii9bhvb7kcydtt9 FOREIGN KEY (coach_id) REFERENCES master.master_coach(id);
ALTER TABLE master.master_train_coach_base_price_policy ADD CONSTRAINT fkhy1jyxkfebjuj6iwt1nrywjes FOREIGN KEY (train_id) REFERENCES master.master_train(id);

CREATE TABLE master.master_train_coach_mapping (
	id int8 NOT NULL,
	train_id int8 NOT NULL,
	coach_id int8 NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_train_coach_mapping_pkey PRIMARY KEY (id)
);
ALTER TABLE master.master_train_coach_mapping ADD CONSTRAINT fkcst6ixc48txqvlhr1i0g9eq1t FOREIGN KEY (coach_id) REFERENCES master.master_coach(id);
ALTER TABLE master.master_train_coach_mapping ADD CONSTRAINT fkhpvahqqm7q8c8brq7lnkb7cy1 FOREIGN KEY (train_id) REFERENCES master.master_train(id);

CREATE TABLE master.master_train_route_mapping (
	id int8 NOT NULL,
	train_id int8 NOT NULL,
	route_id int8 NOT NULL,
	depart_time time(6) NOT NULL,
    arrival_time time(6) NOT NULL,
	created_on timestamp(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	modified_on timestamp(6) NOT NULL,
	modified_by varchar(255) NOT NULL,
	CONSTRAINT master_train_route_mapping_pkey PRIMARY KEY (id)
);
ALTER TABLE master.master_train_route_mapping ADD CONSTRAINT fkk3o7i3lnf5hin19dd27m02hb9 FOREIGN KEY (route_id) REFERENCES master.master_route(id);
ALTER TABLE master.master_train_route_mapping ADD CONSTRAINT fksnug5u3a8co6jl4dh04m3a84m FOREIGN KEY (train_id) REFERENCES master.master_train(id);

CREATE TABLE master.master_coach_seat_mapping (
	id int8 NOT NULL,
	coach_id int8 NULL,
	seat_id int8 NULL,
	created_on timestamp(6) NULL,
	created_by varchar(255) NULL,
	modified_on timestamp(6) NULL,
	modified_by varchar(255) NULL,
	CONSTRAINT master_coach_seat_mapping_pkey PRIMARY KEY (id)
);
ALTER TABLE master.master_coach_seat_mapping ADD CONSTRAINT fkgy71fo2h7ssy9c1lj8tc5kfer FOREIGN KEY (seat_id) REFERENCES master.master_seat(id);
ALTER TABLE master.master_coach_seat_mapping ADD CONSTRAINT fkqg7nen861mlcucl38vud6kjf6 FOREIGN KEY (coach_id) REFERENCES master.master_coach(id);