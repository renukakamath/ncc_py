/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - ncccadet_py
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ncccadet_py` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ncccadet_py`;

/*Table structure for table `academic` */

DROP TABLE IF EXISTS `academic`;

CREATE TABLE `academic` (
  `academic_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `academic` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`academic_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `academic` */

insert  into `academic`(`academic_id`,`student_id`,`academic`,`details`,`phone`,`email`) values 
(1,1,'asdfgj','HGKJGJ','1234567890','teacher@gmail.com'),
(2,1,'asdfgj','HGKJGJ','1234567890','teacher@gmail.com');

/*Table structure for table `activities` */

DROP TABLE IF EXISTS `activities`;

CREATE TABLE `activities` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `head_id` int(11) DEFAULT NULL,
  `Activites` varchar(200) DEFAULT NULL,
  `details` varchar(500) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `activities` */

insert  into `activities`(`activity_id`,`head_id`,`Activites`,`details`,`date`) values 
(1,3,'fij','fii','2022-12-23'),
(2,3,'jdje','bdjje','2022-12-23'),
(3,3,'bsh','bdhd','2022-12-23'),
(4,5,'fikk','dhi','2022-12-24');

/*Table structure for table `attendance` */

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `head_id` int(11) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`attendance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `attendance` */

insert  into `attendance`(`attendance_id`,`student_id`,`head_id`,`date`,`status`) values 
(1,1,8,'2022-12-23','present'),
(2,1,3,'2022-12-23','absent'),
(3,1,3,'2022-12-24','absent'),
(4,1,3,'2022-12-24','present'),
(5,3,5,'2022-12-24','absent');

/*Table structure for table `camp_details` */

DROP TABLE IF EXISTS `camp_details`;

CREATE TABLE `camp_details` (
  `camp_id` int(11) NOT NULL AUTO_INCREMENT,
  `head_id` int(11) DEFAULT NULL,
  `camp` varchar(200) DEFAULT NULL,
  `details` varchar(200) DEFAULT NULL,
  `place` varchar(200) DEFAULT NULL,
  `fromdate` varchar(200) DEFAULT NULL,
  `todate` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`camp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `camp_details` */

insert  into `camp_details`(`camp_id`,`head_id`,`camp`,`details`,`place`,`fromdate`,`todate`) values 
(1,3,'hshhs','hhsh','bshs','23/12/2022','23/12/2022'),
(2,3,'hshhs','hhsh','bshs','23/12/2022','23/12/2022'),
(3,3,'hshhs','hhsh','bshs','23/12/2022','23/12/2022'),
(4,3,'ghj','ghhj','cghkk','24/12/2022','24/12/2022'),
(5,3,'ghh','ghikgyu','fhjo','24/12/2022','24/12/2022'),
(6,3,'vggg','bshsh','bshsh','24/12/2022','31-12-2022'),
(7,3,'fjj','djj','djj','25-1-1900','29-12-2022'),
(8,3,'hhh','ghy','gjj','25-1-1900','29-12-2022'),
(9,5,'fjk','dhjj','dhjk','27-1-1900','31-12-2022');

/*Table structure for table `expences` */

DROP TABLE IF EXISTS `expences`;

CREATE TABLE `expences` (
  `expence_id` int(11) NOT NULL AUTO_INCREMENT,
  `head_id` int(11) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `details` varchar(200) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`expence_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `expences` */

insert  into `expences`(`expence_id`,`head_id`,`title`,`details`,`date`) values 
(1,3,'ha','hdjd','2022-12-24'),
(2,3,'ggjk','hjk','2022-12-24'),
(3,5,'gjj','fhj','2022-12-24');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `user_type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`user_type`) values 
(1,'admin','admin','admin'),
(7,'staff','staff','staff'),
(9,'staff1','staff12','staff'),
(11,'head','head','ncchead');

/*Table structure for table `ncc_head` */

DROP TABLE IF EXISTS `ncc_head`;

CREATE TABLE `ncc_head` (
  `head_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(200) DEFAULT NULL,
  `last_name` varchar(200) DEFAULT NULL,
  `place` varchar(200) DEFAULT NULL,
  `phone` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`head_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ncc_head` */

insert  into `ncc_head`(`head_id`,`login_id`,`first_name`,`last_name`,`place`,`phone`,`email`) values 
(5,11,'user','qwerty','ernakulam1','1234567890','teacher1@gmail.com');

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `notification` */

insert  into `notification`(`notification_id`,`title`,`description`,`date`) values 
(1,'notices','HGKJGJ','2022-12-22');

/*Table structure for table `plan` */

DROP TABLE IF EXISTS `plan`;

CREATE TABLE `plan` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `plan` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `planed_for` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `plan` */

insert  into `plan`(`plan_id`,`plan`,`details`,`planed_for`) values 
(1,'ha','hsuh','jrurh'),
(2,'ha','hsuh','jrurh'),
(3,'bjj','gj','bkk'),
(4,'asdd','gdjnjj','gdjj'),
(5,'sujjj','fzh','fhj'),
(6,'plan','detail','fjj');

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `staff` */

insert  into `staff`(`staff_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`,`designation`) values 
(3,7,'staff1','staff1','ernakulam1','1367890092','staff1@gmail.com','rfvbkm1 ckhjkl'),
(4,9,'staff','staff','jkheqwkj','1234567888','staff1@gmail.com','rfvbkm ckhjkl');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `head_id` int(11) DEFAULT NULL,
  `first_name` varchar(200) DEFAULT NULL,
  `last_name` varchar(200) DEFAULT NULL,
  `place` varchar(200) DEFAULT NULL,
  `phone` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `student` */

insert  into `student`(`student_id`,`head_id`,`first_name`,`last_name`,`place`,`phone`,`email`) values 
(1,3,'student1','fsabfhja1','ernakulam1','1234567891','rensj1@gmail.com'),
(2,4,'stud','dedr','dfefw','1234545','dst'),
(3,5,'jdkjs','bsjsj','bsjsj','1234567890','rebussa@gmail.com'),
(4,5,'jdkjs','bsjsj','bsjsj','1234567890','rebussa@gmail.com'),
(5,5,'jdkjs','bsjsj','bsjsj','1234567890','rebussa@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
