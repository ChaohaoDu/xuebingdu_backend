-- -----------------------------------------------------
-- Schema full-stack-ecommerce
-- -----------------------------------------------------

USE `xuebingdu`;

--
-- Prep work
--
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `order_item`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `address`;
SET FOREIGN_KEY_CHECKS = 1;

--
-- Table structure for table `address`
--
CREATE TABLE `address`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `street1`  varchar(255) DEFAULT NULL,
    `street2`  varchar(255) DEFAULT NULL,
    `city`     varchar(255) DEFAULT NULL,
    `state`    varchar(255) DEFAULT NULL,
    `country`  varchar(255) DEFAULT NULL,
    `zip_code` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `customer`
--
CREATE TABLE `customer`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    `email`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `orders`
--
CREATE TABLE `orders`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT,
    `order_tracking_number` varchar(255)   DEFAULT NULL,
    `product_total_price`   decimal(19, 2) DEFAULT NULL,
    `shipping`              decimal(19, 2) DEFAULT NULL,
    `total_quantity`        int            DEFAULT NULL,
    `address_id`            bigint         DEFAULT NULL,
    `customer_id`           bigint         DEFAULT NULL,
    `status`                varchar(128)   DEFAULT NULL,
    `date_created`          datetime(6)    DEFAULT NULL,
    `last_updated`          datetime(6)    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `order_items`
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
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;
