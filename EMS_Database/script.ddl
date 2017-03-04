CREATE TABLE Living_Adress_Info
(
	country varchar (255) NOT NULL,
	city varchar (255) NOT NULL,
	street varchar (255) NOT NULL,
	house_number integer NOT NULL,
	apartment_numer integer NULL,
	adress_info_id integer NOT NULL,
	PRIMARY KEY(adress_info_id)
);

CREATE TABLE Team
(
	team_id integer NOT NULL,
	size integer NOT NULL,
	PRIMARY KEY(team_id)
);

CREATE TABLE Employees_Work_Status
(
	id integer NOT NULL,
	name char (13) NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO Employees_Work_Status(id, name) VALUES(1, 'checked_out');
INSERT INTO Employees_Work_Status(id, name) VALUES(2, 'on_break');
INSERT INTO Employees_Work_Status(id, name) VALUES(3, 'on_holiday');
INSERT INTO Employees_Work_Status(id, name) VALUES(4, 'on_sick_leave');
INSERT INTO Employees_Work_Status(id, name) VALUES(5, 'checked_in');

CREATE TABLE Roles_For_Users
(
	id integer NOT NULL,
	name char (7) NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO Roles_For_Users(id, name) VALUES(1, 'owner');
INSERT INTO Roles_For_Users(id, name) VALUES(2, 'manager');
INSERT INTO Roles_For_Users(id, name) VALUES(3, 'default');

CREATE TABLE Task_Status
(
	id integer NOT NULL,
	name char (15) NOT NULL,
	PRIMARY KEY(id)
);
INSERT INTO Task_Status(id, name) VALUES(1, 'given');
INSERT INTO Task_Status(id, name) VALUES(2, 'being_worked_on');
INSERT INTO Task_Status(id, name) VALUES(3, 'on_hold');
INSERT INTO Task_Status(id, name) VALUES(4, 'finished');

CREATE TABLE Employee
(
	employee_id integer NOT NULL,
	phone varchar (255) NULL,
	nationality varchar (255) NOT NULL,
	birthdate date NOT NULL,
	gender varchar (255) NOT NULL,
	current_task varchar (255) NULL,
	first_name varchar (255) NOT NULL,
	last_name varchar (255) NOT NULL,
	work_status integer NOT NULL,
	fk_Team integer NOT NULL,
	fk_Living_Adress_Info integer NOT NULL,
	PRIMARY KEY(employee_id),
	FOREIGN KEY(work_status) REFERENCES Employees_Work_Status (id),
	CONSTRAINT fkc_Team FOREIGN KEY(fk_Team) REFERENCES Team (team_id),
	CONSTRAINT fkc_Living_Adress_Info FOREIGN KEY(fk_Living_Adress_Info) REFERENCES Living_Adress_Info (adress_info_id)
);

CREATE TABLE Account
(
	user_id integer NOT NULL,
	username varchar (255) NOT NULL,
	password varchar (255) NOT NULL,
	email_address varchar (255) NOT NULL,
	user_role integer NOT NULL,
	fk_Employee integer NOT NULL,
	PRIMARY KEY(user_id),
	UNIQUE(fk_Employee),
	FOREIGN KEY(user_role) REFERENCES Roles_For_Users (id),
	CONSTRAINT fkc_Employee FOREIGN KEY(fk_Employee) REFERENCES Employee (employee_id)
);

CREATE TABLE Contract_Info
(
	start_date date NOT NULL,
	end_date date NOT NULL,
	salary DECIMAL(10,2) NOT NULL,
	contract_id integer NOT NULL,
	fk_Employee integer NOT NULL,
	PRIMARY KEY(contract_id),
	UNIQUE(fk_Employee),
	CONSTRAINT fkc_Contract_Employee FOREIGN KEY(fk_Employee) REFERENCES Employee (employee_id)
);

CREATE TABLE Project
(
	project_id integer NOT NULL,
	name varchar (255) NOT NULL,
	description varchar (255) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	fk_company_owner integer NOT NULL,
	PRIMARY KEY(project_id),
	CONSTRAINT fkc_User FOREIGN KEY(fk_company_owner) REFERENCES Account (user_id)
);

CREATE TABLE Project_Teams
(
	number_of_teams integer NOT NULL,
	collective_id integer NOT NULL PRIMARY KEY,
	fk_Team integer NOT NULL,
	fk_Project integer NOT NULL,
	CONSTRAINT fkc_A_Team FOREIGN KEY(fk_Team) REFERENCES Team (team_id),
	CONSTRAINT fkc_Team_Project FOREIGN KEY(fk_Project) REFERENCES Project (project_id)
);

CREATE TABLE Task
(
	description varchar (255) NOT NULL,
	creation_date date NOT NULL,
	modification_date date NULL,
	deadline date NOT NULL,
	completion_date date NULL,
	task_id integer NOT NULL,
	status integer NOT NULL,
	fk_Project integer NOT NULL,
	fk_project_manager integer NOT NULL,
	PRIMARY KEY(task_id),
	FOREIGN KEY(status) REFERENCES Task_Status (id),
	CONSTRAINT fkc_Task_Project FOREIGN KEY(fk_Project) REFERENCES Project (project_id),
	CONSTRAINT fkc_Task_User FOREIGN KEY(fk_project_manager) REFERENCES Account (user_id)
);

CREATE TABLE Employees_Tasks
(
	number_of_tasks integer NOT NULL,
	fk_Employee integer NOT NULL,
	fk_Task integer NOT NULL,
	CONSTRAINT id PRIMARY KEY(fk_Employee, fk_Task),
	CONSTRAINT fkc_An_Employee FOREIGN KEY(fk_Employee) REFERENCES Employee (employee_id),
	CONSTRAINT fkc_An_Employee_Task FOREIGN KEY(fk_Task) REFERENCES Task (task_id)
);

CREATE TABLE Task_Comment
(
	comment_id integer NOT NULL,
	content varchar (255) NOT NULL,
	creation_date date NOT NULL,
	modify_date date NULL,
	fk_User integer NOT NULL,
	fk_Task integer NOT NULL,
	PRIMARY KEY(comment_id),
	CONSTRAINT fkc_Comment_User FOREIGN KEY(fk_User) REFERENCES Account (user_id),
	CONSTRAINT fkc_Task_Comment FOREIGN KEY(fk_Task) REFERENCES Task (task_id)
);