package com.xd.web.response;

import com.xd.core.util.StringUtils;
import com.xd.core.web.context.RequestContext;
import com.xd.core.web.response.Result;
import com.xd.web.context.LocalContext;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 返回结果处理，国际化信息的处理
 *
 * @author xiaohei
 * @create 2019-12-10 下午7:58
 **/
@ControllerAdvice
public class XdResponseBodyAdvisor implements ResponseBodyAdvice<Result> {
    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return StringUtils.equals(Result.class.getName(), methodParameter.getGenericParameterType().getClass().getName());
    }

    @Override
    public Result beforeBodyWrite(Result s, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (StringUtils.isNotEmpty(s.getMsgCode())) {
            s.setMsg(messageSource.getMessage(s.getMsgCode(), null, LocalContext.get()));
        }
        return s;
    }
}
