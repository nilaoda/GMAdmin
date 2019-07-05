-- MySQL dump 10.13  Distrib 5.5.40, for Win64 (x86)
--
-- Host: 127.0.0.1    Database: gmadmin
-- ------------------------------------------------------
-- Server version	5.5.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `count` int(11) NOT NULL,
  `rate` decimal(5,2) NOT NULL,
  `price` double(255,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'物品1',55,0.75,301),(7,'石中剑',1,0.02,100),(8,'无尽之刃',10,0.11,3800);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `GR` BEFORE DELETE ON `goods` FOR EACH ROW delete from role_goods where old.id = role_goods.goodsid
; */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `monster`
--

DROP TABLE IF EXISTS `monster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `attribute` int(255) NOT NULL,
  `type` int(255) NOT NULL,
  `power` int(255) NOT NULL DEFAULT '0',
  `speed` int(255) DEFAULT '0',
  `magic` int(255) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sss` (`attribute`),
  CONSTRAINT `sss` FOREIGN KEY (`attribute`) REFERENCES `monster_attribute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monster`
--

LOCK TABLES `monster` WRITE;
/*!40000 ALTER TABLE `monster` DISABLE KEYS */;
INSERT INTO `monster` VALUES (1,'怪物1',1,1,70,80,70),(2,'怪物2',2,1,70,85,87),(3,'怪物3',1,3,65,80,50),(5,'谱尼',1,3,100,100,100),(7,'剑圣',1,2,28,22,19);
/*!40000 ALTER TABLE `monster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monster_attribute`
--

DROP TABLE IF EXISTS `monster_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monster_attribute` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monster_attribute`
--

LOCK TABLES `monster_attribute` WRITE;
/*!40000 ALTER TABLE `monster_attribute` DISABLE KEYS */;
INSERT INTO `monster_attribute` VALUES (1,'火'),(2,'冰'),(3,'雷');
/*!40000 ALTER TABLE `monster_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monster_type`
--

DROP TABLE IF EXISTS `monster_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monster_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monster_type`
--

LOCK TABLES `monster_type` WRITE;
/*!40000 ALTER TABLE `monster_type` DISABLE KEYS */;
INSERT INTO `monster_type` VALUES (1,'人'),(2,'兽'),(3,'灵');
/*!40000 ALTER TABLE `monster_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) NOT NULL,
  `career` int(255) NOT NULL,
  `money` int(255) DEFAULT '0',
  `gamecoin` int(255) DEFAULT '0',
  `daycontinuous` int(255) DEFAULT '0',
  `daytotal` int(255) DEFAULT '0',
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ROLENAME` (`rolename`),
  KEY `UR` (`userid`),
  CONSTRAINT `UR` FOREIGN KEY (`userid`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'角色1',1,200,300,2,5,1),(5,'李四的刺客',3,0,0,0,0,2),(6,'Karnna',3,0,0,0,0,1),(7,'尼古拉赵六',2,0,0,0,0,5),(8,'13',1,0,0,0,0,1),(9,'测试',1,0,0,0,0,1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_career`
--

DROP TABLE IF EXISTS `role_career`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_career` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `career` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_career`
--

LOCK TABLES `role_career` WRITE;
/*!40000 ALTER TABLE `role_career` DISABLE KEYS */;
INSERT INTO `role_career` VALUES (1,'战士'),(2,'法师'),(3,'刺客');
/*!40000 ALTER TABLE `role_career` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_goods`
--

DROP TABLE IF EXISTS `role_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_goods` (
  `roleid` int(11) NOT NULL,
  `goodsid` int(11) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '0',
  KEY `1` (`roleid`),
  KEY `2` (`goodsid`),
  CONSTRAINT `1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `2` FOREIGN KEY (`goodsid`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_goods`
--

LOCK TABLES `role_goods` WRITE;
/*!40000 ALTER TABLE `role_goods` DISABLE KEYS */;
INSERT INTO `role_goods` VALUES (1,1,3);
/*!40000 ALTER TABLE `role_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `money` int(255) NOT NULL DEFAULT '0',
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`UID`,`email`),
  UNIQUE KEY `EMAIL` (`email`),
  KEY `UID` (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'刘备','123',100,'111@qq.com'),(2,'李四','1234',100,'2222@qq.com'),(3,'李四','123',100,'222@qq.com'),(5,'尼古拉斯赵四','123',100,'ashdkjashd@qq.com'),(6,'赛博朋克','123',200,'ashfkjahdjqw22@qq.com'),(7,'测试用户','123',100,'qweyoingm@qq.com'),(8,'任文策','123',100,'pppop@qq.com'),(9,'孙权','123',100,'12WPc@qq.com'),(10,'曹操','123',100,'82346789789@qq.com'),(11,'小乔','123',100,'TTTTTPP@qq.com'),(12,'大乔','123',100,'Gamepol@qq.com'),(13,'李四','123',100,'222222@qq.com'),(14,'塞巴斯蒂安','321',0,'553120@qq.com'),(15,'斯凯','741',14524,'45746856@qq.com'),(16,'欧文','789',241,'78527@qq.com'),(17,'全蛋','456',4540,'55555@qq.com'),(18,'死亡搁浅','66666',1600,'1231231@qq.com'),(19,'摸仙女王','1234567',465464,'21231232@qq.com'),(20,'油呢王子','9876543',999999,'44656@qq.com'),(21,'朵蜜','1231231',11110,'2323418754@qq.com'),(22,'小闸总','9418',66666,'2123456@qq.com'),(35,'Karna','password',0,'ab1234@qq.com');
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

-- Dump completed on 2019-07-05 16:01:05
