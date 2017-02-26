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
                                      @Query("firstName") String firstName,
                                      @Query("lastName") String lastName,
                                      @Query("major") String major,
                                      @Query("facStaffOffice") String facStaffOffice,
                                      @Query("concentration") String concentration,
                                      @Query("sga") String sga,
                                      @Query("userName") String userName,
                                      @Query("campusPhone") String campusPhone,
                                      @Query("hiatus") String hiatus,
                                      @Query("homeAddress") String homeAddress,
                                      @Query("classYr") String classYear,
                                      @Query("campusAddress") String campusAddress,
                                      @Query("bldgDorm") String buildingDorm,
                                      @Query("positionDescription") String positionDescription);

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @POST("db")
    Call<List<Person>> simpleSearch(@Body User user,
                                    @Query("firstName") String firstName,
                                    @Query("lastName") String lastName,
                                    @Query("major") String major,
                                    @Query("classYr") String classYear);

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @POST("db")
    Call<List<Person>> authenticateUser(@Body User user,
                                        @Query("userName") String userName);

}