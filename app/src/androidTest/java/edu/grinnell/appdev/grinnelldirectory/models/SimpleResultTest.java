package edu.grinnell.appdev.grinnelldirectory.models;

import android.os.Bundle;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;


public class SimpleResultTest {

    @Test
    public void unparceledParcelEqualityTest() {
        Person personA = new Person();
        personA.setFirstName("Banana");
        personA.setLastName("Bread");

        Person personB = new Person();
        personB.setFirstName("Lorem");
        personB.setLastName("Ipsum");
        personB.setMajor("Biology");

        List<Person> people = new ArrayList();
        people.add(personA);
        people.add(personB);
        Bundle bundle = new Bundle();
        bundle.putParcelable(SimpleResult.SIMPLE_KEY, new SimpleResult(people));

        SimpleResult result = bundle.getParcelable(SimpleResult.SIMPLE_KEY);
        assertNotNull("SimpleResult should not be null", result);

        List<Person> newPeople = result.getPeople();

        people.equals(newPeople); // this won't work because Person is an object
        assertEquals("The SimpleResults should have the same length", people.size(), newPeople.size());

        for (int i = 0; i < people.size(); i++) {
            Gson gson = new Gson();
            String peopleJson = gson.toJson(people);
            String newPeopleJson = gson.toJson(newPeople);
            assertTrue("Each person should equal each new person", peopleJson.equals(newPeopleJson));
        }
    }

}