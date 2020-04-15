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
INSERT INTO `account_pic` VALUES ('louay',_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0T\0\0À\0\0\0|Tùÿ\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0C`IDATx^\í\Ý}Œ\é~ú_{\â¦¯}3Á\Æ\Îñññ	±\ÄÉ£DJ¬Œ\äDŽK‰”HI&‰•l’8\á=¼z!°\æ5‚‹1\à\Ë8 ’‘XKF(r%s9\Î\ÆÁ¾¶ü\ÇhFY¯Õ£v&¾]\ÝOyz\Æ\í™\Çó\Ò]\Óýù¬~\ÛU\Õ\Ý\Õ\Õ\Õ\Õ5®o?õT­\ëU\0\0\0\0\0°©}\é\0\0\0\0€MT\0\0\0\02	T\0\0\0\02	T\0\0\0\02\Õö\Å\ßtQ*\0\0\0\0€Z¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dªM\Å\ßpQ*\0\0\0\0€Z¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dª}UüuWù\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦\Úþøk.J\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LµzüUWù\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@¦\Úïˆ«Nù\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦Z#>tQ*\0\0\0\0€Z¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dª5ã¯¸\Ê?\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\Õ~güe¥\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦ÚøWù\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦\Ú{ñ—\\”\n\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j\ã/º\Ê?\0\0\0\0@-T\0\0\0\02\Õ\ÅZ¨\0\0\0\0d\ÐB\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 S\ísq\ÅUþ\0\0\0\02h¡\n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0©ö»\â/¸(\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02Õ¦\ãÏ»\Ê?\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\ÕÇŸs•\0\0\0\0€Z¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dªý\îø³.J\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€Lµ™ø3Nù\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦\ÚWÇŸvQ*\0\0\0\0€Z¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dªýžøyWù\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦\Ú\ï?\å¢T\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈT›?\é*ÿ\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû}ñ\'\\”\n\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™jG\â;\å\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™jGã¹\Ê?\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\Õ~üQ¥\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦Ú±ø#®ò\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€Lµ\ãñs.J\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LúP…z;\r\0\0@¾\ãñsi6-T\0\0\0\02Õ¾&~VU‘ÿ\'\r\0\0@¾¯‰ŸMCÀ°i¡\n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0©öù¸\ì¢T0\"¿\×\Ò\0\0\0\äû|\\NCÀ°i¡\n\0\0\0\0I \n\0\0\0\0©v\"~\Æ)ÿ0\"\ã\ï¦!\0\0\0\Èw\"~&\rÃ¦…*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦\Ú\â§]”\nF\ä\×\ãï¥¡\Z§\ãÁ{\Ói\äM´#>¬§‘uN-|7fN\Æ\\\Z_\ï\ÔgK[žw\ÄR\çþ\é\r\ï?µp?\r\0\0°¾?†€a¨\Âm¨\0\0À[Tatœò\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0©v2~\ÊE©`D>‰¿Ÿ†\0\0\0 \ß\Éø©4›ªP	³qc\æl<84›\Æ\ÇÅ¸¾/\0\0\0`R	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\í«Å«PJ¦\0\0\0`+c*¥†SZ¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dr•¥FX\0\0\0°ƒŽ1•R\Ã)-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\ÕN\ÅO\ê\ÈF\äAüƒ4\0\0\0ùN\ÅO¦!`\Øj_ŠKU‘¯\Äõ4\0\0\0ù¾—\Ò0lNù\0\0\0\0\È$P\0\0\0\0\ÈTû\Úø	§üÃˆü¯ø‡i\0\0\0ò}müD\Z†MU\0\0\0\0€LU¨„Ù¸1s6šM\ã\ãb\\\ß\0\0\00©ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ö\Õ\âU(¥FS\0\0\0°ƒŽ1•R\Ã)-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02¹(•R#,\0\0\0ØŠAÇ˜J©\á”ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j_?®#G‘ÿÿ(\r\ÍÆ™“1\×þ$N}ú,M\ãú¾\Ø\Óñ\à½\é4ò¦\Ú\Ö\Ó\È:§>\ém[i|½SŸ-my\ÞKû§Gò\Ú{u\Þ\Å:;µp?\rOŠ´Kc\ë\í\ægö©\Í:»\á6º›\ßë»Ÿ}\ï/§Fn£mÁ>|IÜ‡\Ø6þ\r´\Ýý™¿!Œðß¤\Ûýûñuñ\ãi6*Œ\Ðj \n\0\0\0ùª0:µ\ÓñcU‘ûñ\Ó\0\0\0\ä;?–†€aÓ‡*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦}µxJ©\ÑÔªÙ¸1s6šM\ã\ãb\\\ß0yª»?›?\ÔY®™\ÓilRTø\ïK\ãt\çó87\Zi˜pöW\Õ2^ŸÇ cL¥\ÔpJU\0†H\È>¾|¶À\è”?,Ì§qÞ•}8\0¼*\0\Ã\Ó8s±7[\Ï\Ò\ÆÇ³¸\ÖZŽ¨+û\Ó$€¡˜óõˆG­\Çq+M\á]Ù‡À»¨0$\ãJs:be1n¿L“+÷Ú‹ñ(\Zq®~0M\Ø}g\íþXw§ý<Ma+\ì\Ã Ÿ@€\áh‹‹Sw[\ã^šÄ˜yù0®·#Ž79\í’Ù¸\ÜlD´Ÿ\ÄU?\Öm}8\0d¨0óõ\é\Îÿ—\â£\å\Þ8\ã\éV{©óÿ\é8\ï‚<À0t»’‰¸\ÛÖ•\ÌN°€<®ò¯\Ô&\ÆþqIÿv“aùq\Ü\\‰˜kžˆ3iÀ\î(»’y\Z\×üX·3\ì\ÃaOtŒ©”\ZNi¡\nÀ®;S?\Çc)®¿Ð¿\Ýø{W[KS‡\ã‚›\0»iÿLœÓ•\Ì³€Z¨*5Â‚\ÉPöo·¨u\ê¤X^Œ»Ñˆ‹\Í\Ù4`\ç\Í7t¬Ó•\Ì³‡=c\Ð1¦Rj8¥…*\0»ª¼úòÍ–þ\í&Ç³¸\ÖZŽ¨+Z8»AW2»\È>\06#P`ŒõF\Ä\Êb\Üvõ\å‰r¯½¢\ç\ê\Ó€\Ó\ëJf9\î´u%³\ì\Ã`cU\0vO\ãX\\Ô¿\Ýdzù0®·#Ž7\Å|š°3Ê®dž\ÄU?\Ö\íûp\0Ø@€Ý³|?N-|\ï\ë\ßn\"\Ýúô\ã\Î\ç\ß\é¸À{\ïwþ¶œúTW2»\É>\0\ÞN \n\0\0°m³qc\æl<xKE\ãôÀ\ée\Ýh¤\Ù\0\0•WûrüˆKÃˆüjü\Ó4\0\0\0ù¾?’†€a\ÓB\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 S\í\ë\ã¹(Œ\Èÿˆÿ;\r\0\0@¾¯?”†€a\ÓB*a6nÌœ‡f\Óø¸\×÷Å›|\ÖdjœŽm\åF#\ï\Õ\Ý\Æ\çu–k\æt\Z›\Þ\ç\ì\Ùm\ØöW\Õ\âó\0v†@\0\0¨¶\n\r\å\Zói|;vr^;\Ë§\0\ÐO \n\0\0T\Ú|}:b\åi\\[N*c6\Î\×#µÇ­4e;nµžÆ£˜ŽK¦)Uñ,®µ:+¿~4®\ìO“\0`‚	T\0€\ê\Ú\".¡e{!\î¥IUq\æÀÑ˜‹\å¸\Ó~ž¦l\ÓË…¸³q¼>gÒ¤ª¸\×^ŒGÑˆsõª…½\00|U\0\0 ²\æ›G\âx,\Åõ;Z\î˜Ù¸\ÜlD´Ÿ\ÄÕ—iÒ¶=«­¥ˆ©#q¹j\Ý¼|\×\ÛÇ›\Ç*\Ø%\0×¾Z¼\n¥\Ôh\n\0€ôN©öâŽœR¿£\Z‡c®ss·ý¬7¾S–\ãn\çf®^½þJoµ—:ÿŸŽó.š•0\èS)5œ\ÒB\0\0¨¤ò”ú›­-·\í`\\i\îV¿®\î¯tùq\Ü\\‰˜kž¨\\—\00LU\0\0 ‚Æ…úNŸR¿Cö\ÏÄ¹©ˆ»­‡»Ò¯\ë½O\ân%û+-»$8\\œ\n€	&P\0\0ª§q,.¡\åNŸR¿\Ê~]?\ÚñÖ©¥gñQUû+\ívIÐˆ‹\Í\êuI\0\0\Ã\"P\0\0*f7O©ß¦ý\'\âR=\âQ\ëñ®ö\ëz«õ4\Åt\\:PµVª\î’\0\0†\ÄE©”\Za\00@:¥þQ{aWN©ßŽ3õ\Ãq<–\ãNûyš²K^.Ä•ˆ\ãõ™\ÊõWz¯½*\Ù%L–AÇ˜J©\á”ª\0\0@¥”§\Ô_±Ë¡\å;›\Ë\Íaõ\ëZöWz$.W\íªú/\ÆõªvI\0\0C P\0\0*\åÖ§Ç©…û»zJý\Ö<‹÷:\Ëö\éúu]¾\ßY\ÇûU\ëö £ºŸ\0\ì>*\0f6nÌœo©hœ8½¬ùCƒ§µ\é¼wñµÌœ\î¾;\0\0\0vW\í\â‡u\ä#ò\ßâŸ¥!\0\0\0\È÷\rñ\Ãi6-T\0\0\0\02\Õ\Î\Äi¡\n#r/þy\Z\0\0€|g\â‡\Ò0lZ¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨B%\ÌÆ™³ñ\à\Ðl\Z\ãú¾xSu?\ëùC\åš9\Æ&E…¿{Ó\Ï\ãl\Üh¤ñ=\Ã6^-¶q`¯°¿ªŸ°3ö\Õ\âU(¥FS\0\0„›³Ž€µc*¥†SZ¨\0\0£\Õ8s±7[\ÏÒ„ê˜¯OG¬<k\Ëi\Â\È<‹k­\ÎBÔÆ•ýiRU,?Ž›+s\Íq&M€q&P\0\0F\è`\\i¡\åb\Ü~™&U\Åþq©ñ¨½÷Ò¤Qº\×^ŒGÑˆsõƒiJU<\Û\íåˆ©\Ãq¡ja/\0\ì§ü+5\Â\0˜xcqq*\ân\ëa%B\Ë~ó\Í#q<–\âú‹\çiÊˆ½|\×\ÛÇ›\Çb>MªŠ{/ž\Ä\Ýh\Ä\Åf»$€15\èS)5œ\ÒB\0\0™\î)õ±ü”úõf\ã|½s\Ó^Œ[½	•p«½\Ôùÿtœ¯\\¥\Ï\â£v\ç¦~¸ra/\0\ì4*\0\00\Z\å)õ­Ç•\n-g­f¿®\î¯ôV\ëi<Š\é¸t j]\0À\Î¨\0\0#q¦~¸Z§Ô¿v0.\Ô\í\'qµjýº\Æó¸\ÚZªf¥e—õ§`¬	T\0€˜\Ë\Í\"´¬\Ö)õ]e¿®íŠµN--/V¶¿\Òn—SG\âr\åº$\0€#P\0\0†®²§\Ô\ÇÁ¸ÒœŽXy\Z\×*×¯k\éY\\ku®~4®T­•j…»$\0€\â*ÿJ°\0\0&S:¥~e1nW\í”úý3qn*\âQ{!\î¥IUt¯½¢\ç\êU\ë¯ôy\Ün/W³K3ƒŽ1•R\Ã)-T\0€\á*O©o=¬\\h9\ß<R\Ñ~]\×)û+m«\ÜUõ\ï½xR\Ù.	\0`\'T\0€\áZ¾§>Ž÷+xJý­O?\î,\Ûý\êõ\ë:@u—õY¼\ßù|O}Z\Ñ>h`›ª\0\0\0\0\0™j\ß? #G‘ÿ\Zÿ\"\r\0\0@¾oŠHCÀ°Õ¾9¾_ \n#ò_\â_¦!\0\0\0\È÷\Íñýi6§ü\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨B%\ÌÆ™³ñ\à\Ðl\Z\ãú¾xSu?\ëùC\åš9\Æ&D\ãt\ç=Ÿ4^%U^¶\r\Ù\Æ+\Å6\ìþ÷ðD\î¯|À\Î\ØW‹W¡”\ZM\0LŠ2üŸO\ã\Õ1&?\0W8Œ©\îg{Û cL¥\ÔpJU\0\0`—\Í\ÆùzÄ£\Öã¸•¦TF\ãp\Ì\År\Ül=Kö¨\å\Çqs%b®y\"Î¤IUq«õ4\Åt\\:p0M€½M \n\0\0\ìª3ŽvC\Ë;\í\çiJUŒ+\Íéˆ•Å¸ý2MÚ³ž\Ç\íör\Ä\Ôá¸°?MªŠ—qg%\âx}¦ra/\0l…@\0\0\ØE³q¹Ùˆh?‰«U-\Ç\â\âT\Ä\Ý\ÖÃ¸—&\íe÷^<‰»Ñˆ‹Íªu_ð<®¶–\"¦Ž\ÄeýC0ª\0\0À\î\éžRq·]½S\ê\ç\ëÓÿ/\ÅGË½ñ½\ïY|\Ô\î\Ü\ÔW¯¿\Ò\åÅ¸Û¹™«\ïñ¾j \ÃE©”\Za\0Œ·ò”ú§q­j¡\åþq©ªýºnCuû+}\×Z ~4®T­KØ£c*¥†SZ¨\0\0»cÿLœ«\è)õg\ê‡\ãx,\ÅõU\ë\×u›^>Œ\ë\íjöWZvIp®\î\âT\0\ìmU\0\0`W\Ì7tC\Ë\êR_ö\ëº8V­SK·\ÚU\í¯´\×%Áñ\æ±\êuI\0\0\ï@ \n\0\0\ì¼\nŸR\æÀÑ˜‹\å¸Ùª^¿®;bùq\Ü\\‰˜kž¨\\+\Õ\êvI\0\0ùª\0\0ÀŽ\ëR¿w\ÚU;¥þ`\\¨7\"V\ãö\Ë4i\ì<\Û\íåˆ©\Ãq¡jý•¾\\ˆ;+\Õ\ì’\0\0r	T\0€VžRÿ$®V-´l‹‹\í\×u\'•ý•^lV\íªú\Ï\ãj«ª]\0@WùWj„\00žž\ÅûÇ©O+xJýòý8\ÕY¶÷+×¯\ëNóÀ¸tŒ©”\ZNi¡\n\0\0PhœŽ3g\ßZó‡O/ªh•{cÀô²vs\ÞU­*\0Œ·Ú·\Ä÷i&#òŸ\ã_¥!\0\0\0\È÷-ñ}i¶Ú·\Æ÷\nTaDþSü\ë4\0\0\0ù¾5¾7\r\Ã\æ”\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€Lûjñ*”R£©U³qc\æl<84›\Æ\ÇÅ¸¾/\ÞT\áÏºq:t–\íF#O‚*¿\ç=ûyTwŸ?\ÔY®™\ÓilB\ØÆ=Ã¿‘ªe¼>AÇ˜J©\á”ª\0\0Àö	“\Ù?À°÷T\0€m›¯OG¬<k\Ëidy\×Z¦~4®\ìO“\0 \âª\0\0Àö\ì?—\ê\Úq/M‚\\÷Ú‹ñ(\Zq®~0M€j¨\0\0\Û2\ß<\Çc)®¿xž¦À;xù0®·#Ž7\Å|š\0U&P\0\0¶a6\Î\×;7\íÅ¸Õ›\0\ï\ìV{©óÿ\é8¯^\0ö\0WùWj„\0°×9p4\æb9n¶ž¥)°Ë\ã\æJ\Ä\\óDœI“€\r:\ÆTJ\r§´P\0\0¶\è`\\¨7\"\ÚO\â\ê\Ë4	¶\äy\\m-ELŽ.N@\Å	T\0€­i‹‹Sw\ÛZ§²–\ãn4\âbs6M\0€j¨\0\0[p0®4§#VžÆµ\å4	¶\åY\\ku6¦úÑ¸¢•*\0&P\0\0\Þ\Ýþ™87ñ¨½÷\Ò$Ø®{\í\Åx8W?˜¦\0@õ¸(•R#,\0€½j¾y$Ž\ÇR\\ñ<Mðòa\\oGo‹ù4	l\Ð1¦Rj8¥…*\0\0ð\În}úqœZ¸·\Ò8\ì\Û\0UWû¶ø\Í\ä`Dþcü›447fN\Æ\\û“8õ\é8]\ÔaÀûjœŽ\ïM÷†ø ña=¬sj\á“\Þü\Òøz§>[ª\ä¼#–º\ã­\Â\Ûp\Ú\æ\î~öq¼?)}üUù=\ï\ÙÏ£º\Ûøü¡³ý\Ï$\ìgú\Ø\Æ\Ù@\ï;‘F\ÖÙ«ÿ–ðï”½Ì¿‘ªe¼>o‹\ïICÀ°	Ta„VU\0\0\0\È\'P…\Ñq\Ê?\0\0\0\0@&*\0\0\0\0@¦\Ú\\|·SþaD\îÆ¿MC\0\0\0o.¾;\rÃ¦…*\0\0\0\0@&*\0\0\0\0@&*T\ÂlÜ˜9Í¦q\Æ×¸~\Ö~_\Óñ ³l7\Zi|\"ø<v^u\×\éü¡\ÎrÍœNc“\Â6\ì\Õ\ÝO\ë¿K7\æó\0v‚@\0\0\ÈS\ÙÀX±mþ1 À\æ\Ó8\0ŒÚ¾Z¼\n¥\Ôh\n\0`/™¯OG¬<k\ËiBU4\Ç\\,\Ç\ÍÖ³4w¶ü8n®D\Ì5OÄ™4©*nµžÆ£˜ŽK¦)@a\Ð1¦Rj8¥…*\0\0°¹ý\'\âR=\âQ{!\î¥I\Õp0®4‹ w1n¿L“Ø‚\çq»½1u8.\ìO“ª\â\åB\ÜY‰8^Ÿ©\\\ØÀd¨\0\0›šo‰\ã±\×_<OS*¢q,.NE\Üm=¬XÐ»÷\Ü{ñ$\îF#.6«\Öu\Âó¸\ÚZŠ˜:—õO@8\å_©\0À\Þ0\çë›öb\Ü\êM¨Œn7±U­‚=\éY|\Ô\î\Ü\ÔW¯¿\Ò\åÅ¸Û¹™«\ë\'JƒŽ1•R\Ã)-T\0€\r9p´š}”–\Ý´W.\èÝ«ª\Û_é³¸\ÖZŽ¨+U\ë’\0€‰#P\0\06p0.\Ô\í\'qµb}”ž©®f7{\ÙË‡q½]\ÍþJ\Ë.	\Î\Õ]œ\n€\Ñ¨\0\0oWöQÚ®\Úôg\ãr³z«\×\rÁ^w«]\ÕþJ{]o«^—\0L*\0\0ð\åôŸÆµŠõQZ\Ùn\ÆÁòã¸¹1\×<Q¹Vª\Õ\í’\0€I\â¢TJ°\0\0*mÿLœ›Šx\Ô^¨\ØôS7+‹q»b\ÝŒ‡\çq»½1u8.T­¿Ò—qg¥š]À°\r:\ÆTJ\r§´P\0\0šo©f¥e7­‡z\ÇG\Ù_\é\ÅfÕ®ªÿ<®¶ª\Ú%\0“B \n\0\0t\ëÓ\ã\Ô\Âý\êõQº|¿³\\\Çû\ë†`¼<‹÷;\ëøÔ§\ìRÁ\çÀˆ\Õ\Î\Å\çÃˆÜ‰\Ûih6nÌœŒ¹ö\'\ÕüG+;h\\?\ë\n¿¯\Æ\éxð\Þt\ÜýlõÀkþ\Ð\Ùø°\Þ^\ï\Ô\Â\'½÷’\Æ\×;õ\ÙRw~oóA;F2ïˆ¥n\èÑ³·>½¡ºë´·=øü\Ó\Øz»º\r\Û\Æ÷ð6\ì–7÷\ÓU1`_šöao3ª\çlw\Þoþ;p|›8\Ò0lU¡\Õ@\0\0\0ò	Tatœò\0\0\0\0©ö\íñ]Z¨Âˆü‡øwi\0\0\0ò}{|W\Z†MU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU¨„Ù¸1s6šMãŒ¯qý¬m\ÃdjœŽm\åF#\ï¶q2\í\Ùm\Ø-ó‡:?fN§1x?`O\ÙW‹W¡”\ZM\0TKu<(C©ù4\ÎpUvý¡˜`ƒŽ1•R\Ã)-T\0€ž\Æá˜‹\å¸\Ùz–&T\Ålœ¯G<j=Ž[i\n\Ãu«õ4\Åt\\:p0M©ˆ\å\Çqs%b®y\"Î¤I\0°\Ûª\0\0@\ÇÁ¸ÒœŽXYŒ\Û/Ó¤Š8s\àh7\è½\Ó~ž¦0t/\â\ÎJ\ÄñúLÅ‚\Ë\çq»½1u8.\ìO“\0`—	T\0€ˆÆ±¸8q·õ0\î¥I\Õ0—›ˆö“¸Z± w²<«­¥ˆ©#q¹b§\×\ß{ñ$\îF#.6õs\rÀpT\0€˜¯Owþ¿-÷\Æ+£\Û\rA\Ä\ÝvÕº!˜@Ë‹q·s3W¯Zpù,>jwn\ê‡õ±ÀPT\0`\Ò\í?—*\ÙGi\Ù\rÁÓ¸Vµ w\"=‹k­\ÎQ?\ZW*vz}eûx`,¹Ê¿R#,\0€*8S?\Çc)®¿¨X¥ûg\â\\%»!˜\\\å\éõ\ç\ê._>Œ\ë\í*öñ\n»g\Ð1¦Rj8¥…*\0\0L´²\Ò\Å\Ê]A¾y¤ôV®‚‰\Ö;½þxóX\åN¯¿Õ®f¯\0Œ*\0\0L°ò\nú7[ë£´²\ÝP\Ù\Ó\ë—\ÇÍ•ˆ¹\æ	­T\ØUU\0\0˜X\ãB½±²·+vý^7\Ëq§]±nˆx¹wVªxzýó¸\Ý^Ž˜:*\Ö\Ç+\0\ãE \n\0\0“ªq,.V²Ò²‚\'qµbA/…\çqµU\Í\Ó\ë\Ë>^/6g\Ó\0\Øy.J¥\Ô\0`¤–\ïÇ©…\ãý\ÊõQú,\Þ\ï,×©O+\Ö\r«l;0rƒŽ1•R\Ã)-T\0\0\0\02\Õ\Î\Çwj&#òQüû4\0\0\0ù\Î\Çw¦!`Ø¦N\Æ~!\rCö\ëÿ\0\0\0\à]Œ/¤!`Øœò\0\0\0\0I \n\0\0\0\0©öq^ª0\"ÿ>>JC\0\0\0\ï;\ã|\Z†MU\0\0\0\0€LU¨„Ù¸1s6šM\ãŒ/Ÿ5\0\0°\Öü¡\Î1\Â\Ì\é4TÝ¾Z¼\n¥\Ôh\nÆŸ\0yrM\Âgoûž\\{÷³/\Ø\ç\Óxe4Nw–\ël\Üh¤qö”\ÊnWþ®Vw±—:\ÆTJ\r§´P`=‹k­\åˆúÑ¸²?Mb24\Ç\\,\Ç\ÍÖ³4aÙ¾\'ÖžÝ¾g\ã|=\âQ\ëq\ÜJSªb¾>±ò4®u¾R\ì=·ZO\ãQLÇ¥Ó”ª¨\î~ºº\ë€U\0vÕ½öbç€¡\ç\ê&\ÇÁ¸\Ò,Â‘Å¸ý2M\ZS¶\ïI´w·\ï3Žvƒ\à;\í\çiJE\ì?—Š ·½÷\Ò$ö˜—qg%\âx}&Î¤IUQ\Ùýt…\×\0›¨°»^>Œ\ë\í\ÎCó˜\Ó\Ú&E\ãX\\œŠ¸\Ûz8þ\áˆ\í{ò\ì\Ù\í{6.7\í\'qµbAð|óH¥¸þ¢bA/\ï\ày\\m-EL‰\ËUë¶¡²û\é\n¯3\06%P`\×\Ýjwb:\Î;`˜\ÝSwc)>šSwmß“e\Ïn\ß\Ýn\n\"î¶«\ÖMA¯‚h/V®\Þ\Ñòb\Ü\í\Ü\ÌÕ«\×_ie÷\Ó^g\0l\ÌE©”\ZaÁ\ÄX~7W:\ÍNkwå©»\ì£q\×Ø¾\'ÇžÝ¾\Ën\nª\×Gi\Ù\rÁx÷·<)*Ü¯te÷\Óú\âf{c*¥†SZ¨0\åim‡\ã‚†±v¦~xOÝµ}OŠ=»}ïŸ‰s•\ì¦\à`\\¨7*\Ù\r[s\ïÅ“¸[\É~¥«»Ÿ®\î:`#U\0†£{Z[#.6\Ö6¾\Ê>\Z\'ð\Ô]\Û÷Ø»\Ûw\ÙGi\åº)(û£­\\7lÝ³ø¨ªýJWv?]\áuÀ[	T§µ»\É>u\×ö=\îö\ìö]\Ùn\nª\Û\r\Ûs«õ4\Åt\\:Pµ—\Õ\ÝOWwð6U\0†\æ^{±sÀà´¶ñ”N\Ý]YŒ\Ûz\ê®\í{œ\í\Ý\í»\×MÁr\ÜiW¬›‚\Ô\rÁ£öBÅº!`\Û^.Ä•ˆ\ãõ™\Êõ+]\Ùýt…\×\0ƒ	Tž—\ãº\Ó\Ú\ÆSy\ên\åúh\"\Û÷øÚ³\Ûw\ÙMAõú(-»!˜¬þ–\'E\Ù_é‘¸\Ü\Ùü*¥²û\é\n¯3\0ª}W|»KÃˆü»øih6nÌœŒ¹ö\'q\êSýˆ7Ÿ5\0\0°\Öü¡³ña})N-\ÜOS6÷]ñ\íi6*ŒÐ†j\ãt<xoº7<À\í\èüÁM#\ëœZø¤7¿4¾Þ©Ï–¶<ïˆ¥\Îý\Ó#y\í½:\ïb­þ\ÃH úšmü\rã±\Óeû~ƒ\í»j\Òß£4¶\Þn~\áo\àÙ½\íl³ý\Ù\Þý\îŽ\î»9®ö\Ê>G \n{‹@Fh5P\0\0€|U}¨\0\0\0\0d¨\0\0\0\0dª]ˆsNù‡¹w\Ò\0\0\0\ä»\ç\Ò0lZ¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0`6nÌœ‡f\Óxu\Ì\ê,\×\Ì\é4T@\0\0\0\0 “@\0\0\0\0 Ó¾Z¼\n¥\Ôh\n\0\0\0¶b\Ð1¦Rj8¥…*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&¥Rj„\0\0\0[1\èS)5œ\ÒB\0\0\0\0 “@\0\0\0\0 “Sþ•\Za\0\0ÀV:\ÆTJ\r§´P\0\0\0\0\È$P\0\0\0\0\ÈTû\î˜s\Þ1ŒÈ¿»i\0\0\0ò}wÌ¥!`Ø´P\0\0\0\0\ÈTûžø6-TaDþMü\Ç4\0\0\0ù¾\'¾-\rÃ¦…*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\00\Z§\ãÁ\ÌÙ¸\ÑH\ã•17:\Ëõ\à\Ðl\ZªL \n\0\0\0\0i_-^…Rj4\0\0\0[1\èS)5œ\ÒB\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “‹R)5\Â\0\0€­tŒ©”\ZNi¡\n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0\ÉUþ•\Za\0\0ÀV:\ÆTJ\r©¾7¾Uª#ò¯\ã?¥!\0\0\0\È÷½ñ­i6§ü\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dª}_|‹>TaDþUü\ç4\0\0\0ù¾/¾%\rÃ¦…*\0\0\0\0@&*\0\0\00\Z§\ãÁ\ÌÙ¸\ÑH\ã•17:\Ëõ\à\Ðl\Zªl_-^…Rj4\0\0\0[1\èS)5œ\ÒB\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “«ü+5\Â\0\0€­tŒ©”\ZNi¡\n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0\ÉE©”\Za\0\0ÀV:\ÆTJ\r§´P\0\0\0\0\ÈTûþøf\Í\ä`Dþeü—4\0\0\0ù¾?¾9\rÃ¦…*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦\Ú\Ä7\éCF\ä_\ÄMC\0\0\0\ï\â›\Ò0lZ¨\0\0\0\0d¨\0\0\0ŒR\ãt<˜97\Zi¨4*\0\0\00V\æ3§c>\ì$*\0\0\00Ff\ã|=\âQ\ëq\ÜJS\0vÒ¾Z¼\n¥\Ôh\n\0\0€u\æÀÑ˜‹\å¸\Ó~ž¦Œ§AÇ˜J©\á”ª\0\0\0À˜˜\Ë\ÍFDûI\\}™&\ì0*\0\0\00\Z‡c®ss·ý¬7°ª\0\0\0À8Wš\Ó+O\ã\Úrš°ª\0\0\0ÀÞ·&\ÎME\Üm=Œ{iÀn¨\0\0\0{\Þ|óH¥øH\ëT`—\Õ\æ\ã]jF\äVüJ\Z\0\0`\ËöŸˆ_þÜ‘ˆÖ¯\Æw¼ØƒW÷oœŽ\ïM\Ç\Ý\Ï>Ž÷3\áùø\Æ4›ª\0\0\0Àžv¦~8Ž\Çr\Üi\ïÁ0\Øsª\0\0\0À6—›ˆö“¸ú2M\ØEµŠ3Nù‡ù\çºJ\0\0`§üÿPœICÀ°	Ta„6T\ÓÔ·ù ña=¬sjá“¸1s2\æ\Òøz§>[\Úò¼#–:÷O\äµ\Çv\Þ\Ö\Ù¶;o\ë\ìMÛ™·\ïý`Ûš·uö\ël°Ýœ·uö¦\í\ÌÛ¾r°m\Í\Û:{\Ãv\ç=®\ï\ë]B\È\Ê¨Âž\"P…\ÒB\0\0€­¨\Â\è\èC\0\0\0\0 “@\0\0\0\0 S\í‡\ãœò#ò\Ïâ¿¥!\0\0\0\È÷\Ãñ\ri6-T\0\0\0\02	T\0\0\0\02	T¡f\ã\Æ\Ì\Ùxph6\0\00)\æuŽgN§1 \êª\0\0\0\0\0™ª\0\0\0\0\0™ö\Õ\âU(¥FS\0\0\0°ƒŽ1•R\Ã)-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02¹(•R#,\0\0\0ØŠAÇ˜J©\á”ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j?_v©q‘\Z¿š†\0\0\0 ßÄ—\Ó0lZ¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dªýhüA¥‚ù¿\âÿIC\0\0\0\ïG\ã¦!`Ø´P…J˜3g\ãÁ¡\Ù4\0\0\0@	T\0\0\0†Bc\ZU\0\0\0€ahŽ¹XŽ›­gi°	T\0\0\0v\ÝÁ¸ÒœŽXYŒ\Û/\Ó$`O¨\0\0\0\ì¶Æ±¸8q·õ0\î¥IÀÞ´¯¯B)5š\0\0`2\Ì×§;ÿ_Š–{\ã\Û5\èS)5œ\ÒB\0\0\0`7\í?—\êZ\ãVš\ì]Z¨*5\Â\0\0`ü©Ž\ã±\×_<OS¶o\Ð1¦Rj8¥…*\0\0\0À®™\Ë\ÍFD{Q\ëTU\0\0\0€]r\æÀÑ˜‹\å¸\Ùz–¦\0{@\0\0\0`WŒõF\Ä\Êb\Ü~™&{ž@\0\0\0`74Ž\ÅÅ©ˆ»­‡q/Mö>*\0\0\0ÀnX¾§>Ž÷—\Ó80j?§]jF\ä\Çý447fN\Æ\\û“8õi\êW§q:¼7\Ý\àƒvÄ‡õ4²Î©…OzóK\ã\ëúli\ËóŽX\ê\Ü?=’\×\Ûy[go\Øî¼­³7mgÞ¾÷ƒmk\Þ\Ö\Ù¬³Ávs\Þ\ÖÙ›¶3oû\ÊÁ¶5o\ë\ì\rÛ·uö¦\í\Ì{³\ï}qÿ©…ò˜o\çýXœNCÀ°	Ta„VU\0\0\0\È\'P…\Ñq\Ê?\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦Ú\Ç\×\éCF\ä\ÅÿLC\0\0\0\ï\Ç\ã\ë\Ò0lZ¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dª½ÀUþaDn\Äÿ›†\0\0\0 \ßûñ\Ò0lZ¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dªýD|­‹RÁˆü\Ãø_i\0\0\0òýD|m\Z†MU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LµKñ%Wù‡¹_IC\0\0\0\ïR|)\rÃ¦…*\0\0\0\0@&*\0\0\0\0@¦\ÚO\Æ)§üÃˆüƒx†\0\0\0 \ßOÆ©4›ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j8¾\è¢T0\"ÿgüZ\Z\0\0€|8¾˜†€a\ÓB\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 S\í§â¤«üÃˆüýø$\r\0\0@¾ŸŠ“i6-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\Õ~:¾\à¢T0\"/~=\r\0\0@¾ŸŽ/¤!`Ø´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû™8\á*ÿ0\"7¦!\0\0\0\È÷3q\"\rÃ¦…*À¤kœŽ3g\ãF#Ã˜š?t¶³­c\\ÙŸ&tŒ+\Ó\Åô²N\Ç|ºgûvs\Þ{\Ý\Úu3¬ýÏ™\ß\Ø÷y\ì\ì\ëÞ¾•\Þ\ç\á;\0\ì*Àž\Ñ@LŸˆ3ijžò¹{\ë\àrOûO\Ä/§\Ð\æ—Ly›õAW¯N§{wR#NL¥Á®\çqu\é\ã8µð«qs%M\Ú1»9\ï½l6n\Ì|9Îµµ³n:\ë\ç7Ÿ\Æ\ç\ß\ÛÁ\ïyù\Ý;4›&¬º÷\âWº¯ùƒ­\å4*¨\ï\ïGQ\ëÿ†¬\Ý_®û\î¬{\î \ïÁÛŸ_|7ûž\Ûÿo†ô\ãë›µú˜7ö\ã\ë^{\ËË½\Ék÷þ­0 \Ò<þ}Y¿^¶³\Î˜HU€½bÿLœ›Š¸\Û^Š˜:üc¾\Ò\Î\Ô\ÇñX\ê|^\Ç\ë3\ï€O®»ŸdY÷\ÓÔq\ë\Ó\Þ|ß—¥Ô™Gcn\åiüü‹\ç½	/\Æõv#.6\ß~`\âÁ\Þ\çŽD´z?8\áÿñ\æ—_|E°÷KÍˆ›¿Y\ìÏŠk:ßÏ¥€/=÷xû“\Þ>ô³Î¿\ê\'û\ÂÁÞ«¿\Ôl\Å¯÷³¿W_ö\î?t2>Ÿ^·ø¡\ãQLÇ‡\å¸\Ë÷\Ó\ãSu\ï\ïh/Æ­\â¶qºo¹\Òý}¯½­\å\Þ\äµ\Ë}{Y\å&w\ÛÏº·=Ë«\ËVÔ§«÷u\Ã\Ò\Î\ëÿF\ßßŸ\ï(÷O›¬3\0&—@`¯˜jvºZ­ÎD#\Î\ÕW[O¬?µ±¿eg¯UÅ—\ãb·U^\ç\à¨la±¾u\ÆT_\ëŒu-`×·þX=M6µ|-\ß×º\ãm-;\ÖOß¶õ­V´hy½¬i¼\Þö¾ú\×\ßkž¿‘/Muf\Ö9Ø»¶\Ò9°\ëÀ»ó>t:µ\ê¼Æò=¤\Ï/­\Ç\âu\Êõ6\é­a6]g…\r¶…\ÕõØ«­œ\æ½k\ÛpF‹°Qm\Ã\Î{\Ë\Ûðl\\n6\ânk!.”-\î;÷\â{\Ò,\Ð}\Ì[\×Iy\ß\ë\Ï7§}VwT3\Å]õ“¯\ç‘ÿ™oô\Úlgk­\Î\'ÿµû\ÎB\è\Ö\Ú\ÏoËŸÇ¸\ê~ý\ï·S\Î\Þøb\ß÷\í¶¿Ág1\ïµû•µŸUOùyº\ï\í\æ›\Åö»\×S w\ïÅ“¸Û¹-~˜+–¹øþDûI\nôž\Ç\Õ\ÖR\ç¶÷o‚\Þy}A\âò\ãn\ëø\×?\ê5Žuÿ-p÷³û½t\"˜|$¾\\ˆ;E\Ëú\Î÷òK½)kô–s9n¶z¯u\æõ÷7yÙŠ\ßHƒ\Û^\îuÖ¿öZ\éµVžÆµ¬\Ï\Êeûdðm›¬3\0&×¾Z¼\n¥\Ôh\n\Þ\Å|}ºs€ÐŠ¯¤ƒœ\ãS\Ò=\ë\âZžr¼´\ÚÂ¢¯uFa®ÙŒ\ë\Åô¢e\ÈÔ‘¸\Üw°þa}µe\Ç\í\Îc\ß8=÷pü\â{?Ÿ^\çxó\Ø\î\È¼\ïMÇ£²5\Í-q6\ÑyþùvZi¹\ç\Þ\ëP\ß*Z¯­‹õ\ßw»±\Ù8_x´ò\"\îµ»øš\Ó\Ì\ë\Íxø›©…N½?˜ZOë¼«þ¥ø\ÅøJj‰£õ\Þ\Æ\ël6n\Ô\Óg™Z\'u¶…2pª\îi\ÞE s²\ÛZó»\ËþI\Ü\ío¶™\ÝÜ†7˜w\×V·\á\Æ\á˜+~Š\"¤\èìŠ\ïm´\â£v+ª\Û[\'\Ý.º¯\×Q¶v\ëT^«\äM^;{ŸSiùt†Š\×y¯]<§ø\á«o½¦\å\à&û\áœ\Ïc,\ÛUô\ÖYñž;»~q\Í\ç1\ÝY§O^¯³Õ¿M9\ÛYß¼\×ý],\Â\Ô_*~H-\Z?(ZXng\ì/+Š¿õ½\Ñ\Îg\ßÛ–ºßý\Íø|g°ø\Û\Ò\Óym²°\á¿	R(\Ú=—\ã\á\Ô\Æ!tÏ\Þß­¾eymÿ‰¸\Ôù+‹q;m£½\à·XEp}¢·~\Ëý\Í6—{¯Ý¯\Û¾sû¨½÷z“’Þ²õ\Þw\ßgõzÙše.\ï_ýn½\Û:ƒ\átŒ©”\ZNi¡\n°\'¤€®{€ð<~­G\ë‡w\äà­´¾õ\Åç§Šƒ\Ò\Þë®¶*‰¸\Õ\ê…#ýAMtº\î|ö°»l·\ÛEzÐŒ/ö®e µz\n\Ýö½mÒ¢\å\r\Ë÷û‚Ž´N\Ë\å^?¯t\0÷¨õ8¯…J74ZŽ;\íÎ²¥V:sõ¾@´\ï@ðÍƒ¾ž\ã±\Ø;%º¯•\Ð$ÀÍ½W\ÐöZ_\Ûp=‹÷û~ ¸·\Ò\ê\Þö¶á±\Ûpo;\é|÷Z\Åw§ð,®¡on—»¹\ro4\ï\ÂV·\á\"œ\è|>_,¾¿\í\ÅøJ7¬\è³\Ýu²›¼v\î>\ç\Ü{½3Š\à5/L\íH­\à>û\áŒ\Ïc\\½þÛ•öµk?¥ø`Í‡i\Î\Ü\Îÿ]<\ê\Î:_m\Ùûc}€]ö¡ün§‡¿þñ­ð;ûÁK+¿\Ú\rƒ‹e/¾C\Ý\Ö\×½\ËG\ã\áoapG\ç¾r\ß÷ú\ïM\ê*¨\Ô=s¢§\ÒP)(þ¥-­\Ë`ru­zÝ¢t\Í}ýp7˜\î|\Í#\Ý\ç¾Þž·¹\Üý¿v)}6ý\ßÓŽrÿÝ«ž—¡jZ¶\ã\Íf|ôúþÕ€ü]\Ö\0“E \n°¤ƒ¿\ßX\é >x\Û©\å\Æ\æZñké€±w\à²ûý‹u[ñlK\ÑBi5¼û°,^KÁp:¸\îÀ¥€4C/x)\×É³ø¨8~\×\0¼s€\Ù;XL\åKƒHá«µ¶\ÕT\ï`¶¯\Ö®]\'E­=]y“û7y\íkúP\í¼\ç\\k\ÞWjùTuoœ\"û\Îvo\ÞxÞ™l\Ã=Íµ-¶;+[Àml\Ýf¯·\Ïi\Äñ\î\ã2[¯S\î\ã\×\È\ÞoÝ¶ö»º\Ïy/\âa7ø\ß\Üö¶³\Ôr³\Õ\Ê\å\ÞÁ}N÷=ón¶º­gW\Äiu¿+E\ë\ë¢O\Õ\"h]ó·¶%—\ï¿n¡\ß[®fg›\êÝ·\ÚÊ´\ïTù\å\Å×¡fÿe\ÜÁ?\n¼\å”únW\'c®\Û2¼×ªýuß¯;²Ü…MN\ç\ßð‡‰R\n\Ïû \êX}Nú›½\æþ\Í\×\0“\Ç)ÿJ° Wyð÷ºõ^:x[\Ó\êqX:\ËR¶4©–t›¥<%wõô\Ú^ U\å\é‹\ç:\ë¸\Ûâ¥¯u\Ø\Æ¦\à¥h\Óû¼zaÔN¿\í˜¾$»µ¶…\Õú‹w¬\íú¡ha´öþµÓ›Ü¿\Ék\ï–\â\à¿XÇ¯\Ã\Ø\îi\ä{S¯5TŽ\ÝÝ†7›÷Vu\ß\ßJ+\Êhµ|¿_*öqE°2@þ:\Ùy¿ö }\Îr\Üü¬wºýÀn\nº¡Sg¿ð–S†³[U\ïð~x[û…]\Ýç¼‹\ÕSÔ³~|Z\';KÁ\í\ë\îvb\ÙWõZƒ\ÛQù#Zú›R|?\Ê~I;¯ýú¢n\ëN§_\Ó\Zsi±³­t&¦uò•¢O\ï\Í4Š‹Ku\ÖEû“Á-ò\Ó¼\ë[B¯\í×´ø%µV-N\é\ß\ær¿ö–\×.õ~\Ì\Üü‡£5Ÿu\ç5Š°÷m²\ÖŒÐ cL¥\ÔpJU€\Ê[=½põ\à­\×úcm‰òT\Æ\ÓZ“\r8e7G÷\êÛ\Ûú\Ñ\×ý‰½S?ŒIyñŽ\ìþM3ô.h1—\Êy¾q\ngO\ÙuA\Ù_\Û\Z©në—²O¶5z­TŽ7Ov[¼¬½ZðÒ©Š¯ûY,*õé¸“§ ³\Þrjv0®4w®µXi7¶\áòb3s\Í2\\{ó\Ô\î\ÂÐ·\áÒ†óÞš^8\ÑZ\Ý¥\Ð$¦\Z½$s”û¾\×ýKö+Ã›5û\Ç\ÍmöÚ¹ûœXy?\ß=}ü\ÍÓ‚\ËÓ•\ß\è\Â µz\Øÿô\í‡\Ç\Ý\ëS\Ô3¶ó\ì\íl ²õ÷jŸªƒ?L?ª½\ÛE©\Êþ_÷›]^©³•\ß\éþ\×^«~½\ï\Çj\ëÊ²O\ï\Õy¯(‹À¿\Ø\Ïÿ\ÞX|—\Êýë›¯\×ûn÷õ^Î»˜no¹{\Þþ\Ú]\å~j³\Ïpý\ã\Ê~\éûú\×\ín©M\×\0K \nPue@·\æ\ï) M\å·>-ûü*Z¯Fº\Ð\ËZkS´\Ê\ìÿ\ëÖ§\éB\'\éyÖ‹Vk\Ãiy\ØÓ·\ÌE•-»\Ò)‚\Å)„\ÝéƒÀ5€\é\Üß»IqÿÉˆ\Ïz­ez\Ò†‹À£x\î\çšq}\Ð:\ë\Øvt.ó®\\&\ëZ\Èôõ\í\Ç\Æ\Öô¡:s:M\ÝX\Ù³·|9N´ú?\ë\â\à¼7¿n««Ž\Þk”!Gy\Êq¯\ß\ËÕ–\Å\ï\Þ]Á›6›÷³x?]H§w\êð\É\Þ\é²e1¢m8w\Þ[6ÕŒ¯\ËU?\çV–\âQýdw¿\ÒI6Y\'\ÅýÅ²¤û?ŒO´ž]û˜\â³/O#¼-”÷oþyl¸\Ï\és\ï\ÅWz?z§0÷\ík{\áLG_Ÿ§=\×~Ý·co™ú—{ôû\á\ê*?\Ãò\"Qy­D7\Û\Î6V´¦,¶»õû«\í\ï3:Š¿\Åöûúô÷µ\ÛY\Ñ\â·ÿµ\×nk»Pøpªø!¶\ïôú—\ã;\ÖÍ»øñ¯\×õ`\\y¯9;ú¾;E½\î†!…»Eÿ\Çë·½7\ÖI\Ìöµr\Ý\Ör6x\íB/ ¨¯\év\âsG\"Š<_\Öek\Úò»\×\ÙŠ@¹\ìfg\ÃuÀ$«ýl|óŽaDþNü\ï4TR\ÑZ§sðõ\Ù\éP1UÛ†‹þ6\ßk\Æ\Í\ß\Üý~–s\Ým\é\í¡6À$ø\Ùøš4›ª\00Pj­óN-û J*¸\rw¯Œßˆ‹ï•§ZwìŸùw8%\Z\0\0F­ösq\\U‘¿½a7øÀ¨§öú¡uZ-{Ó†\Ûpj\Ù÷6»\ß\â¯8½÷d·Âže-VywZ¨\Ä\Ïõ:\ê\0F@ \n#$P\0\0`+ª0:U\0\0\0\0€LúP\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû#qLª\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû£ñû]”\n\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j,Žº\Ê?\0\0\0\0@-T\0\0\0\02	T\0\0\0\02	T\0\0\0\02	T\0\0\0\02\ÕþxqQ*\0\0\0\0€Z¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dªý‰ø}®ò\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€Lµ?³Nù\0\0\0\0È …*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@&*\0\0\0\0@¦ÚŸŠ\ß\ë¢T\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTûùø=®ò\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€Lµ?_\í¢T\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû31\ã*ÿ\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû³ñ»]”\n\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™j.;\å\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j>¦]”\n\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j!~—«ü\0\0\0\0d\ÐB\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 “@\0\0\0\0 S\íJ|\ÎUþ\0\0\0\02h¡\n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0©öArQ*\0\0\0\0€Z¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0d¨\0\0\0\0dªý\Å8\è*ÿ\0\0\0\0\0´P\0\0\0\0\ÈTûKñžª\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû…8\à*ÿ\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû\Ëñ;]”\n\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j%š®ò\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€Lµ£\á¢T\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈT»\Z¿\ÃUþ\0\0\0\02h¡\n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0I \n\0\0\0\0©öW£\î¢T\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\ÈTûk±\ß)ÿ\0\0\0\0\0´P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\È$P\0\0\0\0\ÈTû\ëñU®ò\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€Lµ¿S.J\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€Lµ¿û\\\å\0\0\0\0 ƒª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™ª\0\0\0\0\0™j+j.J\0\0\0\0AU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€LU\0\0\0\0€Lµ_Œp•\0\0\0\0€µ\é¯>ò\ê·û·\ãÕ«WQ«Õº·\Ý;:\Ãý\ã¥r|ß¾}[T1\\\è\Þ\ß\ì*žS¼V÷ñ\Å\Ûb|½þy”·\Åã¦¦¾\êõp1ý\Çg»\ã\0\ì¬_øÿu÷µ\Åþx\ßÔ¾\Î>·\Ø¯to\å~º\Üw\×÷w¡¼¿¬þ\ÇŠ\áRy_©œO1me¥x\Í\ÕùOuª˜V>®žšš\ê\Îcý´B9ŸBù˜Bÿô\âvý2”\Êû\Êá¢Š\çó/n\Ëç•+\ï/ªX–bZ¹|\Å;/¦—ó*o\Ë\ç–\ã\åmù\Üþ÷[\Î{½òy…\âþþù\ã\ågWN/Æ‹¿©\Ý\åzU,w1\ïrz[ÿ:\åýûö\ïý\Íu\Òÿº\ëŸ[®‹òþ\â¶|\Ïýú\çQÞ–\ë¡\î>weõ¹\Åxù¹—ó-\Æ\Ë\áB¹\î\ÊûJ\å\ëUè­§\Þpaýý\Ý\×Ió,\ßcq_ù¸rZ¡x\ìoý\Öo­y½þ\Çú\ç_¾·~å´¢Š\Ç\ãÅº(_Xñz\ëŸWÞ–÷\rz^9^\ÞWL+ŸSŽ—÷—ó,§•+nû\ï/«ÿq…b¸\Ô?\ßBñÿ\íb~i¿•>\ÓrþSm¯˜V(\Æs¾ÿ\Å|\n\åc\n\å6\ß\êÞ®_†R1­|^1\\.K1ÿ\â¶|^ù¸òþry‹iý\ËWL/û?¾\á\ëÓ¿…;\ËVN\ïü×½M\Ó\ÊyŠ÷Q<¬3÷\îx¿òy…\âþþù\Ïù\í\îû\í¾Zwz1^®\çb™\Ê\å.”[ÿ:\åýûŠ}E\ç¿ÿþ\ßÿG\ï±\Ý}\Ç\ê|\n\Ý\á\îPOù>\Êû‹\Ûò}õ\ëŸGy[®\Ãrxýs‹ñòs/\ï+\ÆûWŒó.\ï+•¯WT¡œiýý\åû(÷Šû\ÊÇ•\Ó\n\Åc\Çÿû¿öûY\ÜŠ\íª˜^Vù\Üòþb¸\Ô?\ßB9Ÿb\Ú\Êo­Qýs¾ÿ\å¼\Ë\Çú§·ë—¡T\ÞWU<·˜q[>¯|\\yQÅ²\Óú—¯˜^(WÞ–\Ï-\Ç\Ë\Ûb\ÚWuž[\î\ïŠ\ïk÷5\Ó<úu÷\ri¸xLÿ|º\ÏI\ËVN\ï_®òñý\Ë\Ö?^*Ç‹÷5\è±EÃ…õ\Ï-\×Eyq[Œ¯\×?\â¶*þ\Þ\ë¡P\ïµü\\(W¬›òoE1\ß\âõúW®»b¼.u_o\ß\ÚõR\Ê\å)§•\ï£P¾\Ç\â¾òq\å´Bñ\ØwúþwþMSN/•\ËSTñ\Øb¼øÜº\ï¯V¼\Ïòþòý•\ë©7½7b»)\ï+\Ó\Ê\çõ¿Ny_wþûŠ\å/\Ç\Ëû\Ë\å.§•+nû\ï/«ÿq\ÅBÃ¥õÿ\æ*\çSL+÷\é\åü‹\Ú\ìû_l\åöRî‡º\Ã\é1…rþ…\â¶ÿ½õ+\ï+‡‹*ž;¬\ïñ\Üþ÷[\Î{½òy…\âþþù\ãå²•\Óû—«||9\ßòq\ë_§/—£ø û[T1\\Xÿ\Ür]”÷·\Åø\ëV\Ò?ò¶\\\Ör¸˜^nÃ…b;/?÷ò;_l\çý\Ûðû\ßy\\ÿk•Ã…õ÷—\ï£pñ\â\Æ?ù\'7»÷½zõ*þ¾D\\\ÒG\â\ç4\0\0\0\0IEND®B`‚','Annotation 2020-03-08 210620.png','2020-04-11 12:13:10'),('louay1',_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0`\0\0\0`\0\0\0\â˜w8\0\0 IDATx\í\Ó„\"a†ñCXB\á°`8p8„\0‡!,‹C„\Ã\"„A‹\0a0!\Âÿ„µ²*\Ó|S\ßûð\0¯÷‹*XJ)¥”RJ)¥”RJ)¥”jb€–;SŒðˆ2LUð„\ì@1z N¬„Ö°%hCY,#c8 UG\Ë\ØªPŸTE;“7Ø“ºÁvfc\ìI\r`9iC½«†–“Ô®gX\ÎzP …\å,†¢˜#\rx\ßÌ‘Gx\ß\æ\È\Þ\Ã™\ÂûÌ¡%4€C1¼/9Áû\æ0GBx\ß\æ\È\0\Þ×‚9Ò„÷•±…\ål…]*„\å\ì	»T=\ç¬QÁ»\Ô–“.>¤\ÊXÀ\ÎlŠö¤\ê\ØÀ\Î$FŸ¤šg\Z!A\ê€\êXÀ2¡Š#Re±…(E781UGx\ä)žQƒÊ¨2ZaŽ¶c†< À	)¥”RJ)¥”ª¢‰Ÿ \Ä–L1Á}´p‡\nH\Ý\à¿ñkXFV\ÑÁJPt‹.\"¤°œl0A5xU\r]\Ìa1E\\m÷x…\Ø!\Z¸š¾c»0!š¸\Ø\Z˜Á.\Ü+¾\â¢j#…]‰5~ ðU0†]©!²\0Ø•› „\Â5†y\â\nU\æ™\n\Ñ-¶0\ÏlÀy¿`žú\çý…yª\ç%0OEpžùMh\0¿i\0\r\à7\r \nKh\0¥”RJ)¥”RJ)¥”RJ©ÿòE\Þq\Ñn\0\0\0\0IEND®B`‚','person-white-48dp.png','2020-04-13 22:53:02');
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
