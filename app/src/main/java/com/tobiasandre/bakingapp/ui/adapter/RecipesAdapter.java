package com.tobiasandre.bakingapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
                .inflate(R.layout.recipe_list_item, parent, false);
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
        final Context mContext = holder.mView.getContext();
        holder.setRecipe(recipe);
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        TextView tvServingCount, tvStepCount;
        TextView tvrecipeName;
        ImageView imageViewRecipe;
        RecyclerView rvIngredients;

        public Recipe mRecipe;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            tvrecipeName = (TextView) itemView.findViewById(R.id.name);
            tvServingCount = (TextView) itemView.findViewById(R.id.tv_serve_count);
            tvStepCount = (TextView) itemView.findViewById(R.id.tv_steps_count);
            rvIngredients = (RecyclerView) itemView.findViewById(R.id.rv_ingredients);
            imageViewRecipe = (ImageView)itemView.findViewById(R.id.image);
            rvIngredients.setLayoutFrozen(true);
            rvIngredients.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            mView = view;
        }

        void setRecipe(Recipe recipe) {
            this.mRecipe = recipe;
            tvrecipeName.setText(recipe.getName());
            tvStepCount.setText(String.format(Locale.getDefault(), "%d", recipe.getSteps().size()));
            tvServingCount.setText(String.format(Locale.getDefault(), "%d", recipe.getServings()));
            IngredientAdapter ingredientAdapter = new IngredientAdapter(recipe.getIngredients());
            rvIngredients.setAdapter(ingredientAdapter);

            Picasso.with(mView.getContext())
                    .load(R.drawable.bakery_ingredients)
                    .config(Bitmap.Config.RGB_565)
                    .into(imageViewRecipe);
        }

        public void cleanUp() {
            final Context context = mView.getContext();
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            //Intent intent = new Intent(context, RecipeDetailsActivity.class);
            //intent.putExtra("recipe", recipe);
            //context.startActivity(intent);
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
