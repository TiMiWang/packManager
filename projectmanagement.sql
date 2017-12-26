/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : projectmanagement

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-12-26 15:26:41
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

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
INSERT INTO `log_info` VALUES ('43', '进行打安装包操作', 'admin', '2017-12-22 12:02:04', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12', 'test12', 'test12', '2017-12-22 12:02:04');
INSERT INTO `log_info` VALUES ('44', '下载了安装包文件', 'admin', '2017-12-22 12:50:49', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12', 'test12', 'test12', '2017-12-22 12:50:49');
INSERT INTO `log_info` VALUES ('45', '下载了安装包文件', 'admin', '2017-12-22 12:51:23', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12', 'test12', 'test12', '2017-12-22 12:51:23');
INSERT INTO `log_info` VALUES ('46', '下载了安装包文件', 'admin', '2017-12-22 13:59:09', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12', 'test12', 'test12', '2017-12-22 13:59:09');
INSERT INTO `log_info` VALUES ('47', '下载了安装包文件', 'admin', '2017-12-22 13:59:14', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12', 'test12', 'test12', '2017-12-22 13:59:14');
INSERT INTO `log_info` VALUES ('48', '下载了安装包文件', 'admin', '2017-12-22 14:01:01', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12', 'test12', 'test12', '2017-12-22 14:01:01');
INSERT INTO `log_info` VALUES ('49', '下载了安装包文件', 'admin', '2017-12-22 14:08:48', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12', 'test12', 'test12', '2017-12-22 14:08:48');
INSERT INTO `log_info` VALUES ('50', '进行打安装包操作', 'admin', '2017-12-22 14:09:19', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test123', 'test123', 'test12', '2017-12-22 14:09:19');
INSERT INTO `log_info` VALUES ('51', '进行打安装包操作', 'admin', '2017-12-22 14:26:26', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test123', 'test123', 'test12', '2017-12-22 14:26:26');
INSERT INTO `log_info` VALUES ('52', '下载了安装包文件', 'admin', '2017-12-22 15:09:58', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test123', 'test123', 'test12', '2017-12-22 15:09:58');
INSERT INTO `log_info` VALUES ('53', '下载了安装包文件', 'admin', '2017-12-22 15:33:44', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test123', 'test123', 'test12', '2017-12-22 15:33:44');
INSERT INTO `log_info` VALUES ('54', '下载了安装包文件', 'admin', '2017-12-22 16:07:46', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test123', 'test123', 'test12', '2017-12-22 16:07:46');
INSERT INTO `log_info` VALUES ('55', '进行打安装包操作', 'admin', '2017-12-22 17:58:50', 'dsp', '80', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test123', 'test123', 'test12gdfg', '2017-12-22 17:58:50');
INSERT INTO `log_info` VALUES ('56', '进行打安装包操作', 'admin', '2017-12-25 09:28:19', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 09:28:19');
INSERT INTO `log_info` VALUES ('57', '下载了安装包文件', 'admin', '2017-12-25 10:51:28', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:51:28');
INSERT INTO `log_info` VALUES ('58', '下载了安装包文件', 'admin', '2017-12-25 10:52:55', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:52:55');
INSERT INTO `log_info` VALUES ('59', '下载了安装包文件', 'admin', '2017-12-25 10:52:58', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:52:58');
INSERT INTO `log_info` VALUES ('60', '下载了安装包文件', 'admin', '2017-12-25 10:53:00', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:53:00');
INSERT INTO `log_info` VALUES ('61', '下载了安装包文件', 'admin', '2017-12-25 10:53:02', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:53:02');
INSERT INTO `log_info` VALUES ('62', '下载了安装包文件', 'admin', '2017-12-25 10:53:04', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:53:04');
INSERT INTO `log_info` VALUES ('63', '下载了安装包文件', 'admin', '2017-12-25 10:53:07', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:53:07');
INSERT INTO `log_info` VALUES ('64', '下载了安装包文件', 'admin', '2017-12-25 10:53:10', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:53:10');
INSERT INTO `log_info` VALUES ('65', '下载了安装包文件', 'admin', '2017-12-25 10:53:20', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25', 'test12_25', 'test12_25', '2017-12-25 10:53:20');
INSERT INTO `log_info` VALUES ('66', '进行打安装包操作', 'admin', '2017-12-25 15:15:54', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25_1', 'test12_25_1', 'test12_25_1', '2017-12-25 15:15:54');
INSERT INTO `log_info` VALUES ('67', '下载了安装包文件', 'admin', '2017-12-25 16:18:52', 'dsp', '88', '试用版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25_1', 'test12_25_1', 'test12_25_1', '2017-12-25 16:18:52');
INSERT INTO `log_info` VALUES ('68', '进行打安装包操作', 'admin', '2017-12-25 16:23:40', 'dsp', '88', '正式版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-25 16:23:40');
INSERT INTO `log_info` VALUES ('69', '成功将文件：LambdaPRO.zip上传到服务器', 'admin', '2017-12-26 10:53:26', 'dsp', '88', '正式版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 10:53:26');
INSERT INTO `log_info` VALUES ('70', '成功将文件：LambdaPRO.zip上传到服务器', 'admin', '2017-12-26 10:56:57', 'dsp', '88', '正式版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 10:56:57');
INSERT INTO `log_info` VALUES ('71', '成功将文件：LambdaPRO.zip上传到服务器', 'admin', '2017-12-26 10:58:26', 'dsp', '88', '正式版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 10:58:26');
INSERT INTO `log_info` VALUES ('72', '成功将文件：LambdaPRO.zip上传到服务器', 'admin', '2017-12-26 11:03:02', 'dsp', '88', '正式版', 'http://192.168.11.24/svn/DeltaOSDSP/branches/task/006实现系统级调试/LambdaPRO', 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 11:03:02');
INSERT INTO `log_info` VALUES ('73', '成功将文件：LambdaPRO.zip上传到服务器', 'admin', '2017-12-26 11:11:50', 'dsp', '88', '正式版', null, 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 11:11:50');
INSERT INTO `log_info` VALUES ('74', '进行打安装包操作', 'admin', '2017-12-26 11:12:21', 'dsp', '88', '正式版', null, 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 11:12:21');
INSERT INTO `log_info` VALUES ('75', '下载了安装包文件', 'admin', '2017-12-26 11:59:03', 'dsp', '88', '正式版', null, 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 11:59:03');
INSERT INTO `log_info` VALUES ('76', '成功将文件：LambdaPRO.zip上传到服务器', 'admin', '2017-12-26 14:11:45', 'dsp', '88', '正式版', null, 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 14:11:45');
INSERT INTO `log_info` VALUES ('77', '进行打安装包操作', 'admin', '2017-12-26 14:12:37', 'dsp', '88', '试用版', null, 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 14:12:37');
INSERT INTO `log_info` VALUES ('78', '下载了安装包文件', 'admin', '2017-12-26 14:59:40', 'dsp', '88', '试用版', null, 'test12_25_2', 'test12_25_2', 'test12_25_2', '2017-12-26 14:59:40');

-- ----------------------------
-- Table structure for `pack_mode`
-- ----------------------------
DROP TABLE IF EXISTS `pack_mode`;
CREATE TABLE `pack_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_local_path` varchar(200) DEFAULT NULL,
  `is_svn_check` int(11) DEFAULT '0' COMMENT '0:不是、1：是',
  `svn_net_path` varchar(100) DEFAULT '',
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
INSERT INTO `pack_mode` VALUES ('1', 'D:\\xampp\\tomcat\\packUtils\\LambdaPRO_1', '0', null, '0', '88', '', 'dsp', '0', '0', '0', 'test12_25_2', 'test12_25_2', 'test12_25_2');
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
