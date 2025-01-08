package com.example.one_mobile.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
        EvaluationSite.class,
        Matrice.class,
        Site.class,
        Origine.class,
        Evaluation.class,
}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract EvaluationDao evaluationDao();
    public abstract MatriceDao matriceDao();
    public abstract OrigineDao origineDao();
    public abstract SiteDao siteDao();
    public abstract EvaluationSiteDao evaluationSiteDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("PRAGMA foreign_keys=ON;");
                        }

                        @Override
                        public void onOpen(SupportSQLiteDatabase db) {
                            super.onOpen(db);
                            db.execSQL("PRAGMA foreign_keys=ON;");
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}