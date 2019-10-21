package mzlalal.redisession.redisessionjwt.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.redisessionjwt.annotation.Token;
import mzlalal.redisession.redisessionjwt.annotation.TokenPass;
import mzlalal.redisession.redisessionjwt.exception.JwtException;
import mzlalal.redisession.redisessionjwt.interfaces.Errors;
import mzlalal.redisession.redisessionjwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * @description:  验证拦截器
 *  过期时间 刷新什么都还没做呢
 * @author:       Mzlalal
 * @date:         2019/9/5 14:49
 * @version:      1.0
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtTokenUtil jwtToken;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 检查是否有TokenPass注释，有则跳过认证
        // 现在是使用根据方法上面是否带有注解token 有则验证 没有的话不加TokenPass也能够执行好像写了没什么用
        // 拦截的都是带注解的除非使用全局都使用验证token 但这样的话token注解没用 写着玩吧0.0
        if (method.isAnnotationPresent(TokenPass.class)) {
            TokenPass passToken = method.getAnnotation(TokenPass.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查是否带有token注解并进行解析
        if (method.isAnnotationPresent(Token.class)) {
            // 检查注解是否为必须
            Token userLoginToken = method.getAnnotation(Token.class);
            if (userLoginToken.required()) {

                // 从 http 请求头中取出 token
                String token = request.getHeader("token");

                // 执行认证
                if (token == null) {
                    log.error("用户未获取token!");
                    throw Errors.noToeknException;
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new JwtException(j);
                }

                // 查询用户信息
                AuthUserDTO dto = null;
                try {
                    dto = (AuthUserDTO) request.getSession().getAttribute("user");
                } catch (NullPointerException npe) {
                    log.error("用户未登录!");
                    throw Errors.noLoginException;
                } catch (Exception e) {
                    throw new JwtException(e);
                }
                if (dto == null) {
                    throw Errors.noToeknException;
                }

                // 验证用户token
                try {
                    jwtToken.parseToken(token, dto.getPassword());
                } catch (JWTVerificationException e) {
                    log.error("", e);
                    throw Errors.verifyException;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
