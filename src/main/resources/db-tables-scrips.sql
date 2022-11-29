DROP TABLE IF EXISTS applicant;
CREATE TABLE `CSCI5308_3_DEVINT`.`applicant` (
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
DROP TABLE IF EXISTS employee;
CREATE TABLE `CSCI5308_3_DEVINT`.`employee` (
                                        `employeeId` INT NOT NULL,
                                        `firstName` VARCHAR(45) NULL,
                                        `lastName` VARCHAR(45) NULL,
                                        `phoneNumber` VARCHAR(45) NULL,
                                        `emailId` VARCHAR(45) NULL,
                                        `password` VARCHAR(45) NULL,
                                        `role` VARCHAR(45) NULL,
                                        `joinDate` VARCHAR(45) NULL,
                                        `city` VARCHAR(45) NULL,
                                        `province` VARCHAR(45) NULL,
                                        `address` VARCHAR(45) NULL,
                                        `postalCode` VARCHAR(45) NULL,
                                        `employeeStatus` TINYINT NULL,
                                        PRIMARY KEY (`employeeId`));