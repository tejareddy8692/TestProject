package com.teja.testmvc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.teja.testmvc.R;
import com.teja.testmvc.modelInfo.SubCatinfo;

import java.util.List;


public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.ViewHolder> {

    List<SubCatinfo> subCatinfoList;
    Context context;


    public SubCategoriesAdapter(  List<SubCatinfo> subCatinfoList, Context context) {
        this.context = context;
        this.subCatinfoList = subCatinfoList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_categories_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {

            holder.atv_sub_cat_title.setText(subCatinfoList.get(position).getAPI());
            holder.atv_sub_cat_desp.setText(subCatinfoList.get(position).getDescription());



        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return subCatinfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView atv_sub_cat_title;
        TextView atv_sub_cat_desp;
        CardView cv_categories;

        public ViewHolder(View itemView) {
            super(itemView);
            atv_sub_cat_title = itemView.findViewById(R.id.atv_sub_cat_title);
            atv_sub_cat_desp = itemView.findViewById(R.id.atv_sub_cat_desp);
            cv_categories = itemView.findViewById(R.id.cv_categories);

        }
    }

}
