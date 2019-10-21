package mzlalal.redisession.redisessioncosumer.listener;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.listener.BaseRedisKeyExpirationListener;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 默认监听所有db的过期事件 __keyevent@*__:expired
 * 这里有一个bug 也不知道是不是...
 * __keysevent@*__:expired 在博客以及redis配置文件中都是带有s的
 * 在spring中是不带有s的 导致更改*为实际使用数据库时 是无法生效的
 * @author Mzlalal
 */
@Slf4j
public class ConsumerRedisKeyExpirationListener extends BaseRedisKeyExpirationListener {

    public ConsumerRedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * redis.conf 设置 notify-keyspace-events "Ex"
     *
     * @param message
     * @param pattern
     */
    @Override
    public synchronized void onMessage(Message message, byte[] pattern) {
        //  message.toString()可以获取失效的key名
        String expiredKey = message.toString();
        log.info("redisession-consumer : redis失效key通知:expiredKey={}", expiredKey);

//        // 做自己的业务处理即可
//        if (expiredKey.startsWith("xxx")) {
//
//        }
    }
}
