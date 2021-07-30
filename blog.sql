-- MySQL dump 10.13  Distrib 8.0.25, for macos11.3 (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `m_blog`
--

DROP TABLE IF EXISTS `m_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_blog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` longtext,
  `created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint DEFAULT NULL,
  `username` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_blog`
--

LOCK TABLES `m_blog` WRITE;
/*!40000 ALTER TABLE `m_blog` DISABLE KEYS */;
INSERT INTO `m_blog` VALUES (23,2,'测试测试','description','content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.content. Hi there hhh.','2021-07-29 16:02:48',0,'felix'),(24,2,'hello','hi','hi','2021-07-29 15:37:56',0,'felix');
/*!40000 ALTER TABLE `m_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_user`
--

DROP TABLE IF EXISTS `m_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `status` int NOT NULL,
  `created` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_user`
--

LOCK TABLES `m_user` WRITE;
/*!40000 ALTER TABLE `m_user` DISABLE KEYS */;
INSERT INTO `m_user` VALUES (1,'markerhub','https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',NULL,'96e79218965eb72c92a549dd5a330112',0,'2020-04-20 10:44:01',NULL),(2,'felix','https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',NULL,'96e79218965eb72c92a549dd5a330112',0,'2020-04-20 10:44:01',NULL),(8,'钱行之',NULL,'felixqianxz@gmail.com','d3beee20af40a79530e5987f1239f96d',0,'2021-07-27 15:43:10',NULL),(9,'qxz',NULL,'felixqianxz@gmail.com','d3beee20af40a79530e5987f1239f96d',0,'2021-07-27 15:55:52',NULL),(10,'qian',NULL,'felixqianxz@gmail.com','96e79218965eb72c92a549dd5a330112',0,'2021-07-27 15:56:45',NULL);
/*!40000 ALTER TABLE `m_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-29 16:48:41
