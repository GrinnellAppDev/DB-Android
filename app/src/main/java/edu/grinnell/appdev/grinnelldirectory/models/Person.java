package edu.grinnell.appdev.grinnelldirectory.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("nickName")
    @Expose
    private String nickName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("classYear")
    @Expose
    private String classYear;
    @SerializedName("reunionYear")
    @Expose
    private String reunionYear;
    @SerializedName("box")
    @Expose
    private String box;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("major")
    @Expose
    private String major;
    @SerializedName("minor")
    @Expose
    private String minor;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("address3")
    @Expose
    private String address3;
    @SerializedName("address4")
    @Expose
    private String address4;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("homePhone")
    @Expose
    private String homePhone;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("bldg")
    @Expose
    private String bldg;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("spouse")
    @Expose
    private String spouse;
    @SerializedName("alienStatus")
    @Expose
    private String alienStatus;
    @SerializedName("hiatus")
    @Expose
    private String hiatus;
    @SerializedName("imgPath")
    @Expose
    private String imgPath;
    @SerializedName("office_phone")
    @Expose
    private String officePhone;
    @SerializedName("office_email")
    @Expose
    private String officeEmail;
    @SerializedName("office_addr")
    @Expose
    private String officeAddr;
    @SerializedName("office_box")
    @Expose
    private String officeBox;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("page_order")
    @Expose
    private String pageOrder;
    @SerializedName("crs_ID")
    @Expose
    private String crsID;
    @SerializedName("position_name")
    @Expose
    private String positionName;
    @SerializedName("office_hours_1")
    @Expose
    private String officeHours1;
    @SerializedName("office_hours_2")
    @Expose
    private String officeHours2;
    @SerializedName("office_hours_3")
    @Expose
    private String officeHours3;
    @SerializedName("office_hours_4")
    @Expose
    private String officeHours4;
    @SerializedName("personType")
    @Expose
    private String personType;
    @SerializedName("deptMajorClass")
    @Expose
    private String deptMajorClass;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title2")
    @Expose
    private String title2;
    @SerializedName("title3")
    @Expose
    private String title3;
    @SerializedName("title4")
    @Expose
    private String title4;
    @SerializedName("title5")
    @Expose
    private String title5;
    @SerializedName("title6")
    @Expose
    private String title6;
    @SerializedName("title7")
    @Expose
    private String title7;
    @SerializedName("title8")
    @Expose
    private String title8;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    public String getReunionYear() {
        return reunionYear;
    }

    public void setReunionYear(String reunionYear) {
        this.reunionYear = reunionYear;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBldg() {
        return bldg;
    }

    public void setBldg(String bldg) {
        this.bldg = bldg;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getAlienStatus() {
        return alienStatus;
    }

    public void setAlienStatus(String alienStatus) {
        this.alienStatus = alienStatus;
    }

    public String getHiatus() {
        return hiatus;
    }

    public void setHiatus(String hiatus) {
        this.hiatus = hiatus;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    public String getOfficeBox() {
        return officeBox;
    }

    public void setOfficeBox(String officeBox) {
        this.officeBox = officeBox;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(String pageOrder) {
        this.pageOrder = pageOrder;
    }

    public String getCrsID() {
        return crsID;
    }

    public void setCrsID(String crsID) {
        this.crsID = crsID;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getOfficeHours1() {
        return officeHours1;
    }

    public void setOfficeHours1(String officeHours1) {
        this.officeHours1 = officeHours1;
    }

    public String getOfficeHours2() {
        return officeHours2;
    }

    public void setOfficeHours2(String officeHours2) {
        this.officeHours2 = officeHours2;
    }

    public String getOfficeHours3() {
        return officeHours3;
    }

    public void setOfficeHours3(String officeHours3) {
        this.officeHours3 = officeHours3;
    }

    public String getOfficeHours4() {
        return officeHours4;
    }

    public void setOfficeHours4(String officeHours4) {
        this.officeHours4 = officeHours4;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getDeptMajorClass() {
        return deptMajorClass;
    }

    public void setDeptMajorClass(String deptMajorClass) {
        this.deptMajorClass = deptMajorClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getTitle4() {
        return title4;
    }

    public void setTitle4(String title4) {
        this.title4 = title4;
    }

    public String getTitle5() {
        return title5;
    }

    public void setTitle5(String title5) {
        this.title5 = title5;
    }

    public String getTitle6() {
        return title6;
    }

    public void setTitle6(String title6) {
        this.title6 = title6;
    }

    public String getTitle7() {
        return title7;
    }

    public void setTitle7(String title7) {
        this.title7 = title7;
    }

    public String getTitle8() {
        return title8;
    }

    public void setTitle8(String title8) {
        this.title8 = title8;
    }

}