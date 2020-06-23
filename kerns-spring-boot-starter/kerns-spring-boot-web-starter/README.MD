## 主要作用 

 web交互的一些通用类的抽取，符合中国人的一个开发习惯，比如通用的异常的一些处理
 
 ### exception 
     异常的统一处理
     
 
 ### 请求的信息处理
 
 ```java
  @Slf4j
  public class RequestContextInterceptor implements HandlerInterceptor {
  
      public static final String CLIENT = "client";
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
              throws Exception {
          //TODO 业务逻辑处理
          return true;
      }
  }
```
    
拦截器注册

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urls=new ArrayList<>();
        urls.add("/doc.html");
        urls.add("/webjars/**");
        urls.add("/statics/**");
        urls.add("/swagger-resources");
        urls.add("/error");
        urls.add("/swagger-ui.html");
        registry.addInterceptor(new RequestContextInterceptor()).addPathPatterns("/**").excludePathPatterns(urls);
    }
}
```
     
 ### 返回结果的修改
    
```java
    @ControllerAdvice
    public class XdResponseBodyAdvisor implements ResponseBodyAdvice<Result> {
        @Autowired
        private MessageSource messageSource;
    
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            if (methodParameter.getGenericParameterType() instanceof ParameterizedTypeImpl) {
                ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) methodParameter.getGenericParameterType();
                return Result.class == parameterizedType.getRawType();
            } else {
                return false;
            }
        }
    
        @Override
        public Result beforeBodyWrite(Result s, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            if (s!=null&&StringUtils.isNotEmpty(s.getMsgCode())) {
                s.setMsg(messageSource.getMessage(s.getMsgCode(), null, LocalContext.get()));
            }
            return s;
        }
    }
```     