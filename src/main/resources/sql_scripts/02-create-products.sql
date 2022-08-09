-- -----------------------------------------------------
-- Schema xuebingdu
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `xuebingdu`;

CREATE SCHEMA `xuebingdu`;
USE `xuebingdu`;

-- -----------------------------------------------------
-- Table `xuebingdu`.`product_size`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xuebingdu`.`product_size`
(
    `id`             BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `product`        VARCHAR(255) NULL DEFAULT NULL,
    `size`           VARCHAR(255)      DEFAULT NULL,
    `unit_price`     decimal(19, 2)    DEFAULT NULL,
    `active`         BIT               DEFAULT 1,
    `units_in_stock` INT(11)           DEFAULT NULL,
    `date_created`   DATETIME(6)       DEFAULT NULL,
    `last_updated`   DATETIME(6)       DEFAULT NULL,

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `xuebingdu`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xuebingdu`.`product`
(
    `id`           BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(255) DEFAULT NULL,
    `description`  VARCHAR(255) DEFAULT NULL,
    `image_url`    VARCHAR(255) DEFAULT NULL,

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;


