package mzlalal.redisession.redisessionapi.hystrix;

import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.entity.user.AuthUserVO;
import mzlalal.redisession.redisessionapi.service.AuthUserService;

/**
 * @description: 用户服务熔断器
 * @author: Mzlalal
 * @date: 2019/10/15 17:42
 * @version: 1.0
 */
public class AuthUserServiceHystrix implements AuthUserService {
    /**
     * 根据用户 ID 查找用户 VO 对象
     *
     * @param id
     * @return
     */
    @Override
    public AuthUserVO findAuthUserVOById(long id) {
        AuthUserVO vo = new AuthUserVO();
        vo.setUserName("error");
        return vo;
    }

    /**
     * 根据用户 ID 查找用户 VO 对象
     *
     * @param id
     * @return
     */
    @Override
    public AuthUserDTO findAuthUserDTOById(long id) {
        AuthUserDTO dto = new AuthUserDTO();
        dto.setUserName("error");
        return dto;
    }
}
