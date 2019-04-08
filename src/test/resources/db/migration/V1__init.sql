CREATE SCHEMA IF NOT EXISTS "SHOPPINGLIST";

CREATE TABLE IF NOT EXISTS `shoppinglist`.`product_category`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(255) NOT NULL,
    `parentId` INT          NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `shoppinglist`.`product`
(
    `id`                  INT          NOT NULL AUTO_INCREMENT,
    `name`                VARCHAR(255) NOT NULL,
    `ean`                 VARCHAR(255) NULL,
    `product_category_id` INT          NOT NULL,
    PRIMARY KEY (`id`, `product_category_id`),
    INDEX `fk_product_product_category1_idx` (`product_category_id` ASC),
    UNIQUE INDEX `ean_UNIQUE` (`ean` ASC),
    CONSTRAINT `fk_product_product_category1`
        FOREIGN KEY (`product_category_id`)
            REFERENCES `shoppinglist`.`product_category` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `shoppinglist`.`shoppinglist`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `date` VARCHAR(45)  NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `shoppinglist`.`shoppinglist_has_product`
(
    `shoppinglist_id` INT NOT NULL,
    `product_id`      INT NOT NULL,
    PRIMARY KEY (`shoppinglist_id`, `product_id`),
    INDEX `fk_shoppinglist_has_product_product1_idx` (`product_id` ASC),
    INDEX `fk_shoppinglist_has_product_shoppinglist_idx` (`shoppinglist_id` ASC),
    CONSTRAINT `fk_shoppinglist_has_product_shoppinglist`
        FOREIGN KEY (`shoppinglist_id`)
            REFERENCES `shoppinglist`.`shoppinglist` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_shoppinglist_has_product_product1`
        FOREIGN KEY (`product_id`)
            REFERENCES `shoppinglist`.`product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;
