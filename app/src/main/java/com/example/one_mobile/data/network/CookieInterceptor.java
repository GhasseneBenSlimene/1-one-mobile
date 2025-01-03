//package com.example.one_mobile.data.network;
//
//import android.util.Log;
//import java.io.IOException;
//import java.util.List;
//
//import okhttp3.Cookie;
//import okhttp3.HttpUrl;
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.jetbrains.annotations.NotNull;
//import retrofit2.Call;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class CookieInterceptor implements Interceptor {
//
//    private static final String TAG = "CookieInterceptor";
//    private final ApiService apiService;
//
//    public CookieInterceptor() {
//        OkHttpClient client = new OkHttpClient();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RetrofitClient.getBaseUrl())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//        apiService = retrofit.create(ApiService.class);
//    }
//
//    @Override
//    @NotNull
//    public Response intercept(Chain chain) throws IOException {
//        Request originalRequest = chain.request();
//        Request.Builder requestBuilder = originalRequest.newBuilder();
//
//        // If the request is a POST, DELETE, PUT request, or GET /auth/refresh, refresh tokens first
//        if ((originalRequest.method().equals("POST") && !originalRequest.url().encodedPath().equals("/auth")) ||
//                originalRequest.method().equals("DELETE") || originalRequest.method().equals("PUT")) {
//            refreshTokens();
//        }
//
//        Request request = requestBuilder.build();
//        return chain.proceed(request);
//    }
//
//    private void refreshTokens() throws IOException {
//        TokenManager tokenManager = TokenManager.getInstance();
//        String refreshToken = tokenManager.getRefreshToken();
//        String accessToken = tokenManager.getAccessToken();
//        String xsrfToken = tokenManager.getXsrfToken();
//
//        if (refreshToken != null) {
//            String refreshTokenCookie = "refreshTokenCookie=" + refreshToken;
//            String accessTokenCookie = "accessTokenCookie=" + accessToken;
//            String xsrfTokenCookie = "XSRF-TOKEN=" + xsrfToken;
//            Call<Void> call = apiService.refreshTokens(refreshTokenCookie, xsrfTokenCookie, accessTokenCookie);
//            retrofit2.Response<Void> response = call.execute();
//
//            if (response.isSuccessful()) {
//                // Retrieve cookies from the response
//                List<Cookie> cookies = Cookie.parseAll(HttpUrl.get(RetrofitClient.getBaseUrl() + "/auth/refresh"), response.headers());
//                for (Cookie cookie : cookies) {
//                    Log.d(TAG, "Request successful, cookies retrieved:");
//                    if ("accessTokenCookie".equals(cookie.name())) {
//                        String retrievedAccessToken = cookie.value();
//                        tokenManager.setAccessToken(retrievedAccessToken);
//                        Log.d(TAG, "Access Token refreshed: " + retrievedAccessToken);
//                    } else if ("XSRF-TOKEN".equals(cookie.name())) {
//                        String retrievedXsrfToken = cookie.value();
//                        tokenManager.setXsrfToken(retrievedXsrfToken);
//                        Log.d(TAG, "XSRF Token refreshed: " + retrievedXsrfToken);
//                    }
//                }
//            } else {
//                Log.w(TAG, "Response not successful or headers are null");
//            }
//        } else {
//            Log.w(TAG, "Refresh token is null, cannot refresh tokens");
//        }
//    }
//}
