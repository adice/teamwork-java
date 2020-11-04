show tables;

DROP TABLE IF EXISTS `sta_status_dictionary`;
DROP TABLE IF EXISTS `sta_staff`;
DROP TABLE IF EXISTS `sta_staff_detail`;
DROP TABLE IF EXISTS `aut_authority`;
DROP TABLE IF EXISTS `aut_role`;
DROP TABLE IF EXISTS `dep_department`;

CREATE TABLE `sta_status_dictionary` (
    `status_id` int(11) NOT NULL AUTO_INCREMENT,
    `status_name` varchar (10) NOT NULL,
    `status_info` varchar (255) DEFAULT NULL,
    PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sta_staff` (
    `id` varchar(30) NOT NULL,
    `name` varchar (10) NOT NULL,
    `password` varchar (100) NOT NULL,
    `create_time` timestamp NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sta_staff_detail` (
    `staff_id` varchar(30) NOT NULL,
    `staff_name` varchar(10) NOT NULL,
    `staff_sex` varchar(5) DEFAULT NULL,
    `staff_address` varchar(255) DEFAULT NULL,
    `create_time` timestamp NOT NULL,
    PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aut_authority` (
    `authority_id` int(11) NOT NULL AUTO_INCREMENT,
    `authority_name` varchar(30) NOT NULL,
    `authority_info` varchar(255) DEFAULT NULL,
    `authority_status` tinyint NOT NULL,
    `create_time` timestamp NOT NULL,
    PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aut_role` (
    `role_id` int(11) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(30) NOT NULL,
    `role_status` tinyint NOT NULL,
    `create_time` timestamp NOT NULL,
    PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dep_department` (
    `department_id` int(11) NOT NULL AUTO_INCREMENT,
    `department_name` varchar(30) NOT NULL,
    `create_time` timestamp NOT NULL,
    `department_info` varchar(255) DEFAULT NULL,
    `department_status` tinyint NOT NULL,
    PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;