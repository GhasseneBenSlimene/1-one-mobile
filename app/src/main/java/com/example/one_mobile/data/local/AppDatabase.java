package com.example.one_mobile.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.one_mobile.data.local.converters.DateConverter;
import com.example.one_mobile.data.local.converters.MatriceConverter;
import com.example.one_mobile.data.local.converters.OrigineConverter;
import com.example.one_mobile.data.local.converters.RisqueConverter;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.RiskEvaluation;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;

@Database(entities = {
        RiskEvaluation.class,
        SyncQueue.class,
        EvaluationSite.class,
        Matrice.class,
        MatriceFacteur.class,
        Site.class,
        Facteur.class,
        Valeur.class
}, version = 1, exportSchema = false)
@TypeConverters({MatriceConverter.class, DateConverter.class, RisqueConverter.class, OrigineConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract RiskEvaluationDao riskEvaluationDao();
    public abstract SyncQueueDao syncQueueDao();
    public abstract EvaluationSiteDao evaluationSiteDao();
//    public abstract MatriceDao matriceDao();
//    public abstract MatriceFacteurDao matriceFacteurDao();
//    public abstract SiteDao siteDao();
//    public abstract FacteurDao facteurDao();
//    public abstract ValeurDao valeurDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
