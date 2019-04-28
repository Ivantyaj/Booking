-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: bookingbd
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_room` bigint(20) NOT NULL,
  `id_service` bigint(20) DEFAULT NULL,
  `arrival_date` datetime NOT NULL,
  `leaving_date` datetime NOT NULL,
  `id_client` bigint(20) NOT NULL,
  `human_amount` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idclient_idx` (`id_client`),
  KEY `idroom_idx` (`id_room`),
  KEY `idservice_idx` (`id_service`),
  CONSTRAINT `clients` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  CONSTRAINT `idroom` FOREIGN KEY (`id_room`) REFERENCES `hotel_room` (`id`),
  CONSTRAINT `idservice` FOREIGN KEY (`id_service`) REFERENCES `service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) NOT NULL,
  `fio` varchar(255) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `passport_num` varchar(45) NOT NULL,
  `count_visiting` bigint(20) NOT NULL DEFAULT '0',
  `id_discount` bigint(20) DEFAULT NULL,
  `need_call` bit(1) NOT NULL DEFAULT b'0',
  `last_message` varchar(1000) DEFAULT NULL,
  `card_holder` varchar(255) DEFAULT NULL,
  `card_cvv` varchar(5) DEFAULT NULL,
  `card_date` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `iddiscount_idx` (`id_discount`),
  KEY `iduser_idx` (`id_user`),
  CONSTRAINT `iddiscount` FOREIGN KEY (`id_discount`) REFERENCES `discount` (`id`),
  CONSTRAINT `iduser` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (5,4,'Жоржевич Юра Пупкин','+375(29)704-15-17','mail@mail.ru','123456',3,NULL,_binary '\0',NULL,NULL,NULL,NULL),(6,5,'Тарасевич Братан Петрович','+375(29)987-15-00','rewq@mail.ru','234567',1,NULL,_binary '','',NULL,NULL,NULL),(7,6,'Мошко Галуга Федорович','+375(29)134-15-17','zxcv@mail.ru','345678',1,NULL,_binary '','Принесите чаю',NULL,NULL,NULL),(8,7,'Кардин Игорь Сеневич','+375(29)454-15-17','asdf@mail.ru','456789',9,NULL,_binary '','Мне скучно',NULL,NULL,NULL),(9,8,'Молоко Булка Плюшкович','+375(33)654-88-44','qwer@mail.ru','657890',2,NULL,_binary '\0',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `discount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `percent` bigint(20) NOT NULL,
  `min_visiting_count` bigint(20) NOT NULL DEFAULT '1',
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark` varchar(45) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `id_client` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idclients_idx` (`id_client`),
  CONSTRAINT `idclients` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_room`
--

DROP TABLE IF EXISTS `hotel_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `people_amount` bigint(20) NOT NULL DEFAULT '1',
  `id_type` bigint(20) DEFAULT NULL,
  `price` double NOT NULL,
  `picture_url` varchar(255) NOT NULL DEFAULT 'https://vk.cc/9kOXbA',
  `description` varchar(255) NOT NULL DEFAULT ' Пока нет описания ',
  PRIMARY KEY (`id`),
  KEY `idtype_idx` (`id_type`),
  CONSTRAINT `idtype` FOREIGN KEY (`id_type`) REFERENCES `room_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_room`
--

LOCK TABLES `hotel_room` WRITE;
/*!40000 ALTER TABLE `hotel_room` DISABLE KEYS */;
INSERT INTO `hotel_room` VALUES (1,2,1,430,'https://images.wallpaperscraft.com/image/bedroom_furniture_tenderness_romance_comfort_70085_3840x2160.jpg','Хороший номер для двоих'),(2,4,3,200,'https://www.exler.ru/blog/upload/images/big/IMG_2594.JPG','Номер отлично подходит для семейного отдыха'),(3,2,1,500,'http://dekormyhome.ru/wp-content/uploads/2019/12/37-22.jpg','Лучший номер, для наших самых любимых гостей'),(4,5,2,100,'http://www.wallpapers4u.org/wp-content/uploads/bedroom_antique_bed_portrait_interiors_39257_1920x1080.jpg','Самый дешманский номер, но вам пойдет'),(5,1,2,120,'http://www.wallpapers4u.org/wp-content/uploads/bedroom_antique_bed_portrait_interiors_39257_1920x1080.jpg','Просто комнатушка');
/*!40000 ALTER TABLE `hotel_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  `permission` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin',1),(2,'user',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type`
--

DROP TABLE IF EXISTS `room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `room_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type`
--

LOCK TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type` VALUES (1,'LUX','Номер для самых изысанных предпочтений'),(2,'Стандарт','Обычный номер для крестьян'),(3,'Страндарт +','Обычный номер, но чуть дороже');
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribers`
--

DROP TABLE IF EXISTS `subscribers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subscribers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribers`
--

LOCK TABLES `subscribers` WRITE;
/*!40000 ALTER TABLE `subscribers` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscribers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idrole_idx` (`id_role`),
  CONSTRAINT `idrole` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'admin','admin',1),(4,'1','1',2),(5,'2','2',2),(6,'3','3',2),(7,'4','4',2),(8,'5','5',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-28 23:03:02
