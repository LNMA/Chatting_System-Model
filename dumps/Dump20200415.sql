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
INSERT INTO `account` VALUES ('louay','12345678','2020-04-09 19:59:38','client'),('louay1','12345678','2020-04-13 22:53:02','client');
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
INSERT INTO `account_detail` VALUES ('louay','Louay','Amr','1994-08-10','30 day, 7 month, 25 year','male','0096','louay@project.xom','JO','AM','qatar street'),('louay1','Louay','Amr','1994-08-10','4 day, 8 month, 25 year','male','00962','louay@gmail.com','JO','AM','qatar street');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
INSERT INTO `account_pic` VALUES ('louay',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0T\0\0�\0\0\0|T��\0\0\0sRGB\0�\�\�\0\0\0gAMA\0\0���a\0\0\0	pHYs\0\0\�\0\0\�\�o�d\0\0C`IDATx^\�\�}�\�~�_{\��}3��\�\����	�\�ɍ�DJ��\�D�K��HI&��l�8\�=�z!�\�5���1\�\�8 ���XKF(r%s9\�\�����\�hFY�գv&�]\�Oyz\�\�\��\�]\����~\�U\�\�\�\�\�\�5�o?�T�\�U\0\0\0\0\0��}\�\0\0\0\0�MT\0\0\0\02	T\0\0\0\02	T\0\0\0\02\��\�\�tQ*\0\0\0\0�Z�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�M\�\�pQ*\0\0\0\0�Z�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�}U�uW�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�\���k.J\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L�z�UW�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@�\�N�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�Z#>tQ*\0\0\0\0�Z�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�5㯸\�?\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\�~g�e�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�ځ�W�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�\�{�\\�\n\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j\�/�\�?\0\0\0\0@-T\0\0\0\02\�\�Z�\0\0\0\0d\�B\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 S\�sq\�U�\0\0\0\02h�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0����\�/�(\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02զ\�ϻ\�?\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\�ǟs�\0\0\0\0�Z�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d��\���.J\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�L���3N�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�\�WǟvQ*\0\0\0\0�Z�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d����yW�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�\�\�?\�T\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T��?\�*�\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T�}�\'\\�\n\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0�jG\�;\�\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�jG㏹\�?\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\�~�Q�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�ڱ�#��\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L�\��s.J\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L�P�z;\r\0\0@�\��si6-T\0\0\0\02վ&~VU��\'\r\0\0@����MC��i�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�����\�T0\"�\�\�\0\0\0\��|\\NC��i�\n\0\0\0\0�I�\n\0\0\0\0��v\"~\�)�0\"\�\�!\0\0\0\�w\"~&\ræ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�\�\�]�\nF\�\�\�率\Z�\��{\�i\�M�#>���uN-|7fN\�\\\Z_\�\�gK[�w\�R\��\�\r\�?�p?\r\0\0��?���a�\�m�\0\0�[Tat��\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0��v2~\�E�`D>����\0\0\0 \�\���4��P	�qc\�l<84�\�\�Ÿ�/\0\0\0`R	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\�ūPJ��\0\0\0`+c*��SZ�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0dr��FX\0\0\0���1�R\�)-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\�N\�O\�\�F\�A��4\0\0\0�N\�O�!`\�j_�KU��\��4\0\0\0���\�0lN�\0\0\0\0\�$P\0\0\0\0\�T�\��	��È����i\0\0\0�}m�D\Z�MU\0\0\0\0�LU��ٸ1s6�M\�\�b\\\�\0\0\00��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\�\�U(�FS\0\0\0���1�R\�)-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02�(�R#,\0\0\0؊AǘJ�\��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j_?�#G���(\r\�ƍ��1\��$N}�,M\���\��\��\�\�4�\�\�\�\�:�>\�m[i|�S�-my\�K���G�\�{u\�\�:;�p?\rO��Kc\�\�\�g��\�:�\�6��\�뻟}\�/�Fn�m�>|�I܇�\�6�\r�\����!��ߤ\����u�\�i6�*�\�j�\n\0\0\0��0:�\��cU���\�\0\0\0\�;?���aӇ*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�}�xJ�\�Ԫٸ1s6�M\�\�b\\\�0y��?�?\�Y��\�ilRT�\�K\�t\��87\Zi�p�W\�2^�ǠcL�\�pJU\0�H\�>�|��\�?,̧qޕ}8\0��*\0\�\�8s�7[\�\�\�ǳ�\�Z���+�\�$�������G�\�q+M\�]ه���0$\�Js:be1n�L�+�ڋ�(\Zq�~0M\�}g\��Xw��<Ma+\�\� �@�\�h��Sw[\�^�Ęy�0��#�7�9\��ٸ\�lD��\�U?\�m�}8\0d�0��\�\���\�\�\�8\�\�V{���\�8\�<�0t����\�֕\�N��<��\�&\��qI�v�a�q\�\\��k��3i�\�(��y\Z\��X�3\�\�aOt���\ZNi�\n��;S?\�c)��п\��{W[KS�\��\0�i�L�ӕ\���Z�*5\�P�o��u\�X^��ш�\�\�4`\�\�7�t�ӕ\���=c\�1�Rj8��*\0�����͖�\�&ǳ�\�Z���+Z8�AW2�\�>\06#P`��F\�\�b\�v�\�r����\�\�\���\�\�Jf9\�u%�\�\�`cU\0vO\�X\\Կ\�dz�0��#�7�\�|��3ʮd�\�U?\�\��p\0ؐ@�ݳ|?N-|\�\�\�n\"\���\�\�\�\�\��{\�w����TW2�\�>\0\�N�\n\0\0�m�qc\�l<xKE\���\�e\�h�\�\0\0�W�r��K�È�j�\�4\0\0\0��?���a\�B\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 S\�\�\��(�\����;\r\0\0@���?���a\�B*a6n̜��f\���\��ś|\�dj���m\�F#�\�\�\�\�\�u�k\�t\Z�\�\�\�\�m\��W\�\��\0v�@\0\0��\n\r\�\Z�i|;vr^;\��\0\�O�\n\0\0T\�|}:b\�i\\[N*c6\�\�#�ǭ4e;n��ƣ��K�)U�,��:+�~4�\�O�\0`�	T\0�\�\�\".�e{!\�IUq\��ј�\�\�~��l\�˅��q�>gҤ��\�^�Gшs����\00|U\0\0��\�G\�x,\��;Z\�ٸ\�lD��\�՗iҶ=������#q�j\��|\�\�Ǜ\�*\�%\0׾Z�\n�\�h\n\0���N���⎜R��\Z�c�ss���7�S�\�n\�f�^��Jo��:����.��0\�S)5�\�B\0\0������-�\�`\\i\�V��\�t�q\�\\��k��\\�\00LU\0\0��ƅ�N�R�C�\�Ĺ������ү\�O\�n%�+-�$8\\�\n�	&P\0\0��q,.�\�N�R�\�~]?\��֩�g�QU�+\�vIЈ�\�\�uI\0\0\�\"P\0\0*f7O�ߦ�\'\�R=\�Q\���\�z��4\�t\\:P�V�\�\0\0�\�E��\Za\00@:��Q{aWN�ߎ3�\�q<�\�N�y��K^.ĝ��\���\��Wz���*\�%L�AǘJ�\��\0\0@���\�_�ˡ\�;��\�\�a�\�Z�Wz$.W\��/\���vI\0\0C P\0\0*\�֧ǩ���zJ�\�<��:\��\��u]�\�Y\��U\������\0\�>�*\0f6n̜�o�h�8���C���\�w�̜\�;\0\0\0vW\�\�u\�#�\�⟥!\0\0\0\��\r�\�i6-T\0\0\0\02\�\�\�i�\n#r/�y\Z\0\0�|g\�\�0lZ�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�B%\�ƍ���\�\�l\Z\���xSu?\��C�\�9�\�&E��{�ӝ\�\�l\�h��=\�6^-�q`������3�\�\�U(�FS\0\0������c*��SZ�\0\0�\�8s�7[\�҄꘯OG�<�k\�i\�\�<�k�\�Bԏƕ�iRU,?��+s\�q&M�q&P\0\0F\�`\\i�\�b\�~�&U\��q���ҤQ�\�^�Gшs��iJU<�\�\�利\�q�ja/\0\���+5\�\0�x�cqq*\�n\�a%B\�~�\�#q<�\���\�iʈ�|\�\�Ǜ\�b>M��{/�\�\�h\�\�f�$�15\�S)5�\�B\0\0�\�)�������f\�|�s\�^�[�	�p��\���t��\\�\�\�v\�~�ra/\0\�4�*\0\00\Z\�)��Ǖ\n-g�f��\��V\�i<�\�t�j]\0�\��\0\0#q�~�Z�Կv0.\�\�\'q�j��\��\�Z�f�e���`�	T\0���\�\�\"��\�)�]e��튵N--/V��\�n�SG\�r\�$\0��#P\0\0����\�\���Ҝ�Xy\Z\�*ׯk\�Y\\ku�~4�T��j��$\0��\�*�J��\0\0&S:�~e1nW\���3qn*\�Q{!\�IUt����\�\�U\��y\�n/W�K3��1�R\�)-T\0�\�*O�o=�\\h9\�<R\�~]\�)�+m�\�U�\�xR\�.	\0`\'T\0�\�Z��>��+xJ��O?\�,\��\��\�:@u��Y�\��|O}Z\�>h`��\0\0\0\0\0�j\�?�#G��\Z�\"\r\0\0@�o�HC��վ9�_�\n#�_\�_�!\0\0\0\��\���i6��\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�B%\�ƍ���\�\�l\Z\���xSu?\��C�\�9�\�&D\�t\�=���4^%U^�\r\�\�+\�6\����D\�|�\�\�W�W��\ZM\0L�2��O\�\�1&?\0W8��\�g{۠cL�\�pJU\0\0`�\�\��zģ\�㸕�TF\�p\�\�r\�l=K��\�\�qs%b�y\"ΤIUq��4\�t\\:p0M��M�\n\0\0\�3�vC\�;\�\�iJU�+\�鈕Ÿ�2Mڳ�\�\��r\�\�Ḱ?M���qg%\�x}�ra/\0l�@\0\0\�E�q�وh?��U-\�\�\�T\�\�\�ø�&\�e�^<��ш�ͪu_�<���\"��\�e�C0�\0\0�\�\�Rq�]�S\�\�\�ӝ�/\�G˽�\�Y|\�\�\�\�W��\�\�Ÿ۹��\��j�\�E��\Za\0�����q�j�\��q����nCu�+}\�Z���~4�T�Kأc*��SZ�\0\0�c�L��\�)�g\�\�x,\��U\�\�u�^>�\�\�j�WZvIp�\�\�T\0\�mU\0\0`W\�7�tC\�\�R_�\�8V�SK�\�U\�\�%��\�\�uI\0\0\�@�\n\0\0\�\n�R\��ј�\�٪^��;b�q\�\\��k��\\+\�\�vI\0\0��\0\0��\�R�w\�U;��`\\�7\"V\��\�4i\�<�\�\�利\�q�j���\\�;+\�\�\0\0r	T\0�V�R�$�V-�l��\�\�u\'���^lV\��\�\�j��]\0@W�Wj�\00��\��ǩO+xJ���8\�Y��+ׯ\�N���t���\ZNi�\n\0\0Ph��3g\�Z�O/�h�{c���vs\�U�*\0��ڷ\��i&#�\�_�!\0\0\0\��-�}i�ڷ\��\nTaD�S�\�4\0\0\0��5�7\r\�\�\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L�j�*�R��U�qc\�l<84�\�\�Ÿ�/\�T\�Ϻq:t�\�F#�O�*�\�=�yTw�?\�Y��\�ilB\�Ɓ=ÿ��e�>�AǘJ�\��\0\0��	�\�?���T\0�m��OG�<�k\�idy\�Z���~4�\�O�\0�\��\0\0��\�?�\��\�q/M�\\�ڋ�(\Zq�~0M�j�\0\0\�2\�<\�c)��x���;x�0��#�7�\�|�\0U&P\0\0�a6\�\�;7\�Ÿ՛\0\�\�V{���\�8�^\0�\0W�Wj�\0�ם9p4\�b9n���)�ˏ\�\�J\�\\�D�I���\r:\�TJ\r��P\0\0�\�`\\�7\"\�O\�\�\�4	�\�y\\m-EL�.N@\�	T\0��i��Sw\�Z���\�n4\�bs6M\0�j�\0\0[p0�4�#V�Ƶ\�4	�\�Y\\ku6��Ѹ��*\0&P\0\0\�\���87��\�$خ{\�\�x�8W?��\0@��(�R#,\0��j�y$�\�R\\�<M���a\\oGo��4	l\�1�Rj8��*\0\0�\�n}�q�Z��\�8\�\�\0UW���\�\�`D�c��447fN\�\\��8�\�8]\�a��j��\�M�����a=��sj\�\��\��z�>[�\�#��\�\�\�p\�\�\�~�q�?)}�U�=\�\�ϣ�\�������\�$\�g�\�\�\�@\�;�F\�٫���̿��e�>�o�\�IC��	Ta�VU\0\0\0\�\'P�\�q\�?\0\0\0\0@&�*\0\0\0\0@�\�\\|�S�aD\�ƿMC\0\0\0�o.�;\ræ�*\0\0\0\0@&�*\0\0\0\0@&�*T\�lܘ9ͦq\�׸~\�~_�\��l7\Zi|\"�<v^u\�\���\�r͜Nc�\�6\�\�\�O�\�K7\��\0v�@\0\0\�S\��X�m�1��\�\�8\0�ھZ�\n�\�h\n\0`/��OG�<�k\�iBU4\�\\,\�\�ֳ4�w��8n�D\�5Oę4�*n��ƣ��K�)@a\�1�Rj8��*\0\0���\'\�R=\�Q{!\�I\�p0�4��w1n�L�؂\�q��1u8.\�O��\�\�B\�Y�8^��\\\��d�\0\0��o�\�\�_<OS*�q,.NE\�m=�Xл�\�{�$\�F#.6�\�u\��\�Z��:��O@8\�_�\0�\�0\�띛�b\�\�M��n7�U��=\�Y|\�\�\�\�W��\�\�Ÿ۹��\�\'J��1�R\�)-T\0�\r�9p��}��\��W.\�ݫ�\�_鳸\�Z���+U\�\0��#P\0\06p0.\�\�\'q�b}����f7{\�ˇq�]\��J\�.	\�\�]�\n�\��\0\0oW�Qڮ\��g\�r�z�\�\r�^w�]\��J{]o�^�\0L�*\0\0�\���Ƶ��QZ\�n\���㸹1\�<Q�V�\�\�\0�I\�TJ��\0\0*m�L���x\�^�\��S7+�q�b\���\�q��1u8.T��җqg��]��\r:\�TJ\r��P\0\0�o�f�e7��z\�G\�_\�\�fծ��<���\�%\0�B�\n\0\0t\�ӏ\�\�\��\��Q�|��\\\��\�`�<��;\��ԧ\�R�\���\�\�\�\�È܉\�ih6n̜���\'\��G+;h\\?\�\n��\�\�x�\�t\��l��k�\�\���\�^\�\�\�\'���\�\�;�\�Rw~o�A;F2n\�ѳ�>����봷=��\�\�z��\r\�\���6\�7�\�U1`_��ao3�\�lw\�o�;p�|�8\�0lU�\�@\0\0\0�	Tat��\0\0\0\0���\��]Z����wi\0\0\0�}{|W\Z�MU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU��ٸ1s6�M㌯q��m\�dj���m\�F#�\��q2\�\�m\�-�:?fN�1x?`O\�W�W��\ZM\0TKu<(C��4\�pUv���`��1�R\�)-T\0��\�ᘋ\�\�z�&T\�l��G<j=�[i\n\�u��4\�t\\:p0M��\�\�qs%b�y\"ΤI\0�\��\0\0@\���Ҝ�XY�\�/Ӥ�8s\�h7\�\�~��0t/\�\�J\���Lł\�\�q��1u8.\�O�\0`�	T\0��Ʊ�8q��0\�I\�0�������Z��w�<������#q�b�\�\�{�$\�F#.6�s\r�pT\0���Ow��-�\�+�\�\rA\�\�vպ!�@ˋq�s3W�Zp�,>jwn\����PT\0`\�\�?�*\�Gi\�\r�ӸV��w\"=�k�\�Q?\ZW*vz}e�x`,�ʿR#,\0�*8S?\�c)���X��g\�\\%�!�\\\�\��\�\�._>�\�\�*��\n�g\�1�Rj8��*\0\0L���\�\�\�]A�y��V���\�;��x�X\�N��ծf�\0��*\0\0L��\n�7[룴�\�P\�\�\�\�͕��\�	�T\�UU\0\0�X\�B����+v�^7\�q�]�n�x�wV�xz��\�^��:*\�\�+\0\�E�\n\0\0��q,.V��Ҳ�\'q�bA/�\�q�U\�\�\�\�>^/6g\�\0\�y.J�\�\0`��\�ǩ��\��\��Q�,\�\�,שO+\�\r�l;0r��1�R\�)-T\0\0\0\02\�\�\�wj&#�Q��4\0\0\0�\�\�w�!`ئN\�~!\rC�\��\0\0\0\�]��/�!`؜�\0\0\0\0�I�\n\0\0\0\0����q^�0\"�>>JC\0\0\0�\�;\�|\Z�MU\0\0\0\0�LU��ٸ1s6�M\�/�5\0\0�\���\�1\�\�\�4TݾZ�\n�\�h\nƟ\0yrM\�go��\\{��/\�\�\�xe4Nw�\�l\�h�q��\�nW��Vw���:\�TJ\r��P`=�k�\��Ѹ�?Mb24\�\\,\�\�ֳ4aپ\'֞ݾg\�|=\�Q\�q\�JS�b�>��4�u�R\�=�ZO\�QLǥӔ��\�~��\��U\0vս�b瀡\�\�&\���\�,Ÿ�2M\ZS�\�I�w�\�3�v�\�;\�\�iJE\�?������\�$���qg%\�x}&ΤIUQ\��t�\�\0����^>�\�\�\�C�\�\�&E\�X\\���\�z8�\�\�{�\�\�\�{6.7\�\'q�bA�|�H�����bA/\�\�y\\m-EL�\�U붡��\�\n�3\06%P`\�\�jwb:\�;`�\�Swc)>��Swmߓe\�n\�\�n\n\"\�MA��h/V�\�\��b\�\�\�\�ի\�_ie�\�^g\0l\�E��\Za�\�X~7W:\�Nkw婻\�q\�ؾ\'Ǟݾ\�n\n�\�Gi\�\r�x��<)*ܯte�\��\�f{c*��SZ�0\�im�\���v�~xOݵ}O�=�}s�\�\�`\\�7*\�\r[s\�œ�[\�~�����\�:`#U\0��{Z[#.6�\�6�\�>\Z\'�\�]\��ػ\�w\�Gi\�)(���\\7lݳ����JWv?]\�u�[	T����\�>u\��=\��\��]\�n\n�\�\r\�s��4\�t\\:P��\�\�OWw��6U\0�\�^{�s�ശ�N\�]Y�\�z\�\�{�\�\�\�\�M�r\�iW���\�\r���Bź!`\�^.ĝ��\���\��+]\��t�\�\0�	T��\�\�\�\�Sy\�n\��h\"\���ڳ\�w\�MA��(-�!����\'E\�_鑸\�\��*���\�\n�3\0�}W|�K�È���ih6n̜���\'q\�S���7�5\0\0�\�����a})N-\�OS6�]�\�i6�*�І�j\�t<xo�7<�\�\���M#\�Z��7�4�ީϖ�<\��\�#y\�:\�b���\�H���m�\r㱍\�e�~�\�j\�ߣ4�\�n~\�o\�ٽ\�l��\�\��\�\�9��\�>G�\n{�@Fh5P\0\0�|U}�\0\0\0\0d�\0\0\0\0d�]�sN���w\�\0\0\0\�\�\�0lZ�\0\0\0\0d�\0\0\0\0d�\0\0\0`6n̜��f\�xu\�\�,\�\�\�4T�@\0\0\0\0 �@\0\0\0\0 ӾZ�\n�\�h\n\0\0\0�b\�1�Rj8��*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�Rj�\0\0\0[1\�S)5�\�B\0\0\0\0 �@\0\0\0\0 �S��\Za\0\0�V:\�TJ\r��P\0\0\0\0\�$P\0\0\0\0\�T�\�s\�1�ȿ��i\0\0\0�}w̥!`شP\0\0\0\0\�T���6-TaD�M�\�4\0\0\0��\'�-\ræ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\00\Z�\��\�ٸ\�H\�17:\��\�\�l\Z�L�\n\0\0\0\0�i_-^�Rj4\0\0\0[1\�S)5�\�B\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 ��R)5\�\0\0��t���\ZNi�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�\�U��\Za\0\0�V:\�TJ\r��7�U�#�\�?�!\0\0\0\����i6��\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�}_|�>TaD�U�\�4\0\0\0��/�%\ræ�*\0\0\0\0@&�*\0\0\00\Z�\��\�ٸ\�H\�17:\��\�\�l\Z�l_-^�Rj4\0\0\0[1\�S)5�\�B\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 ���+5\�\0\0��t���\ZNi�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�\�E��\Za\0\0�V:\�TJ\r��P\0\0\0\0\�T���f\�\�`D�e��4\0\0\0��?�9\ræ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�\�\�7\�CF\�_\�MC\0\0\0�\�\�\�0lZ�\0\0\0\0d�\0\0\0�R\�t<�97\Zi�4�*\0\0\00V\���3�c>�\�$�*\0\0\00Ff\�|=\�Q\�q\�JS\0vҾZ�\n�\�h\n\0\0��u\��ј�\�\�~����AǘJ�\��\0\0\0����\�\�FD�I\\}�&\�0�*\0\0\00\Z�c�ss���7��\0\0\0�8W�\�+O\�\�r���\0\0\0�޷&\�ME\�m=�{i�n�\0\0\0{\�|�H���H\�T`�\�\�\�]jF\�V�J\Z\0\0`\����_�ܑ�֯\�w�؃W�o��\�M\�\�\�>��3\���\�4��\0\0\0��v�~8�\�r\�i\��0\�s�\0\0\0�6��������2M\�E��3N���\�J\0\0`���P�IC��	Ta�6T\�Է���a=��sjᓸ1s2\�\��z�>[\��#�:�O�\�\�v\�\�\��;o\�\�Mۙ�\��`ۚ�u�\�l�ݜ�u��\�\�۾r�m\�\�:{\�v\�=�\�\�]B\�\��\"P�\�B\0\0���\�\�\�C\0\0\0\0 �@\0\0\0\0 S\�\���#�\�⿥!\0\0\0\��\��\ri6-T\0\0\0\02	T\0\0\0\02	T�f\�\�\�\�xph6�\0\00)\�u�gN�1�\��\0\0\0\0\0��\0\0\0\0\0��\�\�U(�FS\0\0\0���1�R\�)-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02�(�R#,\0\0\0؊AǘJ�\��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j?_v�q�\Z���\0\0\0 ߏė\�0lZ�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d��h�A����\��IC\0\0\0�\�G\��!`شP�J��3g\���\�4\0\0\0@	T\0\0\0�Bc\ZU\0\0\0�ah��X���gi�	T\0\0\0v\���Ҝ�XY�\�/\�$`O�\0\0\0\�Ʊ�8q��0\�I�޴��B)5�\0\0`2\�ק;�_���{\�\�5\�S)5�\�B\0\0\0`7\�?�\��Z�\�V�\�]Z�*5\�\0\0`����\�\�_<OS�o\�1�Rj8��*\0\0\0����\�\�FD{Q\�TU\0\0\0�]r\��ј�\�\�z��\0{�@\0\0\0`W��F\�\�b\�~�&{�@\0\0\0`74�\�ũ����q/M�>�*\0\0\0�nX��>���\�80j?�]jF\�\��447fN\�\\��8�i\�W�q:�7\�\��vć�4�Ω�Oz�K\�\��li\��X\�\�?=�\�\�y[go\��7mg޾��mk\�\�\����vs\�\�ٛ�3o�\���5o\�\�\r۝�u��\�\�{�\�}q����o\��X�NC��	Ta�VU\0\0\0\�\'P�\�q\�?\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�ڏ\�\�\�CF\�\��LC\0\0\0�\�\�\�\�\�0lZ�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d���U�aDn\����\0\0\0 \���\�0lZ�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d��D|��R���\��_i\0\0\0��D|m\Z�MU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L�K�%W���_IC\0\0\0�\�R|)\ræ�*\0\0\0\0@&�*\0\0\0\0@�\�O\�)��È��x��\0\0\0 \�OƩ4��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j8�\�T0\"�g�Z\Z\0\0�|8����a\�B\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 S\�⤫�È���$\r\0\0@����i6-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\�~:�\�T0\"/~=\r\0\0@���/�!`شP\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T��8\�*�0\"7�!\0\0\0\��3q\"\ræ�*��k��3g\�F#�Ø�?t���c\\ٟ&t�+\�\���N\�|�g�vs\�{\�\�u3��ϙ\�\��y\�\�\�޾�\�\�\�;\0\��*��\�@L��3ij��{\�\�rO�O\�/�\�\�Ly��AW�N�{wR#NL���\�qu\�\�8��qs%M\�1�9\�l6n\�|9ε��n:\�\�7�\�\�\�\��\�y�\�;4�&���\�W�����\�4*�\�\�GQ\����\�_��\�{\�\��۟_|7��\��o��\�뛵��7�\�\�^{\�˽\�k���0�\�<�}Y�^��\��HU��b�L����\�^��:�c�\�\�\�\��X\�|^\�\�3\��O���dY�\�ԝq\�\�\�|ߗ��ԙGcn\�i���\�	/\��v#.6\�~`\��\�\�D�z?8\���\�_|E��K͈��Y\�ϊk:ߝϥ�/=�x��\�>��ο\�\'�\��ޏ��\�l\�����W_�\�?t2>�^���\�QLǇ\��\��\�\�Su\�\�h/ƭ\�q�o�\��}���\�\�\�\�}{Y\�&w\�Ϻ�=˫\�Vԧ��u\�\�\�\��F\�ߟ\�(�O��3\0&�@`��jv��Z�΁D#\�\�W[O�?���eg�Uŗ\�b�U^\�\�la��u\�T_\�u-`׷�X=M6�|-\�׺\�m-;\�O߶��V�hy��i�\����\�\�k���/Muf\�9ػ�\�9�\����>t:�\�Ɓ�=�\�/�\�\�u\��6\�a6]g�\r��\��ث��\�k\�pF��Qm\�\�{\�\��l\\n6\�nk!.�-\�;���\�{\�,\�}\�[\�Iy\�\�\�7��}VwT3\�]���\���o�\�lgk�\�\'���\�B\�\�\�\�o˟Ǹ\�~�\�S\�\��b\��\��g�1\�����UO�y�\�\�\�\���\�S�w\�œ�۹-~�+����D�I\n��\�\�\�R\��o�\�y}A\��\�n\��\�?\�5�u�-p����t�\"�|$�\\�;E\��\���K�)k��s9n�z�u\���7yي\�H�\�^\�uֿ�Z\�V�Ƶ�\�\�e�d��m��3\0&׾Z�\n�\�h\n\�\�|}�s�Њ����\�S\�=\�\�Z�r��\�¢�uFa�ٌ\�\���e\�ԑ�\�w��a}�e\�\�\�c\�8=�p�\�{?�^\�x�\�\�\��\�Mǣ�5\�-q6\�y��vZi�\�\�\�P\�*Z����\�w���\�8_�x��\"\����\�\�\�\�x����N�?�Z�O뼫���\��Jj���\�\�\�l6n\�\�g�Z\'u��2p�\�i\�E�s�\�Z��\��I\�\�o��\�܆7�w\�V�\�\�\�+~�\"�\�쏊\�m�\�v+�\�[\'\�.��\�Q�v\�T^�\�M^;{�Si��t��\�y�]<��\�o��\�\�&�\�\�c,\�U�\�Y�;�~q\�\�1\�Y�O^��տM9\�Y߼\��],\�\�_*~H-\Z?(ZXng\�/+����\�\�g\�ۖ�ߏ�\��|g��\�\�\�y�m��\�	R(\�=�\�\�\�\�!tρ\�߭�eym���\��+�q;m��\�XEp}��~\��\�6�{��ݯ\��s����z��޲�\�w\�g�zٚ�e.\�_�n�\�:�\�t���\ZNi�\n�\'���{��<~�G\�w\�୴��\�秊�\�\�뮶*��\�\�#�AMt�\�|���l�\�EzЌ/��e��z\n\���mҢ\�\r\������N\�\�^?�t\0���8��J74Z�;\�β�V:s��@�\�@�̓��\�\�;%���\�$�ͽW\��Z_\�p�=���~ ��\�\�\���᝱\�po;\�|�Z\�w��,��on���\ro4\�\�V�\�\"�\�|>_,��\�\��J7�\�\�u���v\�>\�\�{�3�\�5/L\�H�\�>�\�\�c\\��ە��k?���`͏�i\�\�\��]<\�\�:_m\��c}�]���n������;��K+�\�\r��e/�C\�\�\��\�G\�\�oapG\�r\���\�M\�*�\�=s��\�P)(��-�\�`ru�zݢt\�}��p7�\�|\�#\�\�ޞ��\���v)}6�\�ӎr�ݫ���jZ�\�\�f|���Հ�]\�\0�E�\n����\�X\� >x\��\�\�\�Z�k週w\����u[�lK\�Bi5���,^K�p:�\����4C/x)\�ɳ��8~\�\0�s�\�;XL\�K�H᫵�\�T\�`��\��]\'E�=]y��7y\�k�P\�\�\\k\�Wj�Tuo�\"�\�vo\�xޙl\�=͵-�;�+[�m�l\�f���\�i\��\�\�2[�S\�\�\�\�\�oݶ���\�y/\�a7�\�\����\�r�\�\�\�\��}N�=�n���gW\�iu�+E\�\�O\�\"h]�%�\�n�\�[�fg�\�ݷ\�ʴ\�T�\�\�סf��e\��?\n�\��nW\'c�\�2�ת�u߯;�܅MN\�\����R\n\�� \�X}N���\��\�\�\0�\�)�J�� Wy����^:x[\�\�qX:\�R�4��t���<%w��\�^�U\�\�\�:\�\�⥯u\�\��\�h\���zaԐN�\���$����\���w�\���ha����ӛܿ\�k\�\�\�Xǯ\�\�\�i\�{S�5T�\�݆7��Vu\�\�J+\�h�|�_*�qE�2@�:\�y���}\�r\���w���n\n��Sg��S��[U\��~x[��]\�缋\�SԳ~|Z\';K�\�\�\�vb\�W�Z�\�Q�#Z��R|?\�~I;����n\�N�_\�\Zsi���t&�u�O\�\�4��Ku\�E���-�\��\�[B�\�״�%�V-N\�\�\�r���\�.�~\�\����5�u\�5���m�\��РcL�\�pJU�\�[=�p�\�\��cm��T\�\�Z�\r8e7G�\�۝\��\�\����S?�Iy�\��M3�.h1�\�y�q\ngO\�uA\�_\�\Z��n뗲O�5z�T�7Ov[���Z�ҩ���Y,*�鸓���\�rj�v0�4w��Xi7�\��b3s\�2\\{�\�\�\�з\�҆�ޚ^8\�Z\��\�$�\Z��$s����\��K�+Û5�\�\�m�ڹ��Xy?\�=}�\�ӂ\�ӕ\�\�\� �z\���\�\�\�\�S\�3��\�\�l����j���?L?��\�E�\���_��]^����\�\��\�^�~�\�\�j\�ʲO\�\�y�(���\�\��\�X|�\��뛯\��n��^λ�no�{\��\�]\�~j�\�p�\�\�~\���\�\�n��M\�\0K�\nPue@�\�\�) M\�>-��*Z�F�\�\�ZkS��\�\��\�֧\�B\'\�y֋Vk\�iy\�ӷ\�E�-�\�)�\�)�\�靃�5�\�\�߻Iq�Ɉ\�z�ez\�����x\�\�q}\�:\�\�vt.�\\&\�Z\���\�\�\�\���:s:M\�X\����|9N��?\�\�\�7�n���\�k�!Gy\�q�\�\�Ֆ\�\�\�]��6���x?]H�w\��\�\�\�e�1�m8w\�[6Ռ�\�U?\�V�\�Q�dw�\�I6Y\'\��Ų��?�O��]��\�/O#�-��o�yl�\�\�s\�\�Wz?z�0�\�k{\�LG_��=�\�~ݷco���{��\�\�*?\��\"Qy�D7\�\�6V��,�����\�\�3:��\�������\�Y\�\���\�nk�P�p��!�\����\�;\�ͻ��\��`\\y�9;��;E�\�!��E�\�뷽7\�I\���r\�\�r6x\�B/���\�v\�sG\"�<_\�ek\��\�\��@�\�fg\�u�$��l|��aD�N�\�4TR\�Z�s��\�\�P1Uۆ��6\�k\�\�\�\��~�s\�m\�\�6�$�\���4��\00Pj��N-��J*�\rw��߈�Zw쟍�w8%\Z\0\0F��sq\\U���a7�������uZ-{ӆ\�pj\��6�\�\�8��d�e-VywZ�\�\��:\�\0F@�\n#$P\0\0`+�0:U\0\0\0\0�L�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T�#qL�\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T����]�\n\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j,��\�?\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\��xqQ*\0\0\0\0�Z�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d����}��\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�L�?�N�\0\0\0\0Ƞ�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@&�*\0\0\0\0@�ڟ�\�\�T\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T���=��\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L�?_\�T\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T�31\�*�\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T���]�\n\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0�j.;\�\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j>�]�\n\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j!~���\0\0\0\0d\�B\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 �@\0\0\0\0 S\�J|\�U�\0\0\0\02h�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0���ArQ*\0\0\0\0�Z�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d�\0\0\0\0d��\�8\�*�\0\0\0\0\0�P\0\0\0\0\�T�K��\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T��8\�*�\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T�\��;]�\n\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j%���\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L��\�T\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T�\Z�\�U�\0\0\0\02h�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0�I�\n\0\0\0\0���W�\�T\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�T�k�\�)�\0\0\0\0\0�P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�$P\0\0\0\0\�T�\��U��\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L��S.J\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L���\\\�\0\0\0\0 ��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0��\0\0\0\0\0�j+j.J\0\0\0\0�AU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�LU\0\0\0\0�L�_�p�\0\0\0\0��\�>�\���\�իWQ�պ�\�;:\��\�r|߾}[T1\\\�\�\�\�*�S�V��\��\�b|��y��\�㦦�\��p1�\�g�\�\0\�_��u��\��x\�Ծ\�>�\��to\�~�\�w\��w�����\��\�Ry_��O1me�x\�\��Ou��V>����\�\�c��B9�B��B��\�v�2�\��\�ᢊ\��/n\�畏+\�/�X�bZ�|\�;/���*o\�\�\�\�m�\���[\�{��y�\����\�\�gWN/Ƌ��\�\�zU,w1\�rz�[�:\����\��\�u\���\�[����\�|\���\�Qޖ\�\�>we��\�x����-\�\�\�B�\�\��J\�\�U譧\�pa��\�\�I�,\�cq_��rZ�x\�o�\�o�y��\��\�_��~崢�\�\�ź(_X�z\�Wޖ�\rz^9^\�WL+�S�����,���+n�\�/��q�b�\�?\�B��\�b~�i��>\�r�S�m��V(\�s��\�|\n\�c\n\�6\�\�ޮ_�R1�|^1\\.K1�\�|^����ry�i�\�WL/��?�\�\�ӿ�;\�VN\��׽M\�\�y��Q<�3�\�x��y�\����\��\�\��\�Zwz1^�\�b�\�\�.��[�:\����}E\���\��G\�\�}\�\�|\n\�\�\�PO�>\���\��}�\�Gy[�\�rx�s���s/\�+\��W��.\�+��WT��i��\��(���\�Ǖ\�\n\�c\������Y\��\�^V�\���b�\�?\�B9�b\�\�o��Q�s��\�\�\����뗡T\�WU<��q[>�|\\yQŲ\�����^(Wޖ\�-\�\�\�b\�Wu�[\�\�\�k�5\�<�u�\ri�xL�|�\�I\�VN\�_����\�\�?^*ǋ�5\�EÅ�\�-\�Eyq[��\�?�\�*�\�\�P\��\\(W���oE1\�\���W��b�.u_o\�\��R\�\�)��\�P�\�\��q\�B�\�w��w�MSN/�\�ST�\�b��ܺ�\��V�\������\�7�7�b�)\�+\�\�\���Ny_w����\�/\�\��\�\�.���+n�\�/��q\�Bå��\�*\�SL+�\�\���\�\��_l\��R\�\�1�r��\����+\�+��*�;�\��\���[\�{��y�\����\�岕\����||9\��q\�_�/��� �[T1\\X�\�r]���\��\�V\�?��\\\�r��^nÅb;/?��;_l\���\���\�y\\�k�Å���\�p�\�\�?�\'7���z�*��D\\\�G\�\�4\0\0\0\0IEND�B`�','Annotation 2020-03-08 210620.png','2020-04-11 12:13:10'),('louay1',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0`\0\0\0`\0\0\0\�w8\0\0 IDATx\�\��\"a��CXB\�`8p8�\0�!,�C�\�\"�A�\0a0!\�����*\�|S\���\0���*XJ)��RJ)��RJ)��jb��;S���2LU��\�@1z�N��ְ%hCY,#c8 UG\�\��P�TE;�7ؓ��vfc\�I\r`9iC�����ԮgX\�zP �\�,���#\rx\�̑Gx\�\�\�\�\��\��̡%4�C1�/�9��\�0GBx\�\�\�\0\�ׂ9҄����\�l�]*�\�\�	�T=\��Q��\���.>�\�X�\�l���\�\��\�$F���g\Z!A\�\�X�2��#Re���(E781UGx\�)�Q�ʨ2Za��c�< �	)��RJ)������ \��L1�}�p�\nH\�\���kXFV\��JPt�.\"���l0A5xU\r]\�a1E\\m�x�\�!\Z���c�0!��\�\Z��.\�+�\�j#�]�5~��U0�]�!�\0ؕ���\�5�y\�\nU\�\n\�-�0\�l�y�`��\���y�\�%0OEp��Mh\0�i\0\r\�7\r�\nKh\0��RJ)��RJ)��RJ���E\�q\�n\0\0\0\0IEND�B`�','person-white-48dp.png','2020-04-13 22:53:02');
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
  `username` varchar(30) DEFAULT NULL,
  `post` varchar(1000) DEFAULT NULL,
  `postDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idPost`),
  KEY `account-account_comments-username` (`username`),
  CONSTRAINT `account-account_comments-username_FKY` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_post`
--

LOCK TABLES `account_post` WRITE;
/*!40000 ALTER TABLE `account_post` DISABLE KEYS */;
INSERT INTO `account_post` VALUES (1,'louay','Hi, This is my first post.','2020-04-14 23:05:01'),(2,'louay','Now, time to img post.\r\n ','2020-04-14 23:16:02');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
INSERT INTO `user_friend` VALUES ('louay','louay1','2020-04-13 22:57:07');
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

-- Dump completed on 2020-04-15  3:07:27
