-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2017 at 10:47 AM
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
  `enabled` tinyint(1) DEFAULT '1',
  `active` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `email_address`, `first_name`, `last_name`, `date_of_birth`, `phone_number`, `gender`, `nationality`, `work_status`, `account_role`, `fk_Team`, `enabled`, `active`) VALUES
(10, 'adminas', '$2a$10$6KbN.YzXE3hIselwx.z0yOJo79jwEnE/kA3bqQXO/8cdTcjFzHcH2', 'admin@ems.com', 'Adminas', 'Adminiskauskas', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `contract_info`
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
-- Table structure for table `employees_tasks`
--

CREATE TABLE `employees_tasks` (
  `tasklist_id` int(11) NOT NULL,
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
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `project_teams`
--

CREATE TABLE `project_teams` (
  `teamslist_id` int(11) NOT NULL,
  `number_of_teams` int(11) NOT NULL,
  `fk_Project` int(11) NOT NULL,
  `fk_Team` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL DEFAULT '',
  `role` varchar(20) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `username`, `role`) VALUES
(1, 'adminas', 'ADMIN'),
(2, 'adminas', 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `subtask`
--

CREATE TABLE `subtask` (
  `subtask_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL,
  `fk_Task` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `task_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creation_date` date NOT NULL,
  `deadline` date DEFAULT NULL,
  `completion_date` date DEFAULT NULL,
  `fk_Project` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `task_comment`
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
-- Table structure for table `team`
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
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
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `project_teams`
--
ALTER TABLE `project_teams`
  MODIFY `teamslist_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `subtask`
--
ALTER TABLE `subtask`
  MODIFY `subtask_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subtask_status`
--
ALTER TABLE `subtask_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT;
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
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`account_role`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`work_status`) REFERENCES `employees_work_status` (`id`),
  ADD CONSTRAINT `fkc_Team` FOREIGN KEY (`fk_Team`) REFERENCES `team` (`team_id`);

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
  ADD CONSTRAINT `fkc_List_Subtask` FOREIGN KEY (`fk_Subtask`) REFERENCES `subtask` (`subtask_id`);

--
-- Constraints for table `project_teams`
--
ALTER TABLE `project_teams`
  ADD CONSTRAINT `fkc_Current_Project` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`project_id`),
  ADD CONSTRAINT `fkc_Project_Team` FOREIGN KEY (`fk_Team`) REFERENCES `team` (`team_id`);

--
-- Constraints for table `subtask`
--
ALTER TABLE `subtask`
  ADD CONSTRAINT `fkc_Task` FOREIGN KEY (`fk_Task`) REFERENCES `task` (`task_id`),
  ADD CONSTRAINT `subtask_ibfk_1` FOREIGN KEY (`status`) REFERENCES `subtask_status` (`id`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fkc_Project` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`project_id`);

--
-- Constraints for table `task_comment`
--
ALTER TABLE `task_comment`
  ADD CONSTRAINT `fkc_Subtask` FOREIGN KEY (`fk_Subtask`) REFERENCES `subtask` (`subtask_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
