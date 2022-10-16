/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.25 : Database - exam_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`exam_system` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `exam_system`;

/*Table structure for table `correct_info` */

CREATE TABLE `correct_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `test_group_id` bigint unsigned NOT NULL COMMENT '考试场次id',
  `student_id` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生id',
  `score` tinyint unsigned DEFAULT NULL COMMENT '单题成绩',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `is_new_record` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否为新纪录',
  `create_by` varchar(15) NOT NULL COMMENT '创建者',
  `update_by` varchar(15) NOT NULL COMMENT '修改者',
  `answer_picture` varchar(100) NOT NULL COMMENT '答案图片的url',
  `correct_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否批改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

/*Data for the table `correct_info` */

insert  into `correct_info`(`id`,`test_group_id`,`student_id`,`score`,`create_date`,`update_date`,`del_flag`,`is_new_record`,`create_by`,`update_by`,`answer_picture`,`correct_flag`) values 
(1,1,'10001',85,'2022-10-16 18:38:30','2022-10-16 21:47:43',0,0,'10001','10000','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/6ba0b018cc894fca9c0aa6cb9d673695.png',1),
(2,2,'10002',0,'2022-10-16 20:41:21','2022-10-16 20:41:21',0,0,'10002','10002','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/9b58040098d04d8b8bfae04b59a1ac81.png',0);

/*Table structure for table `question_group` */

CREATE TABLE `question_group` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `paper_id` bigint unsigned NOT NULL COMMENT '试卷id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `create_by` varchar(15) NOT NULL COMMENT '创建者id',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `update_by` varchar(15) NOT NULL COMMENT '更新者id',
  `question_id` bigint unsigned NOT NULL COMMENT '题目id',
  `score` tinyint unsigned NOT NULL COMMENT '该题在这份试卷中的分数',
  `is_new_record` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否为新纪录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

/*Data for the table `question_group` */

insert  into `question_group`(`id`,`paper_id`,`create_date`,`update_date`,`create_by`,`del_flag`,`update_by`,`question_id`,`score`,`is_new_record`) values 
(1,1,'2022-10-12 20:11:59','2022-10-12 20:11:59','10000',0,'10000',1,10,1),
(2,1,'2022-10-12 20:11:59','2022-10-12 20:11:59','10000',0,'10000',2,10,1),
(5,1,'2022-10-13 17:31:09','2022-10-13 17:31:09','10000',0,'10000',3,80,1),
(6,1,'2022-10-14 16:22:49','2022-10-14 16:47:43','10000',0,'10000',4,20,0),
(7,1,'2022-10-14 16:22:49','2022-10-14 16:47:43','10000',0,'10000',5,30,0),
(8,1,'2022-10-14 16:22:49','2022-10-14 16:47:43','10000',0,'10000',6,40,0);

/*Table structure for table `question_info` */

CREATE TABLE `question_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '问题id',
  `create_by` varchar(64) NOT NULL COMMENT '创建人id',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题描述',
  `picture` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '相关图片,可为空',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `remarks` varchar(255) NOT NULL COMMENT '备注',
  `del_flag` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `is_new_record` tinyint NOT NULL DEFAULT '0' COMMENT '是否为新记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

/*Data for the table `question_info` */

insert  into `question_info`(`id`,`create_by`,`description`,`picture`,`create_date`,`update_date`,`remarks`,`del_flag`,`update_by`,`is_new_record`) values 
(1,'10000','1+1等于几','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/ae8fcd71b407428cb3102cb9cb93ecfc.png','2022-10-03 20:21:36','2022-10-03 20:21:40','史上最难题目',0,'10000',1),
(2,'10000','1+666=?','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/34f330e1ff854b0ab9bc937692c7481f.png','2022-10-07 17:00:18','2022-10-07 20:56:15','2这个更难',0,'10000',0),
(3,'10000','2+6=？','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/adf13ad5b93c409886cc9c172d16c0d7.png','2022-10-07 19:13:55','2022-10-07 19:13:55','无',0,'10000',1),
(4,'10000','2+8=？','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/c37bb154c0a741b7b69a79860b1c9db4.png','2022-10-07 19:16:24','2022-10-07 19:16:24','无',0,'10000',1),
(5,'10000','啦啦啦哈哈哈','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/cb7070ea5036467d959e5ea63b57d23f.png','2022-10-09 12:26:43','2022-10-09 12:26:43','很帅',0,'10000',0),
(6,'10000','哈哈哈lalala','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/e1388960b0604303b72770e61f66c253.png','2022-10-09 12:46:46','2022-10-09 12:46:46','很帅',0,'10000',1),
(7,'10000','别出问题呀','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/c8b8650dddf34585bcdf6102d5300cca.png','2022-10-11 19:17:28','2022-10-11 19:28:29','求求了',0,'10000',0),
(8,'10000','aaaaa我希望不要出问题','https://bucketofpicture.oss-cn-hangzhou.aliyuncs.com/f804fccfd9434e4791f14d6e2dc495ba.png','2022-10-11 17:54:24','2022-10-11 17:54:24','球球了',0,'1',1);

/*Table structure for table `test_group` */

CREATE TABLE `test_group` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `test_id` bigint unsigned NOT NULL COMMENT '考试场次id',
  `student_id` varchar(15) NOT NULL COMMENT '学生id',
  `create_by` varchar(15) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(15) NOT NULL COMMENT '修改者',
  `del_flag` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记',
  `is_new_record` tinyint NOT NULL DEFAULT '1' COMMENT '是否为新纪录',
  `status` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

/*Data for the table `test_group` */

insert  into `test_group`(`id`,`test_id`,`student_id`,`create_by`,`create_date`,`update_date`,`update_by`,`del_flag`,`is_new_record`,`status`) values 
(1,2,'10001','10000','2022-10-15 15:31:23','2022-10-16 21:47:43','10000',0,0,'end'),
(2,2,'10002','10000','2022-10-15 15:31:24','2022-10-15 15:31:24','10000',0,0,'waitCorrect');

/*Table structure for table `test_info` */

CREATE TABLE `test_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '考试id',
  `paper_id` bigint unsigned NOT NULL COMMENT '用的试卷id',
  `test_name` varchar(10) NOT NULL COMMENT '考试名称',
  `time_limit` int NOT NULL COMMENT '考试时间限制/min',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `create_by` varchar(15) NOT NULL COMMENT '创建人id',
  `update_by` varchar(15) NOT NULL COMMENT '更新人id',
  `is_new_record` tinyint NOT NULL DEFAULT '1' COMMENT '是否为新记录',
  `del_flag` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

/*Data for the table `test_info` */

insert  into `test_info`(`id`,`paper_id`,`test_name`,`time_limit`,`create_date`,`update_date`,`create_by`,`update_by`,`is_new_record`,`del_flag`) values 
(1,1,'大物',60,'2022-10-08 18:41:39','2022-10-08 18:41:39','10000','10000',0,0),
(2,1,'大物期中考',2,'2022-10-15 11:30:18','2022-10-15 11:30:18','10000','10000',1,0);

/*Table structure for table `test_paper_info` */

CREATE TABLE `test_paper_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '试卷id',
  `create_by` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `paper_name` varchar(10) NOT NULL COMMENT '试卷名称',
  `del_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `is_new_record` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否是新纪录',
  `update_by` varchar(15) NOT NULL COMMENT '更新者id',
  `remarks` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `test_paper_info` */

insert  into `test_paper_info`(`id`,`create_by`,`create_date`,`update_date`,`paper_name`,`del_flag`,`is_new_record`,`update_by`,`remarks`) values 
(1,'10000','2022-10-08 12:18:48','2022-10-08 12:18:48','大物A第三章',0,1,'10000','小测卷'),
(2,'10000','2022-10-09 10:59:10','2022-10-09 10:59:10','垃圾试卷',0,1,'10000','真的垃圾'),
(3,'10000','2022-10-11 20:22:42','2022-10-11 20:22:42','高考模拟试卷',0,0,'10000','小测');

/*Table structure for table `user_info` */

CREATE TABLE `user_info` (
  `id` varchar(64) NOT NULL COMMENT '学号/教职工号',
  `role` tinyint unsigned NOT NULL COMMENT '1为学生/2为教师',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改日期',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `remarks` varchar(255) NOT NULL COMMENT '备注信息',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `is_new_record` tinyint NOT NULL DEFAULT '0' COMMENT '是否为新记录',
  `name` varchar(10) NOT NULL COMMENT '名字',
  `mail` varchar(30) NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`role`,`create_date`,`update_date`,`password`,`create_by`,`remarks`,`del_flag`,`update_by`,`is_new_record`,`name`,`mail`) values 
('10000',2,'2022-09-29 23:36:04','2022-09-29 23:36:08','$2a$10$xp7kjC8JAJKN8fl6B1UTOePjCVCHyvkLsYVQK6tQO1FL/AY3kl6Se','xxxx','有毛病','0','xxxx',0,'张三','2867628793@qq.com'),
('10001',1,'2022-10-04 19:21:44','2022-10-04 19:21:47','$2a$10$xp7kjC8JAJKN8fl6B1UTOePjCVCHyvkLsYVQK6tQO1FL/AY3kl6Se','xxxx','学生','0','xxxx',0,'李四','2867628793@qq.com'),
('10002',1,'2022-10-15 14:33:02','2022-10-15 14:40:42','$2a$10$xp7kjC8JAJKN8fl6B1UTOePjCVCHyvkLsYVQK6tQO1FL/AY3kl6Se','xxxx','学生2','0','10002',0,'王五','2867628793@qq.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
