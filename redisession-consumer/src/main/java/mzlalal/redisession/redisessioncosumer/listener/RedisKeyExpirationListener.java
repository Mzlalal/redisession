package mzlalal.redisession.redisessioncosumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 默认监听所有db的过期事件 __keysevent@*__:expired
 * @author Mzlalal
 */
@Slf4j
//@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
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
        log.info("redis失效key通知:expiredKey={}", expiredKey);

//        // 做自己的业务处理即可
//        if (expiredKey.startsWith("xxx")) {
//
//        }
    }
}
