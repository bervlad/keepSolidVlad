package com.example.app.model;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "volumeItemsTable")
public class VolumeModelItem  {

    @PrimaryKey
    @NonNull
    private String id;

    @Embedded
    VolumeInfo volumeInfo;

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public VolumeModelItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Uri getSelflink() {
        return  checkNull(volumeInfo.getPreviewLink());
    }

    public String getTitle() {
        return volumeInfo.getTitle();
    }

    public String getPublisher() {
        return volumeInfo.getPublisher();
    }

    public String getDescription() {

        String descr="";
        if (volumeInfo.getDescription().length()>100) descr=volumeInfo.getDescription().substring(0,100)+"...";
        else descr = volumeInfo.getDescription();

        return descr;
    }

    public String getAuthors() {
        return volumeInfo.getAuthorsString();
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolumeModelItem that = (VolumeModelItem) o;

        return id == that.id;

    }

    @Override
    public String toString() {
        String descr=this.getDescription();

        return "Title: " + this.getTitle() + "\n" +
                "Authors: " + this.getAuthors() + "\n" +
                "Publisher: " + this.getPublisher() + "\n" +
                "Description: " + descr  + "\n";
    }

    private Uri checkNull (Uri link) {
        if (link!=null) return link; else return Uri.parse("");
    }


}

