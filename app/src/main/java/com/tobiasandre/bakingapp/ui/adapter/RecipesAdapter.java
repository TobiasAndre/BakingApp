package com.tobiasandre.bakingapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Tobias Andre on 23/08/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private final static String TAG = RecipesAdapter.class.getSimpleName();

    private final ArrayList<Recipe> mRecipes;
    private final Callbacks mCallbacks;

    public interface Callbacks {
        void open(Recipe recipe, int position);
    }

    public RecipesAdapter(ArrayList<Recipe> recipes, Callbacks callbacks) {
        mRecipes = recipes;
        this.mCallbacks = callbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipes_list, parent, false);
        final Context context = view.getContext();


        return new ViewHolder(view);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cleanUp();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Recipe recipe = mRecipes.get(position);
        final Context context = holder.mView.getContext();
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public Recipe mRecipe;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }

        public void cleanUp() {
            final Context context = mView.getContext();
        }

    }

    public void add(List<Recipe> recipeList) {
        mRecipes.clear();
        mRecipes.addAll(recipeList);
        notifyDataSetChanged();
    }

    public ArrayList<Recipe> getRecipes() {
        return mRecipes;
    }

}
