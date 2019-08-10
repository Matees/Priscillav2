package com.example.matej.priscilla_v2;

import android.os.Parcel;
import android.os.Parcelable;

public class ExpandableItemTitle implements Parcelable {
    public final String title;

    public ExpandableItemTitle(String title){
        this.title = title;
    }

    protected ExpandableItemTitle(Parcel in) {
        title = in.readString();
    }

    public static final Creator<ExpandableItemTitle> CREATOR = new Creator<ExpandableItemTitle>() {
        @Override
        public ExpandableItemTitle createFromParcel(Parcel in) {
            return new ExpandableItemTitle(in);
        }

        @Override
        public ExpandableItemTitle[] newArray(int size) {
            return new ExpandableItemTitle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
