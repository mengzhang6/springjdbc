/*
Navicat MySQL Data Transfer

Source Server         : MariaDB
Source Server Version : 50505
Source Host           : localhost:3307
Source Database       : meng

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-03-28 11:30:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('100', '李明', '2017-03-27', '57.2', '2017-03-27 11:21:37');
INSERT INTO `user` VALUES ('102', '李哈哈', '1992-04-05', '49.8', '2017-03-27 11:25:06');
INSERT INTO `user` VALUES ('103', '李笑笑', '1993-04-05', '47.8', '2017-03-27 11:54:22');
INSERT INTO `user` VALUES ('104', '王晓敏', '1993-04-05', '47.8', '2017-03-28 11:27:39');
