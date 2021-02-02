package com.teja.testmvc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.teja.testmvc.R;

import java.util.ArrayList;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> implements Filterable {

    public MainCategoryClicked onMainCategoryClicked;
    ArrayList<String> categoriesList;
    Context context;


    public CategoriesAdapter(ArrayList<String> categoriesList, Context context, MainCategoryClicked onMainCategoryClicked) {
        this.context = context;
        this.categoriesList = categoriesList;
        this.onMainCategoryClicked = onMainCategoryClicked;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_row, parent, false);
        return new ViewHolder(v);
    }

    public void updateList(ArrayList<String> list){
        categoriesList = list;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.atv_cat_title.setText(categoriesList.get(position));

            holder.cv_categories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onMainCategoryClicked != null) {
                        onMainCategoryClicked.onCategoryClicked(position, categoriesList.get(position));
                    }
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    @Override
    public Filter getFilter() {


        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView atv_cat_title;
        CardView cv_categories;

        public ViewHolder(View itemView) {
            super(itemView);
            atv_cat_title = itemView.findViewById(R.id.atv_cat_title);
            cv_categories = itemView.findViewById(R.id.cv_categories);

        }
    }

    public interface MainCategoryClicked {
        void onCategoryClicked(int position, String cat_name);
    }
}
