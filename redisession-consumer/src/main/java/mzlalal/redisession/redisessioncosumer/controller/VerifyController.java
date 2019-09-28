package mzlalal.redisession.redisessioncosumer.controller;

import mzlalal.redisession.entity.AjaxJson;
import mzlalal.redisession.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class VerifyController {
    /**
     * 获取spring - redis - template 服务
     */
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 验证是否登录
     */
    @RequestMapping("/verifyLogin")
    @ResponseBody
    public AjaxJson verifyLogin(HttpServletRequest request) {
        ResponseEntity<AjaxJson> responseEntity = restTemplate.postForEntity("http://redisession-core-provider/sso/verifyLogin", null, AjaxJson.class);
        return responseEntity.getBody();
    }
}
