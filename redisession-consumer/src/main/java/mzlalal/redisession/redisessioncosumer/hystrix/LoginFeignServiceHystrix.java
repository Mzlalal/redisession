package mzlalal.redisession.redisessioncosumer.hystrix;

import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.redisessioncosumer.feign.LoginFeignService;

/**
 * @description: 用户服务熔断器
 * @author: Mzlalal
 * @date: 2019/10/15 17:42
 * @version: 1.0
 */
public class LoginFeignServiceHystrix implements LoginFeignService {

    /**
     * 验证用户是否登录
     *
     * @return
     */
    @Override
    public AuthUserDTO findAuthUser() {
        AuthUserDTO dto = new AuthUserDTO();
        dto.setUserName("error");
        return dto;
    }
}
