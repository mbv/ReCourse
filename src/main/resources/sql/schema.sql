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
  `is_deleted`    BOOL                    NOT NULL                  DEFAULT FALSE,
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
   'Mikhail', 'Snitavets', 'male', NULL, 'guest');


DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id`          VARCHAR(256) DEFAULT NULL,
  `token`             BLOB,
  `authentication_id` VARCHAR(256) DEFAULT NULL,
  `user_name`         VARCHAR(256) DEFAULT NULL,
  `client_id`         VARCHAR(256) DEFAULT NULL,
  `authentication`    BLOB,
  `refresh_token`     VARCHAR(256) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id`               VARCHAR(256) NOT NULL,
  `resource_ids`            VARCHAR(256)  DEFAULT NULL,
  `client_secret`           VARCHAR(256)  DEFAULT NULL,
  `scope`                   VARCHAR(256)  DEFAULT NULL,
  `authorized_grant_types`  VARCHAR(256)  DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(256)  DEFAULT NULL,
  `authorities`             VARCHAR(256)  DEFAULT NULL,
  `access_token_validity`   INT(11)       DEFAULT NULL,
  `refresh_token_validity`  INT(11)       DEFAULT NULL,
  `additional_information`  VARCHAR(4096) DEFAULT NULL,
  `autoapprove`             VARCHAR(256)  DEFAULT NULL,
  PRIMARY KEY (`client_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code`           VARCHAR(256) DEFAULT NULL,
  `authentication` BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id`       VARCHAR(256) DEFAULT NULL,
  `token`          BLOB,
  `authentication` BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `oauth_client_details` VALUES
  ('mobile',
    NULL,
    'appname',
    'read,write,trust',
    'password,authorization_code,refresh_token,implicit',
    '',
    'ROLE_USER',
    NULL,
    NULL,
    NULL,
    NULL);