package com.example.one_mobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.one_mobile.data.model.AuthResponse;
import com.example.one_mobile.data.repository.AuthRepository;

public class AuthViewModel extends ViewModel {
    private final AuthRepository authRepository;

    public AuthViewModel() {
        this.authRepository = new AuthRepository();
    }

    public LiveData<Boolean> getSessionToken() {
        return authRepository.getSessionToken();
    }

    public LiveData<AuthResponse> authenticate(String username, String password) {
        return authRepository.authenticate(username, password);
    }
    public LiveData<Boolean> logout() {
        return authRepository.logout();
    }
}
