package com.xd.web.util;

import com.xd.core.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 获取web request 请求相关的参数
 *
 * @author xiaohei
 * @create 2019-12-10 下午8:56
 **/
public class WebUtil {
    public static final int MIN = 2;
    public static Locale getLocale(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        if(StringUtils.isEmpty(language)){
            return Locale.US;
        }
        language = StringUtils.substringBefore(language, ",");
        String[] ss = StringUtils.split("-");
        if(ss.length< MIN){
            return Locale.US;
        }
        Locale locale = new Locale(ss[0], ss[1]);
        return locale;

    }
}
