package mzlalal.redisession.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import mzlalal.redisession.utils.redis.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

/**
 * @description: redis 初始化配置
 * @author: Mzlalal
 */
public class BaseRedisConfig {

    /**
     * 实例化 StringRedisTemplate 对象
     *
     * @return
     */
    @Bean
    public StringRedisTemplate StringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // 设置数据存入 redis 的序列化方式,并开启事务
        // 如果不配置Serializer，那么存储的时候缺省使用String
        // 如果用User类型存储，那么会提示错误User can't cast to String！
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer(Charset.forName("GBK"));
        FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        // 设置对象映射
        fastJson2JsonRedisSerializer.setObjectMapper(om);

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer);

        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 注入封装RedisTemplate
     *
     * @return RedisUtil
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

}