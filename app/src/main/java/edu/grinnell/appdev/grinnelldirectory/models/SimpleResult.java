package edu.grinnell.appdev.grinnelldirectory.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class SimpleResult implements Parcelable {

    List<Person> mPeople;

    static Parcelable.Creator CREATOR = new Parcelable.Creator<SimpleResult>() {
        @Override public SimpleResult createFromParcel(Parcel parcel) {
            return new SimpleResult(parcel);
        }

        public SimpleResult[] newArray(int size) {
            return new SimpleResult[size];
        }
    };

    public SimpleResult(List<Person> people) {
        this.mPeople = people;
    }

    public SimpleResult(Parcel parcel) {
        mPeople = (List<Person>) parcel.readValue(SimpleResult.class.getClassLoader());
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(mPeople);
    }
}
