package mzlalal.redisession.redisessioncosumer.controller;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.redisessionapi.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: FeignClient 测试类
 * @author: Mzlalal
 * @date: 2019/10/15 17:50
 * @version: 1.0
 */
@Slf4j
@RestController()
@RequestMapping("feign")
public class FeignController {

    @Reference
    AuthUserService authUserService;

    @RequestMapping("testFeign")
    public String testFeign(){
       Object obj =  authUserService.findAuthUserDTOById(1);
       if (obj == null) {
            return "错误";
       }
       return "错误";
    }
}
