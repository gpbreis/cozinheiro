package dev.gpbreis.cozinheiro.persistance;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dev.gpbreis.cozinheiro.model.Contractor;

@Database(entities = {Contractor.class}, version = 1, exportSchema = false)
public abstract class ContractorDatabase extends RoomDatabase {

    public abstract ContractorDao contractorDao();
    private static ContractorDatabase instance;

    public static ContractorDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (ContractorDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, ContractorDatabase.class, "cozinheiro.db").allowMainThreadQueries().build();
                }
            }
        }

        return instance;
    }
}
