/*
SQLyog v10.2 
MySQL - 5.5.27 : Database - springboot-vue
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboot-vue` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springboot-vue`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `isbn` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图书编号',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `author` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者',
  `publisher` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '出版社',
  `create_time` date DEFAULT NULL COMMENT '出版时间',
  `status` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '0：未归还 1：已归还',
  `borrownum` int(11) NOT NULL COMMENT '此书被借阅次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `book` */

insert  into `book`(`id`,`isbn`,`name`,`price`,`author`,`publisher`,`create_time`,`status`,`borrownum`) values (9,'233333','蒙古上单的传奇','233.00','陈睿','哔哩哔哩大学','2014-05-14','1',7),(10,'2312315132131','五万个为什么',NULL,NULL,NULL,NULL,'1',3),(11,'25213121232','一万个为什么',NULL,NULL,NULL,NULL,'1',5),(12,'3213123123','操作系统',NULL,NULL,NULL,NULL,'0',8),(13,'345621212321','伊索寓言',NULL,NULL,NULL,NULL,'0',9),(15,'54112312321','格林童话',NULL,NULL,NULL,NULL,'1',1),(16,'114514','下北泽美食','114.00','先辈','霓虹','2024-05-14','1',0);

/*Table structure for table `bookwithuser` */

DROP TABLE IF EXISTS `bookwithuser`;

CREATE TABLE `bookwithuser` (
  `id` bigint(20) NOT NULL COMMENT 'reader_id',
  `isbn` varchar(127) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图书编号',
  `book_name` varchar(127) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图书名',
  `nick_name` varchar(127) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '读者姓名',
  `lendtime` datetime DEFAULT NULL COMMENT '借阅时间',
  `deadtime` datetime DEFAULT NULL COMMENT '应归还时间',
  `prolong` int(11) DEFAULT NULL COMMENT '续借次数',
  PRIMARY KEY (`book_name`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `bookwithuser` */

insert  into `bookwithuser`(`id`,`isbn`,`book_name`,`nick_name`,`lendtime`,`deadtime`,`prolong`) values (18,'345621212321','伊索寓言','123456','2021-12-22 17:30:48','2022-02-20 17:30:48',0),(18,'3213123123','操作系统','123456','2021-10-12 17:30:42','2021-12-14 17:30:42',1);

/*Table structure for table `lend_record` */

DROP TABLE IF EXISTS `lend_record`;

CREATE TABLE `lend_record` (
  `reader_id` bigint(20) NOT NULL COMMENT 'reader_id',
  `isbn` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图书编号',
  `bookname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图书名',
  `lend_time` datetime DEFAULT NULL COMMENT '借书日期',
  `return_time` datetime DEFAULT NULL COMMENT '还书日期',
  `status` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '0：未归还 1：已归还',
  `borrownum` int(11) NOT NULL COMMENT '此书被借阅次数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `lend_record` */

insert  into `lend_record`(`reader_id`,`isbn`,`bookname`,`lend_time`,`return_time`,`status`,`borrownum`) values (13,'92392321222','算法笔记','2021-12-18 10:57:02','2021-12-18 11:03:54','1',2),(13,'465132123123','狂人日记','2021-12-18 10:59:21','2021-12-18 11:22:51','1',2),(13,'54156461231','十万个为什么','2021-12-18 10:59:21','2021-12-18 11:04:38','1',4),(13,'54156461231','十万个为什么','2021-12-18 11:04:40','2021-12-18 11:05:22','1',5),(13,'92392321222','算法笔记','2021-12-18 11:04:51','2021-12-18 11:05:24','1',3),(13,'54156461231','十万个为什么','2021-12-18 11:05:27','2021-12-18 11:10:19','1',6),(13,'92392321222','算法笔记','2021-12-18 11:05:29','2021-12-18 11:06:09','1',4),(13,'92392321222','算法笔记','2021-12-18 11:06:11','2021-12-18 11:06:38','1',5),(13,'92392321222','算法笔记','2021-12-18 11:06:42','2021-12-18 11:06:52','1',6),(13,'92392321222','算法笔记','2021-12-18 11:06:53','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:54','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:54','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:55','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:55','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:55','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:55','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:55','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:55','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:56','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:56','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:56','2021-12-18 11:10:20','1',7),(13,'92392321222','算法笔记','2021-12-18 11:06:56','2021-12-18 11:10:20','1',7),(14,'12341541321','十万个为什么','2021-12-18 16:27:35','2021-12-21 20:18:58','1',1),(14,'2312315132131','五万个为什么','2021-12-18 16:27:36','2021-12-21 20:18:59','1',1),(14,'25213121232','一万个为什么','2021-12-18 16:27:38','2021-12-18 16:29:22','1',1),(14,'3213123123','操作系统','2021-12-18 16:27:40','2021-12-18 16:29:15','1',1),(14,'3213123123','操作系统','2021-12-18 16:29:24','2021-12-21 20:19:01','1',2),(14,'25213121232','一万个为什么','2021-12-19 11:37:54','2021-12-21 20:19:00','1',2),(14,'345621212321','伊索寓言','2021-12-19 11:37:58','2021-12-19 11:38:42','1',1),(15,'345621212321','伊索寓言','2021-12-19 13:13:10','2021-12-19 13:13:26','1',2),(14,'12341541321','十万个为什么','2021-12-21 20:19:09','2021-12-22 15:45:35','1',2),(14,'25213121232','一万个为什么','2021-12-21 20:19:11','2021-12-22 15:45:32','1',3),(14,'2312315132131','五万个为什么','2021-12-21 20:20:00','2021-12-22 15:45:34','1',2),(14,'3213123123','操作系统','2021-12-21 20:20:01','2021-12-22 15:45:27','1',3),(14,'345621212321','伊索寓言','2021-12-21 20:20:02','2021-12-21 20:20:11','1',3),(14,'3213123123','操作系统','2021-12-22 15:45:37','2021-12-22 15:45:39','1',4),(14,'3213123123','操作系统','2021-12-22 15:46:15','2021-12-22 15:51:05','1',5),(14,'3213123123','操作系统','2021-12-22 15:51:12','2021-12-22 15:51:15','1',6),(14,'3213123123','操作系统','2021-12-22 15:51:16','2021-12-22 16:48:09','1',7),(14,'25213121232','一万个为什么','2021-12-22 16:48:10','2021-12-22 16:48:13','1',4),(14,'345621212321','伊索寓言','2021-12-22 16:48:19','2021-12-22 17:30:46','1',4),(14,'3213123123','操作系统','2021-12-22 17:30:42',NULL,'0',8),(14,'345621212321','伊索寓言','2021-12-22 17:30:48',NULL,'0',9),(16,'12341541321','十万个为什么','2021-12-23 14:55:34','2022-04-18 16:36:54','1',3),(16,'2312315132131','五万个为什么','2021-12-23 14:59:03','2022-04-18 16:36:55','1',3),(16,'25213121232','一万个为什么','2021-12-23 14:59:05','2022-04-18 16:36:56','1',5),(17,'54112312321','格林童话','2022-01-18 20:02:01','2022-01-18 20:02:51','1',1),(16,'12341541321','十万个为什么','2022-04-18 16:37:01','2022-04-18 16:37:28','1',4),(16,'12341541321','十万个为什么','2022-04-18 16:37:32','2022-04-18 16:43:30','1',5),(16,'12341541321','十万个为什么','2022-04-18 16:43:39','2022-04-18 16:43:43','1',6),(16,'12341541321','十万个为什么','2022-04-18 16:44:14','2022-04-18 16:44:18','1',7);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话号码',
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `role` int(11) NOT NULL COMMENT '角色、1：管理员 2：普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`nick_name`,`phone`,`sex`,`address`,`role`) values (18,'root','123321','cbg','13581294439','女','湖北工业大学',1),(19,'cbg','123321','123456','13581294439','女','湖北工业大学',1),(20,'yii','123321','yii','1111111','女','湖北工业大学',1),(22,'ggz','123321',NULL,NULL,NULL,NULL,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
