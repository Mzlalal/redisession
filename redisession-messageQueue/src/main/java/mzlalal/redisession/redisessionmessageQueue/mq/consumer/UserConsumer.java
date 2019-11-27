package mzlalal.redisession.redisessionmessageQueue.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.entity.user.AuthUserDTO;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @description:
 * 主题 消费分组在cluster情况下不会重复消费 而为负载均衡
 * 在广播模式下为竞争消费
 * @author: Mzlalal
 * @date: 2019/11/23 21:16
 * @version: 1.0
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "user-login", consumerGroup = "user-information")
public class UserConsumer implements RocketMQListener<AuthUserDTO> {
    @Override
    public void onMessage(AuthUserDTO message) {
        log.info("消息队列:user-login user-information: {}", message);
    }
}
