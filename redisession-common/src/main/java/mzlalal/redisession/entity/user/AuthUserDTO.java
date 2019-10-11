package mzlalal.redisession.entity.user;

import lombok.Data;

/**
 * @description:
 * @author: Mzlalal
 * @date: 2019/10/11 17:01
 * @version: 1.0
 */
@Data
public class AuthUserDTO extends AuthUserVO {

    /**
     * 用户密码
     */
    protected String password;

    @Override

    public String toString() {
        return "AuthUserDTO{" +
                "password='" + password + '\'' +
                '}';
    }
}
