package mzlalal.redisession.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @description: 用户数据传输对象
 * @author: Mzlalal
 * @date: 2019/10/11 17:01
 * @version: 1.0
 */
@Data
@ApiModel(value = "AuthUserDTO", description = "用户数据传输对象")
public class AuthUserDTO implements Serializable {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", name = "id", example = "0")
    protected long id;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", name = "userName", example = "Mz")
    protected String userName;
    /**
     * token
     */
    @ApiModelProperty(value = "token", name = "token", example = "xxx")
    protected String token;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", name = "password", example = "xxx")
    protected String password;

    @Override
    public String toString() {
        return "AuthUserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuthUserDTO that = (AuthUserDTO) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(token, that.token) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, userName, token, password);
    }
}
