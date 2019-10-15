package mzlalal.redisession.redisessionjwt.config;

import mzlalal.redisession.redisessionjwt.interceptor.JwtInterceptor;
import mzlalal.redisession.redisessionjwt.utils.JwtTokenUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:  mvc 拦截器配置
 * 特么的 最好不要继承WebMvcConfigurationSupport 这玩意单继承有默认实现 很容易出现html加载404
 * @author:       Mzlalal
 * @date:         2019年10月11日 17:22:53
 * @version:      2.0
 */
@Configuration
public class RedisessionJwtConfig implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean(JwtTokenUtil.class)
    public JwtTokenUtil createJwtTokenUtil(){
        return new JwtTokenUtil();
    }

    /**
     * 注入封装 restTemplate
     * @return RestTemplate
     */
    @LoadBalanced
    @Bean(name = "restTemplate")
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "jwtInterceptor")
    public JwtInterceptor createJwtInterceptor() {
        createRestTemplate();
        createJwtTokenUtil();
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截所有请求，通过判断是否有 @Token 注解 决定是否需要登录
        registry.addInterceptor(createJwtInterceptor())
                .addPathPatterns("/**");
    }
}
