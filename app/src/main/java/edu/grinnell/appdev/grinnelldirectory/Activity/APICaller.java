package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Interfaces.DatabaseAPI;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nicholasroberson on 2/15/17.
 */

public class APICaller implements APICallerInterface {

    private String baseUrl = "https://itwebappstest.grinnell.edu/DotNet/WebServices/api/";
    private Retrofit retrofit;
    private DatabaseAPI dbAPI;
    private Call<List<Person>> personQuery;
    private User user;

    public APICaller(User user) {
        retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        dbAPI = retrofit.create(DatabaseAPI.class);
        user = new User("test1stu", "selfserv1");
    }

    public void simpleSearch(User user, List<String> fields) {
        personQuery = dbAPI.simpleSearch(user, fields.get(0), fields.get(1), fields.get(2), fields.get(3));

        personQuery.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.body() != null) {
                    Log.d("API_SUCCESS", "API returned list of people.");
                    // response.body() is the list of people, set to 'people'
                    List<Person> people = response.body();
                    simpleSearchCall(people);
                } else {
                    try {
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("API_FAILURE_EXCEPTION", e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.e("API_FAILURE", t.toString());
            }
        });
    }

    public void advancedSearch(User user, List<String> fields) {
        personQuery = dbAPI.advancedSearch(user, fields.get(0), fields.get(1), fields.get(2)
                , fields.get(3), fields.get(4), fields.get(5), fields.get(6), fields.get(7)
                , fields.get(8), fields.get(9), fields.get(10), fields.get(12), fields.get(13)
                , fields.get(14));

        personQuery.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.body() != null) {
                    Log.d("API_SUCCESS", "API returned list of people.");
                    // response.body() is the list of people, set to 'people'
                    List<Person> people = response.body();
                    advancedSearchCall(people);
                } else {
                    try {
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("API_FAILURE_EXCEPTION", e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.e("API_FAILURE", t.toString());
            }
        });
    }

    public void authenticateUser(User user, List<String> fields) {
        personQuery = dbAPI.authenticateUser(user, fields.get(0));

        personQuery.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.body() != null) {
                    Log.d("API_SUCCESS", "API returned list of people.");
                    // response.body() is the list of people, set to 'people'
                    List<Person> people = response.body();
                    authenticateUserCall(people);

                } else {
                    try {
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("API_FAILURE_EXCEPTION", e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.e("API_FAILURE", t.toString());
            }
        });
    }

    @Override
    public List<Person> simpleSearchCall(List<Person> people) {
        Log.e("TEST_INTERFACE_API",people.get(0).getClassYear().toString());
        return people;
    }

    @Override
    public List<Person> advancedSearchCall(List<Person> people) {
        Log.e("TEST_INTERFACE_API",people.get(0).getClassYear().toString());
        return people;
    }

    @Override
    public List<Person> authenticateUserCall(List<Person> people) {
        Log.e("TEST_INTERFACE_API",people.get(0).getClassYear().toString());
        return people;
    }
}

/*
Make simpleSearch, advancedSearch, and authenticateUser functions for both.

New interface: serverResponse, serverError, connectionError.
 */