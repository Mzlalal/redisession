package mzlalal.redisession.redisessioncosumer.config;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.config.BaseRedisConfig;
import mzlalal.redisession.redisessioncosumer.listener.RedisKeyExpirationListener;
import mzlalal.redisession.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description:  redis 初始化配置
 * @author:       Mzlalal
 */
@Slf4j
@Configuration
public class RedisConfig extends BaseRedisConfig {

    @Value("${spring.redis.topic}")
    private String redisTopic;

    /**
     * redis信息监听器容器
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        log.info("***************************redis监听器初始化***************************");
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new RedisKeyExpirationListener(container), new PatternTopic(redisTopic));
        log.info("redis监听键事件主题为:{}", redisTopic);
        log.info("***************************redis监听器初始化完毕***************************");
        return container;
    }
}