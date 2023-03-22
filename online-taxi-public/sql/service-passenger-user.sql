/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : service-passenger-user

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 22/03/2023 21:59:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for passenger_user
-- ----------------------------
DROP TABLE IF EXISTS `passenger_user`;
CREATE TABLE `passenger_user`  (
  `id` bigint(32) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `passenger_phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乘客手机号',
  `passenger_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乘客姓名',
  `passenger_gender` tinyint(1) NULL DEFAULT NULL COMMENT '乘客性别，0是未知的性别，1是男，2是女，9未说明的性别',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '乘客状态，0有效，1失效',
  `profile_photo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乘客头像地址url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1633255120238637058 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
