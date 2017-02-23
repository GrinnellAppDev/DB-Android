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

    // Just for Simple Search
    private static final int FIRST_NAME_FIELD = 0;
    private static final int LAST_NAME_FIELD = 1;
    private static final int MAJOR_FIELD = 2;
    private static final int CLASS_YEAR_FIELD = 3;
    // Additional constants for Advanced Search
    private static final int CONCENTRATION_FIELD = 4;
    private static final int SGA_FIELD = 5;
    private static final int USERNAME_FIELD = 6;
    private static final int CAMPUS_PHONE_FIELD = 7;
    private static final int HIATUS_FIELD = 8;
    private static final int HOME_ADDRESS_FIELD = 9;
    private static final int FAC_STAFF_OFFICE_FIELD = 10;
    private static final int CAMPUS_ADDRESS_FIELD = 11;
    private static final int BUILDING_DORM_FIELD = 12;
    private static final int POSITION_DESCRIPTION_FIELD = 13;


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
        if (user != null && fields != null) {
            personQuery = dbAPI.simpleSearch(user, fields.get(FIRST_NAME_FIELD), fields.get(LAST_NAME_FIELD),
                    fields.get(MAJOR_FIELD), fields.get(CLASS_YEAR_FIELD));

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
    }

    public void advancedSearch(User user, List<String> fields) {

        if (user != null && fields != null) {
            personQuery = dbAPI.advancedSearch(user, fields.get(FIRST_NAME_FIELD), fields.get(LAST_NAME_FIELD),
                    fields.get(MAJOR_FIELD), fields.get(CLASS_YEAR_FIELD), fields.get(CONCENTRATION_FIELD),
                    fields.get(SGA_FIELD), fields.get(USERNAME_FIELD), fields.get(CAMPUS_PHONE_FIELD),
                    fields.get(HIATUS_FIELD), fields.get(HOME_ADDRESS_FIELD), fields.get(FAC_STAFF_OFFICE_FIELD),
                    fields.get(CAMPUS_ADDRESS_FIELD), fields.get(BUILDING_DORM_FIELD), fields.get(POSITION_DESCRIPTION_FIELD));

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
    }

    public void authenticateUser(User user, List<String> fields) {
        if (user != null && fields != null) {
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
}