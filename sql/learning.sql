/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50153
Source Host           : 127.0.0.1:3306
Source Database       : learning

Target Server Type    : MYSQL
Target Server Version : 50153
File Encoding         : 65001

Date: 2015-11-06 17:07:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(125) NOT NULL,
  `content` blob,
  `description` varchar(256) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test');
INSERT INTO `user` VALUES ('2', 'test');
INSERT INTO `user` VALUES ('3', 'dadsadas');
INSERT INTO `user` VALUES ('4', 'adasdasd');
INSERT INTO `user` VALUES ('5', 'sdfsdf');
