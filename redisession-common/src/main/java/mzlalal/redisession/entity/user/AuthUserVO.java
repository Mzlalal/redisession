package mzlalal.redisession.entity.user;

import lombok.Data;

import java.util.Objects;

/**
 * @description: 用户浏览器查看对象
 * @author: Mzlalal
 * @date: 2019/10/11 17:02
 * @version: 1.0
 */
@Data
public class AuthUserVO {
    /**
     * 用户ID
     */
    protected long id;
    /**
     * 用户名称
     */

    protected String userName;

    @Override
    public String toString() {
        return "AuthUserVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuthUserVO that = (AuthUserVO) o;
        return id == that.id &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, userName);
    }
}
