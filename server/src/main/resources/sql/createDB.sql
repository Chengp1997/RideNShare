drop table if exists `area`;
create table area(
	`id` int(11) not null auto_increment comment 'area id',
	`name` varchar(100) not null comment 'area name',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (`id`)
) engine=InnoDB auto_increment=1;

drop table if exists `area_spot_map`;
create table area_spot_map(
	`area_id` int(11) not null comment 'area id',
	`spot_id` int(11) not null comment 'spot id',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (`area_id`, `spot_id`)
) engine=InnoDB;

drop table if exists `spot`;
create table `spot`(
	`id` int(11) not null auto_increment comment 'spot id',
	`location` varchar(100) not null comment 'spot location',
    `longitude` decimal(10,6) not null,
    `latitude` decimal(10,6) not null,
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (`id`)
) engine=InnoDB auto_increment=1;

drop table if exists `post`;
create table post(
	`id` int(11) not null auto_increment comment 'post id',
	`create_time` datetime not null default current_timestamp,
    `update_time` datetime not null default current_timestamp on update current_timestamp,
    `start_time` datetime comment 'preferred start time',
    `end_time` datetime comment 'preferred end time',
    `spot_id_start` int(11) not null comment 'start spot id',
	`spot_id_end` int(11) not null comment 'end spot id',
    `price` decimal(20, 2) not null default '0.00',
    `user_id` int(11) not null,
	`status` int(5) not null default '0' comment '0: in progress, 1: completed, 2: cancelled',
    `type` int(5) not null comment '0: driver, 1: passenger',
    primary key (`id`)
) engine=InnoDB auto_increment=1;

drop table if exists `post_spot_map`;
CREATE TABLE `post_spot_map` (
    `post_id` int(11) not null comment 'post id',
    `spot_id` int(11) not null comment 'spot id',
    `start_flag` TINYINT(1) not null comment '0: start spot, 1: end spot',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (`post_id`, `spot_id`)
) engine=InnoDB;

drop table if exists `order`;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'order id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime not null default current_timestamp on update current_timestamp,
  `driver_id` int NOT NULL COMMENT 'driver id',
  `passenger_id` int NOT NULL COMMENT 'passenger id',
  `post_id` int NOT NULL COMMENT 'post id',
  `price` decimal(20,2) NOT NULL DEFAULT '0.00',
  `status` int NOT NULL DEFAULT '0' COMMENT '0: in progress, 1: completed, 2: cancelled',
  `chat_id` int NOT NULL COMMENT 'chat id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB auto_increment=1;

drop table if exists `room`;
create table `room`(
	`id` int(11) not null auto_increment comment 'room id',
    `roomName` int(11) not null comment 'room name',
    `user1_id` int(11) not null comment 'user1 id',
    `user1_name` text NOT NULL comment 'user1 name',
    `user2_id` int(11) not null comment 'another user id',
    `user2_name` text NOT NULL comment 'another user name',
    primary key (`id`)
) engine = InnoDB auto_increment=1;

drop table if exists `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'message id',
  `room_id` int(11) NOT NULL COMMENT 'room id',
  `sender_id` int(11) NOT NULL COMMENT 'sender id',
  `content` text NOT NULL,
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` int(6) NOT NULL DEFAULT '1' COMMENT '0:no 1:yes',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB auto_increment=1;

drop table if exists `car_info`;
CREATE TABLE `car_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'car id',
  `plate_no` varchar(50) NOT NULL COMMENT 'plate no',
  `user_id` int(11) NOT NULL COMMENT 'user id',
  `car_type` varchar(50) comment 'Toyota Highlander SUV',
  `capacity` int(4),
  `years` int(4),
  `image` TEXT comment 'image url',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB auto_increment=1;

drop table if exists `user`;
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
    `car_id` int(11) COMMENT 'car id',
    `username` varchar(50) NOT NULL DEFAULT 'new user',
    `password` varchar(100) NOT NULL,
    `email` varchar(50) NOT NULL,
    `phone` varchar(20) DEFAULT NULL,
    `question` varchar(100) DEFAULT NULL COMMENT 'forget password question',
    `answer` varchar(100) DEFAULT NULL COMMENT 'forget password answer',
    `activate_code` varchar(10) NOT NULL COMMENT 'activation code',
    `activate_status` int(1) NOT NULL COMMENT '0: not activated, 1: activated',
    `expire_time` datetime NOT NULL COMMENT 'activate code expire time',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS SPRING_SESSION;
CREATE TABLE SPRING_SESSION (
                                PRIMARY_ID CHAR(36) NOT NULL,
                                SESSION_ID CHAR(36) NOT NULL,
                                CREATION_TIME BIGINT NOT NULL,
                                LAST_ACCESS_TIME BIGINT NOT NULL,
                                MAX_INACTIVE_INTERVAL INT NOT NULL,
                                EXPIRY_TIME BIGINT NOT NULL,
                                PRINCIPAL_NAME VARCHAR(100),
                                CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=INNODB ROW_FORMAT=DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

DROP TABLE IF EXISTS SPRING_SESSION_ATTRIBUTES;
CREATE TABLE SPRING_SESSION_ATTRIBUTES (
                                           SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                           ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                           ATTRIBUTE_BYTES BLOB NOT NULL,
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
) ENGINE=INNODB ROW_FORMAT=DYNAMIC;

