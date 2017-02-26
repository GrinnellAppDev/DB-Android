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
<<<<<<< HEAD
    }

    public void advancedSearch(User user, List<String> fields) {
    }

    public void authenticateUser(User user, List<String> fields) {
=======
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
>>>>>>> a8a53b1af8269cc212a9bfb3f6adff758fd944e1
    }

}
