package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Interfaces.DatabaseAPI;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import edu.grinnell.appdev.grinnelldirectory.R;

public class MainActivity extends AppCompatActivity {

    public String baseUrl = "https://itwebappstest.grinnell.edu/DotNet/WebServices/api/";
    public Retrofit retrofit;
    public  DatabaseAPI dbAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        Log.e("STARTED_MAIN","MAIN ACTIVITY STARTED");
        User user = new User("test1stu","selfserv1");
        makeAPICall(user);
    }

    public void makeAPICall(User user) {
        constructCall();

        Call<List<Person>> personQuery = dbAPI.getStudents(user,"Prabir","Pradhan");
        personQuery.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                //Log.e("RESPONSE",response.body().getClassYear());
                if (response.body() != null) {
                    Log.d("API_SUCCESS", "API returned list of people.");
                    // response.body() is the list of people, set to 'people'
                    List<Person> people = response.body();

                } else {
                    Log.e("API_FAILURE", "onResult() called but fields were null");
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.e("API_FAILURE", t.toString());
            }
        });

    }

    public void constructCall() {
        dbAPI = retrofit.create(DatabaseAPI.class);
    }
}
