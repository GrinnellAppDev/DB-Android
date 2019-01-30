package edu.grinnell.appdev.grinnelldirectory;

import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchAPI;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchCallback;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.Query;
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

    private static final String BASE_URL = "https://www.cs.grinnell.edu/~pandeyan/appdev/";

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

    @Override public void search(Query query) {
        Call<List<Person>> call = searchEndpoint.advancedSearch(
            query.getLastName(),
            query.getFirstName(),
            query.getUserName(),
            query.getCampusPhone(),
            query.getCampusAddress(),
            query.getHomeAddress(),
            query.getClassYear(),
            query.getFacStaffOffice(),
            query.getMajor(),
            query.getConcentration(),
            query.getSgaPosition(),
            query.getOnHiatus()
        );
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
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
