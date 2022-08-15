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
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) DEFAULT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `image_url`   VARCHAR(255) DEFAULT NULL,

    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;


create table if not exists `xuebingdu`.`customer`
(
    `id`         bigint(20)   not null auto_increment,
    `first_name` varchar(255) not null,
    `last_name`  varchar(255) not null,
    `email`      varchar(255) not null,

    primary key (`id`)
);

create table if not exists `xuebingdu`.`order`
(
    `id`              bigint(20)   not null auto_increment,
    `tracking_number` varchar(255) not null,
    `customer_id`     bigint(20)   not null,
    `date_placed`     datetime(6)  not null,
    `total_price`     decimal(19, 2) DEFAULT NULL,
    `total_quantity`  int            default null,
    `is_shipped`      bit            default 0,
    primary key (`id`)
);

create table if not exists `xuebingdu`.`order_item`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `image_url`  varchar(255)   DEFAULT NULL,
    `quantity`   int            DEFAULT NULL,
    `unit_price` decimal(19, 2) DEFAULT NULL,
    `order_id`   bigint         DEFAULT NULL,
    `name`       varchar(255)   default null,
    `size`       varchar(255)   default null,
    PRIMARY KEY (`id`)
);