package mzlalal.redisession.redisessioncosumer.config;

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
    @Bean(name = "restTemplate")
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
