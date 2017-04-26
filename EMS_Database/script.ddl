--@(#) script.ddl

CREATE TABLE Project
(
	project_id integer NOT NULL AUTO_INCREMENT,
	name varchar (255) NOT NULL,
	description varchar (255) NOT NULL,
	start_date date NOT NULL,
	end_date date NULL,
	PRIMARY KEY(project_id)
);

CREATE TABLE Team
(
	team_id integer NOT NULL AUTO_INCREMENT,
	size integer NOT NULL,
	PRIMARY KEY(team_id)
);

CREATE TABLE Employees_Work_Status
(
	id integer NOT NULL AUTO_INCREMENT,
	name char (13) NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO Employees_Work_Status(id, name) VALUES(1, 'checked_out');
INSERT INTO Employees_Work_Status(id, name) VALUES(2, 'on_break');
INSERT INTO Employees_Work_Status(id, name) VALUES(3, 'on_holiday');
INSERT INTO Employees_Work_Status(id, name) VALUES(4, 'on_sick_leave');
INSERT INTO Employees_Work_Status(id, name) VALUES(5, 'checked_in');

CREATE TABLE Subtask_Status
(
	id integer NOT NULL AUTO_INCREMENT,
	name char (11) NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO Subtask_Status(id, name) VALUES(1, 'on_progress');
INSERT INTO Subtask_Status(id, name) VALUES(2, 'on_hold');
INSERT INTO Subtask_Status(id, name) VALUES(3, 'finished');

CREATE TABLE User_role
(
	id integer NOT NULL AUTO_INCREMENT,
	name char (7) NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO User_role(id, name) VALUES(1, 'owner');
INSERT INTO User_role(id, name) VALUES(2, 'manager');
INSERT INTO User_role(id, name) VALUES(3, 'default');

CREATE TABLE Employee
(
	employee_id integer NOT NULL AUTO_INCREMENT,
	username varchar (255) NOT NULL,
	password varchar (255) NOT NULL,
	first_name varchar (255) NOT NULL,
	last_name varchar (255) NOT NULL,
	gender varchar (255) NOT NULL,
	nationality varchar (255) NOT NULL,
	email_address varchar (255) NOT NULL,
	birthdate date NOT NULL,
	phone varchar (255) NULL,
	current_task varchar (255) NULL,
	work_status integer NOT NULL,
	user_role integer NOT NULL,
	fk_Team integer NULL,
	PRIMARY KEY(employee_id),
	FOREIGN KEY(user_role) REFERENCES User_role (id),
	FOREIGN KEY(work_status) REFERENCES Employees_Work_Status (id),
	CONSTRAINT fkc_Team FOREIGN KEY(fk_Team) REFERENCES Team (team_id)
);

CREATE TABLE Project_Teams
(
	teamslist_id integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
	number_of_teams integer NOT NULL,
	fk_Project integer NOT NULL,
	fk_Team integer NOT NULL,
	CONSTRAINT fkc_Current_Project FOREIGN KEY(fk_Project) REFERENCES Project (project_id),
	CONSTRAINT fkc_Project_Team FOREIGN KEY(fk_Team) REFERENCES Team (team_id)
);

CREATE TABLE Task
(
	task_id integer NOT NULL AUTO_INCREMENT,
	description varchar (255) NOT NULL,
	creation_date date NOT NULL,
	deadline date NULL,
	completion_date date NULL,
	fk_Project integer NOT NULL,
	PRIMARY KEY(task_id),
	CONSTRAINT fkc_Project FOREIGN KEY(fk_Project) REFERENCES Project (project_id)
);

CREATE TABLE Contract_Info
(
	contract_id integer NOT NULL AUTO_INCREMENT,
	start_date date NOT NULL,
	end_date date NOT NULL,
	salary decimal NOT NULL,
	fk_Employee integer NOT NULL,
	PRIMARY KEY(contract_id),
	UNIQUE(fk_Employee),
	CONSTRAINT fkc_Employee FOREIGN KEY(fk_Employee) REFERENCES Employee (employee_id)
);

CREATE TABLE Subtask
(
	subtask_id integer NOT NULL AUTO_INCREMENT,
	description varchar (255) NOT NULL,
	status integer NOT NULL,
	fk_Task integer NOT NULL,
	PRIMARY KEY(subtask_id),
	FOREIGN KEY(status) REFERENCES Subtask_Status (id),
	CONSTRAINT fkc_Task FOREIGN KEY(fk_Task) REFERENCES Task (task_id)
);

CREATE TABLE Employees_Tasks
(
	tasklist_id integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
	number_of_tasks integer NOT NULL,
	fk_Subtask integer NOT NULL,
	fk_Employee integer NOT NULL,
	CONSTRAINT fkc_List_Subtask FOREIGN KEY(fk_Subtask) REFERENCES Subtask (subtask_id),
	CONSTRAINT fkc_List_Employee FOREIGN KEY(fk_Employee) REFERENCES Employee (employee_id)
);

CREATE TABLE Task_Comment
(
	comment_id integer NOT NULL AUTO_INCREMENT,
	content varchar (255) NOT NULL,
	creation_date date NOT NULL,
	modify_date date NULL,
	author_username varchar (255) NOT NULL,
	fk_Subtask integer NOT NULL,
	PRIMARY KEY(comment_id),
	CONSTRAINT fkc_Subtask FOREIGN KEY(fk_Subtask) REFERENCES Subtask (subtask_id)
);
