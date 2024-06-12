INSERT INTO t_roles (code, role_name) VALUES 
	('ROLE_ADMIN', 'Administrateur'),
	('ROLE_TRAINER', 'Formateur');
	
INSERT INTO t_certification_levels (code, french_level, european_level, equivalence) VALUES 
	('NIV-6', 'II', 6, 'Licence, licence professionnelle, BUT');

INSERT INTO t_certifications (code, certification_name, acronym, start_year, certification_level_id) VALUES 
	('RNCP37873', 'Concepteur développeur d''applications', 'CDA', 2023, 
		(SELECT cl.id FROM t_certification_levels cl WHERE cl.code = 'NIV-6')),
	('RNCP31678', 'Concepteur développeur d''applications', 'CDA', 2018, 
		(SELECT cl.id FROM t_certification_levels cl WHERE cl.code = 'NIV-6'));
	
INSERT INTO t_activities (code, activity_name, certification_id) VALUES 
	('RNCP37873BC01', 'Développer une application sécurisée', (SELECT c.id FROM t_certifications c WHERE c.code = 'RNCP37873')),
	('RNCP37873BC02', 'Concevoir et développer une application sécurisée organisée en couches', (SELECT c.id FROM t_certifications c WHERE c.code = 'RNCP37873')),
	('RNCP37873BC03', 'Préparer le déploiement d''une application sécurisée', (SELECT c.id FROM t_certifications c WHERE c.code = 'RNCP37873')),
	('RNCP31678BC01', 'Concevoir et développer des composants d''interface utilisateur en intégrant les recommandations de sécurité', (SELECT c.id FROM t_certifications c WHERE c.code = 'RNCP31678')),
	('RNCP31678BC02', 'Concevoir et développer la persistance des données en intégrant les recommandations de sécurité', (SELECT c.id FROM t_certifications c WHERE c.code = 'RNCP31678')),
	('RNCP31678BC03', 'Concevoir et développer une application multicouche répartie en intégrant les recommandations de sécurité', (SELECT c.id FROM t_certifications c WHERE c.code = 'RNCP31678'));

INSERT INTO t_skills (code, skill_name, activity_id) VALUES 
	('RNCP37873CP01', 'Installer et configurer son environnement de travail en fonction du projet', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC01')),
	('RNCP37873CP02', 'Développer des interfaces utilisateur', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC01')),
	('RNCP37873CP03', 'Développer des composants métier', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC01')),
	('RNCP37873CP04', 'Contribuer à la gestion d''un projet informatique', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC01')),
	('RNCP37873CP05', 'Analyser les besoins et maquetter une application', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC02')),
	('RNCP37873CP06', 'Définir l''architecture logicielle d''une application', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC02')),
	('RNCP37873CP07', 'Concevoir et mettre en place une base de données relationnelle', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC02')),
	('RNCP37873CP08', 'Développer des composants d''accès aux données SQL et NoSQL', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC02')),
	('RNCP37873CP09', 'Préparer et exécuter les plans de tests d''une application', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC03')),
	('RNCP37873CP10', 'Préparer et documenter le déploiement d''une application', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC03')),
	('RNCP37873CP11', 'Contribuer à la mise en production dans une démarche DevOps', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP37873BC03')),
	('RNCP31678CP01', 'Maquetter une application', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC01')),
	('RNCP31678CP02', 'Développer une interface utilisateur de type desktop', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC01')),
	('RNCP31678CP03', 'Développer des composants d''accès aux données', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC01')),
	('RNCP31678CP04', 'Développer la partie front-end d''une interface utilisateur web', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC01')),
	('RNCP31678CP05', 'Développer la partie back-end d''une interface utilisateur web', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC01')),
	('RNCP31678CP06', 'Concevoir une base de données', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC02')),
	('RNCP31678CP07', 'Mettre en place une base de données', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC02')),
	('RNCP31678CP08', 'Développer des composants dans le langage d''une base de données', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC02')),
	('RNCP31678CP09', 'Collaborer à la gestion d''un projet informatique et à l''organisation de l''environnement de développement', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC03')),
	('RNCP31678CP10', 'Concevoir une application', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC03')),
	('RNCP31678CP11', 'Développer des composants métier', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC03')),
	('RNCP31678CP12', 'Construire une application organisée en couches', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC03')),
	('RNCP31678CP13', 'Développer une application mobile', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC03')),
	('RNCP31678CP14', 'Préparer et exécuter les plans de tests d''une application', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC03')),
	('RNCP31678CP15', 'Préparer et exécuter le déploiement d''une application', (SELECT a.id FROM t_activities a WHERE a.code = 'RNCP31678BC03'));

INSERT INTO t_trainings (training_name, start_date, end_date, description, certification_id) VALUES 
	('ITSCHOOL P3', '2022-10-03', '2024-07-12', 'ITSchool, promotion numéro 3', (SELECT c.id FROM t_certifications c WHERE c.code = 'RNCP31678')),
	('ITSCHOOL P4', '2023-12-11', '2025-09-19', 'ITSchool, promotion numéro 4', (SELECT c.id FROM t_certifications c WHERE c.code = 'RNCP37873'));
