package mzlalal.redisession.redisessioncosumer.config;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.config.BaseRedisConfig;
import mzlalal.redisession.listener.BaseRedisKeyExpirationListener;
import mzlalal.redisession.redisessioncosumer.listener.ConsumerRedisKeyExpirationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @description:  redis 初始化配置
 * @author:       Mzlalal
 */
@Slf4j
@Configuration
public class RedisConfig extends BaseRedisConfig {

    /**
     * 创建 redis 键过期事件
     * 如果想对键过期后进行业务处理
     * 创建一个类BaseRedisKeyExpirationListener 并且重写这个方法返回新的类
     *
     * @param container RedisMessageListenerContainer redis信息键过期时间
     * @return BaseRedisKeyExpirationListener
     */
    @Override
    public BaseRedisKeyExpirationListener createRedisKeyExpirationListener(RedisMessageListenerContainer container) {
        return new ConsumerRedisKeyExpirationListener(container);
    }
}