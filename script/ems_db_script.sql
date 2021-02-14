-- SOURCE /Users/khannosa/Desktop/ems/script/ems_db_script.sql


DROP SCHEMA IF EXISTS `db_employee`;
CREATE SCHEMA `db_employee`;
use `db_employee`;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
                            `id` varchar(64) NOT NULL,
                            `first_name` varchar(30) NOT NULL,
                            `last_name` varchar(30) NOT NULL,
                            `dob` datetime NOT NULL,
                            `work_position_id` varchar(50) NULL,
                            `department_id` varchar(50) NULL,
                            `office_id` varchar(50) NULL,
                            `cv` varchar(1111) NULL,
                            `salary` decimal(14, 2) NOT NULL,
                            `supervisor_id` varchar(50) NULL REFERENCES `employee`(`id`),
                            `is_active` bit NOT NULL,
                            `available_leave_days_per_year` int NOT NULL,
                            `available_sick_days_per_year` int NOT NULL,
                            PRIMARY KEY (`id`),
                            CONSTRAINT `FK_DEPARTMENT` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                            CONSTRAINT `FK_OFFICE` FOREIGN KEY (`office_id`) REFERENCES `office` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                            CONSTRAINT `FK_EMPLOYEE_TYPE` FOREIGN KEY (`work_position_id`) REFERENCES `employee_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                            CONSTRAINT `FK_MANAGER` FOREIGN KEY (`supervisor_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
                              `id` varchar(50) NOT NULL,
                              `name` varchar(50) NOT NULL,
                              `description` varchar(500) NOT NULL,
                              PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
                           `id` varchar(50) NOT NULL,
                           `name` varchar(50) NOT NULL,
                           `description` varchar(500) NOT NULL,
                           PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employee_type`;
CREATE TABLE `employee_type` (
                                 `id` varchar(50) NOT NULL,
                                 `name` varchar(50) NOT NULL,
                                 `description` varchar(500) NOT NULL,
                                 PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `office`;
CREATE TABLE `office` (
                          `id` varchar(50) NOT NULL,
                          `name` varchar(50) NOT NULL,
                          `capacity` int(15) NOT NULL,
                          PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employee_project`;
CREATE TABLE `employee_project` (
                                    `project_id` varchar(50) NOT NULL,
                                    `employee_id` varchar(50) NOT NULL,
                                    PRIMARY KEY (`project_id`, `employee_id`),
                                    CONSTRAINT `FK_EMPLOYEE-MTM` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                    CONSTRAINT `FK_PROJECT-MTM` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



