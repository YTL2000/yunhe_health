package cn.yunhe.util;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis工具类
 * @version 1.0
 * @auther YTL
 * @className RedisUtil
 * since 1.0
 */
public class RedisUtil {
    //删除缓存
    public static void del(RedisTemplate redisTemplate, String key){
        if (redisTemplate.hasKey(key)){
            redisTemplate.delete(key);
        }
    }
}
