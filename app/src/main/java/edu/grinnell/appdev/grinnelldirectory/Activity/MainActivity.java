package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.Model.Person;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.User;


public class MainActivity extends AppCompatActivity implements APICallerInterface {


    private APICaller apiCaller;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User("test1stu", "selfserv1");
        apiCaller = new APICaller(user, this);


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
        apiCaller.simpleSearch(user, test_list_1);
        // Works
        apiCaller.advancedSearch(user, test_list_3);

        // doesn't work currently, need to figure out how to get info from the user field.
        apiCaller.authenticateUser(user, test_list_2);
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
        test_list_3.add(10, "");
        test_list_3.add(11, "2117");
        test_list_3.add(12, "aaaa");
        test_list_3.add(13, "");
        test_list_3.add(14, "");

        // will return too many results
        List<String> test_list_4 = new ArrayList();
        test_list_4.add(0, "");
        test_list_4.add(1, "");
        test_list_4.add(2, "");
        test_list_4.add(3, "");

        // "Bad request"
        apiCaller.simpleSearch(user, test_list_1);

        // "Bad request"
        apiCaller.advancedSearch(user, test_list_3);

        // "Bad request"
        apiCaller.authenticateUser(user, test_list_2);

        // "Search returned too many records.  Please narrow your search and try again."
        apiCaller.simpleSearch(user, test_list_4);
    }


    @Override
    public List<Person> simpleSearchCallSuccess(List<Person> people) {
        Log.d("SIMPLE_SEARCH_SUCCESS", "Returned " + people.size() + " result(s).");
        return people;
    }

    @Override
    public List<Person> advancedSearchCallSuccess(List<Person> people) {
        Log.d("ADV_SEARCH_SUCCESS", "Returned " + people.size() + " result(s).");
        return people;
    }

    @Override
    public boolean authenticateUserCallSuccess(List<Person> people) {

        // get username from the email field of the result
        String username = people.get(0).getEmail().split("@")[0];

        Log.d("USERNAME_COMP",username + " vs. "  + user.getUsername());

        if (user.getUsername().equals(username)) {
            Log.d("AUTH_USER_SUCCESS", "SUCCESS " + username + " vs. " +
                    user.getUsername() + " : matched. Returning true.");
            return true;
        } else {
            Log.e("AUTH_USER_FAIL", "CONFLICT : " + username + " vs. " +
                    user.getUsername() + " : did not match. Returning false.");
            return false;
        }
    }

    @Override
    public String onServerFailure(String fail_message) {
        Log.e("ON_SERVER_FAIL", fail_message);
        return null;
    }

    @Override
    public boolean onNetworkingError(String fail_message) {
        Log.e("ON_NETWORK_FAIL", fail_message);
        return false;
    }
}
