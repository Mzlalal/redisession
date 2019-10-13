package mzlalal.redisession.redisessioncore.config;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.config.BaseRedisConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @description:  redis 初始化配置
 * @author:       Mzlalal
 */
@Slf4j
@Configuration
public class RedisConfig extends BaseRedisConfig {
    /**
     * redis信息监听器容器
     */
//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
//        log.info("***************************redis监听器初始化***************************");
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(new RedisKeyExpirationListener(container), new PatternTopic(redisTopic));
//        log.info("redis监听键事件主题为:{}", redisTopic);
//        log.info("***************************redis监听器初始化完毕***************************");
//        return container;
//    }
}