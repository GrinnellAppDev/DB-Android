package edu.grinnell.appdev.grinnelldirectory;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Interfaces.DatabaseAPI;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.Model.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nicholasroberson on 2/26/17.
 */

public abstract class APICaller {

    private Retrofit mRetrofit;
    private DatabaseAPI dbAPI;
    private Call<List<Person>> personQuery;
    private APICallerInterface apiCallerInterface;
    private User user;

    //
    public APICaller(User user, APICallerInterface apiInterface, String BASE_URL) {
        this.mRetrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        this.dbAPI = mRetrofit.create(DatabaseAPI.class);
        this.apiCallerInterface = apiInterface;
        this.user = user;
    }

    public void simpleSearch(User user, List<String> fields) {
    }

    public void advancedSearch(User user, List<String> fields) {
    }

    public void authenticateUser(User user, List<String> fields) {
    }

}
