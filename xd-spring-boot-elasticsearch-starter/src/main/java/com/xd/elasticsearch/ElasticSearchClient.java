package com.xd.elasticsearch;

import com.sun.istack.internal.NotNull;
import com.xd.json.JSONUtils;
import lombok.extern.log4j.Log4j;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 客户端配置
 *
 * @author xiaohei
 * @create 2020-04-15 下午3:17
 **/
public class ElasticSearchClient {

    private static final String LOG_TAG = "OkHttpUtil";

    private OkHttpClient client;

    private String url;


    public ElasticSearchClient(String url) {
        this.url = url;
        client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    public String post(String sql) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> map = new HashMap<>();
        map.put("query", sql);
        Request request = new Request.Builder().url(url).method("POST", RequestBody.create(mediaType, JSONUtils.toString(map))).build();
        return getString(request);
    }

    private String getString(Request request) {
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                if (null != response.body()) {
                    return Objects.requireNonNull(response.body()).string();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    static class LoggingInterceptor implements Interceptor {
        @NotNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String method = request.method();
            RequestBody requestBody = request.body();
            return chain.proceed(request);
        }
    }
}
