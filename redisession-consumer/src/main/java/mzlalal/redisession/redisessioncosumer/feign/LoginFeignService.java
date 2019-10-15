package mzlalal.redisession.redisessioncosumer.feign;

import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.redisessioncosumer.hystrix.LoginFeignServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * core 服务中 controller Login 根据 feign 调用
 * name value 属性会作为微服务的名称，用于服务发现
 * path 统一添加前缀 和在controller类上添加requestMapping 一样
 * fallback 回调 熔断器
 */
@FeignClient(value = "redisession-core-provider", path = "sso", fallback = LoginFeignServiceHystrix.class)
public interface LoginFeignService {

    /**
     * 验证用户是否登录
     * @return
     */
    @RequestMapping("testFeign")
    AuthUserDTO findAuthUser();
}
