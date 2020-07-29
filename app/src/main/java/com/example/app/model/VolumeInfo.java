package com.example.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class VolumeInfo  {
    private String title, publisher, description;
    private ArrayList<String> authors;
    ImageLinks imageLinks;

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public VolumeInfo(String title, String publisher, String description, ArrayList<String> authors) {
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthors() {
        StringBuilder output = new StringBuilder();
        for (String i : authors) {
            output.append(i).append(" ");
        }

        return output.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
 }



