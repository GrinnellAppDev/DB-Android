package edu.grinnell.appdev.grinnelldirectory;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DatabaseAPI;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DBAPICaller {

    private static final String BASE_URL = "https://itwebappstest.grinnell.edu/DotNet/WebServices/api/";
    private static final int RESPONSE_FORBIDDEN = 403;

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

    /*
    public DBAPICaller(User user, APICallerInterface apiInterface, String BASE_URL) {
        super(user,apiInterface,BASE_URL);
    }

    */

    public DBAPICaller(User user, APICallerInterface apiInterface) {
        mRetrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        dbAPI = mRetrofit.create(DatabaseAPI.class);
        apiCallerInterface = apiInterface;
        this.user = user;
    }

    public void simpleSearch(List<String> fields) {
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
                            apiCallerInterface.onServerFailure(response.errorBody().string());
                        } catch (IOException e) {
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

    public void advancedSearch(List<String> fields) {

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
                            apiCallerInterface.onServerFailure(response.errorBody().string());
                        } catch (IOException e) {
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

    public void authenticateUser() {
        if (user != null) {
            personQuery = dbAPI.authenticateUser(user, user.getUsername());

            personQuery.enqueue(new Callback<List<Person>>() {
                @Override
                public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                    if (response.isSuccessful()) {
                        // TODO: 2/26/17 return the Person associated with the username instead of null
                        apiCallerInterface.authenticateUserCallSuccess(true, null);
                    } else if (response.code() == RESPONSE_FORBIDDEN) {
                        apiCallerInterface.authenticateUserCallSuccess(false, null);
                    } else {
                        try {
                            apiCallerInterface.onServerFailure(response.errorBody().string());
                        } catch (IOException e) {
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