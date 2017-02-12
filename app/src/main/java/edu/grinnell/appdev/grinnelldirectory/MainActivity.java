package edu.grinnell.appdev.grinnelldirectory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import edu.grinnell.appdev.grinnelldirectory.Interfaces.DatabaseAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public String baseUrl = "https://itwebappstest.grinnell.edu/";
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
        makeAPICall("Prabir","Pradhan");
    }

    public void makeAPICall(String fname, String lname) {
        constructCall();

        Call<Person> personQuery = dbAPI.getStudent(fname,lname);
        personQuery.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                //Log.e("RESPONSE",response.body().getClassYear());
                if (response.body() != null) {
                    Log.d("API_SUCCESS", "!");
                    Log.e("SOME_SHIT",response.body().getClassYear());
                    //startWeatherOverview(response);
                } else {
                    Log.e("API_FAILURE", "onResult() called but fields were null");
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("API_FAILURE", t.toString());
            }
        });

    }

    public void constructCall() {
        dbAPI = retrofit.create(DatabaseAPI.class);
    }
}
