package edu.grinnell.appdev.grinnelldirectory.interfaces;

import edu.grinnell.appdev.grinnelldirectory.models.DBRespoonse;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Interface describing an advanced search of the database.
 * Used by retrofit to create an implementation of the API.
 * Simple search is advanced search but with blank strings in the advanced fields.
 */

public interface DbSearchAPI {

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @GET("fetch?")
    Call<DBRespoonse> advancedSearch(
        @Query("lastName") String lastName,
        @Query("firstName") String firstName,
        @Query("userName") String userName,
        @Query("phone") String phone,
        @Query("address") String address,
        @Query("classYear") String classYear,
        @Query("major") String major,
        @Query("sga") String sgaPosition,
        @Query("token") String cookie
    );

}
