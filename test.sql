/*
Navicat MySQL Data Transfer

Source Server         : sunyue
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2021-02-02 21:50:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user` (
  `id` varchar(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(3) NOT NULL,
  `bir` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES ('07222415-adc0-4117-8f85-4a19ea0633d2', '孙越', '22', '2020-12-01');
INSERT INTO `test_user` VALUES ('5d7af197-a25e-418a-a087-7717bd5d4483', '孙跃', '24', '2020-12-01');
INSERT INTO `test_user` VALUES ('695601aa-4ad4-449f-92a3-bbae5cc432b0', '孙越', '22', '2020-11-30');
INSERT INTO `test_user` VALUES ('8c2e2f28-d6f1-48e6-930b-0cf431667089', '孙跃', '24', '2020-11-30');
INSERT INTO `test_user` VALUES ('a2bf3c23-2024-43f1-a328-b09331606c56', '孙跃', '24', '2020-11-30');
