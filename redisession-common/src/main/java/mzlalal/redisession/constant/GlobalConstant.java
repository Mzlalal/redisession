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
     * 获取 redis sessionKey
     * @param request
     * @return
     */
    public static String getRedisSessionKey (HttpServletRequest request) {
        return REDIS_SESSIONS + request.getSession().getId();
    }

}
