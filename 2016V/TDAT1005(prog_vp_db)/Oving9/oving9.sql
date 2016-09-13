-- Oving 9


-- Oppg. c)
	-- Overskriver tabellene
DROP TABLE IF EXISTS vikartjeneste;
DROP TABLE IF EXISTS kvalifikasjon;
DROP TABLE IF EXISTS oppdrag;
DROP TABLE IF EXISTS kandidater;
DROP TABLE IF EXISTS bedrifter;

	-- Oppretter tabellene
CREATE TABLE bedrifter(
	org_nr INTEGER,
	bedrift_navn VARCHAR(50),
	bedrift_tlf VARCHAR(15),
	bedrift_epost VARCHAR(50),
	CONSTRAINT bedrift_pk PRIMARY KEY(org_nr)
);

CREATE TABLE kandidater(
	kandidat_id INTEGER,
	fnavn VARCHAR(50),
	enavn VARCHAR(50),
	tlf VARCHAR(15),
	epost VARCHAR(50),
	CONSTRAINT kandidat_pk PRIMARY KEY(kandidat_id)
);

CREATE TABLE oppdrag(
	oppdrag_nr INTEGER,
	org_nr INTEGER,
	kvalifikasjon_id INTEGER,
	start_dato DATE,
	slutt_dato DATE,
	reell_start DATE,
	reell_slutt DATE,
	ant_timer INTEGER,
	sluttattest VARCHAR(500),
	CONSTRAINT oppdrag_pk PRIMARY KEY(oppdrag_nr)
);

CREATE TABLE kvalifikasjon(
	kvalifikasjon_id INTEGER,
	beskrivelse VARCHAR(100),
	CONSTRAINT kvalifikasjon_pk PRIMARY KEY(kvalifikasjon_id)
);

CREATE TABLE vikartjeneste(
	oppdrag_nr INTEGER,
	org_nr INTEGER,
	kvalifikasjon_id INTEGER,
	kandidat_id INTEGER,
	CONSTRAINT vikartjeneste_pk PRIMARY KEY(kandidat_id, oppdrag_nr, kvalifikasjon_id)
);

	-- Lager foreign keys og referanser
ALTER TABLE vikartjeneste
	ADD CONSTRAINT vikartjeneste_fk FOREIGN KEY(kandidat_id) REFERENCES kandidater(kandidat_id);
	
ALTER TABLE vikartjeneste
	ADD CONSTRAINT vikartjeneste_fk1 FOREIGN KEY(oppdrag_nr) REFERENCES oppdrag(oppdrag_nr);
	
ALTER TABLE vikartjeneste
	ADD CONSTRAINT vikartjeneste_fk2 FOREIGN KEY(kvalifikasjon_id) REFERENCES kvalifikasjon(kvalifikasjon_id);
	
ALTER TABLE oppdrag
	ADD CONSTRAINT oppdrag_fk FOREIGN KEY(org_nr) REFERENCES bedrifter(org_nr);
	
	
	-- Setter inn data
INSERT INTO bedrifter VALUES(1, 'Oles Kulebedrift', '18811881', 'OleKB@hotmail.com');
INSERT INTO kandidater VALUES(1, 'Ole', 'Olesen', '18811882', 'olesen64@hotmail.no');
INSERT INTO kvalifikasjon VALUES(1, 'Lage kuler');
INSERT INTO oppdrag VALUES(1, 1, 1, '2016-02-02', '2016-02-11', '2016-02-03', '2016-02-15', 80, 'Det var artig aa lage kuler.');
INSERT INTO vikartjeneste VALUES(1, 1, 1, 1);

INSERT INTO bedrifter VALUES(2, 'Reinsdyrsjappa', '12345678', 'reinrein@gmail.com');
INSERT INTO kandidater VALUES(2, 'Mikkel', 'Anti Sara Gaup', NULL, 'lavvu@rein.no');
INSERT INTO kvalifikasjon VALUES(2, 'Jakte rein med lasso');
INSERT INTO oppdrag VALUES(2, 2, 2, '2016-04-03', '2016-04-28', '2016-04-08', '2016-04-10', 48, 'Jei fanga firråføtti rein på vidda mi');
INSERT INTO vikartjeneste VALUES(2, 2, 2, 2);

-- INSERT INTO bedrifter VALUES(3, 'Kebab og pizza', '33339999', 'pizzaifryseren@kebabpåkjøkken.no');
INSERT INTO kandidater VALUES(3, 'Ahmed', 'Muhammad', '911', 'Allah@Akbar.ir');
INSERT INTO kvalifikasjon VALUES(3, NULL);
-- INSERT INTO oppdrag VALUES(3, 3, 3, '2015-02-12', '2015-02-17', '2015-02-12', '2016-02-13', 2, 'jai lage masse pizza');
INSERT INTO vikartjeneste VALUES(2, 2, 3, 3);


	
-- Oppg. d)
	-- 1)
SELECT bedrift_navn, bedrift_tlf, bedrift_epost FROM bedrifter;

	-- 2)
SELECT oppdrag_nr, bedrift_epost, bedrift_tlf FROM bedrifter, oppdrag WHERE bedrifter.org_nr = oppdrag.org_nr;

	-- 3)
SELECT kandidater.kandidat_id, fnavn, enavn, kvalifikasjon.kvalifikasjon_id, beskrivelse FROM kandidater, kvalifikasjon, vikartjeneste WHERE kandidater.kandidat_id = vikartjeneste.kandidat_id AND kvalifikasjon.kvalifikasjon_id = vikartjeneste.kvalifikasjon_id;

	-- 4)
SELECT kandidater.kandidat_id, fnavn, enavn, kvalifikasjon.kvalifikasjon_id, beskrivelse FROM kandidater, kvalifikasjon, vikartjeneste WHERE kandidater.kandidat_id = vikartjeneste.kandidat_id AND kvalifikasjon.kvalifikasjon_id = vikartjeneste.kvalifikasjon_id;

	-- 5)
SELECT fnavn, enavn, reell_slutt, oppdrag.oppdrag_nr, bedrift_navn FROM kandidater, oppdrag, bedrifter, vikartjeneste WHERE kandidater.kandidat_id = 1 AND oppdrag.oppdrag_nr = vikartjeneste.oppdrag_nr AND vikartjeneste.kandidat_id = kandidater.kandidat_id AND vikartjeneste.org_nr = bedrifter.org_nr;
