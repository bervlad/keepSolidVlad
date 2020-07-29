package com.example.app.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.app.collections.Car;

import java.util.ArrayList;

public class VolumeModelItem  {
private int id;
private Uri selflink;
VolumeInfo volumeInfo;

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public VolumeModelItem(int id, Uri selflink) {
        this.id = id;
        this.selflink = selflink;
    }

    public int getId() {
        return id;
    }

    public Uri getSelflink() {
        return selflink;
    }

    public String getTitle() {
        return volumeInfo.getTitle();
    }

    public String getPublisher() {
        return volumeInfo.getPublisher();
    }

    public String getDescription() {
        return volumeInfo.getDescription();
    }

    public String getAuthors() {
        return volumeInfo.getAuthors();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSelflink(Uri selflink) {
        this.selflink = selflink;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolumeModelItem that = (VolumeModelItem) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Title: " + this.getTitle() +
                "Authors: " + this.getAuthors() + "\n" +
                "Publisher: " + this.getPublisher() + "\n" +
                "Description: " + this.getDescription() ;
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

