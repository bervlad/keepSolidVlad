package com.example.app.model;

import androidx.room.ColumnInfo;

public class ImageLinks {

    @ColumnInfo(name = "bookImageUrl")
    private String smallThumbnail;

    public ImageLinks(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }
}
