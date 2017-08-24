package com.tobiasandre.bakingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.sync.CommandExec;
import com.tobiasandre.bakingapp.sync.GetRecipesTask;
import com.tobiasandre.bakingapp.sync.NetworkUtils;
import com.tobiasandre.bakingapp.ui.adapter.RecipesAdapter;

import org.json.JSONException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        GetRecipesTask.Listener,RecipesAdapter.Callbacks {


    private RecipesAdapter mAdapter;
    @BindView(R.id.progress)
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getRecipes();
    }

    private void getRecipes(){
        GetRecipesTask.NotifyTaskCompletedCommand command =
                new GetRecipesTask.NotifyTaskCompletedCommand(this);
        new GetRecipesTask(command).execute();
    }


    @Override
    public void onGetFinished(CommandExec command) {
        if (command instanceof GetRecipesTask.NotifyTaskCompletedCommand) {

            mAdapter.add(((GetRecipesTask.NotifyTaskCompletedCommand) command).getRecipes());

            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void open(Recipe recipe, int position) {

    }
}