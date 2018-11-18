package edu.grinnell.appdev.grinnelldirectory;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.rules.*;

import java.io.IOException;
import java.io.InputStream;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import edu.grinnell.appdev.grinnelldirectory.activities.SearchResultsActivity;
import edu.grinnell.appdev.grinnelldirectory.models.SimpleResult;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;

/**
 * Test the SearchResultsActivity UI
 */
@RunWith(AndroidJUnit4.class)
@MediumTest
public class SearchResultsActivitiyTest {

    @Rule
    public IntentsTestRule<SearchResultsActivity> rule =
            new IntentsTestRule<>(SearchResultsActivity.class);

    @Before
    public void stubExternalIntent() {
        // stub the intent that needs to be given to this activity
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    /**
     * Test that items are displayed and that correct action is taken when clicked
     */
    @Test
    public void recyclerTest() {
        // set up the stub for the intent result required by this activity
        Intent result = new Intent();
        result.putExtra(SimpleResult.SIMPLE_KEY, loadJSONFromAsset("dummyData.json"));
        intending(hasComponent(hasShortClassName(".SearchFragmentInterface")))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, result));

        // check recyclerview is displayed
        ViewInteraction recycler = onView(allOf(withId(R.id.rvSearchResults), isDisplayed()));

        // perform a click action on arbitrary item
        recycler.perform(actionOnItemAtPosition(2, click()));

        // check that fragment is launched
        onView(withId(R.layout.activity_detail)).check(matches(isDisplayed()));
    }






    /**
     * A function to load dummy JSON data from an asset file for hermetically testing the activity
     * by simulating responses
     * @param filename - name of a JSON file to load data from into a String
     * @return - JSON string loaded from the asset file
     */
    private String loadJSONFromAsset(String filename) {
        String json;
        try {
            Context context = rule.getActivity().getApplicationContext();
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException io) {
            io.printStackTrace();
            return null;
        }
        return json;
    }

}
