package edu.grinnell.appdev.grinnelldirectory.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("nickName")
    @Expose
    private Object nickName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("classYear")
    @Expose
    private Object classYear;
    @SerializedName("reunionYear")
    @Expose
    private Object reunionYear;
    @SerializedName("box")
    @Expose
    private String box;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("major")
    @Expose
    private Object major;
    @SerializedName("minor")
    @Expose
    private Object minor;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private Object address2;
    @SerializedName("address3")
    @Expose
    private Object address3;
    @SerializedName("address4")
    @Expose
    private Object address4;
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
    private Object country;
    @SerializedName("bldg")
    @Expose
    private Object bldg;
    @SerializedName("room")
    @Expose
    private Object room;
    @SerializedName("spouse")
    @Expose
    private String spouse;
    @SerializedName("alienStatus")
    @Expose
    private Object alienStatus;
    @SerializedName("hiatus")
    @Expose
    private Object hiatus;
    @SerializedName("imgPath")
    @Expose
    private String imgPath;
    @SerializedName("office_phone")
    @Expose
    private Object officePhone;
    @SerializedName("office_email")
    @Expose
    private Object officeEmail;
    @SerializedName("office_addr")
    @Expose
    private Object officeAddr;
    @SerializedName("office_box")
    @Expose
    private Object officeBox;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("page_order")
    @Expose
    private Object pageOrder;
    @SerializedName("crs_ID")
    @Expose
    private Object crsID;
    @SerializedName("position_name")
    @Expose
    private Object positionName;
    @SerializedName("office_hours_1")
    @Expose
    private Object officeHours1;
    @SerializedName("office_hours_2")
    @Expose
    private Object officeHours2;
    @SerializedName("office_hours_3")
    @Expose
    private Object officeHours3;
    @SerializedName("office_hours_4")
    @Expose
    private Object officeHours4;
    @SerializedName("personType")
    @Expose
    private String personType;
    @SerializedName("deptMajorClass")
    @Expose
    private Object deptMajorClass;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title2")
    @Expose
    private Object title2;
    @SerializedName("title3")
    @Expose
    private Object title3;
    @SerializedName("title4")
    @Expose
    private Object title4;
    @SerializedName("title5")
    @Expose
    private String title5;
    @SerializedName("title6")
    @Expose
    private Object title6;
    @SerializedName("title7")
    @Expose
    private Object title7;
    @SerializedName("title8")
    @Expose
    private Object title8;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getNickName() {
        return nickName;
    }

    public void setNickName(Object nickName) {
        this.nickName = nickName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getClassYear() {
        return classYear;
    }

    public void setClassYear(Object classYear) {
        this.classYear = classYear;
    }

    public Object getReunionYear() {
        return reunionYear;
    }

    public void setReunionYear(Object reunionYear) {
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

    public Object getMajor() {
        return major;
    }

    public void setMajor(Object major) {
        this.major = major;
    }

    public Object getMinor() {
        return minor;
    }

    public void setMinor(Object minor) {
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

    public Object getAddress2() {
        return address2;
    }

    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    public Object getAddress3() {
        return address3;
    }

    public void setAddress3(Object address3) {
        this.address3 = address3;
    }

    public Object getAddress4() {
        return address4;
    }

    public void setAddress4(Object address4) {
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

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getBldg() {
        return bldg;
    }

    public void setBldg(Object bldg) {
        this.bldg = bldg;
    }

    public Object getRoom() {
        return room;
    }

    public void setRoom(Object room) {
        this.room = room;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public Object getAlienStatus() {
        return alienStatus;
    }

    public void setAlienStatus(Object alienStatus) {
        this.alienStatus = alienStatus;
    }

    public Object getHiatus() {
        return hiatus;
    }

    public void setHiatus(Object hiatus) {
        this.hiatus = hiatus;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Object getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(Object officePhone) {
        this.officePhone = officePhone;
    }

    public Object getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(Object officeEmail) {
        this.officeEmail = officeEmail;
    }

    public Object getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(Object officeAddr) {
        this.officeAddr = officeAddr;
    }

    public Object getOfficeBox() {
        return officeBox;
    }

    public void setOfficeBox(Object officeBox) {
        this.officeBox = officeBox;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(Object pageOrder) {
        this.pageOrder = pageOrder;
    }

    public Object getCrsID() {
        return crsID;
    }

    public void setCrsID(Object crsID) {
        this.crsID = crsID;
    }

    public Object getPositionName() {
        return positionName;
    }

    public void setPositionName(Object positionName) {
        this.positionName = positionName;
    }

    public Object getOfficeHours1() {
        return officeHours1;
    }

    public void setOfficeHours1(Object officeHours1) {
        this.officeHours1 = officeHours1;
    }

    public Object getOfficeHours2() {
        return officeHours2;
    }

    public void setOfficeHours2(Object officeHours2) {
        this.officeHours2 = officeHours2;
    }

    public Object getOfficeHours3() {
        return officeHours3;
    }

    public void setOfficeHours3(Object officeHours3) {
        this.officeHours3 = officeHours3;
    }

    public Object getOfficeHours4() {
        return officeHours4;
    }

    public void setOfficeHours4(Object officeHours4) {
        this.officeHours4 = officeHours4;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Object getDeptMajorClass() {
        return deptMajorClass;
    }

    public void setDeptMajorClass(Object deptMajorClass) {
        this.deptMajorClass = deptMajorClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getTitle2() {
        return title2;
    }

    public void setTitle2(Object title2) {
        this.title2 = title2;
    }

    public Object getTitle3() {
        return title3;
    }

    public void setTitle3(Object title3) {
        this.title3 = title3;
    }

    public Object getTitle4() {
        return title4;
    }

    public void setTitle4(Object title4) {
        this.title4 = title4;
    }

    public String getTitle5() {
        return title5;
    }

    public void setTitle5(String title5) {
        this.title5 = title5;
    }

    public Object getTitle6() {
        return title6;
    }

    public void setTitle6(Object title6) {
        this.title6 = title6;
    }

    public Object getTitle7() {
        return title7;
    }

    public void setTitle7(Object title7) {
        this.title7 = title7;
    }

    public Object getTitle8() {
        return title8;
    }

    public void setTitle8(Object title8) {
        this.title8 = title8;
    }

}