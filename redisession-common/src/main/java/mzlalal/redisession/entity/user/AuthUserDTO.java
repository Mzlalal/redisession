package mzlalal.redisession.entity.user;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "用户密码", name = "password", example = "xxx")
    protected String password;

    @Override

    public String toString() {
        return "AuthUserDTO{" +
                "password='" + password + '\'' +
                '}';
    }
}
