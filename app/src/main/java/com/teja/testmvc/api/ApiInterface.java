package com.teja.testmvc.api;

import com.teja.testmvc.modelInfo.EntityInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("categories")
    Call<ArrayList<String>> getAllCategories();


    @GET("entries")
    Call<EntityInfo> getSubCategories(@Query("category") String category, @Query("https") String https);

}
