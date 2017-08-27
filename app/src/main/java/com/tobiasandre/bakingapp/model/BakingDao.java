package com.tobiasandre.bakingapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.WorkerThread;

import java.util.List;

/**
 * Created by Tobias Andre on 25/08/2017.
 */

@Dao
@WorkerThread
public interface BakingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRecipe(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(List<Ingredient> ingredient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStep(List<Step> step);

    @Query("Select * FROM recipe")
    List<Recipe> getAllRecipes();

    @Query("Select * FROM recipe WHERE id=:recipeId")
    Recipe getRecipe(int recipeId);

    @Query("Select * FROM ingredient WHERE recipeId=:recipeId")
    List<Ingredient> getAllIngredients(int recipeId);

    @Query("Select * FROM step WHERE recipeId=:recipeId")
    List<Step> getAllSteps(int recipeId);

}
