package dev.gpbreis.cozinheiro.persistance;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.gpbreis.cozinheiro.model.Contractor;

@Dao
public interface ContractorDao {

    @Insert
    long insert(Contractor contractor);

    @Delete
    void delete(Contractor contractor);

    @Update
    void update(Contractor contractor);

    @Query("SELECT * FROM contractor WHERE id = :id")
    Contractor queryById(long id);

    @Query("SELECT * FROM contractor ORDER BY name ASC")
    List<Contractor> queryAll();
}
