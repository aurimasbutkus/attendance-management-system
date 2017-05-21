-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2017 at 01:54 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

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
-- Table structure for table `account`
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
  `enabled` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `email_address`, `first_name`, `last_name`, `date_of_birth`, `phone_number`, `gender`, `nationality`, `work_status`, `account_role`, `fk_Team`, `enabled`) VALUES
(10, 'adminas', '$2a$10$6KbN.YzXE3hIselwx.z0yOJo79jwEnE/kA3bqQXO/8cdTcjFzHcH2', 'admin@ems.com', 'Adminas', 'Adminiskauskas', '1996-11-05', '86666066', '69', 'Lietuvis', 1, NULL, 1, 1),
(13, 'cartman', '$2a$10$.2.4Jq8RuIaqAzaim6zVbOJkGvCWhbST7WW7MjAJQ3QdLvkAm2lXy', 'eric@gmail.com', 'eric', 'cartman', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1),
(14, 'auris331', '$2a$10$5VVTuxGAwuhGoFm1uAJ.quhsyehk3l1A9J71N87NdJad1YnrGcAZ2', 'email@email.com', 'Aurimas', 'Butkus', NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(15, 'another', '$2a$10$Fldg.YmIDM29WX3o4flOLu5CG4hgUMCKzeWtRtotT4Mqg1yJbNpBy', 'eta@mail.co.uk', 'name', 'surname', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1),
(16, 'user', '$2a$10$cYMfnAz4DY8P7u8LtYHUv.QWJttT0V8vHSVgSQ7h7v7yg2zSC/d4K', 'user@a.com', 'user', 'user', NULL, NULL, NULL, NULL, 1, NULL, 1, 1),
(17, 'newuser', '$2a$10$sUVyyPs1M.QKBZxENnOAXujtfsH52dpOiihLtHfhuVHinEbs08VxS', 'nsdfa@f.com', 'neww', 'enwwn', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `contract_info`
--

CREATE TABLE `contract_info` (
  `id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `salary` decimal(10,0) NOT NULL,
  `fk_User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees_tasks`
--

CREATE TABLE `employees_tasks` (
  `id` int(11) NOT NULL,
  `number_of_tasks` int(11) NOT NULL,
  `fk_Subtask` int(11) NOT NULL,
  `fk_User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees_work_status`
--

CREATE TABLE `employees_work_status` (
  `id` int(11) NOT NULL,
  `name` char(13) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `employees_work_status`
--

INSERT INTO `employees_work_status` (`id`, `name`) VALUES
(1, 'checked_out'),
(2, 'on_break'),
(3, 'on_holiday'),
(4, 'on_sick_leave'),
(5, 'checked_in');

-- --------------------------------------------------------

--
-- Table structure for table `private_message`
--

CREATE TABLE `private_message` (
  `id` int(11) NOT NULL,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `fk_account_sender` int(11) NOT NULL,
  `fk_account_receiver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `private_message`
--

INSERT INTO `private_message` (`id`, `text`, `date`, `fk_account_sender`, `fk_account_receiver`) VALUES
(1, 'Testine zinute 1', '2017-05-01', 13, 14),
(2, 'Testine zinute ne man', '2017-05-01', 14, 13),
(3, 'Viskas veikia :o', '2017-05-07', 10, 14),
(4, 'testine zinute naujoje db', '2017-05-08', 14, 10),
(5, 'sveikas mielas drauge', '2017-05-08', 10, 14),
(6, 'smagu kad veikia šitas daiktas', '2017-05-08', 14, 10),
(7, 'testine zinute sau nauja', '2017-05-08', 14, 14),
(8, 'dar zodis ir bana gausi', '2017-05-08', 10, 14),
(9, 'as jau naujam chate testuoju', '2017-05-08', 14, 10),
(10, 'testine zinute sau naujam chate', '2017-05-08', 14, 14);

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `deadline` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`id`, `name`, `description`, `start_date`, `end_date`, `deadline`) VALUES
(1, 'Projektas1', 'Cia yra pirmas projektas', '2017-05-01', NULL, NULL),
(2, 'Projektas2', 'Cia yra antras projektas', '2017-05-17', '2017-06-22', NULL),
(3, 'Project 3', 'Cool', '2017-05-01', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `project_teams`
--

CREATE TABLE `project_teams` (
  `id` int(11) NOT NULL,
  `fk_Project` int(11) NOT NULL,
  `fk_Team` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `project_teams`
--

INSERT INTO `project_teams` (`id`, `fk_Project`, `fk_Team`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL DEFAULT '',
  `role` varchar(20) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `username`, `role`) VALUES
(1, 'adminas', 'ADMIN'),
(2, 'adminas', 'USER'),
(3, 'cartman', 'USER'),
(4, 'cartman', 'USER'),
(5, 'auris331', 'USER'),
(6, 'another', 'USER'),
(7, 'user', 'USER'),
(8, 'newuser', 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `subtask`
--

CREATE TABLE `subtask` (
  `id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL,
  `fk_Task` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `subtask`
--

INSERT INTO `subtask` (`id`, `description`, `status`, `fk_Task`) VALUES
(1, 'Task One', 1, 1),
(2, 'Task Two', 1, 1),
(3, 'Task Three', 1, 1),
(5, 'Task Hotel', 1, 3),
(6, 'Task Omega', 3, 3),
(7, 'Task ToTo', 1, 4),
(8, 'New task n shit', 2, 3),
(9, 'Task fit', 2, 1),
(10, 'tasaasss', 2, 1),
(11, 'Ayy lmao', 2, 1),
(12, 'wut wut', 2, 3),
(13, 'hey wassup', 2, 1),
(14, 'New task!!', 2, 2),
(15, 'nice', 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `subtask_status`
--

CREATE TABLE `subtask_status` (
  `id` int(11) NOT NULL,
  `name` char(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `subtask_status`
--

INSERT INTO `subtask_status` (`id`, `name`) VALUES
(1, 'on_progress'),
(2, 'on_hold'),
(3, 'finished');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creation_date` date NOT NULL,
  `deadline` date DEFAULT NULL,
  `completion_date` date DEFAULT NULL,
  `fk_Project` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id`, `description`, `creation_date`, `deadline`, `completion_date`, `fk_Project`) VALUES
(1, 'This is a simple task with a deadline and completion date', '2017-05-06', '2017-05-31', '2017-05-23', 1),
(2, 'This is another simple task with a deadline but no completion date', '2017-05-06', '2017-05-30', NULL, 1),
(3, 'This is the third task with no deadline and no completion date', '2017-05-01', NULL, NULL, 1),
(4, 'Task for project 2', '2017-05-07', NULL, NULL, 2),
(5, 'Its a new task', '2017-05-15', '2017-05-29', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `task_comment`
--

CREATE TABLE `task_comment` (
  `id` int(11) NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creation_date` date NOT NULL,
  `modify_date` date DEFAULT NULL,
  `fk_Author` int(14) NOT NULL,
  `fk_Subtask` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`id`, `description`, `name`) VALUES
(1, 'This is a description for team Alpha.', 'Alpha'),
(2, 'This is a description for team Bravo.', 'Bravo');

-- --------------------------------------------------------

--
-- Table structure for table `updates`
--

CREATE TABLE `updates` (
  `id` int(14) NOT NULL,
  `date` datetime NOT NULL,
  `content` varchar(255) NOT NULL,
  `fk_Author` int(14) NOT NULL,
  `fk_Project` int(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `fk_Employee` (`fk_User`);

--
-- Indexes for table `employees_tasks`
--
ALTER TABLE `employees_tasks`
  ADD PRIMARY KEY (`id`),
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
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkc_account_sender` (`fk_account_sender`),
  ADD KEY `fkc_account_receiver` (`fk_account_receiver`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `project_teams`
--
ALTER TABLE `project_teams`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkc_Current_Project` (`fk_Project`),
  ADD KEY `fkc_Project_Team` (`fk_Team`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subtask`
--
ALTER TABLE `subtask`
  ADD PRIMARY KEY (`id`),
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
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkc_Project` (`fk_Project`);

--
-- Indexes for table `task_comment`
--
ALTER TABLE `task_comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkc_Subtask` (`fk_Subtask`),
  ADD KEY `fkc_Author` (`fk_Author`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `updates`
--
ALTER TABLE `updates`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkc_UpdateAuthor` (`fk_Author`),
  ADD KEY `fkc_UpdateProject` (`fk_Project`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `contract_info`
--
ALTER TABLE `contract_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `employees_tasks`
--
ALTER TABLE `employees_tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `employees_work_status`
--
ALTER TABLE `employees_work_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `private_message`
--
ALTER TABLE `private_message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `project_teams`
--
ALTER TABLE `project_teams`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `subtask`
--
ALTER TABLE `subtask`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `subtask_status`
--
ALTER TABLE `subtask_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `task_comment`
--
ALTER TABLE `task_comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `updates`
--
ALTER TABLE `updates`
  MODIFY `id` int(14) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`account_role`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`work_status`) REFERENCES `employees_work_status` (`id`),
  ADD CONSTRAINT `fkc_Team` FOREIGN KEY (`fk_Team`) REFERENCES `team` (`id`);

--
-- Constraints for table `contract_info`
--
ALTER TABLE `contract_info`
  ADD CONSTRAINT `fkc_Employee` FOREIGN KEY (`fk_User`) REFERENCES `account` (`id`);

--
-- Constraints for table `employees_tasks`
--
ALTER TABLE `employees_tasks`
  ADD CONSTRAINT `fkc_List_Employee` FOREIGN KEY (`fk_User`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fkc_List_Subtask` FOREIGN KEY (`fk_Subtask`) REFERENCES `subtask` (`id`);

--
-- Constraints for table `private_message`
--
ALTER TABLE `private_message`
  ADD CONSTRAINT `fkc_account_receiver` FOREIGN KEY (`fk_account_receiver`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fkc_account_sender` FOREIGN KEY (`fk_account_sender`) REFERENCES `account` (`id`);

--
-- Constraints for table `project_teams`
--
ALTER TABLE `project_teams`
  ADD CONSTRAINT `fkc_Current_Project` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`id`),
  ADD CONSTRAINT `fkc_Project_Team` FOREIGN KEY (`fk_Team`) REFERENCES `team` (`id`);

--
-- Constraints for table `subtask`
--
ALTER TABLE `subtask`
  ADD CONSTRAINT `fkc_Task` FOREIGN KEY (`fk_Task`) REFERENCES `task` (`id`),
  ADD CONSTRAINT `subtask_ibfk_1` FOREIGN KEY (`status`) REFERENCES `subtask_status` (`id`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fkc_Project` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`id`);

--
-- Constraints for table `task_comment`
--
ALTER TABLE `task_comment`
  ADD CONSTRAINT `fkc_Author` FOREIGN KEY (`fk_Author`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fkc_Subtask` FOREIGN KEY (`fk_Subtask`) REFERENCES `subtask` (`id`);

--
-- Constraints for table `updates`
--
ALTER TABLE `updates`
  ADD CONSTRAINT `fkc_UpdateAuthor` FOREIGN KEY (`fk_Author`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fkc_UpdateProject` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
