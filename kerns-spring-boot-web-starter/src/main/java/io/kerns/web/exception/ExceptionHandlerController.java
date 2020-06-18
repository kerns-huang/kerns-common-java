package io.kerns.web.exception;

import io.kerns.web.response.BasicCodeMsg;
import io.kerns.web.response.Result;
import io.kerns.web.response.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;


/**
 * @author heige
 * exception  异常统一返回 Result
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result onError(HttpServletRequest request, Exception re) {
        String requestUrl = request.getRequestURI();
        if (re instanceof MissingServletRequestParameterException) {
            log.error(" miss request param requestUrl: {} ,header:{} parameters:{}  msg={}", requestUrl, getHeadersStr(request), getParamStr(request), re.getMessage());
            return ResultUtils.fail(BasicCodeMsg.PARAM_ERROR);
        } else if (re instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) re;
            log.error(" request param error requestUrl: {} ,header:{} parameters:{} error param='{}' {}", requestUrl, getHeadersStr(request), getParamStr(request), ex.getName(), re.getMessage());
            return ResultUtils.fail(BasicCodeMsg.PARAM_ERROR);
        } else if (re instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) re;
            log.error(" request param error requestUrl: {} ,header:{} parameters:{} error param='{}' {}", requestUrl, getHeadersStr(request), getParamStr(request), re.getMessage());
            BindingResult bindingResult = ex.getBindingResult();
            if (bindingResult != null && bindingResult.hasErrors()) {
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                if (!CollectionUtils.isEmpty(fieldErrors)) {
                    return ResultUtils.fail(fieldErrors.get(0).getField()+fieldErrors.get(0).getDefaultMessage());
                }
            }
            return ResultUtils.fail(BasicCodeMsg.PARAM_ERROR);
        }
        //TODO 业务异常处理，
//        else if (re instanceof BizException) {
//            BizException biz = (BizException) re;
//            log.info("biz exception biz code={},msg={}", biz.getCode(), biz.getCodeMsg().getMsg());
//            return ResultUtils.fail(biz.getCodeMsg());
//        }
        log.error(" request param type error, requestUrl: {},header:{} parameters:{} msg={}", requestUrl, getHeadersStr(request), getParamStr(request), re.getMessage(), re);
        return ResultUtils.fail(BasicCodeMsg.PARAM_ERROR);
    }

    private String getHeadersStr(HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        StringBuilder sb = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            sb.append(key).append("=").append(value).append(",");
        }
        return sb.toString();
    }

    private String getParamStr(HttpServletRequest request) {
        Enumeration params = request.getParameterNames();
        StringBuilder sb = new StringBuilder();
        while (params.hasMoreElements()) {
            String key = (String) params.nextElement();
            String value = request.getParameter(key);
            sb.append(key).append("=").append(value).append(",");
        }
        return sb.toString();
    }


}
