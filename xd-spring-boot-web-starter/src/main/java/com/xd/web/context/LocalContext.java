package com.xd.web.context;

import java.util.Locale;

/**
 * 语言信息上下文
 *
 * @author xiaohei
 * @create 2019-12-10 下午8:06
 **/
public class LocalContext {

    private static ThreadLocal<Locale> threadLocal = new ThreadLocal<>();

    public static Locale get(){
        if(threadLocal.get()==null){
            return Locale.SIMPLIFIED_CHINESE;
        }else{
            return threadLocal.get();
        }
    }

    public static void set(Locale locale){
        threadLocal.set(locale);
    }
}
