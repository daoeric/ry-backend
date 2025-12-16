ALTER TABLE `t_customer`
    ADD COLUMN `realname_status`  tinyint(1) NULL DEFAULT 0 COMMENT '实名认证状态：0-未认证，1-待审核，2-已认证' AFTER `status`;
