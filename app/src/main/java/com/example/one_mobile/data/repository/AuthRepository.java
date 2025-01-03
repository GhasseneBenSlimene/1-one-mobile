package com.example.one_mobile.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.one_mobile.data.model.AuthResponse;
import com.example.one_mobile.data.model.AuthenticationRequest;
import com.example.one_mobile.data.network.ApiService;
import com.example.one_mobile.data.network.RetrofitClient;
import com.example.one_mobile.data.network.TokenManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

import java.util.List;


public class AuthRepository {
    private final ApiService apiService;
    private final String baseUrl;

    public AuthRepository() {
        this.apiService = RetrofitClient.getApiService();
        this.baseUrl = RetrofitClient.getBaseUrl();
    }

    // Étape 1 : Obtenir le token XSRF
    public LiveData<Boolean> getSessionToken() {
        MutableLiveData<Boolean> sessionLiveData = new MutableLiveData<>();
        apiService.getSessionToken().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() && response.headers() != null) {
                    // Récupérer le cookie XSRF-TOKEN
                    String setCookieHeader = response.headers().get("Set-Cookie");
                    if (setCookieHeader != null && setCookieHeader.contains("XSRF-TOKEN")) {
                        String xsrfToken = extractXsrfToken(setCookieHeader);
                        TokenManager.getInstance().setXsrfToken(xsrfToken);
                        sessionLiveData.postValue(true);
                        return;
                    }
                }
                sessionLiveData.postValue(false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                sessionLiveData.postValue(false);
            }
        });
        return sessionLiveData;
    }

    // Étape 2 : Authentification
    public LiveData<AuthResponse> authenticate(String username, String password) {
        MutableLiveData<AuthResponse> authResponseLiveData = new MutableLiveData<>();
        AuthenticationRequest request = new AuthenticationRequest(username, password);

        TokenManager tokenManager = TokenManager.getInstance();
        String xsrfToken = tokenManager.getXsrfToken();


        apiService.authenticate(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    authResponseLiveData.postValue(response.body());
                    // Retrieve cookies from the response
                    List<Cookie> cookies = Cookie.parseAll(HttpUrl.get(baseUrl + "/auth"), response.headers());
                    for (Cookie cookie : cookies) {
                        if ("accessTokenCookie".equals(cookie.name())) {
                            String accessToken = cookie.value();
                            TokenManager.getInstance().setAccessToken(accessToken);
                            System.out.println("Access Token from cookie: " + accessToken); // Log the access token
                        } else if ("refreshTokenCookie".equals(cookie.name())) {
                            String refreshToken = cookie.value();
                            TokenManager.getInstance().setRefreshToken(refreshToken);
                            System.out.println("Refresh Token from cookie: " + refreshToken); // Log the refresh token
                        }
                    }
                } else {
                    authResponseLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                authResponseLiveData.postValue(null);
            }
        });

        return authResponseLiveData;
    }

    // Méthode pour extraire le token XSRF du cookie
    private String extractXsrfToken(String setCookieHeader) {
        String[] cookies = setCookieHeader.split(";");
        for (String cookie : cookies) {
            if (cookie.trim().startsWith("XSRF-TOKEN=")) {
                return cookie.trim().substring("XSRF-TOKEN=".length());
            }
        }
        return null;
    }
}
