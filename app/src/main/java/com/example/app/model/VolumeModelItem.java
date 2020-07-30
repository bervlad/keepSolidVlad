package com.example.app.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class VolumeModelItem  {
private String id;
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
        return volumeInfo.getAuthors();
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

//    protected VolumeModelItem(Parcel in) {
//        id = in.readInt();
//        selflink = Uri.parse(in.readString());
//        volumeInfo = in.readParcelable(Volume);
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(selflink.toString());
//        dest.writeInt(id);
//        dest.writeString(color);
//        dest.writeString(name);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<Car> CREATOR = new Creator<Car>() {
//        @Override
//        public Car createFromParcel(Parcel in) {
//            return new Car(in);
//        }
//
//        @Override
//        public Car[] newArray(int size) {
//            return new Car[size];
//        }
//    };

}

