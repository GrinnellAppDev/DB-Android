package edu.grinnell.appdev.grinnelldirectory.interfaces;

import edu.grinnell.appdev.grinnelldirectory.models.Person;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DbSearchAPI {

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @GET("list")
    Call<List<Person>> advancedSearch(
        @Query("lastName") String lastName,
        @Query("firstName") String firstName,
        @Query("userName") String userName,
        @Query("campusPhone") String campusPhone,
        @Query("campusAddress") String campusAddress,
        @Query("homeAddress") String homeAddress,
        @Query("classYr") String classYear,
        @Query("facStaffOffice") String facStaffOffice,
        @Query("major") String major,
        @Query("concentration") String concentration,
        @Query("sga") String sgaPosition,
        @Query("hiatus") String onHiatus
    );

}
