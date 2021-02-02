package com.teja.testmvc.modelInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class EntityInfo implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;


    @SerializedName("entries")
    @Expose
    private List<SubCatinfo> entries;

    public int getCount() {
        return count;
    }

    public List<SubCatinfo> getEntries() {
        return entries;
    }
}
