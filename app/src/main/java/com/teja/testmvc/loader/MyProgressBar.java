package com.teja.testmvc.loader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


public class MyProgressBar {


    private ProgressBar mProgressBar;

    private Context mContext;

    LoadingView loadingView;

    RelativeLayout relativeLayout;

    public MyProgressBar(Context context) {
        mContext = context;

        ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();

        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        mProgressBar.setIndeterminate(true);

        loadingView = new LoadingView(mContext);
        loadingView.setColorFilter(Color.GREEN);
        LoadingRenderer renderer = new GearLoadingRenderer(mContext);
        loadingView.setLoadingRenderer(renderer);
        loadingView.setMinimumHeight(150);
        loadingView.setMinimumWidth(150);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.setVerticalGravity(Gravity.CENTER);
        // relativeLayout.setBackgroundColor(Color.parseColor("#4D000000"));
        relativeLayout.addView(loadingView);

        layout.addView(relativeLayout, params);
        hide();
    }

    public void show() {
        loadingView.setVisibility(View.VISIBLE);
        AppCompatActivity activity = (AppCompatActivity) mContext;
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hide() {
        loadingView.setVisibility(View.INVISIBLE);
        AppCompatActivity activity = (AppCompatActivity) mContext;
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}
