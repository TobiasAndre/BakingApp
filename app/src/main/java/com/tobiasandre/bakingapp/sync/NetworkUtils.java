package com.tobiasandre.bakingapp.sync;


import com.tobiasandre.bakingapp.model.Recipe;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Tobias Andre on 23/08/2017.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String API_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    private static Retrofit retrofit;

    public static void init(OkHttpClient client) {
        retrofit = new Retrofit.Builder().baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static ApiCalls createService() {
        ApiCalls service = retrofit.create(ApiCalls.class);
        return service;
    }

    public interface ApiCalls {
        @GET("baking.json")
        List<Recipe> getRecipeList();
    }



}
