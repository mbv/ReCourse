--
--  RUN THIS ONLY WITH NEW SCHEMA OR
--  WITH EMPTY TABLES AND AUTO_INCREMENTS = 0
--
--  IN OTHER CASE IT WILL BE FK VIOLATION
--

USE `recourse`;

INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Foo', 'Bar', 'admin@triumgroup.com',
   'MALE', NULL, 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Danser', 'Dominique',
   'Dominique_Danser@triumgroup.com', 'FEMALE', NULL, 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Habeshaw', 'Vin', 'Vin_Habeshaw@triumgroup.com',
   'MALE', '1990-09-26', 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Hierro', 'Bartolomeo',
   'Bartolomeo_Hierro@triumgroup.com', 'MALE', NULL, 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Springall', 'Brandise',
   'Brandise_Springall@triumgroup.com', 'FEMALE', '1993-04-30', 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Jozwik', 'Hill', 'Hill_Jozwik@triumgroup.com',
   'MALE', '1997-05-04', 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'MacCartair', 'Katrinka',
   'Katrinka_MacCartair@triumgroup.com', 'FEMALE', NULL, 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Kenningham', 'Donalt',
   'Donalt_Kenningham@triumgroup.com', 'MALE', NULL, 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Tweedell', 'Fleming',
   'Fleming_Tweedell@triumgroup.com', 'MALE', '1996-07-19', 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Pairpoint', 'Zacharia',
   'Zacharia_Pairpoint@triumgroup.com', 'MALE', '1998-10-12', 'ADMIN');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Andrey', 'Tatarenko',
   'Andrey_Tatarenko@triumgroup.com', 'MALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Simmon', 'Enriqueta',
   'Enriqueta_Simmon@triumgroup.com', 'FEMALE', '1991-04-16', 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Sterman', 'Sterne', 'Sterne_Sterman@triumgroup.com',
   'MALE', '1997-03-02', 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Niccols', 'Westleigh',
   'Westleigh_Niccols@triumgroup.com', 'MALE', '1997-04-15', 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Deathridge', 'Harlan',
   'Harlan_Deathridge@triumgroup.com', 'MALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Abazi', 'Melody', 'Melody_Abazi@triumgroup.com',
   'FEMALE', '1990-05-04', 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Carrick', 'Padriac',
   'Padriac_Carrick@triumgroup.com', 'MALE', '1992-04-07', 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Bernollet', 'Ilka', 'Ilka_Bernollet@triumgroup.com',
   'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Willbond', 'Germaine',
   'Germaine_Willbond@triumgroup.com', 'FEMALE', '1991-02-25', 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Curryer', 'Ingrid', 'Ingrid_Curryer@triumgroup.com',
   'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Gilliatt', 'Edith', 'Edith_Gilliatt@triumgroup.com',
   'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Schott', 'Karylin', 'Karylin_Schott@triumgroup.com',
   'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Bowsher', 'Marcelle',
   'Marcelle_Bowsher@triumgroup.com', 'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'oldey', 'Dill', 'Dill_oldey@triumgroup.com', 'MALE',
   '1995-05-11', 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Hartless', 'Terri', 'Terri_Hartless@triumgroup.com',
   'MALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Reveland', 'Loralee',
   'Loralee_Reveland@triumgroup.com', 'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Werny', 'Deeyn', 'Deeyn_Werny@triumgroup.com',
   'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Fockes', 'Georgiana',
   'Georgiana_Fockes@triumgroup.com', 'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Simonson', 'Rodolph',
   'Rodolph_Simonson@triumgroup.com', 'MALE', '1993-10-25', 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Bigland', 'Alexine',
   'Alexine_Bigland@triumgroup.com', 'FEMALE', NULL, 'TEACHER');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Mikhail', 'Snitavets',
   'Mikhail_Snitavets@triumgroup.com', 'MALE', '1996-11-21', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Bayly', 'Arlena', 'Arlena_Bayly@triumgroup.com',
   'FEMALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Filimore', 'Waverly',
   'Waverly_Filimore@triumgroup.com', 'MALE', '1999-12-14', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Abethell', 'Russell',
   'Russell_Abethell@triumgroup.com', 'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Hawkswood', 'Humbert',
   'Humbert_Hawkswood@triumgroup.com', 'MALE', '1990-07-29', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Ferrini', 'Belvia', 'Belvia_Ferrini@triumgroup.com',
   'FEMALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Scurfield', 'Dolores',
   'Dolores_Scurfield@triumgroup.com', 'FEMALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Izen', 'Arliene', 'Arliene_Izen@triumgroup.com',
   'FEMALE', '1997-02-22', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Schouthede', 'Marice',
   'Marice_Schouthede@triumgroup.com', 'FEMALE', '1991-07-21', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Acedo', 'Jarret', 'Jarret_Acedo@triumgroup.com',
   'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'enzley', 'Rayner', 'Rayner_enzley@triumgroup.com',
   'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Goodall', 'Clemmie',
   'Clemmie_Goodall@triumgroup.com', 'FEMALE', '1992-12-28', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'M''Quharge', 'Brendan',
   'Brendan_M''Quharge@triumgroup.com', 'MALE', '1999-08-05', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Rediers', 'Geno', 'Geno_Rediers@triumgroup.com',
   'MALE', '1997-02-23', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Blazdell', 'Nicolis',
   'Nicolis_Blazdell@triumgroup.com', 'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Craker', 'Roana', 'Roana_Craker@triumgroup.com',
   'FEMALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Froggatt', 'Andre', 'Andre_Froggatt@triumgroup.com',
   'MALE', '1990-07-24', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Cometto', 'Kitti', 'Kitti_Cometto@triumgroup.com',
   'FEMALE', '1998-02-04', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Kobierra', 'Toddy', 'Toddy_Kobierra@triumgroup.com',
   'MALE', '1999-02-06', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Prior', 'Huntlee', 'Huntlee_Prior@triumgroup.com',
   'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Chisman', 'Alta', 'Alta_Chisman@triumgroup.com',
   'FEMALE', '1998-06-04', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Anster', 'Remington',
   'Remington_Anster@triumgroup.com', 'MALE', '1996-11-06', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Grimmolby', 'Irene',
   'Irene_Grimmolby@triumgroup.com', 'FEMALE', '1990-10-07', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Capener', 'Anthea', 'Anthea_Capener@triumgroup.com',
   'FEMALE', '1998-06-14', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'McRuvie', 'Winfield',
   'Winfield_McRuvie@triumgroup.com', 'MALE', '1998-11-27', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Chamberlin', 'Stephine',
   'Stephine_Chamberlin@triumgroup.com', 'FEMALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Jaquemar', 'Starla',
   'Starla_Jaquemar@triumgroup.com', 'FEMALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Churly', 'Courtnay',
   'Courtnay_Churly@triumgroup.com', 'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Brunsen', 'Diarmid',
   'Diarmid_Brunsen@triumgroup.com', 'MALE', '1997-02-01', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Chiles', 'Sayre', 'Sayre_Chiles@triumgroup.com',
   'FEMALE', '1992-05-23', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Fanshawe', 'Ora', 'Ora_Fanshawe@triumgroup.com',
   'FEMALE', '1994-06-24', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Jeromson', 'Kareem',
   'Kareem_Jeromson@triumgroup.com', 'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'McMillan', 'Sari', 'Sari_McMillan@triumgroup.com',
   'FEMALE', '1994-06-04', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Norcliff', 'Thedrick',
   'Thedrick_Norcliff@triumgroup.com', 'MALE', '1991-03-17', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Allflatt', 'Laurent',
   'Laurent_Allflatt@triumgroup.com', 'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Cowitz', 'Pandora', 'Pandora_Cowitz@triumgroup.com',
   'FEMALE', '1993-02-06', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Arbuckel', 'Gustave',
   'Gustave_Arbuckel@triumgroup.com', 'MALE', NULL, 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Gannon', 'Hurley', 'Hurley_Gannon@triumgroup.com',
   'MALE', '1994-05-25', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Bramsen', 'Karylin',
   'Karylin_Bramsen@triumgroup.com', 'FEMALE', '1998-01-20', 'STUDENT');
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Preshaw', 'Drusi', 'Drusi_Preshaw@triumgroup.com',
   'FEMALE', '1996-05-01', 'STUDENT');

-- -------------------------------------------------------------------

INSERT INTO course (title, description, status, registration_end, max_students) VALUES
  ('Course from Avamba', 'Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.',
   'DRAFT', '2016-12-24 05:32:36', 17);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Midel',
                                                                                        'Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.',
                                                                                        'FINISHED',
                                                                                        '2016-12-20 01:00:06', 26);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Wikizz',
                                                                                        'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam. Nam tristique tortor eu pede.',
                                                                                        'PUBLISHED',
                                                                                        '2016-12-15 15:20:37', 10);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Tagtune',
                                                                                        'Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.',
                                                                                        'DRAFT', '2016-12-28 20:23:55',
                                                                                        20);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Twinder',
                                                                                        'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus.',
                                                                                        'FINISHED',
                                                                                        '2016-12-16 18:54:07', 18);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Talane',
                                                                                        'Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl.',
                                                                                        'PUBLISHED',
                                                                                        '2016-12-29 13:35:58', 15);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Photobug',
                                                                                        'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.',
                                                                                        'DRAFT', '2016-12-27 10:58:28',
                                                                                        27);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES
  ('Course from Topdrive', 'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla.',
   'FINISHED', '2017-01-08 09:33:05', 25);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Kanoodle',
                                                                                        'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus.',
                                                                                        'PUBLISHED',
                                                                                        '2017-01-01 12:02:17', 28);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES
  ('Course from Tagcat', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem.', 'DRAFT',
   '2016-12-14 04:26:45', 12);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Jetpulse',
                                                                                        'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue.',
                                                                                        'FINISHED',
                                                                                        '2016-12-21 21:44:37', 10);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Ntags',
                                                                                        'Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.',
                                                                                        'PUBLISHED',
                                                                                        '2017-01-05 11:38:41', 17);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Dabvine',
                                                                                        'Duis mattis egestas metus. Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.',
                                                                                        'DRAFT', '2016-12-22 22:22:00',
                                                                                        25);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES
  ('Course from Voonder', 'Sed ante. Vivamus tortor.', 'FINISHED', '2016-12-27 18:03:28', 24);
INSERT INTO course (title, description, status, registration_end, max_students) VALUES ('Course from Realmix',
                                                                                        'Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.',
                                                                                        'PUBLISHED',
                                                                                        '2016-12-29 22:37:00', 23);

-- -------------------------------------------------------------------

INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-03 07:18:11', '1:24:17', 2, 'target end-to-end infomediaries', 20, 'Etiam pretium iaculis justo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-18 22:07:47', '0:26:00', 3, 'harness strategic ROI', 16,
   'Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-25 01:28:10', '0:11:57', 4, 'reintermediate scalable bandwidth', 25,
   'Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-04 06:24:00', '1:02:40', 5, 'transition mission-critical communities', 12, 'Pellentesque eget nunc.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-06 13:50:22', '0:16:44', 6, 'synergize innovative relationships', 30,
   'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-10 06:47:32', '0:11:13', 7, 'mesh B2B networks', 19,
   'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-19 21:07:22', '1:33:33', 8, 'transform viral synergies', 30,
   'Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-13 17:01:17', '0:19:28', 9, 'drive next-generation convergence', 26,
   'Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-03 03:13:54', '0:35:59', 10, 'monetize 24/7 vortals', 11,
   'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-11 03:56:05', '1:54:32', 11, 'exploit global ROI', 26,
   'Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-27 20:18:39', '1:33:48', 12, 'reintermediate one-to-one bandwidth', 21,
   'Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-27 18:42:04', '1:45:00', 13, 'evolve cross-platform convergence', 30,
   'In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-05 20:02:38', '0:24:11', 14, 'engineer clicks-and-mortar schemas', 24,
   'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-21 19:22:08', '1:13:07', 15, 'visualize ubiquitous networks', 26,
   'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-03 15:37:00', '1:45:40', 1, 'scale leading-edge applications', 15,
   'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-28 17:14:24', '0:05:06', 2, 'innovate dynamic models', 17,
   'Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-01 00:10:18', '0:52:48', 3, 'engineer plug-and-play e-services', 20,
   'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-02 20:46:49', '0:45:56', 4, 'expedite open-source action-items', 22,
   'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-03 02:55:03', '0:09:16', 5, 'synthesize magnetic infrastructures', 27,
   'Suspendisse potenti. Nullam porttitor lacus at turpis.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-05 08:22:31', '1:32:05', 6, 'cultivate impactful initiatives', 13,
   'Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-27 23:13:12', '0:26:13', 7, 'incubate global paradigms', 27, 'Integer non velit.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-25 01:55:17', '0:19:58', 8, 'whiteboard world-class relationships', 26, 'Vivamus tortor.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-30 00:08:56', '1:35:19', 9, 'e-enable cutting-edge initiatives', 20,
   'Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-14 11:02:07', '0:33:35', 10, 'enhance user-centric channels', 11,
   'Nullam molestie nibh in lectus. Pellentesque at nulla.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-01 23:34:15', '0:17:54', 11, 'synthesize cross-platform methodologies', 26,
   'Nullam molestie nibh in lectus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-05 15:56:21', '0:52:35', 12, 'morph robust convergence', 19,
   'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-28 01:29:10', '0:45:40', 13, 'maximize web-enabled methodologies', 23,
   'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-05 16:46:16', '1:36:22', 14, 'optimize enterprise e-markets', 26,
   'Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-16 09:27:08', '0:55:16', 15, 'deploy interactive paradigms', 17,
   'Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-10 16:58:55', '0:40:07', 1, 'drive holistic convergence', 15, 'Praesent blandit. Nam nulla.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-13 07:10:15', '1:17:58', 2, 'leverage 24/365 convergence', 20,
   'Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-24 02:17:33', '0:43:35', 3, 'utilize bleeding-edge communities', 20,
   'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-22 12:04:43', '1:46:07', 4, 'embrace visionary partnerships', 28,
   'Nunc purus. Phasellus in felis. Donec semper sapien a libero.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-21 22:11:24', '0:44:48', 5, 'generate transparent partnerships', 17,
   'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-14 07:19:43', '1:23:16', 6, 'optimize interactive vortals', 22,
   'In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-01 10:44:22', '0:34:49', 7, 'deliver customized architectures', 14,
   'Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-03 16:00:52', '1:02:05', 8, 'aggregate viral communities', 24,
   'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-02 00:05:12', '0:01:40', 9, 'incubate interactive technologies', 25,
   'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-26 13:45:39', '1:07:33', 10, 'expedite synergistic initiatives', 12,
   'Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-23 01:28:40', '1:52:33', 11, 'exploit bricks-and-clicks initiatives', 28,
   'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-19 08:14:21', '0:45:34', 12, 'cultivate mission-critical metrics', 14,
   'Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-23 08:43:43', '1:37:40', 13, 'harness B2B architectures', 24,
   'Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-26 18:17:10', '0:02:33', 14, 'matrix enterprise content', 13,
   'In eleifend quam a odio. In hac habitasse platea dictumst.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-03 21:20:55', '0:53:36', 15, 'expedite dot-com synergies', 16,
   'Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-15 23:37:17', '1:56:12', 1, 'facilitate web-enabled methodologies', 20,
   'Duis mattis egestas metus. Aenean fermentum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-28 17:18:02', '0:56:06', 2, 'disintermediate customized partnerships', 11,
   'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-02 18:53:02', '1:35:22', 3, 'reintermediate wireless paradigms', 14,
   'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-01 21:04:43', '1:22:45', 4, 'benchmark distributed metrics', 28,
   'Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-02 18:54:29', '0:29:37', 5, 'reintermediate vertical infomediaries', 13,
   'Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-05 04:39:55', '0:55:10', 6, 'incentivize sexy eyeballs', 19, 'Aenean auctor gravida sem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-10 14:47:15', '0:33:08', 7, 'brand cross-platform niches', 17,
   'Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-25 09:47:00', '1:21:51', 8, 'syndicate interactive convergence', 21,
   'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-29 01:38:11', '0:36:55', 9, 'harness vertical communities', 22, 'Morbi a ipsum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-16 14:12:49', '0:34:28', 10, 'scale world-class functionalities', 27,
   'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-22 19:48:23', '1:20:50', 11, 'productize end-to-end platforms', 30,
   'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-25 21:43:26', '1:57:20', 12, 'drive intuitive channels', 17, 'Praesent blandit. Nam nulla.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-16 13:12:23', '1:57:48', 13, 'morph plug-and-play eyeballs', 24,
   'Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-31 14:46:58', '0:16:54', 14, 'engage scalable interfaces', 22,
   'Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-31 10:25:43', '0:13:12', 15, 'morph best-of-breed schemas', 19,
   'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-01 12:49:56', '0:39:31', 1, 'cultivate compelling web-readiness', 12,
   'Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-01 19:43:57', '0:52:36', 2, 'grow mission-critical e-business', 30,
   'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-16 03:33:06', '0:18:00', 3, 'exploit proactive channels', 11,
   'Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-12 00:07:41', '1:38:19', 4, 'transform e-business functionalities', 22,
   'Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-27 17:04:55', '1:17:50', 5, 'strategize plug-and-play web-readiness', 20, 'In congue. Etiam justo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-28 11:18:17', '1:57:01', 6, 'incentivize leading-edge ROI', 24,
   'Cras non velit nec nisi vulputate nonummy.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-13 06:50:44', '1:28:00', 7, 'cultivate holistic eyeballs', 18,
   'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-28 18:18:39', '0:04:29', 8, 'embrace turn-key markets', 16,
   'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-16 11:58:56', '1:32:07', 9, 'synthesize integrated portals', 21,
   'Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-25 20:53:12', '0:55:40', 10, 'incubate efficient channels', 17,
   'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-25 03:20:54', '0:43:57', 11, 'reintermediate dot-com infrastructures', 18,
   'Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-26 05:54:54', '1:52:54', 12, 'productize B2C applications', 13,
   'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-17 11:29:58', '1:36:28', 13, 'enhance wireless initiatives', 26, 'Aenean fermentum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-14 11:59:14', '1:31:40', 14, 'deploy bricks-and-clicks supply-chains', 24,
   'Proin risus. Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-07 12:58:29', '1:20:33', 15, 'synthesize bricks-and-clicks channels', 11,
   'Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-11 12:36:39', '1:16:14', 1, 'reinvent dynamic ROI', 18,
   'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-28 07:04:06', '1:26:29', 2, 'transition sexy eyeballs', 29, 'Nullam varius.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-31 03:09:21', '1:47:08', 3, 'revolutionize interactive content', 24, 'Fusce consequat. Nulla nisl.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-28 16:26:26', '1:01:28', 4, 'leverage magnetic schemas', 15,
   'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-11 02:39:10', '0:44:36', 5, 'transform clicks-and-mortar synergies', 23,
   'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-18 02:03:52', '0:12:38', 6, 'cultivate one-to-one communities', 27,
   'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-01 07:05:34', '1:50:24', 7, 'transition out-of-the-box channels', 21,
   'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-20 01:58:13', '1:30:49', 8, 'brand bleeding-edge action-items', 13,
   'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-25 16:43:26', '1:12:44', 9, 'unleash rich e-commerce', 16,
   'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh. In quis justo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-28 17:36:42', '1:32:13', 10, 'visualize next-generation interfaces', 29,
   'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-21 01:39:39', '1:53:28', 11, 'repurpose web-enabled e-markets', 17,
   'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-25 14:22:25', '0:38:12', 12, 'expedite e-business niches', 20,
   'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-11 07:04:56', '1:20:47', 13, 'aggregate 24/7 markets', 29,
   'Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-06 13:52:43', '1:45:43', 14, 'streamline B2B e-commerce', 19,
   'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-26 04:23:33', '1:42:36', 15, 'utilize synergistic eyeballs', 14,
   'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-22 03:40:45', '0:06:39', 1, 'envisioneer visionary methodologies', 21,
   'Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-21 10:42:42', '0:16:07', 2, 'enhance cross-media networks', 21,
   'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-23 20:20:10', '1:56:09', 3, 'iterate integrated action-items', 28,
   'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-01 00:37:13', '0:56:22', 4, 'optimize wireless technologies', 23,
   'Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-26 22:56:37', '1:45:41', 5, 'seize distributed communities', 15,
   'Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-03 08:34:20', '0:03:22', 6, 'strategize virtual communities', 27,
   'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-10 23:43:23', '0:15:59', 7, 'deliver open-source content', 21,
   'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-05 15:14:24', '1:45:11', 8, 'whiteboard wireless action-items', 24, 'Quisque porta volutpat erat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-07 21:55:41', '1:22:00', 9, 'harness impactful eyeballs', 17,
   'Curabitur at ipsum ac tellus semper interdum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-25 19:09:49', '1:02:03', 10, 'expedite B2B architectures', 19,
   'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-20 16:52:40', '1:59:54', 11, 'repurpose scalable synergies', 27,
   'Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-12 04:57:03', '0:03:01', 12, 'recontextualize ubiquitous interfaces', 20,
   'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-12 02:42:27', '0:27:25', 13, 'transition frictionless metrics', 28,
   'Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-01 19:34:28', '0:49:01', 14, 'disintermediate revolutionary supply-chains', 17,
   'Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-16 04:55:29', '0:51:15', 15, 'grow synergistic infrastructures', 20,
   'Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-25 01:17:01', '0:47:04', 1, 'syndicate mission-critical content', 22,
   'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus. Pellentesque at nulla.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-16 15:23:12', '0:18:36', 2, 'synergize scalable models', 25,
   'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-29 09:40:21', '1:14:32', 3, 'deploy plug-and-play e-commerce', 17,
   'Nulla mollis molestie lorem. Quisque ut erat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-20 05:44:59', '1:36:44', 4, 'morph plug-and-play e-business', 15,
   'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-08 04:07:43', '1:08:07', 5, 'scale scalable systems', 23,
   'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-19 02:34:49', '0:22:39', 6, 'monetize value-added systems', 29,
   'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-09 04:28:40', '0:02:51', 7, 'transition cutting-edge experiences', 29,
   'In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-22 15:20:45', '1:07:03', 8, 'streamline bricks-and-clicks e-services', 17,
   'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-08 21:29:41', '1:14:55', 9, 'leverage turn-key vortals', 19,
   'Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-25 06:37:10', '1:55:56', 10, 'visualize 24/7 functionalities', 15,
   'Nulla ut erat id mauris vulputate elementum. Nullam varius.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-16 06:03:28', '1:38:42', 11, 'architect world-class models', 13,
   'Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-02 08:57:48', '0:00:22', 12, 'architect impactful interfaces', 21,
   'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-02 06:51:24', '1:13:33', 13, 'productize rich technologies', 14,
   'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-20 17:47:43', '0:08:54', 14, 'orchestrate viral infomediaries', 11,
   'Nunc purus. Phasellus in felis. Donec semper sapien a libero.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-15 22:11:11', '1:38:36', 15, 'implement turn-key web services', 14,
   'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-11 19:22:20', '1:29:30', 1, 'incentivize 24/7 web services', 23,
   'Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-16 10:53:13', '0:39:06', 2, 'target best-of-breed web services', 26,
   'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-14 03:47:59', '1:54:58', 3, 'incentivize dot-com portals', 21,
   'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem. Sed sagittis.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-07 01:49:12', '1:37:41', 4, 'evolve mission-critical vortals', 16,
   'Phasellus in felis. Donec semper sapien a libero.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-10 00:09:45', '1:43:39', 5, 'benchmark turn-key niches', 30,
   'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-06 19:16:13', '1:51:57', 6, 'maximize wireless e-tailers', 20,
   'Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-21 01:54:08', '0:55:44', 7, 'maximize 24/365 action-items', 17,
   'Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-15 13:49:29', '0:38:40', 8, 'iterate world-class niches', 21,
   'Suspendisse potenti. Cras in purus eu magna vulputate luctus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-21 13:01:26', '0:52:23', 9, 'innovate mission-critical e-business', 29, 'Nullam varius.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-30 15:36:51', '1:58:39', 10, 'disintermediate value-added paradigms', 22,
   'Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-07 15:22:38', '0:51:03', 11, 'enable ubiquitous solutions', 17,
   'Integer ac leo. Pellentesque ultrices mattis odio.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-18 15:09:57', '0:29:34', 12, 'brand real-time systems', 20,
   'Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-23 23:17:20', '0:08:30', 13, 'facilitate 24/7 e-services', 18,
   'Duis bibendum. Morbi non quam nec dui luctus rutrum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-25 18:27:15', '0:46:43', 14, 'facilitate B2B convergence', 28, 'In hac habitasse platea dictumst.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-29 12:50:04', '0:49:33', 15, 'extend end-to-end web services', 17,
   'Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-23 01:58:13', '1:47:03', 1, 'integrate plug-and-play web-readiness', 16,
   'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-03 14:32:36', '1:33:06', 2, 'exploit dot-com web-readiness', 15, 'Morbi non quam nec dui luctus rutrum.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-17 01:00:54', '0:27:43', 3, 'generate web-enabled interfaces', 26,
   'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-19 12:37:42', '0:58:36', 4, 'exploit out-of-the-box infomediaries', 28,
   'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-23 08:26:33', '0:18:33', 5, 'seize next-generation synergies', 20,
   'Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-20 07:29:33', '1:53:26', 6, 'empower mission-critical methodologies', 27,
   'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-27 05:31:06', '0:02:34', 7, 'synergize clicks-and-mortar content', 18,
   'Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-12 10:01:46', '0:42:56', 8, 'engineer sticky niches', 26,
   'Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-05 22:51:53', '1:51:31', 9, 'disintermediate compelling infomediaries', 12,
   'Nunc purus. Phasellus in felis. Donec semper sapien a libero.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-03-29 08:20:00', '0:23:47', 10, 'incentivize rich deliverables', 30, 'Proin risus.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-11 03:12:11', '0:23:07', 11, 'reintermediate virtual models', 27,
   'Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-11 23:27:18', '1:34:25', 12, 'unleash vertical action-items', 22,
   'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-02-14 03:15:55', '1:57:20', 13, 'deploy distributed channels', 11,
   'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-21 23:17:32', '0:36:56', 14, 'revolutionize integrated eyeballs', 26,
   'Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-06-05 18:19:22', '0:02:53', 15, 'whiteboard transparent eyeballs', 15,
   'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.');
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-04-04 20:51:28', '0:53:56', 1, 'strategize wireless paradigms', 13, 'Pellentesque eget nunc.');

-- -------------------------------------------------------------------

INSERT INTO course_student (course_id, student_id) VALUES (2, 32);
INSERT INTO course_student (course_id, student_id) VALUES (3, 33);
INSERT INTO course_student (course_id, student_id) VALUES (4, 34);
INSERT INTO course_student (course_id, student_id) VALUES (5, 35);
INSERT INTO course_student (course_id, student_id) VALUES (6, 36);
INSERT INTO course_student (course_id, student_id) VALUES (7, 37);
INSERT INTO course_student (course_id, student_id) VALUES (8, 38);
INSERT INTO course_student (course_id, student_id) VALUES (9, 39);
INSERT INTO course_student (course_id, student_id) VALUES (10, 40);
INSERT INTO course_student (course_id, student_id) VALUES (1, 41);
INSERT INTO course_student (course_id, student_id) VALUES (2, 42);
INSERT INTO course_student (course_id, student_id) VALUES (3, 43);
INSERT INTO course_student (course_id, student_id) VALUES (4, 44);
INSERT INTO course_student (course_id, student_id) VALUES (5, 45);
INSERT INTO course_student (course_id, student_id) VALUES (6, 46);
INSERT INTO course_student (course_id, student_id) VALUES (7, 47);
INSERT INTO course_student (course_id, student_id) VALUES (8, 48);
INSERT INTO course_student (course_id, student_id) VALUES (9, 49);
INSERT INTO course_student (course_id, student_id) VALUES (10, 50);
INSERT INTO course_student (course_id, student_id) VALUES (1, 51);
INSERT INTO course_student (course_id, student_id) VALUES (2, 52);
INSERT INTO course_student (course_id, student_id) VALUES (3, 53);
INSERT INTO course_student (course_id, student_id) VALUES (4, 54);
INSERT INTO course_student (course_id, student_id) VALUES (5, 55);
INSERT INTO course_student (course_id, student_id) VALUES (6, 56);
INSERT INTO course_student (course_id, student_id) VALUES (7, 57);
INSERT INTO course_student (course_id, student_id) VALUES (8, 58);
INSERT INTO course_student (course_id, student_id) VALUES (9, 59);
INSERT INTO course_student (course_id, student_id) VALUES (10, 60);
INSERT INTO course_student (course_id, student_id) VALUES (1, 61);
INSERT INTO course_student (course_id, student_id) VALUES (2, 62);
INSERT INTO course_student (course_id, student_id) VALUES (3, 63);
INSERT INTO course_student (course_id, student_id) VALUES (4, 64);
INSERT INTO course_student (course_id, student_id) VALUES (5, 65);
INSERT INTO course_student (course_id, student_id) VALUES (6, 66);
INSERT INTO course_student (course_id, student_id) VALUES (7, 67);
INSERT INTO course_student (course_id, student_id) VALUES (8, 68);
INSERT INTO course_student (course_id, student_id) VALUES (9, 69);
INSERT INTO course_student (course_id, student_id) VALUES (10, 70);
INSERT INTO course_student (course_id, student_id) VALUES (1, 31);

