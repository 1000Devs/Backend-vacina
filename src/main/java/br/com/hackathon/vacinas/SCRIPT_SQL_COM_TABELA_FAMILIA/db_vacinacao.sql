DROP DATABASE IF EXISTS vacinacao;
CREATE DATABASE vacinacao;

USE vacinacao;

CREATE TABLE `familia` (
  `id_familia` int NOT NULL AUTO_INCREMENT,
  `nome_familia` varchar(100) NOT NULL,
  `telefone_contato` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_familia`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `vacina` (
  `id_vacina` int NOT NULL AUTO_INCREMENT,
  `nome_vacina` varchar(50) NOT NULL,
  `descricao_vacina` varchar(200) NOT NULL,
  `limite_aplicacao` int DEFAULT NULL,
  `publico_alvo` enum('CRIANÇA','ADOLESCENTE','ADULTO','GESTANTE') NOT NULL,
  PRIMARY KEY (`id_vacina`),
  UNIQUE KEY `vacina_UNIQUE` (`nome_vacina`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `paciente` (
  `id_paciente` int NOT NULL AUTO_INCREMENT,
  `id_familia` int NOT NULL,
  `nome_paciente` varchar(60) NOT NULL,
  `cpf_paciente` varchar(11) DEFAULT NULL,
  `sexo` enum('M','F') NOT NULL,
  `data_nascimento` date NOT NULL,
  PRIMARY KEY (`id_paciente`),
  UNIQUE KEY `nome_UNIQUE` (`nome_paciente`),
  KEY `fk_familia_id_idx` (`id_familia`),
  CONSTRAINT `fk_familia_id` FOREIGN KEY (`id_familia`) REFERENCES `familia` (`id_familia`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `dose` (
  `id_dose` int NOT NULL AUTO_INCREMENT,
  `id_vacina` int NOT NULL,
  `descricao_dose` varchar(45) NOT NULL,
  `idade_recomendada_aplicacao` int NOT NULL,
  PRIMARY KEY (`id_dose`),
  UNIQUE KEY `unique_id_vacina_dose` (`id_vacina`,`id_dose`),
  KEY `fk_vacina_id_idx` (`id_vacina`),
  CONSTRAINT `fk_vacina_id` FOREIGN KEY (`id_vacina`) REFERENCES `vacina` (`id_vacina`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `imunizacoes` (
  `id_imunizacao` int NOT NULL AUTO_INCREMENT,
  `id_paciente` int NOT NULL,
  `id_dose` int NOT NULL,
  `data_aplicacao` date NOT NULL,
  `fabricante` varchar(45) DEFAULT NULL,
  `lote` varchar(45) DEFAULT NULL,
  `local_aplicacao` varchar(45) DEFAULT NULL,
  `profissional_aplicador` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_imunizacao`),
  UNIQUE KEY `unique_dose_paciente` (`id_paciente`,`id_dose`),
  KEY `fk_paciente_id_idx` (`id_paciente`),
  KEY `fk_dose_id_idx` (`id_dose`) ,
  CONSTRAINT `fk_dose_id` FOREIGN KEY (`id_dose`) REFERENCES `dose` (`id_dose`),
  CONSTRAINT `fk_paciente_id` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO vacina VALUES (1,'BCG','Proteção contra formas graves de tuberculose (meníngea e miliar)', 60,'CRIANÇA');
INSERT INTO vacina VALUES (2,'Hepatite B','Proteção contra o vírus da Hepatite B', NULL,'CRIANÇA');
INSERT INTO vacina VALUES (3,'Poliomielite 1, 2 e 3','Proteção contra o vírus da Poliomielite', NULL,'CRIANÇA');
INSERT INTO vacina VALUES (4,'Rotavírus','Proteção contra o rotavírus humano G1P[8] (ROTA)', 9, 'CRIANÇA');
INSERT INTO vacina VALUES (5,'Pentavalente (DTP, HB, Hib)','Proteção contra DTP (difteria, tétano, coqueluche), HB (hepatite b) e Hib (influenza tipo b)', 84 ,'CRIANÇA');
INSERT INTO vacina VALUES (6,'Pneumocócica 10','Proteção contra Pneumonias, Meningites, Otites e Sinusites pelos sorotipos que compõem a vacina', 60, 'CRIANÇA');
INSERT INTO vacina VALUES (7,'Meningocócica C','Proteção contra Meningite meningocócica tipo C',60,'CRIANÇA');
INSERT INTO vacina VALUES (8,'COVID-19','Proteção contra o vírus SARS-CoV-2', 60 ,'CRIANÇA');
INSERT INTO vacina VALUES (9,'Febre Amarela','Proteção contra o vírus da febre amarela',NULL,'CRIANÇA');
INSERT INTO vacina VALUES (10,'Tríplice Viral','Proteção contra os vírus: sarampo, caxumba e rubéola', 72,'CRIANÇA');
INSERT INTO vacina VALUES (11,'Tetraviral','Proteção contra os vírus: sarampo, caxumba, rubéola e varicela', 84,'CRIANÇA');
INSERT INTO vacina VALUES (12,'Hepatite A','Proteção contra o vírus da Hepatite A', 60,'CRIANÇA');
INSERT INTO vacina VALUES (13,'DTP ','Proteção contra Difteria, Tétano e Pertussis', 84,'CRIANÇA');
INSERT INTO vacina VALUES (14,'HPV4','Proteção contra o Papilomavírus Humano 6, 11, 16 e 18', 180,'CRIANÇA');
INSERT INTO vacina VALUES (15,'Pneumocócica 23','Proteção contra Meningites bacterianas, Pneumonias, Sinusite e outros',NULL,'CRIANÇA');
INSERT INTO vacina VALUES (16,'Varicela','Proteção contra o vírus da varicela (catapora)', 156 ,'CRIANÇA');

INSERT INTO dose values(1,1,'Dose Única',0);
INSERT INTO dose values(2,2,'Dose Única',0);
INSERT INTO dose values(3,3,'1ª Dose',2);
INSERT INTO dose values(4,3,'2ª Dose',4);
INSERT INTO dose values(5,3,'3ª Dose',6);
INSERT INTO dose values(6,4,'1ª Dose',2);
INSERT INTO dose values(7,4,'2ª Dose',4);
INSERT INTO dose values(8,5,'1ª Dose',2);
INSERT INTO dose values(9,5,'2ª Dose',4);
INSERT INTO dose values(10,5,'3ª Dose',6);
INSERT INTO dose values(11,6,'1ª Dose',2);
INSERT INTO dose values(12,6,'2ª Dose',4);
INSERT INTO dose values(13,6,'Reforço',12);
INSERT INTO dose values(14,7,'1ª Dose',3);
INSERT INTO dose values(15,7,'2ª Dose',5);
INSERT INTO dose values(16,7,'Reforço',12);
INSERT INTO dose values(17,8,'1ª Dose',6);
INSERT INTO dose values(18,8,'2ª Dose',7);
INSERT INTO dose values(19,9,'1ª Dose',9);
INSERT INTO dose values(20,9,'Reforço',48);
INSERT INTO dose values(21,10,'1ª Dose',12);
INSERT INTO dose values(22,11,'1ª Dose',15);
INSERT INTO dose values(23,12,'Dose Única',15);
INSERT INTO dose values(24,13,'1ª Reforço',15);
INSERT INTO dose values(25,13,'2ª Reforço',48);
INSERT INTO dose values(26,14,'Dose Única',108);
INSERT INTO dose values(27,15,'1ª Dose',60);
INSERT INTO dose values(28,15,'2ª Dose',120);
INSERT INTO dose values(29,16,'2ª Dose',48);

INSERT INTO familia (id_familia, nome_familia, telefone_contato) VALUES 
(1, 'Família Oliveira', '911222333'),
(2, 'Família Costa', '966777888');

INSERT INTO paciente (id_paciente, id_familia, nome_paciente, cpf_paciente, sexo, data_nascimento) VALUES 
(1, 1, 'Carlos Oliveira', '11122233344', 'M', '1985-10-12'),
(2, 1, 'Ana Oliveira', '55566677788', 'F', '2018-05-20'),
(3, 2, 'Beatriz Costa', '99988877766', 'F', '1992-03-08');