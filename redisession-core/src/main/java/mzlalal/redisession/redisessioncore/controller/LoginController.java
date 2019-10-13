package mzlalal.redisession.redisessioncore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.constant.GlobalConstant;
import mzlalal.redisession.entity.AjaxJson;
import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.redisessionjwt.annotation.Token;
import mzlalal.redisession.redisessionjwt.annotation.TokenPass;
import mzlalal.redisession.redisessionjwt.utils.JwtTokenUtil;
import mzlalal.redisession.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 登录控制器
 * @author: Mzlalal
 */
@Slf4j
@RestController
@RefreshScope
@RequestMapping("/sso")
@Api(value = "LoginController", tags = "登录控制器")
public class LoginController {

    @Autowired
    RedisUtil redisUtil;

    @Value("${account:admin}")
    String account;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * 登录方法
     *
     * @param userName    用户名
     * @param password    密码
     * @param callbackUrl 回调URL
     * @return
     */
    @RequestMapping("/login")
    @ApiOperation(httpMethod = GlobalConstant.HTTP_POST, value = "login", tags = "登录方法",
            notes = "根据用户名, 密码, 回调URL作为参数请求登录方法", response = AjaxJson.class)
    public AjaxJson login(
            @ApiParam(name = "userName", value = "用户名", required = true) String userName,
            @ApiParam(name = "password", value = "密码", required = true) String password,
            @ApiParam(name = "callbackUrl", value = "回调URL") String callbackUrl,
            HttpServletRequest request) {

        // 返回信息
        AjaxJson aj = new AjaxJson();

        if (request.getSession().getAttribute("user") != null) {
            aj.setMsg("用户已登录！" + request.getSession().getAttribute("user"));

            // 测试反序列化
            System.out.println(redisUtil.get("aj2"));
            System.out.println(redisUtil.hget("aj", "a"));
            return aj;
        }

        // 用户验证
        if (account.equals(userName) && account.equals(password)) {
            // 保存用户对象
            AuthUserDTO authUserDTO = new AuthUserDTO();
            authUserDTO.setPassword(password);
            authUserDTO.setUserName(userName);
            authUserDTO.setToken(password);

            request.getSession().setAttribute("user", authUserDTO);

            // 返回token
            aj.put("token", jwtTokenUtil.createToken("1", password));
            // 回调URL
            aj.put("callbackUrl", callbackUrl);
        } else {
            aj.setErrorMsg("用户登录失败(用户名或者密码错误)...请重新登录...");
        }

        // 测试序列化
        redisUtil.set("aj2", aj);
        redisUtil.hset("aj", "a", aj);
        return aj;
    }

    /**
     * 验证用户是否登录
     */
    @Token
    @RequestMapping("/verifyLogin")
    @ApiOperation(httpMethod = GlobalConstant.HTTP_POST, value = "verifyLogin", tags = "验证用户是否登录",
            notes = "验证用户是否登录", response = AjaxJson.class)
    public AjaxJson verifyLogin(HttpServletRequest request) {
        AjaxJson aj = new AjaxJson();

        if (request.getSession().getAttribute("user") != null) {
            aj.setMsg("用户已登录！" + request.getSession().getAttribute("user"));
        } else {
            aj.setSuccess(false);
            aj.setMsg("用户未登录或者登录已过期！请重新登录！");
        }
        return aj;
    }

    /**
     * 验证用户是否登录
     */
    @TokenPass
    @RequestMapping("/findAuthUser")
    @ApiOperation(httpMethod = GlobalConstant.HTTP_GET, value = "findAuthUser", tags = "获取用户信息",
            notes = "获取用户信息,先从缓存中获取,若缓存不存在则查询数据库", response = AuthUserDTO.class)
    public AuthUserDTO findAuthUser(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("user");
        if (obj != null) {
            log.info(obj.toString());
        }
        return (AuthUserDTO) request.getSession().getAttribute("user");
    }
}
