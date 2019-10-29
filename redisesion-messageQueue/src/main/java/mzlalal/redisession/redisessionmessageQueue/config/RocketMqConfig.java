package mzlalal.redisession.redisessionmessageQueue.config;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.redisessionmessageQueue.constants.RocketMQErrorEnum;
import mzlalal.redisession.redisessionmessageQueue.exception.RocketMQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootConfiguration
public class RocketMqConfig {
    /**
     * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置,
     * rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
     */
    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.nameServerAddr}")
    private String nameServerAddr;
    /**
     * 消息最大大小，默认4M
     */
    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize;
    /**
     * 消息发送超时时间，默认3秒
     */
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;
    /**
     * 消息发送失败重试次数，默认2次
     */
    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;

    @Bean
    public DefaultMQProducer getRocketMQProducer() throws RocketMQException {
        if (StringUtils.isEmpty(this.groupName)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "groupName为空", false);
        }
        if (StringUtils.isEmpty(this.nameServerAddr)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "nameServerAddr为空", false);
        }
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.nameServerAddr);
        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        //producer.setInstanceName(instanceName);
        if (this.maxMessageSize != null) {
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        if (this.sendMsgTimeout != null) {
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if (this.retryTimesWhenSendFailed != null) {
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }

        try {
            producer.start();

            log.info("producer 已启动! groupName:{},nameServerAddr:{}", this.groupName, this.nameServerAddr);
        } catch (MQClientException e) {
            log.error("producer 启动出现了错误: {}", e.getMessage(), e);
            throw new RocketMQException(e);
        }
        return producer;
    }
}
