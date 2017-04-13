package edu.grinnell.appdev.grinnelldirectory.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class SimpleResult implements Parcelable {

    public static final String SIMPLE_KEY = "SIMPLE_KEY";

    private List<Person> mPeople;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<SimpleResult>() {
        /**
         * Create a new SimpleResult by deserializing parcel
         * @param parcel A parcel created by SimpleResult.writeToParcel
         * @return a deserialiezd SimpleResult object
         */
        @Override public SimpleResult createFromParcel(Parcel parcel) {
            return new SimpleResult(parcel);
        }

        /**
         * Create a new array of type SimpleResult[] with everything initialized to null
         * @param size the size of the array to be created
         * @return a new array of type SimpleResult[] with everything initialized to null
         */
        @Override public SimpleResult[] newArray(int size) {
            return new SimpleResult[size];
        }
    };

    /**
     * Create a new SimpleResult with non-serialized data
     * @param people A list of people
     */
    public SimpleResult(List<Person> people) {
        this.mPeople = people;
    }

    public List<Person> getPeople() {
        return mPeople;
    }

    /**
     * Create a new SimpleResult by deserializing parcel
     * @param parcel A parcel created by SimpleResult.writeToParcel
     * @return a deserialiezd SimpleResult object
     */
    public SimpleResult(Parcel parcel) {
        mPeople = (List<Person>) parcel.readValue(SimpleResult.class.getClassLoader());
    }

    /**
     * @return a bitmask that specifies if parcelable includes a file descriptor
     */
    @Override public int describeContents() {
        return 0;
    }

    /**
     * serialize `this` into `parcel`
     * @oaram parcel The parcel that the SimpleResult gets serialized into
     * @param flags Defaults to 0, equals Parcelable.PARCELABLE_WRITE_RETURN_VALUE if object being
     * written is a return value
     */
    @Override public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(mPeople);
    }
}
