package mzlalal.redisession.redisessioncore.serviceImpl;

import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.entity.user.AuthUserVO;
import mzlalal.redisession.redisessionapi.service.AuthUserService;
import org.apache.dubbo.config.annotation.Service;


/**
 * 用户服务实现类
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {
    /**
     * 根据用户 ID 查找用户 VO 对象
     *
     * @param id
     * @return
     */
    @Override
    public AuthUserVO findAuthUserVOById(long id) {
        AuthUserVO vo = new AuthUserVO();
        vo.setUserName("success");
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
        dto.setUserName("success");
        return dto;
    }
}
