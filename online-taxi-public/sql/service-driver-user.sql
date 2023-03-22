/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : service-driver-user

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 22/03/2023 21:58:57
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
  `tid` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '终端id',
  `trid` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轨迹id',
  `trname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轨迹name',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for driver_car_binding_relationship
-- ----------------------------
DROP TABLE IF EXISTS `driver_car_binding_relationship`;
CREATE TABLE `driver_car_binding_relationship`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `driver_id` bigint(32) NULL DEFAULT NULL COMMENT 'driver_user表中司机的id',
  `car_id` bigint(32) NULL DEFAULT NULL COMMENT 'car表中car的id',
  `bind_state` int(2) NULL DEFAULT NULL COMMENT '绑定状态 1：绑定 2：解绑',
  `binding_date` datetime(0) NULL DEFAULT NULL COMMENT '司机与车的绑定时间',
  `un_binding_date` datetime(0) NULL DEFAULT NULL COMMENT '司机与车的解绑时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for driver_user
-- ----------------------------
DROP TABLE IF EXISTS `driver_user`;
CREATE TABLE `driver_user`  (
  `id` bigint(32) NOT NULL COMMENT '主键id',
  `address` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机注册地行政区划分',
  `driver_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机姓名',
  `driver_phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机电话',
  `driver_gender` tinyint(2) NULL DEFAULT NULL COMMENT '司机性别',
  `driver_birthday` date NULL DEFAULT NULL COMMENT '司机出生日期',
  `driver_nation` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机民族',
  `driver_contact_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '驾驶员通信地址',
  `license_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机动车驾驶证号',
  `get_driver_license_date` date NULL DEFAULT NULL COMMENT '初次领取驾驶证日期',
  `driver_license_on` date NULL DEFAULT NULL COMMENT '驾驶证有效期限起',
  `driver_license_off` date NULL DEFAULT NULL COMMENT '驾驶证有效期限止',
  `taxi_driver` tinyint(2) NULL DEFAULT NULL COMMENT '是否巡游出租汽车驾驶员',
  `certificate_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网络预约出租汽车驾驶员资格证号',
  `network_car_issue_organization` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网络预约出租汽车驾驶员发证机构',
  `network_car_issue_date` date NULL DEFAULT NULL COMMENT '资格证发证日期',
  `get_network_car_proof_date` date NULL DEFAULT NULL COMMENT '初次领取资格证日期',
  `network_car_proof_on` date NULL DEFAULT NULL COMMENT '资格证有效起始日期',
  `network_car_proof_off` date NULL DEFAULT NULL COMMENT '资格证有效截止日期',
  `register_date` date NULL DEFAULT NULL COMMENT '驾驶员信息向服务所在地出租汽车行政主管部门报备日期',
  `commercial_type` tinyint(2) NULL DEFAULT NULL COMMENT '服务类型 1.网络预约出租汽车，2.巡游出租汽车, 3.私人小客车',
  `contract_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '驾驶员合同（或协议）签署公司',
  `contract_on` date NULL DEFAULT NULL COMMENT '合同有效期起',
  `contract_off` date NULL DEFAULT NULL COMMENT '合同有效期止',
  `state` tinyint(2) NULL DEFAULT NULL COMMENT '司机状态：0 有效，1 失效',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for driver_user_work_status
-- ----------------------------
DROP TABLE IF EXISTS `driver_user_work_status`;
CREATE TABLE `driver_user_work_status`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `driver_id` bigint(32) NULL DEFAULT NULL COMMENT '司机id',
  `work_status` int(4) NULL DEFAULT NULL COMMENT '司机状态 0：收车状态 1：出车状态 2：暂停状态',
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1636690417840369666 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
