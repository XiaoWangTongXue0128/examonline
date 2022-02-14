-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: exam
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `t_dictionary`
--

DROP TABLE IF EXISTS `t_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dictionary`
--

LOCK TABLES `t_dictionary` WRITE;
/*!40000 ALTER TABLE `t_dictionary` DISABLE KEYS */;
INSERT INTO `t_dictionary` VALUES (1,'软件工程','专业'),(2,'计算机科学与技术','专业'),(3,'网络工程','专业'),(4,'电子信息工程','专业'),(5,'电气自动化','专业'),(6,'土木工程','专业'),(7,'车辆工程','专业'),(8,'软件工程','专业'),(9,'应用数学','专业'),(10,'java程序设计','课程'),(11,'C语言程序设计','课程'),(12,'软件工程','课程'),(13,'编译原理','课程'),(14,'计算机组成原理','课程'),(15,'高等数学','课程'),(16,'线性代数','课程'),(17,'汇编语言','课程');
/*!40000 ALTER TABLE `t_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_exam`
--

DROP TABLE IF EXISTS `t_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `course` varchar(32) DEFAULT NULL,
  `template_id` bigint(20) DEFAULT NULL,
  `tid` bigint(20) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL COMMENT '单位：分钟',
  `status` varchar(32) DEFAULT NULL COMMENT '正常（未发布，已发布，考试中，已完成），丢弃',
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  `yl3` varchar(32) DEFAULT NULL,
  `yl4` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_exam`
--

LOCK TABLES `t_exam` WRITE;
/*!40000 ALTER TABLE `t_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question`
--

DROP TABLE IF EXISTS `t_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '单选题，多选题，判断题，填空题，综合题',
  `course` varchar(32) DEFAULT NULL COMMENT '直接存储课程名称：经济法',
  `level` varchar(32) DEFAULT NULL COMMENT '简单，中等，困难',
  `subject` text,
  `options` text,
  `answer` text,
  `status` varchar(32) DEFAULT NULL COMMENT '正常（私有，公有），丢弃',
  `tid` bigint(20) DEFAULT NULL COMMENT '关联teacher.id',
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  `yl3` varchar(32) DEFAULT NULL,
  `yl4` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question`
--

LOCK TABLES `t_question` WRITE;
/*!40000 ALTER TABLE `t_question` DISABLE KEYS */;
INSERT INTO `t_question` VALUES (1,'单选题','java程序设计','简单','以下哪一个不是赋值运算符1','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(2,'单选题','java程序设计','简单','以下哪一个不是赋值运算符2','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(3,'单选题','java程序设计','简单','以下哪一个不是赋值运算符3','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(4,'单选题','java程序设计','简单','以下哪一个不是赋值运算符4','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(5,'单选题','java程序设计','简单','以下哪一个不是赋值运算符5','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(6,'单选题','java程序设计','简单','以下哪一个不是赋值运算符6','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(7,'多选题','java程序设计','中等','静态关键字static都可以修饰哪些内容2','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(8,'判断题','C语言程序设计','简单','<p>3333</p>','','1','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(9,'多选题','java程序设计','中等','静态关键字static都可以修饰哪些内容4','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(10,'判断题','java程序设计','简单','抽象类中必须定义抽象方法2','','1','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(11,'判断题','java程序设计','简单','抽象类中必须定义抽象方法3','','1','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(12,'判断题','java程序设计','简单','抽象类中必须定义抽象方法4','','1','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(13,'填空题','java程序设计','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口2','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(14,'填空题','java程序设计','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口3','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(15,'填空题','java程序设计','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口4','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(16,'综合题','java程序设计','中等','面向对象的特征1','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(17,'综合题','java程序设计','中等','面向对象的特征2','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(18,'综合题','java程序设计','中等','面向对象的特征3','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(19,'综合题','java程序设计','中等','面向对象的特征4','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(20,'判断题','java程序设计','简单','<p>final修饰的方法不能被重载</p>','','1','私有',1,NULL,NULL,NULL,NULL,'2022-01-27 16:35:57',NULL),(21,'单选题','C语言程序设计','简单','以下哪一个不是赋值运算符1','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(22,'单选题','C语言程序设计','简单','以下哪一个不是赋值运算符2','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(23,'单选题','C语言程序设计','简单','以下哪一个不是赋值运算符3','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(24,'单选题','C语言程序设计','简单','以下哪一个不是赋值运算符4','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(25,'单选题','C语言程序设计','简单','以下哪一个不是赋值运算符5','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(26,'单选题','C语言程序设计','简单','以下哪一个不是赋值运算符6','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(27,'多选题','C语言程序设计','中等','静态关键字static都可以修饰哪些内容2','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(28,'多选题','C语言程序设计','中等','静态关键字static都可以修饰哪些内容3','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(29,'多选题','C语言程序设计','中等','静态关键字static都可以修饰哪些内容4','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(30,'判断题','C语言程序设计','简单','抽象类中必须定义抽象方法2','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(31,'判断题','C语言程序设计','简单','抽象类中必须定义抽象方法3','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(32,'判断题','C语言程序设计','简单','抽象类中必须定义抽象方法4','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(33,'填空题','C语言程序设计','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口2','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(34,'填空题','C语言程序设计','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口3','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(35,'填空题','C语言程序设计','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口4','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(36,'综合题','C语言程序设计','中等','面向对象的特征1','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(37,'综合题','C语言程序设计','中等','面向对象的特征2','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(38,'综合题','C语言程序设计','中等','面向对象的特征3','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(39,'综合题','C语言程序设计','中等','面向对象的特征4','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(40,'单选题','','简单','以下哪一个不是赋值运算符1','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(41,'单选题','','简单','以下哪一个不是赋值运算符1','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(42,'单选题','','简单','以下哪一个不是赋值运算符2','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(43,'单选题','','简单','以下哪一个不是赋值运算符2','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(44,'单选题','','简单','以下哪一个不是赋值运算符3','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(45,'单选题','','简单','以下哪一个不是赋值运算符3','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(46,'单选题','','简单','以下哪一个不是赋值运算符4','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(47,'单选题','','简单','以下哪一个不是赋值运算符4','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(48,'单选题','','简单','以下哪一个不是赋值运算符5','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(49,'单选题','','简单','以下哪一个不是赋值运算符5','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(50,'单选题','','简单','以下哪一个不是赋值运算符6','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(51,'单选题','','简单','以下哪一个不是赋值运算符6','+=}-|-{-=}-|-{++}-|-{%=}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(52,'多选题','','中等','静态关键字static都可以修饰哪些内容2','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(53,'多选题','','中等','静态关键字static都可以修饰哪些内容2','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(54,'多选题','','中等','静态关键字static都可以修饰哪些内容3','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(55,'多选题','','中等','静态关键字static都可以修饰哪些内容3','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(56,'多选题','','中等','静态关键字static都可以修饰哪些内容4','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(57,'多选题','','中等','静态关键字static都可以修饰哪些内容4','类}-|-{属性}-|-{方法}-|-{代码段}-|-{','1}-|-{2}-|-{3}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(58,'判断题','','简单','抽象类中必须定义抽象方法2','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(59,'判断题','','简单','抽象类中必须定义抽象方法3','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(60,'判断题','','简单','抽象类中必须定义抽象方法2','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(61,'判断题','','简单','抽象类中必须定义抽象方法4','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(62,'判断题','','简单','抽象类中必须定义抽象方法3','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(63,'填空题','','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口2','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(64,'判断题','','简单','抽象类中必须定义抽象方法4','','1','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(65,'填空题','','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口3','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(66,'填空题','','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口4','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(67,'综合题','','中等','面向对象的特征1','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(68,'填空题','','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口2','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(69,'填空题','','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口3','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(70,'综合题','','中等','面向对象的特征2','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(71,'填空题','','中等','使用【】关键字修饰的类称为抽象类。类使用【】关键字实现接口4','','abstract}-|-{implements}-|-{','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(72,'综合题','','中等','面向对象的特征1','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(73,'综合题','','中等','面向对象的特征3','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(74,'综合题','','中等','面向对象的特征4','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(75,'综合题','','中等','面向对象的特征2','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(76,'综合题','','中等','面向对象的特征3','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(77,'综合题','','中等','面向对象的特征4','','封装，继承，多态','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 20:54:34',NULL),(78,'单选题','C语言程序设计','简单','<p>11</p>','<p>1</p>}-|-{<p>2</p>}-|-{<p>3</p>}-|-{<p>4</p>}-|-{','2','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 22:31:47',NULL),(79,'判断题','C语言程序设计','简单','','','0','私有',1,NULL,NULL,NULL,NULL,'2022-02-07 22:32:22',NULL);
/*!40000 ALTER TABLE `t_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student`
--

DROP TABLE IF EXISTS `t_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `sname` varchar(32) DEFAULT NULL,
  `mnemonic_code` varchar(32) DEFAULT NULL,
  `pass` varchar(64) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL COMMENT '2021,2022',
  `major` varchar(32) DEFAULT NULL COMMENT '直接存储专业名称：财务会计',
  `class_no` varchar(32) DEFAULT NULL COMMENT '1班',
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  `yl3` varchar(32) DEFAULT NULL,
  `yl4` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_Key_2` (`code`),
  UNIQUE KEY `AK_Key_3` (`sname`),
  UNIQUE KEY `AK_Key_4` (`mnemonic_code`)
) ENGINE=InnoDB AUTO_INCREMENT=373 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student`
--

LOCK TABLES `t_student` WRITE;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` VALUES (250,'202011001','乔峰1','qiaofeng1','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(251,'202011002','乔峰2','qiaofeng2','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(252,'202011003','乔峰3','qiaofeng3','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(253,'202011004','乔峰4','qiaofeng4','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(254,'202011005','乔峰5','qiaofeng5','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(255,'202011006','乔峰6','qiaofeng6','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(256,'202011007','乔峰7','qiaofeng7','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(257,'202011008','乔峰8','qiaofeng8','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(258,'202011009','乔峰9','qiaofeng9','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(259,'202011010','乔峰10','qiaofeng10','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(260,'202011011','乔峰11','qiaofeng11','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(261,'202011012','乔峰12','qiaofeng12','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(262,'202011013','乔峰13','qiaofeng13','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(263,'202011014','乔峰14','qiaofeng14','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(264,'202011015','乔峰15','qiaofeng15','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(265,'202011016','乔峰16','qiaofeng16','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(266,'202011017','乔峰17','qiaofeng17','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(267,'202011018','乔峰18','qiaofeng18','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(268,'202011019','乔峰19','qiaofeng19','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(269,'202011020','乔峰20','qiaofeng20','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(270,'202011021','乔峰21','qiaofeng21','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(271,'202011022','乔峰22','qiaofeng22','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(272,'202011023','乔峰23','qiaofeng23','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(273,'202011024','乔峰24','qiaofeng24','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(274,'202011025','乔峰25','qiaofeng25','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(275,'202011026','乔峰26','qiaofeng26','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(276,'202011027','乔峰27','qiaofeng27','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(277,'202011028','乔峰28','qiaofeng28','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(278,'202011029','乔峰29','qiaofeng29','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(279,'202011030','乔峰30','qiaofeng30','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(280,'202011031','乔峰31','qiaofeng31','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(281,'202011032','乔峰32','qiaofeng32','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(282,'202011033','乔峰33','qiaofeng33','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(283,'202011034','乔峰34','qiaofeng34','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(284,'202011035','乔峰35','qiaofeng35','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(285,'202011036','乔峰36','qiaofeng36','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(286,'202011037','乔峰37','qiaofeng37','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(287,'202011038','乔峰38','qiaofeng38','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(288,'202011039','乔峰39','qiaofeng39','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(289,'202011040','乔峰40','qiaofeng40','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(290,'202011041','乔峰41','qiaofeng41','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(291,'202011042','乔峰42','qiaofeng42','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(292,'202011043','乔峰43','qiaofeng43','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(293,'202011044','乔峰44','qiaofeng44','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(294,'202011045','乔峰45','qiaofeng45','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(295,'202011046','乔峰46','qiaofeng46','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(296,'202011047','乔峰47','qiaofeng47','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(297,'202011048','乔峰48','qiaofeng48','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(298,'202011049','乔峰49','qiaofeng49','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(299,'202011050','乔峰50','qiaofeng50','202cb962ac59075b964b07152d234b70',2020,'软件','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(300,'202012001','段誉1','duanyu1','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(301,'202012002','段誉2','duanyu2','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(302,'202012003','段誉3','duanyu3','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(303,'202012004','段誉4','duanyu4','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(304,'202012005','段誉5','duanyu5','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(305,'202012006','段誉6','duanyu6','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(306,'202012007','段誉7','duanyu7','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(307,'202012008','段誉8','duanyu8','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(308,'202012009','段誉9','duanyu9','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(309,'202012010','段誉10','duanyu10','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(310,'202012011','段誉11','duanyu11','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(311,'202012012','段誉12','duanyu12','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(312,'202012013','段誉13','duanyu13','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(313,'202012014','段誉14','duanyu14','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(314,'202012015','段誉15','duanyu15','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(315,'202012016','段誉16','duanyu16','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(316,'202012017','段誉17','duanyu17','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(317,'202012018','段誉18','duanyu18','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(318,'202012019','段誉19','duanyu19','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(319,'202012020','段誉20','duanyu20','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(320,'202012021','段誉21','duanyu21','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(321,'202012022','段誉22','duanyu22','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:33',NULL),(322,'202012023','段誉23','duanyu23','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(323,'202012024','段誉24','duanyu24','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(324,'202012025','段誉25','duanyu25','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(325,'202012026','段誉26','duanyu26','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(326,'202012027','段誉27','duanyu27','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(327,'202012028','段誉28','duanyu28','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(328,'202012029','段誉29','duanyu29','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(329,'202012030','段誉30','duanyu30','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(330,'202012031','段誉31','duanyu31','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(331,'202012032','段誉32','duanyu32','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(332,'202012033','段誉33','duanyu33','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(333,'202012034','段誉34','duanyu34','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(334,'202012035','段誉35','duanyu35','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(335,'202012036','段誉36','duanyu36','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(336,'202012037','段誉37','duanyu37','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(337,'202012038','段誉38','duanyu38','202cb962ac59075b964b07152d234b70',2020,'软件','2班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(346,'201905009','虚竹9','xuzhu9','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(347,'201905010','虚竹10','xuzhu10','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(348,'201905011','虚竹11','xuzhu11','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(349,'201905012','虚竹12','xuzhu12','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(350,'201905013','虚竹13','xuzhu13','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(351,'201905014','虚竹14','xuzhu14','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(352,'201905015','虚竹15','xuzhu15','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(353,'201905016','虚竹16','xuzhu16','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(354,'201905017','虚竹17','xuzhu17','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(355,'201905018','虚竹18','xuzhu18','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(356,'201905019','虚竹19','xuzhu19','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(357,'201905020','虚竹20','xuzhu20','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(358,'201905021','虚竹21','xuzhu21','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(359,'201905022','虚竹22','xuzhu22','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(360,'201905023','虚竹23','xuzhu23','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(361,'201905024','虚竹24','xuzhu24','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(362,'201905025','虚竹25','xuzhu25','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(363,'201905026','虚竹26','xuzhu26','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(364,'201905027','虚竹27','xuzhu27','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(365,'201905028','虚竹28','xuzhu28','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(366,'201905029','虚竹29','xuzhu29','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(367,'201905030','虚竹30','xuzhu30','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(368,'201905031','虚竹31','xuzhu31','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(369,'201905032','虚竹32','xuzhu32','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(370,'201905033','虚竹33','xuzhu33','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(371,'201905034','虚竹34','xuzhu34','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL),(372,'201905035','虚竹35','xuzhu35','202cb962ac59075b964b07152d234b70',2019,'计算机','1班',NULL,NULL,NULL,NULL,'2022-01-19 14:56:34',NULL);
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student_exam`
--

DROP TABLE IF EXISTS `t_student_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_student_exam` (
  `exam_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `exam_group` varchar(32) DEFAULT NULL COMMENT '自定义班级名称：重修班',
  `status` varchar(32) DEFAULT NULL COMMENT '正常（未考试，考试中，已完成），作弊，缺考',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `answer1` text,
  `answer2` text,
  `answer3` text,
  `answer4` text,
  `answer5` text,
  `review4` text,
  `review5` text,
  `score` int(11) DEFAULT NULL,
  `page_path` varchar(128) DEFAULT NULL,
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  `yl3` varchar(32) DEFAULT NULL,
  `yl4` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`exam_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student_exam`
--

LOCK TABLES `t_student_exam` WRITE;
/*!40000 ALTER TABLE `t_student_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_student_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_teacher`
--

DROP TABLE IF EXISTS `t_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tname` varchar(32) DEFAULT NULL,
  `mnemonic_code` varchar(32) DEFAULT NULL,
  `pass` varchar(64) DEFAULT NULL,
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  `yl3` varchar(32) DEFAULT NULL,
  `yl4` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_Key_2` (`tname`),
  UNIQUE KEY `AK_Key_3` (`mnemonic_code`)
) ENGINE=InnoDB AUTO_INCREMENT=354 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_teacher`
--

LOCK TABLES `t_teacher` WRITE;
/*!40000 ALTER TABLE `t_teacher` DISABLE KEYS */;
INSERT INTO `t_teacher` VALUES (1,'张三','zhangsan','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-12 15:20:22','2022-01-27 09:25:09'),(4,'李四','lisi','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 11:21:20','2022-01-27 09:25:09'),(11,'李四1','lisi1','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48','2022-01-27 09:25:09'),(12,'李四2','lisi2','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48','2022-01-27 09:25:09'),(13,'李四3','lisi3','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48','2022-01-27 09:25:09'),(14,'李四4','lisi4','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48','2022-01-27 09:25:09'),(15,'李四5','lisi5','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48','2022-01-27 09:25:09'),(16,'李四6','lisi6','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48','2022-01-27 09:25:09'),(17,'李四7','lisi7','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48','2022-01-27 09:25:09'),(18,'李四8','lisi8','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48','2022-01-27 09:25:09'),(19,'李四9','lisi9','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(20,'李四10','lisi10','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(21,'李四11','lisi11','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(22,'李四12','lisi12','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(23,'李四13','lisi13','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(24,'李四14','lisi14','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(25,'李四15','lisi15','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(26,'李四16','lisi16','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(27,'李四17','lisi17','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(28,'李四18','lisi18','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(29,'李四19','lisi19','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(30,'李四20','lisi20','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(31,'李四21','lisi21','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(32,'李四22','lisi22','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(33,'李四23','lisi23','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(34,'李四24','lisi24','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(35,'李四25','lisi25','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(36,'李四26','lisi26','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(37,'李四27','lisi27','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(38,'李四28','lisi28','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(39,'李四29','lisi29','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(40,'李四30','lisi30','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(41,'李四31','lisi31','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(42,'李四32','lisi32','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(43,'李四33','lisi33','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(44,'李四34','lisi34','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(45,'李四35','lisi35','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(46,'李四36','lisi36','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(47,'李四37','lisi37','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:01:48',NULL),(344,'王五1','wangwu1','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(345,'王五2','wangwu2','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(346,'王五3','wangwu3','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(347,'王五4','wangwu4','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(348,'王五5','wangwu5','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(349,'王五6','wangwu6','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(350,'王五7','wangwu7','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(351,'王五8','wangwu8','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(352,'王五9','wangwu9','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL),(353,'王五10','wangwu10','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,'2022-01-17 16:21:00',NULL);
/*!40000 ALTER TABLE `t_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_template`
--

DROP TABLE IF EXISTS `t_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL COMMENT '静态模板，动态模板',
  `question1` text,
  `question2` text,
  `question3` text,
  `question4` text,
  `question5` text,
  `total_score` int(11) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL COMMENT '正常（私有，公有），丢弃',
  `tid` bigint(20) DEFAULT NULL COMMENT '关联teacher.id',
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  `yl3` varchar(32) DEFAULT NULL,
  `yl4` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_Key_2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_template`
--

LOCK TABLES `t_template` WRITE;
/*!40000 ALTER TABLE `t_template` DISABLE KEYS */;
INSERT INTO `t_template` VALUES (3,'java-期末考试模板','静态模板','2}-|-{1}-|-{2}-|-{3}-|-{4}-|-{5}-|-{6','2}-|-{7}-|-{8}-|-{9','2}-|-{10}-|-{11}-|-{12}-|-{20','2}-|-{13}-|-{14}-|-{15','5}-|-{16}-|-{17}-|-{18}-|-{19',58,'丢弃',1,'java程序设计',NULL,NULL,NULL,'2022-01-27 16:35:57','2022-02-07 14:04:30'),(5,'1111','动态模板','2}-|-{2}-|-{2}-|-{2','2}-|-{2}-|-{2}-|-{2','2}-|-{2}-|-{2}-|-{2','2}-|-{2}-|-{2}-|-{2','2}-|-{2}-|-{2}-|-{2',60,'公有',1,'C语言程序设计',NULL,NULL,NULL,'2022-02-07 11:11:19','2022-02-07 17:20:35'),(6,'222','动态模板','3}-|-{3}-|-{3}-|-{3','3}-|-{3}-|-{3}-|-{3','3}-|-{3}-|-{3}-|-{3','3}-|-{3}-|-{3}-|-{3','3}-|-{3}-|-{3}-|-{3',135,'私有',4,'汇编语言',NULL,NULL,NULL,'2022-02-07 14:07:37',NULL),(7,'123','静态模板','2}-|-{21}-|-{22}-|-{23}-|-{24}-|-{25}-|-{26','2}-|-{27}-|-{28}-|-{29','2}-|-{30}-|-{31}-|-{32','2}-|-{33}-|-{34}-|-{35','5}-|-{36}-|-{37}-|-{38}-|-{39',56,'私有',1,'C语言程序设计',NULL,NULL,NULL,'2022-02-07 14:35:17',NULL),(8,'4444','静态模板','4}-|-{78','4','4}-|-{8}-|-{79','4','6',12,'私有',1,'C语言程序设计',NULL,NULL,NULL,'2022-02-07 20:54:34','2022-02-07 22:32:22'),(9,'555','动态模板','2}-|-{2}-|-{2}-|-{2','2}-|-{2}-|-{2}-|-{2','2}-|-{2}-|-{2}-|-{2','2}-|-{2}-|-{2}-|-{2','2}-|-{2}-|-{2}-|-{2',60,'私有',1,'C语言程序设计',NULL,NULL,NULL,'2022-02-08 09:43:43',NULL);
/*!40000 ALTER TABLE `t_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_template_teacher`
--

DROP TABLE IF EXISTS `t_template_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_template_teacher` (
  `template_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`template_id`,`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_template_teacher`
--

LOCK TABLES `t_template_teacher` WRITE;
/*!40000 ALTER TABLE `t_template_teacher` DISABLE KEYS */;
INSERT INTO `t_template_teacher` VALUES (1,4),(5,4),(6,1),(7,4),(7,11);
/*!40000 ALTER TABLE `t_template_teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-08 10:11:08
