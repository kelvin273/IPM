drop database ipm;
create database ipm;
use ipm;
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

CREATE TABLE projects (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  username varchar(45) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_username_project FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE);
  

  
CREATE TABLE skills (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  projectId INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_skillls_projectId FOREIGN KEY (projectId) REFERENCES projects (id) ON DELETE CASCADE);

CREATE TABLE resources (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  salary FLOAT NOT NULL,
  maxDedication FLOAT, 
  projectId INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_resources_projectId FOREIGN KEY (projectId) REFERENCES projects (id) ON DELETE CASCADE);  
  
  
CREATE TABLE skillResources (
  resourceId INTEGER NOT NULL,
  skillId INTEGER NOT NULL,
  PRIMARY KEY (resourceId, skillId),
  CONSTRAINT fk_skillResources_resources FOREIGN KEY (resourceId) REFERENCES resources (id) ON DELETE CASCADE,
  CONSTRAINT fk_skillResources_skill FOREIGN KEY (skillId) REFERENCES skills (id) ON DELETE CASCADE
  ); 
  
CREATE TABLE tasks (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  effort FLOAT, 
  exclusive boolean,
  projectId INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_task_projectId FOREIGN KEY (projectId) REFERENCES projects (id) ON DELETE CASCADE);  
  
CREATE TABLE skillTasks (
  skillId INTEGER NOT NULL,
  taskId INTEGER NOT NULL,
  PRIMARY KEY (taskId, skillId),
  CONSTRAINT fk_skillTask_skills FOREIGN KEY (skillId) REFERENCES skills (id) ON DELETE CASCADE,
  CONSTRAINT fk_skillTasks_task FOREIGN KEY (taskId) REFERENCES tasks (id) ON DELETE CASCADE
  ); 
  
CREATE TABLE resourceTasks (
  resourceId INTEGER NOT NULL,
  taskId INTEGER NOT NULL,
  PRIMARY KEY (taskId, resourceId),
  CONSTRAINT fk_resourceTasks_resource FOREIGN KEY (resourceId) REFERENCES resources (id) ON DELETE CASCADE,
  CONSTRAINT fk_resourceTasks_tasks FOREIGN KEY (taskId) REFERENCES tasks (id) ON DELETE CASCADE
  ); 
  
CREATE TABLE dependentTasks (
  taskId INTEGER NOT NULL,
  dependentTaskId INTEGER NOT NULL,
  PRIMARY KEY (taskId, dependentTaskId),
  CONSTRAINT fk_dependentTasks_dependentTask FOREIGN KEY (dependentTaskId) REFERENCES tasks (id) ON DELETE CASCADE,
  CONSTRAINT fk_dependentTasks_task FOREIGN KEY (taskId) REFERENCES tasks (id) ON DELETE CASCADE
  ); 

  
INSERT INTO users(username,password,enabled) VALUES ('mkyong','123456', true);
INSERT INTO users(username,password,enabled) VALUES ('alex','123456', true);
INSERT INTO user_roles (username, role) VALUES ('mkyong', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('mkyong', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('alex', 'ROLE_USER');
INSERT INTO projects (name, username) VALUES ('Company Web Site', 'alex');
INSERT INTO skills (name, projectId) VALUES ('Programing expertise', 1);
INSERT INTO skills (name, projectId) VALUES ('Leadership', 1);
INSERT INTO skills (name, projectId) VALUES ('Database expertise', 1);
INSERT INTO skills (name, projectId) VALUES ('UML expertise', 1);
INSERT INTO skills (name, projectId) VALUES ('Web design expertise', 1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('Employee 1', 2000,1,1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('Employee 2', 2500,1,1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('Employee 3', 1700,0.5,1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('Employee 4', 3000,1,1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('Employee 5', 2200,1.2,1);
INSERT INTO skillResources (resourceId, skillId) VALUES (1,1);
INSERT INTO skillResources (resourceId, skillId) VALUES (1,4);
INSERT INTO skillResources (resourceId, skillId) VALUES (2,2);
INSERT INTO skillResources (resourceId, skillId) VALUES (2,3);
INSERT INTO skillResources (resourceId, skillId) VALUES (2,4);
INSERT INTO skillResources (resourceId, skillId) VALUES (3,5);
INSERT INTO skillResources (resourceId, skillId) VALUES (4,1);
INSERT INTO skillResources (resourceId, skillId) VALUES (4,2);
INSERT INTO skillResources (resourceId, skillId) VALUES (5,4);
INSERT INTO skillResources (resourceId, skillId) VALUES (5,5);


INSERT INTO tasks (name, effort,exclusive,projectId) VALUES ('Perform UML Diagrams',5,false,1);
INSERT INTO tasks (name, effort,exclusive,projectId) VALUES ('Design Database',20,false,1);
INSERT INTO tasks (name, effort,exclusive,projectId) VALUES ('Implementation',50,false,1);
INSERT INTO tasks (name, effort,exclusive,projectId) VALUES ('Design the web page templates',10,false,1);
INSERT INTO tasks (name, effort,exclusive,projectId) VALUES ('Test the software',50,false,1);
INSERT INTO tasks (name, effort,exclusive,projectId) VALUES ('Database design documents',15,false,1);
INSERT INTO tasks (name, effort,exclusive,projectId) VALUES ('User manual',10,false,1);

INSERT INTO skillTasks (skillId, taskId) VALUES (4,1);
INSERT INTO skillTasks (skillId, taskId) VALUES (1,2);
INSERT INTO skillTasks (skillId, taskId) VALUES (3,2);
INSERT INTO skillTasks (skillId, taskId) VALUES (1,3);
INSERT INTO skillTasks (skillId, taskId) VALUES (4,3);
INSERT INTO skillTasks (skillId, taskId) VALUES (5,4);
INSERT INTO skillTasks (skillId, taskId) VALUES (2,5);
INSERT INTO skillTasks (skillId, taskId) VALUES (4,6);
INSERT INTO skillTasks (skillId, taskId) VALUES (1,7);