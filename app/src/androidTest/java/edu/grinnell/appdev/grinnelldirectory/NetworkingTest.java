package edu.grinnell.appdev.grinnelldirectory;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


@RunWith(AndroidJUnit4.class)
public class NetworkingTest {
    @Test
    public void testAuthentication() throws Exception {
        // TODO: 2/25/17 Implement!
    }

    @Test
    public void successfulSearchTests() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        final CountDownLatch latch = new CountDownLatch(2);

        User user = new User("test1stu", "selfserv1");
        DBAPICaller apiCaller = new DBAPICaller(user, new APICallerInterface() {
            @Override
            public void onSearchSuccess(List<Person> people) {
                assertNotNull(people);
                assertFalse(people.size() == 0);
                latch.countDown();
            }

            @Override
            public void authenticateUserCallSuccess(boolean success, Person person) {
                fail("Test Failed: Not testing authentication.");
                latch.countDown();
            }

            @Override
            public void onServerFailure(String fail_message) {
                fail("Test Failed: " + fail_message);
                latch.countDown();
            }

            @Override
            public void onNetworkingError(String fail_message) {
                fail("Test Failed: " + fail_message);
                latch.countDown();
            }
        });

        List<String> test_list_1 = new ArrayList();
        test_list_1.add(0, "Nicholas");
        test_list_1.add(1, "Roberson");
        test_list_1.add(2, "");
        test_list_1.add(3, "");

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
        apiCaller.simpleSearch(test_list_1);
        // Works
        apiCaller.search(test_list_3);

        latch.await();
    }

    @Test
    public void failingTest() throws Exception {

        final CountDownLatch latch = new CountDownLatch(2);

        User user = new User("test1stu", "selfserv1");
        DBAPICaller apiCaller = new DBAPICaller(user, new APICallerInterface() {
            @Override
            public void onSearchSuccess(List<Person> people) {
                fail("Test Failed: search should not return successful results");
                latch.countDown();
            }

            @Override
            public void authenticateUserCallSuccess(boolean success, Person person) {
                fail("Test Failed: search should not return successful results");
                latch.countDown();
            }

            @Override
            public void onServerFailure(String fail_message) {
                assertEquals("\"No records returned\"", fail_message);
                latch.countDown();
            }

            @Override
            public void onNetworkingError(String fail_message) {
                fail("Test Failed: " + fail_message);
                latch.countDown();
            }
        });

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

        // "Bad request"
        apiCaller.simpleSearch(test_list_1);

        // "No "
        apiCaller.search(test_list_3);

        latch.await();

    }

    @Test
    public void tooManyResultsTest() throws Exception {

        final CountDownLatch latch = new CountDownLatch(2);

        User user = new User("test1stu", "selfserv1");
        DBAPICaller apiCaller = new DBAPICaller(user, new APICallerInterface() {
            @Override
            public void onSearchSuccess(List<Person> people) {
                fail("Test Failed: search should not return successful results");
                latch.countDown();
            }

            @Override
            public void authenticateUserCallSuccess(boolean success, Person person) {
                fail("Test Failed: authenticate not called");
                latch.countDown();
            }

            @Override
            public void onServerFailure(String fail_message) {
                assertEquals("\"Search returned too many records.  Please narrow your search and try again.\"", fail_message);
                latch.countDown();
            }

            @Override
            public void onNetworkingError(String fail_message) {
                fail("Test Failed: " + fail_message);
                latch.countDown();
            }
        });

        // will return too many results
        List<String> test_list_4 = new ArrayList();
        test_list_4.add(0, "");
        test_list_4.add(1, "");
        test_list_4.add(2, "");
        test_list_4.add(3, "");
        test_list_4.add(4, "");
        test_list_4.add(5, "");
        test_list_4.add(6, "");
        test_list_4.add(7, "");
        test_list_4.add(8, "");
        test_list_4.add(9, "");
        test_list_4.add(10, "");
        test_list_4.add(11, "");
        test_list_4.add(12, "");
        test_list_4.add(13, "");

        // "Search returned too many records.  Please narrow your search and try again."
        apiCaller.simpleSearch(test_list_4);
        apiCaller.search(test_list_4);

        latch.await();
    }
}
