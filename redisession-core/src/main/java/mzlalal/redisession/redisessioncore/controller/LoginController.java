package mzlalal.redisession.redisessioncore.controller;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.entity.AjaxJson;
import mzlalal.redisession.constant.GlobalConstant;
import mzlalal.redisession.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 登录控制器
 * @author: Mzlalal
 */
@Slf4j
@Controller
@RefreshScope
@RequestMapping("/sso")
public class LoginController {
    /**
     * 获取spring - redis - template 服务
     */
    @Autowired
    RedisUtil redisUtil;

    @Value("${account:admin}")
    String account;

    /**
     * 登录方法
     *
     * @param userName    用户名
     * @param password    密码
     * @param callbackUrl 回调URL
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    AjaxJson login(String userName, String password, String callbackUrl, HttpServletRequest request, HttpServletResponse response) {

        // 返回信息
        AjaxJson aj = new AjaxJson();

        if (redisUtil.hHasKey(GlobalConstant.getRedisSessionKey(request), "creationTime")) {
            aj.setMsg("用户已登录！");
            return aj;
        }

        // 用户验证
         if (account.equals(userName) && account.equals(password)) {
            request.getSession().setAttribute("user", account);
            // 返回token
            aj.put("token", GlobalConstant.REDIS_SESSIONS + request.getSession().getId());

            // 回调URL
            aj.put("callbackUrl", callbackUrl);
        } else {
            aj.setErrorMsg("用户登录失败...请重新登录...");
        }

        return aj;
    }


}
