package com.example.one_mobile.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EvaluationSiteViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public EvaluationSiteViewModelFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EvaluationSiteViewModel.class)) {
            return (T) new EvaluationSiteViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

