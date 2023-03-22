/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : service-order

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 22/03/2023 21:57:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(32) NOT NULL COMMENT '订单id',
  `passenger_id` bigint(32) NULL DEFAULT NULL COMMENT '乘客id',
  `passenger_phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乘客电话',
  `driver_id` bigint(32) NULL DEFAULT NULL COMMENT '司机id',
  `driver_phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机电话',
  `car_id` bigint(32) NULL DEFAULT NULL COMMENT '车辆id',
  `address` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出发地行政区码',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单发起时间',
  `depart_time` datetime(0) NULL DEFAULT NULL COMMENT '预计用车时间',
  `departure` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预计出发点',
  `dep_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出发点经度',
  `dep_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出发点纬度',
  `destination` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目的地',
  `dest_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目的地经度',
  `dest_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目的地纬度',
  `encrypt` int(4) NULL DEFAULT NULL COMMENT '坐标加密标识  \r\n1：GCJ-02 测绘局标准\r\n2:  WGS64 GPS标准\r\n3： BD-09百度标准\r\n4：CGCS2000 北斗标准\r\n0：其他',
  `fareType` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运价类型编码',
  `receive_order_car_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接单时车辆的经度',
  `receive_order_car_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接单时车辆的纬度',
  `receive_order_time` datetime(0) NULL DEFAULT NULL COMMENT '接单时间或派单时间',
  `license_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机动车驾驶证号',
  `vehicle_no` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `to_pick_up_passenger_time` datetime(0) NULL DEFAULT NULL COMMENT '司机去接乘客出发时间',
  `to_pick_up_passenger_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机去接乘客时，司机的经度',
  `to_pick_up_passenger_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机去接乘客时，司机的纬度\r\n\r\n',
  `to_pick_up_passenger_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机去接乘客时，司机位置',
  `driver_arrived_departure_time` datetime(0) NULL DEFAULT NULL COMMENT '司机到达上车点的时间',
  `pick_up_passenger_time` datetime(0) NULL DEFAULT NULL COMMENT '接到乘客的时间',
  `pick_up_passenger_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接到乘客的经度',
  `pick_up_passenger_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接到乘客的纬度',
  `passenger_getoff_time` datetime(0) NULL DEFAULT NULL COMMENT '乘客下车时间',
  `passenger_getoff_longitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乘客下车经度',
  `passenger_getoff_latitude` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乘客下车纬度',
  `cancel_time` datetime(0) NULL DEFAULT NULL COMMENT '撤销订单时间',
  `cancel_operator` int(4) NULL DEFAULT NULL COMMENT '取消的操作员：1.乘客 2.驾驶员 2.平台公司',
  `cancel_type_code` int(4) NULL DEFAULT NULL COMMENT '撤销代码类型 1.乘客提前撤销 2.司机提前撤销 3.平台公司撤销 4.乘客违约撤销 5.驾驶员违约撤销',
  `driver_mile` bigint(16) NULL DEFAULT NULL COMMENT '载客里程（米）',
  `driver_time` bigint(16) NULL DEFAULT NULL COMMENT '载客时间（分）',
  `order_status` int(4) NULL DEFAULT NULL COMMENT '订单状态 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8：支付完成 9：订单取消',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
