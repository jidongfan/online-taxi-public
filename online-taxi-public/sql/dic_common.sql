/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : dic_common

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 22/03/2023 22:00:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` bigint(32) NOT NULL COMMENT '公司标识id',
  `address` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆所在城市（注册地行政区别代码）',
  `vehicle` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆牌照',
  `plate_color` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌颜色 1.蓝 2.黄 3.黑 4.白 5.绿色 9.其他',
  `seats` int(3) NULL DEFAULT NULL COMMENT '限定载客位',
  `brand` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆厂牌',
  `vehicle_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `owner_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆所有人',
  `vehicle_color` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车身颜色 1.白 2.黑\r\n',
  `engine_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发动机号',
  `vin` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆VIN码',
  `certify_date_a` date NULL DEFAULT NULL COMMENT '车辆注册日期',
  `fue_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆燃料类型 1.汽油 2.柴油 3.天然气 4.液化气 5.电动 9.其他',
  `engine_displace` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发动机排量 单位：毫升',
  `trans_agency` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆运输证发证机构',
  `trans_area` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆经营区域',
  `trans_date_start` date NULL DEFAULT NULL COMMENT '车辆运输证有效期起',
  `trans_date_end` date NULL DEFAULT NULL COMMENT '车辆运输证有效期止',
  `certify_date_b` date NULL DEFAULT NULL COMMENT '车辆初次登记日期',
  `fix_state` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆检修状态（0：为检修 1：检修 2：未知）',
  `next_fix_date` date NULL DEFAULT NULL COMMENT '车辆下次年检时间',
  `check_state` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆年度审验状态',
  `fee_print_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发票打印设备序列号',
  `gps_brand` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卫星定位装置品牌',
  `gps_model` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卫星定位装置型号',
  `gps_install_date` date NULL DEFAULT NULL COMMENT '卫星定位设备安装日期',
  `register_date` date NULL DEFAULT NULL COMMENT '车辆报备日期',
  `commercial_type` int(2) NULL DEFAULT NULL COMMENT '服务类型（1.网络预约出租车 2.巡游出租车 3.私人小客车合乘）',
  `fare_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运价类型',
  `states` tinyint(1) NULL DEFAULT NULL COMMENT '状态：0有效 1无效',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dic_nation
-- ----------------------------
DROP TABLE IF EXISTS `dic_nation`;
CREATE TABLE `dic_nation`  (
  `nation_code` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '民族编码',
  `nation_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '民族名称',
  PRIMARY KEY (`nation_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
