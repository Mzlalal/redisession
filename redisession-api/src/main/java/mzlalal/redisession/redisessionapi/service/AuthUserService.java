package mzlalal.redisession.redisessionapi.service;

import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.entity.user.AuthUserVO;
import mzlalal.redisession.redisessionapi.hystrix.AuthUserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: 用户服务
 * @author: Mzlalal
 * @date: 2019/10/11 16:59
 * @version: 1.0
 */
@FeignClient(name = "authUserService", fallback = AuthUserServiceHystrix.class)
public interface AuthUserService {

    /**
     * 根据用户 ID 查找用户 VO 对象
     *
     * @param id
     * @return
     */
    AuthUserVO findAuthUserVOById(long id);

    /**
     * 根据用户 ID 查找用户 VO 对象
     *
     * @param id
     * @return
     */
    AuthUserDTO findAuthUserDTOById(long id);
}
