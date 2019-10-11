package mzlalal.redisession.redisessionjwt.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: jwt异常
 * @author: Mzlalal
 * @date: 2019/10/11 16:10
 * @version: 1.0
 */
@Setter
@Getter
public class JwtException extends Exception {

    /**
     * 错误编码
     */
    private String code;

    /**
     * 错误信息
     */
    private Object data;

    /**
     * 默认异常构造函数
     * @param msg 信息
     */
    public JwtException(String msg) {
        super(msg);
    }

    /**
     * 默认异常构造函数

     * @param e 异常
     */
    public JwtException(Exception e) {
        super(e);
    }

    /**
     * 默认异常构造函数
     * @param code 错误代码
     * @param data 错误信息
     */
    public JwtException(String code, Object data) {
        super();
        this.code = code;
        this.data = data;
    }

    /**
     * 获取RuntimeException异常，在做dubbo服务时可以用到，因为dubbo处理自定义异常有问题。
     */
    public RuntimeException getRuntimeException() {
        return new RuntimeException(this.getMessage());
    }
}
