package com.teja.testmvc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teja.testmvc.R;
import com.teja.testmvc.Util;
import com.teja.testmvc.adapter.CategoriesAdapter;
import com.teja.testmvc.api.ApiClient;
import com.teja.testmvc.api.ApiInterface;
import com.teja.testmvc.loader.MyProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CategoriesAdapter.MainCategoryClicked {
    RecyclerView rv_all_categories;
    TextView ctv_no_items_found;
    EditText et_search_view;
    ArrayList<String> arrayList;
    CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();


        arrayList = new ArrayList<>();


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_all_categories.setLayoutManager(mLayoutManager);
        rv_all_categories.setHasFixedSize(true);

        viewVisibility(false);

        if (Util.isNetworkAvailable(MainActivity.this)) {

            makeApiCall();

        } else {
            toastLong("Please check your internet connection");
        }
        et_search_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });


    }

    private void makeApiCall() {
        MyProgressBar myProgressBar = new MyProgressBar(MainActivity.this);

        myProgressBar.show();
        ApiInterface apiService = ApiClient.getClient(MainActivity.this).create(ApiInterface.class);
        Call<ArrayList<String>> call = apiService.getAllCategories();
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                try {
                    if (response.body() != null) {
                        viewVisibility(true);
                        arrayList = response.body();
                        setAdapter(arrayList);

                    } else viewVisibility(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                myProgressBar.hide();
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                viewVisibility(false);
                if (myProgressBar != null)
                    myProgressBar.hide();
            }
        });

    }

    private void findViews() {
        et_search_view = findViewById(R.id.et_search_view);
        rv_all_categories = findViewById(R.id.rv_all_categories);
        ctv_no_items_found = findViewById(R.id.ctv_no_items_found);
    }

    private void setAdapter(ArrayList<String> arrayList) {
        categoriesAdapter = new CategoriesAdapter(arrayList, MainActivity.this, MainActivity.this);
        rv_all_categories.setAdapter(categoriesAdapter);
    }


    private void filter(String text) {
        ArrayList<String> temp = new ArrayList();
        for (String d : arrayList) {
            if (d.contains(text)) {
                temp.add(d);
            }
        }
        if (categoriesAdapter != null)
            categoriesAdapter.updateList(temp);

    }

    private void viewVisibility(boolean b) {
        if (b) {
            ctv_no_items_found.setVisibility(View.GONE);
            rv_all_categories.setVisibility(View.VISIBLE);
        } else {
            ctv_no_items_found.setVisibility(View.VISIBLE);
            rv_all_categories.setVisibility(View.GONE);
        }
    }


    @Override
    public void onCategoryClicked(int position, String cat_name) {

        Intent intent = new Intent(MainActivity.this, SubCategoryActivity.class);
        intent.putExtra("selected_cat_name", cat_name);
        startActivity(intent);
        finish();

    }

    protected void toastLong(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

}