-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: schedule_management
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4xqvdpkafb91tt3hsb67ga3fj` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'2023-09-27 17:27:34.070189',1,'2023-09-28 17:37:19.190468',2,'Basic C# Topic','Basic C# Training'),(2,'2023-09-27 17:28:39.227919',2,'2023-09-27 17:28:39.227919',2,'Advanced Java sessions','Advanced Java Training'),(3,'2023-09-27 17:29:23.839049',2,'2023-09-27 17:29:23.839049',2,'Advanced Python sessions','Advanced Python Training'),(4,'2023-09-28 17:55:08.287907',2,'2023-09-28 17:56:18.377052',1,'Advanced Kotlin topic','Advanced Kotlin Training'),(6,'2023-10-07 00:13:46.135915',2,'2023-10-07 00:13:46.135915',2,'Advanced Ruby sessions','Advanced Ruby Training'),(7,'2023-10-07 00:14:27.799572',2,'2023-10-07 00:14:27.799572',2,'Advanced C++ sessions','Advanced C++ Training'),(8,'2023-10-07 00:30:40.579897',2,'2023-10-07 00:30:40.579897',2,'Advanced AVR sessions','Advanced AVR Training'),(9,'2023-10-07 00:31:00.853296',2,'2023-10-07 00:31:00.853296',2,'Customer Communication sessions','Customer Communication'),(10,'2023-10-07 00:31:20.279166',2,'2023-10-07 00:31:20.279166',2,'Clean code','Clean code'),(11,'2023-10-07 00:31:35.980868',2,'2023-10-07 00:31:35.980868',2,'Security Policy','Security Policy'),(12,'2023-10-07 01:16:37.829287',2,'2023-10-07 01:16:37.829287',2,'TMA Overview','TMA Overview'),(13,'2023-10-25 11:10:01.125366',11,'2023-10-25 11:10:31.857770',11,'Improve JavaScript skills','Advanced JavaScript');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-29 15:30:56
