CREATE DATABASE  IF NOT EXISTS `chatting_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chatting_system`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: chatting_system
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `dateCreate` timestamp NOT NULL,
  `accountPermission` enum('admin','supervisor','client') DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_detail`
--

DROP TABLE IF EXISTS `account_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_detail` (
  `username` varchar(30) NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `age` varchar(30) DEFAULT NULL,
  `gender` enum('male','female') DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `email` varchar(65) DEFAULT NULL,
  `country` varchar(60) DEFAULT NULL,
  `state` varchar(70) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `account_detail_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_detail`
--

LOCK TABLES `account_detail` WRITE;
/*!40000 ALTER TABLE `account_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_img_post`
--

DROP TABLE IF EXISTS `account_img_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_img_post` (
  `idPost` bigint(30) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `img` longblob,
  `fileName` varchar(60) DEFAULT NULL,
  `dateUpload` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idPost`),
  KEY `accountaccount_img_post-username` (`username`),
  CONSTRAINT `accountaccount_img_post-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_img_post`
--

LOCK TABLES `account_img_post` WRITE;
/*!40000 ALTER TABLE `account_img_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_img_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_message`
--

DROP TABLE IF EXISTS `account_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_message` (
  `idMessage` bigint(30) NOT NULL AUTO_INCREMENT,
  `source` varchar(30) DEFAULT NULL,
  `massage` varchar(640) DEFAULT NULL,
  `target` varchar(30) DEFAULT NULL,
  `sentDate` timestamp NULL DEFAULT NULL,
  `isSeen` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idMessage`),
  KEY `account-account_massage-source` (`source`),
  KEY `account-account_massage-target` (`target`),
  CONSTRAINT `account-account_massage-source` FOREIGN KEY (`source`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `account-account_massage-target` FOREIGN KEY (`target`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_message`
--

LOCK TABLES `account_message` WRITE;
/*!40000 ALTER TABLE `account_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_pic`
--

DROP TABLE IF EXISTS `account_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_pic` (
  `username` varchar(30) NOT NULL,
  `pic` longblob,
  `picName` varchar(60) DEFAULT NULL,
  `uploadDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `account-account_pic-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_pic`
--

LOCK TABLES `account_pic` WRITE;
/*!40000 ALTER TABLE `account_pic` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_post`
--

DROP TABLE IF EXISTS `account_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_post` (
  `idPost` bigint(30) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `post` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `postDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idPost`),
  KEY `account-account_comments-username` (`username`),
  CONSTRAINT `account-account_comments-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_post`
--

LOCK TABLES `account_post` WRITE;
/*!40000 ALTER TABLE `account_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_status`
--

DROP TABLE IF EXISTS `account_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_status` (
  `username` varchar(30) NOT NULL,
  `isSignIn` tinyint(1) DEFAULT NULL,
  `isValid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `username-accoun_status-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='				';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_status`
--

LOCK TABLES `account_status` WRITE;
/*!40000 ALTER TABLE `account_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_request`
--

DROP TABLE IF EXISTS `friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_request` (
  `username` varchar(30) NOT NULL,
  `requestTarget` varchar(30) DEFAULT NULL,
  `requestDate` timestamp NULL DEFAULT NULL,
  KEY `account-friend_request-username` (`username`) /*!80000 INVISIBLE */,
  KEY `account-friend_request-requestTarget` (`requestTarget`),
  CONSTRAINT `username-friend_request-requestTarget_FKY` FOREIGN KEY (`requestTarget`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username-friend_request-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_request`
--

LOCK TABLES `friend_request` WRITE;
/*!40000 ALTER TABLE `friend_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_detail`
--

DROP TABLE IF EXISTS `group_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_detail` (
  `idGroup` varchar(30) NOT NULL,
  `groupPrivacy` enum('public','private','hidden') DEFAULT NULL,
  `groupCreateDate` timestamp NULL DEFAULT NULL,
  `groupActivity` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_detail`
--

LOCK TABLES `group_detail` WRITE;
/*!40000 ALTER TABLE `group_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_img_post`
--

DROP TABLE IF EXISTS `group_img_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_img_post` (
  `idPost` bigint(30) NOT NULL AUTO_INCREMENT,
  `idGroup` varchar(30) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `img` longblob,
  `fileName` varchar(60) DEFAULT NULL,
  `dateUpload` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idPost`),
  KEY `group_detail-group_img_post-idGroup` (`idGroup`),
  KEY `account-group_img_post-username` (`username`),
  CONSTRAINT `account-group_img_post-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_detail-group_img_post-idGroup_FKY` FOREIGN KEY (`idGroup`) REFERENCES `group_detail` (`idGroup`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_img_post`
--

LOCK TABLES `group_img_post` WRITE;
/*!40000 ALTER TABLE `group_img_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_img_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_invite`
--

DROP TABLE IF EXISTS `group_invite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_invite` (
  `source` varchar(30) NOT NULL,
  `target` varchar(30) DEFAULT NULL,
  `inviteDate` timestamp NULL DEFAULT NULL,
  KEY `group_detail-group_invite-idGroup` (`source`) /*!80000 INVISIBLE */,
  KEY `account-group_invite-username` (`target`),
  CONSTRAINT `account-group_invite-username_FKY` FOREIGN KEY (`target`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_detail-group_invite-idGroup_FKY` FOREIGN KEY (`source`) REFERENCES `group_detail` (`idGroup`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_invite`
--

LOCK TABLES `group_invite` WRITE;
/*!40000 ALTER TABLE `group_invite` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_invite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_member`
--

DROP TABLE IF EXISTS `group_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_member` (
  `idGroup` varchar(30) NOT NULL,
  `member` varchar(30) DEFAULT NULL,
  `memberType` enum('master','slave') DEFAULT NULL,
  `joinDate` timestamp NULL DEFAULT NULL,
  KEY `group_detail-group_member-idGroup` (`idGroup`) /*!80000 INVISIBLE */,
  KEY `account-group_member-member` (`member`),
  CONSTRAINT `account-group_member-member_FKY` FOREIGN KEY (`member`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_detail-group_member-idGroup_FKY` FOREIGN KEY (`idGroup`) REFERENCES `group_detail` (`idGroup`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_pic`
--

DROP TABLE IF EXISTS `group_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_pic` (
  `idGroup` varchar(30) NOT NULL,
  `pic` longblob,
  `picName` varchar(60) DEFAULT NULL,
  `uploadDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idGroup`),
  CONSTRAINT `group_detail-group_pic-idGroup_FKY` FOREIGN KEY (`idGroup`) REFERENCES `group_detail` (`idGroup`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_pic`
--

LOCK TABLES `group_pic` WRITE;
/*!40000 ALTER TABLE `group_pic` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_post`
--

DROP TABLE IF EXISTS `group_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_post` (
  `idPost` bigint(30) NOT NULL AUTO_INCREMENT,
  `idGroupe` varchar(30) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `post` varchar(1000) DEFAULT NULL,
  `postDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idPost`),
  KEY `group_detail-group_comments-idGroup` (`idGroupe`) /*!80000 INVISIBLE */,
  KEY `account-group_comments-username` (`username`),
  CONSTRAINT `account-group_comments-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_detail-group_comments-idGroup_FKY` FOREIGN KEY (`idGroupe`) REFERENCES `group_detail` (`idGroup`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_post`
--

LOCK TABLES `group_post` WRITE;
/*!40000 ALTER TABLE `group_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_request`
--

DROP TABLE IF EXISTS `group_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_request` (
  `idGroup` varchar(30) NOT NULL,
  `requestTarget` varchar(30) DEFAULT NULL,
  `sentDate` timestamp NULL DEFAULT NULL,
  KEY `group_detail-group_request-idGroup` (`idGroup`) /*!80000 INVISIBLE */,
  KEY `account-group_request-requestTarget` (`requestTarget`),
  CONSTRAINT `account-group_request-requestTarget_FKY` FOREIGN KEY (`requestTarget`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_detail-group_request-idGroup_FKY` FOREIGN KEY (`idGroup`) REFERENCES `group_detail` (`idGroup`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_request`
--

LOCK TABLES `group_request` WRITE;
/*!40000 ALTER TABLE `group_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sign_in_date`
--

DROP TABLE IF EXISTS `sign_in_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sign_in_date` (
  `username` varchar(30) NOT NULL,
  `signInDate` timestamp NULL DEFAULT NULL,
  KEY `account-sign_in_date-username` (`username`),
  CONSTRAINT `username-sign_in_date-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sign_in_date`
--

LOCK TABLES `sign_in_date` WRITE;
/*!40000 ALTER TABLE `sign_in_date` DISABLE KEYS */;
/*!40000 ALTER TABLE `sign_in_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_friend`
--

DROP TABLE IF EXISTS `user_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_friend` (
  `username` varchar(30) NOT NULL,
  `friend` varchar(30) DEFAULT NULL,
  `friendSince` timestamp NULL DEFAULT NULL,
  KEY `username-username_friend` (`username`) /*!80000 INVISIBLE */,
  KEY `friend-user_friend` (`friend`),
  CONSTRAINT `username-friend-user_friend_FKY` FOREIGN KEY (`friend`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username-username-user_friend_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_friend`
--

LOCK TABLES `user_friend` WRITE;
/*!40000 ALTER TABLE `user_friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'chatting_system'
--

--
-- Dumping routines for database 'chatting_system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-03 16:29:54
