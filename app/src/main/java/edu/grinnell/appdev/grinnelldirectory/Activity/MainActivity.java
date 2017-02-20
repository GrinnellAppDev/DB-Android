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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User("test1stu", "selfserv1");

        APICaller apiCaller = new APICaller(user);
        List<String> test_list_1 = new ArrayList();
        test_list_1.add(0, "Nicholas");
        test_list_1.add(1, "Roberson");
        test_list_1.add(2, "");
        test_list_1.add(3, "");

        List<String> test_list_2 = new ArrayList();
        test_list_2.add(0, "test1stu");

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
        // apiCaller.authenticateUser(user, test_list_2);
    }


    @Override
    public List<Person> simpleSearchCall(List<Person> people) {
        Log.d("TEST_INTERFACE_API_MAIN", people.get(0).getClassYear().toString());
        return null;
    }

    @Override
    public List<Person> advancedSearchCall(List<Person> people) {
        return null;
    }

    @Override
    public boolean authenticateUserCall(List<Person> people) {
        return false;
    }
}
