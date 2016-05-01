package me.kamadi.samsungnewscrawler.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class Category implements Parcelable {
    private String name;
    private String param;
    public Category(String name, String param) {
        this.name = name;
        this.param = param;
    }


    protected Category(Parcel in) {
        name = in.readString();
        param = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(param);
    }
}
