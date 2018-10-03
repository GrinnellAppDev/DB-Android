package edu.grinnell.appdev.grinnelldirectory.models;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Person implements Parcelable {
    public static final String PERSON_KEY = "PERSON_KEY";

    private String firstName;
    private String lastName;
    private String nickName;
    private String userName;
    private int classYear;
    private String box;
    private String email;
    private String major;
    private String minor;
    private String address;
    private int phone;
    private String imgPath;
    private String personType;
    private List<String> titles;
    private List<String> departments;
    private String spouse;
    private String homeAddress;
    private int office_phone;
    private String office_email;
    private String office_addr;
    private String office_box;
    private String position_name;
    private List<String> office_hours;

    public Person() {}

    protected Person(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        nickName = in.readString();
        userName = in.readString();
        classYear = in.readInt();
        box = in.readString();
        email = in.readString();
        major = in.readString();
        minor = in.readString();
        address = in.readString();
        phone = in.readInt();
        imgPath = in.readString();
        personType = in.readString();
        titles = in.createStringArrayList();
        departments = in.createStringArrayList();
        spouse = in.readString();
        homeAddress = in.readString();
        office_phone = in.readInt();
        office_email = in.readString();
        office_addr = in.readString();
        office_box = in.readString();
        position_name = in.readString();
        office_hours = in.createStringArrayList();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public int getOffice_phone() {
        return office_phone;
    }

    public void setOffice_phone(int office_phone) {
        this.office_phone = office_phone;
    }

    public String getOffice_email() {
        return office_email;
    }

    public void setOffice_email(String office_email) {
        this.office_email = office_email;
    }

    public String getOffice_addr() {
        return office_addr;
    }

    public void setOffice_addr(String office_addr) {
        this.office_addr = office_addr;
    }

    public String getOffice_box() {
        return office_box;
    }

    public void setOffice_box(String office_box) {
        this.office_box = office_box;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public List<String> getOffice_hours() {
        return office_hours;
    }

    public void setOffice_hours(List<String> office_hours) {
        this.office_hours = office_hours;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(nickName);
        dest.writeString(userName);
        dest.writeInt(classYear);
        dest.writeString(box);
        dest.writeString(email);
        dest.writeString(major);
        dest.writeString(minor);
        dest.writeString(address);
        dest.writeInt(phone);
        dest.writeString(imgPath);
        dest.writeString(personType);
        dest.writeStringList(titles);
        dest.writeStringList(departments);
        dest.writeString(spouse);
        dest.writeString(homeAddress);
        dest.writeInt(office_phone);
        dest.writeString(office_email);
        dest.writeString(office_addr);
        dest.writeString(office_box);
        dest.writeString(position_name);
        dest.writeStringList(office_hours);
    }
}
