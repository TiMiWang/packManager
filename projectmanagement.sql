/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : projectmanagement

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-12-22 09:17:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `log_info`
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT '',
  `user_name` varchar(20) NOT NULL DEFAULT '',
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pack_mode_structure` varchar(11) DEFAULT '' COMMENT '架构',
  `indate` int(11) DEFAULT '90' COMMENT '有效期',
  `version_info` varchar(20) DEFAULT '' COMMENT '试用、正式',
  `svn_net_path` varchar(100) DEFAULT '' COMMENT 'svn路径',
  `project_name` varchar(50) DEFAULT '' COMMENT '所属项目名',
  `product_name` varchar(50) DEFAULT '' COMMENT '所属产品名',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `date_time_str` varchar(50) DEFAULT '' COMMENT '时间的字符串形式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_info
-- ----------------------------
INSERT INTO `log_info` VALUES ('21', '进行打安装包操作', 'admin', '2017-12-20 17:54:18', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', '', '', '', '2017-12-20 17:54:18');
INSERT INTO `log_info` VALUES ('22', '进行打安装包操作', 'admin', '2017-12-21 10:13:56', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', '', '', '', '2017-12-21 10:13:56');
INSERT INTO `log_info` VALUES ('23', '进行打安装包操作', 'admin', '2017-12-21 10:31:50', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', '', '', '', '2017-12-21 10:31:50');
INSERT INTO `log_info` VALUES ('24', '进行打安装包操作', 'admin', '2017-12-21 11:56:15', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'tete', 'tete', 'tete', '2017-12-21 11:56:15');
INSERT INTO `log_info` VALUES ('25', '进行打安装包操作', 'admin', '2017-12-21 14:03:13', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'tete', 'tete', 'tete', '2017-12-21 14:03:13');
INSERT INTO `log_info` VALUES ('26', '进行打安装包操作', 'admin', '2017-12-21 15:30:44', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test', 'test', 'test', '2017-12-21 15:30:44');
INSERT INTO `log_info` VALUES ('37', '进行打安装包操作', 'admin', '2017-12-21 16:55:01', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test1', 'test1', 'test1', '2017-12-21 16:55:01');
INSERT INTO `log_info` VALUES ('38', '进行打安装包操作', 'admin', '2017-12-21 17:27:03', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test1', 'test1', 'test1', '2017-12-21 17:27:03');
INSERT INTO `log_info` VALUES ('39', '进行打安装包操作', 'admin', '2017-12-21 17:40:19', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test1', 'test1', 'test1', '2017-12-21 17:40:19');
INSERT INTO `log_info` VALUES ('40', '进行打安装包操作', 'admin', '2017-12-21 17:50:32', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test1', 'test1', 'test1', '2017-12-21 17:50:32');
INSERT INTO `log_info` VALUES ('41', '进行打安装包操作', 'admin', '2017-12-21 17:56:11', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test1', 'test1', 'test1', '2017-12-21 17:56:11');
INSERT INTO `log_info` VALUES ('42', '下载了安装包文件', 'admin', '2017-12-21 18:17:17', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test1', 'test1', 'test1', '2017-12-21 18:17:17');

-- ----------------------------
-- Table structure for `pack_mode`
-- ----------------------------
DROP TABLE IF EXISTS `pack_mode`;
CREATE TABLE `pack_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_local_path` varchar(200) DEFAULT NULL,
  `is_svn_check` int(11) DEFAULT '0' COMMENT '0:不是、1：是',
  `svn_net_path` varchar(100) NOT NULL DEFAULT '',
  `version_info` int(11) DEFAULT '0' COMMENT '版本信息 0:试用、1：正式',
  `indate` int(11) DEFAULT '0' COMMENT '有效期',
  `system_version` varchar(20) NOT NULL COMMENT '系统版本',
  `structure_type` varchar(20) NOT NULL DEFAULT 'x86' COMMENT '架构',
  `is_update_key` int(11) NOT NULL DEFAULT '0' COMMENT '是否更新密钥 0:不更新，1:更新',
  `is_update_uuid` int(11) NOT NULL DEFAULT '0' COMMENT '是否更新UUID 0：更新、1：不更新',
  `status` int(11) DEFAULT '0' COMMENT '0:未使用，1：使用中,2:已完成',
  `project_name` varchar(50) DEFAULT NULL COMMENT '所属项目名称',
  `product_name` varchar(50) DEFAULT NULL COMMENT '所属产品名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注（打包原因）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pack_mode
-- ----------------------------
INSERT INTO `pack_mode` VALUES ('1', 'E:/apache-tomcat-7.0.77/packUtils/LambdaPRO_1', '1', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', '0', '80', '', 'dsp', '0', '0', '0', 'test1', 'test1', 'test1');
INSERT INTO `pack_mode` VALUES ('3', null, '0', '', '0', '0', '', 'x86', '0', '0', '0', null, null, null);

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `permission` int(11) NOT NULL DEFAULT '0' COMMENT '0：开发人员、1：管理人员',
  `svn_username` varchar(20) DEFAULT '',
  `svn_password` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'admin', 'admin123', '0', 'yuq', 'coretekyuq');
INSERT INTO `person` VALUES ('2', 'test', 'test', '0', 'yuq', 'coretekyuq');
