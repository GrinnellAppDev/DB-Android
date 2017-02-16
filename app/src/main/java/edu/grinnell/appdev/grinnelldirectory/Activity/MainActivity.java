package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Interfaces.DatabaseAPI;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String baseUrl = "https://itwebappstest.grinnell.edu/DotNet/WebServices/api/";
    private Retrofit retrofit;
    private DatabaseAPI dbAPI;
    private Call<List<Person>> personQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    public void setup() {
        retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        dbAPI = retrofit.create(DatabaseAPI.class);

        User user = new User("test1stu", "selfserv1");
        makeAPICall(user);
    }

    public void makeAPICall(User user) {
        personQuery = dbAPI.getStudents(user, "Nicholas", "Roberson", "", "", "", "", "", "", "", "", "", "", "", "");

        personQuery.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.body() != null) {
                    Log.d("API_SUCCESS", "API returned list of people.");
                    // response.body() is the list of people, set to 'people'
                    List<Person> people = response.body();
                    Log.e("TEST", people.get(0).getClassYear().toString());
                } else {
                    try {
                        Log.e("BAD_CALL", response.errorBody().string());
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
}
