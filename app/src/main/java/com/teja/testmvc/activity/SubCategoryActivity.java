package com.teja.testmvc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teja.testmvc.R;
import com.teja.testmvc.Util;
import com.teja.testmvc.adapter.SubCategoriesAdapter;
import com.teja.testmvc.api.ApiClient;
import com.teja.testmvc.api.ApiInterface;
import com.teja.testmvc.loader.MyProgressBar;
import com.teja.testmvc.modelInfo.EntityInfo;
import com.teja.testmvc.modelInfo.SubCatinfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {

    RecyclerView rv_sub_categories;
    TextView ctv_no_items_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_sub_categories.setLayoutManager(mLayoutManager);
        rv_sub_categories.setHasFixedSize(true);


        viewVisibility(false);
        if (getIntent() != null) {
            String selected_cat_name = getIntent().getStringExtra("selected_cat_name");
            if (Util.isNetworkAvailable(SubCategoryActivity.this)) {
                makeApiCall(selected_cat_name);
            } else {
                toastLong("Please check your internet connection");
            }


        }


    }

    private void findViews() {
        rv_sub_categories = findViewById(R.id.rv_sub_categories);
        ctv_no_items_found = findViewById(R.id.ctv_no_items_found);
    }

    private void makeApiCall(String selected_cat_name) {

        MyProgressBar myProgressBar = new MyProgressBar(SubCategoryActivity.this);
        myProgressBar.show();
        String[] splitStr = selected_cat_name.trim().split("\\s+");
        String catName = splitStr[0].toLowerCase();
        getSupportActionBar().setTitle(catName);
        ApiInterface apiService = ApiClient.getClient(SubCategoryActivity.this).create(ApiInterface.class);
        Call<EntityInfo> call = apiService.getSubCategories(catName, "true");

        call.enqueue(new Callback<EntityInfo>() {
            @Override
            public void onResponse(Call<EntityInfo> call, Response<EntityInfo> response) {
                try {
                    if (response.body() != null) {
                        viewVisibility(true);
                        EntityInfo entityInfo = response.body();
                        if (entityInfo != null && entityInfo.getEntries() != null && entityInfo.getEntries().size() > 0) {
                            List<SubCatinfo> subCatinfoList = entityInfo.getEntries();
                            setAdapter(subCatinfoList);
                        } else viewVisibility(false);
                    } else viewVisibility(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                myProgressBar.hide();
            }

            @Override
            public void onFailure(Call<EntityInfo> call, Throwable t) {
                viewVisibility(false);
                myProgressBar.hide();
            }
        });
    }


    private void setAdapter(List<SubCatinfo> subCatinfoList) {
        SubCategoriesAdapter subCategoriesAdapter = new SubCategoriesAdapter(subCatinfoList, SubCategoryActivity.this);
        rv_sub_categories.setAdapter(subCategoriesAdapter);
    }

    protected void toastLong(String message) {
        Toast.makeText(SubCategoryActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void viewVisibility(boolean b) {
        if (b) {
            ctv_no_items_found.setVisibility(View.GONE);
            rv_sub_categories.setVisibility(View.VISIBLE);
        } else {
            ctv_no_items_found.setVisibility(View.VISIBLE);
            rv_sub_categories.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(SubCategoryActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}