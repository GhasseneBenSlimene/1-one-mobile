package com.example.one_mobile.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.one_mobile.data.local.Dao.EvaluationDao;
import com.example.one_mobile.data.local.Dao.EvaluationSiteDao;
import com.example.one_mobile.data.local.Dao.MatriceDao;
import com.example.one_mobile.data.local.Dao.OrigineDao;
import com.example.one_mobile.data.local.Dao.SiteDao;
import com.example.one_mobile.data.model.Evaluation;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.Origine;
import com.example.one_mobile.data.model.Site;
@Database(entities = {
//        RiskEvaluation.class,
//        SyncQueue.class,
        EvaluationSite.class,
        Matrice.class,
//        MatriceFacteur.class,
        Site.class,
        Origine.class,
        Evaluation.class,
//        Facteur.class,
//        Valeur.class,
//        RiskEvaluationDao.class,
//        SyncQueueDao.class,
}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract EvaluationDao evaluationDao();
    public abstract MatriceDao matriceDao();
    public abstract OrigineDao origineDao();
    public abstract SiteDao siteDao();
    public abstract EvaluationSiteDao evaluationSiteDao();
//    public abstract RiskEvaluationDao riskEvaluationDao();
//    public abstract SyncQueueDao syncQueueDao();

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
