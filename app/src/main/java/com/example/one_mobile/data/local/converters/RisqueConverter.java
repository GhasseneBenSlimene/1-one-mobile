package com.example.one_mobile.data.local.converters;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.example.one_mobile.data.model.Risque;

public class RisqueConverter {
    @TypeConverter
    public static Risque fromString(String value) {
        return value == null ? null : new Gson().fromJson(value, Risque.class);
    }

    @TypeConverter
    public static String fromRisque(Risque risque) {
        return risque == null ? null : new Gson().toJson(risque);
    }
}