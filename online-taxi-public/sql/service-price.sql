/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : service-price

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 22/03/2023 21:59:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for price_rule
-- ----------------------------
DROP TABLE IF EXISTS `price_rule`;
CREATE TABLE `price_rule`  (
  `city_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '城市代码',
  `vehicle_type` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车辆类型型',
  `start_fare` double(4, 2) NULL DEFAULT NULL COMMENT '起步价',
  `start_mile` int(4) NULL DEFAULT NULL COMMENT '起步里程',
  `unit_price_per_mile` double(4, 2) NULL DEFAULT NULL COMMENT '一公里多少前',
  `unit_price_per_minute` double(4, 2) NULL DEFAULT NULL COMMENT '每分钟多少钱',
  PRIMARY KEY (`city_code`, `vehicle_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
