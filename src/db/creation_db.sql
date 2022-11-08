CREATE TABLE IF NOT EXISTS Filiere(
	IdFiliere VARCHAR(5) PRIMARY KEY,
	NomComplet VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS Etudiant(
	NumInscription VARCHAR(8) PRIMARY KEY,
	Nom VARCHAR(20) NOT NULL,
	Prenom VARCHAR(20) NOT NULL,
	Email VARCHAR(30) NOT NULL,
	Classe VARCHAR(6) NOT NULL,
	FiliereEtud VARCHAR(5) NOT NULL REFERENCES Filiere(IdFiliere)
);
INSERT INTO Filiere(IdFiliere,NomComplet) VALUES("RT","Réseaux et Télécommunications");
INSERT INTO Filiere(IdFiliere,NomComplet) VALUES("IIA","Informatique Industriel et Automatisme");
INSERT INTO Filiere(IdFiliere,NomComplet) VALUES("IMI","Instrumentation et Maintenance Industrielle");
INSERT INTO Filiere(IdFiliere,NomComplet) VALUES("GL","Génie Logiciel");
INSERT INTO Filiere(IdFiliere,NomComplet) VALUES("CH","Chimie");
INSERT INTO Filiere(IdFiliere,NomComplet) VALUES("BIO","Biologie");
