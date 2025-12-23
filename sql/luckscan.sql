/*
Navicat MySQL Data Transfer

Source Server         : loacl
Source Server Version : 50744
Source Host           : localhost:3306
Source Database       : luckscan

Target Server Type    : MYSQL
Target Server Version : 50744
File Encoding         : 65001

Date: 2025-12-15 03:08:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
                             `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                             `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
                             `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
                             `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
                             `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
                             `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
                             `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
                             `tpl_web_type` varchar(30) DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
                             `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
                             `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
                             `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
                             `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
                             `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
                             `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
                             `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
                             `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
                             `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                             PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES ('1', 't_customer', '用户表', null, null, 'TCustomer', 'crud', 'element-plus', 'com.ruoyi.business', 'business', 'customer', '用户管理', 'ruoyi', '0', '/', '{\"parentMenuId\":2000}', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24', null);
INSERT INTO `gen_table` VALUES ('2', 't_credit_log', '额度变更', null, null, 'TCreditLog', 'crud', 'element-plus', 'com.ruoyi.business', 'business', 'creditLog', '额度变更', 'ruoyi', '0', '/', '{\"parentMenuId\":2000}', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07', null);
INSERT INTO `gen_table` VALUES ('3', 't_payment_request', '入金订单', null, null, 'TPaymentRequest', 'crud', 'element-plus', 'com.ruoyi.business', 'business', 'paymentRequest', '存入订单', 'ruoyi', '0', '/', '{\"parentMenuId\":2000}', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00', null);
INSERT INTO `gen_table` VALUES ('4', 't_scan_order', '扫描订单', null, null, 'TScanOrder', 'crud', 'element-plus', 'com.ruoyi.business', 'business', 'scanOrder', '扫描订单', 'ruoyi', '0', '/', '{\"parentMenuId\":2000}', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:28:13', null);
INSERT INTO `gen_table` VALUES ('5', 't_vip', 'VIP管理', null, null, 'TVip', 'crud', 'element-plus', 'com.ruoyi.business', 'business', 'vip', 'VIP管理', 'ruoyi', '0', '/', '{\"parentMenuId\":2000}', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29', null);
INSERT INTO `gen_table` VALUES ('6', 't_withdraw_request', '提现订单', null, null, 'TWithdrawRequest', 'crud', 'element-plus', 'com.ruoyi.business', 'business', 'withdrawRequest', '提现订单', 'ruoyi', '0', '/', '{\"parentMenuId\":2000}', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19', null);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
                                    `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                    `table_id` bigint(20) DEFAULT NULL COMMENT '归属表编号',
                                    `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
                                    `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
                                    `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
                                    `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
                                    `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
                                    `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
                                    `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
                                    `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
                                    `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
                                    `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
                                    `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
                                    `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
                                    `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
                                    `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
                                    `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
                                    `sort` int(11) DEFAULT NULL COMMENT '排序',
                                    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES ('1', '1', 'id', '用户ID', 'bigint(16)', 'Long', 'id', '1', '1', '0', '1', null, null, null, 'EQ', 'input', '', '1', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('2', '1', 'username', '用户名', 'varchar(32)', 'String', 'username', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', '2', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('3', '1', 'password', '密码', 'varchar(64)', 'String', 'password', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', '3', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('4', '1', 'invite_code', '邀请码', 'varchar(8)', 'String', 'inviteCode', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', '4', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('5', '1', 'path', 'path', 'varchar(255)', 'String', 'path', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '5', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('6', '1', 'p_id', '父级ID', 'bigint(16)', 'Long', 'pId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '6', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('7', '1', 'balance', '余额', 'decimal(16,2)', 'BigDecimal', 'balance', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '7', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('8', '1', 'lock_balance', '冻结余额', 'decimal(16,2)', 'BigDecimal', 'lockBalance', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '8', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('9', '1', 'grade', 'VIP等级', 'tinyint(1)', 'Integer', 'grade', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '9', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('10', '1', 'withdraw_password', '提款密码', 'varchar(126)', 'String', 'withdrawPassword', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '10', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('11', '1', 'last_login_address', '最后登录IP', 'varchar(64)', 'String', 'lastLoginAddress', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '11', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('12', '1', 'last_login_time', '最后登录时间', 'timestamp', 'Date', 'lastLoginTime', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', '12', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('13', '1', 'create_time', '注册时间', 'timestamp', 'Date', 'createTime', '0', '0', '0', '1', null, null, null, 'EQ', 'datetime', '', '13', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('14', '1', 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '0', '0', '1', '1', null, null, 'EQ', 'datetime', '', '14', 'admin', '2025-12-08 22:15:54', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('16', '1', 'status', '0正常 1启用', 'tinyint(1)', 'Integer', 'status', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', 'sys_normal_disable', '15', '', '2025-12-08 22:20:09', '', '2025-12-08 22:23:24');
INSERT INTO `gen_table_column` VALUES ('17', '2', 'id', null, 'bigint(32)', 'Long', 'id', '1', '1', '0', '1', null, null, null, 'EQ', 'input', '', '1', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('18', '2', 'customer_id', '商户号', 'bigint(20)', 'Long', 'customerId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '2', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('19', '2', 'opearte_type', '操作类型', 'int(2)', 'Integer', 'opearteType', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'select', '', '3', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('20', '2', 'opearte_amount', '操作金额，可以为负数', 'decimal(10,4)', 'BigDecimal', 'opearteAmount', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '4', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('21', '2', 'pre_balance', '操作前金额', 'decimal(10,4)', 'BigDecimal', 'preBalance', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '5', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('22', '2', 'post_balance', '操作后金额', 'decimal(10,4)', 'BigDecimal', 'postBalance', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '6', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('23', '2', 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '0', '1', null, null, null, 'EQ', 'datetime', '', '7', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('24', '2', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', '0', '1', null, null, null, 'EQ', 'input', '', '8', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('25', '2', 'update_time', null, 'timestamp', 'Date', 'updateTime', '0', '0', '0', '1', '1', null, null, 'EQ', 'datetime', '', '9', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('26', '2', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', '0', '1', '1', null, null, 'EQ', 'input', '', '10', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('27', '2', 'ref_id', '关联ID', 'varchar(32)', 'String', 'refId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '11', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('28', '2', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '0', '1', '1', '1', null, 'EQ', 'input', '', '12', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:07');
INSERT INTO `gen_table_column` VALUES ('29', '3', 'request_id', null, 'varchar(32)', 'String', 'requestId', '1', '0', '0', '1', null, null, null, 'EQ', 'input', '', '1', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('30', '3', 'customer_id', '商户号', 'bigint(20)', 'Long', 'customerId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', '2', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('31', '3', 'username', '商户名', 'varchar(255)', 'String', 'username', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', '3', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('32', '3', 'status', '支付状态', 'tinyint(1)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', 'order_status', '4', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('33', '3', 'order_amount', '订单金额', 'decimal(12,2)', 'BigDecimal', 'orderAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', '5', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('34', '3', 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '0', '1', null, null, null, 'EQ', 'datetime', '', '6', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('35', '3', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', '0', '1', null, null, null, 'EQ', 'input', '', '7', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('36', '3', 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '0', '0', '1', '1', null, null, 'EQ', 'datetime', '', '8', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('37', '3', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', '0', '1', '1', null, null, 'EQ', 'input', '', '9', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('38', '3', 'remark', '备注', 'varchar(64)', 'String', 'remark', '0', '0', '0', '1', '1', '1', null, 'EQ', 'input', '', '10', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('39', '3', 'real_amount', '真实金额', 'decimal(12,2)', 'BigDecimal', 'realAmount', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '11', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('40', '3', 'success_time', '成功时间', 'timestamp', 'Date', 'successTime', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', '12', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:30:00');
INSERT INTO `gen_table_column` VALUES ('41', '4', 'order_no', '订单号', 'varchar(32)', 'String', 'orderNo', '1', '0', '0', '1', null, null, null, 'EQ', 'input', '', '1', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:28:13');
INSERT INTO `gen_table_column` VALUES ('42', '4', 'customer_id', '用户ID', 'bigint(16)', 'Long', 'customerId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '2', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:28:13');
INSERT INTO `gen_table_column` VALUES ('43', '4', 'username', '用户名', 'varchar(64)', 'String', 'username', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', '3', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:28:14');
INSERT INTO `gen_table_column` VALUES ('44', '4', 'barcode', '条形码', 'varchar(255)', 'String', 'barcode', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '4', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:28:14');
INSERT INTO `gen_table_column` VALUES ('45', '4', 'reward_amount', null, 'decimal(10,2)', 'BigDecimal', 'rewardAmount', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '5', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:28:14');
INSERT INTO `gen_table_column` VALUES ('46', '4', 'create_time', null, 'timestamp', 'Date', 'createTime', '0', '0', '0', '1', null, null, null, 'EQ', 'datetime', '', '6', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:28:14');
INSERT INTO `gen_table_column` VALUES ('47', '4', 'status', '0正常 1启用', 'tinyint(1)', 'Integer', 'status', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', 'order_status', '7', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:28:14');
INSERT INTO `gen_table_column` VALUES ('48', '5', 'id', null, 'int(4)', 'Integer', 'id', '1', '1', '0', '1', null, null, null, 'EQ', 'input', '', '1', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('49', '5', 'scan_limit', '扫码次数', 'int(8)', 'Integer', 'scanLimit', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '2', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('50', '5', 'withdraw_limit', '提现次数', 'int(8)', 'Integer', 'withdrawLimit', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '3', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('51', '5', 'min_reward', '最小奖励', 'decimal(12,2)', 'BigDecimal', 'minReward', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '4', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('52', '5', 'max_reward', '最大奖励', 'decimal(12,2)', 'BigDecimal', 'maxReward', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '5', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('53', '5', 'share_count', '分享奖励扫码次数', 'int(8)', 'Integer', 'shareCount', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '6', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('54', '5', 'share_reward', '分享奖励', 'decimal(12,2)', 'BigDecimal', 'shareReward', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '7', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('55', '5', 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '0', '1', null, null, null, 'EQ', 'datetime', '', '8', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('56', '5', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', '0', '1', null, null, null, 'EQ', 'input', '', '9', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('57', '5', 'update_time', null, 'timestamp', 'Date', 'updateTime', '0', '0', '0', '1', '1', null, null, 'EQ', 'datetime', '', '10', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('58', '5', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', '0', '1', '1', null, null, 'EQ', 'input', '', '11', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('59', '5', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '0', '1', '1', '1', null, 'EQ', 'input', '', '12', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:55:29');
INSERT INTO `gen_table_column` VALUES ('60', '6', 'withdraw_id', null, 'varchar(32)', 'String', 'withdrawId', '1', '0', '0', '1', null, null, null, 'EQ', 'input', '', '1', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('61', '6', 'withdraw_amount', '提现金额', 'decimal(12,2)', 'BigDecimal', 'withdrawAmount', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '2', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('62', '6', 'customer_id', '商户号', 'bigint(20)', 'Long', 'customerId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '3', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('63', '6', 'username', null, 'varchar(64)', 'String', 'username', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', '4', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('64', '6', 'status', '状态', 'tinyint(1)', 'Integer', 'status', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', 'order_status', '5', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('65', '6', 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '0', '1', null, null, null, 'EQ', 'datetime', '', '6', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('66', '6', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', '0', '1', null, null, null, 'EQ', 'input', '', '7', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('67', '6', 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '0', '0', '1', '1', null, null, 'EQ', 'datetime', '', '8', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('68', '6', 'update_by', '修改人', 'varchar(32)', 'String', 'updateBy', '0', '0', '0', '1', '1', null, null, 'EQ', 'input', '', '9', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('69', '6', 'real_amount', '真实金额', 'decimal(12,2)', 'BigDecimal', 'realAmount', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', '10', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');
INSERT INTO `gen_table_column` VALUES ('70', '6', 'remark', '备注', 'varchar(128)', 'String', 'remark', '0', '0', '0', '1', '1', '1', null, 'EQ', 'input', '', '11', 'admin', '2025-12-12 22:15:13', '', '2025-12-12 22:56:19');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
                                      `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                      `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                      `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                      `blob_data` blob COMMENT '存放持久化Trigger对象',
                                      PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                      CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Blob类型的触发器表';

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
                                  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                  `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
                                  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
                                  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日历信息表';

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
                                      `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                      `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                      `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                      `cron_expression` varchar(200) NOT NULL COMMENT 'cron表达式',
                                      `time_zone_id` varchar(80) DEFAULT NULL COMMENT '时区',
                                      PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                      CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cron类型的触发器表';

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
                                       `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                       `entry_id` varchar(95) NOT NULL COMMENT '调度器实例id',
                                       `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                       `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                       `instance_name` varchar(200) NOT NULL COMMENT '调度器实例名',
                                       `fired_time` bigint(13) NOT NULL COMMENT '触发的时间',
                                       `sched_time` bigint(13) NOT NULL COMMENT '定时器制定的时间',
                                       `priority` int(11) NOT NULL COMMENT '优先级',
                                       `state` varchar(16) NOT NULL COMMENT '状态',
                                       `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
                                       `job_group` varchar(200) DEFAULT NULL COMMENT '任务组名',
                                       `is_nonconcurrent` varchar(1) DEFAULT NULL COMMENT '是否并发',
                                       `requests_recovery` varchar(1) DEFAULT NULL COMMENT '是否接受恢复执行',
                                       PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已触发的触发器表';

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
                                    `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                    `job_name` varchar(200) NOT NULL COMMENT '任务名称',
                                    `job_group` varchar(200) NOT NULL COMMENT '任务组名',
                                    `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
                                    `job_class_name` varchar(250) NOT NULL COMMENT '执行任务类名称',
                                    `is_durable` varchar(1) NOT NULL COMMENT '是否持久化',
                                    `is_nonconcurrent` varchar(1) NOT NULL COMMENT '是否并发',
                                    `is_update_data` varchar(1) NOT NULL COMMENT '是否更新数据',
                                    `requests_recovery` varchar(1) NOT NULL COMMENT '是否接受恢复执行',
                                    `job_data` blob COMMENT '存放持久化job对象',
                                    PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务详细信息表';

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
                              `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                              `lock_name` varchar(40) NOT NULL COMMENT '悲观锁名称',
                              PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储的悲观锁信息表';

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
                                            `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                            `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                            PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='暂停的触发器表';

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
                                        `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                        `instance_name` varchar(200) NOT NULL COMMENT '实例名称',
                                        `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
                                        `checkin_interval` bigint(13) NOT NULL COMMENT '检查间隔时间',
                                        PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度器状态表';

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
                                        `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                        `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                        `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                        `repeat_count` bigint(7) NOT NULL COMMENT '重复的次数统计',
                                        `repeat_interval` bigint(12) NOT NULL COMMENT '重复的间隔时间',
                                        `times_triggered` bigint(10) NOT NULL COMMENT '已经触发的次数',
                                        PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                        CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简单触发器的信息表';

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
                                         `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                         `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
                                         `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
                                         `str_prop_1` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
                                         `str_prop_2` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
                                         `str_prop_3` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
                                         `int_prop_1` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
                                         `int_prop_2` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
                                         `long_prop_1` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
                                         `long_prop_2` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
                                         `dec_prop_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
                                         `dec_prop_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
                                         `bool_prop_1` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
                                         `bool_prop_2` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
                                         PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                         CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同步机制的行锁表';

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
                                 `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
                                 `trigger_name` varchar(200) NOT NULL COMMENT '触发器的名字',
                                 `trigger_group` varchar(200) NOT NULL COMMENT '触发器所属组的名字',
                                 `job_name` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
                                 `job_group` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
                                 `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
                                 `next_fire_time` bigint(13) DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
                                 `prev_fire_time` bigint(13) DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
                                 `priority` int(11) DEFAULT NULL COMMENT '优先级',
                                 `trigger_state` varchar(16) NOT NULL COMMENT '触发器状态',
                                 `trigger_type` varchar(8) NOT NULL COMMENT '触发器的类型',
                                 `start_time` bigint(13) NOT NULL COMMENT '开始时间',
                                 `end_time` bigint(13) DEFAULT NULL COMMENT '结束时间',
                                 `calendar_name` varchar(200) DEFAULT NULL COMMENT '日程表名称',
                                 `misfire_instr` smallint(2) DEFAULT NULL COMMENT '补偿执行的策略',
                                 `job_data` blob COMMENT '存放持久化job对象',
                                 PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
                                 KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
                                 CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='触发器详细信息表';

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
                              `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
                              `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
                              `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
                              `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
                              `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
                              `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                              PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2025-12-08 18:37:13', '', null, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2025-12-08 18:37:13', '', null, '初始化密码 123456');
INSERT INTO `sys_config` VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2025-12-08 18:37:13', '', null, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES ('4', '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2025-12-08 18:37:13', '', null, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES ('5', '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2025-12-08 18:37:13', '', null, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES ('6', '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2025-12-08 18:37:13', '', null, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
                            `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
                            `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
                            `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
                            `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
                            `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
                            `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
                            `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
                            `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                            `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
                            `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '奈斯科技', '0', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '1', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', '奈斯', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2025-12-08 18:37:12', '', null);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
                                 `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
                                 `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
                                 `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
                                 `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
                                 `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
                                 `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                 `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
                                 `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
                                 `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2025-12-08 18:37:13', '', null, '性别男');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '性别女');
INSERT INTO `sys_dict_data` VALUES ('3', '3', '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '性别未知');
INSERT INTO `sys_dict_data` VALUES ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2025-12-08 18:37:13', '', null, '显示菜单');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2025-12-08 18:37:13', '', null, '正常状态');
INSERT INTO `sys_dict_data` VALUES ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '停用状态');
INSERT INTO `sys_dict_data` VALUES ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2025-12-08 18:37:13', '', null, '正常状态');
INSERT INTO `sys_dict_data` VALUES ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '停用状态');
INSERT INTO `sys_dict_data` VALUES ('10', '1', '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2025-12-08 18:37:13', '', null, '默认分组');
INSERT INTO `sys_dict_data` VALUES ('11', '2', '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '系统分组');
INSERT INTO `sys_dict_data` VALUES ('12', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2025-12-08 18:37:13', '', null, '系统默认是');
INSERT INTO `sys_dict_data` VALUES ('13', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '系统默认否');
INSERT INTO `sys_dict_data` VALUES ('14', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2025-12-08 18:37:13', '', null, '通知');
INSERT INTO `sys_dict_data` VALUES ('15', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '公告');
INSERT INTO `sys_dict_data` VALUES ('16', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2025-12-08 18:37:13', '', null, '正常状态');
INSERT INTO `sys_dict_data` VALUES ('17', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '关闭状态');
INSERT INTO `sys_dict_data` VALUES ('18', '99', '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '其他操作');
INSERT INTO `sys_dict_data` VALUES ('19', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '新增操作');
INSERT INTO `sys_dict_data` VALUES ('20', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '修改操作');
INSERT INTO `sys_dict_data` VALUES ('21', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '删除操作');
INSERT INTO `sys_dict_data` VALUES ('22', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '授权操作');
INSERT INTO `sys_dict_data` VALUES ('23', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '导出操作');
INSERT INTO `sys_dict_data` VALUES ('24', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '导入操作');
INSERT INTO `sys_dict_data` VALUES ('25', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '强退操作');
INSERT INTO `sys_dict_data` VALUES ('26', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '生成操作');
INSERT INTO `sys_dict_data` VALUES ('27', '9', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '清空操作');
INSERT INTO `sys_dict_data` VALUES ('28', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '正常状态');
INSERT INTO `sys_dict_data` VALUES ('29', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2025-12-08 18:37:13', '', null, '停用状态');
INSERT INTO `sys_dict_data` VALUES ('30', '1', '待处理', '1', 'order_status', '', 'primary', 'N', '0', 'admin', '2022-04-26 11:32:08', 'admin', '2024-06-14 17:39:27', '');
INSERT INTO `sys_dict_data` VALUES ('31', '2', '成功', '2', 'order_status', '', 'success', 'N', '0', 'admin', '2022-04-26 11:32:18', 'admin', '2024-06-14 17:38:19', '');
INSERT INTO `sys_dict_data` VALUES ('32', '3', '失败', '3', 'order_status', '', 'danger', 'N', '0', 'admin', '2022-04-26 11:32:27', 'admin', '2024-06-14 17:38:32', '');
INSERT INTO `sys_dict_data` VALUES ('33', '4', '超时', '4', 'order_status', '', 'info', 'N', '0', 'admin', '2024-05-02 11:20:29', 'admin', '2024-06-14 17:38:43', '');
INSERT INTO `sys_dict_data` VALUES ('34', '5', '取消退单', '5', 'order_status', '', 'warning', 'N', '0', 'admin', '2024-05-02 11:21:00', 'admin', '2024-06-14 17:38:52', '');
INSERT INTO `sys_dict_data` VALUES ('35', '6', '预处理', '6', 'order_status', '', 'primary', 'N', '0', 'admin', '2024-05-02 11:21:13', 'admin', '2024-06-14 17:39:12', '');
INSERT INTO `sys_dict_data` VALUES ('36', '7', '异常', '7', 'order_status', '', 'danger', 'N', '0', 'admin', '2024-06-15 03:14:35', 'admin', '2024-06-15 03:14:46', '');
INSERT INTO `sys_dict_data` VALUES ('37', '8', '测试单', '8', 'order_status', '', 'warning', 'N', '0', 'admin', '2024-08-08 15:10:19', '', '2025-12-12 22:26:13', '');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
                                 `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
                                 `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
                                 `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
                                 `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`dict_id`),
                                 UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', '2025-12-08 18:37:13', '', null, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', '0', 'admin', '2025-12-08 18:37:13', '', null, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', '0', 'admin', '2025-12-08 18:37:13', '', null, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', '0', 'admin', '2025-12-08 18:37:13', '', null, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('5', '任务分组', 'sys_job_group', '0', 'admin', '2025-12-08 18:37:13', '', null, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES ('6', '系统是否', 'sys_yes_no', '0', 'admin', '2025-12-08 18:37:13', '', null, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES ('7', '通知类型', 'sys_notice_type', '0', 'admin', '2025-12-08 18:37:13', '', null, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES ('8', '通知状态', 'sys_notice_status', '0', 'admin', '2025-12-08 18:37:13', '', null, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES ('9', '操作类型', 'sys_oper_type', '0', 'admin', '2025-12-08 18:37:13', '', null, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES ('10', '系统状态', 'sys_common_status', '0', 'admin', '2025-12-08 18:37:13', '', null, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES ('11', '订单状态', 'order_status', '0', 'admin', '2025-12-12 22:23:34', '', null, null);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
                           `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
                           `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
                           `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
                           `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
                           `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
                           `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
                           `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
                           `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
                           `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
                           PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2025-12-08 18:37:13', '', null, '');
INSERT INTO `sys_job` VALUES ('2', '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2025-12-08 18:37:13', '', null, '');
INSERT INTO `sys_job` VALUES ('3', '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2025-12-08 18:37:13', '', null, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
                               `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
                               `job_name` varchar(64) NOT NULL COMMENT '任务名称',
                               `job_group` varchar(64) NOT NULL COMMENT '任务组名',
                               `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
                               `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
                               `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
                               `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
                                  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
                                  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
                                  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
                                  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
                                  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
                                  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
                                  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
                                  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
                                  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
                                  PRIMARY KEY (`info_id`),
                                  KEY `idx_sys_logininfor_s` (`status`),
                                  KEY `idx_sys_logininfor_lt` (`login_time`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES ('100', 'admin', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-08 22:09:13');
INSERT INTO `sys_logininfor` VALUES ('101', 'admin', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-08 23:09:51');
INSERT INTO `sys_logininfor` VALUES ('102', 'admin', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-09 11:57:35');
INSERT INTO `sys_logininfor` VALUES ('103', 'admin123', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '1', '用户不存在/密码错误', '2025-12-10 22:20:21');
INSERT INTO `sys_logininfor` VALUES ('104', '6465421215451212', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '1', '用户不存在/密码错误', '2025-12-12 12:44:36');
INSERT INTO `sys_logininfor` VALUES ('105', 'test8899', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '1', '用户不存在/密码错误', '2025-12-12 12:45:36');
INSERT INTO `sys_logininfor` VALUES ('106', 'test8899', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-12 13:46:32');
INSERT INTO `sys_logininfor` VALUES ('107', 'test8899', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-12 14:30:39');
INSERT INTO `sys_logininfor` VALUES ('108', 'test8899', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-12 16:57:07');
INSERT INTO `sys_logininfor` VALUES ('109', 'admin', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-12 17:56:54');
INSERT INTO `sys_logininfor` VALUES ('110', 'admin', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-12 21:33:20');
INSERT INTO `sys_logininfor` VALUES ('111', 'admin', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-12 23:51:24');
INSERT INTO `sys_logininfor` VALUES ('112', 'test8899', '127.0.0.1', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-14 13:18:34');
INSERT INTO `sys_logininfor` VALUES ('113', 'test8899', '192.168.1.10', '内网IP', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2025-12-14 13:58:51');
INSERT INTO `sys_logininfor` VALUES ('114', 'test8899', '192.168.1.10', '内网IP', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2025-12-14 14:35:25');
INSERT INTO `sys_logininfor` VALUES ('115', 'test8899', '192.168.1.10', '内网IP', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2025-12-14 20:07:32');
INSERT INTO `sys_logininfor` VALUES ('116', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-14 22:11:04');
INSERT INTO `sys_logininfor` VALUES ('117', 'test8899', '192.168.1.10', '内网IP', 'Chrome Mobile', 'Android 1.x', '0', '登录成功', '2025-12-14 22:29:02');
INSERT INTO `sys_logininfor` VALUES ('118', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-14 23:46:25');
INSERT INTO `sys_logininfor` VALUES ('119', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-15 01:10:51');
INSERT INTO `sys_logininfor` VALUES ('120', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-15 02:14:07');
INSERT INTO `sys_logininfor` VALUES ('121', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-15 02:37:54');
INSERT INTO `sys_logininfor` VALUES ('122', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '1', '用户不存在/密码错误', '2025-12-15 02:39:05');
INSERT INTO `sys_logininfor` VALUES ('123', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-15 02:39:10');
INSERT INTO `sys_logininfor` VALUES ('124', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-15 02:46:03');
INSERT INTO `sys_logininfor` VALUES ('125', 'test8899', '192.168.1.7', '内网IP', 'Chrome 14', 'Windows 10', '0', '登录成功', '2025-12-15 03:06:50');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                            `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
                            `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
                            `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
                            `path` varchar(200) DEFAULT '' COMMENT '路由地址',
                            `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
                            `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
                            `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
                            `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
                            `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
                            `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
                            `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
                            `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
                            `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) DEFAULT '' COMMENT '备注',
                            PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2037 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', 'system', null, '', '1', '0', 'M', '0', '0', '', 'system', 'admin', '2025-12-08 18:37:12', '', null, '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', 'monitor', null, '', '1', '0', 'M', '0', '0', '', 'monitor', 'admin', '2025-12-08 18:37:12', '', null, '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', 'tool', null, '', '1', '0', 'M', '0', '0', '', 'tool', 'admin', '2025-12-08 18:37:12', '', null, '系统工具目录');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', 'user', 'system/user/index', '', '1', '0', 'C', '0', '0', 'system:user:list', 'user', 'admin', '2025-12-08 18:37:12', '', null, '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', 'role', 'system/role/index', '', '1', '0', 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2025-12-08 18:37:12', '', null, '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '', '1', '0', 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2025-12-08 18:37:12', '', null, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', 'dept', 'system/dept/index', '', '1', '0', 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2025-12-08 18:37:12', '', null, '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', 'post', 'system/post/index', '', '1', '0', 'C', '0', '0', 'system:post:list', 'post', 'admin', '2025-12-08 18:37:12', '', null, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', 'dict', 'system/dict/index', '', '1', '0', 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2025-12-08 18:37:12', '', null, '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', 'config', 'system/config/index', '', '1', '0', 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2025-12-08 18:37:12', '', null, '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', 'notice', 'system/notice/index', '', '1', '0', 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2025-12-08 18:37:12', '', null, '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', 'log', '', '', '1', '0', 'M', '0', '0', '', 'log', 'admin', '2025-12-08 18:37:12', '', null, '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', 'online', 'monitor/online/index', '', '1', '0', 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2025-12-08 18:37:12', '', null, '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', 'job', 'monitor/job/index', '', '1', '0', 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2025-12-08 18:37:12', '', null, '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', 'druid', 'monitor/druid/index', '', '1', '0', 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2025-12-08 18:37:12', '', null, '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '4', 'server', 'monitor/server/index', '', '1', '0', 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2025-12-08 18:37:12', '', null, '服务监控菜单');
INSERT INTO `sys_menu` VALUES ('113', '缓存监控', '2', '5', 'cache', 'monitor/cache/index', '', '1', '0', 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2025-12-08 18:37:12', '', null, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES ('114', '缓存列表', '2', '6', 'cacheList', 'monitor/cache/list', '', '1', '0', 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', '2025-12-08 18:37:12', '', null, '缓存列表菜单');
INSERT INTO `sys_menu` VALUES ('115', '表单构建', '3', '1', 'build', 'tool/build/index', '', '1', '0', 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2025-12-08 18:37:12', '', null, '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('116', '代码生成', '3', '2', 'gen', 'tool/gen/index', '', '1', '0', 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2025-12-08 18:37:12', '', null, '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('117', '系统接口', '3', '3', 'swagger', 'tool/swagger/index', '', '1', '0', 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2025-12-08 18:37:12', '', null, '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', 'operlog', 'monitor/operlog/index', '', '1', '0', 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2025-12-08 18:37:12', '', null, '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '', '1', '0', 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2025-12-08 18:37:12', '', null, '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '', '', '', '1', '0', 'F', '0', '0', 'system:user:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '', '', '', '1', '0', 'F', '0', '0', 'system:user:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '', '', '', '1', '0', 'F', '0', '0', 'system:user:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '', '', '', '1', '0', 'F', '0', '0', 'system:user:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '', '', '', '1', '0', 'F', '0', '0', 'system:user:export', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1005', '用户导入', '100', '6', '', '', '', '1', '0', 'F', '0', '0', 'system:user:import', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1006', '重置密码', '100', '7', '', '', '', '1', '0', 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1007', '角色查询', '101', '1', '', '', '', '1', '0', 'F', '0', '0', 'system:role:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1008', '角色新增', '101', '2', '', '', '', '1', '0', 'F', '0', '0', 'system:role:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1009', '角色修改', '101', '3', '', '', '', '1', '0', 'F', '0', '0', 'system:role:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1010', '角色删除', '101', '4', '', '', '', '1', '0', 'F', '0', '0', 'system:role:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1011', '角色导出', '101', '5', '', '', '', '1', '0', 'F', '0', '0', 'system:role:export', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1012', '菜单查询', '102', '1', '', '', '', '1', '0', 'F', '0', '0', 'system:menu:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单新增', '102', '2', '', '', '', '1', '0', 'F', '0', '0', 'system:menu:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单修改', '102', '3', '', '', '', '1', '0', 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1015', '菜单删除', '102', '4', '', '', '', '1', '0', 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1016', '部门查询', '103', '1', '', '', '', '1', '0', 'F', '0', '0', 'system:dept:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1017', '部门新增', '103', '2', '', '', '', '1', '0', 'F', '0', '0', 'system:dept:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1018', '部门修改', '103', '3', '', '', '', '1', '0', 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1019', '部门删除', '103', '4', '', '', '', '1', '0', 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1020', '岗位查询', '104', '1', '', '', '', '1', '0', 'F', '0', '0', 'system:post:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位新增', '104', '2', '', '', '', '1', '0', 'F', '0', '0', 'system:post:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位修改', '104', '3', '', '', '', '1', '0', 'F', '0', '0', 'system:post:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位删除', '104', '4', '', '', '', '1', '0', 'F', '0', '0', 'system:post:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1024', '岗位导出', '104', '5', '', '', '', '1', '0', 'F', '0', '0', 'system:post:export', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1025', '字典查询', '105', '1', '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1026', '字典新增', '105', '2', '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1027', '字典修改', '105', '3', '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1028', '字典删除', '105', '4', '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1029', '字典导出', '105', '5', '#', '', '', '1', '0', 'F', '0', '0', 'system:dict:export', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1030', '参数查询', '106', '1', '#', '', '', '1', '0', 'F', '0', '0', 'system:config:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1031', '参数新增', '106', '2', '#', '', '', '1', '0', 'F', '0', '0', 'system:config:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1032', '参数修改', '106', '3', '#', '', '', '1', '0', 'F', '0', '0', 'system:config:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1033', '参数删除', '106', '4', '#', '', '', '1', '0', 'F', '0', '0', 'system:config:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1034', '参数导出', '106', '5', '#', '', '', '1', '0', 'F', '0', '0', 'system:config:export', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1035', '公告查询', '107', '1', '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1036', '公告新增', '107', '2', '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1037', '公告修改', '107', '3', '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1038', '公告删除', '107', '4', '#', '', '', '1', '0', 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1039', '操作查询', '500', '1', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1040', '操作删除', '500', '2', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1041', '日志导出', '500', '3', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1042', '登录查询', '501', '1', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1043', '登录删除', '501', '2', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1044', '日志导出', '501', '3', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1045', '账户解锁', '501', '4', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1046', '在线查询', '109', '1', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1047', '批量强退', '109', '2', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1048', '单条强退', '109', '3', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1049', '任务查询', '110', '1', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1050', '任务新增', '110', '2', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1051', '任务修改', '110', '3', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1052', '任务删除', '110', '4', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1053', '状态修改', '110', '5', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1054', '任务导出', '110', '6', '#', '', '', '1', '0', 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1055', '生成查询', '116', '1', '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1056', '生成修改', '116', '2', '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1057', '生成删除', '116', '3', '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1058', '导入代码', '116', '4', '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1059', '预览代码', '116', '5', '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('1060', '生成代码', '116', '6', '#', '', '', '1', '0', 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('2000', '业务管理', '0', '3', 'business', null, null, '1', '0', 'M', '0', '0', null, 'example', 'admin', '2025-12-08 22:12:24', '', null, '');
INSERT INTO `sys_menu` VALUES ('2001', '用户管理', '2000', '1', 'customer', 'business/customer/index', null, '1', '0', 'C', '0', '0', 'business:customer:list', '#', 'admin', '2025-12-08 22:38:39', '', null, '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('2002', '用户管理查询', '2001', '1', '#', '', null, '1', '0', 'F', '0', '0', 'business:customer:query', '#', 'admin', '2025-12-08 22:38:39', '', null, '');
INSERT INTO `sys_menu` VALUES ('2003', '用户管理新增', '2001', '2', '#', '', null, '1', '0', 'F', '0', '0', 'business:customer:add', '#', 'admin', '2025-12-08 22:38:39', '', null, '');
INSERT INTO `sys_menu` VALUES ('2004', '用户管理修改', '2001', '3', '#', '', null, '1', '0', 'F', '0', '0', 'business:customer:edit', '#', 'admin', '2025-12-08 22:38:39', '', null, '');
INSERT INTO `sys_menu` VALUES ('2005', '用户管理删除', '2001', '4', '#', '', null, '1', '0', 'F', '0', '0', 'business:customer:remove', '#', 'admin', '2025-12-08 22:38:39', '', null, '');
INSERT INTO `sys_menu` VALUES ('2006', '用户管理导出', '2001', '5', '#', '', null, '1', '0', 'F', '0', '0', 'business:customer:export', '#', 'admin', '2025-12-08 22:38:39', '', null, '');
INSERT INTO `sys_menu` VALUES ('2007', '额度变更', '2000', '1', 'creditLog', 'business/creditLog/index', null, '1', '0', 'C', '0', '0', 'business:creditLog:list', '#', 'admin', '2025-12-12 23:04:40', '', null, '额度变更菜单');
INSERT INTO `sys_menu` VALUES ('2008', '额度变更查询', '2007', '1', '#', '', null, '1', '0', 'F', '0', '0', 'business:creditLog:query', '#', 'admin', '2025-12-12 23:04:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2009', '额度变更新增', '2007', '2', '#', '', null, '1', '0', 'F', '0', '0', 'business:creditLog:add', '#', 'admin', '2025-12-12 23:04:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2010', '额度变更修改', '2007', '3', '#', '', null, '1', '0', 'F', '0', '0', 'business:creditLog:edit', '#', 'admin', '2025-12-12 23:04:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2011', '额度变更删除', '2007', '4', '#', '', null, '1', '0', 'F', '0', '0', 'business:creditLog:remove', '#', 'admin', '2025-12-12 23:04:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2012', '额度变更导出', '2007', '5', '#', '', null, '1', '0', 'F', '0', '0', 'business:creditLog:export', '#', 'admin', '2025-12-12 23:04:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2013', '存入订单', '2000', '1', 'paymentRequest', 'business/paymentRequest/index', null, '1', '0', 'C', '0', '0', 'business:paymentRequest:list', '#', 'admin', '2025-12-12 23:04:55', '', null, '存入订单菜单');
INSERT INTO `sys_menu` VALUES ('2014', '存入订单查询', '2013', '1', '#', '', null, '1', '0', 'F', '0', '0', 'business:paymentRequest:query', '#', 'admin', '2025-12-12 23:04:55', '', null, '');
INSERT INTO `sys_menu` VALUES ('2015', '存入订单新增', '2013', '2', '#', '', null, '1', '0', 'F', '0', '0', 'business:paymentRequest:add', '#', 'admin', '2025-12-12 23:04:55', '', null, '');
INSERT INTO `sys_menu` VALUES ('2016', '存入订单修改', '2013', '3', '#', '', null, '1', '0', 'F', '0', '0', 'business:paymentRequest:edit', '#', 'admin', '2025-12-12 23:04:55', '', null, '');
INSERT INTO `sys_menu` VALUES ('2017', '存入订单删除', '2013', '4', '#', '', null, '1', '0', 'F', '0', '0', 'business:paymentRequest:remove', '#', 'admin', '2025-12-12 23:04:55', '', null, '');
INSERT INTO `sys_menu` VALUES ('2018', '存入订单导出', '2013', '5', '#', '', null, '1', '0', 'F', '0', '0', 'business:paymentRequest:export', '#', 'admin', '2025-12-12 23:04:55', '', null, '');
INSERT INTO `sys_menu` VALUES ('2019', '扫描订单', '2000', '1', 'scanOrder', 'business/scanOrder/index', null, '1', '0', 'C', '0', '0', 'business:scanOrder:list', '#', 'admin', '2025-12-12 23:05:15', '', null, '扫描订单菜单');
INSERT INTO `sys_menu` VALUES ('2020', '扫描订单查询', '2019', '1', '#', '', null, '1', '0', 'F', '0', '0', 'business:scanOrder:query', '#', 'admin', '2025-12-12 23:05:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2021', '扫描订单新增', '2019', '2', '#', '', null, '1', '0', 'F', '0', '0', 'business:scanOrder:add', '#', 'admin', '2025-12-12 23:05:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2022', '扫描订单修改', '2019', '3', '#', '', null, '1', '0', 'F', '0', '0', 'business:scanOrder:edit', '#', 'admin', '2025-12-12 23:05:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2023', '扫描订单删除', '2019', '4', '#', '', null, '1', '0', 'F', '0', '0', 'business:scanOrder:remove', '#', 'admin', '2025-12-12 23:05:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2024', '扫描订单导出', '2019', '5', '#', '', null, '1', '0', 'F', '0', '0', 'business:scanOrder:export', '#', 'admin', '2025-12-12 23:05:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2025', 'VIP管理', '2000', '1', 'vip', 'business/vip/index', null, '1', '0', 'C', '0', '0', 'business:vip:list', '#', 'admin', '2025-12-12 23:05:27', '', null, 'VIP管理菜单');
INSERT INTO `sys_menu` VALUES ('2026', 'VIP管理查询', '2025', '1', '#', '', null, '1', '0', 'F', '0', '0', 'business:vip:query', '#', 'admin', '2025-12-12 23:05:27', '', null, '');
INSERT INTO `sys_menu` VALUES ('2027', 'VIP管理新增', '2025', '2', '#', '', null, '1', '0', 'F', '0', '0', 'business:vip:add', '#', 'admin', '2025-12-12 23:05:27', '', null, '');
INSERT INTO `sys_menu` VALUES ('2028', 'VIP管理修改', '2025', '3', '#', '', null, '1', '0', 'F', '0', '0', 'business:vip:edit', '#', 'admin', '2025-12-12 23:05:27', '', null, '');
INSERT INTO `sys_menu` VALUES ('2029', 'VIP管理删除', '2025', '4', '#', '', null, '1', '0', 'F', '0', '0', 'business:vip:remove', '#', 'admin', '2025-12-12 23:05:27', '', null, '');
INSERT INTO `sys_menu` VALUES ('2030', 'VIP管理导出', '2025', '5', '#', '', null, '1', '0', 'F', '0', '0', 'business:vip:export', '#', 'admin', '2025-12-12 23:05:27', '', null, '');
INSERT INTO `sys_menu` VALUES ('2031', '提现订单', '2000', '1', 'withdrawRequest', 'business/withdrawRequest/index', null, '1', '0', 'C', '0', '0', 'business:withdrawRequest:list', '#', 'admin', '2025-12-12 23:05:40', '', null, '提现订单菜单');
INSERT INTO `sys_menu` VALUES ('2032', '提现订单查询', '2031', '1', '#', '', null, '1', '0', 'F', '0', '0', 'business:withdrawRequest:query', '#', 'admin', '2025-12-12 23:05:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2033', '提现订单新增', '2031', '2', '#', '', null, '1', '0', 'F', '0', '0', 'business:withdrawRequest:add', '#', 'admin', '2025-12-12 23:05:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2034', '提现订单修改', '2031', '3', '#', '', null, '1', '0', 'F', '0', '0', 'business:withdrawRequest:edit', '#', 'admin', '2025-12-12 23:05:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2035', '提现订单删除', '2031', '4', '#', '', null, '1', '0', 'F', '0', '0', 'business:withdrawRequest:remove', '#', 'admin', '2025-12-12 23:05:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2036', '提现订单导出', '2031', '5', '#', '', null, '1', '0', 'F', '0', '0', 'business:withdrawRequest:export', '#', 'admin', '2025-12-12 23:05:40', '', null, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
                              `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
                              `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
                              `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
                              `notice_content` longblob COMMENT '公告内容',
                              `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
                              `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                              PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '温馨提醒：2018-07-01 奈斯新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2025-12-08 18:37:13', '', null, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
                                `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                                `title` varchar(50) DEFAULT '' COMMENT '模块标题',
                                `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                                `method` varchar(100) DEFAULT '' COMMENT '方法名称',
                                `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
                                `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
                                `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
                                `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
                                `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
                                `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
                                `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
                                `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
                                `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
                                `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
                                `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
                                `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
                                `cost_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
                                PRIMARY KEY (`oper_id`),
                                KEY `idx_sys_oper_log_bt` (`business_type`),
                                KEY `idx_sys_oper_log_s` (`status`),
                                KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('100', '菜单管理', '3', 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', '1', 'admin', '研发部门', '/system/menu/4', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"菜单已分配,不允许删除\",\"code\":601}', '0', null, '2025-12-08 18:44:30', '17');
INSERT INTO `sys_oper_log` VALUES ('101', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.edit()', 'PUT', '1', 'admin', '研发部门', '/system/role', '127.0.0.1', '内网IP', '{\"admin\":false,\"createTime\":\"2025-12-08 18:37:12\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"deptCheckStrictly\":true,\"flag\":false,\"menuCheckStrictly\":true,\"menuIds\":[1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,501,1042,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,114,3,115,116,1055,1056,1057,1058,1059,1060,117],\"params\":{},\"remark\":\"普通角色\",\"roleId\":2,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"roleSort\":2,\"status\":\"0\",\"updateBy\":\"admin\"}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-08 18:44:37', '54');
INSERT INTO `sys_oper_log` VALUES ('102', '菜单管理', '3', 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', '1', 'admin', '研发部门', '/system/menu/4', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-08 18:44:47', '27');
INSERT INTO `sys_oper_log` VALUES ('103', '通知公告', '3', 'com.ruoyi.web.controller.system.SysNoticeController.remove()', 'DELETE', '1', 'admin', '研发部门', '/system/notice/2', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-08 18:44:56', '8');
INSERT INTO `sys_oper_log` VALUES ('104', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', '1', 'admin', '研发部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"example\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"业务管理\",\"menuType\":\"M\",\"orderNum\":3,\"params\":{},\"parentId\":0,\"path\":\"business\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-08 22:12:24', '19');
INSERT INTO `sys_oper_log` VALUES ('105', '代码生成', '6', 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', '1', 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_customer\"}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-08 22:15:54', '102');
INSERT INTO `sys_oper_log` VALUES ('106', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', '1', 'admin', '研发部门', '/tool/gen/synchDb/t_customer', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-08 22:19:53', '80');
INSERT INTO `sys_oper_log` VALUES ('107', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', '1', 'admin', '研发部门', '/tool/gen/synchDb/t_customer', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-08 22:20:10', '94');
INSERT INTO `sys_oper_log` VALUES ('108', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', '1', 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"customer\",\"className\":\"TCustomer\",\"columns\":[{\"capJavaField\":\"Id\",\"columnComment\":\"用户ID\",\"columnId\":1,\"columnName\":\"id\",\"columnType\":\"bigint(16)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-08 22:15:54\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2025-12-08 22:20:09\",\"usableColumn\":false},{\"capJavaField\":\"Username\",\"columnComment\":\"用户名\",\"columnId\":2,\"columnName\":\"username\",\"columnType\":\"varchar(32)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-08 22:15:54\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"username\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2025-12-08 22:20:09\",\"usableColumn\":false},{\"capJavaField\":\"Password\",\"columnComment\":\"密码\",\"columnId\":3,\"columnName\":\"password\",\"columnType\":\"varchar(64)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-08 22:15:54\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"password\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2025-12-08 22:20:09\",\"usableColumn\":false},{\"capJavaField\":\"InviteCode\",\"columnComment\":\"邀请码\",\"columnId\":4,\"columnName\":\"invite_code\",\"columnType\":\"varchar(8)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-08 22:15:54\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isE', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-08 22:23:24', '50');
INSERT INTO `sys_oper_log` VALUES ('109', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', '1', 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_customer\"}', null, '0', null, '2025-12-08 22:25:24', '259');
INSERT INTO `sys_oper_log` VALUES ('110', '代码生成', '6', 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', '1', 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"t_payment_request,t_withdraw_request,t_vip,t_scan_order,t_credit_log\"}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-12 22:15:13', '252');
INSERT INTO `sys_oper_log` VALUES ('111', '字典类型', '1', 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', '1', 'admin', '研发部门', '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"订单状态\",\"dictType\":\"order_status\",\"params\":{},\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-12 22:23:34', '15');
INSERT INTO `sys_oper_log` VALUES ('112', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', '1', 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"scanOrder\",\"className\":\"TScanOrder\",\"columns\":[{\"capJavaField\":\"OrderNo\",\"columnComment\":\"订单号\",\"columnId\":41,\"columnName\":\"order_no\",\"columnType\":\"varchar(32)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"orderNo\",\"javaType\":\"String\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":4,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CustomerId\",\"columnComment\":\"用户ID\",\"columnId\":42,\"columnName\":\"customer_id\",\"columnType\":\"bigint(16)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"customerId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":4,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Username\",\"columnComment\":\"用户名\",\"columnId\":43,\"columnName\":\"username\",\"columnType\":\"varchar(64)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"username\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":4,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Barcode\",\"columnComment\":\"条形码\",\"columnId\":44,\"columnName\":\"barcode\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-12 22:28:14', '85');
INSERT INTO `sys_oper_log` VALUES ('113', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', '1', 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"creditLog\",\"className\":\"TCreditLog\",\"columns\":[{\"capJavaField\":\"Id\",\"columnId\":17,\"columnName\":\"id\",\"columnType\":\"bigint(32)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CustomerId\",\"columnComment\":\"商户号\",\"columnId\":18,\"columnName\":\"customer_id\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"customerId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"OpearteType\",\"columnComment\":\"操作类型\",\"columnId\":19,\"columnName\":\"opearte_type\",\"columnType\":\"int(2)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"select\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"opearteType\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"OpearteAmount\",\"columnComment\":\"操作金额，可以为负数\",\"columnId\":20,\"columnName\":\"opearte_amount\",\"columnType\":\"decimal(10,4)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-12 22:29:06', '85');
INSERT INTO `sys_oper_log` VALUES ('114', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', '1', 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"paymentRequest\",\"className\":\"TPaymentRequest\",\"columns\":[{\"capJavaField\":\"RequestId\",\"columnId\":29,\"columnName\":\"request_id\",\"columnType\":\"varchar(32)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"requestId\",\"javaType\":\"String\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":3,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CustomerId\",\"columnComment\":\"商户号\",\"columnId\":30,\"columnName\":\"customer_id\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"customerId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":3,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Username\",\"columnComment\":\"商户名\",\"columnId\":31,\"columnName\":\"username\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"username\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":3,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Status\",\"columnComment\":\"支付状态\",\"columnId\":32,\"columnName\":\"status\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"order_status\",\"edit\":true,\"htmlType\":\"radio\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\"', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-12 22:30:00', '78');
INSERT INTO `sys_oper_log` VALUES ('115', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', '1', 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"creditLog\",\"className\":\"TCreditLog\",\"columns\":[{\"capJavaField\":\"Id\",\"columnId\":17,\"columnName\":\"id\",\"columnType\":\"bigint(32)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"updateTime\":\"2025-12-12 22:29:06\",\"usableColumn\":false},{\"capJavaField\":\"CustomerId\",\"columnComment\":\"商户号\",\"columnId\":18,\"columnName\":\"customer_id\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"customerId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"updateTime\":\"2025-12-12 22:29:06\",\"usableColumn\":false},{\"capJavaField\":\"OpearteType\",\"columnComment\":\"操作类型\",\"columnId\":19,\"columnName\":\"opearte_type\",\"columnType\":\"int(2)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"select\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"opearteType\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"updateTime\":\"2025-12-12 22:29:06\",\"usableColumn\":false},{\"capJavaField\":\"OpearteAmount\",\"columnComment\":\"操作金额，可以为负数\",\"columnId\":20,\"columnName\":\"opearte_amount\",\"columnType\":\"decimal(10,4)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"inser', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-12 22:30:07', '78');
INSERT INTO `sys_oper_log` VALUES ('116', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', '1', 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"vip\",\"className\":\"TVip\",\"columns\":[{\"capJavaField\":\"Id\",\"columnId\":48,\"columnName\":\"id\",\"columnType\":\"int(4)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Integer\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":5,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"ScanLimit\",\"columnComment\":\"扫码次数\",\"columnId\":49,\"columnName\":\"scan_limit\",\"columnType\":\"int(8)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"scanLimit\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":5,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"WithdrawLimit\",\"columnComment\":\"提现次数\",\"columnId\":50,\"columnName\":\"withdraw_limit\",\"columnType\":\"int(8)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"withdrawLimit\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":5,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"MinReward\",\"columnComment\":\"最小奖励\",\"columnId\":51,\"columnName\":\"min_reward\",\"columnType\":\"decimal(12,2)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"minRewar', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-12 22:55:29', '103');
INSERT INTO `sys_oper_log` VALUES ('117', '代码生成', '2', 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', '1', 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"withdrawRequest\",\"className\":\"TWithdrawRequest\",\"columns\":[{\"capJavaField\":\"WithdrawId\",\"columnId\":60,\"columnName\":\"withdraw_id\",\"columnType\":\"varchar(32)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"withdrawId\",\"javaType\":\"String\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":6,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"WithdrawAmount\",\"columnComment\":\"提现金额\",\"columnId\":61,\"columnName\":\"withdraw_amount\",\"columnType\":\"decimal(12,2)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"withdrawAmount\",\"javaType\":\"BigDecimal\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":6,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CustomerId\",\"columnComment\":\"商户号\",\"columnId\":62,\"columnName\":\"customer_id\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"customerId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":6,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Username\",\"columnId\":63,\"columnName\":\"username\",\"columnType\":\"varchar(64)\",\"createBy\":\"admin\",\"createTime\":\"2025-12-12 22:15:13\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":', '{\"msg\":\"操作成功\",\"code\":200}', '0', null, '2025-12-12 22:56:19', '58');
INSERT INTO `sys_oper_log` VALUES ('118', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', '1', 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"t_credit_log,t_payment_request,t_scan_order,t_vip,t_withdraw_request\"}', null, '0', null, '2025-12-12 22:56:27', '794');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
                            `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
                            `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
                            `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
                            `post_sort` int(4) NOT NULL COMMENT '显示顺序',
                            `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '0', 'admin', '2025-12-08 18:37:12', '', null, '');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', 'admin', '2025-12-08 18:37:12', '', null, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                            `role_name` varchar(30) NOT NULL COMMENT '角色名称',
                            `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
                            `role_sort` int(4) NOT NULL COMMENT '显示顺序',
                            `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
                            `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
                            `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
                            `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
                            `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '1', '1', '1', '1', '0', '0', 'admin', '2025-12-08 18:37:12', '', null, '超级管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '2', '1', '1', '0', '0', 'admin', '2025-12-08 18:37:12', 'admin', '2025-12-08 18:44:37', '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
                                 PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('2', '100');
INSERT INTO `sys_role_dept` VALUES ('2', '101');
INSERT INTO `sys_role_dept` VALUES ('2', '105');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                                 PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '100');
INSERT INTO `sys_role_menu` VALUES ('2', '101');
INSERT INTO `sys_role_menu` VALUES ('2', '102');
INSERT INTO `sys_role_menu` VALUES ('2', '103');
INSERT INTO `sys_role_menu` VALUES ('2', '104');
INSERT INTO `sys_role_menu` VALUES ('2', '105');
INSERT INTO `sys_role_menu` VALUES ('2', '106');
INSERT INTO `sys_role_menu` VALUES ('2', '107');
INSERT INTO `sys_role_menu` VALUES ('2', '108');
INSERT INTO `sys_role_menu` VALUES ('2', '109');
INSERT INTO `sys_role_menu` VALUES ('2', '110');
INSERT INTO `sys_role_menu` VALUES ('2', '111');
INSERT INTO `sys_role_menu` VALUES ('2', '112');
INSERT INTO `sys_role_menu` VALUES ('2', '113');
INSERT INTO `sys_role_menu` VALUES ('2', '114');
INSERT INTO `sys_role_menu` VALUES ('2', '115');
INSERT INTO `sys_role_menu` VALUES ('2', '116');
INSERT INTO `sys_role_menu` VALUES ('2', '117');
INSERT INTO `sys_role_menu` VALUES ('2', '500');
INSERT INTO `sys_role_menu` VALUES ('2', '501');
INSERT INTO `sys_role_menu` VALUES ('2', '1000');
INSERT INTO `sys_role_menu` VALUES ('2', '1001');
INSERT INTO `sys_role_menu` VALUES ('2', '1002');
INSERT INTO `sys_role_menu` VALUES ('2', '1003');
INSERT INTO `sys_role_menu` VALUES ('2', '1004');
INSERT INTO `sys_role_menu` VALUES ('2', '1005');
INSERT INTO `sys_role_menu` VALUES ('2', '1006');
INSERT INTO `sys_role_menu` VALUES ('2', '1007');
INSERT INTO `sys_role_menu` VALUES ('2', '1008');
INSERT INTO `sys_role_menu` VALUES ('2', '1009');
INSERT INTO `sys_role_menu` VALUES ('2', '1010');
INSERT INTO `sys_role_menu` VALUES ('2', '1011');
INSERT INTO `sys_role_menu` VALUES ('2', '1012');
INSERT INTO `sys_role_menu` VALUES ('2', '1013');
INSERT INTO `sys_role_menu` VALUES ('2', '1014');
INSERT INTO `sys_role_menu` VALUES ('2', '1015');
INSERT INTO `sys_role_menu` VALUES ('2', '1016');
INSERT INTO `sys_role_menu` VALUES ('2', '1017');
INSERT INTO `sys_role_menu` VALUES ('2', '1018');
INSERT INTO `sys_role_menu` VALUES ('2', '1019');
INSERT INTO `sys_role_menu` VALUES ('2', '1020');
INSERT INTO `sys_role_menu` VALUES ('2', '1021');
INSERT INTO `sys_role_menu` VALUES ('2', '1022');
INSERT INTO `sys_role_menu` VALUES ('2', '1023');
INSERT INTO `sys_role_menu` VALUES ('2', '1024');
INSERT INTO `sys_role_menu` VALUES ('2', '1025');
INSERT INTO `sys_role_menu` VALUES ('2', '1026');
INSERT INTO `sys_role_menu` VALUES ('2', '1027');
INSERT INTO `sys_role_menu` VALUES ('2', '1028');
INSERT INTO `sys_role_menu` VALUES ('2', '1029');
INSERT INTO `sys_role_menu` VALUES ('2', '1030');
INSERT INTO `sys_role_menu` VALUES ('2', '1031');
INSERT INTO `sys_role_menu` VALUES ('2', '1032');
INSERT INTO `sys_role_menu` VALUES ('2', '1033');
INSERT INTO `sys_role_menu` VALUES ('2', '1034');
INSERT INTO `sys_role_menu` VALUES ('2', '1035');
INSERT INTO `sys_role_menu` VALUES ('2', '1036');
INSERT INTO `sys_role_menu` VALUES ('2', '1037');
INSERT INTO `sys_role_menu` VALUES ('2', '1038');
INSERT INTO `sys_role_menu` VALUES ('2', '1039');
INSERT INTO `sys_role_menu` VALUES ('2', '1040');
INSERT INTO `sys_role_menu` VALUES ('2', '1041');
INSERT INTO `sys_role_menu` VALUES ('2', '1042');
INSERT INTO `sys_role_menu` VALUES ('2', '1043');
INSERT INTO `sys_role_menu` VALUES ('2', '1044');
INSERT INTO `sys_role_menu` VALUES ('2', '1045');
INSERT INTO `sys_role_menu` VALUES ('2', '1046');
INSERT INTO `sys_role_menu` VALUES ('2', '1047');
INSERT INTO `sys_role_menu` VALUES ('2', '1048');
INSERT INTO `sys_role_menu` VALUES ('2', '1049');
INSERT INTO `sys_role_menu` VALUES ('2', '1050');
INSERT INTO `sys_role_menu` VALUES ('2', '1051');
INSERT INTO `sys_role_menu` VALUES ('2', '1052');
INSERT INTO `sys_role_menu` VALUES ('2', '1053');
INSERT INTO `sys_role_menu` VALUES ('2', '1054');
INSERT INTO `sys_role_menu` VALUES ('2', '1055');
INSERT INTO `sys_role_menu` VALUES ('2', '1056');
INSERT INTO `sys_role_menu` VALUES ('2', '1057');
INSERT INTO `sys_role_menu` VALUES ('2', '1058');
INSERT INTO `sys_role_menu` VALUES ('2', '1059');
INSERT INTO `sys_role_menu` VALUES ('2', '1060');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
                            `user_name` varchar(30) NOT NULL COMMENT '用户账号',
                            `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
                            `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
                            `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
                            `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
                            `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
                            `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
                            `password` varchar(100) DEFAULT '' COMMENT '密码',
                            `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                            `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                            `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
                            `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            `google_code` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '谷歌验证code',
                            `safe_mode` tinyint(1) DEFAULT '0' COMMENT '安全模式 0-无 1-有',
                            `ip_address` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '登录IP地址，如果不是这个IP不允许登录',
                            PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', '奈斯', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2025-12-12 23:51:24', 'admin', '2025-12-08 18:37:12', '', '2025-12-12 23:51:24', '管理员', null, '0', null);
INSERT INTO `sys_user` VALUES ('2', '105', 'ry', '奈斯', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2025-12-08 18:37:12', 'admin', '2025-12-08 18:37:12', '', null, '测试员', null, '0', null);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
                                 PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1');
INSERT INTO `sys_user_post` VALUES ('2', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');

-- ----------------------------
-- Table structure for t_credit_log
-- ----------------------------
DROP TABLE IF EXISTS `t_credit_log`;
CREATE TABLE `t_credit_log` (
                                `id` bigint(32) NOT NULL AUTO_INCREMENT,
                                `customer_id` bigint(20) DEFAULT NULL COMMENT '商户号',
                                `opearte_type` int(2) DEFAULT NULL COMMENT '操作类型',
                                `opearte_amount` decimal(10,4) DEFAULT NULL COMMENT '操作金额，可以为负数',
                                `pre_balance` decimal(10,4) DEFAULT NULL COMMENT '操作前金额',
                                `post_balance` decimal(10,4) DEFAULT NULL COMMENT '操作后金额',
                                `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                                `ref_id` varchar(32) DEFAULT NULL COMMENT '关联ID',
                                `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000190573921591298 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_credit_log
-- ----------------------------
INSERT INTO `t_credit_log` VALUES ('2000179330775195650', '2', '6', '4.7025', '0.0000', '4.7025', '2025-12-14 20:21:29', 'SYSTEM', '2025-12-14 20:21:28', null, 'SCAN1207058986629677058', '扫码奖励');
INSERT INTO `t_credit_log` VALUES ('2000181597175750658', '2', '6', '1.1584', '4.7000', '5.8584', '2025-12-14 20:30:29', 'SYSTEM', '2025-12-14 20:30:29', null, 'SCAN1207061253663571968', '扫码奖励');
INSERT INTO `t_credit_log` VALUES ('2000190573921591297', '2', '6', '0.7883', '5.8600', '6.6483', '2025-12-14 21:06:09', 'SYSTEM', '2025-12-14 21:06:09', null, 'SCAN1207070229922922497', '扫码奖励');

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
                              `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                              `username` varchar(32) NOT NULL COMMENT '用户名',
                              `password` varchar(64) NOT NULL COMMENT '密码',
                              `invite_code` varchar(12) NOT NULL COMMENT '邀请码',
                              `path` varchar(255) DEFAULT NULL COMMENT 'path',
                              `p_id` bigint(16) DEFAULT NULL COMMENT '父级ID',
                              `balance` decimal(16,2) DEFAULT '0.00' COMMENT '余额',
                              `lock_balance` decimal(16,2) DEFAULT '0.00' COMMENT '冻结余额',
                              `grade` tinyint(1) DEFAULT '1' COMMENT 'VIP等级',
                              `withdraw_password` varchar(126) DEFAULT NULL COMMENT '提款密码',
                              `last_login_address` varchar(64) DEFAULT NULL COMMENT '最后登录IP',
                              `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
                              `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `status` tinyint(1) DEFAULT '0' COMMENT '0正常 1启用',
                              `realname_status` tinyint(1) DEFAULT '0' COMMENT '实名认证状态：0-未认证，1-待审核，2-已认证',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `invite_code` (`invite_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', 'test123456', '$2a$10$BQd6jNWCnCBpvzY4nSs08u07i8/UJOTe4PUsKZ0mKspCER9qqktjy', '1R2Hw78xtM8', null, null, '0.00', '0.00', '1', null, null, null, '2025-12-09 23:55:01', '2025-12-09 23:55:00', '0');
INSERT INTO `t_customer` VALUES ('2', 'test8899', '$2a$10$RxpoWUPDsPmPJUgzkGoV5.X6WFwxfChPwoKaUCo7Kf918SpccIejm', '1R3mtpDMGcD', null, '1', '6.65', '0.00', '1', null, null, null, '2025-12-10 21:34:58', '2025-12-14 21:06:09', '0');

-- ----------------------------
-- Table structure for t_payment_request
-- ----------------------------
DROP TABLE IF EXISTS `t_payment_request`;
CREATE TABLE `t_payment_request` (
                                     `request_id` varchar(32) NOT NULL,
                                     `customer_id` bigint(20) NOT NULL COMMENT '商户号',
                                     `username` varchar(255) DEFAULT NULL COMMENT '商户名',
                                     `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付状态',
                                     `order_amount` decimal(12,2) NOT NULL COMMENT '订单金额',
                                     `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                     `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_withdraw_request
-- ----------------------------
DROP TABLE IF EXISTS `t_withdraw_request`;
CREATE TABLE `t_withdraw_request` (
  `withdraw_id` varchar(32) NOT NULL,
  `withdraw_amount` decimal(12,2) DEFAULT NULL COMMENT '提现金额',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '商户号',
  `username` varchar(64) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `real_amount` decimal(12,2) DEFAULT NULL COMMENT '真实金额',
  `remark` varchar(128) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`withdraw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_withdraw_request
-- ----------------------------

-- ----------------------------
-- Table structure for t_realname_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_realname_auth`;
CREATE TABLE `t_realname_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '商户ID',
  `real_name` varchar(50) NOT NULL COMMENT '真实姓名',
  `id_card_front` varchar(255) NOT NULL COMMENT '身份证正面图片URL',
  `id_card_back` varchar(255) NOT NULL COMMENT '身份证反面图片URL',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：0-待审核，1-审核通过，2-审核拒绝',
  `audit_reason` varchar(255) DEFAULT NULL COMMENT '审核原因',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `audit_by` varchar(32) DEFAULT NULL COMMENT '审核人',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_customer_id` (`customer_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实名认证表';

-- ----------------------------
-- Table structure for t_merchant_message
-- ----------------------------
DROP TABLE IF EXISTS `t_merchant_message`;
CREATE TABLE `t_merchant_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) NOT NULL COMMENT '消息标题',
  `content` text NOT NULL COMMENT '消息内容',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '商户ID，NULL表示发送给所有商户',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：0-未读，1-已读',
  `type` tinyint(1) DEFAULT '0' COMMENT '消息类型：0-普通消息，1-奖励通知，2-系统通知',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `read_time` timestamp NULL DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户消息表';

-- ----------------------------
-- Table structure for t_scan_order
DROP TABLE IF EXISTS `t_scan_order`;
CREATE TABLE `t_scan_order` (
                                `order_no` varchar(32) NOT NULL COMMENT '订单号',
                                `customer_id` bigint(16) DEFAULT NULL COMMENT '用户ID',
                                `username` varchar(64) DEFAULT NULL COMMENT '用户名',
                                `barcode` varchar(255) DEFAULT NULL COMMENT '条形码',
                                `reward_amount` decimal(10,2) DEFAULT NULL,
                                `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                `status` tinyint(1) DEFAULT '0' COMMENT '0正常 1启用',
                                PRIMARY KEY (`order_no`),
                                UNIQUE KEY `barcode` (`barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_scan_order
-- ----------------------------
INSERT INTO `t_scan_order` VALUES ('6901028184243', '2', 'test8899', '6901028184243', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184244', '2', 'test8899', '6901028184244', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184245', '2', 'test8899', '6901028184245', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184246', '2', 'test8899', '6901028184246', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184247', '2', 'test8899', '6901028184247', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184248', '2', 'test8899', '6901028184248', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184249', '2', 'test8899', '6901028184249', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184250', '2', 'test8899', '6901028184250', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184251', '2', 'test8899', '6901028184251', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184252', '2', 'test8899', '6901028184252', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184253', '2', 'test8899', '6901028184253', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184254', '2', 'test8899', '6901028184254', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184255', '2', 'test8899', '6901028184255', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184256', '2', 'test8899', '6901028184256', '0.79', '2025-12-14 21:06:09', '2');
INSERT INTO `t_scan_order` VALUES ('6901028184257', '2', 'test8899', '6901028184257', '0.79', '2025-12-14 21:06:09', '2');

-- ----------------------------
-- Table structure for t_vip
-- ----------------------------
DROP TABLE IF EXISTS `t_vip`;
CREATE TABLE `t_vip` (
                         `id` int(4) NOT NULL AUTO_INCREMENT,
                         `scan_limit` int(8) DEFAULT '0' COMMENT '扫码次数',
                         `withdraw_limit` int(8) DEFAULT '0' COMMENT '提现次数',
                         `min_reward` decimal(12,2) DEFAULT '0.00' COMMENT '最小奖励',
                         `max_reward` decimal(12,2) DEFAULT NULL COMMENT '最大奖励',
                         `share_count` int(8) DEFAULT NULL COMMENT '分享奖励扫码次数',
                         `share_reward` decimal(12,2) DEFAULT NULL COMMENT '分享奖励',
                         `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                         `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                         `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vip
-- ----------------------------
INSERT INTO `t_vip` VALUES ('1', '3', '1', '0.01', '10.00', '1', '0.00', '2025-12-12 23:52:18', null, '2025-12-12 23:52:18', null, null);
INSERT INTO `t_vip` VALUES ('2', '5', '1', '0.10', '13.00', '1', '0.00', '2025-12-12 23:52:18', '', '2025-12-12 23:52:18', '', '');
INSERT INTO `t_vip` VALUES ('3', '8', '1', '0.50', '18.00', '1', '0.00', '2025-12-12 23:52:18', '', '2025-12-12 23:52:18', '', '');

-- ----------------------------
-- Table structure for t_withdraw_request
-- ----------------------------
DROP TABLE IF EXISTS `t_withdraw_request`;
CREATE TABLE `t_withdraw_request` (
                                      `withdraw_id` varchar(32) NOT NULL,
                                      `withdraw_amount` decimal(12,2) DEFAULT NULL COMMENT '提现金额',
                                      `customer_id` bigint(20) DEFAULT NULL COMMENT '商户号',
                                      `username` varchar(64) DEFAULT NULL,
                                      `status` tinyint(1) DEFAULT '0' COMMENT '状态',
                                      `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                                      `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
                                      `real_amount` decimal(12,2) DEFAULT NULL COMMENT '真实金额',
                                      `remark` varchar(128) DEFAULT '' COMMENT '备注',
                                      PRIMARY KEY (`withdraw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_withdraw_request
-- ----------------------------
