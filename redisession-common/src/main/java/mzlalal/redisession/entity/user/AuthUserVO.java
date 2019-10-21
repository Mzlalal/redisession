package mzlalal.redisession.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @description: 用户浏览器查看对象
 * @author: Mzlalal
 * @date: 2019/10/11 17:02
 * @version: 1.0
 */
@Data
@ApiModel(value = "AuthUserVO", description = "用户浏览对象")
public class AuthUserVO implements Serializable {
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

    @Override
    public String toString() {
        return "AuthUserVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuthUserVO that = (AuthUserVO) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, userName, token);
    }
}
