package com.ruoyi.quartz.task;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask {
    @Autowired
    private RedisCache redisCache;

    private Random random = new Random();

    /**
     * 定时增加奖池和奖励数据
     * 每分钟执行一次，随机增加1-10
     */
    public void increasePoolAndRewards() {
        // 增加奖池金额
        increaseValue(Constants.POOL_KEY, new BigDecimal("16000"));

        // 增加奖励金额
        increaseValue(Constants.REWARDS_KEY, new BigDecimal("3600"));
    }

    /**
     * 增加Redis中的数值
     *
     * @param key          Redis键
     * @param initialValue 初始值（当缓存中没有值时使用）
     */
    private void increaseValue(String key, BigDecimal initialValue) {
        // 获取当前值
        String currentValueStr = redisCache.getCacheObject(key);
        BigDecimal currentValue;

        if (currentValueStr == null || currentValueStr.trim().isEmpty()) {
            // 如果缓存中没有值，设置初始值
            currentValue = initialValue;
            redisCache.setCacheObject(key, currentValue.toString());
        } else {
            // 解析当前值
            try {
                currentValue = new BigDecimal(currentValueStr);
            } catch (NumberFormatException e) {
                // 如果解析失败，使用初始值
                currentValue = initialValue;
            }

            // 随机增加1-10
            int randomIncrease = random.nextInt(10) + 1; // 1-10的随机数
            BigDecimal increaseAmount = new BigDecimal(randomIncrease);
            BigDecimal newValue = currentValue.add(increaseAmount);

            // 更新Redis中的值
            redisCache.setCacheObject(key, newValue.toString());
        }
    }

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法");
    }
}



