package com.example.app.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeResponse {

    @SerializedName("kind")
    private String kind;

    @SerializedName("totalItems")
    private long totalItems;

    @SerializedName("items")
    private List<VolumeModelItem> volumeItems;

    public List<VolumeModelItem> getItems() {

        return volumeItems;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public void setVolumeItems(List<VolumeModelItem> volumeItems) {
        this.volumeItems = volumeItems;
    }

    public String getKind() {
        return kind;
    }

    public long getTotalItems() {
        return totalItems;
    }


}
