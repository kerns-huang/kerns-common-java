package com.xd.web.interceptor;

import com.xd.core.util.StringUtils;
import com.xd.core.web.context.RequestContext;
import com.xd.web.context.LocalContext;
import com.xd.web.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author watt
 */
@Slf4j
public class RequestContextInterceptor implements HandlerInterceptor {

    public static final String CLIENT = "client";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String path= request.getPathInfo();
        if(!StringUtils.contains(path,CLIENT)){
            return  true;
        }
        String uid = request.getHeader("uid");
        if (!StringUtils.isEmpty(uid)) {
            RequestContext.setUid(Integer.parseInt(uid));
        }
        String appId = request.getHeader("appid");
        if (StringUtils.isEmpty(appId)) {
            log.error("header appid can not be null");
            return false;
        }
        RequestContext.setAppId(appId);
        String platform = request.getHeader("platform");
        if (StringUtils.isEmpty(platform)) {
            log.error("header platform can not be null");
            return false;
        }
        RequestContext.setPlatform(Integer.valueOf(platform));
        LocalContext.set(WebUtil.getLocale(request));
        return true;
    }
}
