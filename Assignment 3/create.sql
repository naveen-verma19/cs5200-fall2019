use `cs5200_jdbc`;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS module;

DROP TABLE IF EXISTS widget;
DROP TABLE IF EXISTS widget_type;
DROP TABLE IF EXISTS page_priviledge;
DROP TABLE IF EXISTS page_role;
DROP TABLE IF EXISTS page;
DROP TABLE IF EXISTS website_priviledge;
DROP TABLE IF EXISTS website_role;
DROP TABLE IF EXISTS website;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS priviledge;
DROP TABLE IF EXISTS developer;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS person;

CREATE TABLE `person` (
    `id` INT(10) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50),
    `last_name` VARCHAR(50) NOT NULL,
    `username` VARCHAR(50),
    `password` VARCHAR(50),
    `email` VARCHAR(50),
    `dob` DATE,
    PRIMARY KEY (`id`)
);

CREATE TABLE `phone`(
`id` INT(10) NOT NULL AUTO_INCREMENT,
`phone` VARCHAR(20) NOT NULL,
`primary` BOOLEAN,
`person_id` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `phone_person_composition` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `address` (
    `id` INT(10) NOT NULL AUTO_INCREMENT,
    `street1` VARCHAR(50) NOT NULL,
    `street2` VARCHAR(50),
    `city` VARCHAR(20) NOT NULL,
    `state` VARCHAR(20) NOT NULL,
    `zip` VARCHAR(10) NOT NULL,
    `primary` BOOLEAN,
    `person_id` INT(10) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `address_person_composition` FOREIGN KEY (`person_id`)
        REFERENCES `person` (`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `user`(
`id` INT(10) NOT NULL,
`user_aggrement` BOOLEAN,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `developer`(
`id` INT(10) NOT NULL,
`developer_key` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `developer_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `priviledge`(
`name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `role`(
`name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`name`)
);
CREATE TABLE `website` (
    `id` INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `description` VARCHAR(500),
    `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `visits` INT(20),
    PRIMARY KEY (`id`)
);

CREATE TABLE `website_role`(
`developer_id` INT(10) NOT NULL,
`website_id` INT(10) NOT NULL,
`role` VARCHAR(10) NOT NULL,

PRIMARY KEY (developer_id,website_id),
  CONSTRAINT `website_role_developer_FK` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `website_role_website_FK` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `website_role_role_FK` FOREIGN KEY (`role`) REFERENCES `role` (`name`)  ON DELETE CASCADE
);

CREATE TABLE website_priviledge(
`developer_id` INT(10) NOT NULL,
`website_id` INT(10) NOT NULL,
`priviledge` VARCHAR(10) NOT NULL,

PRIMARY KEY (developer_id,website_id,priviledge),
  CONSTRAINT `website_priviledge_developer_FK` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `website_priviledge_website_FK` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `website_priviledge_priviledge_FK` FOREIGN KEY (`priviledge`) REFERENCES `priviledge` (`name`)  ON DELETE CASCADE
);

CREATE TABLE `page` (
    `id` INT(10) NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(50) NOT NULL,
    `description` VARCHAR(500),
    `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `views` INT(20),
    `website_id` INT(10) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `page_website_FK` FOREIGN KEY (`website_id`)
        REFERENCES `website` (`id`)
        ON DELETE CASCADE
);

CREATE TABLE page_role(
`developer_id` INT(10) NOT NULL,
`page_id` INT(10) NOT NULL,
`role` VARCHAR(10) NOT NULL,

PRIMARY KEY (page_id, developer_id),
  CONSTRAINT `page_role_developer_FK` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `page_role_page_FK` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `page_role_role_FK` FOREIGN KEY (`role`) REFERENCES `role` (`name`)  ON DELETE CASCADE
);

CREATE TABLE page_priviledge(
`developer_id` INT(10) NOT NULL,
`page_id` INT(10) NOT NULL,
`priviledge` VARCHAR(10) NOT NULL,

PRIMARY KEY (page_id, developer_id,priviledge),
  CONSTRAINT `page_priviledge_developer_FK` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `page_priviledge_page_FK` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `page_priviledge_priviledge_FK` FOREIGN KEY (`priviledge`) REFERENCES `priviledge` (`name`)  ON DELETE CASCADE
);

CREATE TABLE `widget_type`(
`name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `widget` (
    `id` INT(10) NOT NULL AUTO_INCREMENT,
    `dtype` VARCHAR(20) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `width` INT(10),
    `height` INT(10),
    `css_class` VARCHAR(1000),
    `css_style` VARCHAR(1000),
    `text` VARCHAR(1000),
    `order` INT(10),
    `image_src` VARCHAR(50) DEFAULT NULL,
    `youtube_url` VARCHAR(50) DEFAULT NULL,
    `youtube_shareable` BOOLEAN DEFAULT NULL,
    `youtube_expandable` BOOLEAN DEFAULT NULL,
    `heading_size` INT(10) DEFAULT 2,
    `html` VARCHAR(1000) DEFAULT NULL,
    `page_id` INT(10) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `widget_page_FK` FOREIGN KEY (`page_id`)
        REFERENCES `page` (`id`)
        ON DELETE CASCADE,
    CONSTRAINT `widget_page_widget_type_FK` FOREIGN KEY (`dtype`)
        REFERENCES `widget_type` (`name`)
        ON DELETE CASCADE
);


CREATE TABLE module(
    name varchar(246),
    PRIMARY KEY(name)
);

CREATE TABLE question(
    id int(15) NOT NULL,
    text varchar(234),
    askedBy int(15) NOT NULL,
    postedOn date,
    length int(15),
    views int(15),
    endorsedByInstructor boolean,
    module varchar(234),
    PRIMARY KEY(id),
    CONSTRAINT question_widget_generalization
    FOREIGN KEY(id) REFERENCES widget(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT question_user_FK
    FOREIGN KEY(askedBy) REFERENCES user(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT question_module_FK
    FOREIGN KEY(module) REFERENCES module(name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE answer(
    id int(15) NOT NULL,
    text varchar(234),
    postedBy int(15) NOT NULL,
    postedOn date,
    correctAnswer boolean,
    upVotes int(15),
    downVotes int(15),
    question int(15),
    PRIMARY KEY(id),
    CONSTRAINT answer_widget_generalization
    FOREIGN KEY(id) REFERENCES widget(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT answer_user_FK
    FOREIGN KEY(postedBy) REFERENCES user(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT answer_question_FK
    FOREIGN KEY(question) REFERENCES question(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

