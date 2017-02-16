package edu.grinnell.appdev.grinnelldirectory.Interfaces;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by nicholasroberson on 2/10/17.
 */

public interface DatabaseAPI {

    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    @POST("db")
    Call<List<Person>> getStudents(@Body User user,
                                  @Query("firstName") String fname,
                                  @Query("lastName") String lname);
}

