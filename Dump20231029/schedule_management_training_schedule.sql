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
-- Table structure for table `training_schedule`
--

DROP TABLE IF EXISTS `training_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `session_name` varchar(200) NOT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `clazz_id` bigint DEFAULT NULL,
  `clazz_type` enum('CLASSROOM','ZOOM') NOT NULL,
  `clazz_details` varchar(700) DEFAULT NULL,
  `training_type` enum('AD_HOC','MONTHLY','T_AND_S') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i1b28dg1pnmnmhgeu8m11vs8s` (`session_name`),
  KEY `FK8aq2f2xgrxk9mpyg5nqii1bwo` (`clazz_id`),
  CONSTRAINT `FK8aq2f2xgrxk9mpyg5nqii1bwo` FOREIGN KEY (`clazz_id`) REFERENCES `clazz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_schedule`
--

LOCK TABLES `training_schedule` WRITE;
/*!40000 ALTER TABLE `training_schedule` DISABLE KEYS */;
INSERT INTO `training_schedule` VALUES (3,'2023-10-03 16:33:11.753071',2,'2023-10-18 16:32:50.914255',2,'2023-11-10 16:30:00.000000','Session 3 - Microservice (part 1)','2023-11-10 14:30:00.000000',2,'ZOOM','https://us05web.zoom.us/j/84834627914?pwd=OUoxNS94ZFc1cGFuZzBPY29nbHRHUT09#success','T_AND_S'),(6,'2023-10-11 10:30:12.599662',2,'2023-10-11 10:30:12.599662',2,'2023-10-15 16:30:00.000000','Session 5 - Performance (part 3)','2023-10-15 14:30:00.000000',2,'CLASSROOM','Lab 6 - C4 Room','AD_HOC'),(8,'2023-10-13 15:57:17.834670',2,'2023-10-13 15:59:13.861733',2,'2023-10-15 16:30:00.000000','Session 5 - Performance (part 4)','2023-10-15 14:30:00.000000',2,'CLASSROOM','Lab 6 - C4 Room','AD_HOC'),(9,'2023-10-17 10:52:48.928076',2,'2023-10-18 10:12:35.343039',2,'2023-10-22 16:30:00.000000','Session 5 - Performance (part 5)','2023-10-22 13:30:00.000000',2,'ZOOM','http://zoom.com.vn','MONTHLY'),(10,'2023-10-18 09:51:18.689238',2,'2023-10-18 10:10:53.932290',2,'2023-10-21 17:30:00.000000','Session 5 - Performance (part 6)','2023-10-21 14:30:00.000000',2,'CLASSROOM','Lab 6 - C4 Room','T_AND_S'),(11,'2023-10-18 10:31:35.860945',2,'2023-10-18 11:08:35.997774',2,'2023-10-18 12:30:42.000000','Session 5 - Performance (part 7)','2023-10-18 11:30:42.000000',1,'CLASSROOM','Lab 6 - C4 Room','MONTHLY'),(16,'2023-10-25 14:43:16.228524',11,'2023-10-26 11:08:48.588646',2,'2023-11-06 16:30:23.000000','Session 1 - Javascript Overview','2023-11-06 14:30:23.000000',6,'ZOOM','http://zoom.com.vn','MONTHLY'),(17,'2023-10-26 11:20:16.912467',2,'2023-10-26 11:20:16.912467',2,'2023-10-30 13:00:14.000000','Session 2 - JavaScript Basic','2023-10-30 11:00:14.000000',6,'CLASSROOM','Lab 6 - C4 Room','MONTHLY');
/*!40000 ALTER TABLE `training_schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-29 15:31:04
