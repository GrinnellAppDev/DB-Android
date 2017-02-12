package edu.grinnell.appdev.grinnelldirectory.Interfaces;

import edu.grinnell.appdev.grinnelldirectory.Person;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nicholasroberson on 2/10/17.
 */

public interface DatabaseAPI {
    @GET("DotNet/WebServices/api/db")
    Call<Person> getStudent(@Query("firstName") String fname,
                            @Query("lastName") String lname);
}

