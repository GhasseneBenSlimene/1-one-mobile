package com.example.one_mobile.data.network;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.Headers;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:8081";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            // Configurer un CookieManager pour gérer les cookies
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .addNetworkInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder();

                    String xsrfToken = TokenManager.getInstance().getXsrfToken();
                    if (xsrfToken != null) {
                        requestBuilder.addHeader("x-xsrf-token", xsrfToken);
                    }

                    requestBuilder.addHeader("x-api-key", TokenManager.API_KEY);


                    Request request = requestBuilder.build();
                    Response response = chain.proceed(request);

                    // Log the cookies sent by Retrofit
                    Headers requestHeaders = request.headers();
                    for (String header : requestHeaders.values("Cookie")) {
                        String[] cookies = header.split(";");
                        for (String cookie : cookies) {
                            if (!cookie.contains("refreshTokenCookie")) {
                                System.out.println("Token sent by Retrofit: " + cookie.trim());
                            }
                        }
                    }

                    return response;
                })
                .build();

            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        }
        return retrofit;
    }

    public static ApiService getApiService() {
        return getInstance().create(ApiService.class);
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
