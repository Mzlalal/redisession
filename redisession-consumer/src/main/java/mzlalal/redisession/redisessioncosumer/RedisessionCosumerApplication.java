package mzlalal.redisession.redisessioncosumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"mzlalal.redisession.redisessioncosumer"})
public class RedisessionCosumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisessionCosumerApplication.class, args);
    }

}
