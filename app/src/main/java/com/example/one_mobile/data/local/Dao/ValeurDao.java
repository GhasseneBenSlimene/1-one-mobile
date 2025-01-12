package com.example.one_mobile.data.local.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Valeur;

import java.util.List;

@Dao
public interface ValeurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Valeur valeur);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Valeur> valeurs);

    @Query("SELECT * FROM valeurs WHERE facteur_id = :facteurId")
    List<Valeur> getValeursByFacteurId(long facteurId);

    @Query("SELECT * FROM valeurs WHERE id = :id")
    Valeur getValeurById(long id);

    @Query("DELETE FROM valeurs WHERE facteur_id = :facteurId")
    void deleteByFacteurId(long facteurId);

    @Query("DELETE FROM valeurs")
    void clearAll();
}
