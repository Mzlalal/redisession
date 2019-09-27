package mzlalal.redisession.constant;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 全局常量
 * @author: Mzlalal
 */
public class GlobalConstant {

    /**
     * 未知
     */
    public final static String UNKNOWN = "unknown";

    /**
     * 127.0.0.1
     */
    public final static String LOCALHOST127 = "127.0.0.1";

    /**
     * 英文逗号
     */
    public final static String ENGLISH_COMMA = ",";

    /**
     * token
     */
    public final static String TOKEN = "token";

    /**
     * sessions
     */
    public final static String SESSIONS ="sessions";

    /**
     * redis sessions head
     */
    public final static String REDIS_SESSIONS ="mzlalal.sso:sessions:";

    /**
     * http 请求中的 GET 方法
     */
    public final static String HTTP_GET = "GET";

    /**
     * http 请求中的 POST 方法
     */
    public final static String HTTP_POST = "POST";

    /**
     * http 请求中的 PUT 方法
     */
    public final static String HTTP_PUT = "PUT";

    /**
     * http 请求中的 DELETE 方法
     */
    public final static String HTTP_DELETE = "DELETE";

    /**
     * http 请求中的 HEAD 方法
     */
    public final static String HTTP_HEAD = "HEAD";

    /**
     * http 请求中的 OPTIONS 方法
     */
    public final static String HTTP_OPTIONS = "OPTIONS";

    /**
     * http 请求中的 PATCH 方法
     */
    public final static String HTTP_PATCH = "PATCH";

    /**
     * 获取 redis sessionKey
     * @param request 当前请求对象
     * @return redis sessionKey
     */
    public static String getRedisSessionKey (HttpServletRequest request) {
        return REDIS_SESSIONS + request.getSession().getId();
    }

}
