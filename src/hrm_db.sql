/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : hrm_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-21 19:38:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept_inf
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE dept_inf (
  id varchar2(32) DEFAULT sys_guid() NOT NULL,
  name varchar2(50) NOT NULL,
  remark varchar(300) DEFAULT NULL,
  PRIMARY KEY (id)
)
-- ----------------------------
-- Table structure for document_inf
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `filename` varchar(300) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `document_inf_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inf` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document_inf
-- ----------------------------

-- ----------------------------
-- Table structure for employee_inf
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE employee_inf (
  id varchar2(32) DEFAULT sys_guid() NOT NULL,
  dept_id varchar2(32) NOT NULL,
  job_id varchar2(32) NOT NULL,
  name varchar2(20) NOT NULL,
  card_id varchar2(20) NOT NULL,
  address varchar2(50) DEFAULT NULL,
  post_code varchar2(50) DEFAULT NULL,
  tel varchar2(16) DEFAULT NULL,
  phone varchar2(11) NOT NULL,
  qq_num varchar2(10) DEFAULT NULL,
  email varchar2(50) DEFAULT NULL,
  sex number(11) DEFAULT 1,
  party varchar2(10) DEFAULT NULL,
  birthday date DEFAULT NULL,
  race varchar2(100) DEFAULT NULL,
  education varchar2(10) DEFAULT NULL,
  speciality varchar2(20) DEFAULT NULL,
  hobby varchar2(100) DEFAULT NULL,
  remark varchar2(500) DEFAULT NULL,
  create_date varchar2(20) DEFAULT TO_CHAR(sysdate,'yyyy-mm-dd hh24:mi:ss') NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_employee_dept FOREIGN KEY (dept_id) REFERENCES dept_inf (id),
  CONSTRAINT fk_employee_job FOREIGN KEY (job_id) REFERENCES job_inf (id)
)

-- ----------------------------
-- Table structure for job_inf
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE job_inf (
  id varchar2(32) DEFAULT sys_guid() NOT NULL,
  name varchar2(50) DEFAULT NULL,
  remark varchar2(300) DEFAULT NULL,
  PRIMARY KEY (id)
)

-- ----------------------------
-- Table structure for notice_inf
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE notice_inf (
  id varchar2(32) DEFAULT sys_guid() NOT NULL,
  title varchar2(50) NOT NULL,
  content clob NOT NULL,
  create_date varchar2(20) DEFAULT TO_CHAR(sysdate,'yyyy-mm-dd hh24:mi:ss') NOT NULL,
  user_id varchar2(32) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_notice_inf FOREIGN KEY (user_id) REFERENCES user_inf (id)
)

-- ----------------------------
-- Records of notice_inf
-- ----------------------------

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE user_inf (
  id varchar2(32) DEFAULT sys_guid() NOT NULL,
  loginname varchar2(20) NOT NULL,
  password varchar2(16) NOT NULL,
  status number(11) DEFAULT '1' NOT NULL,
  create_date varchar2(20) DEFAULT TO_CHAR(sysdate,'yyyy-mm-dd hh24:mi:ss') NOT NULL,
  username varchar2(20) DEFAULT NULL,
  PRIMARY KEY (id)
)

