package com.example.one_mobile.data.repository;

import com.example.one_mobile.data.network.ApiService;
import com.example.one_mobile.data.network.RetrofitClient;
import com.example.one_mobile.data.network.TokenManager;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenRefresherRepository {

    private final ApiService apiService;

    public TokenRefresherRepository() {
        apiService = RetrofitClient.getApiService();
    }

    public void refreshTokens(TokenRefreshCallback callback) {
        TokenManager tokenManager = TokenManager.getInstance();
        String refreshToken = tokenManager.getRefreshToken();
        String accessToken = tokenManager.getAccessToken();
        String xsrfToken = tokenManager.getXsrfToken();

        if (refreshToken != null) {
//            String refreshTokenCookie = "refreshTokenCookie=" + refreshToken;
//            String accessTokenCookie = "accessTokenCookie=" + accessToken;
//            String xsrfTokenCookie = "XSRF-TOKEN=" + xsrfToken;
//            String cookieHeader = "refreshTokenCookie=" + refreshToken + "; XSRF-TOKEN=" + xsrfToken + "; accessTokenCookie=" + accessToken;
            apiService.refreshTokens().enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Retrieve cookies from the response
                        List<Cookie> cookies = Cookie.parseAll(HttpUrl.get(RetrofitClient.getBaseUrl() + "/auth/refresh"), response.headers());
                        for (Cookie cookie : cookies) {
                            if ("accessTokenCookie".equals(cookie.name())) {
                                String retrievedAccessToken = cookie.value();
                                tokenManager.setAccessToken(retrievedAccessToken);
                            } else if ("XSRF-TOKEN".equals(cookie.name())) {
                                String retrievedXsrfToken = cookie.value();
                                tokenManager.setXsrfToken(retrievedXsrfToken);
                            }
                        }
                        callback.onTokensRefreshed();
                    } else {
                        callback.onFailure();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    callback.onFailure();
                }
            });
        } else {
            callback.onFailure();
        }
    }

    public interface TokenRefreshCallback {
        void onTokensRefreshed();
        void onFailure();
    }
}