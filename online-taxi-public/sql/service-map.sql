/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : service-map

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 22/03/2023 21:58:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dic_district
-- ----------------------------
DROP TABLE IF EXISTS `dic_district`;
CREATE TABLE `dic_district`  (
  `address_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地区编码',
  `address_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区名称',
  `parent_address_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上一级地区编码',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '级别，0国家，1省级，2市级，3县级',
  PRIMARY KEY (`address_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
