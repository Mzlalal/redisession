package mzlalal.redisession.redisessioncosumer.controller;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.entity.AjaxJson;
import mzlalal.redisession.entity.user.AuthUserDTO;
import mzlalal.redisession.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class VerifyController {
    /**
     * 获取redis - template 服务
     */
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 验证redis过期监听
     */
    @RequestMapping("/testExpired")
    public void testExpired() {
        redisUtil.set("testKey", "testValue", 5);
    }

    /**
     * 验证是否登录
     */
    @RequestMapping("/verifyLogin")
    public AjaxJson verifyLogin() {
        ResponseEntity<AjaxJson> responseEntity = restTemplate.postForEntity("http://redisession-core-provider/sso/verifyLogin", null, AjaxJson.class);
        return responseEntity.getBody();
    }

    /**
     * 验证是否登录
     */
    @RequestMapping("/findAuthUser")
    public AuthUserDTO findAuthUser(HttpServletRequest request) {
        // 直接获取user即可  如果请求其他服务获取user容易被误认为新的请求 而获取不同的session
        Object obj = request.getSession().getAttribute("user");
        if (obj != null) {
            log.info(obj.toString());
        }
        return (AuthUserDTO) request.getSession().getAttribute("user");
    }
}
