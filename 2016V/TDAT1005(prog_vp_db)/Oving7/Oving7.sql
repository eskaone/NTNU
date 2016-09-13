-- Oving 7

DROP TABLE IF EXISTS andelseiere;
DROP TABLE IF EXISTS leiligheter;
DROP TABLE IF EXISTS bygninger;
DROP TABLE IF EXISTS borettslag;

CREATE TABLE borettslag(
	borettslag_id INTEGER AUTO_INCREMENT,
	borettslag_navn VARCHAR(50),
	borettslag_adr VARCHAR(50),
	etb_aar INTEGER,
	ant_bygninger INTEGER NOT NULL,
	CONSTRAINT borettslag_pk PRIMARY KEY(borettslag_id)
);

CREATE TABLE bygninger(
	bygning_id INTEGER AUTO_INCREMENT,
	bygning_adr VARCHAR(50),
	ant_leiligheter INTEGER,
	ant_etasjer INTEGER,
	borettslag_id INTEGER NOT NULL,
	CONSTRAINT bygninger_pk PRIMARY KEY(bygning_id)
);

CREATE TABLE leiligheter(
	leilighet_id INTEGER AUTO_INCREMENT,
	ant_rom INTEGER,
	ant_m2 INTEGER,
	etasje INTEGER,
	bygning_id INTEGER NOT NULL,
	CONSTRAINT leiligheter_pk PRIMARY KEY(leilighet_id)
);

CREATE TABLE andelseiere(
	andelseier_id INTEGER AUTO_INCREMENT,
	fnavn VARCHAR(50) NOT NULL,
	enavn VARCHAR(50) NOT NULL,
	tlf CHAR(15),
	leilighet_id INTEGER,
	CONSTRAINT andelseiere_pk PRIMARY KEY(andelseier_id)
);

ALTER TABLE andelseiere
	ADD CONSTRAINT andelseiere_pk FOREIGN KEY(leilighet_id) REFERENCES leiligheter(leilighet_id);
	
ALTER TABLE leiligheter
	ADD CONSTRAINT leiligheter_pk FOREIGN KEY(bygning_id) REFERENCES bygninger(bygning_id);

ALTER TABLE bygninger
	ADD CONSTRAINT bygninger_pk FOREIGN KEY(borettslag_id) REFERENCES borettslag(borettslag_id);

	--Funker--

INSERT INTO borettslag VALUES(DEFAULT, 'Borettslaget', 'Oslo', 1981, 1);
INSERT INTO bygninger VALUES(DEFAULT, 'Blokk 1', 20, 5, 1);
INSERT INTO leiligheter VALUES(DEFAULT, 3, 20, 2, 1);
INSERT INTO andelseiere VALUES(DEFAULT, 'Johan', 'Bobby', '1881', 1);


	--Funker ikke--
	
INSERT INTO andelseiere VALUES(DEFAULT, 'Johan', 'Bobby', '1881', 1, 100, 'Ole');