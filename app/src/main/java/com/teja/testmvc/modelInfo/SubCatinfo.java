package com.teja.testmvc.modelInfo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SubCatinfo implements Serializable {


    @SerializedName("API")
    @Expose
    private String API;

    @SerializedName("Description")
    @Expose
    private String Description;

    @SerializedName("Auth")
    @Expose
    private String Auth;

    @SerializedName("HTTPS")
    @Expose
    private boolean HTTPS;

    @SerializedName("Cors")
    @Expose
    private String Cors;

    @SerializedName("Link")
    @Expose
    private String Link;

    @SerializedName("Category")
    @Expose
    private String Category;

    public String getAPI() {
        return API;
    }

    public String getDescription() {
        return Description;
    }

    public String getAuth() {
        return Auth;
    }

    public boolean isHTTPS() {
        return HTTPS;
    }

    public String getCors() {
        return Cors;
    }

    public String getLink() {
        return Link;
    }

    public String getCategory() {
        return Category;
    }
}
