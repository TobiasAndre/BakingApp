package com.tobiasandre.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.tobiasandre.bakingapp.model.Recipe;
import com.tobiasandre.bakingapp.sync.CommandExec;
import com.tobiasandre.bakingapp.sync.GetRecipesTask;
import com.tobiasandre.bakingapp.ui.adapter.RecipesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        GetRecipesTask.Listener,RecipesAdapter.Callbacks {


    private RecipesAdapter mAdapter;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_recipes);
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);//arrumar bind
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Baking App");

        mAdapter = new RecipesAdapter(new ArrayList<Recipe>(),this);
        mRecyclerView.setAdapter(mAdapter);

        getRecipes();
    }

    private void getRecipes(){
        GetRecipesTask.NotifyTaskCompletedCommand command =
                new GetRecipesTask.NotifyTaskCompletedCommand(this);
        new GetRecipesTask(command,this).execute();
    }


    @Override
    public void onGetFinished(CommandExec command) {
        if (command instanceof GetRecipesTask.NotifyTaskCompletedCommand) {

            try {
                mAdapter.add(((GetRecipesTask.NotifyTaskCompletedCommand) command).getRecipes());
            }catch (Exception error){
                System.out.println(error.getMessage());
            }

            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void open(Recipe recipe, int position) {

    }
}