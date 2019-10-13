package mzlalal.redisession.redisessionjwt.interfaces;

import mzlalal.redisession.redisessionjwt.exception.JwtException;

/**
 * @description: 异常类接口
 * @author: Mzlalal
 * @date: 2019/10/11 16:33
 * @version: 1.0
 */
public interface Errors {

    // 使用接口是因为jdk8后 接口的变量默认为 public static final

    JwtException noToeknException = new JwtException("0", "token未获取!");

    JwtException noUserException = new JwtException("1", "用户不存在,请重新登录!");

    JwtException expiredException = new JwtException("2", "token未已经过期,请重新登录!");

    JwtException verifyException = new JwtException("3", "token验证失败!");

    JwtException noLoginException = new JwtException("4", "用户未登录!");
}
