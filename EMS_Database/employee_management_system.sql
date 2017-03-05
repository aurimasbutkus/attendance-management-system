-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2017 at 11:01 AM
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
  `user_id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_role` int(11) NOT NULL,
  `fk_Employee` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `contract_info`
--

CREATE TABLE `contract_info` (
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  `contract_id` int(11) NOT NULL,
  `fk_Employee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nationality` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `birthdate` date NOT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `current_task` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `work_status` int(11) NOT NULL,
  `fk_Team` int(11) NOT NULL,
  `fk_Living_Adress_Info` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees_tasks`
--

CREATE TABLE `employees_tasks` (
  `number_of_tasks` int(11) NOT NULL,
  `fk_Employee` int(11) NOT NULL,
  `fk_Task` int(11) NOT NULL
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
-- Table structure for table `living_adress_info`
--

CREATE TABLE `living_adress_info` (
  `country` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `street` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `house_number` int(11) NOT NULL,
  `apartment_numer` int(11) DEFAULT NULL,
  `adress_info_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `fk_company_owner` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `project_teams`
--

CREATE TABLE `project_teams` (
  `number_of_teams` int(11) NOT NULL,
  `collective_id` int(11) NOT NULL,
  `fk_Team` int(11) NOT NULL,
  `fk_Project` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles_for_users`
--

CREATE TABLE `roles_for_users` (
  `id` int(11) NOT NULL,
  `name` char(7) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `roles_for_users`
--

INSERT INTO `roles_for_users` (`id`, `name`) VALUES
(1, 'owner'),
(2, 'manager'),
(3, 'default');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creation_date` date NOT NULL,
  `modification_date` date DEFAULT NULL,
  `deadline` date NOT NULL,
  `completion_date` date DEFAULT NULL,
  `task_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `fk_Project` int(11) NOT NULL,
  `fk_project_manager` int(11) NOT NULL
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
  `fk_User` int(11) NOT NULL,
  `fk_Task` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `task_status`
--

CREATE TABLE `task_status` (
  `id` int(11) NOT NULL,
  `name` char(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `task_status`
--

INSERT INTO `task_status` (`id`, `name`) VALUES
(1, 'given'),
(2, 'being_worked_on'),
(3, 'on_hold'),
(4, 'finished');

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
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `fk_Employee` (`fk_Employee`),
  ADD KEY `user_role` (`user_role`);

--
-- Indexes for table `contract_info`
--
ALTER TABLE `contract_info`
  ADD PRIMARY KEY (`contract_id`),
  ADD UNIQUE KEY `fk_Employee` (`fk_Employee`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `work_status` (`work_status`),
  ADD KEY `fkc_Team` (`fk_Team`),
  ADD KEY `fkc_Living_Adress_Info` (`fk_Living_Adress_Info`);

--
-- Indexes for table `employees_tasks`
--
ALTER TABLE `employees_tasks`
  ADD PRIMARY KEY (`fk_Employee`,`fk_Task`),
  ADD KEY `fkc_An_Employee_Task` (`fk_Task`);

--
-- Indexes for table `employees_work_status`
--
ALTER TABLE `employees_work_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `living_adress_info`
--
ALTER TABLE `living_adress_info`
  ADD PRIMARY KEY (`adress_info_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `fkc_User` (`fk_company_owner`);

--
-- Indexes for table `project_teams`
--
ALTER TABLE `project_teams`
  ADD PRIMARY KEY (`collective_id`),
  ADD KEY `fkc_A_Team` (`fk_Team`),
  ADD KEY `fkc_Team_Project` (`fk_Project`);

--
-- Indexes for table `roles_for_users`
--
ALTER TABLE `roles_for_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `status` (`status`),
  ADD KEY `fkc_Task_Project` (`fk_Project`),
  ADD KEY `fkc_Task_User` (`fk_project_manager`);

--
-- Indexes for table `task_comment`
--
ALTER TABLE `task_comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `fkc_Comment_User` (`fk_User`),
  ADD KEY `fkc_Task_Comment` (`fk_Task`);

--
-- Indexes for table `task_status`
--
ALTER TABLE `task_status`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `contract_info`
--
ALTER TABLE `contract_info`
  MODIFY `contract_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `employees_work_status`
--
ALTER TABLE `employees_work_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `living_adress_info`
--
ALTER TABLE `living_adress_info`
  MODIFY `adress_info_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `project_teams`
--
ALTER TABLE `project_teams`
  MODIFY `collective_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `roles_for_users`
--
ALTER TABLE `roles_for_users`
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
-- AUTO_INCREMENT for table `task_status`
--
ALTER TABLE `task_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
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
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`user_role`) REFERENCES `roles_for_users` (`id`),
  ADD CONSTRAINT `fkc_Employee` FOREIGN KEY (`fk_Employee`) REFERENCES `employee` (`employee_id`);

--
-- Constraints for table `contract_info`
--
ALTER TABLE `contract_info`
  ADD CONSTRAINT `fkc_Contract_Employee` FOREIGN KEY (`fk_Employee`) REFERENCES `employee` (`employee_id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`work_status`) REFERENCES `employees_work_status` (`id`),
  ADD CONSTRAINT `fkc_Living_Adress_Info` FOREIGN KEY (`fk_Living_Adress_Info`) REFERENCES `living_adress_info` (`adress_info_id`),
  ADD CONSTRAINT `fkc_Team` FOREIGN KEY (`fk_Team`) REFERENCES `team` (`team_id`);

--
-- Constraints for table `employees_tasks`
--
ALTER TABLE `employees_tasks`
  ADD CONSTRAINT `fkc_An_Employee` FOREIGN KEY (`fk_Employee`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `fkc_An_Employee_Task` FOREIGN KEY (`fk_Task`) REFERENCES `task` (`task_id`);

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `fkc_User` FOREIGN KEY (`fk_company_owner`) REFERENCES `account` (`user_id`);

--
-- Constraints for table `project_teams`
--
ALTER TABLE `project_teams`
  ADD CONSTRAINT `fkc_A_Team` FOREIGN KEY (`fk_Team`) REFERENCES `team` (`team_id`),
  ADD CONSTRAINT `fkc_Team_Project` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`project_id`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fkc_Task_Project` FOREIGN KEY (`fk_Project`) REFERENCES `project` (`project_id`),
  ADD CONSTRAINT `fkc_Task_User` FOREIGN KEY (`fk_project_manager`) REFERENCES `account` (`user_id`),
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`status`) REFERENCES `task_status` (`id`);

--
-- Constraints for table `task_comment`
--
ALTER TABLE `task_comment`
  ADD CONSTRAINT `fkc_Comment_User` FOREIGN KEY (`fk_User`) REFERENCES `account` (`user_id`),
  ADD CONSTRAINT `fkc_Task_Comment` FOREIGN KEY (`fk_Task`) REFERENCES `task` (`task_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
