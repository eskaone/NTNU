-- Oppg. 1:
SELECT bolag_navn, bolag_adr, etabl_aar, postnr FROM borettslag WHERE etabl_aar > 1975 AND etabl_aar < 1985;

-- Oppg. 2:
SELECT CONCAT(fornavn, ' ', etternavn, ', ansiennitet: ',  ansiennitet, ' år') FROM andelseier ORDER BY ansiennitet DESC;

-- Oppg. 3:
SELECT MIN(etabl_aar) FROM borettslag;

-- Oppg. 4:
SELECT DISTINCT bygn_adr FROM bygning NATURAL JOIN leilighet WHERE ant_rom >= 3;

-- Oppg. 5:
SELECT COUNT(bygn_id) as AntallBygninger FROM bygning WHERE bolag_navn = 'Tertitten');

-- Oppg. 6:
SELECT bo.bolag_navn, COUNT(bygn_id) 'Antall bygninger' 
FROM bygning b RIGHT JOIN borettslag bo
ON (b.bolag_navn = bo.bolag_navn) GROUP BY bolag_navn ORDER BY bolag_navn;

-- Oppg. 7:
SELECT COUNT(*) as AntallLeiligheter FROM leilighet JOIN bygning ON leilighet.bygn_id = bygning.bygn_id JOIN borettslag
ON borettslag.bolag_navn = 'Tertitten';

-- Oppg. 8:
SELECT MAX(ant_etasjer) as HoyesteEtasje FROM bygning WHERE bolag_navn = "Tertitten";

-- Oppg. 9:
SELECT fornavn, etternavn FROM andelseier WHERE and_eier_nr NOT IN (SELECT and_eier_nr FROM leilighet);

-- Oppg. 10:
SELECT borettslag.bolag_navn, count(andelseier.and_eier_nr) FROM borettslag LEFT OUTER JOIN andelseier ON
 GROUP BY borettslag.bolag_navn;

-- Oppg. 11:
SELECT fornavn, etternavn, leil_nr FROM andelseier
LEFT JOIN leilighet ON andelseier.and_eier_nr = leilighet.and_eier_nr;

-- Oppg. 12:
SELECT borettslag.bolag_navn FROM borettslag JOIN bygning
ON borettslag.bolag_navn = bygning.bolag_navn
JOIN leilighet ON bygning.bygn_id = leilighet.bygn_id WHERE ant_rom = 5 GROUP BY bolag_navn;

-- Oppg. 13:
SELECT postnr, COUNT(and_eier_nr) as AntallAndEiere FROM andelseier NATURAL JOIN leilighet NATURAL JOIN bygning;