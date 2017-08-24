package com.tobiasandre.bakingapp.sync;

import android.os.AsyncTask;
import android.util.Log;

import com.tobiasandre.bakingapp.model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tobias Andre on 23/08/2017.
 */

public class GetRecipesTask extends AsyncTask<Void, Void, List<Recipe>> {

    public static String TAG = GetRecipesTask.class.getSimpleName();

    private final NotifyTaskCompletedCommand mCommand;

    public interface Listener {
        void onGetFinished(CommandExec command);
    }

    public static class NotifyTaskCompletedCommand implements CommandExec {
        private GetRecipesTask.Listener mListener;
        private List<Recipe> mRecipes;

        public NotifyTaskCompletedCommand(GetRecipesTask.Listener listener) {
            mListener = listener;
        }

        @Override
        public void execute() {
            mListener.onGetFinished(this);
        }

        public List<Recipe> getRecipes() {
            return mRecipes;
        }
    }

    public GetRecipesTask(NotifyTaskCompletedCommand command) {
        mCommand = command;
    }

    @Override
    protected void onPostExecute(List<Recipe> recipes) {
        if (recipes != null) {
            mCommand.mRecipes = recipes;
        } else {
            mCommand.mRecipes = new ArrayList<>();
        }
        mCommand.execute();

    }

    @Override
    protected List<Recipe> doInBackground(Void... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipesService service = retrofit.create(RecipesService.class);
        Call<List<Recipe>> call = service.discoverRecipes();
        try {
            SyncRecipes(call.execute().body());


        } catch (IOException e) {
            Log.e(TAG, "Ocorreu um problema ao obter as receitas:", e);
        }
        return null;
    }

    private void SyncRecipes(List<Recipe> recipes){
        try {
            if(recipes!=null) {
                for (Recipe r : recipes) {
                    r.save();
                }
            }
        }catch (Exception erro){
            Log.e(TAG,erro.getMessage());
        }
    }

}
