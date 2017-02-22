package edu.grinnell.appdev.grinnelldirectory.Interfaces;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.Model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by nicholasroberson on 2/10/17.
 */

public interface DatabaseAPI {

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @POST("db")
    Call<List<Person>> advancedSearch(@Body User user,
                                   @Query("firstName") String fname,
                                   @Query("lastName") String lname,
                                   @Query("major") String major,
                                   @Query("facStaffOffice") String facStaffOffice,
                                   @Query("concentration") String concentration,
                                   @Query("sga") String sga,
                                   @Query("userName") String userName,
                                   @Query("campusPhone") String campusPhone,
                                   @Query("hiatus") String hiatus,
                                   @Query("homeAddress") String homeAddress,
                                   @Query("classYr") String classYr,
                                   @Query("campusAddress") String campusAddress,
                                   @Query("bldgDorm") String bldgDorm,
                                   @Query("positionDescription") String positionDescription);

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @POST("db")
    Call<List<Person>> simpleSearch(@Body User user,
                                    @Query("firstName") String fname,
                                    @Query("lastName") String lname,
                                    @Query("major") String major,
                                    @Query("classYr") String classYr);

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @POST("db")
    Call<List<Person>> authenticateUser(@Body User user,
                                        @Query("userName") String userName);

}

/*
Fields of API that we can use:

lastName
firstName
major
facStaffOffice
concentration
sga
userName
campusPhone
hiatus
homeAddress
classYr
campusAddress
bldgDorm
positionDescription
 */