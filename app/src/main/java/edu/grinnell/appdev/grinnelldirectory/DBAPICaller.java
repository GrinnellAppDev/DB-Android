package edu.grinnell.appdev.grinnelldirectory;

import android.util.Log;

import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchAPI;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchCallback;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.models.DBRespoonse;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Executes simple and advanced search
 */

public class DBAPICaller implements SearchCaller {

    private static final String BASE_URL = "https://appdev.grinnell.edu/api/db/v1/";

    private DbSearchAPI searchEndpoint;
    private DbSearchCallback callback;

    public DBAPICaller(DbSearchCallback callback) {
        Retrofit retrofit = new Retrofit.Builder().
            baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            build();
        searchEndpoint = retrofit.create(DbSearchAPI.class);
        this.callback = callback;
    }

    @Override
    public void simpleSearch(String lastName, String firstName, String major, String classYear, String cookie) {
        advancedSearch(
            lastName,
            firstName,
            "",
            "",
            "",
            "",
            "",
            "",
             cookie
        );
    }

    @Override public void advancedSearch(String lastName, String firstName, String userName,
        String phone, String address,String classYear, String major, String sga, String cookie) {
        Call<DBRespoonse> call = searchEndpoint.advancedSearch(
            lastName,
            firstName,
            userName,
            phone,
            address,
            classYear,
            major,
            sga,
            cookie
        );
        call.enqueue(new Callback<DBRespoonse>() {
            @Override
            public void onResponse(Call<DBRespoonse> call, Response<DBRespoonse> response) {
                if (response.isSuccessful()) {
                    Log.e("response", response.body().toString());
                    callback.onSuccess(response.body());
                } else {
                    callback.onServerError(response.code(), response.errorBody());
                    response.errorBody().close();
                }
            }

            @Override public void onFailure(Call<DBRespoonse> call, Throwable t) {
                Log.e("error", call.toString());
                Log.e("error", t.getMessage());
                callback.onNetworkError(t.toString());
            }
        });
    }
}
