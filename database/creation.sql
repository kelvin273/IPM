CREATE TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

  
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

INSERT INTO users(username,password,enabled) VALUES ('mkyong','123456', true);
INSERT INTO users(username,password,enabled) VALUES ('alex','123456', true);

INSERT INTO user_roles (username, role) VALUES ('mkyong', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('mkyong', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('alex', 'ROLE_USER');

CREATE TABLE projects (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255),
  username varchar(45) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_username_project FOREIGN KEY (username) REFERENCES users (username));
  
INSERT INTO projects (name, username) VALUES ('P1', 'alex');
INSERT INTO projects (name, username) VALUES ('P2', 'alex');
INSERT INTO projects (name, username) VALUES ('P3', 'alex');
  
CREATE TABLE skills (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255),
  projectId INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_skillls_projectId FOREIGN KEY (projectId) REFERENCES projects (id));
  
INSERT INTO skills (name, projectId) VALUES ('S1', 1);
INSERT INTO skills (name, projectId) VALUES ('S2', 1);
INSERT INTO skills (name, projectId) VALUES ('S3', 1);
  
CREATE TABLE resources (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255),
  salary FLOAT NOT NULL,
  maxDedication FLOAT, 
  projectId INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_resources_projectId FOREIGN KEY (projectId) REFERENCES projects (id));  
  
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('R1', 10,1,1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('R2', 10,1,1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('R3', 10,1,1);
  
  
  