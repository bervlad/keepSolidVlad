package com.example.app.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class ParcableModel implements Parcelable {

    String title, authors, publisher, description;
    Uri selflink;

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public Uri getSelflink() {
        return selflink;
    }

    public ParcableModel(String title, String authors, String publisher, String description, Uri selflink) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.description = description;
        this.selflink = selflink;
    }


    protected ParcableModel (Parcel in) {
    title = in.readString();
    authors = in.readString();
    publisher = in.readString();
    description = in.readString();
    selflink = Uri.parse(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(selflink.toString());
        dest.writeString(title);
        dest.writeString(authors);
        dest.writeString(publisher);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcableModel> CREATOR = new Creator<ParcableModel>() {
        @Override
        public ParcableModel createFromParcel(Parcel in) {
            return new ParcableModel(in);
        }

        @Override
        public ParcableModel[] newArray(int size) {
            return new ParcableModel[size];
        }
    };
    public String toString() {
        return "Title: " + this.getTitle() +
                "Authors: " + this.getAuthors() + "\n" +
                "Publisher: " + this.getPublisher() + "\n" +
                "Description: " + this.getDescription() ;
    }
}
