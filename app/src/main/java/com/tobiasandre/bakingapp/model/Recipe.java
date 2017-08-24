package com.tobiasandre.bakingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by Tobias Andre on 23/08/2017.
 */
@Table(database = BakingDb.class)
public class Recipe extends BaseModel {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("ingredients")
    @Expose
    @ForeignKey
    private List<Ingredient> ingredients = null;

    @SerializedName("steps")
    @Expose
    @ForeignKey
    private List<Step> steps = null;


    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;


}
