-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pf_petfood
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `pf_np_region`
--

DROP TABLE IF EXISTS `pf_np_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pf_np_region` (
  `ref` varchar(255) NOT NULL,
  `areas_center` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `description_ru` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ref`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pf_np_region`
--

LOCK TABLES `pf_np_region` WRITE;
/*!40000 ALTER TABLE `pf_np_region` DISABLE KEYS */;
INSERT INTO `pf_np_region` VALUES ('71508128-9b87-11de-822f-000c2965ae0e','db5c88b7-391c-11dd-90d9-001a92567626','АРК','АРК'),('71508129-9b87-11de-822f-000c2965ae0e','db5c88de-391c-11dd-90d9-001a92567626','Вінницька','Винницкая'),('7150812a-9b87-11de-822f-000c2965ae0e','db5c893b-391c-11dd-90d9-001a92567626','Волинська','Волынская'),('7150812b-9b87-11de-822f-000c2965ae0e','db5c88f0-391c-11dd-90d9-001a92567626','Дніпропетровська','Днепропетровская'),('7150812c-9b87-11de-822f-000c2965ae0e','db5c88bf-391c-11dd-90d9-001a92567626','Донецька','Донецкая'),('7150812d-9b87-11de-822f-000c2965ae0e','db5c88c4-391c-11dd-90d9-001a92567626','Житомирська','Житомирская'),('7150812e-9b87-11de-822f-000c2965ae0e','e221d627-391c-11dd-90d9-001a92567626','Закарпатська','Закарпатская'),('7150812f-9b87-11de-822f-000c2965ae0e','db5c88c6-391c-11dd-90d9-001a92567626','Запорізька','Запорожская'),('71508130-9b87-11de-822f-000c2965ae0e','db5c8904-391c-11dd-90d9-001a92567626','Івано-Франківська','Ивано-Франковская'),('71508131-9b87-11de-822f-000c2965ae0e','8d5a980d-391c-11dd-90d9-001a92567626','Київська','Киевская'),('71508132-9b87-11de-822f-000c2965ae0e','db5c891b-391c-11dd-90d9-001a92567626','Кіровоградська','Кировоградская'),('71508133-9b87-11de-822f-000c2965ae0e','db5c88ba-391c-11dd-90d9-001a92567626','Луганська','Луганская'),('71508134-9b87-11de-822f-000c2965ae0e','db5c88f5-391c-11dd-90d9-001a92567626','Львівська','Львовская'),('71508135-9b87-11de-822f-000c2965ae0e','db5c888c-391c-11dd-90d9-001a92567626','Миколаївська','Николаевская'),('71508136-9b87-11de-822f-000c2965ae0e','db5c88d0-391c-11dd-90d9-001a92567626','Одеська','Одесская'),('71508137-9b87-11de-822f-000c2965ae0e','db5c8892-391c-11dd-90d9-001a92567626','Полтавська','Полтавская'),('71508138-9b87-11de-822f-000c2965ae0e','db5c896a-391c-11dd-90d9-001a92567626','Рівненська','Ровенская'),('71508139-9b87-11de-822f-000c2965ae0e','db5c88e5-391c-11dd-90d9-001a92567626','Сумська','Сумская'),('7150813a-9b87-11de-822f-000c2965ae0e','db5c8900-391c-11dd-90d9-001a92567626','Тернопільська','Тернопольская'),('7150813b-9b87-11de-822f-000c2965ae0e','db5c88e0-391c-11dd-90d9-001a92567626','Харківська','Харьковская'),('7150813c-9b87-11de-822f-000c2965ae0e','db5c88cc-391c-11dd-90d9-001a92567626','Херсонська','Херсонская'),('7150813d-9b87-11de-822f-000c2965ae0e','db5c88ac-391c-11dd-90d9-001a92567626','Хмельницька','Хмельницкая'),('7150813e-9b87-11de-822f-000c2965ae0e','db5c8902-391c-11dd-90d9-001a92567626','Черкаська','Черкасская'),('7150813f-9b87-11de-822f-000c2965ae0e','e221d642-391c-11dd-90d9-001a92567626','Чернівецька','Черновицкая'),('71508140-9b87-11de-822f-000c2965ae0e','db5c897c-391c-11dd-90d9-001a92567626','Чернігівська','Черниговская');
/*!40000 ALTER TABLE `pf_np_region` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-26 22:35:54
