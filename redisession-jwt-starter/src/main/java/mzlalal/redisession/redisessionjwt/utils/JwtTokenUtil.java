package mzlalal.redisession.redisessionjwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @description: jwt 工具类
 * @author: Mzlalal
 * @date: 2019/9/5 14:42
 * @version: 1.0
 */
@Component
public class JwtTokenUtil {

    /**
     * 过期时间
     */
    @Value("${jwt.expiration: 30}")
    private int expiration;

    /**
     * 通过 user 获取 token
     * @param id     用户ID
     * @param secret 用户密码
     * @return String token
     */
    public String createToken(String id, String secret) {
        // 将 user id 保存到 token 里面
        Date now = new Date();
        return JWT.create()
                // 签发时间
                .withIssuedAt(now)
                // 设置过期时间
                .withExpiresAt(DateUtils.addMinutes(now, expiration))
                // 接受者ID
                .withAudience(id)
                // 使用密码作为私匙
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 通过 secret 用户棉麻 解析 token
     * @param token token
     * @param secret 用户密码
     * @return DecodedJWT
     * @throws JWTVerificationException
     */
    public DecodedJWT parseToken(String token, String secret) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        return jwtVerifier.verify(token);
    }
}
