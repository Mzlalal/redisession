package mzlalal.redisession.redisessioncore.serviceImpl;

import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.entity.user.AuthUserVO;
import mzlalal.redisession.redisessionapi.service.AuthUserService;

/**
 * 用户服务实现类
 */
public class AuthUserServiceImpl implements AuthUserService {
    /**
     * 根据用户 ID 查找用户 VO 对象
     *
     * @param id
     * @return
     */
    @Override
    public AuthUserVO findAuthUserVOById(long id) {
        return null;
    }

    /**
     * 根据用户 ID 查找用户 VO 对象
     *
     * @param id
     * @return
     */
    @Override
    public AuthUserDTO findAuthUserDTOById(long id) {
        return null;
    }
}
