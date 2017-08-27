package com.tobiasandre.bakingapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Tobias Andre on 23/08/2017.
 */
@Entity(primaryKeys = {"recipeId","_id"})
public class Step {

    private Long recipeId;
    private Long _id;
    @SerializedName("id")
    private Integer index;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("description")
    private String description;

    @SerializedName("videoURL")
    private String videoURL;

    @SerializedName("thumbnailURL")
    private String thumbnailURL;


    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
