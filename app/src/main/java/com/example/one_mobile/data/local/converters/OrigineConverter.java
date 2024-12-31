package com.example.one_mobile.data.local.converters;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.example.one_mobile.data.model.Origine;

public class OrigineConverter {
    @TypeConverter
    public static Origine fromString(String value) {
        return value == null ? null : new Gson().fromJson(value, Origine.class);
    }

    @TypeConverter
    public static String fromOrigine(Origine origine) {
        return origine == null ? null : new Gson().toJson(origine);
    }
}