CREATE DATABASE  IF NOT EXISTS `webaodai` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `webaodai`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: webaodai
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int NOT NULL,
  `product_size_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `size_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKwf7ajefj1nkvesk74p2i87vb` (`product_size_id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`),
  KEY `FK3d704slv66tw6x5hmbm6p2x3u` (`product_id`),
  KEY `FK1g6tqdhv91y5fytvihysvcuw5` (`size_id`),
  CONSTRAINT `FK1g6tqdhv91y5fytvihysvcuw5` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKwf7ajefj1nkvesk74p2i87vb` FOREIGN KEY (`product_size_id`) REFERENCES `product_size` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,1,NULL,2,3,2),(5,1,NULL,5,13,3),(6,1,NULL,5,1,1),(7,1,NULL,5,2,1),(8,1,NULL,5,3,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Áo dài truyền thống',1),(2,'Áo dài cách tân',1),(3,'Áo dài cưới',1),(4,'Áo dài',0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_size`
--

DROP TABLE IF EXISTS `category_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `size_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKko9w2jke3lr73fwbiad0q3ojv` (`category_id`),
  KEY `FKiqt5jcixwfl19w2l7saugxden` (`size_id`),
  CONSTRAINT `FKiqt5jcixwfl19w2l7saugxden` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  CONSTRAINT `FKko9w2jke3lr73fwbiad0q3ojv` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_size`
--

LOCK TABLES `category_size` WRITE;
/*!40000 ALTER TABLE `category_size` DISABLE KEYS */;
INSERT INTO `category_size` VALUES (27,1,1),(28,1,2),(29,1,3),(30,2,1),(31,2,2),(32,2,3),(33,3,1),(34,3,2);
/*!40000 ALTER TABLE `category_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `booking_date` datetime(6) DEFAULT NULL,
  `country` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `fullname` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `note` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `payment_method` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `total` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcpl0mjoeqhxvgeeeq5piwpd3i` (`user_id`),
  CONSTRAINT `FKcpl0mjoeqhxvgeeeq5piwpd3i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (6,'hòa lạc ','2025-06-10 00:00:00.000000','vietnam','nttuclone002@gmail.com','tuantu','','Payment on delivery','0562004812','Pending',1500000,5),(7,'hòa lạc ','2025-06-10 00:00:00.000000','vietnam','nttuclone002@gmail.com','tuantu','','Payment on delivery','0562004812','Pending',101200000,5),(8,'hòa lạc ','2025-06-09 00:00:00.000000','vietnam','nttuclone002@gmail.com','tuantu','','Payment on delivery','0562004812','Succeeded',101200000,5);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `product_size_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `size_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs234mi6jususbx4b37k44cipy` (`order_id`),
  KEY `FKhhtfmjxqj7awt1qmiydpp4n9e` (`product_size_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  KEY `FK8klnbu469mgjnuybpa6lbkljo` (`size_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK8klnbu469mgjnuybpa6lbkljo` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  CONSTRAINT `FKhhtfmjxqj7awt1qmiydpp4n9e` FOREIGN KEY (`product_size_id`) REFERENCES `product_size` (`id`),
  CONSTRAINT `FKs234mi6jususbx4b37k44cipy` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (6,1,6,NULL,3,1),(7,1,7,NULL,13,3),(8,1,7,NULL,1,1),(9,1,8,NULL,13,3),(10,1,8,NULL,1,1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `description` varchar(11111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_active` int DEFAULT NULL,
  `is_selling` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `product_name` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT 'FEMALE',
  `sold` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2025-05-31','Áo dài xanh truyền thống với họa tiết tinh tế',1,1,1200000,'Áo dài xanh','FEMALE',0,1,'https://file.hstatic.net/200000503583/file/ao-dai-lemur__3__68aa5ede08f64add8579b5c5090833f2.jpg?fbclid=IwY2xjawK1sJNleHRuA2FlbQIxMABicmlkETFjdVBOaHFuTnQxYXZ0RW1PAR5o9_Pl0KeCl4wWJEVH_yWAjkujW3CmZokoip4eJ7ItZTeZ6Sce8j-SrTBh_A_aem_EFHJSqDKLIjuUy3S9WgcLQ',10),(2,'2025-06-02','Áo dài trắng học sinh dịu dàng, thanh lịch',1,1,1000000,'Áo dài trắng','FEMALE',5,1,'https://tailocwedding.vn/wp-content/uploads/2022/11/Ao-dai-le-pho-00011-519x800.jpg?fbclid=IwY2xjawK1sHRleHRuA2FlbQIxMABicmlkETFjdVBOaHFuTnQxYXZ0RW1PAR7UZoSgGbhz-I-P-ONGdkLqrtPJwl002MGjYWLAPrC7I8v7rpWMLRUto5vniA_aem_Gb_447oxEYrAMTN0ShKHkQ',11),(3,'2025-05-29','Áo dài cách tân với họa tiết hoa nổi bật',1,1,1500000,'Áo dài cam','FEMALE',7,2,'https://namtuyen.com/wp-content/uploads/2024/01/ao-dai-ngu-than-nu-ao-dai-nam-tuyen-1-2.jpg?fbclid=IwY2xjawK1sKJleHRuA2FlbQIxMABicmlkETFjdVBOaHFuTnQxYXZ0RW1PAR42Q517uJeQ1FHaWz4mShHtD8Ak47ToxMQODNegHHJlAkaISdDCGQi3Jh91hA_aem_OEebSq7mEbXkj8ai6tdnxQ',7),(4,'2025-06-03','Áo dài cách tân màu vàng chanh nổi bật',1,1,1600000,'Áo dài vàng','FEMALE',3,2,'https://lamia.com.vn/storage/ao-dai/ao-dai-cach-tan-ta-ngan-tay-lo-ad087-5.jpg?fbclid=IwY2xjawK1sLJleHRuA2FlbQIxMABicmlkETFjdVBOaHFuTnQxYXZ0RW1PAR7SCMzAzdE8yPjb3YfcmeDQtQbi1_NPqCnCaRSqfzua63fNA7LFlUGTtkMwLw_aem_MUmKE-X59IKT1_9XxJ64dQ',22),(11,'2025-06-07','Set áo dài cách tân nữ Econice Nga Tô',1,NULL,990000,'Set áo dài cách tân nữ Econice Nga Tô','FEMALE',0,2,'http://res.cloudinary.com/dnoitd3ju/image/upload/v1749393062/bivu9m6laavcu5w8jpmj.png',12),(12,'2025-06-07','Sét bộ áo dài tơ thêu hoa',1,NULL,678000,'Sét bộ áo dài tơ thêu hoa','FEMALE',0,1,'http://res.cloudinary.com/dnoitd3ju/image/upload/v1749400051/p16dnorkywtubqn2iut0.png',12),(13,'2025-06-08','Áo dài lemur',1,NULL,100000000,'Lemur','FEMALE',0,2,'http://res.cloudinary.com/dnoitd3ju/image/upload/v1749606315/zxx6zqsrdo82xsiotk8c.png',998);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url_image` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6oo0cvcdtb6qmwsga468uuukk` (`product_id`),
  CONSTRAINT `FK6oo0cvcdtb6qmwsga468uuukk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (2,'http://res.cloudinary.com/dnoitd3ju/image/upload/v1750346076/easigcozlp9ssmhwexrj.png',1),(4,'http://res.cloudinary.com/dnoitd3ju/image/upload/v1750347115/k6zhe9ppt9xnwd7udrnd.png',1),(5,'http://res.cloudinary.com/dnoitd3ju/image/upload/v1750347184/i0rxohvkqme2tc7oiezv.png',1),(6,'http://res.cloudinary.com/dnoitd3ju/image/upload/v1750347205/j3tczoxgrbsbgsughvdc.png',1);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_size`
--

DROP TABLE IF EXISTS `product_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `size_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8i3jm2ctt0lsyeik2wt76yvv0` (`product_id`),
  KEY `FKnlkh5xcjuopsnoimdvmumioia` (`size_id`),
  CONSTRAINT `FK8i3jm2ctt0lsyeik2wt76yvv0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKnlkh5xcjuopsnoimdvmumioia` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_size`
--

LOCK TABLES `product_size` WRITE;
/*!40000 ALTER TABLE `product_size` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `description` varchar(11111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `size_name` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,NULL,'L','L'),(2,NULL,'M','M'),(3,NULL,'S','S');
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_number` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `role` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'3602 Gaylord Dr','admin@gmail.com','ADMIN','$2a$12$e9v8B3eh/dSBnbl3CJWLGOgF6cRdbom1QIGurk/XHjbwXxnqfa1W.','6308348000','ADMIN',1),(2,'3602 Gaylord Dr','phamminhhiep0402@gmail.com','Nguyễn Trang','$2a$10$obOZRq.2xzUB9ATCJMje7upt1qZ1L7eIgZzdrmUq07Y69FWVOV/62','6308348000','USER',1),(3,'3602 Gaylord Dr','hvv@gmail.com','Hoàng Văn Vinh','$2a$10$d6.myZVSo3Rs4h.wyZO9S.YFa.f3YdGx2FRODtr5DWDNX7lMxpEKe','6308348000','ADMIN',1),(4,'hòa lạc ','tuantua72004@gmail.com','shishou','$2a$10$fAj.NtCT2t8kl4DY0jFWZ.p11zSjmQlg0Rjq5//4m/T30JEMGp4fy','0562004812','USER',1),(5,'hòa lạc ','nttuclone002@gmail.com','tuantu','$2a$10$d6.myZVSo3Rs4h.wyZO9S.YFa.f3YdGx2FRODtr5DWDNX7lMxpEKe','0562004812','USER',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'webaodai'
--

--
-- Dumping routines for database 'webaodai'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-19 23:05:19
