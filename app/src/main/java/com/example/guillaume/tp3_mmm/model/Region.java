package com.example.guillaume.tp3_mmm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Region implements Parcelable {

    private String name;
    private String url;

    public Region(String name) {
        this.name = name;
    }

    protected Region(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }


    public static final Creator<Region> CREATOR = new Creator<Region>() {
        @Override
        public Region createFromParcel(Parcel in) {
            return new Region(in);
        }

        @Override
        public Region[] newArray(int size) {
            return new Region[size];
        }
    };
}
