package com.example.one_mobile.data.network;

public class TokenManager {
    private static TokenManager instance;
    private String xsrfToken;
    private String accessToken;
    private String refreshToken;

    public static final String API_KEY = "02bxsdeea";


    private TokenManager() {}

    public static synchronized TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public String getXsrfToken() {
        return xsrfToken;
    }

    public void setXsrfToken(String xsrfToken) {
        this.xsrfToken = xsrfToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

}