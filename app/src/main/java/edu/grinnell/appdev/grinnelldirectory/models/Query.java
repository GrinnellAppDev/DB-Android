package edu.grinnell.appdev.grinnelldirectory.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a search query.
 *
 * This class is needed to pass query information from the search page to the results page,
 * where the search is actually performed.
 */

public class Query implements Parcelable {

    public static final String QUERY_KEY = "QUERY_KEY";

    private String lastName;
    private String firstName;
    private String userName;
    private String campusPhone;
    private String campusAddress;
    private String homeAddress;
    private String classYear;
    private String facStaffOffice;
    private String major;
    private String concentration;
    private String sgaPosition;
    private String onHiatus;

    public Query() {}

    protected Query(Parcel in) {
        lastName = in.readString();
        firstName = in.readString();
        userName = in.readString();
        campusPhone = in.readString();
        campusAddress = in.readString();
        homeAddress = in.readString();
        classYear = in.readString();
        facStaffOffice = in.readString();
        major = in.readString();
        concentration = in.readString();
        sgaPosition = in.readString();
        onHiatus = in.readString();
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lastName);
        dest.writeString(firstName);
        dest.writeString(userName);
        dest.writeString(campusPhone);
        dest.writeString(campusAddress);
        dest.writeString(homeAddress);
        dest.writeString(classYear);
        dest.writeString(facStaffOffice);
        dest.writeString(major);
        dest.writeString(concentration);
        dest.writeString(sgaPosition);
        dest.writeString(onHiatus);
    }

    @Override public int describeContents() {
        return 0;
    }

    public static final Creator<Query> CREATOR = new Creator<Query>() {
        @Override public Query createFromParcel(Parcel in) {
            return new Query(in);
        }

        @Override public Query[] newArray(int size) {
            return new Query[size];
        }
    };

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCampusPhone() {
        return campusPhone;
    }

    public void setCampusPhone(String campusPhone) {
        this.campusPhone = campusPhone;
    }

    public String getCampusAddress() {
        return campusAddress;
    }

    public void setCampusAddress(String campusAddress) {
        this.campusAddress = campusAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    public String getFacStaffOffice() {
        return facStaffOffice;
    }

    public void setFacStaffOffice(String facStaffOffice) {
        this.facStaffOffice = facStaffOffice;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getSgaPosition() {
        return sgaPosition;
    }

    public void setSgaPosition(String sgaPosition) {
        this.sgaPosition = sgaPosition;
    }

    public String getOnHiatus() {
        return onHiatus;
    }

    public void setOnHiatus(String onHiatus) {
        this.onHiatus = onHiatus;
    }
}
