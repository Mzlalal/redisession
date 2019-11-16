package mzlalal.redisession.redisessionjwt.config;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
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
 * 注解 LoadBalanced 在dubbo中被再次配置 导致空指针 详细原因待解决
 * 在dubbo 源码 DubboLoadBalancedRestTemplateAutoConfiguration  可以看出需要添加 DubboTransported 强化一下RestTemplate
 * @author:       Mzlalal
 * @date:         2019年10月11日 17:22:53
 * @version:      2.0
 */
@Configuration
public class RedisessionJwtConfig implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean(value = JwtTokenUtil.class)
    public JwtTokenUtil createJwtTokenUtil(){
        return new JwtTokenUtil();
    }

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
