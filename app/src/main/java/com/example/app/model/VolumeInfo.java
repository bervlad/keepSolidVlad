package com.example.app.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.TypeConverters;

import com.example.app.database.TypesConverter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@TypeConverters({TypesConverter.class})
public class VolumeInfo  {

    @ColumnInfo(name = "bookTitle")
    private String title;

    @ColumnInfo(name = "bookPublisher")
    private String publisher;

    @ColumnInfo(name = "bookDescription")
    private String description;

    @ColumnInfo(name = "bookAuthors")
    private ArrayList<String> authors;

    @Embedded
    ImageLinks imageLinks;

    @ColumnInfo(name = "bookUrl")
    Uri previewLink;

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public VolumeInfo(String title, String publisher, String description, ArrayList<String> authors, Uri previewLink) {
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.authors = authors;
        this.previewLink = previewLink;
    }

    public Uri getPreviewLink() {
        return previewLink;
    }

    public String getTitle() {
        return checkNull(title);
    }

    public String getPublisher() {
        return checkNull(publisher);
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return checkNull(description);
    }

    public String getAuthorsString() {

        StringBuilder output = new StringBuilder();
        if (authors!=null && authors.size()!=0) {
            for (String i : authors) {
                if (i != null && i != "") {
                    if (authors.size()>1 && authors.indexOf(i)!=authors.size()-1) {
                    output.append(i).append(", ");} else output.append(i);
                }
            }
        } else return null;

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

    private String checkNull (String s) {
        if (s!=null) return s; else return "unknown";
    }

 }



