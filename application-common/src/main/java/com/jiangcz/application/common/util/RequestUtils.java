package com.jiangcz.application.common.util;

import com.google.common.base.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};

    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public static String getClientIpAddress(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return getClientIpAddress(request);
    }

    /**
     * 是否AJAX请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return !Strings.isNullOrEmpty(request.getHeader("HTTP_X_REQUESTED_WITH"))
                && request.getHeader("HTTP_X_REQUESTED_WITH").equalsIgnoreCase("xmlhttprequest");
    }

    public static Map<String, String> getParas(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();

        if (request == null)
            return result;

        request.getParameterMap().forEach((k, v) -> {
            String[] values = (String[]) v;
            String value = "";
            for (String str : values) {
                value = value + str + ",";
            }
            result.put((String) k, Strings.isNullOrEmpty(value) ? "" : value.substring(0, value.length() - 1));
        });

        return result;
    }

}
