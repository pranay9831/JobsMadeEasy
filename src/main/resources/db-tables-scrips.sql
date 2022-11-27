DROP TABLE IF EXISTS applicant;
CREATE TABLE `csci_5308`.`applicant` (
                                    `applicantId` INT NOT NULL,
                                    `firstName` VARCHAR(45) NOT NULL,
                                    `lastName` VARCHAR(45) NOT NULL,
                                    `phoneNumber` VARCHAR(45) NOT NULL,
                                    `emailId` VARCHAR(45) NOT NULL,
                                    `password` VARCHAR(45) NOT NULL,
                                    `city` VARCHAR(45) NOT NULL,
                                    `province` VARCHAR(45) NOT NULL,
                                    `address` VARCHAR(45) NOT NULL,
                                    `postalCode` VARCHAR(45) NOT NULL,
                                    `applicantStatus` TINYINT NOT NULL DEFAULT TRUE,
                                    PRIMARY KEY (`applicantId`));