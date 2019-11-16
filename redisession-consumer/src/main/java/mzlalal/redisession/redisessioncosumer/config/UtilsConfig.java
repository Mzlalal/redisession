package mzlalal.redisession.redisessioncosumer.config;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: Mzlalal
 * @date: 2019/9/28 13:58
 * @version: 1.0
 */
@Configuration
public class UtilsConfig {
    /**
     * 注入封装 restTemplate
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    @DubboTransported
    @ConditionalOnMissingBean(value = RestTemplate.class)
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
