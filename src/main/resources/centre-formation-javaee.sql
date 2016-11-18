-- Change le délimiteur pour pouvoir écrire des ; dans la
-- procédure stockée

DELIMITER §



DROP SCHEMA IF EXISTS centre_formation §
CREATE SCHEMA IF NOT EXISTS centre_formation DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci §
USE centre_formation §

CREATE TABLE IF NOT EXISTS promotion (
  id_promotion BIGINT(20) NOT NULL AUTO_INCREMENT,
  nom VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_promotion),
  UNIQUE INDEX nom_UNIQUE (nom ASC))
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS personne (
  id_personne BIGINT(20) NOT NULL AUTO_INCREMENT,
  nom VARCHAR(45) NOT NULL,
  prenom VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  pwd  VARCHAR(45) NOT NULL,
  profil  boolean not null default false,
  PRIMARY KEY (id_personne),
  UNIQUE INDEX email_UNIQUE (email ASC))
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS membre_promotion (
  id_promotion BIGINT(20) NOT NULL,
  id_personne BIGINT(20) NOT NULL,
  PRIMARY KEY (id_promotion, id_personne),
  CONSTRAINT fk_membre_promotion_promotion
    FOREIGN KEY (id_promotion)
    REFERENCES promotion (id_promotion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_membre_promotion_personne
    FOREIGN KEY (id_personne)
    REFERENCES personne (id_personne)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS projet (
  id_projet BIGINT(20) NOT NULL AUTO_INCREMENT,
  id_promotion BIGINT(20) NOT NULL,
  id_createur BIGINT(20) NOT NULL,
  sujet TEXT NOT NULL,
  titre VARCHAR(100) NOT NULL,
  date_creation DATETIME NOT NULL,
  date_limite DATETIME NOT NULL,
  PRIMARY KEY (id_projet),
  CONSTRAINT fk_projet_promotion
    FOREIGN KEY (id_promotion)
    REFERENCES promotion (id_promotion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_projet_personne
    FOREIGN KEY (id_createur)
    REFERENCES personne (id_personne)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS equipe (
  id_equipe BIGINT(20) NOT NULL AUTO_INCREMENT,
  id_projet BIGINT(20) NOT NULL,
  id_createur BIGINT(20) NOT NULL,
  date_creation DATETIME NOT NULL,
  resume VARCHAR(255) NULL,
  PRIMARY KEY (id_equipe),
  CONSTRAINT fk_equipe_projet
    FOREIGN KEY (id_projet)
    REFERENCES projet (id_projet)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_equipe_personne
    FOREIGN KEY (id_createur)
    REFERENCES personne (id_personne)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB§


CREATE TABLE IF NOT EXISTS membre_equipe (
  id_equipe BIGINT(20) NOT NULL,
  id_personne BIGINT(20) NOT NULL,
  PRIMARY KEY (id_equipe, id_personne),
  CONSTRAINT fk_membre_equipe_equipe
    FOREIGN KEY (id_equipe)
    REFERENCES equipe (id_equipe)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_membre_equipe_personne
    FOREIGN KEY (id_personne)
    REFERENCES personne (id_personne)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB§


DROP PROCEDURE IF EXISTS centre_formation_refresh §
CREATE DEFINER=root@localhost PROCEDURE centre_formation_refresh()
BEGIN
	-- Lever temporairement les contraintes d'intégrité
	SET FOREIGN_KEY_CHECKS=0;
	TRUNCATE equipe;
	TRUNCATE membre_equipe;
	TRUNCATE membre_promotion;
	TRUNCATE personne;
    TRUNCATE projet;
    TRUNCATE promotion;
	-- Remettre les contraintes d'integrite
	SET FOREIGN_KEY_CHECKS=1;

	
	BEGIN
	  DECLARE EXIT HANDLER FOR SQLSTATE '23000'
	  BEGIN
		SHOW ERRORS;
        ROLLBACK;
	  END;
		START TRANSACTION;
        
	INSERT INTO personne(id_personne,nom, prenom, email,pwd,profil) VALUES
		(1, 'Haddock', 'Archibald', 'haddock@moulinsart.be','karara',false),
		(2,'Castafiore', 'Bianca', 'bianca.castafiore@scala.it','kamiri',false),
		(3, 'Tournesol', 'Tryphon', 'tournesol@moulinsart.be','milou',false),
		(4,'Lampion', 'Séraphin', 'lampion@mondass.fr','malin',false),
		(5,'Krad', 'Imen', 'imkr@yahoo.fr','ilala',false),
		(6,'Siby', 'Abdoulaye', 'absiby@yahoo.fr','soloma',false),
		(7, 'Buittot', 'Eleanor', 'bui.elea@gamil.com','sisqo',false),
		(8,'Mickael', 'Angelo', 'M.angelo@tortue.fr','sabin',false),
		(9,'Eddy', 'Kenzo', 'edke@yahoo.fr','sodoma',true),
		(10,'Feyte', 'Floria', 'f.feyte@gamil.com','sisoko', true);

	INSERT INTO promotion (id_promotion, nom) VALUES
		(1, 'Decisionel et developpement JEE'),
		(2, 'MOA');

	INSERT INTO membre_promotion (id_promotion, id_personne) VALUES
		(1, 1),
		(1, 2),
		(1, 3),
		(1, 4),
		(1,5),
		(1, 6),
      
		(2, 7),
		(2, 8);
		
	INSERT INTO projet (id_projet,id_promotion, id_createur,sujet,titre,date_creation, date_limite ) VALUES
		(1, 1,9,'gestion de terrain','flow managment project','2016-4-12 22:00:00','2016-7-12 22:00:00'),
		(2, 1,10,'interface contenteiux','scores-decision project','2016-5-15 22:00:00','2016-8-14 22:00:00'),
		(3, 1,9,'gerer les annonces legales','socores-decisions project','2016-6-13 22:00:00','2016-9-11 22:00:00');

	INSERT INTO equipe (id_equipe, id_projet,id_createur,date_creation) VALUES
		(1, 1,2,'2016-4-20 22:00:00'),
        (2, 1,3,'2016-4-23 22:00:00'),
        (3, 2,1,'2016-5-21 22:00:00'),
        (4, 1,4,'2016-5-1 22:00:00');
        
	INSERT INTO membre_equipe (id_equipe,id_personne) VALUES
		(1, 2),
        (1, 5),
        (2, 3),
        (2, 1),
        (3, 1),
        (3, 4),
        (4, 4),
        (4, 6);	
	  COMMIT;
	END;
END§

DROP FUNCTION IF EXISTS initcap§
CREATE FUNCTION initcap(chaine text) RETURNS text CHARSET utf8
deterministic
BEGIN
 DECLARE gauche, droite text;
 SET gauche='';
 SET droite ='';
 WHILE chaine LIKE '% %' DO -- si elle contient un espace
 SELECT SUBSTRING_INDEX(chaine, ' ', 1) INTO gauche;
 SELECT SUBSTRING(chaine, LOCATE(' ', chaine) + 1) INTO chaine;
 SELECT CONCAT(droite, ' ',
 CONCAT(UPPER(SUBSTRING(gauche, 1, 1)),
 LOWER(SUBSTRING(gauche, 2)))) INTO droite;
 END WHILE;
 RETURN LTRIM(CONCAT(droite, ' ',
CONCAT(UPPER(SUBSTRING(chaine,1,1)), LOWER(SUBSTRING(chaine, 2)))));
END§

DROP TRIGGER IF EXISTS personne_before_update_trigger§
CREATE TRIGGER personne_before_update_trigger
BEFORE UPDATE ON personne
FOR EACH ROW
BEGIN
 SET NEW.prenom = trim(initcap(NEW.prenom));
 SET NEW.nom = trim(upper(NEW.nom));
 SET NEW.email = trim(NEW.email);
END§

DROP TRIGGER IF EXISTS personne_before_update_trigger§
CREATE TRIGGER personne_before_update_trigger
BEFORE INSERT ON personne
FOR EACH ROW
BEGIN
 SET NEW.prenom = trim(initcap(NEW.prenom));
 SET NEW.nom = trim(upper(NEW.nom));
 SET NEW.email = trim(NEW.email);
END§



DROP TRIGGER IF EXISTS projet_before_update_trigger§
CREATE TRIGGER projet_before_update_trigger
BEFORE INSERT ON projet
FOR EACH ROW 
BEGIN
 SET NEW.date_creation = now();
END§


call centre_formation_refresh()§