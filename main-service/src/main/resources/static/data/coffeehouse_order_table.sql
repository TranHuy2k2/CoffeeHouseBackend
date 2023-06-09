CREATE DATABASE  IF NOT EXISTS `coffeehouse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `coffeehouse`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: coffeehouse
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `delivery_time` datetime(6) NOT NULL,
  `note` varchar(255) NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `tongsl` int DEFAULT NULL,
  `tongtien` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr8fxoe0wvf803dar4t5thvslq` (`voucher_id`),
  KEY `idx_customer` (`customer_id`),
  CONSTRAINT `FKdit09e676nqbguvt1k1w8mlj2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKr8fxoe0wvf803dar4t5thvslq` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (3,'B3-10 Đường số 3','2023-02-27 07:58:54.569000','',3,NULL,'RECEIVED','2023-02-27 07:58:54.569000','2023-02-27 07:58:54.569000',2,100000),(4,'B21-10 Đường số 2','2023-02-27 07:58:54.569000','Để nước đá riêng',3,NULL,'RECEIVED','2023-02-27 07:58:54.569000','2023-03-22 07:22:52.302000',3,95000),(6,'B21-10 Đường số 2','2023-02-27 07:58:54.569000','',3,NULL,'RECEIVED','2023-03-21 15:31:40.202000','2023-03-22 14:27:36.518000',3,217000),(10,'B52 đường số 4','2023-03-22 08:31:58.066000','',8,NULL,'RECEIVED','2023-03-22 09:42:26.100000','2023-03-22 09:47:55.263000',2,180000),(11,'B3-10 Đường số 3','2023-03-22 09:51:03.399000','',8,NULL,'RECEIVED','2023-03-22 09:51:03.399000','2023-03-22 10:06:41.796000',4,316000),(14,'B2-10, Đường số 3','2023-04-03 15:11:31.626000','',3,NULL,'RECEIVED','2023-04-03 14:11:40.040000','2023-04-03 14:11:40.040000',2,92000),(15,'B2-10, Đường số 3','2023-04-03 15:46:54.000000','',3,NULL,'RECEIVED','2023-04-03 14:47:01.668000','2023-04-03 14:47:01.668000',3,255000),(16,'B2-10, Đường số 3','2023-04-03 16:41:18.237000','Đậu xe trước cửa công ty',3,NULL,'RECEIVED','2023-04-03 15:41:36.431000','2023-04-03 15:41:36.431000',2,136000),(17,'B2-10, Đường số 3','2023-04-03 17:22:41.950000','',3,NULL,'RECEIVED','2023-04-03 16:22:49.368000','2023-04-03 16:22:49.368000',3,171000),(18,'B2-10, Đường số 3','2023-04-03 17:28:43.006000','',3,NULL,'RECEIVED','2023-04-03 16:28:55.519000','2023-04-03 16:28:55.519000',1,85000),(20,'B2-10, Đường số 3','2023-04-11 10:21:03.000000','',3,6,'RECEIVED','2023-04-11 08:21:29.057000','2023-04-11 08:21:29.057000',2,119000),(21,'B2-10, Đường số 3','2023-04-11 10:26:05.000000','',3,6,'RECEIVED','2023-04-11 09:26:25.830000','2023-04-11 09:26:25.830000',2,114800),(22,'B3-10 Đường số 3','2023-04-17 14:37:26.764000','',8,NULL,'DELIVERING','2023-04-17 13:37:42.829000','2023-04-17 13:40:54.514000',2,170000),(23,'B3-10 Đường số 3','2023-04-17 14:53:02.437000','',8,NULL,'RECEIVED','2023-04-17 13:53:07.350000','2023-04-17 13:53:07.350000',3,171000),(24,'B2-10, Đường số 3','2023-04-17 15:02:06.469000','',3,7,'RECEIVED','2023-04-17 14:02:11.223000','2023-04-17 14:02:11.223000',2,63500),(28,'B2-10, Đường số 3','2023-04-24 19:25:11.378000','',3,NULL,'RECEIVED','2023-04-24 18:35:20.137000','2023-04-24 18:35:20.137000',2,172000),(29,'B123 - Đường số 4','2023-04-26 12:08:02.526000','Để nước đá riêng hết giùm em',11,NULL,'RECEIVED','2023-04-26 11:10:43.933000','2023-04-26 11:10:43.933000',1,65000),(32,'B2-10, Đường số 3','2023-05-27 19:27:57.855000','Đậu xe trước cổng',3,3,'RECEIVED','2023-05-27 18:35:31.288000','2023-05-27 18:35:31.289000',2,102000),(33,'B2-10, Đường số 3','2023-05-27 19:44:19.983000','',3,NULL,'RECEIVED','2023-05-27 18:50:31.827000','2023-05-27 18:50:31.827000',1,69000);
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-05 11:36:57
