package com.tobiasandre.bakingapp.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by Tobias Andre on 27/08/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>  {

    private List<Ingredient> ingredients;

    public IngredientAdapter(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        StringBuilder stringBuilder = new StringBuilder(ingredients.get(i).getIngredient());
        stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        viewHolder.ingredient.setText(stringBuilder.toString());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient;

        ViewHolder(View itemView) {
            super(itemView);
            ingredient = (TextView) itemView.findViewById(R.id.ingredient);
        }
    }
}
