package edu.grinnell.appdev.grinnelldirectory;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Interfaces.DatabaseAPI;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.Model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APICaller {

    private static final String BASE_URL = "https://itwebappstest.grinnell.edu/DotNet/WebServices/api/";

    private Retrofit mRetrofit;
    private DatabaseAPI dbAPI;
    private Call<List<Person>> personQuery;
    private APICallerInterface apiCallerInterface;
    private User user;

    public APICaller(User user1, APICallerInterface apiInterface) {
        mRetrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        dbAPI = mRetrofit.create(DatabaseAPI.class);
        apiCallerInterface = apiInterface;
        user = user1;
    }

    public void simpleSearch(User user, List<String> fields) {
        personQuery = dbAPI.simpleSearch(user, fields.get(0), fields.get(1), fields.get(2), fields.get(3));

        personQuery.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.isSuccessful()) {
                    List<Person> people = response.body();
                    apiCallerInterface.onSearchSuccess(people);
                } else {
                    try {
                        Log.e("ERROR_SIMPLE_SEARCH", response.errorBody().string());
                        apiCallerInterface.onServerFailure(response.raw().message());
                    } catch (IOException e) {
                        Log.e("API_FAILURE_EXCEPTION", e.toString());
                        apiCallerInterface.onServerFailure(e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                apiCallerInterface.onNetworkingError(t.toString());
                Log.e("API_SIMPLE_FAILURE", t.toString());
                apiCallerInterface.onNetworkingError(t.getMessage());
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
                if (response.isSuccessful()) {
                    List<Person> people = response.body();
                    apiCallerInterface.onSearchSuccess(people);
                } else {
                    try {
                        Log.e("ERROR_ADV_SEARCH", response.errorBody().string());
                        apiCallerInterface.onServerFailure(response.raw().message());
                    } catch (IOException e) {
                        Log.e("API_FAILURE_EXCEPTION", e.toString());
                        apiCallerInterface.onServerFailure(e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                apiCallerInterface.onNetworkingError(t.toString());
            }
        });
    }

    public void authenticateUser(User user, List<String> fields) {
        personQuery = dbAPI.authenticateUser(user, fields.get(0));

        personQuery.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.isSuccessful()) {
                    List<Person> people = response.body();
                    apiCallerInterface.authenticateUserCallSuccess(people);
                } else {
                    try {
                        Log.e("ERROR_AUTH_USER_SEARCH", response.errorBody().string());
                        apiCallerInterface.onServerFailure(response.raw().message());
                    } catch (IOException e) {
                        Log.e("API_FAILURE_EXCEPTION", e.toString());
                        apiCallerInterface.onServerFailure(e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                apiCallerInterface.onNetworkingError(t.toString());
            }
        });
    }
}