package com.example.one_mobile.data.local.converters;


import androidx.room.TypeConverter;
import com.example.one_mobile.data.model.Matrice;
import com.google.gson.Gson;

public class MatriceConverter {
    @TypeConverter
    public static String fromMatrice(Matrice matrice) {
        if (matrice == null) return null;
        return new Gson().toJson(matrice);
    }

    @TypeConverter
    public static Matrice toMatrice(String matriceString) {
        if (matriceString == null) return null;
        return new Gson().fromJson(matriceString, Matrice.class);
    }
}
