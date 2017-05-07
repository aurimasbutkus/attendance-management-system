-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2017 m. Geg 07 d. 14:06
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employee_management_system`
--

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nationality` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `work_status` int(11) DEFAULT NULL,
  `account_role` int(11) DEFAULT NULL,
  `fk_Team` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `active` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Sukurta duomenų kopija lentelei `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `email_address`, `first_name`, `last_name`, `date_of_birth`, `phone_number`, `gender`, `nationality`, `work_status`, `account_role`, `fk_Team`, `enabled`, `active`) VALUES
(10, 'adminas', '$2a$10$6KbN.YzXE3hIselwx.z0yOJo79jwEnE/kA3bqQXO/8cdTcjFzHcH2', 'admin@ems.com', 'Adminas', 'Adminiskauskas', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
(13, 'cartman', '$2a$10$.2.4Jq8RuIaqAzaim6zVbOJkGvCWhbST7WW7MjAJQ3QdLvkAm2lXy', 'eric@gmail.com', 'eric', 'cartman', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
(14, 'auris331', '$2a$10$5VVTuxGAwuhGoFm1uAJ.quhsyehk3l1A9J71N87NdJad1YnrGcAZ2', 'email@email.com', 'Aurimas', 'Butkus', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `contract_info`
--

CREATE TABLE `contract_info` (
  `contract_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `salary` decimal(10,0) NOT NULL,
  `fk_User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `employees_tasks`
--

CREATE TABLE `employees_tasks` (
  `tasklist_id` int(11) NOT NULL,
  `number_of_tasks` int(11) NOT NULL,
  `fk_Subtask` int(11) NOT NULL,
  `fk_User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `employees_work_status`
--

CREATE TABLE `employees_work_status` (
  `id` int(11) NOT NULL,
  `name` char(13) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Sukurta duomenų kopija lentelei `employees_work_status`
--

INSERT INTO `employees_work_status` (`id`, `name`) VALUES
(1, 'checked_out'),
(2, 'on_break'),
(3, 'on_holiday'),
(4, 'on_sick_leave'),
(5, 'checked_in');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `private_message`
--

CREATE TABLE `private_message` (
  `message_id` int(11) NOT NULL,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `fk_account_sender` int(11) NOT NULL,
  `fk_account_receiver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Sukurta duomenų kopija lentelei `private_message`
--

INSERT INTO `private_message` (`message_id`, `text`, `date`, `fk_account_sender`, `fk_account_receiver`) VALUES
(1, 'Testine zinute 1', '2017-05-01', 13, 14),
(2, 'Testine zinute ne man', '2017-05-01', 14, 13),
(3, 'Viskas veikia :o', '2017-05-07', 10, 14);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `project`
--

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `completion_date` date DEFAULT NULL,
  `creation_date` date NOT NULL,
  `deadline` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Sukurta duomenų kopija lentelei `project`
--

INSERT INTO `project` (`project_id`, `name`, `description`, `start_date`, `end_date`, `completion_date`, `creation_date`, `deadline`) VALUES
(1, 'Projektas1', 'Cia yra pirmas projektas', '2017-05-01', NULL, NULL, '0000-00-00', NULL),
(2, 'Projektas2', 'Cia yra antras projektas', '2017-05-17', '2017-06-22', NULL, '0000-00-00', NULL);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `project_teams`
--

CREATE TABLE `project_teams` (
  `teamslist_id` int(11) NOT NULL,
  `number_of_teams` int(11) NOT NULL,
  `fk_Project` int(11) NOT NULL,
  `fk_Team` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL DEFAULT '',
  `role` varchar(20) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sukurta duomenų kopija lentelei `roles`
--

INSERT INTO `roles` (`role_id`, `username`, `role`) VALUES
(1, 'adminas', 'ADMIN'),
(2, 'adminas', 'USER'),
(3, 'cartman', 'USER'),
(4, 'cartman', 'USER'),
(5, 'auris331', 'USER');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `subtask`
--

CREATE TABLE `subtask` (
  `subtask_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL,
  `fk_Task` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Sukurta duomenų kopija lentelei `subtask`
--

INSERT INTO `subtask` (`subtask_id`, `description`, `status`, `fk_Task`) VALUES
(1, 'Task One', 1, 1),
(2, 'Task Two', 1, 1),
(3, 'Task Three', 1, 1),
(5, 'Task Hotel', 1, 3),
(6, 'Task Omega', 3, 3),
(7, 'Task ToTo', 1, 4);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `subtask_status`
--

CREATE TABLE `subtask_status` (
  `id` int(11) NOT NULL,
  `name` char(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Sukurta duomenų kopija lentelei `subtask_status`
--

INSERT INTO `subtask_status` (`id`, `name`) VALUES
(1, 'on_progress'),
(2, 'on_hold'),
(3, 'finished');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `task`
--

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creation_date` date NOT NULL,
  `deadline` date DEFAULT NULL,
  `completion_date` date DEFAULT NULL,
  `fk_Project` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Sukurta duomenų kopija lentelei `task`
--

INSERT INTO `task` (`task_id`, `description`, `creation_date`, `deadline`, `completion_date`, `fk_Project`) VALUES
(1, 'This is a simple task with a deadline and completion date', '2017-05-06', '2017-05-31', '2017-05-23', 1),
(2, 'This is another simple task with a deadline but no completion date', '2017-05-06', '2017-05-30', NULL, 1),
(3, 'This is the third task with no deadline and no completion date', '2017-05-06', NULL, NULL, 1),
(4, 'Task for project 2', '2017-05-07', NULL, NULL, 2);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `task_comment`
--

CREATE TABLE `task_comment` (
  `comment_id` int(11) NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creation_date` date NOT NULL,
  `modify_date` date DEFAULT NULL,
  `author_username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fk_Subtask` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `team`
--

CREATE TABLE `team` (
  `team_id` int(11) NOT NULL,
  `size` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_role` (`account_role`),
  ADD KEY `work_status` (`work_status`),
  ADD KEY `fkc_Team` (`fk_Team`);

--
-- Indexes for table `contract_info`
--
ALTER TABLE `contract_info`
  ADD PRIMARY KEY (`contract_id`),
  ADD UNIQUE KEY `fk_Employee` (`fk_User`);

--
-- Indexes for table `employees_tasks`
--
ALTER TABLE `employees_tasks`
  ADD PRIMARY KEY (`tasklist_id`),
  ADD KEY `fkc_List_Subtask` (`fk_Subtask`),
  ADD KEY `fkc_List_Employee` (`fk_User`);

--
-- Indexes for table `employees_work_status`
--
ALTER TABLE `employees_work_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `private_message`
--
ALTER TABLE `private_message`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `fkc_account_sender` (`fk_account_sender`),
  ADD KEY `fkc_account_receiver` (`fk_account_receiver`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`);

--
-- Indexes for table `project_teams`
--
ALTER TABLE `project_teams`
  ADD PRIMARY KEY (`teamslist_id`),
  ADD KEY `fkc_Current_Project` (`fk_Project`),
  ADD KEY `fkc_Project_Team` (`fk_Team`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `subtask`
--
ALTER TABLE `subtask`
  ADD PRIMARY KEY (`subtask_id`),
  ADD KEY `status` (`status`),
  ADD KEY `fkc_Task` (`fk_Task`);

--
-- Indexes for table `subtask_status`
--
ALTER TABLE `subtask_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `fkc_Project` (`fk_Project`);

--
-- Indexes for table `task_comment`
--
ALTER TABLE `task_comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `fkc_Subtask` (`fk_Subtask`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`team_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `contract_info`
--
ALTER TABLE `contract_info`
  MODIFY `contract_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `employees_tasks`
--
ALTER TABLE `employees_tasks`
  MODIFY `tasklist_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `employees_work_status`
--
ALTER TABLE `employees_work_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `private_message`
--
ALTER TABLE `private_message`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `project_teams`
--
ALTER TABLE `project_teams`
  MODIFY `teamslist_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `subtask`
--
ALTER TABLE `subtask`
  MODIFY `subtask_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `subtask_status`
--
ALTER TABLE `subtask_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `task_comment`
--
ALTER TABLE `task_comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `team_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Apribojimai eksportuotom lentelėm
--

--
-- Apribojimai lentelei `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`account_role`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`work_status`) REFERENCES `employees_work_status` (`id`),
  ADD CONSTRAINT `fkc_Team` FOREIGN KEY (`fk_Team`) REFERENCES `team` (`team_id`);

--
-- Apribojimai lentelei `contract_info`
--
ALTER TABLE `contract_info`
  ADD CONSTRAINT `fkc_Employee` FOREIGN KEY (`fk_User`) REFERENCES `account` (`id`);

--
-- Apribojimai lentelei `employees_tasks`
--
ALTER TABLE `employees_tasks`
  ADD CONSTRAINT `fkc_List_Employee` FOREIGN KEY (`fk_User`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fkc_List_Subtask` FOREIGN KEY (`fk_Subtask`) REFERENCES `subtask` (`subtask_id`);

--
-- Apribojimai lentelei `private_message`
--
ALTER TABLE `private_message`
  ADD CONSTRAINT `fkc_account_receiver` FOREIGN KEY (`fk_account_receiver`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fkc_account_sender` FOREIGN KEY (`fk_account_sender`) REFERENCES `account` (`id`);

--
-- Apribojimai lentelei `project_teams`
--
ALTER TABLE `project_teams`
  ADD CONSTRAINT `fkc_Current_Project` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`project_id`),
  ADD CONSTRAINT `fkc_Project_Team` FOREIGN KEY (`fk_Team`) REFERENCES `team` (`team_id`);

--
-- Apribojimai lentelei `subtask`
--
ALTER TABLE `subtask`
  ADD CONSTRAINT `fkc_Task` FOREIGN KEY (`fk_Task`) REFERENCES `task` (`task_id`),
  ADD CONSTRAINT `subtask_ibfk_1` FOREIGN KEY (`status`) REFERENCES `subtask_status` (`id`);

--
-- Apribojimai lentelei `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fkc_Project` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`project_id`);

--
-- Apribojimai lentelei `task_comment`
--
ALTER TABLE `task_comment`
  ADD CONSTRAINT `fkc_Subtask` FOREIGN KEY (`fk_Subtask`) REFERENCES `subtask` (`subtask_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
