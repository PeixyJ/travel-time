-- MySQL dump 10.13  Distrib 8.0.32, for macos13.0 (arm64)
--
-- Host: 10.0.0.243    Database: travel_time
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `travel_record`
--

DROP TABLE IF EXISTS `travel_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travel_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '行驶记录ID',
  `user_id` int NOT NULL DEFAULT '-1' COMMENT '用户ID',
  `travel_code` varchar(256) NOT NULL DEFAULT '' COMMENT '行驶编号',
  `travel_time` datetime NOT NULL COMMENT '行驶发生时间',
  `driving_time` bigint NOT NULL COMMENT '驾驶时间',
  `longitude` varchar(64) NOT NULL COMMENT '经度',
  `latitude` varchar(64) NOT NULL COMMENT '纬度',
  `travel_distance` int NOT NULL DEFAULT '-1' COMMENT '行驶距离',
  `travel_type` varchar(8) NOT NULL DEFAULT 'unknown' COMMENT '驾驶类型',
  `site` varchar(128) NOT NULL DEFAULT 'unknown' COMMENT '地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 COMMENT='行驶记录表';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nickname` varchar(64) NOT NULL COMMENT '昵称',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `salt` varchar(256) NOT NULL COMMENT '盐',
  `open_id` varchar(256) NOT NULL COMMENT 'OpenId',
  `home_lat` varchar(256) NOT NULL COMMENT '家里的经纬度',
  `company_lat` varchar(64) NOT NULL COMMENT '公司经纬度',
  `bark_id` varchar(256) DEFAULT '' COMMENT 'Bark通知ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;