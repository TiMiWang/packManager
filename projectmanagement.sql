/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : projectmanagement

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-12-01 17:30:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `log_info`
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pack_mode_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_info
-- ----------------------------

-- ----------------------------
-- Table structure for `pack_mode`
-- ----------------------------
DROP TABLE IF EXISTS `pack_mode`;
CREATE TABLE `pack_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_local_path` varchar(200) DEFAULT NULL,
  `is_svn_check` int(11) NOT NULL DEFAULT '0' COMMENT '0:不是、1：是',
  `svn_net_path` varchar(100) NOT NULL DEFAULT '',
  `version_info` int(11) NOT NULL DEFAULT '0' COMMENT '版本信息 0:试用、1：正式',
  `indate` int(11) DEFAULT '0' COMMENT '有效期',
  `system_version` varchar(20) NOT NULL COMMENT '系统版本',
  `structure_type` varchar(20) NOT NULL DEFAULT 'x86' COMMENT '架构',
  `is_update_key` int(11) NOT NULL DEFAULT '0' COMMENT '是否更新密钥 0:不更新，1:更新',
  `is_update_uuid` int(11) NOT NULL DEFAULT '0' COMMENT '是否更新UUID 0：更新、1：不更新',
  `platform_svn_path` varchar(100) DEFAULT '',
  `status` int(11) DEFAULT '0' COMMENT '0:未使用，1：使用中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pack_mode
-- ----------------------------
INSERT INTO `pack_mode` VALUES ('1', 'G:\\work\\dabao\\webworkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PackManagement\\upload\\installer_tonghuang.rar', '0', '', '0', '90', '6.0', 'x86', '0', '0', 'F:\\tonghuang\\platform\\dabao', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'admin', 'admin123', '0', 'admin', 'admin123123');
