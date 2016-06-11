DROP TABLE IF EXISTS security_metadata;

DROP TABLE IF EXISTS groups_role;

DROP TABLE IF EXISTS users_groups;

DROP TABLE IF EXISTS users_role;

DROP TABLE IF EXISTS groups;

DROP TABLE IF EXISTS role;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS web_resource;


/*==============================================================*/
/* Table: security_metadata                                      */
/*==============================================================*/
CREATE TABLE security_metadata
(
  id              INT NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  web_resource_id INT NOT NULL
  COMMENT '资源ID',
  role_id         INT NOT NULL
  COMMENT '角色ID',
  PRIMARY KEY (id),
  UNIQUE KEY UK_security_metadata (web_resource_id, role_id)
);

/*==============================================================*/
/* Table: groups                                                */
/*==============================================================*/
CREATE TABLE groups
(
  id          INT          NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  name        VARCHAR(50)  NOT NULL
  COMMENT '名称',
  description VARCHAR(100) NOT NULL
  COMMENT '描述',
  PRIMARY KEY (id),
  UNIQUE KEY UK_groups (name)
);

ALTER TABLE groups COMMENT '群体';

/*==============================================================*/
/* Table: groups_role                                           */
/*==============================================================*/
CREATE TABLE groups_role
(
  id        INT NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  role_id   INT NOT NULL
  COMMENT '角色ID',
  groups_id INT NOT NULL
  COMMENT '群体ID',
  PRIMARY KEY (id),
  UNIQUE KEY UK_groups_role (role_id, groups_id)
);

ALTER TABLE groups_role COMMENT '群体角色';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
CREATE TABLE role
(
  id          INT          NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  name        VARCHAR(50)  NOT NULL
  COMMENT '名称',
  description VARCHAR(100) NOT NULL
  COMMENT '描述',
  PRIMARY KEY (id),
  UNIQUE KEY UK_role (name)
);

ALTER TABLE role COMMENT '角色';

/*==============================================================*/
/* Table: users                                                  */
/*==============================================================*/
CREATE TABLE users
(
  id       INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  name     VARCHAR(50) NOT NULL
  COMMENT '用户名',
  password VARCHAR(50) NOT NULL
  COMMENT '密码',
  enabled  BOOLEAN     NOT NULL
  COMMENT 'true : 可用,  false : 不可用',
  PRIMARY KEY (id),
  UNIQUE KEY UK_name (name)
);

ALTER TABLE users COMMENT '用户';

/*==============================================================*/
/* Table: users_groups                                           */
/*==============================================================*/
CREATE TABLE users_groups
(
  id        INT NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  users_id  INT NOT NULL
  COMMENT '用户ID',
  groups_id INT NOT NULL
  COMMENT '群体ID',
  PRIMARY KEY (id),
  UNIQUE KEY UK_users_groups (users_id, groups_id)
);

ALTER TABLE users_groups COMMENT '用户群体';

/*==============================================================*/
/* Table: users_role                                             */
/*==============================================================*/
CREATE TABLE users_role
(
  id       INT NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  users_id INT NOT NULL
  COMMENT '用户ID',
  role_id  INT NOT NULL
  COMMENT '角色ID',
  PRIMARY KEY (id),
  UNIQUE KEY UK_users_role (users_id, role_id)
);

ALTER TABLE users_role COMMENT '用户角色';

/*==============================================================*/
/* Table: web_resource                                           */
/*==============================================================*/
CREATE TABLE web_resource
(
  id       INT          NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  pattern  VARCHAR(100) NOT NULL
  COMMENT 'URI模式',
  sequence INT          NOT NULL
  COMMENT '排序号',
  PRIMARY KEY (id),
  UNIQUE KEY UK_pattern (pattern),
  UNIQUE KEY UK_sequence (sequence)
);

ALTER TABLE web_resource COMMENT '系统资源';

/*==============================================================*/
/* Table: designer                                           */
/*==============================================================*/
CREATE TABLE `designer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `first_name` varchar(50) NOT NULL COMMENT '设计者first name',
  `second_name` varchar(50) NOT NULL COMMENT '设计者second name',
  `mobile_phone` varchar(20) NOT NULL COMMENT '移动电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT 'email地址',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `photograph` varchar(200) DEFAULT NULL COMMENT '照面uri',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='设计者';

CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `description` varchar(300) DEFAULT NULL COMMENT '项目描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT ='项目';

ALTER TABLE security_metadata ADD CONSTRAINT FK_security_metadata_Reference_role FOREIGN KEY (role_id)
REFERENCES role (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE security_metadata ADD CONSTRAINT FK_security_metadata_Reference_web_resource FOREIGN KEY (web_resource_id)
REFERENCES web_resource (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE groups_role ADD CONSTRAINT FK_groups_role_Reference_groups FOREIGN KEY (groups_id)
REFERENCES groups (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE groups_role ADD CONSTRAINT FK_groups_role_Reference_role FOREIGN KEY (role_id)
REFERENCES role (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE users_groups ADD CONSTRAINT FK_users_groups_Reference_groups FOREIGN KEY (groups_id)
REFERENCES groups (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE users_groups ADD CONSTRAINT FK_users_groups_Reference_users FOREIGN KEY (users_id)
REFERENCES users (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE users_role ADD CONSTRAINT FK_users_role_Reference_role FOREIGN KEY (role_id)
REFERENCES role (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE users_role ADD CONSTRAINT FK_users_role_Reference_users FOREIGN KEY (users_id)
REFERENCES users (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;


SELECT
  u.name    AS username,
  role.name AS rolename
FROM (SELECT
        id,
        name
      FROM user
      WHERE name = ?) AS u INNER JOIN users_role ON u.id = users_role.user_id
  INNER JOIN role ON user_role.role_id = role.id;

SELECT
  groups.name        AS groupsname,
  groups.description AS groupsdesc,
  role.name          AS rolename
FROM
  (SELECT groups_id
   FROM (SELECT id
         FROM user
         WHERE name = ?) AS u
     INNER JOIN user_groups ON u.id = user_groups.user_id) AS g
  INNER JOIN groups ON g.groups_id = groups.id
  INNER JOIN groups_role ON groups.id = groups_role.groups_id
  INNER JOIN role ON groups_role.role_id = role.id;


ALTER TABLE `interface_design`.`role` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
ALTER TABLE `interface_design`.`security_metadata` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
ALTER TABLE `interface_design`.`auth_user` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
ALTER TABLE `interface_design`.`groups` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
ALTER TABLE `interface_design`.`groups_role` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
ALTER TABLE `interface_design`.`users` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
ALTER TABLE `interface_design`.`users_groups` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
ALTER TABLE `interface_design`.`users_role` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
ALTER TABLE `interface_design`.`web_resource` CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;
