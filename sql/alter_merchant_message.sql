-- 添加已读商户ID字段到商户消息表
ALTER TABLE t_merchant_message ADD COLUMN read_merchant_ids TEXT COMMENT '已读商户ID列表，以逗号分隔';

-- 可选：移除原有的status字段（如果确定不再需要）
-- 注意：如果要保留向后兼容性，可以先不执行这一步
-- ALTER TABLE t_merchant_message DROP COLUMN status;