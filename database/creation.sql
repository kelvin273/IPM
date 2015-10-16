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
  name varchar(255) NOT NULL,
  username varchar(45) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_username_project FOREIGN KEY (username) REFERENCES users (username));
  
INSERT INTO projects (name, username) VALUES ('P1', 'alex');
INSERT INTO projects (name, username) VALUES ('P2', 'alex');
INSERT INTO projects (name, username) VALUES ('P3', 'alex');
  
CREATE TABLE skills (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  projectId INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_skillls_projectId FOREIGN KEY (projectId) REFERENCES projects (id));
  
INSERT INTO skills (name, projectId) VALUES ('S1', 1);
INSERT INTO skills (name, projectId) VALUES ('S2', 1);
INSERT INTO skills (name, projectId) VALUES ('S3', 1);
  
CREATE TABLE resources (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  salary FLOAT NOT NULL,
  maxDedication FLOAT, 
  projectId INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_resources_projectId FOREIGN KEY (projectId) REFERENCES projects (id));  
  
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('R1', 10,1,1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('R2', 10,1,1);
INSERT INTO resources (name, salary,maxDedication,projectId) VALUES ('R3', 10,1,1);
  
CREATE TABLE skillResources (
  resourceId INTEGER NOT NULL,
  skillId INTEGER NOT NULL,
  PRIMARY KEY (resourceId, skillId),
  CONSTRAINT fk_skillResources_resources FOREIGN KEY (resourceId) REFERENCES resources (id),
  CONSTRAINT fk_skillResources_skill FOREIGN KEY (skillId) REFERENCES skills (id)
  ); 
  
CREATE TABLE tasks (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  effort FLOAT, 
  exclusive boolean,
  projectId INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_task_projectId FOREIGN KEY (projectId) REFERENCES projects (id));  
  
CREATE TABLE skillTasks (
  skillId INTEGER NOT NULL,
  taskId INTEGER NOT NULL,
  PRIMARY KEY (taskId, skillId),
  CONSTRAINT fk_skillTask_skills FOREIGN KEY (skillId) REFERENCES skills (id),
  CONSTRAINT fk_skillTasks_task FOREIGN KEY (taskId) REFERENCES tasks (id)
  ); 
  
CREATE TABLE resourceTasks (
  resourceId INTEGER NOT NULL,
  taskId INTEGER NOT NULL,
  PRIMARY KEY (taskId, resourceId),
  CONSTRAINT fk_resourceTasks_resource FOREIGN KEY (resourceId) REFERENCES resources (id),
  CONSTRAINT fk_resourceTasks_tasks FOREIGN KEY (taskId) REFERENCES tasks (id)
  ); 
  
CREATE TABLE dependentTasks (
  taskId INTEGER NOT NULL,
  dependentTaskId INTEGER NOT NULL,
  PRIMARY KEY (taskId, dependentTaskId),
  CONSTRAINT fk_dependentTasks_dependentTask FOREIGN KEY (dependentTaskId) REFERENCES tasks (id),
  CONSTRAINT fk_dependentTasks_task FOREIGN KEY (taskId) REFERENCES tasks (id)
  ); 