package com.xd.web.interceptor;

import com.xd.core.web.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
         String uid=request.getHeader("uid");
         if(!StringUtils.isEmpty(uid)) {
             RequestContext.setUid(Integer.parseInt(uid));
         }
        String appId=  request.getHeader("appid");
         if(StringUtils.isEmpty(appId)){
             log.error("header appid can not be null");
             return false;
         }
        RequestContext.setAppId(appId);
        String platform=request.getHeader("platform");
        if(StringUtils.isEmpty(platform)){
            log.error("header platform can not be null");
            return false;
        }
        RequestContext.setPlatform(Integer.valueOf(platform));
        return true;
    }
}
