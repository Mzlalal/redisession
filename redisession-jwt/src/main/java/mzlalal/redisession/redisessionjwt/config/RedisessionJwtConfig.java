package mzlalal.redisession.redisessionjwt.config;

import mzlalal.redisession.redisessionjwt.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:  mvc 拦截器配置
 * @author:       Mzlalal
 * @date:         2019年10月11日 17:22:53
 * @version:      1.0
 */
@Configuration
public class RedisessionJwtConfig implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截所有请求，通过判断是否有 @Token 注解 决定是否需要登录
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**");
    }
}
