package mzlalal.redisession.redisessioncosumer.controller;

import mzlalal.redisession.constant.GlobalConstant;
import mzlalal.redisession.entity.AjaxJson;
import mzlalal.redisession.redisessioncosumer.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class VerifyController {
    /**
     * 获取spring - redis - template 服务
     */
    @Autowired
    RedisUtil redisUtil;

    /**
     * 验证是否登录
     */
    @RequestMapping("/verifyLogin")
    @ResponseBody
    public AjaxJson verifyLogin(HttpServletRequest request) {
        AjaxJson aj = new AjaxJson();

        if (redisUtil.hHasKey(GlobalConstant.getRedisSessionKey(request), "creationTime")) {
            aj.setMsg("用户已登录！");
        } else {
            aj.setSuccess(false);
            aj.setMsg("用户未登录或者登录已过期！请重新登录！");
        }
        return aj;
    }
}
