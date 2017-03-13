CREATE DATABASE IF NOT EXISTS `recourse`
  DEFAULT CHARACTER SET utf8;
USE `recourse`;

CREATE USER IF NOT EXISTS 'test'@'localhost'
  IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `recourse`.* TO 'test'@'localhost';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`            INT(11)                 NOT NULL                  AUTO_INCREMENT,
  `email`         VARCHAR(255)            NOT NULL,
  `password_hash` CHAR(60)                NOT NULL,
  `surname`       VARCHAR(50)             NOT NULL,
  `name`          VARCHAR(50)             NOT NULL,
  `gender`        ENUM ('MALE', 'FEMALE') NOT NULL,
  `birthday`      DATE                                              DEFAULT NULL,
  `role`          ENUM ('GUEST', 'STUDENT', 'TEACHER', 'ORGANIZER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_name` (`name`, `surname`),
  UNIQUE KEY `email_UNIQUE` (`email`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


INSERT INTO `user` (email, password_hash, name, surname, gender, birthday, role) VALUES
  ('Ivan_Shimko@triumgroup.com',
   '$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', /* 12345 */
   'Ivan', 'Shimko', 'male', NULL, 'organizer'),
  ('Andrey_Tatarenko@triumgroup.com',
   '$2a$12$eI7e3tSsXOBx5ZDUxY4gAeZQTu7yxKH7fGmPrVindUoJPDz4Cp03G', /* 54321 */
   'Andrey', 'Tatarenko', 'male', NULL, 'student'),
  ('Mikhail_Snitavets@triumgroup.com',
   '$2a$12$dhZ.l.x3RwbAYSMTOS.ERuRlRy6ikeSVIkdDPbD20uGrP08WmmZRe', /* 09876 */
   'Mikhail', 'Snitavets', 'male', NULL, 'guest')

