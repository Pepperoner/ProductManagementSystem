DROP DATABASE IF EXISTS productManagementDB2;
CREATE DATABASE IF NOT EXISTS productManagementDB2
  DEFAULT CHARACTER SET utf8;
USE productManagementDB2;

-- products
CREATE TABLE IF NOT EXISTS products (
  id           INT UNSIGNED NOT NULL AUTO_INCREMENT,
  title        VARCHAR(300) NOT NULL DEFAULT '',
  manufacturer VARCHAR(300) NOT NULL DEFAULT '',
  description  TEXT         NOT NULL,
  cost         INT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

-- users
CREATE TABLE IF NOT EXISTS users (
  id       INT UNSIGNED           NOT NULL  AUTO_INCREMENT,
  username VARCHAR(300)           NOT NULL  DEFAULT '',
  password VARCHAR(300)           NOT NULL  DEFAULT '',
  role     ENUM ('ADMIN', 'USER') NOT NULL  DEFAULT 'USER',
  locked   BOOLEAN                NOT NULL  DEFAULT FALSE,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;