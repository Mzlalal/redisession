package mzlalal.redisession.utils.common;

import lombok.extern.slf4j.Slf4j;
import mzlalal.redisession.constant.GlobalConstant;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @description: 根据Request获取IP地址
 * @author: Mzlalal
 * @date: 2019/8/24 15:37
 * @version: 1.0
 */
public class IPUtil {

    /**
     * 获取真实调用IP地址
     *
     * @return ip
     */
    public static String getRealRemoteIp(HttpServletRequest request) {
        // 获取IP地址
        String ip = request.getHeader("x-forwarded-for");

        // 如果 IP 为空 且 IP长度不为0 且 IP值不为 unknown
        if (ip == null || ip.length() == 0 || GlobalConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || GlobalConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || GlobalConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (GlobalConstant.LOCALHOST127.equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    throw new RuntimeException("获取真实调用IP地址出错", e);
                }
                ip = inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (GlobalConstant.ENGLISH_COMMA.indexOf(ip) > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
