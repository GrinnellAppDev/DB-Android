package edu.grinnell.appdev.grinnelldirectory;

import android.util.Log;
import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchAPI;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchCallback;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.User;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static edu.grinnell.appdev.grinnelldirectory.constants.searchConstansts.FIRST_NAME_FIELD;

public class DBAPICaller implements SearchCaller {

    private static final String BASE_URL = "https://itwebappstest.grinnell.edu/DotNet/WebServices/api/";
    private static final int RESPONSE_FORBIDDEN = 403;

    private DbSearchAPI searchEndpoint;
    private Call<List<Person>> personQuery;
    private DbSearchCallback callback;
    //private User user;

    public DBAPICaller(DbSearchCallback callback) {
        Retrofit retrofit = new Retrofit.Builder().
            baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            build();
        searchEndpoint = retrofit.create(DbSearchAPI.class);
        this.callback = callback;
        //this.user = user;
    }

    //public void simpleSearch(List<String> fields) {
    //    if (user != null && fields != null) {
    //        personQuery = searchEndpoint.simpleSearch(user, fields.get(FIRST_NAME_FIELD), fields.get(LAST_NAME_FIELD),
    //                fields.get(MAJOR_FIELD), fields.get(CLASS_YEAR_FIELD));
    //
    //        personQuery.enqueue(new Callback<List<Person>>() {
    //
    //            @Override
    //            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
    //                if (response.isSuccessful()) {
    //                    List<Person> people = response.body();
    //                    apiCallerInterface.onSearchSuccess(people);
    //                } else {
    //                    try {
    //                        apiCallerInterface.onServerFailure(response.errorBody().string());
    //                    } catch (IOException e) {
    //                        apiCallerInterface.onServerFailure(e.toString());
    //                    }
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<List<Person>> call, Throwable t) {
    //                apiCallerInterface.onNetworkingError(t.toString());
    //                Log.e("API_SIMPLE_FAILURE", t.toString());
    //                apiCallerInterface.onNetworkingError(t.getMessage());
    //            }
    //        });
    //    }
    //}

    //public void advancedSearch(List<String> fields) {
    //
    //    if (user != null && fields != null) {
    //        personQuery = searchEndpoint.advancedSearch(user,
    //                fields.get(FIRST_NAME_FIELD),
    //                fields.get(LAST_NAME_FIELD),
    //                fields.get(MAJOR_FIELD),
    //                fields.get(FAC_STAFF_OFFICE_FIELD),
    //                fields.get(CONCENTRATION_FIELD),
    //                fields.get(SGA_FIELD),
    //                fields.get(USERNAME_FIELD),
    //                fields.get(CAMPUS_PHONE_FIELD),
    //                fields.get(HIATUS_FIELD),
    //                fields.get(HOME_ADDRESS_FIELD),
    //                fields.get(CLASS_YEAR_FIELD),
    //                fields.get(CAMPUS_ADDRESS_FIELD),
    //                fields.get(BUILDING_DORM_FIELD),
    //                fields.get(POSITION_DESCRIPTION_FIELD));
    //
    //        personQuery.enqueue(new Callback<List<Person>>() {
    //
    //            @Override
    //            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
    //                if (response.isSuccessful()) {
    //                    List<Person> people = response.body();
    //                    apiCallerInterface.onSearchSuccess(people);
    //                } else {
    //                    try {
    //                        apiCallerInterface.onServerFailure(response.errorBody().string());
    //                    } catch (IOException e) {
    //                        apiCallerInterface.onServerFailure(e.toString());
    //                    }
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<List<Person>> call, Throwable t) {
    //                apiCallerInterface.onNetworkingError(t.toString());
    //            }
    //        });
    //    }
    //}

    //public void authenticateUser() {
    //    if (user != null) {
    //        personQuery = searchEndpoint.authenticateUser(user, user.getUsername());
    //
    //        personQuery.enqueue(new Callback<List<Person>>() {
    //            @Override
    //            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
    //                if (response.isSuccessful()) {
    //                    // TODO: 2/26/17 return the Person associated with the username instead of null
    //                    apiCallerInterface.authenticateUserCallSuccess(true, null);
    //                } else if (response.code() == RESPONSE_FORBIDDEN) {
    //                    apiCallerInterface.authenticateUserCallSuccess(false, null);
    //                } else {
    //                    try {
    //                        apiCallerInterface.onServerFailure(response.errorBody().string());
    //                    } catch (IOException e) {
    //                        apiCallerInterface.onServerFailure(e.toString());
    //                    }
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<List<Person>> call, Throwable t) {
    //                apiCallerInterface.onNetworkingError(t.toString());
    //            }
    //        });
    //    }
    //}

    @Override
    public void simpleSearch(String lastName, String firstName, String major, String classYear) {
        advancedSearch(
            lastName,
            firstName,
            "",
            "",
            "",
            "",
            classYear,
            "",
            major,
            "",
            "",
            ""
        );
    }

    @Override public void advancedSearch(String lastName, String firstName, String userName,
        String campusPhone, String campusAddress, String homeAddress, String classYear,
        String facStaffOffice, String major, String concentration, String sgaPosition,
        String onHiatus) {
        Call<List<Person>> call = searchEndpoint.advancedSearch(
            lastName,
            firstName,
            userName,
            campusPhone,
            campusAddress,
            homeAddress,
            classYear,
            facStaffOffice,
            major,
            concentration,
            sgaPosition,
            onHiatus
        );
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    String errorMessage;
                    callback.onServerError(response.code(), response.errorBody());
                    response.errorBody().close();
                }
            }

            @Override public void onFailure(Call<List<Person>> call, Throwable t) {
                callback.onNetworkError(t.toString());
            }
        });
    }
}
