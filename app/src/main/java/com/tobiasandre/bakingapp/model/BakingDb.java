package com.tobiasandre.bakingapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Tobias Andre on 23/08/2017.
 */

@Database(entities = {Recipe.class, Ingredient.class, Step.class}, version = 1)
public abstract class BakingDb extends RoomDatabase {
    public abstract BakingDao getDao();
}
