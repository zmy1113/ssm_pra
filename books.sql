/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.17-log : Database - atguigu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`atguigu` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `atguigu`;

/*Table structure for table `t_book` */

DROP TABLE IF EXISTS `t_book`;

CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `sales` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `img_path` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `t_book` */

insert  into `t_book`(`id`,`name`,`author`,`price`,`sales`,`stock`,`img_path`) values (1,'东邪西毒','黄皇后','30.00',2001,11,'static/img/default.jpg'),(2,'数据结构与算法','严敏君','78.50',10,9,'static/img/default.jpg'),(3,'怎样拐跑别人的媳妇','龙伍','68.00',100004,47,'static/img/default.jpg'),(4,'木虚肉盖饭','小胖','16.00',1004,46,'static/img/default.jpg'),(5,'C++编程思想','刚哥','45.50',15,94,'static/img/default.jpg'),(6,'蛋炒饭','周星星','9.90',12,53,'static/img/default.jpg'),(7,'赌神','龙伍','66.50',126,534,'static/img/default.jpg'),(8,'Java编程思想','阳哥','99.50',47,36,'static/img/default.jpg'),(9,'JavaScript从入门到精通','婷姐','9.90',85,95,'static/img/default.jpg'),(10,'cocos2d-x游戏编程入门','国哥','49.00',52,62,'static/img/default.jpg'),(11,'C语言程序设计','谭浩强','28.00',52,74,'static/img/default.jpg'),(12,'Lua语言程序设计','雷丰阳','51.50',48,82,'static/img/default.jpg'),(13,'西游记','罗贯中','12.00',19,9999,'static/img/default.jpg'),(14,'水浒传','华仔','33.05',22,88,'static/img/default.jpg'),(15,'操作系统原理','刘优','133.05',122,188,'static/img/default.jpg'),(16,'数据结构 java版','封大神','173.15',21,81,'static/img/default.jpg'),(17,'UNIX高级环境编程','乐天','99.15',210,810,'static/img/default.jpg'),(18,'javaScript高级编程','国哥','69.15',210,810,'static/img/default.jpg'),(19,'大话设计模式','国哥','89.15',20,10,'static/img/default.jpg'),(30,'东邪西毒','黄皇后','30.00',2000,1000,'static/img/default.jpg');

/*Table structure for table `t_cart` */

DROP TABLE IF EXISTS `t_cart`;

CREATE TABLE `t_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `total_price` decimal(10,0) DEFAULT NULL,
  `userId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_cart` */

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `orderId` varchar(200) NOT NULL,
  `createTime` datetime NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`orderId`),
  KEY `user_id` (`userId`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`orderId`,`createTime`,`price`,`status`,`userId`) values ('16353293404431','2021-10-27 18:09:00','79.00',1,1),('16353293414511','2021-10-27 18:09:01','16.00',1,1);

/*Table structure for table `t_order_item` */

DROP TABLE IF EXISTS `t_order_item`;

CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `totalPrice` decimal(11,2) DEFAULT NULL,
  `count` int(11) NOT NULL,
  `orderId` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `t_order` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `t_order_item` */

insert  into `t_order_item`(`id`,`name`,`price`,`totalPrice`,`count`,`orderId`) values (23,'数据结构与算法','79.00','79.00',1,'16353293404431'),(24,'木虚肉盖饭','16.00','16.00',1,'16353293414511');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`email`) values (1,'admin','admin','admin@atguigu.com'),(2,'wzg168','wzg168','wzg168@atguigu.com'),(3,'zmy','0530','zmy@qq.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
