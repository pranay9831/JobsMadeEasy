CREATE SCHEMA IF NOT EXISTS `CSCI5308_3_DEVINT` DEFAULT CHARACTER SET utf8mb4;
USE `CSCI5308_3_DEVINT`;

CREATE TABLE IF NOT EXISTS `CSCI5308_3_DEVINT`.`role`
(
    `roleId`   INT NOT NULL,
    `roleName` VARCHAR(45) NULL,
    PRIMARY KEY (`roleId`)
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `CSCI5308_3_DEVINT`.`user`
(
    `userId`       INT NOT NULL,
    `firstName`   VARCHAR(45) NULL,
    `lastName`    VARCHAR(45) NULL,
    `phoneNumber` VARCHAR(45) NULL,
    `emailId`     VARCHAR(45) NULL,
    `password`    VARCHAR(45) NULL,
    `city`        VARCHAR(45) NULL,
    `province`    VARCHAR(45) NULL,
    `address`     VARCHAR(45) NULL,
    `postalCode`  VARCHAR(45) NULL,
    `roleId`      INT         NULL DEFAULT 1 ,
    `isEmployee`  TINYINT     NULL,
    `isApproved`  TINYINT     NULL,
    PRIMARY KEY (`userId`),
    INDEX `roleId_idx` (`roleId` ASC),
    CONSTRAINT `roleId`
        FOREIGN KEY (`roleId`)
            REFERENCES `CSCI5308_3_DEVINT`.`role` (`roleId`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;
