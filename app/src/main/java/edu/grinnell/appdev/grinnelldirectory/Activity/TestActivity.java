package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.Model.User;
import edu.grinnell.appdev.grinnelldirectory.R;

/**
 * Created by nicholasroberson on 2/22/17.
 */

/*
    NOT DECLARED IN MANIFEST ANYMORE
 */

public class TestActivity extends AppCompatActivity implements APICallerInterface {

    private DBAPICaller mDBApiCaller;
    private User user;
    private static final String BASE_URL = "https://itwebappstest.grinnell.edu/DotNet/WebServices/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User("test1stu", "selfserv1");
        mDBApiCaller = new DBAPICaller(user, this);


        runSuccessfulTests();
        runFailingTests();
    }

    public void runSuccessfulTests() {

        List<String> test_list_1 = new ArrayList();
        test_list_1.add(0, "Nicholas");
        test_list_1.add(1, "Roberson");
        test_list_1.add(2, "");
        test_list_1.add(3, "");

        List<String> test_list_2 = new ArrayList();
        test_list_2.add(0, user.getUsername());

        List<String> test_list_3 = new ArrayList();
        test_list_3.add(0, "Nicholas");
        test_list_3.add(1, "Roberson");
        test_list_3.add(2, "");
        test_list_3.add(3, "");

        test_list_3.add(4, "");
        test_list_3.add(5, "");
        test_list_3.add(6, "");
        test_list_3.add(7, "");
        test_list_3.add(8, "");
        test_list_3.add(9, "");
        test_list_3.add(10, "");
        test_list_3.add(11, "");
        test_list_3.add(12, "");
        test_list_3.add(13, "");
        test_list_3.add(14, "");

        // Works
        mDBApiCaller.simpleSearch(user, test_list_1);
        // Works
        mDBApiCaller.advancedSearch(user, test_list_3);

        // doesn't work currently, need to figure out how to get info from the user field.
        mDBApiCaller.authenticateUser(user, test_list_2);
    }

    public void runFailingTests() {

        // Not real major
        List<String> test_list_1 = new ArrayList();
        test_list_1.add(0, "Nicholas");
        test_list_1.add(1, "Roberson");
        test_list_1.add(2, "bananas");
        test_list_1.add(3, "");

        // Username doesnt exist
        List<String> test_list_2 = new ArrayList();
        test_list_2.add(0, "roberson");

        // not real class year for another century
        List<String> test_list_3 = new ArrayList();
        test_list_3.add(0, "Nicholas");
        test_list_3.add(1, "Roberson");
        test_list_3.add(2, "");
        test_list_3.add(3, "");
        test_list_3.add(4, "");
        test_list_3.add(5, "");
        test_list_3.add(6, "");
        test_list_3.add(7, "");
        test_list_3.add(8, "");
        test_list_3.add(9, "");
        test_list_3.add(10, "2117");
        test_list_3.add(11, "1aaaadsfhsfd");
        test_list_3.add(12, "");
        test_list_3.add(13, "");

        // will return too many results
        List<String> test_list_4 = new ArrayList();
        test_list_4.add(0, "");
        test_list_4.add(1, "");
        test_list_4.add(2, "");
        test_list_4.add(3, "");

        // "Bad request"
        mDBApiCaller.simpleSearch(user, test_list_1);

        // "Bad request"
        mDBApiCaller.advancedSearch(user, test_list_3);

        // "Bad request"
        mDBApiCaller.authenticateUser(user, test_list_2);

        // "Search returned too many records.  Please narrow your search and try again."
        mDBApiCaller.simpleSearch(user, test_list_4);
    }


    @Override
    public List<Person> onSearchSuccess(List<Person> people) {
        return people;
    }

    @Override
    public boolean authenticateUserCallSuccess(List<Person> people) {
        // get username from the email field of the result
        String username = people.get(0).getEmail().split("@")[0];

        if (user.getUsername().equals(username)) {
            Log.d("AUTH_USER_SUCCESS", "SUCCESS " + username + " vs. " +
                    user.getUsername() + " : matched. Returning true.");
            return true;
        } else {
            Toast.makeText(this, "Error: User authentication failed.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public String onServerFailure(String fail_message) {
        Toast.makeText(this, "Server Failure: " + fail_message, Toast.LENGTH_SHORT).show();
        return null;
    }

    @Override
    public boolean onNetworkingError(String fail_message) {
        Toast.makeText(this, "Networking Error: " + fail_message, Toast.LENGTH_SHORT).show();
        return false;
    }
}

