package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
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

public class MainActivity extends AppCompatActivity  implements APICallerInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User("test1stu", "selfserv1");

        APICaller apiCaller = new APICaller(user);
        List<String> list = new ArrayList();
        list.add(0,"Nicholas");
        list.add(1,"Roberson");
        list.add(2,"");
        list.add(3,"");

        apiCaller.simpleSearch(user, list);

    }

    @Override
    public List<Person> simpleSearchCall(List<Person> people) {
        Log.e("TEST_INTERFACE_MAIN",people.get(0).getClassYear().toString());
        return null;
    }

    @Override
    public List<Person> advancedSearchCall(List<Person> people) {
        Log.e("TEST_INTERFACE_MAIN",people.get(0).getClassYear().toString());
        return null;
    }

    @Override
    public List<Person> authenticateUserCall(List<Person> people) {
        Log.e("TEST_INTERFACE_MAIN",people.get(0).getClassYear().toString());
        return null;
    }
}
