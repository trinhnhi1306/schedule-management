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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','TRAINEE','TRAINER') NOT NULL,
  `username` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2023-09-27 17:27:09.018878',NULL,'2023-09-27 17:27:09.018878',NULL,'reintrinh1306@tma.com.vn','Alice','Anderson','$2a$10$oMKiwHfnYmazcB0BkskcPeYBZdqMUTE5I7GQO2dpxhUKLBE/SoUlm','TRAINEE','reintrinh1306','peach_and_goma.webp'),(2,'2023-09-27 17:27:53.571593',NULL,'2023-09-27 17:27:53.571593',NULL,'quanlee2408@tma.com.vn','Benjamin','Brown','$2a$10$dCazghJuTnApTJHWy2ClCOrw62azsrfpJuBZA20jlFzWvvp/z19JS','ADMIN','quanlee2408','peach_and_goma.webp'),(3,'2023-09-28 17:19:04.236797',NULL,'2023-09-28 17:19:04.236797',NULL,'reinlee2408@tma.com.vn','Carol','Clark','$2a$10$xohvbPW7V81.aAW1JJVj5.vvHipmAmeCgRjMZmLWFCE7VUSou2gVu','TRAINER','reinlee2408','peach-goma-rabbit.webp'),(4,'2023-09-28 17:19:15.136298',NULL,'2023-09-28 17:19:15.136298',NULL,'reinlee1306@tma.com.vn','David','Davis','$2a$10$tRLghZ0u0pVaMQExs2JI5uyejnNUSoAoEOtL9SZLwHS3DUh3GFTQm','TRAINER','reinlee1306','peach_and_goma.webp'),(5,'2023-09-29 16:15:23.045613',NULL,'2023-10-17 13:50:20.875153',NULL,'trainer1@tma.com.vn','Emily','Evans','$2a$10$l4xEXrG2O1wOblih8hpO1OQXsXzededpsYQidoLZbfrndGORAtUmG','TRAINER','trainer1','goma-hugs-peach.webp'),(6,'2023-09-29 16:15:28.087543',NULL,'2023-10-17 13:50:20.876150',NULL,'trainer2@tma.com.vn','Frank','Foster','$2a$10$BvRyQ4BC8e9wq6uqXnffbuuFXII8ZnKS5c/rv97eyJjbS4FEYgE0O','TRAINER','trainer2','peach_and_goma.webp'),(7,'2023-09-29 16:15:31.862710',NULL,'2023-09-29 16:15:31.862710',NULL,'trainer3@tma.com.vn','Grace','Green','$2a$10$WWqZod7wkfu9krziSxW9ru02vcJB1bjAIlUXHLCC6/Ke19UuJkJgy','TRAINER','trainer3','peach-bite-goma.webp'),(8,'2023-10-11 10:42:02.796042',NULL,'2023-10-11 10:42:02.796042',NULL,'trainer4@tma.com.vn','Henry','Harris','$2a$10$rz76yyrp0PZXBWjMZBvtm.j9xpzY6Pc526/yhTDM3XBR4sDLwRJI.','TRAINER','trainer4','peach_and_goma.webp'),(9,'2023-10-18 09:38:14.569036',NULL,'2023-10-18 09:38:14.569036',NULL,'trainer5@tma.com.vn','Isabella','Jackson','$2a$10$utIXXEuO4l25Os3/IP8KCujO3igH9q9OPCjwcCKq3yQW0NmUsEBQ2','TRAINER','trainer5','small-peach-cat-goma-cat.webp'),(10,'2023-10-18 10:48:35.863435',NULL,'2023-10-18 10:48:35.863435',NULL,'trainer6@tma.com.vn','Jack','Johnson','$2a$10$6f9ewS6BxA0lOM5kGwBU2O4kFMsrljHN4xU8rLkMSBFBz5cTuYDH.','TRAINER','trainer6','peach_and_goma.webp'),(11,'2023-10-18 16:12:19.683556',NULL,'2023-10-18 16:12:19.683556',NULL,'admin1@tma.com.vn','Admin','Nguyen','$2a$10$JECebPdl2r8lcL0wm4jvbeS9XEznqljOPaa2OUCYgdDn6yHjABcpO','ADMIN','admin1','peach_and_goma.webp'),(12,'2023-10-25 11:32:50.040879',11,'2023-10-25 11:32:50.040879',11,'admin2@tma.com.vn','Admin','Tran','$2a$10$2DLXaZnPbZmG0.B4tdeN4.VRXYqi4vTNCFc1uPfTz65tZPP6vTgJS','ADMIN','admin2','peach_and_goma.webp'),(13,'2023-10-25 18:23:16.421648',NULL,'2023-10-26 10:06:00.200085',2,'admin3@tma.com.vn','Admin','Le','$2a$10$Eu2e9fU1k6Z3y6iNH/x.TOQHbIO/GyBMMounDRYZfUM/Ir9n6FwOC','TRAINEE','admin3','default');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-29 15:31:06
