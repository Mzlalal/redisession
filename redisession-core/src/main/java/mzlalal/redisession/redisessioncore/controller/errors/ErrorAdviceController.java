package mzlalal.redisession.redisessioncore.controller.errors;

import mzlalal.redisession.entity.AjaxJson;
import mzlalal.redisession.redisessionjwt.exception.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常处理
 */
@ControllerAdvice
public class ErrorAdviceController {

    /**
     * redisession - jwt 报错出来的异常
     * @param e jwt异常
     * @return ajaxjson
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = JwtException.class)
    public AjaxJson jwtExceptionHandler (JwtException e) {
        AjaxJson aj = new AjaxJson();
        aj.setErrorCode(e.getCode());
        aj.setErrorMsg(e.getData().toString());
        return aj;
    }

//    /**
//     * redisession - jwt 报错出来的异常
//     * @param e jwt异常
//     * @return ajaxjson
//     */
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = JwtException.class)
//    public AjaxJson jwtExceptionHandler (Exception e) {
//        AjaxJson aj = new AjaxJson();
//        aj.setErrorCode("9999");
//        aj.setErrorMsg("服务好像出现了未知的异常...");
//        aj.put("errorDetail", ExceptionUtils.getRootCauseMessage(e));
//        return aj;
//    }
}
