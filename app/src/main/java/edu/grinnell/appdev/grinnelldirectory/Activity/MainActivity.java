package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Interfaces.DatabaseAPI;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements APICallerInterface{

    private String baseUrl = "https://itwebappstest.grinnell.edu/DotNet/WebServices/api/";
    private Retrofit retrofit;
    private DatabaseAPI dbAPI;
    private Call<List<Person>> personQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APICaller apiCaller = new APICaller(new User("test1stu","selfserv1"));

    }
}
