package mzlalal.redisession.redisessionjwt.config;

import mzlalal.redisession.redisessionjwt.interceptor.JwtInterceptor;
import mzlalal.redisession.redisessionjwt.utils.JwtTokenUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description:  mvc 拦截器配置
 * @author:       Mzlalal
 * @date:         2019年10月11日 17:22:53
 * @version:      1.0
 */
public class RedisessionJwtConfig extends WebMvcConfigurationSupport {

    @Bean
    public JwtTokenUtil createJwtTokenUtil(){
        return new JwtTokenUtil();
    }

    /**
     * 注入封装 restTemplate
     * @return RestTemplate
     */
    @LoadBalanced
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截所有请求，通过判断是否有 @Token 注解 决定是否需要登录
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**");
    }
}
